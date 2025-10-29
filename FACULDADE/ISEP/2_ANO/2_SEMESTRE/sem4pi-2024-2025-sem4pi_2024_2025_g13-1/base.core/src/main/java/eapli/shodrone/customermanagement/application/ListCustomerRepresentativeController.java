package eapli.shodrone.customermanagement.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.shodrone.customermanagement.domain.Customer;
import eapli.shodrone.customermanagement.domain.CustomerRepresentative;
import eapli.shodrone.customermanagement.domain.VatNumber;
import eapli.shodrone.customermanagement.repository.CustomerRepository;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.usermanagement.domain.Roles;

public class ListCustomerRepresentativeController {

    private final CustomerService customerService = new CustomerService(PersistenceContext.repositories().customerRepository());
    private final CustomerRepresentativeService representativeService = new CustomerRepresentativeService();
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    public Iterable<Customer> allCreatedCustomers() {
        authorizationService.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN, Roles.CRM_COLLABORATOR);
        return representativeService.allCreatedCustomers();
    }

    public Iterable<CustomerRepresentative> listEnabledRepresentatives(VatNumber vatNumber) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN, Roles.CRM_COLLABORATOR);
        return representativeService.listEnableRepresentatives(vatNumber);
    }
}
