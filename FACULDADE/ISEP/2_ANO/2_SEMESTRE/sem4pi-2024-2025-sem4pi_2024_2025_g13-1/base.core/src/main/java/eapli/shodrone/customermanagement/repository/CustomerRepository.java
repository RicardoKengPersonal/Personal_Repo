package eapli.shodrone.customermanagement.repository;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.shodrone.customermanagement.domain.Customer;
import eapli.shodrone.customermanagement.domain.CustomerID;
import eapli.shodrone.customermanagement.domain.CustomerRepresentative;
import eapli.shodrone.customermanagement.domain.VatNumber;

import java.util.Optional;

public interface CustomerRepository extends DomainRepository<CustomerID, Customer> {

    Optional<Customer> findByNameIgnoreCase(String name);

    Optional<Customer> findByVATNumber(VatNumber vatNumber);

    Iterable<Customer> findCreatedCustomers();

    Iterable<CustomerRepresentative> findEnableRepresentatives(VatNumber vatNumber);

    Integer findMaxId();
}
