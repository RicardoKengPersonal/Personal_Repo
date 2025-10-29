package eapli.shodrone.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.shodrone.dronemanagement.domain.MaintenanceType;
import eapli.shodrone.dronemanagement.repository.MaintenanceTypeRepository;


import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class InMemoryMaintenanceTypeRepository extends InMemoryDomainRepository<MaintenanceType, Long> implements MaintenanceTypeRepository {

    @Override
    public Optional<MaintenanceType> findByNameIgnoreCase(String name) {
        return matchOne(maintenanceType -> maintenanceType.name().equalsIgnoreCase(name));
    }

    @Override
    public Iterable<MaintenanceType> findAllTypes() {
        Iterable<MaintenanceType> all = findAll();
        return StreamSupport.stream(all.spliterator(), false)
                .collect(Collectors.toList());
    }
}
