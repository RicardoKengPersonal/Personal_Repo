package eapli.shodrone.dronemanagement.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.shodrone.dronemanagement.domain.Drone;
import eapli.shodrone.dronemanagement.domain.DroneModel;
import eapli.shodrone.dronemanagement.repository.DroneModelRepository;
import eapli.shodrone.dronemanagement.repository.DroneRepository;
import eapli.shodrone.dronemanagement.repository.MaintenanceTypeRepository;
import eapli.shodrone.usermanagement.domain.Roles;

import java.util.Optional;

public class DroneLanguageController {
    private final AuthorizationService authz;
    private final DroneInventoryService service;

    public DroneLanguageController(AuthorizationService authz,
                                                      DroneModelRepository modelRepo,
                                                      DroneRepository droneRepo,
                                                      MaintenanceTypeRepository maintenanceTypeRepository) {
        this.authz = authz;
        this.service = new DroneInventoryService(modelRepo, droneRepo, maintenanceTypeRepository);
    }

    public void assignLanguageToDrone(Drone drone, String language) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);
        service.setDroneProgrammingLanguage(drone, language);
    }

    public Optional<Drone> findDroneByName(String droneName) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);
        return service.findDroneByName(droneName);
    }
}
