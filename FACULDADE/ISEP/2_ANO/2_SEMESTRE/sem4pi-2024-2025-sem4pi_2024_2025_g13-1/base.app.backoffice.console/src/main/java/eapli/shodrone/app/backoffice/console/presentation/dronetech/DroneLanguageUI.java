package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.shodrone.dronemanagement.application.DroneLanguageController;
import eapli.shodrone.dronemanagement.application.ListActiveDronesByModelController;
import eapli.shodrone.dronemanagement.domain.Drone;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;

import java.util.Optional;
import java.util.Scanner;

public class DroneLanguageUI extends AbstractUI {

    private final ListActiveDronesByModelController listDronesController =
            new ListActiveDronesByModelController(
                    AuthzRegistry.authorizationService(),
                    PersistenceContext.repositories().drone(),
                    PersistenceContext.repositories().droneModel(),
                    PersistenceContext.repositories().maintenanceType()
            );

    private final DroneLanguageController assignLanguageController =
            new DroneLanguageController(
                    AuthzRegistry.authorizationService(),
                    PersistenceContext.repositories().droneModel(),
                    PersistenceContext.repositories().drone(),
                    PersistenceContext.repositories().maintenanceType()
            );

    @Override
    protected boolean doShow() {
        try {
            Iterable<Drone> drones = listDronesController.findAllDrones();
            System.out.println("\nAll drones:");
            boolean any = false;
            for (Drone drone : drones) {
                System.out.println(" - " + drone);
                any = true;
            }

            if (!any) {
                System.out.println("No drones found.");
                return false;
            }

            final String droneName = Console.readLine("Enter Drone Name to assign a programming language: ");
            Optional<Drone> optionalDrone = assignLanguageController.findDroneByName(droneName);

            if (optionalDrone.isEmpty()) {
                System.out.println("No drone found with that name.");
                return false;
            }

            Drone drone = optionalDrone.get();
            System.out.println("Drone found.");
            selectLanguageForDrone(drone);
            return true;

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }

        return false;
    }

    private void selectLanguageForDrone(Drone drone) {
        String pathOneLanguage = "base.integrations.plugins.dsl/lprog/files input/DroneOne drone progamming language.txt";
        String pathTwoLanguage = "base.integrations.plugins.dsl/lprog/files input/DroneTwo drone progamming language.txt";
        Scanner scanner = new Scanner(System.in);
        boolean validLanguage = false;

        while (!validLanguage) {
            System.out.println("Select a drone programming language: (One/Two)");
            String language = scanner.nextLine();

            switch (language) {
                case "One":
                    drones.Drone.droneOnePlugin(pathOneLanguage);
                    assignLanguageController.assignLanguageToDrone(drone, "One");
                    System.out.println("Drone language 'One' assigned and saved.");
                    validLanguage = true;
                    break;
                case "Two":
                    drones.Drone.droneTwoPlugin(pathTwoLanguage);
                    assignLanguageController.assignLanguageToDrone(drone, "Two");
                    System.out.println("Drone language 'Two' assigned and saved.");
                    validLanguage = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    @Override
    public String headline() {
        return "Assign Programming Language to Drone";
    }
}
