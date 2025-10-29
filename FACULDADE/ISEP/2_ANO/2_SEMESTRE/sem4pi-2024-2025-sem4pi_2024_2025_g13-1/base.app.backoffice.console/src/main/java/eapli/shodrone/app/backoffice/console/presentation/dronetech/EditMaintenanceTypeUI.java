package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.io.util.Console;
import eapli.shodrone.dronemanagement.application.EditMaintenanceTypeController;
import eapli.shodrone.dronemanagement.domain.MaintenanceType;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;

import java.util.Optional;

public class EditMaintenanceTypeUI extends AbstractUI {

    private final EditMaintenanceTypeController controller = new EditMaintenanceTypeController(
            AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().droneModel(),
            PersistenceContext.repositories().drone(),
            PersistenceContext.repositories().maintenanceType());

    @Override
    protected boolean doShow() {
        // List all maintenance types
        System.out.println("Existing Maintenance Types:");
        PersistenceContext.repositories().maintenanceType().findAll()
                .forEach(mt -> System.out.printf("ID: %d | Name: %s | Description: %s%n",
                        mt.identity(), mt.name(), mt.description()));

        // Ask for the ID to edit
        final Long id = Console.readLong("Enter the ID of the maintenance type you want to edit");

        Optional<MaintenanceType> maintenanceTypeOpt = controller.findMaintenanceTypeById(id);
        if (maintenanceTypeOpt.isEmpty()) {
            System.out.println("Maintenance Type with that ID not found.");
            return false;
        }

        MaintenanceType maintenanceType = maintenanceTypeOpt.get();

        // Check if it can be edited
        if (!controller.canEditMaintenanceType(maintenanceType)) {
            System.out.println("Cannot edit this Maintenance Type because it is in use.");
            return false;
        }

        System.out.printf("Current Name: %s%n", maintenanceType.name());
        System.out.printf("Current Description: %s%n", maintenanceType.description());

        // Get new values
        String newName = Console.readLine("Enter new name (leave blank to keep current)");
        String newDescription = Console.readLine("Enter new description (leave blank to keep current)");

        // Use existing if user leaves blank
        if (newName == null || newName.trim().isEmpty()) {
            newName = maintenanceType.name();
        }
        if (newDescription == null || newDescription.trim().isEmpty()) {
            newDescription = maintenanceType.description();
        }

        try {
            controller.editMaintenanceType(id, newName, newDescription);
            System.out.println("Maintenance Type updated successfully.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error updating Maintenance Type: " + e.getMessage());
        }

        return false;  // Return false to stay in this menu or true to exit, depending on your app flow
    }

    @Override
    public String headline() {
        return "Edit Maintenance Type";
    }
}
