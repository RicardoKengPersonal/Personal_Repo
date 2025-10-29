package eapli.shodrone.showproposalmanagement.domain;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.shodrone.customermanagement.domain.*;
import eapli.shodrone.figuremanagement.domain.FigureVersionID;
import eapli.shodrone.showrequestmanagement.domain.*;
import eapli.shodrone.customermanagement.domain.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ShowProposalTest {

    private ShowProposalID proposalID;
    private ShowRequest showRequest;

    @BeforeEach
    void setUp() {
        CustomerID customerID = new CustomerID(2);
        VatNumber vatNumber = new VatNumber("PT123456789");
        String companyName = "Empresa XYZ";
        CustomerStatus status = CustomerStatus.CREATED;
        CustomerPriority priority = CustomerPriority.REGULAR;
        Address customerAddress = new Address("Rua Exemplo", "Paredes", "4580");

        Customer customer = new Customer(customerID, vatNumber, companyName, status, priority, customerAddress);

        ShowRequestID showRequestID = new ShowRequestID(1);
        eapli.shodrone.showrequestmanagement.domain.Address showAddress =
                new eapli.shodrone.showrequestmanagement.domain.Address("Rua das Flores", "Porto", "4050-123");
        ShowRequestDuration duration = new ShowRequestDuration(30);
        ShowRequestDescription description = new ShowRequestDescription("Espectáculo noturno com drones");

        Date submissionDate = Date.from(LocalDate.now());
        Date eventDate = Date.from(LocalDate.now().plusDays(7));
        Time eventTime = Time.from(LocalTime.of(21, 30)); // 21h30

        Set<FigureVersionID> figureVersions = Set.of(FigureVersionID.valueOf(1));
        SystemUser designer = mock(SystemUser.class);
        SystemUser author = mock(SystemUser.class);
        CustomerRepresentative representative = mock(CustomerRepresentative.class);

        proposalID = new ShowProposalID(1);
        showRequest = new ShowRequest(
                showRequestID,
                showAddress,
                duration,
                description,
                submissionDate,
                eventDate,
                eventTime,
                customer,
                figureVersions,
                5,
                designer,
                representative,
                author
        );
    }

    @Test
    void validProposalShouldBeCreated() {
        ShowProposal proposal = new ShowProposal(proposalID, showRequest);
        assertNotNull(proposal);
        assertEquals(proposalID, proposal.identity());
        assertEquals(ShowProposalStatus.CREATED, proposal.status());
        assertEquals(showRequest, proposal.showRequest());
    }

    @Test
    void updateStatusShouldChangeStatusCorrectly() {
        ShowProposal proposal = new ShowProposal(proposalID, showRequest);
        proposal.updateStatus(ShowProposalStatus.CREATED);
        assertEquals(ShowProposalStatus.CREATED, proposal.status());
    }

    @Test
    void updateStatusWithNullShouldThrowException() {
        ShowProposal proposal = new ShowProposal(proposalID, showRequest);
        assertThrows(IllegalArgumentException.class, () -> proposal.updateStatus(null));
    }

    @Test
    void sameAsShouldReturnTrueForSameID() {
        ShowProposal p1 = new ShowProposal(proposalID, showRequest);
        ShowProposal p2 = new ShowProposal(proposalID, showRequest);
        assertTrue(p1.sameAs(p2));
    }

    @Test
    void toStringShouldIncludeDetails() {
        ShowProposal proposal = new ShowProposal(proposalID, showRequest);
        String result = proposal.toString();
        assertTrue(result.contains("Proposal ID"));
        assertTrue(result.contains("Status"));
        assertTrue(result.contains("Request ID"));
    }

    @Test
    void constructorWithNullArgsShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new ShowProposal(null, showRequest));
        assertThrows(IllegalArgumentException.class, () -> new ShowProposal(proposalID, null));
    }
}
