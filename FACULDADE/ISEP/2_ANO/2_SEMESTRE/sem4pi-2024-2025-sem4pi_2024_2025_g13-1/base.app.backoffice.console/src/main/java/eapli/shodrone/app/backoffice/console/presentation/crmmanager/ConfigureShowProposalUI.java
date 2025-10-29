package eapli.shodrone.app.backoffice.console.presentation.crmmanager;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.shodrone.figuremanagement.repository.FigureRepository;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.showproposalmanagement.application.ConfigureShowProposalController;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;

import java.util.List;

public class ConfigureShowProposalUI extends AbstractUI {

    private final ShowProposalRepository repo = PersistenceContext.repositories().showProposalRepository();
    private final ConfigureShowProposalController controller = new ConfigureShowProposalController(repo);

    @Override
    protected boolean doShow() {
        List<ShowProposal> proposals = repo.findAllReadyToTest();

        if (proposals.isEmpty()) {
            System.out.println("No proposals ready to be tested.");
            return false;
        }

        System.out.println("--- Select a Proposal ---");
        int index = 1;
        for (ShowProposal proposal : proposals) {
            // Using a more descriptive output for the proposal
            System.out.printf("%d. %s%n", index++, proposal.toString());
        }

        final int choice = Console.readInteger("\nSelect proposal:");
        if (choice < 1 || choice > proposals.size()) {
            System.out.println("Invalid selection.");
            return false;
        }

        final ShowProposal selected = proposals.get(choice - 1);

        System.out.println("\n--- Select a Model ---");
        System.out.println("1) Show Proposal model 01");
        System.out.println("2) Show Proposal model 02");
        System.out.println("3) Show Proposal model 03");

        int modelOpc;
        // Loop corrected to validate the input range
        while (true) {
            modelOpc = Console.readInteger("\nSelect a show proposal model (1-3):");
            if (modelOpc >= 1 && modelOpc <= 3) {
                break; // Valid option, exit loop
            }
            System.out.println("Invalid option. Please choose a value between 1 and 3.");
        }

        try {
            controller.configure(selected.identity(), modelOpc);
            System.out.println("\nSuccess: Show Proposal configured successfully.");
        } catch (Exception e) {
            System.out.println("\nError: Could not configure the proposal. " + e.getMessage());
        }

        return false;
    }

    @Override
    public String headline() {
        return "Configure Show Proposal";
    }
}