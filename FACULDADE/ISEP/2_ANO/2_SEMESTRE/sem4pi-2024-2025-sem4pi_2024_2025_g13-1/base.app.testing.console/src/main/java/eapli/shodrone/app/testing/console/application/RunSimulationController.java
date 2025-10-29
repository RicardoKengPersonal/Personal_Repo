package eapli.shodrone.app.testing.console.application;

import eapli.shodrone.showproposalmanagement.domain.ShowProposal;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RunSimulationController {

    // Declares and initializes the two services that the controller will use.
    private final RunSimulationService simulationService = new RunSimulationService();
    private final ListShowsProposalService listShowsService = new ListShowsProposalService();

    /**
     * Retrieves the list of all available shows using the appropriate service.
     * The UI will call this method to present options to the user.
     *
     * @return A collection of Show objects.
     */
    public Iterable<ShowProposal> listAvailableShowsProposal() {
        // Now the call to 'listShowsService' works because the variable was declared.
        // Filtra os shows com VIDEO_LINK preenchido antes de retornar
        return StreamSupport.stream(this.listShowsService.allShowsProposal().spliterator(), false)
                .filter(show -> show.videoLink() != null && !show.videoLink().isEmpty())
                .collect(Collectors.toList());
    }

    /**
     * Starts the simulation for a specific show.
     * This method is called by the UI after the user has selected a show.
     *
     * @param selectedProposal The show selected by the user.
     */
    public boolean runSimulation(ShowProposal selectedProposal) {
        boolean success = simulationService.runSimulation(selectedProposal.identity());

        if (success) {
            System.out.println("Simulation successful. Updating proposal status...");
            try {
                UpdateStatusService.updateStatusToReadyToSend(selectedProposal.identity());
                System.out.println("Proposal status updated to 'TESTED'.");
            } catch (Exception e) {
                System.err.println("Error updating the proposal status: " + e.getMessage());
                // Se a atualização falhar, a operação geral falhou.
                return false;
            }
        }
        // Retorna o resultado da simulação (e da atualização) para a UI
        return success;
    }
}