package eapli.shodrone.showmanagement.domain;

import eapli.shodrone.customermanagement.domain.Customer;
import eapli.shodrone.customermanagement.domain.CustomerRepresentative;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.showrequestmanagement.domain.Date;
import eapli.shodrone.showrequestmanagement.domain.ShowRequest;
import eapli.shodrone.showrequestmanagement.domain.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShowTest {

    // Stubs for dependencies
    private ShowProposal stubProposal;
    private ShowRequest stubRequest;
    private Customer stubCustomer;
    private CustomerRepresentative stubRepresentative;

    // Value Objects
    private ShowID showID;
    private Date scheduledDate;
    private Time scheduledTime;

    private static class StubCustomer extends Customer {
        // Empty, just for type compatibility
    }

    private static class StubCustomerRepresentative extends CustomerRepresentative {
        // Empty, just for type compatibility
    }

    private static class StubDate extends Date {
        private final int year, month, day;
        public StubDate(int year, int month, int day) { this.year = year; this.month = month; this.day = day; }
        @Override public String toString() { return String.format("%d-%02d-%02d", year, month, day); }
    }

    private static class StubTime extends Time {
        private final int hour, minute;
        public StubTime(int hour, int minute) { this.hour = hour; this.minute = minute; }
        @Override public String toString() { return String.format("%02d:%02d", hour, minute); }
    }

    private static class StubShowProposal extends ShowProposal {
        // Empty, just for type compatibility
    }

    private static class StubShowRequest extends ShowRequest {
        private final Customer customer;
        private final CustomerRepresentative representative;

        public StubShowRequest(Customer customer, CustomerRepresentative representative) {
            this.customer = customer;
            this.representative = representative;
        }

        @Override
        public Customer customer() {
            return this.customer;
        }

        @Override
        public CustomerRepresentative representative() {
            return this.representative;
        }
    }
    //endregion

    @BeforeEach
    void setUp() {
        // Initialize common test objects using our stubs
        showID = new ShowID(1);
        scheduledDate = new StubDate(2025, 10, 20);
        scheduledTime = new StubTime(21, 30);

        stubCustomer = new StubCustomer();
        stubRepresentative = new StubCustomerRepresentative();
        stubProposal = new StubShowProposal();
        stubRequest = new StubShowRequest(stubCustomer, stubRepresentative);
    }

    @Test
    @DisplayName("Ensure Show is created successfully with valid parameters")
    void ensureShowIsCreatedWithValidParameters() {
        // Act
        Show show = new Show(showID, stubProposal, stubRequest, scheduledDate, scheduledTime);

        // Assert
        assertNotNull(show);
        assertEquals(showID, show.identity());
        assertEquals(stubProposal, show.proposal());
        assertEquals(stubRequest, show.request());
        assertEquals(scheduledDate, show.scheduledDate());
        assertEquals(scheduledTime, show.scheduledTime());
        assertEquals(ShowStatus.SCHEDULED, show.status(), "Initial status should be SCHEDULED");
    }

    @Test
    @DisplayName("Ensure Show creation fails with null ShowID")
    void ensureShowCreationFailsWithNullId() {
        assertThrows(IllegalArgumentException.class, () ->
                new Show(null, stubProposal, stubRequest, scheduledDate, scheduledTime)
        );
    }

    @Test
    @DisplayName("Ensure Show creation fails with null ShowProposal")
    void ensureShowCreationFailsWithNullProposal() {
        assertThrows(IllegalArgumentException.class, () ->
                new Show(showID, null, stubRequest, scheduledDate, scheduledTime)
        );
    }

    @Test
    @DisplayName("Ensure Show creation fails with null ShowRequest")
    void ensureShowCreationFailsWithNullRequest() {
        assertThrows(IllegalArgumentException.class, () ->
                new Show(showID, stubProposal, null, scheduledDate, scheduledTime)
        );
    }

    @Test
    @DisplayName("Ensure Show creation fails with null ScheduledDate")
    void ensureShowCreationFailsWithNullDate() {
        assertThrows(IllegalArgumentException.class, () ->
                new Show(showID, stubProposal, stubRequest, null, scheduledTime)
        );
    }

    @Test
    @DisplayName("Ensure Show creation fails with null ScheduledTime")
    void ensureShowCreationFailsWithNullTime() {
        assertThrows(IllegalArgumentException.class, () ->
                new Show(showID, stubProposal, stubRequest, scheduledDate, null)
        );
    }

    @Test
    @DisplayName("Ensure customer() returns the correct customer from the request")
    void ensureCustomerReturnsCorrectCustomer() {
        // Arrange
        Show show = new Show(showID, stubProposal, stubRequest, scheduledDate, scheduledTime);

        // Act
        Customer customer = show.customer();

        // Assert
        assertEquals(stubCustomer, customer);
    }

    @Test
    @DisplayName("Ensure representative() returns the correct representative from the request")
    void ensureRepresentativeReturnsCorrectRepresentative() {
        // Arrange
        Show show = new Show(showID, stubProposal, stubRequest, scheduledDate, scheduledTime);

        // Act
        CustomerRepresentative representative = show.representative();

        // Assert
        assertEquals(stubRepresentative, representative);
    }

    @Test
    @DisplayName("Ensure markAsCompleted() changes status to COMPLETED")
    void ensureMarkAsCompletedChangesStatus() {
        // Arrange
        Show show = new Show(showID, stubProposal, stubRequest, scheduledDate, scheduledTime);

        // Act
        show.markAsCompleted();

        // Assert
        assertEquals(ShowStatus.COMPLETED, show.status());
    }

    @Test
    @DisplayName("Ensure cancel() changes status to CANCELLED")
    void ensureCancelChangesStatus() {
        // Arrange
        Show show = new Show(showID, stubProposal, stubRequest, scheduledDate, scheduledTime);

        // Act
        show.cancel();

        // Assert
        assertEquals(ShowStatus.CANCELLED, show.status());
    }

    @Test
    @DisplayName("Ensure identity() returns the correct ShowID")
    void ensureIdentityReturnsCorrectId() {
        // Arrange
        Show show = new Show(showID, stubProposal, stubRequest, scheduledDate, scheduledTime);

        // Act & Assert
        assertEquals(showID, show.identity());
    }

    @Test
    @DisplayName("Ensure sameAs() is true for the same instance")
    void ensureSameAsForSameInstance() {
        // Arrange
        Show show = new Show(showID, stubProposal, stubRequest, scheduledDate, scheduledTime);

        // Act & Assert
        assertTrue(show.sameAs(show));
    }

    @Test
    @DisplayName("Ensure sameAs() is true for different instances with the same ID")
    void ensureSameAsForDifferentInstancesWithSameId() {
        // Arrange
        Show show1 = new Show(showID, stubProposal, stubRequest, scheduledDate, scheduledTime);
        Show show2 = new Show(showID, stubProposal, stubRequest, scheduledDate, scheduledTime);

        // Act & Assert
        assertTrue(show1.sameAs(show2));
    }

    @Test
    @DisplayName("Ensure sameAs() is false for different instances with a different ID")
    void ensureSameAsForDifferentId() {
        // Arrange
        ShowID otherId = new ShowID(2);
        Show show1 = new Show(showID, stubProposal, stubRequest, scheduledDate, scheduledTime);
        Show show2 = new Show(otherId, stubProposal, stubRequest, scheduledDate, scheduledTime);

        // Act & Assert
        assertFalse(show1.sameAs(show2));
    }

    @Test
    @DisplayName("Ensure sameAs() is false for different object types")
    void ensureSameAsForDifferentObjectType() {
        // Arrange
        Show show = new Show(showID, stubProposal, stubRequest, scheduledDate, scheduledTime);

        // Act & Assert
        assertFalse(show.sameAs(new Object()));
    }

    @Test
    @DisplayName("Ensure toString() returns the correct string format")
    void ensureToStringIsCorrect() {
        // Arrange
        Show show = new Show(showID, stubProposal, stubRequest, scheduledDate, scheduledTime);
        String expectedString = String.format("Show ID: %s | Date: %s | Time: %s",
                showID.toString(), scheduledDate.toString(), scheduledTime.toString());

        // Act
        String actualString = show.toString();

        // Assert
        assertEquals(expectedString, actualString);
    }
}