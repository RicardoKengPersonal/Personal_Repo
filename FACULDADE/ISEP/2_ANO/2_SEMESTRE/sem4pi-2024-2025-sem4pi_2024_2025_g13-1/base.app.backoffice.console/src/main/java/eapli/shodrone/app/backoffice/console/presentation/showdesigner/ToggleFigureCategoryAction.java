package eapli.shodrone.app.backoffice.console.presentation.showdesigner;

import eapli.framework.actions.Action;

public class ToggleFigureCategoryAction implements Action {

    public boolean execute() { return new ToggleFigureCategoryUI().show(); }
}
