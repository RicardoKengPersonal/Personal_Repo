package eapli.shodrone.app.customer.console.presentation;

import eapli.framework.actions.Action;

public class CheckShowsDatesAction implements Action {

    @Override
    public boolean execute() {
        return new CheckShowsDatesUI().show();
    }
}
