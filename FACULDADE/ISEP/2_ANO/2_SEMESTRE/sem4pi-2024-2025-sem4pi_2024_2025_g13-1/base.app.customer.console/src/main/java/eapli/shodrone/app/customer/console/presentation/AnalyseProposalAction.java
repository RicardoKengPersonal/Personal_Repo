package eapli.shodrone.app.customer.console.presentation;

import eapli.framework.actions.Action;

public class AnalyseProposalAction implements Action {

    @Override
    public boolean execute() {
        return new AnalyseProposalUI().show();
    }
}
