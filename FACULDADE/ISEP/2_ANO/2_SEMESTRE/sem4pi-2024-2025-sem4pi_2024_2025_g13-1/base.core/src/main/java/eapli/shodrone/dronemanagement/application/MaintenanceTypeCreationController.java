package eapli.shodrone.dronemanagement.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.shodrone.dronemanagement.repository.DroneModelRepository;
import eapli.shodrone.dronemanagement.repository.DroneRepository;
import eapli.shodrone.dronemanagement.repository.MaintenanceTypeRepository;
import eapli.shodrone.usermanagement.domain.Roles;

public class MaintenanceTypeCreationController {

    private final AuthorizationService authz;
    private final DroneInventoryService service;

    public MaintenanceTypeCreationController(AuthorizationService authz, final DroneModelRepository modelRepo, final DroneRepository droneRepo, final MaintenanceTypeRepository maintenanceTypeRepository) {
        this.authz = authz;
        this.service = new DroneInventoryService(modelRepo,droneRepo,maintenanceTypeRepository);
    }

    public void addMaintenanceType(
            final String name,
            final String description
    ) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);
        service.addMaintenanceType(name,description);
    }

}
