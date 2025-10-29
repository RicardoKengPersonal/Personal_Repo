package eapli.shodrone.showproposalmanagement.application;

import eapli.framework.application.ApplicationService;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalID;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalStatus;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;
import jakarta.transaction.Transactional;

// O serviço não precisa mais de sockets ou de lidar com ficheiros.
// Pode ser anotado como @ApplicationService se usar injeção de dependência.
public class SendShowProposalService {

    private final ShowProposalRepository repo;

    // O construtor agora só precisa do repositório.
    public SendShowProposalService(ShowProposalRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public String sendProposal(ShowProposalID id) {

        ShowProposal proposal = repo.ofIdentity(id)
                .orElseThrow(() -> new IllegalArgumentException("Proposal not found: " + id));


        validateProposal(proposal);

        // 3. Executar a lógica de negócio na entidade de domínio
        String accessCode = proposal.markAsSentAndGenerateAccessCode();


        repo.save(proposal);

        // 5. Retornar o código de acesso para a camada de UI
        return accessCode;
    }

    private void validateProposal(ShowProposal proposal) {

        if (proposal.videoLink() == null || proposal.videoLink().isBlank()) {
            throw new IllegalStateException("Proposal must have a video link before sending.");
        }
        if (proposal.figureEntries().isEmpty()) {
            throw new IllegalStateException("Proposal has no figures.");
        }
        if (proposal.getDroneFleet().isEmpty()) {
            throw new IllegalStateException("Proposal has no drone fleet defined.");
        }
        if (!proposal.status().equals(ShowProposalStatus.READY_TO_SEND)) {
            throw new IllegalStateException("Proposal is not in 'READY_TO_SEND' state. Current status: " + proposal.status());
        }
    }
}