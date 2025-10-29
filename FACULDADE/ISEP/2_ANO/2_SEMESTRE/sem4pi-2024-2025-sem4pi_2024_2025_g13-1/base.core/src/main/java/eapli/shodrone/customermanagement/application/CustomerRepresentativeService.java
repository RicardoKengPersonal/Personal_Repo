package eapli.shodrone.customermanagement.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.shodrone.customermanagement.domain.Customer;
import eapli.shodrone.customermanagement.domain.CustomerRepresentative;
import eapli.shodrone.customermanagement.domain.VatNumber;
import eapli.shodrone.customermanagement.repository.CustomerRepository;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.usermanagement.domain.Roles;

import java.util.Optional;

public class CustomerRepresentativeService {

    private final CustomerRepository customerRepository = PersistenceContext.repositories().customerRepository();

    public Iterable<CustomerRepresentative> listEnableRepresentatives(VatNumber vatNumber) {
        return customerRepository.findEnableRepresentatives(vatNumber);
    }

    public Iterable<Customer> allCreatedCustomers() {return customerRepository.findCreatedCustomers();}

    public Iterable<Customer> allCustomers() {
        return customerRepository.findAll();
    }

    public boolean disableRepresentative(Customer customer, CustomerRepresentative representative) {
        if (representative.isDisabled()) {
            return false;
        }

        representative.disable();
        customerRepository.save(customer);
        return true;
    }


    public boolean editRepresentative(Customer customer, CustomerRepresentative representative,
                                      String newFirstName, String newLastName,
                                      String newPhoneNumber, String newPosition) {
        boolean changed = false;

        if (!newFirstName.isBlank()) {
            representative.updateFirstName(newFirstName);
            changed = true;
        }

        if (!newLastName.isBlank()) {
            representative.updateLastName(newLastName);
            changed = true;
        }

        if (!newPhoneNumber.isBlank()) {
            representative.updatePhoneNumber(newPhoneNumber);
            changed = true;
        }

        if (!newPosition.isBlank()) {
            representative.updatePosition(newPosition);
            changed = true;
        }

        if (changed) {
            customerRepository.save(customer);
        }

        return changed;
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }
}
