package eapli.shodrone.app.backoffice.console.presentation.showdesigner;
import eapli.framework.actions.Action;

public class EditFigureCategoryAction implements Action {

    @Override
    public boolean execute() { return new EditFigureCategoryUI().show(); }
}
