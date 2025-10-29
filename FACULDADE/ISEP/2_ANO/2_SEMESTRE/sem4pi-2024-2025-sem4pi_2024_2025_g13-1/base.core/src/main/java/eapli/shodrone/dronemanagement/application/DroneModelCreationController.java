package eapli.shodrone.dronemanagement.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.shodrone.dronemanagement.domain.DroneType;
import eapli.shodrone.dronemanagement.repository.DroneModelRepository;
import eapli.shodrone.dronemanagement.repository.MaintenanceTypeRepository;
import eapli.shodrone.usermanagement.domain.Roles;
import eapli.shodrone.dronemanagement.repository.DroneRepository;

import java.util.Calendar;
import java.util.Set;

@UseCaseController
public class DroneModelCreationController {

    private final AuthorizationService authz;
    private final DroneInventoryService service;


    public DroneModelCreationController(AuthorizationService authz, final DroneModelRepository modelRepo, final DroneRepository droneRepo, final MaintenanceTypeRepository maintenanceTypeRepository) {
        this.authz = authz;
        this.service = new DroneInventoryService(modelRepo,droneRepo,maintenanceTypeRepository);
    }

    public void addDroneModel(
            final String name,
            final String manufacturer,
            final float xAxisTolerance,
            final float yAxisTolerance,
            final float zAxisTolerance,
            final Calendar ofDay,
            final float maxSpeed,
            final float maxRotationSpeed,
            final Set<DroneType> lightingOptions
    ) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);
        service.registerDroneModel(name, manufacturer, xAxisTolerance, yAxisTolerance, zAxisTolerance, ofDay, maxSpeed, maxRotationSpeed, lightingOptions);
    }
}
