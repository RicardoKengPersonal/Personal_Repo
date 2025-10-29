package eapli.shodrone.customermanagement.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.shodrone.customermanagement.domain.*;
import eapli.shodrone.customermanagement.repository.CustomerRepository;
import eapli.shodrone.usermanagement.domain.Roles;

import java.util.Collections;
import java.util.Set;

public class CreateCustomerController {

    private final AuthorizationService authz;
    private final CustomerService service;

    public CreateCustomerController(final AuthorizationService authz, final CustomerRepository repo) {
        this.authz = authz;
        this.service = new CustomerService(repo);
    }


    public void addCustomerWithRepresentative(final VatNumber vat, final String company,
                                              final CustomerStatus status, final CustomerPriority priority,
                                              final Address address, final CustomerRepresentative representative) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN, Roles.CRM_COLLABORATOR);
        service.registerCustomer(vat, company, status, priority, address, Collections.singleton(representative));
    }

    public Iterable<Customer> allCustomers() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN, Roles.CRM_COLLABORATOR);
        return service.allCustomers();
    }
}
