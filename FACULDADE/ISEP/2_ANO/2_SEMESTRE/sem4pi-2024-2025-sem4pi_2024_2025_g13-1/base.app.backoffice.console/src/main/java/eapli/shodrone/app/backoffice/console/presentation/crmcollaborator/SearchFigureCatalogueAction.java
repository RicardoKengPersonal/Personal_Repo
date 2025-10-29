package eapli.shodrone.app.backoffice.console.presentation.crmcollaborator;

import eapli.framework.actions.Action;

public class SearchFigureCatalogueAction implements Action {

    @Override
    public boolean execute() {
        return new SearchFigureCatalogueUI().show();
    }
}
