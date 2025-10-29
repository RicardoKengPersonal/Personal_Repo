package eapli.shodrone.infrastructure.bootstrapers.demo;

import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.shodrone.dronemanagement.application.DroneInventoryService;
import eapli.shodrone.dronemanagement.domain.DroneType;
import eapli.shodrone.infrastructure.bootstrapers.TestDataConstants;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class DroneModelBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(DroneModelBootstrapper.class);

    @Override
    public boolean execute() {
        final Calendar now = Calendar.getInstance();
        final Set<DroneType> lightingOptions = new HashSet<>();
        lightingOptions.add(DroneType.LED); // adicionar outras opções se necessário

        bootstrapModel(
                TestDataConstants.DRONE_MODEL_NAME1,
                TestDataConstants.DRONE_MODEL_MANUFACTURER1,
                0.1f, 0.1f, 0.1f,
                now,
                5.0f,
                180.0f,
                lightingOptions
        );

        bootstrapModel(
                TestDataConstants.DRONE_MODEL_NAME2,
                TestDataConstants.DRONE_MODEL_MANUFACTURER2,
                0.2f, 0.2f, 0.2f,
                now,
                10.0f,
                200.0f,
                lightingOptions
        );

        bootstrapModel(
                TestDataConstants.DRONE_MODEL_NAME3,
                TestDataConstants.DRONE_MODEL_MANUFACTURER3,
                0.3f, 0.3f, 0.3f,
                now,
                20.0f,
                400.0f,
                lightingOptions
        );

        return true;
    }

    private void bootstrapModel(
            final String name,
            final String manufacturer,
            final float xAxisTolerance,
            final float yAxisTolerance,
            final float zAxisTolerance,
            final Calendar day,
            final float maxSpeed,
            final float maxRotation,
            final Set<DroneType> lightingOptions) {

        final var modelRepo = PersistenceContext.repositories().droneModel();

        if (modelRepo.findByNameIgnoreCase(name).isPresent()) {
            LOGGER.info("DroneModel '{}' already exists, skipping.", name);
            return;
        }

        final var droneRepo = PersistenceContext.repositories().drone();
        final var maintenanceRepo = PersistenceContext.repositories().maintenanceType();
        final DroneInventoryService service = new DroneInventoryService(modelRepo, droneRepo,maintenanceRepo);

        try {
            service.registerDroneModel(name, manufacturer, xAxisTolerance, yAxisTolerance, zAxisTolerance,
                    day, maxSpeed, maxRotation, lightingOptions);
            LOGGER.info("✅ DroneModel '{}' bootstrapped.", name);
        } catch (IntegrityViolationException | ConcurrencyException e) {
            LOGGER.warn("⚠ Could not bootstrap DroneModel '{}'.", name);
            LOGGER.trace("Details: ", e);
        }
    }
}
