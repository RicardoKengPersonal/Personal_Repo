package eapli.shodrone.dronemanagement.repository;

import eapli.framework.domain.repositories.DomainRepository;

import eapli.shodrone.dronemanagement.domain.MaintenanceType;

import java.util.Optional;

public interface MaintenanceTypeRepository extends DomainRepository<Long, MaintenanceType> {

    Optional<MaintenanceType> findByNameIgnoreCase(String name);

    Iterable<MaintenanceType> findAllTypes();

}
