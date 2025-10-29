package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.shodrone.figuremanagement.application.AddDslPathController;
import eapli.shodrone.figuremanagement.application.ListPublicFiguresController;
import eapli.shodrone.figuremanagement.domain.Figure;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.integrations.plugins.dsl.figure.*;


import java.util.*;

public class FigurePluginUI extends AbstractUI {

    private final ListPublicFiguresController controller =
            new ListPublicFiguresController(
                    AuthzRegistry.authorizationService(),
                    PersistenceContext.repositories().figure(),
                    PersistenceContext.repositories().figureCategory()
            );

    private final AddDslPathController controller2 =
            new AddDslPathController(
                    AuthzRegistry.authorizationService(),
                    PersistenceContext.repositories().figure(),
                    PersistenceContext.repositories().figureCategory()
            );


    private final Scanner scanner = new Scanner(System.in);

    @Override
    protected boolean doShow() {
        try {
            System.out.println("\n--- Public Figures ---");

            List<Figure> publicFigures = new ArrayList<>();
            int index = 1;

            for (Figure fig : controller.listPublicFigures()) {
                if (fig.isPublic()) {
                    System.out.printf("%d) %s | %s\n", index, fig.figureName(), fig.description());
                    publicFigures.add(fig);
                    index++;
                }
            }

            if (publicFigures.isEmpty()) {
                System.out.println("No public figures available.");
                return false;
            }

            System.out.print("\nSelect a figure by number: ");
            int selectedFigureIndex = Integer.parseInt(scanner.nextLine());

            if (selectedFigureIndex < 1 || selectedFigureIndex > publicFigures.size()) {
                System.out.println("Invalid selection.");
                return false;
            }

            Figure selectedFigure = publicFigures.get(selectedFigureIndex - 1);

            System.out.println("\nSelect which DSL script version to interpret:");
            System.out.println("1) sample_DSL_figure_1");
            System.out.println("2) sample_DSL_figure_2");
            System.out.println("3) sample_DSL_figure_3");
            System.out.print("Choice: ");
            int versionChoice = Integer.parseInt(scanner.nextLine());

            String filePath;
            String dslTxt;

            if (versionChoice == 1) {
                filePath = "base.integrations.plugins.dsl/lprog/files input/sample_DSL_figure_1.txt";
                dslTxt ="sample_DSL_figure_1.txt";
            } else if (versionChoice == 2) {
                filePath = "base.integrations.plugins.dsl/lprog/files input/sample_DSL_figure_2.txt";
                dslTxt ="sample_DSL_figure_2.txt";
            } else if (versionChoice == 3) {
                filePath = "base.integrations.plugins.dsl/lprog/files input/sample_DSL_figure_3.txt";
                dslTxt ="sample_DSL_figure_3.txt";
            } else {
                System.out.println("Invalid option.");
                return false;
            }
            controller2.updateDsl(selectedFigure,dslTxt);
            System.out.println("\nInterpreting script from: " + filePath);
            FigurePlugin figurePlugin=new FigurePlugin();
            figurePlugin.interpretFigureScript(filePath);

        } catch (Exception e) {
            System.out.println("⚠ Error: " + e.getMessage());
        }

        return true;
    }

    @Override
    public String headline() {
        return "Interpret Figure DSL Script";
    }
}
