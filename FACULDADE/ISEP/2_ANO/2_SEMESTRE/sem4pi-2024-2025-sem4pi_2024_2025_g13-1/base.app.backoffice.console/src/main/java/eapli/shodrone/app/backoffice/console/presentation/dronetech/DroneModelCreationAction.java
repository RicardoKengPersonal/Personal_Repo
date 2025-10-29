package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.actions.Action;

public class DroneModelCreationAction implements Action{
    public boolean execute() { return new DroneModelCreationUI().show(); }
}
