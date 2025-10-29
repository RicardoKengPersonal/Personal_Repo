package eapli.shodrone.showproposalmanagement.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalID;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalStatus;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;
import eapli.shodrone.usermanagement.domain.Roles;

@UseCaseController
public class MarkShowProposalAcceptedController {

    private final AuthorizationService authz;
    private final ShowProposalRepository proposalRepo;

    public MarkShowProposalAcceptedController(AuthorizationService authz, ShowProposalRepository proposalRepo) {
        this.authz = authz;
        this.proposalRepo = proposalRepo;
    }
    public void markProposalAsAccepted(String Id) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN, Roles.CRM_COLLABORATOR);

        ShowProposalID id = ShowProposalID.valueOf(Integer.valueOf(Id));

        ShowProposal proposal = proposalRepo.ofIdentity(id)
                .orElseThrow(() -> new IllegalArgumentException("Proposal with ID " + id + " not found."));

        if (proposal.status() != ShowProposalStatus.ACCEPTED_CUSTOMER) {
            throw new IllegalStateException("Only proposals with status ACCEPTED_CUSTOMER can be accepted.");
        }

        proposal.updateStatus(ShowProposalStatus.ACCEPTED_CRM);
        proposalRepo.save(proposal);
    }
}