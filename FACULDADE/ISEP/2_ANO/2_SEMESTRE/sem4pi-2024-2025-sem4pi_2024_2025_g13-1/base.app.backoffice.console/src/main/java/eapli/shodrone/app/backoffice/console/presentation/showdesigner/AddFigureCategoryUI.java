package eapli.shodrone.app.backoffice.console.presentation.showdesigner;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.io.util.Console;
import eapli.shodrone.figuremanagement.application.AddFigureCategoryController;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;

public class AddFigureCategoryUI extends AbstractUI {

    private final AddFigureCategoryController controller = new AddFigureCategoryController(
            AuthzRegistry.authorizationService(), PersistenceContext.repositories().figureCategory());

    @Override
    protected boolean doShow() {
        boolean valid = false;

        while (!valid) {
            try {
                final String name = Console.readLine("Enter category name:");
                this.controller.addCategory(name);
                System.out.println("Category added successfully!");
                valid = true;
            } catch (IllegalArgumentException e) {
                System.out.println("⚠ " + e.getMessage());
            }
        }

        return true;
    }

    @Override
    public String headline() {
        return "Add New Figure Category";
    }
}
