package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignMaintenanceController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.VFMUI;


/**
 * The AssignMaintenanceUI class represents the user interface for assigning maintenance tasks to vehicles.
 * It interacts with the AssignMaintenanceController to display lists of vehicles and identify which vehicles need maintenance.
 */
public class AssignMaintenanceUI implements Runnable {
    private final AssignMaintenanceController controller;

    /**
     * Constructs a new AssignMaintenanceUI instance.
     */
    public AssignMaintenanceUI(){
        controller = new AssignMaintenanceController();
    }

    /**
     * Navigates back to the main menu of the Vehicle Fleet Management (VFM) system.
     */
    private void backToVFMUI() {
        MenuItem item = new MenuItem(AuthenticationController.ROLE_VFM, new VFMUI());
        item.run();
    }

    /**
     * Runs the AssignMaintenanceUI, displaying the list of vehicles and identifying those needing maintenance.
     */
    public void run() {
        System.out.println("Vehicles registered:");
        controller.displayVehiclesList();
        System.out.println("------------------------------------");
        controller.getVehiclesNeedingMaintenance();
        backToVFMUI();
    }
}
