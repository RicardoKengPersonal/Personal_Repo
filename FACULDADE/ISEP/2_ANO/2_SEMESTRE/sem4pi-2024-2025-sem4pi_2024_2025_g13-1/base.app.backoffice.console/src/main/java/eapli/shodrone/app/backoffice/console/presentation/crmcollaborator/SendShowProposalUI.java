package eapli.shodrone.app.backoffice.console.presentation.crmcollaborator;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.io.util.Console;
import eapli.shodrone.showproposalmanagement.application.SendShowProposalController;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;

import java.util.List;

public class SendShowProposalUI extends AbstractUI {

    private final ShowProposalRepository repo = PersistenceContext.repositories().showProposalRepository();
    private final SendShowProposalController controller = new SendShowProposalController(repo);


    @Override
    protected boolean doShow() {
        List<ShowProposal> proposals = repo.findAllReadyToSend();

        if (proposals.isEmpty()) {
            System.out.println("No proposals ready to send.");
            return false;
        }

        System.out.println("Select a proposal to send:");
        int index = 1;
        for (ShowProposal proposal : proposals) {
            System.out.printf("%d. %s%n", index++, proposal);
        }

        final int choice = Console.readInteger("\nYour choice:");
        if (choice < 1 || choice > proposals.size()) {
            System.out.println("Invalid selection.");
            return false;
        }

        final ShowProposal selected = proposals.get(choice - 1);

        try {
            // 1. Capturar o código de acesso retornado pelo controller.
            String accessCode = controller.sendProposal(selected.identity());

            // 2. Apresentar o código de acesso ao utilizador.
            System.out.println("\n----------------------------------------------------");
            System.out.println("Proposal sent successfully!");
            System.out.println("Please provide the following access code to the customer:");
            System.out.println("Access Code: " + accessCode);
            System.out.println("----------------------------------------------------");

        } catch (Exception e) {
            System.out.println("\nAn error occurred while sending the proposal: " + e.getMessage());
        }

        return false;
    }

    @Override
    public String headline() {
        return "Send Show Proposal to Customer";
    }
}