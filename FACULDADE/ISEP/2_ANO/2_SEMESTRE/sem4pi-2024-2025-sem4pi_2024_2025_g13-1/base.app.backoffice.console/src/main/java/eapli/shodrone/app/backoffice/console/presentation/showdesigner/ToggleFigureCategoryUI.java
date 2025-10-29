package eapli.shodrone.app.backoffice.console.presentation.showdesigner;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.shodrone.figuremanagement.application.ToggleFigureCategoryController;
import eapli.shodrone.figuremanagement.domain.FigureCategory;
import eapli.shodrone.figuremanagement.domain.FigureCategoryID;

public class ToggleFigureCategoryUI extends AbstractUI {

    private final ToggleFigureCategoryController controller;

    public ToggleFigureCategoryUI() {
        this.controller = new ToggleFigureCategoryController(
                eapli.framework.infrastructure.authz.application.AuthzRegistry.authorizationService(),
                eapli.shodrone.infrastructure.persistence.PersistenceContext.repositories().figureCategory());
    }

    protected boolean doShow() {
        try {
            System.out.println("\n--- Figure Categories ---");
            for (FigureCategory cat : controller.listAllCategories()) {
                System.out.printf("ID: %s | Name: %s | Status: %s%n",
                        cat.identity(), cat.name(), cat.isActive() ? "ACTIVE" : "INACTIVE");
            }

            final String oldName = Console.readLine("\nEnter Name of category to toggle: ");
            final FigureCategory updated = controller.toggleActivation(oldName);
            System.out.println(updated);
            System.out.printf("✔ Category '%s' is now %s.%n",
                    updated.name(), updated.isActive() ? "ACTIVE" : "INACTIVE");

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("⚠ Error: " + e.getMessage());
        } catch (Exception ex) {
            System.out.println("⚠ Unexpected error: " + ex.getMessage());
        }

        return true;
    }

    @Override
    public String headline() {
        return "Toggle Figure Category";
    }
}
