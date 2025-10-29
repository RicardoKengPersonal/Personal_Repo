package eapli.shodrone.customermanagement.domain;

import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "CUSTOMER_REPRESENTATIVE")
public class CustomerRepresentative implements Comparable<CustomerRepresentative> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Embedded
    @AttributeOverride(name = "email", column = @Column(name = "email", nullable = false, unique = true))
    private EmailAddress email;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String position;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RepresentativeStatus status;

    @OneToOne(optional = false)
    private SystemUser systemUser;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    protected CustomerRepresentative() {
        // for ORM
    }

    public CustomerRepresentative(final String position, final String firstName, final String lastName,
                                  final EmailAddress email, final String phoneNumber) {
        Preconditions.noneNull(position, firstName, lastName, email, phoneNumber);
        this.position = position;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = RepresentativeStatus.ENABLE;
    }

    public void assignUser(final SystemUser systemUser) {
        Preconditions.nonNull(systemUser);
        this.systemUser = systemUser;
    }

    public void setCustomer(final Customer customer) {
        Preconditions.nonNull(customer);
        this.customer = customer;
    }

    public boolean isDisabled() {
        return this.status == RepresentativeStatus.DISABLE;
    }

    public void disable() {
        this.status = RepresentativeStatus.DISABLE;
    }

    public void enable() {
        this.status = RepresentativeStatus.ENABLE;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public EmailAddress email() {
        return email;
    }

    public String phoneNumber() {
        return phoneNumber;
    }

    public String position() {
        return position;
    }

    public RepresentativeStatus status() {
        return status;
    }

    public SystemUser systemUser() {
        return systemUser;
    }

    public Customer customer() {
        return customer;
    }

    public void updateFirstName(String newFirstName) {
        Preconditions.nonEmpty(newFirstName, "First name cannot be empty.");
        this.firstName = newFirstName;
    }

    public void updateLastName(String newLastName) {
        Preconditions.nonEmpty(newLastName, "Last name cannot be empty.");
        this.lastName = newLastName;
    }

    public void updatePhoneNumber(String newPhoneNumber) {
        Preconditions.nonEmpty(newPhoneNumber, "Phone number cannot be empty.");
        this.phoneNumber = newPhoneNumber;
    }

    public void updatePosition(String newPosition) {
        Preconditions.nonEmpty(newPosition, "Position cannot be empty.");
        this.position = newPosition;
    }

    @Override
    public int compareTo(final CustomerRepresentative o) {
        return this.email.toString().compareTo(o.email.toString());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerRepresentative)) return false;
        final CustomerRepresentative that = (CustomerRepresentative) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "CustomerRepresentative{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email=" + email +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", position='" + position + '\'' +
                ", status=" + status +
                '}';
    }
}
