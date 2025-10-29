package eapli.shodrone.app.backoffice.console.presentation.crmcollaborator;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.io.util.Console;
import eapli.shodrone.figuremanagement.application.SearchFigureCatalogueController;
import eapli.shodrone.figuremanagement.domain.Figure;
import eapli.shodrone.figuremanagement.domain.FigureCategoryID;
import eapli.shodrone.figuremanagement.domain.FigureVersion;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class SearchFigureCatalogueUI extends AbstractUI {

    private final SearchFigureCatalogueController controller =
            new SearchFigureCatalogueController(
                    AuthzRegistry.authorizationService(),
                    PersistenceContext.repositories().figure(),
                    PersistenceContext.repositories().figureCategory()
            );

    @Override
    protected boolean doShow() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("\n--- Search Figures Catalogue ---");
            System.out.println("1. Search by Category");
            System.out.println("2. Search by Keywords");
            System.out.println("3. Search by Category and Keywords");
            int op = scanner.nextInt();
            scanner.nextLine();

            Iterable<Figure> results = null;

            switch (op) {
                case 1 -> {
                    System.out.println("\nAvailable categories:");
                    controller.listCategoriesActive().forEach(cat -> System.out.println(" - " + cat.name()));
                    String categoryName = Console.readLine("Enter category name:");
                    Optional<FigureCategoryID> categoryOpt = controller.findCategoryByName(categoryName);
                    if (categoryOpt.isEmpty()) {
                        System.out.println("⚠ Category not found.");
                        return false;
                    }
                    results = controller.searchByCategory(categoryOpt.get());
                }

                case 2 -> {
                    String keywordsInput = Console.readLine("Enter keywords (comma-separated):");
                    List<String> kwList = Arrays.asList(keywordsInput.split("\\s*,\\s*"));
                    results = controller.searchByKeywords(kwList);
                }

                case 3 -> {
                    String categoryName = Console.readLine("Enter category name:");
                    Optional<FigureCategoryID> categoryOpt = controller.findCategoryByName(categoryName);
                    if (categoryOpt.isEmpty()) {
                        System.out.println("⚠ Category not found.");
                        return false;
                    }

                    String keywordsInput = Console.readLine("Enter keywords (comma-separated):");
                    List<String> keywordList = Arrays.asList(keywordsInput.split("\\s*,\\s*"));
                    results = controller.searchByCategoryAndKeywords(categoryOpt.get(), keywordList);
                }

                default -> {
                    System.out.println("Invalid option");
                    return false;
                }
            }

            System.out.println("\n--- Results ---");
            if (!results.iterator().hasNext()) {
                System.out.println("No matching public figures found.");
            } else {
                for (Figure fig : results) {
                    if (fig.isPublic()) {
                        for (FigureVersion version : fig.versions()) {
                            System.out.printf(
                                    "ID: %s | Name: %s | Desc: %s | Version: %s | DSL: %s | Designer: %s | Visibility: %s%n",
                                    fig.identity(),
                                    fig.figureName(),
                                    fig.description(),
                                    version.version(),
                                    version.dsl().toString(),
                                    version.designer().identity(),
                                    fig.visibility()
                            );
                        }
                    }
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println("⚠ Error: " + e.getMessage());
        }
        return true;
    }

    @Override
    public String headline() {
        return "Search Figures Catalogue";
    }
}
