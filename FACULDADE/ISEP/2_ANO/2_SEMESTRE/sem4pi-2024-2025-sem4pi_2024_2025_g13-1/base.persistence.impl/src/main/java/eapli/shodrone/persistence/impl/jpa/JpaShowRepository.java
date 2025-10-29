package eapli.shodrone.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.shodrone.Application;
import eapli.shodrone.showmanagement.domain.Show;
import eapli.shodrone.showmanagement.domain.ShowID;
import eapli.shodrone.showmanagement.repository.ShowRepository;

import jakarta.persistence.TypedQuery;
import java.util.List;

public class JpaShowRepository extends JpaAutoTxRepository<Show, ShowID, ShowID> implements ShowRepository {

    public JpaShowRepository(final String puname) {
        super(puname, Application.settings().extendedPersistenceProperties(), "id");
    }

    public JpaShowRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    @Override
    public List<Show> findByCustomerRepresentativeEmail(String email) {
        final TypedQuery<Show> query = entityManager().createQuery(
                "SELECT s FROM Show s WHERE s.request.representative.email.email = :email", Show.class);
        query.setParameter("email", email);
        return query.getResultList();
    }

    @Override
    public List<Show> findScheduledShows() {
        return entityManager().createQuery(
                "SELECT s FROM Show s WHERE s.status = 'SCHEDULED'", Show.class).getResultList();
    }

    @Override
    public List<Show> findScheduledShowsByCustomer(String customerId) {
        final TypedQuery<Show> query = entityManager().createQuery(
                "SELECT s FROM Show s WHERE s.request.customer.vatNumber.number = :customerId AND s.status = 'SCHEDULED'",
                Show.class);
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }
}