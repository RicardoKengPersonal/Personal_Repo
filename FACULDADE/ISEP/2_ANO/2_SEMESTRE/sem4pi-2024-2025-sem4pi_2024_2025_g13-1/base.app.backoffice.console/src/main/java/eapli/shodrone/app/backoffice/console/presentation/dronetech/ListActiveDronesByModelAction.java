package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.actions.Action;

public class ListActiveDronesByModelAction implements Action {
    public boolean execute() { return new ListActiveDronesUI().show(); }
}
