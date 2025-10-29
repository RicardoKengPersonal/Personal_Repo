package eapli.shodrone.showproposalmanagement.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;
import eapli.shodrone.usermanagement.domain.Roles;

public class AddVideoToShowProposalController {
    private final AuthorizationService authz;
    private final ShowProposalService service;

    public AddVideoToShowProposalController(
            final AuthorizationService authz,
            final ShowProposalRepository showProposalRepo,
            final UserRepository userRepository) {

        this.authz = authz;
        this.service = new ShowProposalService(showProposalRepo, userRepository);
    }

    public void updateVideo(String proposalIdStr, String videoLink) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN, Roles.CRM_COLLABORATOR);
        service.updateVideoLink(proposalIdStr, videoLink);
    }

}
