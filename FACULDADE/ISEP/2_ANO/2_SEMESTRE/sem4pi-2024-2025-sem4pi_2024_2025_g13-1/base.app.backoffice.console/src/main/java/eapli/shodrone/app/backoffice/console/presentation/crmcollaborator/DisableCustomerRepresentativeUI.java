package eapli.shodrone.app.backoffice.console.presentation.crmcollaborator;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.shodrone.customermanagement.application.DisableCustomerRepresentativeController;
import eapli.shodrone.customermanagement.domain.Customer;
import eapli.shodrone.customermanagement.domain.CustomerRepresentative;
import eapli.shodrone.customermanagement.domain.RepresentativeStatus;

import java.util.ArrayList;
import java.util.List;

public class DisableCustomerRepresentativeUI extends AbstractUI {

    private final DisableCustomerRepresentativeController controller = new DisableCustomerRepresentativeController();

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

        List<CustomerRepresentative> representatives = new ArrayList<>();
        for (CustomerRepresentative rep : selectedCustomer.representatives()) {
            if (rep.status() == RepresentativeStatus.ENABLE) {
                representatives.add(rep);
            }
        }

        if (representatives.isEmpty()) {
            System.out.println("This customer has no representatives.");
            return false;
        }

        System.out.println("\n--- Representatives ---");
        for (int i = 0; i < representatives.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, representatives.get(i).toString());
        }

        int repIndex = Console.readInteger("Select a representative to disable: ") - 1;
        if (repIndex < 0 || repIndex >= representatives.size()) {
            System.out.println("Invalid selection.");
            return false;
        }

        CustomerRepresentative rep = representatives.get(repIndex);

        if (rep.isDisabled()) {
            System.out.println("Representative is already disabled.");
            return false;
        }

        if (controller.disableRepresentative(selectedCustomer, rep)) {
            System.out.println("✅ Representative disabled successfully.");
        } else {
            System.out.println("❌ Failed to disable the representative.");
        }

        return true;
    }

    @Override
    public String headline() {
        return "Disable Customer Representative";
    }
}
