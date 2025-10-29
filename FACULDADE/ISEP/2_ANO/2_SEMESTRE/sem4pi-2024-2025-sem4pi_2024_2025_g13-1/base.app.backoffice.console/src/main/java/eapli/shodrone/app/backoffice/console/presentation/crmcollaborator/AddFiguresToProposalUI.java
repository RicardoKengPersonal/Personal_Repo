package eapli.shodrone.app.backoffice.console.presentation.crmcollaborator;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.shodrone.dronemanagement.domain.DroneModel;
import eapli.shodrone.dronemanagement.domain.DroneType;
import eapli.shodrone.figuremanagement.domain.FigureVersion;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.showproposalmanagement.application.AddFiguresToProposalController;
import eapli.shodrone.showproposalmanagement.application.AddFiguresToProposalService;
import eapli.shodrone.showproposalmanagement.domain.FigureSelectionMode;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.showproposalmanagement.DTO.DroneTypeMappingDTO;
import eapli.shodrone.showrequestmanagement.domain.ShowRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AddFiguresToProposalUI extends AbstractUI {

    private final AddFiguresToProposalController ctrl = new AddFiguresToProposalController(
            PersistenceContext.repositories().showProposalRepository(),
            new AddFiguresToProposalService(PersistenceContext.repositories().figure())
    );

    @Override
    protected boolean doShow() {
        // 1. SELECT THE PROPOSAL (without SelectWidget)
        final List<ShowProposal> proposals = ctrl.listAvailableProposals();
        if (proposals.isEmpty()) {
            System.out.println("There are no proposals with defined drones and without figures.");
            return false;
        }

        System.out.println("--- Select a Proposal ---");
        int counter = 1;
        for (final ShowProposal p : proposals) {
            System.out.printf("%d. %s%n", counter++, p.toString());
        }
        System.out.println("0. Cancel");
        final int proposalOption = Console.readOption(1, proposals.size(), 0);
        if (proposalOption == 0) {
            return false;
        }
        final ShowProposal selectedProposal = proposals.get(proposalOption - 1);


        // Create a copy of the fleet to track the available state
        final Map<DroneModel, Integer> availableDronesForShow = new HashMap<>(ctrl.getAvailableDroneFleetForProposal(selectedProposal));

        // 2. GET AVAILABLE FIGURES
        final ShowRequest req = ctrl.getShowRequestFromProposal(selectedProposal);
        final FigureSelectionMode mode = selectMode();
        if (mode == null) return false;
        final List<FigureVersion> availableFigures = new ArrayList<>(ctrl.figuresForMode(mode, req));

        // 3. MAIN LOOP TO ADD FIGURES
        boolean doneAddingFigures = false;
        while (!doneAddingFigures) {
            try {
                // Display current fleet state and select a figure
                FigureVersion chosenFigure = selectFigure(availableFigures, selectedProposal, availableDronesForShow);
                if (chosenFigure == null) {
                    doneAddingFigures = true;
                    continue;
                }

                // 4. PERFORM DRONE MAPPING (with quantity validation)
                Map<DroneModel, Integer> consumedDronesInThisStep = new HashMap<>();
                Map<String, String> mappings = performDroneMapping(chosenFigure, availableDronesForShow, consumedDronesInThisStep);

                // 5. ADD TO PROPOSAL AND UPDATE LOCAL STATE
                DroneTypeMappingDTO dto = new DroneTypeMappingDTO(mappings);
                ctrl.addFigureToProposal(selectedProposal, chosenFigure, dto);

                // Update the local state map with the consumed drones
                consumedDronesInThisStep.forEach((model, consumedQty) ->
                        availableDronesForShow.merge(model, -consumedQty, Integer::sum)
                );

                System.out.println("✅ Figure added successfully!\n");

            } catch (Exception e) {
                System.out.println("\n❌ Error: " + e.getMessage());
                System.out.println("The figure was not added. Please try again.\n");
            }
        }

        // 6. SAVE THE PROPOSAL
        if (!selectedProposal.figureEntries().isEmpty()) {
            ctrl.saveProposal(selectedProposal);
            System.out.println("\nProposal updated and saved successfully.");
        } else {
            System.out.println("\nNo figures were added. The proposal was not changed.");
        }
        return true;
    }

    private FigureSelectionMode selectMode() {
        System.out.println("\n--- Figure Selection Mode ---");
        System.out.println("1. Use figures from the original request");
        System.out.println("2. Choose from all available figures");
        System.out.println("0. Cancel");
        int modeOption = Console.readOption(1, 2, 0);
        return switch (modeOption) {
            case 1 -> FigureSelectionMode.USE;
            case 2 -> FigureSelectionMode.IGNORE;
            default -> null;
        };
    }

    private FigureVersion selectFigure(List<FigureVersion> availableFigures, ShowProposal proposal, Map<DroneModel, Integer> currentFleet) {
        System.out.println("\n--- Figures Currently Added ---");
        if (proposal.figureEntries().isEmpty()) {
            System.out.println("None.");
        } else {
            proposal.figureEntries().forEach(entry -> System.out.printf("Pos %d: %s%n", entry.position(), entry.figure()));
        }

        System.out.println("\n--- Drone Fleet Available for the Show ---");
        if(currentFleet.isEmpty()){
            System.out.println("No drones available.");
        } else {
            // Código modificado
            currentFleet.forEach((model, qty) -> System.out.printf("Model: %s | Types: %s | Available Quantity: %d%n", model.name(), model.lightingOptions(), qty));
        }

        if (availableFigures.isEmpty()) {
            System.out.println("\n⚠️ No more figures available to add.");
            return null;
        }

        System.out.println("\n--- Select a Figure to Add ---");
        int counter = 1;
        for(final FigureVersion fv : availableFigures) {
            String displayText = String.format("%s (%s) - Requires %d drones", fv.figure().figureName(), fv.version(), fv.getRequiredDronesSummary().values().stream().mapToInt(Integer::intValue).sum());
            System.out.printf("%d. %s%n", counter++, displayText);
        }
        System.out.println("0. Finish Adding Figures");

        final int figureOption = Console.readOption(1, availableFigures.size(), 0);
        if (figureOption == 0) {
            return null;
        }
        return availableFigures.get(figureOption - 1);
    }

    private Map<String, String> performDroneMapping(FigureVersion chosenFigure, Map<DroneModel, Integer> availableDrones, Map<DroneModel, Integer> outConsumptionMap) {
        System.out.printf("\n--- Drone Mapping for Figure: %s ---\n", chosenFigure.figure().figureName());

        Map<DroneType, Integer> requiredDronesSummary = chosenFigure.getRequiredDronesSummary();
        if (requiredDronesSummary.isEmpty()) {
            System.out.println("Warning: This figure does not require specific drone types. The mapping will be empty.");
            return new HashMap<>();
        }

        Map<String, String> mappings = new HashMap<>();
        outConsumptionMap.clear();

        for (Map.Entry<DroneType, Integer> requirement : requiredDronesSummary.entrySet()) {
            DroneType requiredType = requirement.getKey();
            int dronesNeeded = requirement.getValue();

            System.out.printf("\nThe figure requires %d drone(s) of TYPE: [%s]\n", dronesNeeded, requiredType.name());

            List<DroneModel> compatibleAndAvailableModels = availableDrones.keySet().stream()
                    .filter(model -> model.lightingOptions().contains(requiredType))
                    .filter(model -> availableDrones.getOrDefault(model, 0) >= dronesNeeded)
                    .collect(Collectors.toList());

            if (compatibleAndAvailableModels.isEmpty()) {
                throw new IllegalStateException(String.format("No drone model in the fleet is compatible and has sufficient quantity (%d) for type '%s'", dronesNeeded, requiredType.name()));
            }

            System.out.println("Select one of the compatible models with sufficient stock:");
            int counter = 1;
            for (final DroneModel model : compatibleAndAvailableModels) {
                System.out.printf("%d. %s (Available: %d)%n", counter++, model.name(), availableDrones.get(model));
            }
            System.out.println("0. Cancel Mapping");

            final int modelOption = Console.readOption(1, compatibleAndAvailableModels.size(), 0);

            if (modelOption == 0) {
                throw new IllegalStateException("Mapping canceled by user.");
            }
            DroneModel chosenModel = compatibleAndAvailableModels.get(modelOption - 1);

            mappings.put(requiredType.name(), chosenModel.name());
            outConsumptionMap.put(chosenModel, dronesNeeded);
        }

        return mappings;
    }

    @Override
    public String headline() {
        return "Add Figures to a Proposal";
    }
}