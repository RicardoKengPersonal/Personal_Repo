package eapli.shodrone.app.backoffice.console.presentation.crmcollaborator;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.shodrone.figuremanagement.domain.Figure;
import eapli.shodrone.figuremanagement.domain.FigureVersion;
import eapli.shodrone.figuremanagement.domain.FigureVersionID;
import eapli.shodrone.figuremanagement.repository.FigureRepository;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.showrequestmanagement.application.ListShowRequestController;
import eapli.shodrone.showrequestmanagement.domain.ShowRequest;
import eapli.shodrone.showrequestmanagement.domain.ShowRequestStatus;

import java.util.HashMap;
import java.util.Map;

public class ListShowRequestUI extends AbstractUI {

    private final ListShowRequestController controller = new ListShowRequestController(
            PersistenceContext.repositories().showRequestRepository()
    );

    private final FigureRepository figureRepo = PersistenceContext.repositories().figure();

    @Override
    protected boolean doShow() {
        try {
            System.out.println("1. List all show requests");
            System.out.println("2. List show requests by status");

            int option;
            do {
                option = Console.readInteger("Choose an option:");
            } while (option != 1 && option != 2);

            Iterable<ShowRequest> showRequests;

            if (option == 1) {
                showRequests = controller.findAll();
            } else {
                System.out.println("\nAvailable statuses:");
                for (ShowRequestStatus status : ShowRequestStatus.values()) {
                    System.out.println(" - " + status.name());
                }

                final String statusInput = Console.readLine("Enter status: ");
                ShowRequestStatus status = ShowRequestStatus.valueOf(statusInput.trim().toUpperCase());
                showRequests = controller.findByStatus(status);
            }

            // construir mapa FigureVersionID → FigureVersion
            Map<FigureVersionID, FigureVersion> versionMap = new HashMap<>();
            for (Figure fig : figureRepo.findAll()) {
                for (FigureVersion v : fig.versions()) {
                    versionMap.put(v.getId(), v);
                }
            }

            System.out.println("\n=== Show Requests ===");
            boolean any = false;

            for (ShowRequest request : showRequests) {
                System.out.printf(
                        "ID: %s | Status: %s | Customer: %s | Representative: %s %s | Date: %s | Drones: %d%n",
                        request.identity(),
                        request.status(),
                        request.customer().companyName(),
                        request.representative().firstName(),
                        request.representative().lastName(),
                        request.submissionDate(),
                        request.numDrones()
                );

                if (!request.figureVersions().isEmpty()) {
                    System.out.println("Figures:");
                    for (FigureVersionID id : request.figureVersions()) {
                        FigureVersion version = versionMap.get(id);
                        if (version != null) {
                            System.out.printf(
                                    "  - Name: %s | Version: %s | Designer: %s | DSL: %s | Desc: %s%n",
                                    version.figure().figureName(),
                                    version.version(),
                                    version.designer().identity(),
                                    version.dsl(),
                                    version.figure().description()
                            );
                        } else {
                            System.out.printf("  • FigureVersion ID %s (not found)%n", id);
                        }
                    }
                } else {
                    System.out.println("Figures: None");
                }

                System.out.println("--------------------------------------------------");
                any = true;
            }

            if (!any) {
                System.out.println("No show requests found for the selected criteria.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("⚠ Error retrieving show requests ⚠ : " + e.getMessage());
        }

        return true;
    }

    @Override
    public String headline() {
        return "List Show Requests";
    }
}
