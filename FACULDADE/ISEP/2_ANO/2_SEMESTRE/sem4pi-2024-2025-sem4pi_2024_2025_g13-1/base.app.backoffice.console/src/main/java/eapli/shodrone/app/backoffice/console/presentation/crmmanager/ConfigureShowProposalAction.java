package eapli.shodrone.app.backoffice.console.presentation.crmmanager;
import eapli.framework.actions.Action;
public class ConfigureShowProposalAction implements Action {

    public boolean execute() {
        return new ConfigureShowProposalUI().show();
    }

}
