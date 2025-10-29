package eapli.shodrone.infrastructure.bootstrapers.demo;

import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.shodrone.dronemanagement.application.DroneInventoryService;
import eapli.shodrone.dronemanagement.domain.DroneID;
import eapli.shodrone.dronemanagement.domain.DroneModel;
import eapli.shodrone.dronemanagement.domain.DroneStatus;
import eapli.shodrone.dronemanagement.repository.DroneModelRepository;
import eapli.shodrone.dronemanagement.repository.DroneRepository;
import eapli.shodrone.dronemanagement.repository.MaintenanceTypeRepository;
import eapli.shodrone.infrastructure.bootstrapers.TestDataConstants;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Optional;

public class DroneBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(DroneBootstrapper.class);

    private final DroneRepository droneRepo = PersistenceContext.repositories().drone();
    private final DroneModelRepository modelRepo = PersistenceContext.repositories().droneModel();
    private final MaintenanceTypeRepository maintenanceRepo = PersistenceContext.repositories().maintenanceType();
    private final DroneInventoryService service = new DroneInventoryService(modelRepo, droneRepo, maintenanceRepo);

    @Override
    public boolean execute() {
        int serialCounter = 1;

        String[] modelNames = {
                TestDataConstants.DRONE_MODEL_NAME1,
                TestDataConstants.DRONE_MODEL_NAME2,
                TestDataConstants.DRONE_MODEL_NAME3
        };

        for (String modelName : modelNames) {
            Optional<DroneModel> optionalModel = modelRepo.findByNameIgnoreCase(modelName);

            if (optionalModel.isEmpty()) {
                LOGGER.warn("⚠ DroneModel '{}' not found. Skipping drone registration.", modelName);
                continue;
            }

            DroneModel model = optionalModel.get();

            for (int i = 1; i <= 20; i++) {
                String serial = String.format("DRONE%03d", serialCounter++);
                String name = modelName + " Unit " + i;
                LocalDate date = LocalDate.of(2024, 10, 1).plusDays(i);
                registerDrone(serial, null, name, model, date);
            }
        }

        return true;
    }

    private void registerDrone(String serialNumber, String programmingLanguage, String droneName, DroneModel model, LocalDate acquisitionDate) {
        try {
            service.addToInventoryDrone(new DroneID(serialNumber), programmingLanguage, droneName, model, acquisitionDate, DroneStatus.ACTIVE);
            LOGGER.info("✅ Drone '{}' registered successfully.", serialNumber);
        } catch (IntegrityViolationException | ConcurrencyException e) {
            LOGGER.warn("⚠ Could not register drone '{}'.", serialNumber);
            LOGGER.trace("Details: ", e);
        }
    }
}
