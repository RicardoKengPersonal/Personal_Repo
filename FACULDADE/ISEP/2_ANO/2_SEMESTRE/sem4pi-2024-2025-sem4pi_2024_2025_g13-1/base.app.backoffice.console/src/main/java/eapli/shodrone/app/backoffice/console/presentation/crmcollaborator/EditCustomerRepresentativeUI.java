package eapli.shodrone.app.backoffice.console.presentation.crmcollaborator;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.shodrone.customermanagement.application.EditCustomerRepresentativeController;
import eapli.shodrone.customermanagement.domain.Customer;
import eapli.shodrone.customermanagement.domain.CustomerRepresentative;
import eapli.shodrone.customermanagement.domain.RepresentativeStatus;

import java.util.ArrayList;
import java.util.List;

public class EditCustomerRepresentativeUI extends AbstractUI {

    private final EditCustomerRepresentativeController controller = new EditCustomerRepresentativeController();

    @Override
    protected boolean doShow() {
        List<Customer> customers = new ArrayList<>();
        controller.allCustomers().forEach(customers::add);

        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return false;
        }

        System.out.println("\n--- Customers ---");
        for (int i = 0; i < customers.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, customers.get(i).toString());
        }

        int index = Console.readInteger("Select a customer by number: ") - 1;
        if (index < 0 || index >= customers.size()) {
            System.out.println("Invalid selection.");
            return false;
        }

        Customer selectedCustomer = customers.get(index);

        List<CustomerRepresentative> representatives = new ArrayList<>(selectedCustomer.representatives());

        if (representatives.isEmpty()) {
            System.out.println("This customer has no ENABLED representatives.");
            return false;
        }

        System.out.println("\n--- Representatives ---");
        for (int i = 0; i < representatives.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, representatives.get(i).toString());
        }

        int repIndex = Console.readInteger("Select a representative to edit: ") - 1;
        if (repIndex < 0 || repIndex >= representatives.size()) {
            System.out.println("Invalid selection.");
            return false;
        }

        CustomerRepresentative rep = representatives.get(repIndex);

        System.out.println("\n--- Current Representative Data ---");
        System.out.println("First Name: " + rep.firstName());
        System.out.println("Last Name: " + rep.lastName());
        System.out.println("Phone Number: " + rep.phoneNumber());
        System.out.println("Position: " + rep.position());
        System.out.println("Status: " + rep.status());

        boolean changed = false;
        boolean editing = true;

        while (editing) {
            System.out.println("\nSelect field to edit:");
            System.out.println("1. First Name");
            System.out.println("2. Last Name");
            System.out.println("3. Phone Number");
            System.out.println("4. Position");
            System.out.println("5. Status");
            System.out.println("0. Finish Editing");

            int choice = Console.readInteger("Option: ");
            switch (choice) {
                case 1 -> {
                    final String newFirstName = Console.readLine("New First Name: ");
                    if (!newFirstName.isBlank()) {
                        rep.updateFirstName(newFirstName);
                        changed = true;
                    }
                }
                case 2 -> {
                    final String newLastName = Console.readLine("New Last Name: ");
                    if (!newLastName.isBlank()) {
                        rep.updateLastName(newLastName);
                        changed = true;
                    }
                }
                case 3 -> {
                    final String newPhone = Console.readLine("New Phone Number: ");
                    if (!newPhone.isBlank()) {
                        rep.updatePhoneNumber(newPhone);
                        changed = true;
                    }
                }
                case 4 -> {
                    final String newPosition = Console.readLine("New Position: ");
                    if (!newPosition.isBlank()) {
                        rep.updatePosition(newPosition);
                        changed = true;
                    }
                }
                case 5 -> {
                    System.out.println("\nSelect new status:");
                    System.out.println("1. ENABLE");
                    System.out.println("2. DISABLE");
                    int statusChoice = Console.readInteger("Option: ");
                    switch (statusChoice) {
                        case 1 -> {
                            if (rep.status() != RepresentativeStatus.ENABLE) {
                                rep.enable();
                                changed = true;
                            } else {
                                System.out.println("Representative is already ENABLED.");
                            }
                        }
                        case 2 -> {
                            if (rep.status() != RepresentativeStatus.DISABLE) {
                                rep.disable();
                                changed = true;
                            } else {
                                System.out.println("Representative is already DISABLED.");
                            }
                        }
                        default -> System.out.println("Invalid status option.");
                    }
                }
                case 0 -> editing = false;
                default -> System.out.println("Invalid option.");
            }
        }

        if (changed) {
            controller.saveCustomer(selectedCustomer);
            System.out.println("✅ Representative updated successfully.");
        } else {
            System.out.println("No changes were made.");
        }

        return true;
    }

    @Override
    public String headline() {
        return "Edit Customer Representative";
    }
}
