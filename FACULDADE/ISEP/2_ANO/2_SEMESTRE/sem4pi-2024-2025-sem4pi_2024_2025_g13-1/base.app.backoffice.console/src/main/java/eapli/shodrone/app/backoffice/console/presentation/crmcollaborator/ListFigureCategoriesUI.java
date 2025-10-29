package eapli.shodrone.app.backoffice.console.presentation.crmcollaborator;

import eapli.framework.presentation.console.AbstractUI;
import eapli.shodrone.figuremanagement.application.ListFigureCategoriesController;
import eapli.shodrone.figuremanagement.domain.FigureCategory;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;

public class ListFigureCategoriesUI extends AbstractUI {

    private final ListFigureCategoriesController controller =
            new ListFigureCategoriesController(
                    AuthzRegistry.authorizationService(),
                    PersistenceContext.repositories().figureCategory());

    @Override
    protected boolean doShow() {
        try {
            System.out.println("\n--- Figure Categories ---");
            for (FigureCategory cat : controller.listAllCategories()) {
                System.out.printf("ID: %s | Name: %s | Status: %s%n",
                        cat.identity(), cat.name(), cat.isActive() ? "ACTIVE" : "INACTIVE");
            }
        } catch (Exception e) {
            System.out.println("⚠ Error listing categories: " + e.getMessage());
        }

        return true;
    }

    @Override
    public String headline() {
        return "List Figure Categories";
    }
}
