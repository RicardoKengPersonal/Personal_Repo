package eapli.shodrone.dronemanagement.repository;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.shodrone.dronemanagement.domain.DroneModel;

import java.util.List;
import java.util.Optional;

public interface DroneModelRepository extends DomainRepository<Long, DroneModel> {

    Optional<DroneModel> findByNameIgnoreCase(String name);

    List<DroneModel> findByActiveTrue();

}
