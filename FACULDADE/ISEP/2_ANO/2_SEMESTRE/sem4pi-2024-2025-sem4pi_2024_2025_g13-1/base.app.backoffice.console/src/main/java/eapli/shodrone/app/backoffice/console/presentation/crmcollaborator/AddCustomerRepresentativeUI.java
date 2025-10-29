package eapli.shodrone.app.backoffice.console.presentation.crmcollaborator;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.shodrone.customermanagement.application.AddCustomerRepresentativeController;
import eapli.shodrone.customermanagement.domain.Customer;
import eapli.shodrone.customermanagement.domain.CustomerRepresentative;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.shodroneusermanagement.domain.PhoneNumber;
import eapli.shodrone.shodroneusermanagement.domain.ShodroneUser;


import java.util.*;

public class AddCustomerRepresentativeUI extends AbstractUI {

    private final AddCustomerRepresentativeController controller = new AddCustomerRepresentativeController();

    @Override
    protected boolean doShow() {
        Iterable<Customer> customers = controller.allCustomers();

        if (!customers.iterator().hasNext()) {
            System.out.println("No customers found. Please create a customer first.");
            return false;
        }

        final Customer selected = selectCustomer(customers);
        if (selected == null) return false;

        // --- Input fields ---
        final String firstName = Console.readLine("Representative First Name");
        final String lastName = Console.readLine("Representative Last Name");

        EmailAddress email = null;
        while (email == null) {
            try {
                final String emailStr = Console.readLine("Representative Email");
                email = EmailAddress.valueOf(emailStr);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid email. Try again.");
            }
        }

        final String phone = Console.readLine("Phone Number");
        final String position = Console.readLine("Representative Position");
        final String password = Console.readLine("Representative Password");

        final CustomerRepresentative rep = new CustomerRepresentative(position, firstName, lastName, email, phone);

        controller.addRepresentativeToCustomer(selected, rep, password);

        final var phoneNumber = new PhoneNumber(phone);
        final var shodroneUser = new ShodroneUser(rep.systemUser(), phoneNumber);
        PersistenceContext.repositories().shodroneUsers().save(shodroneUser);

        System.out.println("Representative successfully added to customer.");
        return true;
    }

    private Customer selectCustomer(Iterable<Customer> customers) {
        System.out.println("Select a customer:");
        List<Customer> list = new ArrayList<>();
        int i = 1;
        for (Customer c : customers) {
            System.out.printf("%d. %s [%s]%n", i, c.companyName(), c.vatNumber().toString());
            list.add(c);
            i++;
        }

        System.out.println("0. Return");
        final int option = Console.readOption(1, list.size(), 0);
        if (option == 0) return null;
        return list.get(option - 1);
    }

    @Override
    public String headline() {
        return "Add Customer Representative";
    }
}
