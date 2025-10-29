package eapli.shodrone.app.backoffice.console.presentation.crmcollaborator;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.shodrone.customermanagement.application.ListCustomerRepresentativeController;
import eapli.shodrone.customermanagement.domain.Customer;
import eapli.shodrone.customermanagement.domain.CustomerRepresentative;

import java.util.ArrayList;
import java.util.List;

public class ListCustomerRepresentativeUI extends AbstractUI {

    private final ListCustomerRepresentativeController controller = new ListCustomerRepresentativeController();

    @Override
    protected boolean doShow() {
        try {
            final Iterable<Customer> customers = controller.allCreatedCustomers();

            List<Customer> customerList = new ArrayList<>();
            int i = 1;
            for (Customer c : customers) {
                System.out.printf("%d. %s [%s]%n", i, c.companyName(), c.vatNumber());
                customerList.add(c);
                i++;
            }

            if (customerList.isEmpty()) {
                System.out.println("No customers available.");
                return false;
            }

            System.out.println("0. Cancel");
            final int option = Console.readOption(1, customerList.size(), 0);
            if (option == 0) {
                System.out.println("Operation cancelled.");
                return false;
            }

            final Customer customer = customerList.get(option - 1);

            System.out.println("\nCustomer VAT Number: " + customer.vatNumber());
            final Iterable<CustomerRepresentative> representatives = controller.listEnabledRepresentatives(customer.vatNumber());

            if (!representatives.iterator().hasNext()) {
                System.out.println("No active representatives found for this customer.");
            } else {
                System.out.println("\nCustomer Representatives:");
                System.out.println(listHeader());
                System.out.println("-----------------------------------------------------------------------------------");
                for (CustomerRepresentative rep : representatives) {
                    printRepresentative(rep);
                }
            }

        } catch (final Exception e) {
            System.out.println("Error listing customer representatives: " + e.getMessage());
            e.printStackTrace(); // useful for debugging
        }

        return false;
    }

    @Override
    public String headline() {
        return "List Customer Representatives";
    }

    private void printRepresentative(CustomerRepresentative rep) {
        System.out.printf("%-30s%-15s%-15s%-15s%-20s%n",
                safeString(rep.email()), safeString(rep.firstName()), safeString(rep.lastName()),
                safeString(rep.phoneNumber()), safeString(rep.position()));
    }

    private String listHeader() {
        return String.format("%-30s%-15s%-15s%-15s%-20s",
                "EMAIL", "F. NAME", "L. NAME", "PHONE", "POSITION");
    }

    private String safeString(Object value) {
        return (value != null) ? value.toString() : "-";
    }
}
