package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateVehicleController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.VFMUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;

import java.util.Scanner;


/**
 * The CreateVehicleUI class handles the user interface for creating vehicles.
 * It interacts with the CreateVehicleController to add vehicles to the system.
 */
public class CreateVehicleUI implements Runnable {
    private final CreateVehicleController controller;

    /**
     * Constructs a new CreateVehicleUI object and initializes the controller.
     */
    public CreateVehicleUI() {
        controller = new CreateVehicleController();
    }

    /**
     * Displays the form for entering vehicle details.
     */
    public void displayVehicleForm() {
        System.out.println("Vehicle Form:");
        System.out.println("Vehicle Plate:");
        System.out.println("Brand:");
        System.out.println("Model:");
        System.out.println("Type:");
        System.out.println("Tare weight:");
        System.out.println("Gross Weight:");
        System.out.println("Current Km:");
        System.out.println("Register Date:");
        System.out.println("Acquisition Date:");
        System.out.println("Maintenance Frequency:");
    }

    /**
     * Gets vehicle data from the user through the console.
     * @return True if the vehicle was successfully added, false otherwise
     */
    public boolean getVehicleDataFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Vehicle details:");
        System.out.print("Plate:");
        String plate = scanner.nextLine();
        System.out.print("Brand:");
        String brand = scanner.nextLine();
        System.out.print("Model:");
        String model = scanner.nextLine();
        System.out.print("Type:");
        String type = scanner.nextLine();
        System.out.print("Tare Weight:");
        float tareWeight = scanner.nextFloat();
        System.out.print("Gross Weight:");
        float grossWeight = scanner.nextFloat();
        System.out.print("Current km:");
        float currentKm = scanner.nextFloat();
        scanner.nextLine();
        System.out.print("Register Date:");
        String registerDate = scanner.nextLine();
        System.out.print("Acquisition Date:");
        String acquisitionDate = scanner.nextLine();
        System.out.print("Maintenance Frequency:");
        float maintenanceFrequency = scanner.nextFloat();

        return controller.addVehicle(plate, brand, model, type, tareWeight, grossWeight,
                currentKm, registerDate, acquisitionDate, maintenanceFrequency);
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
     * Navigates back to the main menu of the Vehicle Fleet Management UI.
     */
    private void backToVFMUI() {
        MenuItem item = new MenuItem(AuthenticationController.ROLE_VFM, new VFMUI());
        item.run();
    }

    /**
     * Runs the CreateVehicleUI functionality.
     * Displays the vehicle form, gets data from the user, and displays success or failure messages.
     * Finally, navigates back to the main menu of the Vehicle Fleet Management UI.
     */
    public void run() {
        displayVehicleForm();
        boolean result = getVehicleDataFromUser();
        if (result) {
            displayOperationSuccess();
        } else {
            displayOperationFailure();
        }
        backToVFMUI();
    }
}
