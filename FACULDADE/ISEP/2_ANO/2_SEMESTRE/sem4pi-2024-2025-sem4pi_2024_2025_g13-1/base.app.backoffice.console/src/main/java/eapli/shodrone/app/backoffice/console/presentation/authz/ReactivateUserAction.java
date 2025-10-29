package eapli.shodrone.app.backoffice.console.presentation.authz;

import eapli.framework.actions.Action;

public class ReactivateUserAction implements Action {
    //
    @Override
    public boolean execute() {
        return new ReactivateUserUI().show();
    }
}
