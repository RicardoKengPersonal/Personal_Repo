package eapli.shodrone.app.testing.console.presentation;

import eapli.framework.actions.Action;

public class RunSimulationAction implements Action {

    @Override
    public boolean execute() {
        return new RunSimulationUI().show();
    }
}
