package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.io.util.Console;
import eapli.shodrone.dronemanagement.application.DroneModelCreationController;
import eapli.shodrone.dronemanagement.domain.DroneType;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import java.text.SimpleDateFormat;


import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class DroneModelCreationUI extends AbstractUI {
    private final DroneModelCreationController controller = new DroneModelCreationController(
            AuthzRegistry.authorizationService(), PersistenceContext.repositories().droneModel(),PersistenceContext.repositories().drone()
    ,PersistenceContext.repositories().maintenanceType());

    @Override
    protected boolean doShow() {
        boolean valid = false;

        while(!valid){
            try{
                final String name = Console.readLine("Name:");
                final String manufacturer = Console.readLine("Manufacturer:");
                final float xAxisTolerance =(float) Console.readDouble("Drone wind tolerance for the X axis (in meters):");
                final float yAxisTolerance =(float) Console.readDouble("Drone wind tolerance for the Y axis (in meters):");
                final float zAxisTolerance =(float) Console.readDouble("Drone wind tolerance for the Z axis (in meters):");
                final float maxSpeed = (float) Console.readDouble("Maximum speed (in m/s):");
                final float maxRotationSpeed = (float) Console.readDouble("Maximum rotation speed (in rad/s):");

                final String lightingInput = Console.readLine("Lighting options (comma-separated, e.g., RED,GREEN,YELLOW):");

                final Set<DroneType> lightingOptions = new HashSet<>();
                for (String option : lightingInput.split(",")) {

                    lightingOptions.add(DroneType.valueOf(option.trim().toUpperCase()));
                }

                final Calendar ofDay = Calendar.getInstance();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                System.out.println("Registration date: " + formatter.format(ofDay.getTime()));

                this.controller.addDroneModel(name, manufacturer, xAxisTolerance, yAxisTolerance, zAxisTolerance, ofDay, maxSpeed, maxRotationSpeed, lightingOptions);

                System.out.println("DroneModel created");
                valid = true;
            } catch (IllegalArgumentException e)
            {
                System.out.println("Error: "+ e.getMessage());
            }
        }
        return true;
    }

    @Override
    public String headline(){ return "Add Drone Model";}
}
