package eapli.shodrone.customermanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "CUSTOMER")
public class Customer implements AggregateRoot<CustomerID> {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CustomerID id;

    @Embedded
    @AttributeOverride(name = "number", column = @Column(name = "vat_number", nullable = false, unique = true))
    private VatNumber vatNumber;

    @Column(nullable = false)
    private String companyName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CustomerStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CustomerPriority priority;

    @Embedded
    @AttributeOverride(name = "address", column = @Column(name = "customer_address", nullable = false))
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "customer")
    private final List<CustomerRepresentative> representatives = new ArrayList<>();

    protected Customer() {
        // for ORM only
    }

    public Customer(CustomerID id, VatNumber vatNumber, String companyName,
                    CustomerStatus status, CustomerPriority priority, Address address) {
        Preconditions.noneNull(id, vatNumber, companyName, status, priority, address);
        this.id = id;
        this.vatNumber = vatNumber;
        this.companyName = companyName;
        this.status = status;
        this.priority = priority;
        this.address = address;
    }

    public void addRepresentative(CustomerRepresentative representative) {
        Preconditions.nonNull(representative);
        representative.setCustomer(this);
        representatives.add(representative);
    }

    public void removeRepresentative(CustomerRepresentative representative) {
        Preconditions.nonNull(representative);
        if (representatives.size() <= 1) {
            throw new IllegalStateException("A customer must have at least one representative.");
        }
        representatives.remove(representative);
    }

    public void changeStatus(CustomerStatus newStatus) {
        Preconditions.nonNull(newStatus);
        this.status = newStatus;
    }

    public void changePriority(CustomerPriority newPriority) {
        Preconditions.nonNull(newPriority);
        this.priority = newPriority;
    }

    // Getters
    public CustomerID id() {
        return id;
    }

    public VatNumber vatNumber() {
        return vatNumber;
    }

    public String companyName() {
        return companyName;
    }

    public CustomerStatus status() {
        return status;
    }

    public CustomerPriority priority() {
        return priority;
    }

    public Address address() {
        return address;
    }

    public Set<CustomerRepresentative> representatives() {
        return Set.copyOf(representatives);
    }

    // AggregateRoot
    @Override
    public CustomerID identity() {
        return id;
    }

    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (!(other instanceof Customer)) return false;
        Customer that = (Customer) other;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public boolean equals(Object o) {
        return sameAs(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Customer: id=%s \n vatNumber=%s | companyName='%s' | status=%s | priority=%s | address=%s | representatives=%d |",
                id, vatNumber, companyName, status, priority, address, representatives.size());
    }

}
