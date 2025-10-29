package eapli.shodrone.showproposalmanagement.domain;

import eapli.shodrone.showrequestmanagement.domain.ShowRequest;
import eapli.shodrone.showrequestmanagement.domain.ShowRequestID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Helper class to stub a ShowRequest, as its full implementation was not provided.
// This allows the instantiation of a ShowProposal for testing purposes.
class StubShowRequest extends ShowRequest {
    public StubShowRequest() {

        super();
    }

    @Override
    public ShowRequestID identity() {
        return new ShowRequestID(1);
    }
}


public class MarkShowProposalAcceptedTests {

    private ShowRequest validShowRequest;

    @BeforeEach
    void setUp() {
        // Create a valid, non-null ShowRequest instance for the proposal.
        validShowRequest = new StubShowRequest();
    }

    @Test
    void ensureNewProposalHasCreatedStatus() {
        // Action
        ShowProposal proposal = new ShowProposal(new ShowProposalID(1), validShowRequest);

        // Assert
        assertEquals(ShowProposalStatus.CREATED, proposal.status());
    }

    @Test
    void ensureStatusCanBeUpdated() {
        // Arrange
        ShowProposal proposal = new ShowProposal(new ShowProposalID(1), validShowRequest);
        assertEquals(ShowProposalStatus.CREATED, proposal.status(), "Initial status should be CREATED");

        // Action: Update the status to any other valid status.
        proposal.updateStatus(ShowProposalStatus.ACCEPTED_CRM);

        // Assert
        assertEquals(ShowProposalStatus.ACCEPTED_CRM, proposal.status(), "Status should be updated to ACCEPTED_CRM");
    }

    @Test
    void ensureProposalCanBeMarkedAsReadyToSendWhenInCreatedStatus() {
        // Arrange
        ShowProposal proposal = new ShowProposal(new ShowProposalID(1), validShowRequest);

        // Action
        proposal.markAsReadyToTest();

        // Assert
        assertEquals(ShowProposalStatus.READY_TO_SEND, proposal.status());
    }

    @Test
    void ensureCannotMarkAsReadyToSendWhenNotInCreatedStatus() {
        // Arrange: Create a proposal and change its status to something other than CREATED
        ShowProposal proposal = new ShowProposal(new ShowProposalID(1), validShowRequest);
        proposal.updateStatus(ShowProposalStatus.ACCEPTED_CRM);

        // Action & Assert: Expect an IllegalStateException when trying to mark as ready.
        assertThrows(IllegalStateException.class, proposal::markAsReadyToTest);
    }

    @Test
    void ensureProposalsWithSameIdAreEqual() {
        // Arrange
        ShowProposalID id = new ShowProposalID(123);
        ShowProposal proposal1 = new ShowProposal(id, validShowRequest);
        ShowProposal proposal2 = new ShowProposal(id, new StubShowRequest()); // Different request, same ID

        // Assert
        assertTrue(proposal1.sameAs(proposal2), "Proposals with the same ID should be considered the same.");
        assertEquals(proposal1, proposal2, "Equals method should return true for proposals with the same ID.");
        assertEquals(proposal1.hashCode(), proposal2.hashCode(), "Hash codes should be equal for equal objects.");
    }

    @Test
    void ensureProposalsWithDifferentIdAreNotEqual() {
        // Arrange
        ShowProposal proposal1 = new ShowProposal(new ShowProposalID(123), validShowRequest);
        ShowProposal proposal2 = new ShowProposal(new ShowProposalID(456), validShowRequest);

        // Assert
        assertFalse(proposal1.sameAs(proposal2), "Proposals with different IDs should not be considered the same.");
        assertNotEquals(proposal1, proposal2, "Equals method should return false for proposals with different IDs.");
    }
}