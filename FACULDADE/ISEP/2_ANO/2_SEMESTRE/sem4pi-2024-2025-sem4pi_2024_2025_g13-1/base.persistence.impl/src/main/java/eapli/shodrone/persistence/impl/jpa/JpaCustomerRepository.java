package eapli.shodrone.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.shodrone.Application;
import eapli.shodrone.customermanagement.domain.*;
import eapli.shodrone.customermanagement.repository.CustomerRepository;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class JpaCustomerRepository extends JpaAutoTxRepository<Customer, Long, Long>
        implements CustomerRepository {

    public JpaCustomerRepository(final String puname) {
        super(puname, Application.settings().extendedPersistenceProperties(), "username");
    }

    public JpaCustomerRepository(final TransactionalContext autoTx) {
        super(autoTx.toString(), Application.settings().extendedPersistenceProperties(), "username");
    }

    @Override
    public Optional<Customer> findByNameIgnoreCase(String name) {
        final TypedQuery<Customer> query = createQuery(
                "SELECT fc FROM Customer fc WHERE LOWER(fc.name) = LOWER(:name)", Customer.class);
        query.setParameter("name", name);
        return query.getResultStream().findFirst();
    }

    @Override
    public Optional<Customer> findByVATNumber(VatNumber vatNumber) {
        final TypedQuery<Customer> query = createQuery(
                "SELECT c FROM Customer c WHERE c.vatNumber = :vatNumber", Customer.class);
        query.setParameter("vatNumber", vatNumber);
        return query.getResultStream().findFirst();
    }

    @Override
    public Iterable<Customer> findCreatedCustomers() {
        final TypedQuery<Customer> query = entityManager().createQuery(
                "SELECT c FROM Customer c WHERE c.status = :status", Customer.class);
        query.setParameter("status", CustomerStatus.CREATED);

        return query.getResultList();
    }

    @Override
    public Iterable<CustomerRepresentative> findEnableRepresentatives(VatNumber vatNumber) {
        final TypedQuery<CustomerRepresentative> query = entityManager().createQuery(
                "SELECT cr FROM Customer c JOIN c.representatives cr " +
                        "WHERE cr.status = :status AND c.vatNumber = :vat",
                CustomerRepresentative.class
        );
        query.setParameter("status", RepresentativeStatus.ENABLE);
        query.setParameter("vat", vatNumber);

        return query.getResultList();
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
        final TypedQuery<Integer> query = createQuery(
                "SELECT MAX(c.id.id) FROM Customer c", Integer.class
        );
        return query.getSingleResult();
    }
}

