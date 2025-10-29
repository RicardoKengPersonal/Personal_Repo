package eapli.shodrone.app.backoffice.console.presentation.crmcollaborator;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.shodrone.figuremanagement.application.ListPublicFiguresController;
import eapli.shodrone.figuremanagement.domain.Figure;
import eapli.shodrone.figuremanagement.domain.FigureVersion;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;

public class ListFigureCatalogueUI extends AbstractUI {

    private final ListPublicFiguresController controller =
            new ListPublicFiguresController(
                    AuthzRegistry.authorizationService(),
                    PersistenceContext.repositories().figure(),
                    PersistenceContext.repositories().figureCategory()
            );

    @Override
    protected boolean doShow() {
        try {
            System.out.println("\n--- Public Figures Catalogue ---");

            Iterable<Figure> figures = controller.listPublicFigures();
            boolean foundAny = false;

            for (Figure fig : figures) {
                for (FigureVersion version : fig.versions()) {
                    if (fig.isPublic()) {
                        System.out.printf(
                                "ID: %s | Name: %s | Desc: %s | Version: %s | Designer: %s | DSL: %s | Visibility: %s%n",
                                fig.identity(),
                                fig.figureName(),
                                fig.description(),
                                version.version(),
                                version.designer().identity(),
                                version.dsl().toString(),
                                fig.visibility()
                        );
                        foundAny = true;
                    }
                }
            }

            if (!foundAny) {
                System.out.println("No public figures available.");
            }

        } catch (Exception e) {
            System.out.println("⚠ Error listing public figures: " + e.getMessage());
        }

        return true;
    }

    @Override
    public String headline() {
        return "List Public Figures Catalogue";
    }
}
