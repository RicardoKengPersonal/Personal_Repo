package eapli.shodrone.showproposalmanagement.application;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.shodrone.figuremanagement.repository.FigureRepository;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalID;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;

public class ConfigureShowProposalController {

    private final ConfigureShowProposalService service;

    public ConfigureShowProposalController(final ShowProposalRepository repo) {
        this.service = new ConfigureShowProposalService(repo);
    }

    public void configure(ShowProposalID id, int modelOpc) {
        final SystemUser authenticatedUser = AuthzRegistry.authorizationService().session().get().authenticatedUser();
        final String createName = authenticatedUser.name().toString();
        final String createRole = authenticatedUser.roleTypes().iterator().next().toString();
        service.configure(id, modelOpc, createName, createRole);
    }
}
