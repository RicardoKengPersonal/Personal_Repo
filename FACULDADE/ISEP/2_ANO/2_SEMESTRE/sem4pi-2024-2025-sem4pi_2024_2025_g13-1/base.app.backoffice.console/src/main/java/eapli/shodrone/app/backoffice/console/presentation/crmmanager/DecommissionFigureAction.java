package eapli.shodrone.app.backoffice.console.presentation.crmmanager;

import eapli.framework.actions.Action;


public class DecommissionFigureAction implements Action {

    @Override
    public boolean execute() {

        return new DecommissionFigureUI().show();

    }
}