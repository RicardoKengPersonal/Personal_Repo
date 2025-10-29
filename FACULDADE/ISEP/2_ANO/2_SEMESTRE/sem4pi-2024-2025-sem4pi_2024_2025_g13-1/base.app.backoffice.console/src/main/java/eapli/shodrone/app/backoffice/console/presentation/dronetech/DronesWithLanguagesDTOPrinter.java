package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.visitor.Visitor;
import eapli.shodrone.dronemanagement.dto.DroneLanguagesDTO;
import eapli.shodrone.dronemanagement.dto.MaintenanceTypeDTO;

public class DronesWithLanguagesDTOPrinter implements Visitor<DroneLanguagesDTO> {

    @Override
    public void visit(final DroneLanguagesDTO visitee) {
        System.out.printf(" - Name: %s, Description: %s%n",
                visitee.getId(),
                visitee.getProgrammingLanguage()
        );
    }
}
