package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.io.util.Console;
import eapli.shodrone.dronemanagement.application.ListActiveDronesByModelController;
import eapli.shodrone.dronemanagement.domain.Drone;
import eapli.shodrone.dronemanagement.domain.DroneModel;
import eapli.shodrone.dronemanagement.domain.DroneStatus;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;

import java.util.Optional;

public class ListActiveDronesUI extends AbstractUI {

    private final ListActiveDronesByModelController controller = new ListActiveDronesByModelController(
            AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().drone(),
            PersistenceContext.repositories().droneModel(),
            PersistenceContext.repositories().maintenanceType()
    );

    @Override
    protected boolean doShow() {
        boolean valid = false;

        while (!valid) {
            try {
                final String filterOption = Console.readLine("Do you want to filter the list by model and status? (yes/no): ").trim().toLowerCase();
                Iterable<Drone> drones;

                if (filterOption.equals("yes")) {
                    final String modelName = Console.readLine("Drone Model Name: ");

                    Optional<DroneModel> optionalModel = controller.findDroneModelByName(modelName);
                    if (optionalModel.isEmpty()) {
                        System.out.println("No model found with that name.");
                        return false;
                    }

                    DroneModel model = optionalModel.get();

                    System.out.println("Available statuses:");
                    for (DroneStatus status : DroneStatus.values()) {
                        System.out.println(" - " + status.name());
                    }

                    final String statusInput = Console.readLine("Drone Status: ");
                    final DroneStatus status = DroneStatus.valueOf(statusInput.toUpperCase());

                    drones = controller.findByModelAndStatus(model, status);

                    System.out.println("\nDrones with model '" + model.name() + "' and status '" + status + "':");
                } else if (filterOption.equals("no")) {
                    drones = controller.findAllDrones();
                    System.out.println("\nAll drones:");
                } else {
                    System.out.println("Invalid option. Please type 'yes' or 'no'.");
                    continue;
                }

                boolean any = false;
                for (Drone drone : drones) {
                    System.out.println(" - " + drone);
                    any = true;
                }

                if (!any) {
                    System.out.println("No drones found.");
                }

                valid = true;

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }

        return true;
    }

    @Override
    public String headline() {
        return "List Drones";
    }
}
