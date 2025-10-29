package eapli.shodrone.customermanagement.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.time.util.CurrentTimeCalendars;
import eapli.shodrone.customermanagement.domain.Customer;
import eapli.shodrone.customermanagement.domain.CustomerRepresentative;
import eapli.shodrone.customermanagement.repository.CustomerRepository;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.usermanagement.domain.Roles;
import jakarta.transaction.Transactional;

import java.util.Calendar;
import java.util.Set;

@UseCaseController
public class AddCustomerRepresentativeController {

    private final CustomerRepository customerRepository = PersistenceContext.repositories().customerRepository();
    private final UserManagementService userSvc = AuthzRegistry.userService();

    public Iterable<Customer> allCustomers() {
        return customerRepository.findAll();
    }

    @Transactional
    public void addRepresentativeToCustomer(final Customer customer, final CustomerRepresentative representative, final String password) {
        SystemUser user = registerUserToRepresentative(representative, password);
        representative.assignUser(user);

        customer.addRepresentative(representative);
        customerRepository.save(customer);
    }

    private SystemUser registerUserToRepresentative(final CustomerRepresentative rep, final String password) {
        final String username = rep.email().toString();
        final Set<Role> roles = Set.of(Roles.CUSTOMER_REPRESENTATIVE);
        final Calendar createdOn = CurrentTimeCalendars.now();

        return userSvc.registerNewUser(
                username,
                password,
                rep.firstName(),
                rep.lastName(),
                rep.email().toString(),
                roles,
                createdOn
        );
    }
}
