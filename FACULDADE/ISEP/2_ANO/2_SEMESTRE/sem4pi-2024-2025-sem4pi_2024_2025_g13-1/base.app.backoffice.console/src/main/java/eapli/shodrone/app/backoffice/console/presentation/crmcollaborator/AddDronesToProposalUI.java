package eapli.shodrone.app.backoffice.console.presentation.crmcollaborator;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.shodrone.dronemanagement.domain.AvailableDroneModelDTO;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

public class AddDronesToProposalUI extends AbstractUI {

    private final eapli.shodrone.showproposalmanagement.application.AddDronesToProposalController controller =
            new eapli.shodrone.showproposalmanagement.application.AddDronesToProposalController(
                    AuthzRegistry.authorizationService(),
                    PersistenceContext.repositories().showProposalRepository(),
                    PersistenceContext.repositories().drone(),
                    PersistenceContext.repositories().droneModel());

    @Override
    protected boolean doShow() {
        try {
            String proposalId = Console.readLine("Enter Proposal ID: ");

            // Step 1: Show available drones by model
            Iterable<AvailableDroneModelDTO> available = controller.listAvailableDroneModels();

            System.out.println("\n--- Available Drones ---");
            for (AvailableDroneModelDTO dto : available) {
                System.out.printf("Model: %s | Available: %d%n", dto.modelName, dto.availableCount);
            }

            // Step 2: User selection
            List<AvailableDroneModelDTO> selected = new ArrayList<>();
            boolean done = false;

            while (!done) {
                String modelName = Console.readLine("\nEnter model name to add (or 'done' to finish): ");
                if ("done".equalsIgnoreCase(modelName)) break;

                int count = Console.readInteger("Enter number of drones to add: ");
                selected.add(new AvailableDroneModelDTO(modelName, count));
            }


            controller.addDronesToProposal(proposalId, selected);

            System.out.println("✅ Drones added to proposal successfully.");

        } catch (Exception e) {
            System.out.println("⚠ Error adding drones: " + e.getMessage());
        }

        return true;
    }

    @Override
    public String headline() {
        return "Add Drones to a Proposal";
    }
}
