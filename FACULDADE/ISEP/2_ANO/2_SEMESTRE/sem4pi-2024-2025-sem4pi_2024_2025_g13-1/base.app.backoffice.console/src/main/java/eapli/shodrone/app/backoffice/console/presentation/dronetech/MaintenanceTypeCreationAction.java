package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.actions.Action;

public class MaintenanceTypeCreationAction implements Action {
    public boolean execute() { return new MaintenanceTypeCreationUI().show(); }
}
