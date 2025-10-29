package eapli.shodrone.app.backoffice.console.presentation.crmcollaborator;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.showproposalmanagement.application.AddVideoToShowProposalController;

public class AddVideoToShowProposalUI extends AbstractUI {
    private final AddVideoToShowProposalController controller=new AddVideoToShowProposalController(AuthzRegistry.authorizationService(), PersistenceContext.repositories().showProposalRepository(),PersistenceContext.repositories().users());



    @Override
    protected boolean doShow() {
        final String proposalId = Console.readLine("Enter the ID of the Show Proposal to update:");
        final String videoLink = Console.readLine("Enter the video link:");

        try {
            controller.updateVideo(proposalId, videoLink);
            System.out.println("Video link updated successfully.");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public String headline() {
        return "Add Video Link to Show Proposal";
    }
}
