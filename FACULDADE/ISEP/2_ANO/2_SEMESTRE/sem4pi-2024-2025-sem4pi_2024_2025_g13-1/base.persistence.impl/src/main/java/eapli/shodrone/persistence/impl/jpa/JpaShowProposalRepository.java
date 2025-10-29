package eapli.shodrone.persistence.impl.jpa;


import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.shodrone.Application;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalStatus;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalID;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JpaShowProposalRepository extends JpaAutoTxRepository<ShowProposal, Long, Long>
        implements ShowProposalRepository {

    public JpaShowProposalRepository(final String puname) {
        super(puname, Application.settings().extendedPersistenceProperties(), "id");
    }

    public JpaShowProposalRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    @Override
    public List<ShowProposal> findByStatus(ShowProposalStatus status) {
        final TypedQuery<ShowProposal> query = createQuery(
                "SELECT sh FROM ShowProposal sh WHERE sh.status = :status", ShowProposal.class
        );
        query.setParameter("status", status);
        return query.getResultList();
    }

    @Override
    public Optional<ShowProposal> ofIdentity(ShowProposalID id) {
        final TypedQuery<ShowProposal> query = createQuery(
                "SELECT sh FROM ShowProposal sh WHERE sh.id = :id", ShowProposal.class
        );
        query.setParameter("id", id);
        return query.getResultStream().findFirst();
    }

    @Override
    public void deleteOfIdentity(ShowProposalID entityId) {
        final TypedQuery<ShowProposal> query = createQuery(
                "SELECT sh FROM ShowProposal sh WHERE sh.id = :id", ShowProposal.class
        );
        query.setParameter("id", entityId);
        query.getResultStream().findFirst().ifPresent(this::delete);
    }

    @Override
    public Integer findMaxId() {
        final TypedQuery<Integer> query = createQuery(
                "SELECT MAX(sh.id.id) FROM ShowProposal sh", Integer.class
        );
        return query.getSingleResult();
    }

    @Override
    public List<ShowProposal>findAllReadyToTest() {
        final TypedQuery<ShowProposal> query = entityManager().createQuery(
                "SELECT p FROM ShowProposal p WHERE p.status = :status", ShowProposal.class);
        query.setParameter("status", ShowProposalStatus.TESTED);

        return query.getResultList().stream()
                .filter(p -> p.videoLink() != null && !p.videoLink().isBlank())
                .filter(p -> !p.figureEntries().isEmpty())
                .filter(p -> !p.getDroneFleet().isEmpty())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ShowProposal> findByAccessCode(String accessCode) {
        final var query = "SELECT sp FROM ShowProposal sp WHERE sp.accessCode = :code";
        return this.createQuery(query, ShowProposal.class)
                .setParameter("code", accessCode)
                .getResultStream()
                .findFirst();
    }

    @Override
    public List<ShowProposal> findAllReadyToSend() {
        final TypedQuery<ShowProposal> query = entityManager().createQuery(
                "SELECT p FROM ShowProposal p WHERE p.status = :status", ShowProposal.class);
        query.setParameter("status", ShowProposalStatus.READY_TO_SEND);

        return query.getResultList().stream()
                .filter(p -> p.videoLink() != null && !p.videoLink().isBlank())
                .filter(p -> !p.figureEntries().isEmpty())
                .filter(p -> !p.getDroneFleet().isEmpty())
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowProposal> findProposalsWithoutFigures() {
        final TypedQuery<ShowProposal> query = createQuery(
                "SELECT p FROM ShowProposal p WHERE p.status = :status AND size(p.figureEntries) = 0", ShowProposal.class);
        query.setParameter("status", ShowProposalStatus.CREATED);
        return query.getResultList();
    }
}
