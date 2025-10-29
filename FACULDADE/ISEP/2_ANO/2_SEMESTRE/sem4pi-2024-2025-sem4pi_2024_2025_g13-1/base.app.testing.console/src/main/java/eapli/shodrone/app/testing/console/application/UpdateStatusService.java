package eapli.shodrone.app.testing.console.application;

import eapli.shodrone.showproposalmanagement.domain.ShowProposalID;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;
import java.util.Optional;

public class UpdateStatusService {

    private static final ShowProposalRepository repo = PersistenceContext.repositories().showProposalRepository();

    public static void updateStatusToReadyToSend(ShowProposalID proposalId)
            throws ConcurrencyException, IntegrityViolationException {

        final Optional<ShowProposal> optionalProposal = repo.ofIdentity(proposalId);

        if (optionalProposal.isEmpty()) {
            throw new IllegalArgumentException("Proposal ID not exits: " + proposalId);
        }

        final ShowProposal proposal = optionalProposal.get();

        // Delega a lógica de mudança de estado para o próprio objeto de domínio
        proposal.markAsReadyToTest();

        // Guarda a proposta atualizada na base de dados
        repo.save(proposal);
    }
}