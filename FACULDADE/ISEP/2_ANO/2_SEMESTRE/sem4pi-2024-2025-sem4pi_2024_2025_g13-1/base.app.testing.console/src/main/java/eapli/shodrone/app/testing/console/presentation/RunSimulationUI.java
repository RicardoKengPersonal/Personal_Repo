package eapli.shodrone.app.testing.console.presentation;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.shodrone.app.testing.console.application.RunSimulationController;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;

import java.util.List;

public class RunSimulationUI extends AbstractUI {

    private final RunSimulationController controller = new RunSimulationController();

    @Override
    protected boolean doShow() {
        // 1. Retrieve the list of shows through the controller
        final List<ShowProposal> availableShowsProposal = (List<ShowProposal>) this.controller.listAvailableShowsProposal();

        // 2. Check if there are any shows available for testing
        if (availableShowsProposal.isEmpty()) {
            System.out.println("No Shows Proposal available for simulation.");
            return true;
        }

        // 3. Use the SelectWidget to display the list and allow selection
        final SelectWidget<ShowProposal> selector = new SelectWidget<>("Select the Show Proposal you want to simulate:", availableShowsProposal);
        selector.show();

        // 4. Get the element selected by the user
        final ShowProposal selectedShowProposal = selector.selectedElement();

        // 5. If an element was selected, start the simulation for that show
        if (selectedShowProposal != null) {
            try {
                System.out.println("\nStarting simulation for the Show Proposal: " + selectedShowProposal.identity() + "...");

                // 1. Executa o controlador E armazena o resultado
                boolean wasSuccessful = this.controller.runSimulation(selectedShowProposal);

                // 2. Apresenta a mensagem final com base no resultado
                if (wasSuccessful) {
                    System.out.println("\n✅ Simulation completed with success!");
                } else {
                    System.err.println("\n❌ Simulation failed. Please check the logs for details.");
                }

            } catch (Exception e) {
                // Este catch serve para erros inesperados ao iniciar a simulação
                System.err.println("\nA critical error occurred: " + e.getMessage());
            }
        }

        return true;
    }

    @Override
    public String headline() {
        return "Test a Show Proposal";
    }
}