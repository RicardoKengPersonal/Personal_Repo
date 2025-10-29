package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.shodrone.dronemanagement.application.DroneLanguageValidatorController;
import eapli.shodrone.dronemanagement.application.viadto.ListDronesWithLanguagesDTOController;
import eapli.shodrone.dronemanagement.dto.DroneLanguagesDTO;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

public class ValidateDroneLanguageUI extends AbstractUI {

    private final ListDronesWithLanguagesDTOController listController = new ListDronesWithLanguagesDTOController();

    private final DroneLanguageValidatorController validatorController = new DroneLanguageValidatorController(

                    AuthzRegistry.authorizationService(),
                    PersistenceContext.repositories().drone(),
                    PersistenceContext.repositories().droneModel(),
                    PersistenceContext.repositories().maintenanceType()
            );

    @Override
    protected boolean doShow() {
        List<DroneLanguagesDTO> droneList = new ArrayList<>();
        listController.dronesWithLanguages().forEach(droneList::add);

        if (droneList.isEmpty()) {
            System.out.println("No drones with programming languages.");
            return false;
        }

        System.out.println("\nDRONES WITH PROGRAMMING LANGUAGES:");
        int index = 1;
        for (DroneLanguagesDTO dto : droneList) {
            System.out.printf("%d. Drone ID: %s | Language: %s%n", index++, dto.getId(), dto.getProgrammingLanguage());
        }

        int selected = Console.readInteger("\nSelect a drone by number to validate its language: ");
        if (selected < 1 || selected > droneList.size()) {
            System.out.println("Invalid selection.");
            return false;
        }

        DroneLanguagesDTO selectedDrone = droneList.get(selected - 1);
        String lang = selectedDrone.getProgrammingLanguage();

        String filePath;

        switch (lang.toLowerCase()) {
            case "one":
                filePath = "base.integrations.plugins.dsl/lprog/files input/DroneOne drone progamming language.txt";
                break;
            case "two":
                filePath = "base.integrations.plugins.dsl/lprog/files input/DroneTwo drone progamming language.txt";
                break;
            default:
                System.out.println("Unknown programming language '" + lang + "'. Cannot determine file path.");
                return false;
        }

        try {
            boolean valid = validatorController.validateDroneProgram(filePath, lang);
            if (valid) {
                System.out.println("Validation successful for drone language '" + lang + "'.");
            } else {
                System.out.println("Validation failed for drone language '" + lang + "'.");
            }
            return valid;
        } catch (IllegalArgumentException e) {
            System.out.println("Error during validation: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Unexpected error during validation: " + e.getMessage());
            return false;
        }
    }



    @Override
    public String headline() {
        return "Validate Drone Programming Language";
    }
}
