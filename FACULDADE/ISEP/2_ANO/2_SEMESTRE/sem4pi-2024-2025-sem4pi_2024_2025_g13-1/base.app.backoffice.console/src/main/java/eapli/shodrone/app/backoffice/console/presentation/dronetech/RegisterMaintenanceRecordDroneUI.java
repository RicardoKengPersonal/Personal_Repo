package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.io.util.Console;
import eapli.shodrone.dronemanagement.application.RegisterMaintenanceRecordDroneController;
import eapli.shodrone.dronemanagement.domain.Drone;
import eapli.shodrone.dronemanagement.domain.MaintenanceType;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class RegisterMaintenanceRecordDroneUI extends AbstractUI {

    private final RegisterMaintenanceRecordDroneController controller = new RegisterMaintenanceRecordDroneController(
            AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().droneModel(),
            PersistenceContext.repositories().drone(),
            PersistenceContext.repositories().maintenanceType()
    );

    @Override
    protected boolean doShow() {
        try {
            System.out.println("=== Register Maintenance Record ===");

            // List drones and choose one
            Iterable<Drone> drones = controller.listAllDrones();
            List<Drone> droneList = (List<Drone>) drones;
            if (droneList.isEmpty()) {
                System.out.println("No drones found.");
                return false;
            }
            for (int i = 0; i < droneList.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, droneList.get(i).droneName());
            }
            int droneChoice = Console.readInteger("Select a drone by number");
            if (droneChoice < 1 || droneChoice > droneList.size()) {
                System.out.println("Invalid drone selection.");
                return false;
            }
            Drone selectedDrone = droneList.get(droneChoice - 1);

            // List maintenance types and choose one
            Iterable<MaintenanceType> maintenanceTypes = controller.listAllMaintenanceTypes();
            List<MaintenanceType> typeList = (List<MaintenanceType>) maintenanceTypes;
            if (typeList.isEmpty()) {
                System.out.println("No maintenance types found.");
                return false;
            }
            for (int i = 0; i < typeList.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, typeList.get(i).name());
            }
            int typeChoice = Console.readInteger("Select a maintenance type by number");
            if (typeChoice < 1 || typeChoice > typeList.size()) {
                System.out.println("Invalid maintenance type selection.");
                return false;
            }
            MaintenanceType selectedType = typeList.get(typeChoice - 1);

            // Get date input
            String dateStr = Console.readLine("Enter maintenance date (YYYY-MM-DD)");
            LocalDate date;
            try {
                date = LocalDate.parse(dateStr);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format.");
                return false;
            }

            // Register the maintenance record
            controller.registerMaintenanceRecord(selectedDrone, selectedType, date);

            System.out.println("Maintenance record registered successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public String headline() {
        return "Register Maintenance Record";
    }
}
