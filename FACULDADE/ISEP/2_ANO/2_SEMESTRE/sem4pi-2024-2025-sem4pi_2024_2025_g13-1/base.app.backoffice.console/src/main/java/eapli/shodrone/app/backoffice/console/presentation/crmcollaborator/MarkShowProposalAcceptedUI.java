package eapli.shodrone.app.backoffice.console.presentation.crmcollaborator;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.io.util.Console;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.showproposalmanagement.application.MarkShowProposalAcceptedController;

public class MarkShowProposalAcceptedUI extends AbstractUI {

   private final MarkShowProposalAcceptedController controller = new MarkShowProposalAcceptedController(AuthzRegistry.authorizationService(),
           PersistenceContext.repositories().showProposalRepository());

    @Override
    protected boolean doShow() {
        final String proposalId = Console.readLine("Enter the Proposal ID to accept: ");

        try {
            controller.markProposalAsAccepted(proposalId);
            System.out.println("Proposal accepted successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return true;
    }

    @Override
    public String headline() {
        return "Accept Proposal";
    }

}
