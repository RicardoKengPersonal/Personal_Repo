package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.actions.Action;

public class GenerateAndValidateProposalAction implements Action {
    @Override
    public boolean execute() {
        return new GenerateAndValidateProposalUI().show();
    }
}
