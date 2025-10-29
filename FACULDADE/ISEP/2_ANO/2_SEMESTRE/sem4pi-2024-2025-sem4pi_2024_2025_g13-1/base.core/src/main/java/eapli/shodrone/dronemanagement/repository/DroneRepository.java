package eapli.shodrone.dronemanagement.repository;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.shodrone.dronemanagement.domain.*;


import java.util.Optional;

public interface DroneRepository extends DomainRepository<DroneID, Drone>{
    Optional<Drone> findByDroneNameIgnoreCase(String name);
    Iterable<Drone> findByModelAndStatus(DroneModel model, DroneStatus status);
    Optional<Drone> existsMaintenanceRecordsByType(MaintenanceType maintenanceType);
    Iterable<Drone>findAllWithProgrammingLanguage();
}
