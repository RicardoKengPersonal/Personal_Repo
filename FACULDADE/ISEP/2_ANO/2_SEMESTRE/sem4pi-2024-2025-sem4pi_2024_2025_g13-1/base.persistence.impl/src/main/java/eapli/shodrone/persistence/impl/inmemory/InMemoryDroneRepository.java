package eapli.shodrone.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.shodrone.dronemanagement.domain.*;
import eapli.shodrone.dronemanagement.repository.DroneRepository;

import java.util.Optional;

public class InMemoryDroneRepository
        extends InMemoryDomainRepository<Drone, DroneID>
        implements DroneRepository {


    @Override
    public Optional<Drone> findByDroneNameIgnoreCase(String name) {
        return Optional.empty();
    }

    @Override
    public Iterable<Drone> findByModelAndStatus(DroneModel model, DroneStatus status) {
        return null;
    }

    @Override
    public Optional<Drone> existsMaintenanceRecordsByType(MaintenanceType maintenanceType) {return Optional.empty();}
    @Override
    public Iterable<Drone> findAllWithProgrammingLanguage() {return null;}

}
