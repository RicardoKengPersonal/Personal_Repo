package eapli.shodrone.app.backoffice.console.presentation.crmcollaborator;
import eapli.framework.actions.Action;
public class ListShowRequestAction implements Action {

    public boolean execute() {
        return new ListShowRequestUI().show();
    }

}
