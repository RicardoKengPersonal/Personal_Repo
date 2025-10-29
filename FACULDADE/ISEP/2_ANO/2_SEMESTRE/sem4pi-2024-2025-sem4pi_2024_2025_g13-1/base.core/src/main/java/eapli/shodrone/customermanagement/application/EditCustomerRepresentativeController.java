package eapli.shodrone.customermanagement.application;

import eapli.shodrone.customermanagement.domain.Customer;
import eapli.shodrone.customermanagement.domain.CustomerRepresentative;

import java.util.Optional;

public class EditCustomerRepresentativeController {

    private final CustomerRepresentativeService service = new CustomerRepresentativeService();

    public Iterable<Customer> allCustomers() {
        return service.allCustomers();
    }

    public boolean editRepresentative(Customer customer, CustomerRepresentative rep,
                                      String newFirstName, String newLastName,
                                      String newPhoneNumber, String newPosition) {
        return service.editRepresentative(customer, rep, newFirstName, newLastName, newPhoneNumber, newPosition);
    }

    public void saveCustomer(Customer customer) {
        service.save(customer);
    }

}
