package eapli.shodrone.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.shodrone.Application;
import eapli.shodrone.dronemanagement.domain.*;
import eapli.shodrone.dronemanagement.repository.DroneRepository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.Optional;

public class JpaDroneRepository extends JpaAutoTxRepository<Drone, DroneID, DroneID> implements DroneRepository {

    public JpaDroneRepository(final String puname) {
        super(puname, Application.settings().extendedPersistenceProperties(), "id");
    }

    public JpaDroneRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    @Override
    public Optional<Drone> findByDroneNameIgnoreCase(String name) {
        final TypedQuery<Drone> query = createQuery(
                "SELECT d FROM Drone d WHERE LOWER(d.droneName) = LOWER(:name)", Drone.class);
        query.setParameter("name", name);
        return query.getResultStream().findFirst();
    }

    @Override
    public Iterable<Drone> findByModelAndStatus(DroneModel model, DroneStatus status) {
        final TypedQuery<Drone> query = entityManager().createQuery(
                "SELECT d FROM Drone d WHERE d.model = :model AND d.status = :status", Drone.class);
        query.setParameter("model", model);
        query.setParameter("status", status);
        return query.getResultList();
    }

    @Override
    public Optional<Drone> existsMaintenanceRecordsByType(MaintenanceType maintenanceType) {
        final TypedQuery<Drone> query = entityManager().createQuery(
                "SELECT DISTINCT d FROM Drone d JOIN d.maintenanceRecords dm WHERE dm.maintenanceType = :maintenanceType",
                Drone.class);
        query.setParameter("maintenanceType", maintenanceType);
        query.setMaxResults(1); // Optimization: we only need one match

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Iterable<Drone> findAllWithProgrammingLanguage() {
        final TypedQuery<Drone> query = entityManager().createQuery(
                "SELECT d FROM Drone d WHERE d.programmingLanguage IS NOT NULL AND TRIM(d.programmingLanguage) <> ''",
                Drone.class
        );
        return query.getResultList();
    }

}
