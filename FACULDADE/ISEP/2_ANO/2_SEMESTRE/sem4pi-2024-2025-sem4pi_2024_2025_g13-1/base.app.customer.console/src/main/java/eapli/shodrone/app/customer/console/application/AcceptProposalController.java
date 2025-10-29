package eapli.shodrone.app.customer.console.application;

public class AcceptProposalController {

    private final AcceptProposalService service = new AcceptProposalService();

    public void acceptProposal(String proposalId) {
        service.acceptProposal(proposalId);
    }

    public void rejectProposal(String proposalId) {
        service.rejectProposal(proposalId);
    }

}
