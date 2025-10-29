package eapli.shodrone.showproposalmanagement.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.shodrone.dronemanagement.domain.AvailableDroneModelDTO;
import eapli.shodrone.dronemanagement.repository.DroneModelRepository;
import eapli.shodrone.dronemanagement.repository.DroneRepository;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;
import eapli.shodrone.usermanagement.domain.Roles;

import java.util.List;

@UseCaseController
public class AddDronesToProposalController {

    private final AuthorizationService authz;
    private final eapli.shodrone.showproposalmanagement.application.AddDronesToProposalService service;

    public AddDronesToProposalController(
            AuthorizationService authz,
            ShowProposalRepository proposalRepo,
            DroneRepository droneRepo,
            DroneModelRepository droneModelRepo
    ) {
        this.authz = authz;
        this.service = new eapli.shodrone.showproposalmanagement.application.AddDronesToProposalService(proposalRepo, droneRepo, droneModelRepo);
    }

    public List<AvailableDroneModelDTO> listAvailableDroneModels() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.CRM_COLLABORATOR, Roles.ADMIN);
        return service.getAvailableDroneModels();
    }

    public void addDronesToProposal(String proposalId, List<AvailableDroneModelDTO> selectedDrones) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.CRM_COLLABORATOR, Roles.ADMIN);
        service.addDronesToProposal(proposalId, selectedDrones);
    }
}
