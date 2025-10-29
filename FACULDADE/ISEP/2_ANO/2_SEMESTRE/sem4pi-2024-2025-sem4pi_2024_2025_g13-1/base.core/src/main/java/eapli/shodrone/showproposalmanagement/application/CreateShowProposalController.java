package eapli.shodrone.showproposalmanagement.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.shodrone.showproposalmanagement.DTO.ShowProposalDTO;
import eapli.shodrone.showproposalmanagement.DTO.ShowProposalParser;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;
import eapli.shodrone.showrequestmanagement.repository.ShowRequestRepository;
import eapli.shodrone.usermanagement.domain.Roles;


public class CreateShowProposalController {
    private final AuthorizationService authz;
    private final ShowProposalService service;
    private final ShowProposalRepository showProposalRepo;
    private final ShowRequestRepository showRequestRepo;

    public CreateShowProposalController(
            final AuthorizationService authz,
            final ShowProposalRepository showProposalRepo,
            final ShowRequestRepository showRequestRepo,
            final UserRepository userRepository) {

        this.authz = authz;
        this.showProposalRepo = showProposalRepo;
        this.showRequestRepo = showRequestRepo;
        this.service = new ShowProposalService(showProposalRepo, userRepository);
    }

    public Iterable<SystemUser> findAllUsers() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN, Roles.CRM_COLLABORATOR);
        return service.findAllUsers();
    }


    public void addShowProposal(final ShowProposalDTO dto) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN, Roles.CRM_COLLABORATOR);

        final ShowProposalParser parser = new ShowProposalParser(showProposalRepo, showRequestRepo);
        final ShowProposal proposal = parser.valueOf(dto);

        service.registerNewShowProposal(proposal);
    }

}
