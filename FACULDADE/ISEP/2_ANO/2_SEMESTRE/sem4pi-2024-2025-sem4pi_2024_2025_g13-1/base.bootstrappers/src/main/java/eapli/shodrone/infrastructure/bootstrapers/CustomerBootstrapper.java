package eapli.shodrone.infrastructure.bootstrapers;

import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.strings.util.Strings;
import eapli.framework.validations.Invariants;
import eapli.shodrone.customermanagement.application.CreateCustomerController;
import eapli.shodrone.customermanagement.domain.*;
import eapli.shodrone.customermanagement.repository.CustomerRepository;
import eapli.shodrone.infrastructure.bootstrapers.demo.BackofficeUsersBootstrapper;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.usermanagement.domain.Roles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;
import java.util.Optional;
import java.util.Set;

public class CustomerBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(CustomerBootstrapper.class);

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AuthenticationService authenticationService = AuthzRegistry.authenticationService();
    private final UserManagementService userManagementService = AuthzRegistry.userService();
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customerRepository();

    private final CreateCustomerController controller = new CreateCustomerController(authz, customerRepository);

    @Override
    public boolean execute() {
        final Action[] actions = { new BackofficeUsersBootstrapper() };
        authenticateForBootstrapping();

        registerCustomerWithRepresentative("PT123456789", "Shodrone", "Main Street", "Porto", "4000-001");

        return true;
    }

    private boolean registerCustomerWithRepresentative(String vatStr, String company,
                                                       String street, String city, String zipCode) {
        try {
            final VatNumber vat = new VatNumber(vatStr);
            final Address address = new Address(street, city, zipCode);

            // Representative data
            final String firstName = "Rodrigo";
            final String lastName = "Silva";
            final String emailStr = "rodrigo.silva@shodrone.pt";
            final EmailAddress email = EmailAddress.valueOf(emailStr);
            final String phone = "913531588";
            final String position = "Manager";
            final String password = "Password1";

            // Create SystemUser with CUSTOMER_REPRESENTATIVE role
            final Set<Role> roles = Set.of(Roles.CUSTOMER_REPRESENTATIVE);
            final Calendar createdOn = Calendar.getInstance();

            final SystemUser user = userManagementService.registerNewUser(
                    emailStr, password, firstName, lastName, emailStr, roles, createdOn
            );

            // Create representative
            final CustomerRepresentative representative =
                    new CustomerRepresentative(position, firstName, lastName, email, phone);
            representative.assignUser(user);

            // Register customer
            controller.addCustomerWithRepresentative(vat, company, CustomerStatus.CREATED,
                    CustomerPriority.REGULAR, address, representative);

            LOGGER.info("Customer '{}' with representative '{}' created successfully", company, emailStr);
            return true;

        } catch (ConcurrencyException | IntegrityViolationException e) {
            LOGGER.warn("Customer or user may already exist. Details: {}", e.getMessage());
            LOGGER.trace("Exception details", e);
            return false;
        } catch (Exception e) {
            LOGGER.error("Error registering customer '{}': {}", company, e.getMessage());
            LOGGER.debug("Full error", e);
            return false;
        }
    }

    protected void authenticateForBootstrapping() {
        authenticationService.authenticate("admin", "Password1");
        Invariants.ensure(authz.hasSession());
    }

    private String nameOfEntity(final Action boot) {
        final var name = boot.getClass().getSimpleName();
        return Strings.left(name, name.length() - "Bootstrapper".length());
    }
}
