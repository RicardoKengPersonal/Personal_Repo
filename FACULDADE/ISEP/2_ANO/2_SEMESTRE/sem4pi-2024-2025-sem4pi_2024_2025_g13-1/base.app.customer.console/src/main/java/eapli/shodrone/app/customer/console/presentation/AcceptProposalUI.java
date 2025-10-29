package eapli.shodrone.app.customer.console.presentation;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.shodrone.app.customer.console.application.AcceptProposalController;

public class AcceptProposalUI extends AbstractUI  {
    private final AcceptProposalController controller = new AcceptProposalController();

    @Override
    protected boolean doShow() {
        // ANOTAÇÃO: Pedir o código de acesso, não o ID numérico.
        final String accessCode = Console.readLine("Enter the proposal's Access Code: ");
        if (accessCode == null || accessCode.isBlank()) {
            System.out.println("Operation cancelled.");
            return false;
        }

        System.out.println("\nPlease choose an action for this proposal:");
        System.out.println("1. Accept Proposal");
        System.out.println("2. Reject Proposal");
        System.out.println("0. Cancel");

        final int option = Console.readOption(1, 2, 0);

        try {
            switch (option) {
                case 1:
                    System.out.println("Accepting proposal...");
                    controller.acceptProposal(accessCode);
                    break;
                case 2:
                    System.out.println("Rejecting proposal...");
                    //String feedback = Console.readLine("You can provide optional feedback for the rejection: ");
                    controller.rejectProposal(accessCode);
                    break;
                case 0:
                    System.out.println("Operation cancelled.");
                    break;
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }

        return false;
    }

    @Override
    public String headline() {
        return "Accept or Reject a Show Proposal";
    }
}