package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.actions.Action;

public class EditMaintenanceTypeAction implements Action {
    public boolean execute() { return new EditMaintenanceTypeUI().show(); }
}
