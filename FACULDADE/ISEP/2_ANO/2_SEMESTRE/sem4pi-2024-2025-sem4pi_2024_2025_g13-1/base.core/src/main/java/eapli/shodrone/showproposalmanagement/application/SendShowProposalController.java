package eapli.shodrone.showproposalmanagement.application;

import eapli.shodrone.showproposalmanagement.domain.ShowProposalID;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;

public class SendShowProposalController {

    private final SendShowProposalService service;

    public SendShowProposalController(final ShowProposalRepository repo) {
        this.service = new SendShowProposalService(repo);
    }

    public String sendProposal(ShowProposalID id) {

        return service.sendProposal(id);
    }
}