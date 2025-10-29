package eapli.shodrone.app.backoffice.console.presentation.crmcollaborator;

import eapli.framework.actions.Action;

public class MarkShowProposalAcceptedAction implements Action{
    public boolean execute() { return new MarkShowProposalAcceptedUI().show(); }
}