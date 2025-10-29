package eapli.shodrone.app.backoffice.console.presentation.crmcollaborator;
import eapli.framework.actions.Action;

public class CreateShowProposalAction implements Action{
    @Override
    public boolean execute() {
        return new CreateShowProposalUI().show();
    }
}
