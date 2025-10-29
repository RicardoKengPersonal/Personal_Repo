package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.io.util.Console;
import eapli.shodrone.dronemanagement.application.DroneRemoveFromInventoryController;
import eapli.shodrone.dronemanagement.domain.Drone;
import eapli.shodrone.dronemanagement.domain.DroneID;
import eapli.shodrone.dronemanagement.domain.DroneModel;
import eapli.shodrone.dronemanagement.domain.DroneStatus;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;

import java.time.LocalDate;
import java.util.Optional;

public class DroneInventoryRemovalUI extends AbstractUI {
    private final DroneRemoveFromInventoryController controller = new DroneRemoveFromInventoryController(
            AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().drone(),
            PersistenceContext.repositories().droneModel(),
            PersistenceContext.repositories().maintenanceType()
    );

    @Override
    protected boolean doShow() {

        if (controller.isDroneRepositoryEmpty()) {
            System.out.println("The drone model repository is empty. It is not possible to add a drone.");
            return false;
        }
        boolean valid = false;

        while (!valid) {
            try {
                System.out.println("Available Drones:");
                for (Drone d : controller.findAllDrones()) {
                    System.out.println(" - " + d.toString());
                }

                final String droneName = Console.readLine("Drone Name: ");

                Optional<Drone> optionalModel = controller.findDroneByName(droneName);

                if (optionalModel.isEmpty()) {
                    System.out.println("No drone found with that name.");
                    return false;
                }

                System.out.println("Available statuses:");
                for (DroneStatus status : DroneStatus.values()) {
                    System.out.println(" - " + status.name());
                }
                final String statusInput = Console.readLine("Drone Status: ");
                final DroneStatus newStatus = DroneStatus.valueOf(statusInput.toUpperCase());

                if (newStatus == DroneStatus.ACTIVE) {
                    System.out.println("Cannot set status to ACTIVE when removing drone from inventory.");
                    return false;
                }
                final String reason = Console.readLine("Reason for removal: ");
                final LocalDate date = LocalDate.parse(Console.readLine("Removal Date (yyyy-MM-dd): "));

                controller.removeFromInventory(droneName, reason, date,newStatus);

                valid = true;
                System.out.println("Drone successfully removed from the inventory.");

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
        return "Remove Drone from Inventory";
    }
}
