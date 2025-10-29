package eapli.shodrone.dronemanagement.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.shodrone.dronemanagement.domain.DroneID;
import eapli.shodrone.dronemanagement.domain.DroneModel;
import eapli.shodrone.dronemanagement.domain.DroneStatus;
import eapli.shodrone.dronemanagement.repository.DroneModelRepository;
import eapli.shodrone.dronemanagement.repository.MaintenanceTypeRepository;
import eapli.shodrone.usermanagement.domain.Roles;
import eapli.shodrone.dronemanagement.repository.DroneRepository;

import java.time.LocalDate;
import java.util.Optional;

@UseCaseController
public class DroneAddToInventoryController {

    private final AuthorizationService authz;
    private final DroneInventoryService service;

    public DroneAddToInventoryController(AuthorizationService authz, final DroneRepository droneRepo, final DroneModelRepository modelRepo, final MaintenanceTypeRepository maintenanceTypeRepository) {
        this.authz = authz;
        this.service = new DroneInventoryService(modelRepo,droneRepo,maintenanceTypeRepository);
    }

    public void addToInventoryDrone(DroneID id,String programmingLanguage,String name,DroneModel model, LocalDate acquisitionDate, DroneStatus status) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);
        service.addToInventoryDrone(id,programmingLanguage,name,model,acquisitionDate,status);
    }

    public Optional<DroneModel> findDroneModelByName(String name) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);
        return service.findDroneModelByName(name);
    }

    public boolean isDroneModelRepositoryEmpty() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);
        return service.isDroneModelRepositoryEmpty();
    }

    public Iterable<DroneModel> findAllDronesModel() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);
        return service.findAllDronesModel();
    }

}
