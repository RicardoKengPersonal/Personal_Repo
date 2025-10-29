package eapli.shodrone.dronemanagement.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.shodrone.dronemanagement.repository.DroneModelRepository;
import eapli.shodrone.dronemanagement.repository.DroneRepository;
import eapli.shodrone.dronemanagement.repository.MaintenanceTypeRepository;
import eapli.shodrone.usermanagement.domain.Roles;

public class DroneLanguageValidatorController {

    private final AuthorizationService authz;
    private final DroneInventoryService service;

    public DroneLanguageValidatorController(final AuthorizationService authz,
                                            final DroneRepository droneRepo,
                                            final DroneModelRepository modelRepo,
                                            final MaintenanceTypeRepository maintenanceTypeRepository) {
        this.authz = authz;
        this.service = new DroneInventoryService(modelRepo, droneRepo, maintenanceTypeRepository);
    }

    public boolean validateDroneProgram(String filePath, String languageType) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);
        return service.validateDroneProgram(filePath, languageType);
    }
}
