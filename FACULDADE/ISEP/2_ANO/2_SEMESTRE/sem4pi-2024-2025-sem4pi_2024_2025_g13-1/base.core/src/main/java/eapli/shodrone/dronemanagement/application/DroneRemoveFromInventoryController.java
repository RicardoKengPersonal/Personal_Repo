package eapli.shodrone.dronemanagement.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.shodrone.dronemanagement.domain.Drone;
import eapli.shodrone.dronemanagement.domain.DroneID;
import eapli.shodrone.dronemanagement.domain.DroneModel;
import eapli.shodrone.dronemanagement.domain.DroneStatus;
import eapli.shodrone.dronemanagement.repository.DroneModelRepository;
import eapli.shodrone.dronemanagement.repository.MaintenanceTypeRepository;
import eapli.shodrone.usermanagement.domain.Roles;
import eapli.shodrone.dronemanagement.repository.DroneRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@UseCaseController
public class DroneRemoveFromInventoryController {

    private final AuthorizationService authz;
    private final DroneInventoryService service;

    private static final Set<Role> ALLOWED_ROLES = Set.of(Roles.DRONE_TECH);

    public DroneRemoveFromInventoryController(AuthorizationService authz, final DroneRepository droneRepo, final DroneModelRepository modelRepo, final MaintenanceTypeRepository maintenanceTypeRepository) {
        this.authz = authz;
        this.service = new DroneInventoryService(modelRepo,droneRepo,maintenanceTypeRepository);
    }

    public void removeFromInventory(String droneName, String reason, LocalDate date, DroneStatus newStatus) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);
        service.removeFromInventory(droneName, reason, date,newStatus);
    }

    public Optional<Drone> findDroneByName(String droneName) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);
        return service.findDroneByName(droneName);
    }

    public boolean isDroneRepositoryEmpty() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);
        return service.isDroneRepositoryEmpty();
    }

    public Iterable<Drone> findAllDrones() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);
        return service.findAllDrones();
    }

}
