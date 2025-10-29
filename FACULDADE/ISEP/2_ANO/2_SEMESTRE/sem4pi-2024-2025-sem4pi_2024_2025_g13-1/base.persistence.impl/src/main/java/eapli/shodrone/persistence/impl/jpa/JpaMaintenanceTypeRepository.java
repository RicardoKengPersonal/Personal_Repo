package eapli.shodrone.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.shodrone.Application;
import eapli.shodrone.dronemanagement.domain.MaintenanceType;
import eapli.shodrone.dronemanagement.repository.MaintenanceTypeRepository;
import jakarta.persistence.TypedQuery;


import java.util.Optional;


public class JpaMaintenanceTypeRepository extends JpaAutoTxRepository<MaintenanceType, Long, Long> implements MaintenanceTypeRepository {

    public JpaMaintenanceTypeRepository(final String puname) {
        super(puname, Application.settings().extendedPersistenceProperties(), "id");
    }

    public JpaMaintenanceTypeRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    @Override
    public Optional<MaintenanceType> findByNameIgnoreCase(String name) {
        final TypedQuery<MaintenanceType> query = createQuery(
                "SELECT mt FROM MaintenanceType mt WHERE LOWER(mt.name) = LOWER(:name)", MaintenanceType.class);
        query.setParameter("name", name);
        return query.getResultStream().findFirst();
    }

    @Override
    public Iterable<MaintenanceType> findAllTypes() {
        final TypedQuery<MaintenanceType> query = createQuery("SELECT t FROM MaintenanceType t", MaintenanceType.class);
        return query.getResultList();
    }
}
