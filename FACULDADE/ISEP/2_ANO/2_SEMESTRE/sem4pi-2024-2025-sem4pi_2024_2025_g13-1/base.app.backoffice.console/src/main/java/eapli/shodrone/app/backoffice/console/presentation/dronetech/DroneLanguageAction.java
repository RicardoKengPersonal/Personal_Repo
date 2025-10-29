package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.actions.Action;

public class DroneLanguageAction implements Action  {
    public boolean execute() { return new DroneLanguageUI().show(); }
}
