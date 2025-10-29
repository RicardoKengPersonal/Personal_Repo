package eapli.shodrone.dronemanagement.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.shodrone.dronemanagement.domain.MaintenanceType;
import eapli.shodrone.dronemanagement.repository.DroneModelRepository;
import eapli.shodrone.dronemanagement.repository.DroneRepository;
import eapli.shodrone.dronemanagement.repository.MaintenanceTypeRepository;
import eapli.shodrone.usermanagement.domain.Roles;

import java.util.Optional;

public class EditMaintenanceTypeController {

    private final AuthorizationService authz;
    private final DroneInventoryService service;

    public EditMaintenanceTypeController(AuthorizationService authz, final DroneModelRepository modelRepo,
                                         final DroneRepository droneRepo, final MaintenanceTypeRepository maintenanceTypeRepository) {
        this.authz = authz;
        this.service = new DroneInventoryService(modelRepo, droneRepo, maintenanceTypeRepository);
    }

    public Optional<MaintenanceType> findMaintenanceTypeById(Long id) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);
        return service.findMaintenanceTypeById(id);
    }

    public boolean canEditMaintenanceType(MaintenanceType maintenanceType) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);
        return !service.existsMaintenanceRecordsByType(maintenanceType);
    }

    public void editMaintenanceType(Long id, String newName, String newDescription) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);
        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("Maintenance type name cannot be null or empty.");
        }

        service.editMaintenanceType(id, newName, newDescription);
    }
}
