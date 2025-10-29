package eapli.shodrone.app.customer.console.presentation;

import eapli.framework.actions.Action;

public class AcceptProposalAction implements Action {
    @Override
    public boolean execute() {
        return new AcceptProposalUI().show();
    }
}
