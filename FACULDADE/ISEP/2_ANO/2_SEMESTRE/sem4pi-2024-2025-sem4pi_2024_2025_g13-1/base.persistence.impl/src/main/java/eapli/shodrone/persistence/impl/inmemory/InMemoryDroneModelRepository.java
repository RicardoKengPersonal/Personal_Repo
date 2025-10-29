package eapli.shodrone.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.shodrone.dronemanagement.domain.DroneModel;
import eapli.shodrone.dronemanagement.repository.DroneModelRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class InMemoryDroneModelRepository
        extends InMemoryDomainRepository<DroneModel, Long>
        implements DroneModelRepository {

    @Override
    public Optional<DroneModel> findByNameIgnoreCase(String name) {
        return matchOne(drone -> drone.name().equalsIgnoreCase(name));
    }

    @Override
    public List<DroneModel> findByActiveTrue() {
        Iterable<DroneModel> allActive = match(DroneModel::isActive);
        return StreamSupport.stream(allActive.spliterator(), false)
                .collect(Collectors.toList());
    }
}
