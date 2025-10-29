package eapli.shodrone.showrequestmanagement;

import eapli.shodrone.showrequestmanagement.domain.*;
import eapli.shodrone.customermanagement.domain.Customer;
import eapli.shodrone.customermanagement.domain.CustomerRepresentative;
import eapli.shodrone.figuremanagement.domain.FigureVersionID;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShowRequestTest {

    private ShowRequestID showRequestID;
    private Address address;
    private ShowRequestDuration duration;
    private ShowRequestDescription description;
    private Date submissionDate;
    private Date eventDate;
    private Time eventTime;
    private Customer customer;
    private CustomerRepresentative representative;
    private SystemUser author;
    private SystemUser designer;
    private Set<FigureVersionID> figureVersions;

    @BeforeEach
    void setUp() {
        showRequestID = new ShowRequestID(1);
        address = new Address("Rua das Flores", "Porto", "4050-123");
        duration = new ShowRequestDuration(30);
        description = new ShowRequestDescription("Espectáculo noturno com drones");
        submissionDate = Date.from(LocalDate.now());
        eventDate = Date.from(LocalDate.now().plusDays(5));
        eventTime = Time.from(LocalTime.of(22, 0)); // 22:00
        customer = mock(Customer.class);
        representative = mock(CustomerRepresentative.class);
        author = mock(SystemUser.class);
        designer = mock(SystemUser.class);
        figureVersions = Set.of(FigureVersionID.valueOf(1));
    }

    @Test
    void ensureValidShowRequestIsCreated() {
        ShowRequest request = new ShowRequest(showRequestID, address, duration, description,
                submissionDate, eventDate, eventTime,
                customer, figureVersions, 5, designer, representative, author);

        assertEquals(showRequestID, request.identity());
        assertEquals(ShowRequestStatus.SUBMITTED, request.status());
        assertEquals(address, request.address());
        assertEquals(duration, request.duration());
        assertEquals(description, request.description());
        assertEquals(submissionDate, request.submissionDate());
        assertEquals(eventDate, request.eventDate());
        assertEquals(eventTime, request.eventTime());
        assertEquals(customer, request.customer());
        assertEquals(5, request.numDrones());
        assertEquals(designer, request.assignedDesigner());
        assertEquals(representative, request.representative());
        assertEquals(author, request.author());
        assertFalse(request.hasProposal());
    }

    @Test
    void ensureCannotCreateWithInvalidDroneCount() {
        assertThrows(IllegalArgumentException.class, () ->
                new ShowRequest(showRequestID, address, duration, description,
                        submissionDate, eventDate, eventTime,
                        customer, figureVersions, 0, designer, representative, author));
    }

    @Test
    void ensureCanMarkProposalAsCreated() {
        ShowRequest request = new ShowRequest(showRequestID, address, duration, description,
                submissionDate, eventDate, eventTime,
                customer, figureVersions, 5, designer, representative, author);

        assertFalse(request.hasProposal());
        request.markProposalCreated();
        assertTrue(request.hasProposal());
    }

    @Test
    void ensureCanUpdateStatus() {
        ShowRequest request = new ShowRequest(showRequestID, address, duration, description,
                submissionDate, eventDate, eventTime,
                customer, figureVersions, 5, designer, representative, author);

        request.updateStatus(ShowRequestStatus.SCHEDULED);
        assertEquals(ShowRequestStatus.SCHEDULED, request.status());
    }

    @Test
    void ensureCanUpdateDescription() {
        ShowRequest request = new ShowRequest(showRequestID, address, duration, description,
                submissionDate, eventDate, eventTime,
                customer, figureVersions, 5, designer, representative, author);

        ShowRequestDescription newDescription = new ShowRequestDescription("Novo espectáculo");
        request.updateDescription(newDescription);
        assertEquals(newDescription, request.description());
    }

    @Test
    void ensureCanUpdateAddress() {
        ShowRequest request = new ShowRequest(showRequestID, address, duration, description,
                submissionDate, eventDate, eventTime,
                customer, figureVersions, 5, designer, representative, author);

        Address newAddress = new Address("Av. Liberdade", "Lisboa", "1000-000");
        request.updateAddress(newAddress);
        assertEquals(newAddress, request.address());
    }

    @Test
    void ensureCanUpdateDuration() {
        ShowRequest request = new ShowRequest(showRequestID, address, duration, description,
                submissionDate, eventDate, eventTime,
                customer, figureVersions, 5, designer, representative, author);

        ShowRequestDuration newDuration = new ShowRequestDuration(45);
        request.updateDuration(newDuration);
        assertEquals(newDuration, request.duration());
    }

    @Test
    void ensureCanUpdateFigureVersions() {
        ShowRequest request = new ShowRequest(showRequestID, address, duration, description,
                submissionDate, eventDate, eventTime,
                customer, figureVersions, 5, designer, representative, author);

        Set<FigureVersionID> newVersions = Set.of(FigureVersionID.valueOf(2));
        request.updateFigureVersions(newVersions);
        assertEquals(newVersions, request.figureVersions());
    }

    @Test
    void ensureEditFailsForInvalidStatus() {
        ShowRequest request = new ShowRequest(showRequestID, address, duration, description,
                submissionDate, eventDate, eventTime,
                customer, figureVersions, 5, designer, representative, author);
        request.updateStatus(ShowRequestStatus.PROPOSAL_SENT);

        assertThrows(IllegalStateException.class, () ->
                request.editRequest(new Address("Nova", "Cidade", "1234-567"),
                        new ShowRequestDuration(60),
                        new ShowRequestDescription("Outra descrição"),
                        10));
    }

    @Test
    void ensureEditFailsIfNoChangeDetected() {
        ShowRequest request = new ShowRequest(showRequestID, address, duration, description,
                submissionDate, eventDate, eventTime,
                customer, figureVersions, 5, designer, representative, author);

        assertThrows(IllegalArgumentException.class, () ->
                request.editRequest(address, duration, description, 5));
    }

    @Test
    void ensureEditSuccessWhenChanged() {
        ShowRequest request = new ShowRequest(showRequestID, address, duration, description,
                submissionDate, eventDate, eventTime,
                customer, figureVersions, 5, designer, representative, author);

        Address newAddress = new Address("Rua Nova", "Porto", "4000-000");
        ShowRequestDuration newDuration = new ShowRequestDuration(45);
        ShowRequestDescription newDescription = new ShowRequestDescription("Novo show");
        int newDrones = 6;

        request.editRequest(newAddress, newDuration, newDescription, newDrones);

        assertEquals(newAddress, request.address());
        assertEquals(newDuration, request.duration());
        assertEquals(newDescription, request.description());
        assertEquals(6, request.numDrones());
    }

    @Test
    void ensureEqualsAndHashCodeAreConsistent() {
        ShowRequestID id1 = new ShowRequestID(1);
        ShowRequestID id2 = new ShowRequestID(1);
        assertEquals(id1, id2);
        assertEquals(id1.hashCode(), id2.hashCode());
    }
}
