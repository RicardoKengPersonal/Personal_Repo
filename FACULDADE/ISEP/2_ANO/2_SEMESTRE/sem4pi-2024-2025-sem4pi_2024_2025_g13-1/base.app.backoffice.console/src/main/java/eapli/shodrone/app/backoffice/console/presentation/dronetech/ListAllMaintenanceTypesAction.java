package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.actions.Action;

public class ListAllMaintenanceTypesAction implements Action{
    public boolean execute() { return new ListAllMaintenanceTypesUI().show(); }
}
