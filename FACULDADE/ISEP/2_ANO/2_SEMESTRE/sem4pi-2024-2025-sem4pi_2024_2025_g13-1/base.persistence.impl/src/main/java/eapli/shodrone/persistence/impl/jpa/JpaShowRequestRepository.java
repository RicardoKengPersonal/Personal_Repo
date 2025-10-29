package eapli.shodrone.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.shodrone.Application;
import eapli.shodrone.showrequestmanagement.domain.Date;
import eapli.shodrone.showrequestmanagement.domain.ShowRequest;
import eapli.shodrone.showrequestmanagement.domain.ShowRequestID;
import eapli.shodrone.showrequestmanagement.domain.ShowRequestStatus;
import eapli.shodrone.showrequestmanagement.repository.ShowRequestRepository;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class JpaShowRequestRepository extends JpaAutoTxRepository<ShowRequest, Long, Long>
        implements ShowRequestRepository {

    public JpaShowRequestRepository(final String puname) {
        super(puname, Application.settings().extendedPersistenceProperties(), "id");
    }

    public JpaShowRequestRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    @Override
    public List<ShowRequest> findByStatus(ShowRequestStatus status) {
        final TypedQuery<ShowRequest> query = createQuery(
                "SELECT sh FROM ShowRequest sh WHERE sh.status = :status", ShowRequest.class
        );
        query.setParameter("status", status);
        return query.getResultList();
    }

    @Override
    public Optional<ShowRequest> ofIdentity(ShowRequestID id) {
        final TypedQuery<ShowRequest> query = createQuery(
                "SELECT sh FROM ShowRequest sh WHERE sh.id = :id", ShowRequest.class
        );
        query.setParameter("id", id);
        return query.getResultStream().findFirst();
    }

    @Override
    public void deleteOfIdentity(ShowRequestID entityId) {
        final TypedQuery<ShowRequest> query = createQuery(
                "SELECT sh FROM ShowRequest sh WHERE sh.id = :id", ShowRequest.class
        );
        query.setParameter("id", entityId);
        query.getResultStream().findFirst().ifPresent(this::delete);
    }

    @Override
    public Integer findMaxId() {
        final TypedQuery<Integer> query = createQuery(
                "SELECT MAX(sh.id.id) FROM ShowRequest sh", Integer.class
        );
        return query.getSingleResult();
    }
}
