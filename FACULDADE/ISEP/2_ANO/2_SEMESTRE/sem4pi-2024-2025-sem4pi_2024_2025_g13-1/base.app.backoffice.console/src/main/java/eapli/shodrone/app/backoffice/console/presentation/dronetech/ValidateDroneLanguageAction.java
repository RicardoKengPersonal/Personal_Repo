package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.actions.Action;

public class ValidateDroneLanguageAction implements Action  {
    public boolean execute() { return new ValidateDroneLanguageUI().show(); }
}
