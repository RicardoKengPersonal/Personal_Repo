package eapli.shodrone.customermanagement.application;

import eapli.shodrone.customermanagement.domain.Customer;
import eapli.shodrone.customermanagement.domain.CustomerRepresentative;
import eapli.shodrone.customermanagement.domain.VatNumber;

import java.util.Optional;

public class DisableCustomerRepresentativeController {

    private final CustomerRepresentativeService service = new CustomerRepresentativeService();

    public Iterable<Customer> allCustomers() {
        return service.allCustomers();
    }

    public boolean disableRepresentative(Customer customer, CustomerRepresentative representative) {
        return service.disableRepresentative(customer, representative);
    }
}