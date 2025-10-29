package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;
import eapli.shodrone.dronemanagement.application.viadto.ListMaintenanceTypesDTOController;
import eapli.shodrone.dronemanagement.dto.MaintenanceTypeDTO;

public class ListMaintenanceTypesDTOUI extends AbstractListUI<MaintenanceTypeDTO> {

    private final ListMaintenanceTypesDTOController theController = new ListMaintenanceTypesDTOController();

    @Override
    protected Iterable<MaintenanceTypeDTO> elements() {
        return this.theController.allTypes();
    }

    @Override
    protected Visitor<MaintenanceTypeDTO> elementPrinter() {
        return new MaintenanceTypesDTOPrinter();
    }

    @Override
    protected String elementName() {
        return "Maintenance Type";
    }

    @Override
    protected String listHeader() {
        return "MAINTENANCE TYPES";
    }

    @Override
    protected String emptyMessage() {
        return "No data.";
    }

    @Override
    public String headline() {
        return "List Maintenance Types";
    }

}
