package eapli.shodrone.customermanagement.application;

import eapli.shodrone.customermanagement.domain.*;
import eapli.shodrone.customermanagement.repository.CustomerRepository;
import jakarta.transaction.Transactional;

import java.util.Set;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void registerCustomer(final VatNumber vatNumber, final String companyName,
                                 final CustomerStatus status, final CustomerPriority priority,
                                 final Address address, final Set<CustomerRepresentative> representatives) {

        if (customerRepository.findByVATNumber(vatNumber).isPresent()) {
            throw new IllegalArgumentException("A customer with this VAT number already exists.");
        }

        final CustomerID id = CustomerID.valueOf(nextAvailableId());

        final Customer newCustomer = new Customer(id, vatNumber, companyName, status, priority, address);
        for (CustomerRepresentative rep : representatives) {
            newCustomer.addRepresentative(rep);
        }

        customerRepository.save(newCustomer);
    }

    public Iterable<Customer> allCustomers() {
        return this.customerRepository.findAll();
    }

    public Iterable<Customer> allCreatedCustomers() {
        return customerRepository.findCreatedCustomers();
    }

    private Integer nextAvailableId() {
        Integer maxId = customerRepository.findMaxId();
        return (maxId != null) ? maxId + 1 : 1;
    }
}
