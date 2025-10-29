package eapli.shodrone.app.backoffice.console.presentation.crmcollaborator;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.io.util.Console;
import eapli.shodrone.figuremanagement.domain.Figure;
import eapli.shodrone.figuremanagement.domain.FigureVersion;
import eapli.shodrone.figuremanagement.domain.FigureVersionID;
import eapli.shodrone.figuremanagement.repository.FigureRepository;
import eapli.shodrone.showrequestmanagement.application.EditShowRequestController;
import eapli.shodrone.showrequestmanagement.domain.*;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class EditShowRequestUI extends AbstractUI {

    private final EditShowRequestController controller = new EditShowRequestController(
            PersistenceContext.repositories().showRequestRepository()
    );

    private final FigureRepository figureRepo = PersistenceContext.repositories().figure();

    @Override
    protected boolean doShow() {
        try {
            // Step 1: Mostrar todos os pedidos existentes
            Iterable<ShowRequest> requests = controller.findAll();
            System.out.println("\n=== Existing Show Requests ===");

            boolean any = false;
            for (ShowRequest r : requests) {
                System.out.printf("ID: %s | Status: %s | Adress: %s | Description: %s\n",
                        r.identity(), r.status().name(), r.address(), r.description());
                any = true;
            }

            if (!any) {
                System.out.println("⚠ No show requests found.");
                return false;
            }

            // Step 2: Escolher um pedido por ID
            final String idRaw = Console.readLine("\nEnter the ID of the ShowRequest to edit: ");
            final ShowRequestID idInput = ShowRequestID.valueOf(Integer.parseInt(idRaw));

            final Optional<ShowRequest> optionalRequest = controller.findById(idInput);
            if (optionalRequest.isEmpty()) {
                System.out.println("⚠ No ShowRequest found with that ID.");
                return false;
            }

            final ShowRequest request = optionalRequest.get();

            System.out.println("\nEditing ShowRequest (leave blank to keep current information): " + request.identity());

            // Step 3: Editar status
            System.out.println("Available statuses:");
            for (ShowRequestStatus s : ShowRequestStatus.values()) {
                System.out.println(" - " + s.name());
            }

            final String statusInput = Console.readLine("New Status: ");
            final ShowRequestStatus newStatus = statusInput.isBlank()
                    ? request.status()
                    : parseStatusOrThrow(statusInput.trim());

            // Step 4: Editar descrição
            final String desc = Console.readLine("New Description: ");
            final ShowRequestDescription newDesc = desc.isBlank()
                    ? request.description()
                    : new ShowRequestDescription(desc);

            // Step 5: Editar local
            final String address = Console.readLine("New Address (Street, City, Zip Code): ");
            final Address newAddress = address.isBlank()
                    ? request.address()
                    : parseAddressOrThrow(address);

            // Step 6: Editar duração
            final String durationStr = Console.readLine("New Duration in minutes: ");
            final ShowRequestDuration newDuration = durationStr.isBlank()
                    ? request.duration()
                    : new ShowRequestDuration(Integer.parseInt(durationStr.trim()));

            // Step 7: Editar figuras
            Set<FigureVersionID> updatedFigures = new HashSet<>(request.figureVersions());

            // Mostrar figuras actuais
            System.out.println("\nCurrent Figures:");
            if (updatedFigures.isEmpty()) {
                System.out.println(" - None");
            } else {
                for (FigureVersionID id : updatedFigures) {
                    System.out.println(" - FigureVersion ID: " + id);
                }
            }

            // Perguntar se quer remover
            String removeInput = Console.readLine("Do you want to remove any figure versions? (Y/N): ");
            if (removeInput.equalsIgnoreCase("Y")) {
                String toRemove = Console.readLine("Enter IDs to remove (comma-separated): ");
                for (String s : toRemove.split(",")) {
                    updatedFigures.remove(FigureVersionID.valueOf(Integer.parseInt(s.trim())));
                }
            }

            // Perguntar se quer adicionar
            String addInput = Console.readLine("Do you want to add figure versions? (Y/N): ");
            if (addInput.equalsIgnoreCase("Y")) {
                System.out.println("\nAvailable Figure Versions:");
                for (Figure f : figureRepo.findAll()) {
                    for (FigureVersion v : f.versions()) {
                        System.out.printf("ID: %s | Name: %s | Version: %s | Designer: %s%n",
                                v.getId(), f.figureName(), v.version(), v.designer().identity());
                    }
                }

                String toAdd = Console.readLine("Enter IDs to add (comma-separated): ");
                for (String s : toAdd.split(",")) {
                    updatedFigures.add(FigureVersionID.valueOf(Integer.parseInt(s.trim())));
                }
            }

            controller.updateRequest(idInput, newStatus, newDesc, newAddress, newDuration, updatedFigures);

            System.out.println("✅ ShowRequest updated successfully.");

        } catch (IllegalArgumentException e) {
            System.out.println("⚠ Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error editing ShowRequest: " + e.getMessage());
        }

        return true;
    }

    private ShowRequestStatus parseStatusOrThrow(String input) {
        try {
            return ShowRequestStatus.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status: " + input);
        }
    }

    private Address parseAddressOrThrow(String input) {
        String[] parts = input.split(",", 3);
        if (parts.length < 3) {
            throw new IllegalArgumentException("Address must include Street, City, and Zip Code separated by commas.");
        }
        return new Address(parts[0].trim(), parts[1].trim(), parts[2].trim());
    }

    @Override
    public String headline() {
        return "Edit Show Request";
    }
}
