package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.visitor.Visitor;
import eapli.shodrone.dronemanagement.dto.MaintenanceTypeDTO;

public class MaintenanceTypesDTOPrinter implements Visitor<MaintenanceTypeDTO> {

    @Override
    public void visit(final MaintenanceTypeDTO visitee) {
        System.out.printf(" - Name: %s, Description: %s%n",
                visitee.getName(),
                visitee.getDescription()
        );
    }
}