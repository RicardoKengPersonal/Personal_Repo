package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.actions.Action;

public class DroneInventoryRemovalAction implements Action {
    public boolean execute() { return new DroneInventoryRemovalUI().show(); }
}
