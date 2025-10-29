package eapli.shodrone.app.backoffice.console.presentation.crmmanager;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.io.util.Console;
import eapli.shodrone.figuremanagement.application.DecommissionFigureController;
import eapli.shodrone.figuremanagement.domain.FigureVersion;
import eapli.shodrone.figuremanagement.domain.FigureVersionID;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;

public class DecommissionFigureUI extends AbstractUI {

    private final DecommissionFigureController controller = new DecommissionFigureController(
            AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().figure(),
            PersistenceContext.repositories().figureCategory());

    @Override
    protected boolean doShow() {
        boolean success = false;

        while (!success) {
            try {
                System.out.println("\n--- Available Figure Versions ---");
                for (FigureVersion version : controller.listAllFigureVersions()) {
                    System.out.println("\n------------------------------");
                    System.out.println("ID:         " + version.getId());
                    System.out.println("Version:    " + version.version());
                    System.out.println("Designer:   " + version.designer().username());
                    System.out.println("Timestamp:  " + version.timestamp());
                    System.out.println("Figure:     " + version.figure().figureName());
                }
                System.out.println("\n------------------------------");


                final int id = Console.readInteger("\nEnter the Figure Version ID to delete:");

                controller.decommissionFigure(new FigureVersionID(id));

                System.out.println("✔ FigureVersion successfully decommissioned.");
                success = true;

            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("⚠ " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }

        return true;
    }


    @Override
    public String headline() {
        return "Decommission Figure";
    }
}