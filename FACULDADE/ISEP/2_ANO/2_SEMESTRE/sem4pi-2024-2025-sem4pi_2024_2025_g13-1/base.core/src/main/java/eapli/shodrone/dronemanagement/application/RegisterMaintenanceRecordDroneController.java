package eapli.shodrone.dronemanagement.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.shodrone.dronemanagement.domain.Drone;
import eapli.shodrone.dronemanagement.domain.MaintenanceType;
import eapli.shodrone.dronemanagement.repository.DroneModelRepository;
import eapli.shodrone.dronemanagement.repository.DroneRepository;
import eapli.shodrone.dronemanagement.repository.MaintenanceTypeRepository;
import eapli.shodrone.usermanagement.domain.Roles;

import java.time.LocalDate;

public class RegisterMaintenanceRecordDroneController {

    private final AuthorizationService authz;
    private final DroneInventoryService service;

    public RegisterMaintenanceRecordDroneController(AuthorizationService authz,
                                                    final DroneModelRepository modelRepo,
                                                    final DroneRepository droneRepo,
                                                    final MaintenanceTypeRepository maintenanceTypeRepository) {
        this.authz = authz;
        this.service = new DroneInventoryService(modelRepo, droneRepo, maintenanceTypeRepository);
    }

    public Iterable<Drone> listAllDrones() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);
        return service.findAllDrones();
    }

    public Iterable<MaintenanceType> listAllMaintenanceTypes() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);
        return service.findAllMaintenanceTypes();
    }

    public void registerMaintenanceRecord(Drone drone, MaintenanceType type, LocalDate date) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);
        service.registerMaintenanceRecord(drone, type, date);
    }
}
