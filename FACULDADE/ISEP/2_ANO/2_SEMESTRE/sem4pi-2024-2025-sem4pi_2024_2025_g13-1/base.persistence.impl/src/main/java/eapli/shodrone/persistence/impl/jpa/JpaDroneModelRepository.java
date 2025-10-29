package eapli.shodrone.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.shodrone.Application;
import eapli.shodrone.dronemanagement.domain.DroneModel;
import eapli.shodrone.dronemanagement.repository.DroneModelRepository;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class JpaDroneModelRepository extends JpaAutoTxRepository<DroneModel, Long, Long> implements DroneModelRepository {

    public JpaDroneModelRepository(final String puname) {
        super(puname, Application.settings().extendedPersistenceProperties(), "id");
    }

    public JpaDroneModelRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    @Override
    public Optional<DroneModel> findByNameIgnoreCase(String name) {
        final TypedQuery<DroneModel> query = createQuery(
                "SELECT dm FROM DroneModel dm WHERE LOWER(dm.name) = LOWER(:name)", DroneModel.class);
        query.setParameter("name", name);
        return query.getResultStream().findFirst();
    }

    @Override
    public List<DroneModel> findByActiveTrue() {
        final TypedQuery<DroneModel> query = createQuery(
                "SELECT dm FROM DroneModel dm WHERE dm.active = true", DroneModel.class);
        return query.getResultList();
    }
}
