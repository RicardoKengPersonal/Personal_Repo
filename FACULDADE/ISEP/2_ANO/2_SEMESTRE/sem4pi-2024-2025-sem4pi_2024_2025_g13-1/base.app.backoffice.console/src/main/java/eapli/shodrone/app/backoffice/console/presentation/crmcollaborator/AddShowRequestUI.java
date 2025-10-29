package eapli.shodrone.app.backoffice.console.presentation.crmcollaborator;

import eapli.framework.io.util.Console;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.presentation.console.AbstractUI;
import eapli.shodrone.customermanagement.domain.Customer;
import eapli.shodrone.customermanagement.domain.CustomerRepresentative;
import eapli.shodrone.figuremanagement.domain.Figure;
import eapli.shodrone.figuremanagement.domain.FigureVersion;
import eapli.shodrone.figuremanagement.domain.FigureVersionID;
import eapli.shodrone.showrequestmanagement.application.AddShowRequestController;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.usermanagement.domain.Roles;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class AddShowRequestUI extends AbstractUI {

    private final AddShowRequestController controller = new AddShowRequestController(
            AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().customerRepository(),
            PersistenceContext.repositories().figure(),
            PersistenceContext.repositories().showRequestRepository(),
            PersistenceContext.repositories().users());

    @Override
    protected boolean doShow() {
        try {
            // Escolher Customer
            List<Customer> customers = new ArrayList<>();
            int i = 1;
            for (Customer c : controller.listCustomers()) {
                System.out.printf("%d. %s [%s]%n", i, c.companyName(), c.vatNumber());
                customers.add(c);
                i++;
            }
            if (customers.isEmpty()) {
                System.out.println("⚠ No customers available.");
                return false;
            }

            System.out.println("0. Cancel");
            int option = Console.readOption(1, customers.size(), 0);
            if (option == 0) return false;

            Customer selectedCustomer = customers.get(option - 1);

            // Escolher Representative
            Iterable<CustomerRepresentative> reps = controller.representativesForCustomer(selectedCustomer.vatNumber());
            if (!reps.iterator().hasNext()) {
                System.out.println("⚠ No active representatives for this customer.");
                return false;
            }

            Map<Integer, CustomerRepresentative> repMap = new HashMap<>();
            i = 1;
            for (CustomerRepresentative rep : reps) {
                System.out.printf("%d. %s %s (%s)%n", i, rep.firstName(), rep.lastName(), rep.email());
                repMap.put(i, rep);
                i++;
            }

            System.out.println("0. Cancel");
            int options = Console.readOption(1, repMap.size(), 0);
            if (options == 0) return false;

            CustomerRepresentative selectedRep = repMap.get(options);


            // Address input
            final String street = Console.readLine("Street:");
            final String city = Console.readLine("City:");
            final String zip = Console.readLine("Zip Code:");

            // Duração
            final int duration = Console.readInteger("Enter duration (minutes):");

            // Descrição
            final String descriptionInput = Console.readLine("Enter show description:");

            // Data e Hora do Evento
            final String eventDateStr = Console.readLine("Data do evento (yyyy-MM-dd):");
            final String eventTimeStr = Console.readLine("Hora do evento (HH:mm):");

            final LocalDate eventDate = LocalDate.parse(eventDateStr);
            final LocalTime eventTime = LocalTime.parse(eventTimeStr);


            // Drones
            final int drones = Console.readInteger("Number of drones:");

            // Figures
            System.out.println("\n--- Public Figures Catalogue ---");

            Iterable<Figure> figures = controller.listPublicFigures();
            Set<FigureVersionID> selectedFigureIds = new HashSet<>();
            String descriptionNote = "";
            boolean foundAny = false;

            for (Figure fig : figures) {
                if (fig.isPublic() && fig.versions() != null && fig.versions().iterator().hasNext()) {
                    for (FigureVersion version : fig.versions()) {
                        System.out.printf(
                                "Figure ID: %s | Name: %s | Desc: %s | Version: %s | Designer: %s | DSL: %s%n",
                                version.getId(),
                                fig.figureName(),
                                fig.description(),
                                version.version(),
                                version.designer().identity(),
                                version.dsl().toString()
                        );
                        foundAny = true;
                    }
                }
            }

            if (!foundAny) {
                System.out.println("⚠ No public figures available.");
                descriptionNote = Console.readLine("Describe what you would like to be designed:");
            } else {
                String choose = Console.readLine("Do you want to select any? (Y/N):");
                if (choose.trim().equalsIgnoreCase("Y")) {
                    String input = Console.readLine("Enter Figure Version IDs to associate (comma-separated):");
                    for (String s : input.split(",")) {
                        selectedFigureIds.add(FigureVersionID.valueOf(Integer.parseInt(s.trim())));
                    }
                } else {
                    descriptionNote = Console.readLine("Describe what you would like to be designed:");
                }
            }

            System.out.println("\n");
            System.out.println("Choose a Show Designer");
            // Designer
            Iterable<SystemUser> allUsers = controller.findAllUsers();
            List<SystemUser> designers = new ArrayList<>();

            for (SystemUser user : allUsers) {
                if (user.roleTypes().contains(Roles.SHOW_DESIGNER)) {
                    designers.add(user);
                }
            }

            if (designers.isEmpty()) {
                System.out.println("⚠ No Show Designers found.");
                return false;
            }

            int y = 1;
            for (SystemUser d : designers) {
                System.out.printf("%d. %s%n", y, d.name());
                y++;
            }

            System.out.println("0. Cancel");
            int opt = Console.readOption(1, designers.size(), 0);
            if (opt == 0) return false;

            SystemUser selectedDesigner = designers.get(opt - 1);


            // Utilizador autenticado
            SystemUser creator = AuthzRegistry.authorizationService().session().get().authenticatedUser();

            // Descrição final
            String fullDescription = descriptionInput;
            if (!descriptionNote.isBlank()) {
                fullDescription += "\n[Figure Request Note]: " + descriptionNote;
            }

            controller.addShowRequest(
                    selectedCustomer.vatNumber(),
                    selectedRep,
                    creator,
                    street, city, zip,
                    eventDate, eventTime,
                    duration,
                    fullDescription,
                    drones,
                    selectedFigureIds,
                    selectedDesigner
            );


            System.out.println("✅ Show Request created successfully.");
            return true;

        } catch (Exception e) {
            System.out.println("⚠ Error: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public String headline() {
        return "Make a Show Request";
    }
}
