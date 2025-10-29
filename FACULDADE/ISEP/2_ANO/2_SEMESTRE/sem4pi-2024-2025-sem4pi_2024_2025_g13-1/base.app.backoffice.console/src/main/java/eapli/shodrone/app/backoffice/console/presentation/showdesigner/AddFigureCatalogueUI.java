package eapli.shodrone.app.backoffice.console.presentation.showdesigner;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.SelectWidget;
import eapli.shodrone.dronemanagement.domain.DroneModel;
import eapli.shodrone.dronemanagement.domain.DroneType;
import eapli.shodrone.figuremanagement.application.AddFigureCatalogueController;
import eapli.shodrone.figuremanagement.domain.*;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class AddFigureCatalogueUI extends AbstractUI {

    private final AddFigureCatalogueController controller =
            new AddFigureCatalogueController(
                    AuthzRegistry.authorizationService(),
                    PersistenceContext.repositories().figure(),
                    PersistenceContext.repositories().figureCategory()
            );

    @Override
    protected boolean doShow() {
        boolean valid = false;
        while (!valid) {
            try {
                System.out.println("Do you want to search for an existing Figure by:");
                System.out.println("1 - Keyword");
                System.out.println("2 - Category");
                System.out.println("3 - List all");
                System.out.println("4 - Add a new figure");

                int option = Console.readOption(1, 4, 0);

                Optional<Figure> selectedFigure = Optional.empty();
                switch (option) {
                    case 1 -> {
                        String keyword = Console.readLine("Enter keyword:");
                        selectedFigure = controller.findByKeyword(keyword);
                    }
                    case 2 -> {
                        System.out.println("\nAvailable categories:");
                        controller.listCategoriesActive().forEach(cat -> System.out.println(" - " + cat.name()));
                        String categoryName = Console.readLine("Enter category name:");
                        Optional<FigureCategoryID> categoryOpt = controller.findCategoryByName(categoryName);
                        if (categoryOpt.isPresent()) {
                            List<Figure> figuresByCategory = controller.findByCategory(categoryOpt.get());
                            if (!figuresByCategory.isEmpty()) {
                                System.out.println("\nAvailable Figure:");
                                figuresByCategory.forEach(f -> System.out.println(" - " + f.figureName()));
                                String figureName = Console.readLine("Enter the name of the figure to use:");
                                selectedFigure = controller.findByName(figureName);
                            }
                        }
                    }
                    case 3 -> {
                        List<Figure> allFigures = controller.findAllFigures();
                        if (!allFigures.isEmpty()) {
                            allFigures.forEach(f -> System.out.println(" - " + f.figureName()));
                            String figureName = Console.readLine("Enter the name of the figure to use:");
                            selectedFigure = controller.findByName(figureName);
                        }
                    }
                    case 4 -> {
                        System.out.println("\nCreating a new figure.");
                        final String name = Console.readLine("Name:");
                        final String keyword = Console.readLine("Keyword:");
                        final String description = Console.readLine("Description:");
                        final String visibilityInput = Console.readLine("Is this figure public? (yes/no):").trim().toLowerCase();
                        final FigureVisibility visibility = visibilityInput.equals("yes") ? FigureVisibility.PUBLIC : FigureVisibility.PRIVATE;
                        System.out.println("\nAvailable categories:");
                        controller.listCategoriesActive().forEach(cat -> System.out.println(" - " + cat.name()));
                        String categoryName = Console.readLine("Enter category name:");
                        Optional<FigureCategoryID> categoryOpt = controller.findCategoryByName(categoryName);

                        if (categoryOpt.isEmpty()) {
                            System.out.println("Category not found. Figure creation aborted.");
                            break;
                        }

                        try {
                            selectedFigure = Optional.of(
                                    controller.createFigure(name, keyword, description, categoryOpt, visibility)
                            );
                        } catch (IllegalArgumentException e) {
                            System.out.println("⚠ Error: " + e.getMessage());
                            boolean tryAgain = Console.readBoolean("Do you want to try again? (y/n):");
                            if (tryAgain) {
                                continue;
                            } else {
                                System.out.println("\nAborting operation.");
                                return false;
                            }
                        }
                    }


                }

                Figure figure;
                FigureVersion version;
                SystemUser user = AuthzRegistry.authorizationService().session().get().authenticatedUser();

                if (selectedFigure.isPresent()) {
                    figure = selectedFigure.get();
                } else {
                    System.out.println("⚠ No figures found with the specified criteria.");
                    boolean tryAgain = Console.readBoolean("Do you want to try again? (y/n):");
                    if (tryAgain) {
                        continue;
                    } else {
                        System.out.println("\nAborting operation.");
                        return false;
                    }
                }

                System.out.println("\n");
                String dslValue = Console.readLine("DSL code:");
                DSL dsl = new DSL(dslValue);
                String staticValue = Console.readLine("Figure Static (yes/no):");
                FigureStatic figureStatic = new FigureStatic(staticValue);
                String dynamicValue = Console.readLine("Figure Dynamic (yes/no):");
                FigureDynamic figureDynamic = new FigureDynamic(dynamicValue);

                version = controller.addVersionToFigure(figure, dsl, figureStatic, figureDynamic, user);

                System.out.println("\nFigure Element:");

                boolean addMore = true;
                do {
                    try {
                        ElementType type = ElementType.valueOf(Console.readLine("Element type (GEOMETRIC, BITMAPS_3D):"));
                        Movement movement = Movement.valueOf(Console.readLine("Movement (ROTATION, TRANSLATION):"));
                        System.out.println("\nAvailable drone types:");
                        SelectWidget<DroneType> typeSelector = new SelectWidget<>("Select the required drone type:", List.of(DroneType.values()));
                        typeSelector.show();
                        DroneType requiredType = typeSelector.selectedElement();

                        if (requiredType == null) {
                            System.out.println("No type selected. Element not added.");
                            continue;
                        }

                        int totalDrones = Console.readInteger("Number of drones for this element:");

                        controller.addElementToVersionByType(version, type, movement, requiredType, totalDrones);

                        addMore = Console.readBoolean("Do you want to add another Figure Element? (y/n):");
                    } catch (IllegalArgumentException e) {
                        System.out.println("⚠ Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("⚠ Unexpected error: " + e.getMessage());
                    }
                } while (addMore);

                controller.saveFigure(figure);

                System.out.println("+==============================================================================+");
                System.out.println("Figure successfully added to the catalogue!");
                valid = true;

            } catch (IllegalArgumentException e) {
                System.out.println("\u26A0 Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("\u26A0 Unexpected error: " + e.getMessage());
            }
        }
        return true;
    }

    @Override
    public String headline() {
        return "Add Figure to the Catalogue";
    }
}
