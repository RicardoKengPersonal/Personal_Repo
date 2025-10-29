package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterMaintenanceController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.VFMUI;

import java.util.Scanner;

/**
 * The RegisterMaintenanceUI class handles the user interface for registering vehicle maintenance checkups.
 * It interacts with the RegisterMaintenanceController to register maintenance checkups in the system.
 */
public class RegisterMaintenanceUI implements Runnable {
    private final RegisterMaintenanceController controller;

    /**
     * Constructs a new RegisterMaintenanceUI object and initializes the controller.
     */
    public RegisterMaintenanceUI() {
        controller = new RegisterMaintenanceController();
    }

    /**
     * Navigates back to the main menu of the Vehicle Fleet Management UI.
     */
    private void backToVFMUI() {
        MenuItem item = new MenuItem(AuthenticationController.ROLE_VFM, new VFMUI());
        item.run();
    }

    /**
     * Gets vehicle checkup data from the user through the console and registers the maintenance checkup.
     * @return True if the checkup was successfully registered, false otherwise
     */
    public boolean getVehicleCheckupDataFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Vehicle details:");
        System.out.print("Plate:");
        String plateNumber = scanner.nextLine();
        System.out.print("Registry Date:");
        String date = scanner.nextLine();
        System.out.print("Current Km:");
        String kmAtCheckup = scanner.nextLine();
        return controller.registerVehicleMaintenance(plateNumber, date, kmAtCheckup);
    }

    /**
     * Displays a message indicating the success of the operation.
     */
    public void displayOperationSuccess() {
        System.out.println("Operation successful.");
    }

    /**
     * Displays a message indicating the failure of the operation.
     */
    public void displayOperationFailure() {
        System.out.println("Operation failed.");
    }

    /**
     * Runs the RegisterMaintenanceUI functionality.
     * Gets vehicle checkup data from the user, registers the maintenance checkup,
     * and displays success or failure messages.
     * Finally, navigates back to the main menu of the Vehicle Fleet Management UI.
     */
    public void run() {
        boolean result = getVehicleCheckupDataFromUser();
        if (result) {
            displayOperationSuccess();
        } else {
            displayOperationFailure();
        }
        backToVFMUI();
    }
}
