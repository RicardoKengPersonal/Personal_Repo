
# US 317 - Mark Show Proposal as Accepted

## 4. Tests

**Test 1:** Ensure marking show proposal as accepted works.  
✓ Proposal is updated with the correct status.

```java
@Test
void ensureStatusCanBeUpdatedToAccepted() {
    ShowProposal proposal = new ShowProposal(new ShowProposalID(1), mock(ShowRequest.class));
    assertEquals(ShowProposalStatus.CREATED, proposal.status());

    proposal.updateStatus(ShowProposalStatus.ACCEPTED);

    assertEquals(ShowProposalStatus.ACCEPTED, proposal.status());
}
```

**Test 2:** Ensure marking show proposal as accepted on an already accepted show proposal doesn't work.  
✓ Proposal should stay accepted and tell it's already in that state.

```java
@Test
void ensureProposalIsMarkedAsAcceptedWhenInCreatedStatus() {
    ShowProposalID id = new ShowProposalID(1);
    ShowProposal proposal = new ShowProposal(id, mock(ShowRequest.class));

    when(repository.findById(id)).thenReturn(Optional.of(proposal));

    service.markProposalAsAccepted(id);

    assertEquals(ShowProposalStatus.ACCEPTED, proposal.status());
    verify(repository).save(proposal);
}
```

**Test 3:** Ensure you can only mark show proposal as accepted if the show request associated exists.  
✓ Not found proposal

```java
@Test
void ensureExceptionIsThrownWhenProposalNotFound() {
    ShowProposalID id = new ShowProposalID(99);
    when(repository.findById(id)).thenReturn(Optional.empty());

    assertThrows(IllegalArgumentException.class, () -> {service.markProposalAsAccepted(id);});
}
```

**Test 4:** Ensure it doesn't work to accept if the proposal doesn't exist.  
✓ Not found proposal

```java
@Test
void ensureCannotAcceptNonCreatedProposal(ShowProposalStatus status) {
    ShowRequest request = new ShowRequest(showRequestID, address, duration, description, submissionDate, customer, figureVersions, 5, designer, representative, author);
    ShowProposalID id = new ShowProposalID(2);
    ShowProposal proposal = new ShowProposal(id, request);
    repository.save(proposal);
    ShowProposalService service = new ShowProposalService();
    service.markProposalAsAccepted(1);
    ShowProposal updated = repository.findById(1).orElseThrow();
    assertEquals(ShowProposalStatus.ACCEPTED, updated.status());
    assertEquals(1, updated.identity().toInteger());
}
```

---

## Construction (Implementation)

### Class: ShowProposal

```java
package eapli.shodrone.showproposalmanagement.domain;

import eapli.shodrone.showrequestmanagement.domain.ShowRequest;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "SHOW_PROPOSAL")
public class ShowProposal implements AggregateRoot<ShowProposalID>, Serializable {

    @EmbeddedId
    @Getter
    private ShowProposalID showProposalId;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "show_request_id", nullable = false, unique = true)
    private ShowRequest showRequest;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShowProposalStatus status;


    protected ShowProposal() {
        // for ORM
    }

    public ShowProposal(ShowProposalID id, ShowRequest showRequest) {
        Preconditions.noneNull(id, showRequest);

        this.showProposalId = id;
        this.showRequest = showRequest;
        this.status = ShowProposalStatus.CREATED;
    }

    public ShowRequest showRequest() {
        return showRequest;
    }

    public ShowProposalStatus status() {
        return status;
    }

    public void updateStatus(ShowProposalStatus newStatus) {
        Preconditions.nonNull(newStatus, "Status cannot be null.");
        this.status = newStatus;
    }

    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (!(other instanceof ShowProposal)) return false;
        ShowProposal that = (ShowProposal) other;
        return Objects.equals(showProposalId, that.showProposalId);
    }

    @Override
    public ShowProposalID identity() {
        return showProposalId;
    }

    @Override
    public String toString() {
        return String.format("Proposal ID: %s | Status: %s | Request ID: %s ",
                showProposalId,
                status,
                showRequest.identity()
        );
    }
}
```

