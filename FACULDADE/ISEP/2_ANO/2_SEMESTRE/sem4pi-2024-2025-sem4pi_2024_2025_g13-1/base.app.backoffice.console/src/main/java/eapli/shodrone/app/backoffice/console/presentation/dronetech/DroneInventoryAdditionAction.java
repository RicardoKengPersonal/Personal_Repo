package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.actions.Action;

public class DroneInventoryAdditionAction implements Action {
    public boolean execute() { return new DroneInventoryAdditionUI().show(); }
}
