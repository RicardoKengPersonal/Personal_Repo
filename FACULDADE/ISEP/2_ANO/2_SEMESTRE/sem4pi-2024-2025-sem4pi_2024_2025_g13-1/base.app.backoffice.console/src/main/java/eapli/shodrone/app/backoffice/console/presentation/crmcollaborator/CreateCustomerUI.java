package eapli.shodrone.app.backoffice.console.presentation.crmcollaborator;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.shodrone.customermanagement.application.CreateCustomerController;
import eapli.shodrone.customermanagement.domain.*;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.shodroneusermanagement.domain.PhoneNumber;
import eapli.shodrone.shodroneusermanagement.domain.ShodroneUser;
import eapli.shodrone.usermanagement.application.AddUserController;
import eapli.shodrone.usermanagement.domain.Roles;
import eapli.shodrone.usermanagement.domain.ShodronePasswordPolicy;

import java.util.Calendar;
import java.util.Set;

public class CreateCustomerUI extends AbstractUI {

    private final CreateCustomerController controller =
            new CreateCustomerController(AuthzRegistry.authorizationService(),
                    PersistenceContext.repositories().customerRepository());

    @Override
    protected boolean doShow() {
        try {
            VatNumber vat = askVatNumber();
            String company = askCompanyName();
            CustomerStatus status = CustomerStatus.CREATED;
            if (status == null) return false;

            CustomerPriority priority = askPriority();
            if (priority == null) return false;

            Address address = askAddress();
            if (address == null) return false;

            // --- Dados do representante ---
            final String firstName = Console.readLine("Representative First Name");
            final String lastName = Console.readLine("Representative Last Name");
            final String emailStr = Console.readLine("Representative Email");
            final EmailAddress email = EmailAddress.valueOf(emailStr);
            final String phone = Console.readLine("Phone Number");
            final String position = Console.readLine("Representative Position");
            final String password = Console.readLine("Representative Password");

            AddUserController addUserController = new AddUserController();

            final Set<Role> roles = Set.of(Roles.CUSTOMER_REPRESENTATIVE);

            // Validate password before creating the user
            ShodronePasswordPolicy passwordPolicy = new ShodronePasswordPolicy();
            if (!passwordPolicy.isSatisfiedBy(password)) {
                System.out.println("Error: Password must be at least 6 characters long, contain at least one digit and one uppercase letter.");
                return false;
            }

            SystemUser systemUser;
            try {
                systemUser = addUserController.addUser(
                        emailStr, password, firstName, lastName, emailStr, roles, Calendar.getInstance());
            } catch (IllegalArgumentException e) {
                System.out.println("Error while creating user: " + e.getMessage());
                return false;
            }

            CustomerRepresentative representative = new CustomerRepresentative(position, firstName, lastName, email, phone);
            representative.assignUser(systemUser);

            controller.addCustomerWithRepresentative(vat, company, status, priority, address, representative);

            final var phoneNumber = new PhoneNumber(phone);
            final var shodroneUser = new ShodroneUser(systemUser, phoneNumber);
            PersistenceContext.repositories().shodroneUsers().save(shodroneUser);

            System.out.println("Customer and Representative registered successfully.");
            return true;

        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private VatNumber askVatNumber() {
        while (true) {
            final String input = Console.readLine("VAT Number (EU format: PT123456789)");
            if (input.matches("[A-Z]{2}\\d{9}")) {
                try {
                    return new VatNumber(input);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid VAT number: " + e.getMessage());
                }
            } else {
                System.out.println("Invalid format. Example: PT123456789");
            }
        }
    }

    private String askCompanyName() {
        return Console.readLine("Company Name");
    }

    private CustomerPriority askPriority() {
        System.out.println("Priority options: REGULAR, VIP");
        String priorityStr = Console.readLine("Customer Priority");
        try {
            return CustomerPriority.valueOf(priorityStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid priority. Please use one of: REGULAR, VIP.");
            return null;
        }
    }

    private Address askAddress() {
        try {
            final String street = Console.readLine("Street");
            final String city = Console.readLine("City");
            Address.validateCity(city);
            final String zipCode = Console.readLine("ZIP Code");

            return new Address(street, city, zipCode);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid address: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String headline() {
        return "Register Customer and First Representative";
    }
}
