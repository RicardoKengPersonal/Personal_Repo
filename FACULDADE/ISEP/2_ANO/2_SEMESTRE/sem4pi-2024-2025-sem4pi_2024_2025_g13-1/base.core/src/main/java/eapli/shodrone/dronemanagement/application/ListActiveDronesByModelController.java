package eapli.shodrone.dronemanagement.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.shodrone.dronemanagement.domain.Drone;
import eapli.shodrone.dronemanagement.domain.DroneModel;
import eapli.shodrone.dronemanagement.domain.DroneStatus;
import eapli.shodrone.dronemanagement.repository.DroneModelRepository;
import eapli.shodrone.dronemanagement.repository.DroneRepository;
import eapli.shodrone.dronemanagement.repository.MaintenanceTypeRepository;
import eapli.shodrone.usermanagement.domain.Roles;

import java.util.Optional;


@UseCaseController
public class ListActiveDronesByModelController {

    private final AuthorizationService authz;
    private final DroneInventoryService service;


    public ListActiveDronesByModelController(AuthorizationService authz, final DroneRepository droneRepo, final DroneModelRepository modelRepo, final MaintenanceTypeRepository maintenanceTypeRepository) {
        this.authz = authz;
        this.service = new DroneInventoryService(modelRepo,droneRepo,maintenanceTypeRepository);
    }

    public Optional<DroneModel> findDroneModelByName(String name) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);
        return service.findDroneModelByName(name);
    }

    public Iterable<Drone> findByModelAndStatus(DroneModel model, DroneStatus status) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);
        return service.findByModelAndStatus(model,status);
    }

    public Iterable<Drone> findAllDrones() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);
        return service.findAllDrones();
    }
}
