package eapli.shodrone.customermanagement.domain;

import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.shodrone.usermanagement.domain.Roles;
import eapli.shodrone.usermanagement.domain.UserBuilderHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private VatNumber vatNumber;
    private String companyName;
    private CustomerStatus status;
    private CustomerPriority priority;
    private CustomerID customerID;
    private Address address;

    private SystemUser user1;
    private SystemUser user2;
    private CustomerRepresentative rep1;
    private CustomerRepresentative rep2;

    @BeforeEach
    void setUp() {
        vatNumber = new VatNumber("PT123456789");
        companyName = "TestCompany";
        status = CustomerStatus.CREATED;
        priority = CustomerPriority.REGULAR;
        customerID = new CustomerID(1);
        address = new Address("Rua Teste", "Porto", "4000-000");

        user1 = UserBuilderHelper.builder()
                .withUsername("rep1")
                .withPassword("Password1")
                .withName("Rep", "One")
                .withEmail("rep1@shodrone.com")
                .withRoles(Roles.CUSTOMER_REPRESENTATIVE)
                .build();

        user2 = UserBuilderHelper.builder()
                .withUsername("rep2")
                .withPassword("Password1")
                .withName("Rep", "Two")
                .withEmail("rep2@shodrone.com")
                .withRoles(Roles.CUSTOMER_REPRESENTATIVE)
                .build();

        rep1 = new CustomerRepresentative(
                "Gestor de Conta",
                "Rep",
                "One",
                EmailAddress.valueOf("rep1@shodrone.com"),
                "910000000"
        );
        rep1.assignUser(user1);

        rep2 = new CustomerRepresentative(
                "Gestor de Conta",
                "Rep",
                "Two",
                EmailAddress.valueOf("rep2@shodrone.com"),
                "910000001"
        );
        rep2.assignUser(user2);

    }

    @Test
    void ensureCustomerIsCreatedSuccessfully() {
        Customer customer = new Customer(customerID, vatNumber, companyName, status, priority, address);
        customer.addRepresentative(rep1);

        assertEquals(vatNumber, customer.vatNumber());
        assertEquals(companyName, customer.companyName());
        assertEquals(status, customer.status());
        assertEquals(priority, customer.priority());
        assertTrue(customer.representatives().contains(rep1));
    }

    @Test
    void ensureStatusCanBeChanged() {
        Customer customer = new Customer(customerID, vatNumber, companyName, status, priority, address);
        customer.addRepresentative(rep1);

        customer.changeStatus(CustomerStatus.INFRINGEMENT);
        assertEquals(CustomerStatus.INFRINGEMENT, customer.status());
    }

    @Test
    void ensurePriorityCanBeChanged() {
        Customer customer = new Customer(customerID, vatNumber, companyName, status, priority, address);
        customer.addRepresentative(rep1);

        customer.changePriority(CustomerPriority.VIP);
        assertEquals(CustomerPriority.VIP, customer.priority());
    }

    @Test
    void ensureRepresentativeCanBeAdded() {
        Customer customer = new Customer(customerID, vatNumber, companyName, status, priority, address);
        customer.addRepresentative(rep1);
        customer.addRepresentative(rep2);

        assertTrue(customer.representatives().contains(rep2));
        assertEquals(2, customer.representatives().size());
    }

    @Test
    void ensureRepresentativeCannotBeRemovedIfOnlyOneExists() {
        Customer customer = new Customer(customerID, vatNumber, companyName, status, priority, address);
        customer.addRepresentative(rep1);

        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            customer.removeRepresentative(rep1);
        });

        assertTrue(ex.getMessage().toLowerCase().contains("at least one representative"));
    }

    @Test
    void ensureRepresentativeCanBeRemovedIfMultipleExist() {
        Customer customer = new Customer(customerID, vatNumber, companyName, status, priority, address);
        customer.addRepresentative(rep1);
        customer.addRepresentative(rep2);

        customer.removeRepresentative(rep1);

        assertFalse(customer.representatives().contains(rep1));
        assertEquals(1, customer.representatives().size());
    }

    @Test
    void testCustomerEquality() {
        Customer c1 = new Customer(customerID, vatNumber, companyName, status, priority, address);
        Customer c2 = new Customer(customerID, vatNumber, companyName, status, priority, address);

        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }
}
