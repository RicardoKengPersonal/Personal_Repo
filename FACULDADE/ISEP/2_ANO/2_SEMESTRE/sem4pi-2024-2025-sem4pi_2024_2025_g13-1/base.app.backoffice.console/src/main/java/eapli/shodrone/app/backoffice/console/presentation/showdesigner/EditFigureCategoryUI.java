package eapli.shodrone.app.backoffice.console.presentation.showdesigner;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.shodrone.figuremanagement.domain.FigureCategory;
import eapli.shodrone.figuremanagement.domain.FigureCategoryID;
import eapli.shodrone.figuremanagement.application.EditFigureCategoryController;

public class EditFigureCategoryUI extends AbstractUI {

    private final EditFigureCategoryController controller;

    public EditFigureCategoryUI() {
        this.controller = new EditFigureCategoryController(
                eapli.framework.infrastructure.authz.application.AuthzRegistry.authorizationService(),
                eapli.shodrone.infrastructure.persistence.PersistenceContext.repositories().figureCategory());
    }

    @Override
    protected boolean doShow() {
        try {
            System.out.println("\n--- Figure Categories ---");
            for (FigureCategory cat : controller.listAllCategories()) {
                System.out.printf("ID: %s | Name: %s | Status: %s%n",
                        cat.identity(), cat.name(), cat.isActive() ? "ACTIVE" : "INACTIVE");
            }

            final String oldName = Console.readLine("\nEnter Name of category to edit: ");
            final String newName = Console.readLine("Enter new name for the category: ");
            controller.editCategory(oldName, newName);
            System.out.println("✔ Category updated successfully.");

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("⚠ " + e.getMessage());
        } catch (Exception ex) {
            System.out.println("⚠ Unexpected error: " + ex.getMessage());
        }

        return true;
    }

    @Override
    public String headline() {
        return "Edit Figure Category";
    }
}