---

### Class: ShowProposalService
```java
package eapli.shodrone.showproposalmanagement.application;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalID;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;
import jakarta.transaction.Transactional;

public class ShowProposalService {
private final ShowProposalRepository showProposalRepo;
private final UserRepository userRepo;

    public ShowProposalService(
            ShowProposalRepository showProposalRepo,
            UserRepository userRepo) {
        this.showProposalRepo = showProposalRepo;
        this.userRepo = userRepo;
    }

    public Iterable<SystemUser> findAllUsers() {
        return userRepo.findAll();
    }

    public void registerNewShowProposal(final ShowProposal proposal) {
        try {
            showProposalRepo.save(proposal);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save ShowProposal", e);
        }
    }
    @Transactional
    public void updateVideoLink(String proposalIdStr, String videoLink) {
        ShowProposalID proposalId = ShowProposalID.valueOf(Integer.valueOf(proposalIdStr));

        ShowProposal proposal = showProposalRepo.ofIdentity(proposalId)
                .orElseThrow(() -> new IllegalStateException("ShowProposal with ID " + proposalIdStr + " not found."));

        proposal.setVideoLink(videoLink);
        showProposalRepo.save(proposal);
    }

}

```
---

### Class: MarkProposalAcceptedController
```java
package eapli.shodrone.showproposalmanagement.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalID;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalStatus;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;
import eapli.shodrone.usermanagement.domain.Roles;

@UseCaseController
public class MarkShowProposalAcceptedController {

    private final AuthorizationService authz;
    private final ShowProposalRepository proposalRepo;

    public MarkShowProposalAcceptedController(AuthorizationService authz, ShowProposalRepository proposalRepo) {
        this.authz = authz;
        this.proposalRepo = proposalRepo;
    }
    public void markProposalAsAccepted(String Id) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN, Roles.CRM_COLLABORATOR);

        ShowProposalID id = ShowProposalID.valueOf(Integer.valueOf(Id));

        ShowProposal proposal = proposalRepo.ofIdentity(id)
                .orElseThrow(() -> new IllegalArgumentException("Proposal with ID " + id + " not found."));

        if (proposal.status() != ShowProposalStatus.ACCEPTED_CUSTOMER) {
            throw new IllegalStateException("Only proposals with status ACCEPTED_CUSTOMER can be accepted.");
        }

        proposal.updateStatus(ShowProposalStatus.ACCEPTED_CRM);
        proposalRepo.save(proposal);
    }
}
```
---
### Test Class: MarkShowProposalAcceptedTests
```java
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

```
---
### Class: MarkShowProposalAcceptedUI
```java
package eapli.shodrone.app.backoffice.console.presentation.crmcollaborator;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.io.util.Console;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.showproposalmanagement.application.MarkShowProposalAcceptedController;

public class MarkShowProposalAcceptedUI extends AbstractUI {

    private final MarkShowProposalAcceptedController controller = new MarkShowProposalAcceptedController(AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().showProposalRepository());

    @Override
    protected boolean doShow() {
        final String proposalId = Console.readLine("Enter the Proposal ID to accept: ");

        try {
            controller.markProposalAsAccepted(proposalId);
            System.out.println("Proposal accepted successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return true;
    }

    @Override
    public String headline() {
        return "Accept Proposal";
    }

}

```


---
## Integration and Demo

- A new option was added to the CRM Collaborator menu: "Mark Proposal as Accepted".
- When this option is selected, the UI allows the user to:
    - Select a Show Proposal that has been accepted by a Customer Representative (status is available in the system).
    - Confirm the operation to mark the proposal as accepted.
- Once confirmed:
    - The system updates the status of the Show Proposal to ACCEPTED.
    - The change is reflected in the proposal's details, and can be tracked within the system.
- Attempts to mark a proposal that has not been accepted in the Customer App will result in a validation error.

---

## 7. Observations

n/a.
