package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.io.util.Console;
import eapli.shodrone.dronemanagement.application.DroneAddToInventoryController;
import eapli.shodrone.dronemanagement.domain.DroneID;
import eapli.shodrone.dronemanagement.domain.DroneModel;
import eapli.shodrone.dronemanagement.domain.DroneStatus;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;

import java.time.LocalDate;
import java.util.Optional;

public class DroneInventoryAdditionUI extends AbstractUI {
    private final DroneAddToInventoryController controller = new DroneAddToInventoryController(
            AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().drone(),
            PersistenceContext.repositories().droneModel(),
            PersistenceContext.repositories().maintenanceType()
    );

    @Override
    protected boolean doShow() {
        if (controller.isDroneModelRepositoryEmpty()) {
            System.out.println("The drone model repository is empty. It is not possible to add a drone.");
            return false;
        }

        boolean valid = false;

        while (!valid) {
            try {
                final String serial = Console.readLine("Drone Serial Number: ");
                final String programmingLanguage = Console.readLine("Programming Language: ");
                final String name = Console.readLine("Drone Name: ");

                System.out.println("Available Drone Models:");
                for (DroneModel model : controller.findAllDronesModel()) {
                    System.out.println(" - " + model.name());
                }

                final String modelName = Console.readLine("Drone Model Name: ");


                Optional<DroneModel> optionalModel = controller.findDroneModelByName(modelName);
                if (optionalModel.isEmpty()) {
                    System.out.println("No model found with that name.");
                    return false;
                }

                DroneModel model = optionalModel.get();

                final String dateInput = Console.readLine("Acquisition Date (yyyy-MM-dd): ");
                final LocalDate date = LocalDate.parse(dateInput);

                System.out.println("Available statuses:");
                for (DroneStatus status : DroneStatus.values()) {
                    System.out.println(" - " + status.name());
                }
                final String statusInput = Console.readLine("Drone Status: ");
                final DroneStatus status = DroneStatus.valueOf(statusInput.toUpperCase());

                DroneID id = new DroneID(serial);

                controller.addToInventoryDrone(id,programmingLanguage,name, model, date, status);

                // Message based on status
                if (status == DroneStatus.ACTIVE) {
                    System.out.println("Drone successfully added to the inventory.");
                } else {
                    System.out.println("Drone registered with status '" + status.name() + "' but not added to inventory.");
                }

                valid = true;

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }

        return true;
    }

    @Override
    public String headline() {
        return "Add Drone to Inventory";
    }
}
