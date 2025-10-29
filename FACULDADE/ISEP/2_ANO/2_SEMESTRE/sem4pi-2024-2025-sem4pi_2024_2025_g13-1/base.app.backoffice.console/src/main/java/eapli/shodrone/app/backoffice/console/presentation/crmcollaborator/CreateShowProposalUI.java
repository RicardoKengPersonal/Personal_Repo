package eapli.shodrone.app.backoffice.console.presentation.crmcollaborator;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.showproposalmanagement.DTO.ShowProposalDTO;
import eapli.shodrone.showproposalmanagement.application.CreateShowProposalController;
import eapli.shodrone.showrequestmanagement.application.ListShowRequestController;
import eapli.shodrone.showrequestmanagement.domain.ShowRequest;

import java.util.ArrayList;
import java.util.List;

public class CreateShowProposalUI extends AbstractUI {

    private final CreateShowProposalController controller = new CreateShowProposalController(
            AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().showProposalRepository(),
            PersistenceContext.repositories().showRequestRepository(),
            PersistenceContext.repositories().users());

    private final ListShowRequestController listController = new ListShowRequestController(
            PersistenceContext.repositories().showRequestRepository()
    );

    @Override
    protected boolean doShow() {
        try {
            Iterable<ShowRequest> iterable = listController.findAll();
            List<ShowRequest> requests = new ArrayList<>();
            iterable.forEach(requests::add);

            if (requests.isEmpty()) {
                System.out.println("No show requests available.\n");
                return false;
            }

            System.out.println("\n=== Available Show Requests ===");
            for (int i = 0; i < requests.size(); i++) {
                ShowRequest request = requests.get(i);
                System.out.printf("%d. ID: %s | Customer: %s | Status: %s | Drones: %d%n",
                        i + 1,
                        request.identity(),
                        request.customer().companyName(),
                        request.status(),
                        request.numDrones());
            }

            int selection;
            do {
                selection = Console.readInteger("\nSelect a request by number (1-" + requests.size() + "): ");
            } while (selection < 1 || selection > requests.size());

            ShowRequest selectedRequest = requests.get(selection - 1);
            final ShowProposalDTO dto = new ShowProposalDTO(
                    selectedRequest.identity().toString()
            );

            controller.addShowProposal(dto);
            System.out.println(" Show Proposal created successfully for request " + selectedRequest.identity());

        } catch (IllegalArgumentException e) {
            System.out.println(" Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }

        return true;
    }

    @Override
    public String headline() {
        return "Create Show Proposal";
    }



}
