package eapli.shodrone.showproposalmanagement.application;

import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalID;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;

import static eapli.shodrone.showproposalmanagement.domain.ShowProposalStatus.READY_TO_SEND;
import static eapli.shodrone.showproposalmanagement.domain.ShowProposalStatus.TESTED;

public class ConfigureShowProposalService {

    private final ShowProposalRepository repo;

    public ConfigureShowProposalService(ShowProposalRepository repo) {
        this.repo = repo;
    }

    public void configure(ShowProposalID id, int modelOpc, String createName, String createRole) {

        ShowProposal proposal = repo.ofIdentity(id)
                .orElseThrow(() -> new IllegalArgumentException("Proposal not found."));

        if (proposal.status() != TESTED) {
            throw new IllegalStateException("Proposal is not ready to be configured. Current status: " + proposal.status());
        }

        String templateId = "MODEL_" + modelOpc;

        proposal.assignTemplateAndAuditor(templateId, createName, createRole);

        proposal.updateStatus(READY_TO_SEND);

        repo.save(proposal);
    }
}