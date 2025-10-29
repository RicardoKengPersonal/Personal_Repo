package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.AssignMaintenanceUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class VFMUI implements Runnable {

    /**
     * Constructs a new instance of VFMUI.
     */
    public VFMUI() {
    }

    /**
     * Runs the VFM user interface.
     */
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register a Vehicle", new CreateVehicleUI()));
        options.add(new MenuItem("Register vehicle's maintenance",new RegisterMaintenanceUI()));
        options.add(new MenuItem("Show vehicles needing maintenance",new AssignMaintenanceUI()));
        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- VFM MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}