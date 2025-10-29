# US 310 Create Show Proposal

## 4. Tests 

**ShowProposalTest:**
    
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


## 5. Construction (Implementation)

### Class ShowProposal

```java
@Entity
@Table(name = "SHOW_PROPOSAL")
public class ShowProposal implements AggregateRoot<ShowProposalID>, DTOable<ShowProposalDTO>, Serializable {

    @EmbeddedId
    @Getter
    private ShowProposalID showProposalId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "show_request_id", nullable = false)
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
    public ShowProposalDTO toDTO() {
        return new ShowProposalDTO(
                showRequest.identity().toString()
        );
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

### Class ShowProposalUI

```java
public class CreateShowProposalUI extends AbstractUI {

    private final CreateShowProposalController controller = new CreateShowProposalController(
            AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().showProposalRepository(),
            PersistenceContext.repositories().showRequestRepository(),
            PersistenceContext.repositories().users());

    private final ListShowRequestController listController = new ListShowRequestController(
            PersistenceContext.repositories().showRequestRepository()
    );

    @Override
    protected boolean doShow() {
        try {
            Iterable<ShowRequest> iterable = listController.findAll();
            List<ShowRequest> requests = new ArrayList<>();
            iterable.forEach(requests::add);

            if (requests.isEmpty()) {
                System.out.println("No show requests available.\n");
                return false;
            }

            System.out.println("\n=== Available Show Requests ===");
            for (int i = 0; i < requests.size(); i++) {
                ShowRequest request = requests.get(i);
                System.out.printf("%d. ID: %s | Customer: %s | Status: %s | Drones: %d%n",
                        i + 1,
                        request.identity(),
                        request.customer().companyName(),
                        request.status(),
                        request.numDrones());
            }

            int selection;
            do {
                selection = Console.readInteger("\nSelect a request by number (1-" + requests.size() + "): ");
            } while (selection < 1 || selection > requests.size());

            ShowRequest selectedRequest = requests.get(selection - 1);
            final ShowProposalDTO dto = new ShowProposalDTO(
                    selectedRequest.identity().toString()
            );

            controller.addShowProposal(dto);
            System.out.println(" Show Proposal created successfully for request " + selectedRequest.identity());

        } catch (IllegalArgumentException e) {
            System.out.println(" Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }

        return true;
    }

    @Override
    public String headline() {
        return "Create Show Proposal";
    }

}

```

### Class ShowProposalService

```java
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
            e.printStackTrace();  // ou log mais detalhado
            throw new RuntimeException("Failed to save ShowProposal", e);
        }
    }

}

```


### Class ShowProposalController

```java
public class CreateShowProposalController {
    private final AuthorizationService authz;
    private final ShowProposalService service;
    private final ShowProposalRepository showProposalRepo;
    private final ShowRequestRepository showRequestRepo;

    public CreateShowProposalController(
            final AuthorizationService authz,
            final ShowProposalRepository showProposalRepo,
            final ShowRequestRepository showRequestRepo,
            final UserRepository userRepository) {

        this.authz = authz;
        this.showProposalRepo = showProposalRepo;
        this.showRequestRepo = showRequestRepo;
        this.service = new ShowProposalService(showProposalRepo, userRepository);
    }

    public Iterable<SystemUser> findAllUsers() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN, Roles.CRM_COLLABORATOR);
        return service.findAllUsers();
    }


    public void addShowProposal(final ShowProposalDTO dto) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN, Roles.CRM_COLLABORATOR);

        final ShowProposalParser parser = new ShowProposalParser(showProposalRepo, showRequestRepo);
        final ShowProposal proposal = parser.valueOf(dto);

        service.registerNewShowProposal(proposal);
    }

}

```
