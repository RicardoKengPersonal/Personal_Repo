package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.actions.Action;

public class RegisterMaintenanceRecordDroneAction implements Action {
    public boolean execute() { return new RegisterMaintenanceRecordDroneUI().show(); }
}
