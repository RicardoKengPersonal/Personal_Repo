package eapli.shodrone.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.shodrone.customermanagement.domain.*;
import eapli.shodrone.customermanagement.repository.CustomerRepository;

import javax.xml.validation.Validator;
import java.util.Optional;
import java.util.stream.StreamSupport;

public class InMemoryCustomerRepository
        extends InMemoryDomainRepository<Customer, CustomerID>
        implements CustomerRepository {

    @Override
    public Optional<Customer> findByNameIgnoreCase(String name) {
        return matchOne(c -> c.companyName().equalsIgnoreCase(name));
    }

    @Override
    public Optional<Customer> findByVATNumber(VatNumber vatNumber) {
        return matchOne(c -> false);
    }

    @Override
    public Iterable<Customer> findCreatedCustomers() {
        return match(c -> c.status() == CustomerStatus.CREATED);
    }

    @Override
    public Iterable<CustomerRepresentative> findEnableRepresentatives(VatNumber vatNumber) {
        return StreamSupport.stream(match(c -> c.vatNumber().equals(vatNumber)).spliterator(), false)
                .flatMap(c -> c.representatives().stream())
                .filter(r -> r.status() == RepresentativeStatus.ENABLE)
                .toList();
    }

    @Override
    public Optional<Customer> ofIdentity(CustomerID id) {
        return Optional.empty();
    }

    @Override
    public void deleteOfIdentity(CustomerID entityId) {

    }

    @Override
    public Integer findMaxId() {
        return StreamSupport.stream(findAll().spliterator(), false)
                .map(c -> c.identity().toInteger())
                .max(Integer::compareTo)
                .orElse(0);
    }
}
