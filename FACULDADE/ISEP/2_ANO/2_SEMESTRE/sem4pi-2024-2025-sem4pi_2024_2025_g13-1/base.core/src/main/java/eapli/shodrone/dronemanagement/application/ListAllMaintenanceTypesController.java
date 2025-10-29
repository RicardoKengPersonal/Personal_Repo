package eapli.shodrone.dronemanagement.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.shodrone.dronemanagement.domain.Drone;
import eapli.shodrone.dronemanagement.domain.MaintenanceType;
import eapli.shodrone.dronemanagement.repository.DroneModelRepository;
import eapli.shodrone.dronemanagement.repository.DroneRepository;
import eapli.shodrone.dronemanagement.repository.MaintenanceTypeRepository;
import eapli.shodrone.usermanagement.domain.Roles;


public class ListAllMaintenanceTypesController {

    private final AuthorizationService authz;
    private final DroneInventoryService service;

    public ListAllMaintenanceTypesController(AuthorizationService authz, final DroneRepository droneRepo, final DroneModelRepository modelRepo, final MaintenanceTypeRepository maintenanceTypeRepository) {
        this.authz = authz;
        this.service = new DroneInventoryService(modelRepo,droneRepo,maintenanceTypeRepository);
    }

    public Iterable<MaintenanceType> findAllMaintenanceTypes() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);
        return service.findAllMaintenanceTypes();
    }
}
