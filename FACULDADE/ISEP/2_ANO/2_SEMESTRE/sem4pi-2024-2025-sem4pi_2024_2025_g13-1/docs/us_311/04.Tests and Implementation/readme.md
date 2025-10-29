# US311 - Add drones to a Show Proposal

## 4. Tests

**Test 1:** Ensure adding drones to an empty fleet works correctly.  
✓ Proposal fleet is updated with the correct drone model and quantity.

```java
@Test
void ensureCanAddDronesToEmptyFleet() {
    assertTrue(proposal.getDroneFleet().isEmpty(), "Drone fleet should be initially empty.");

    proposal.addDronesOfModel(droneModel1, 5);

    Map<DroneModel, Integer> fleet = proposal.getDroneFleet();
    assertEquals(1, fleet.size());
    assertEquals(5, fleet.get(droneModel1));
}
```

**Test 2:** Ensure adding the same drone model sums the quantity instead of overwriting.  
✓ The fleet entry is updated to reflect the total sum.

```java
@Test
void ensureAddingSameDroneModelSumsQuantity() {
    proposal.addDronesOfModel(droneModel1, 3);
    proposal.addDronesOfModel(droneModel1, 7);

    Map<DroneModel, Integer> fleet = proposal.getDroneFleet();
    assertEquals(1, fleet.size());
    assertEquals(10, fleet.get(droneModel1));
}
```

**Test 3:** Ensure adding different models creates separate entries.  
✓ Each model is tracked independently in the proposal.

```java
@Test
void ensureAddingDifferentDroneModelsCreatesSeparateEntries() {
    proposal.addDronesOfModel(droneModel1, 5);
    proposal.addDronesOfModel(droneModel2, 10);

    Map<DroneModel, Integer> fleet = proposal.getDroneFleet();
    assertEquals(2, fleet.size());
    assertEquals(5, fleet.get(droneModel1));
    assertEquals(10, fleet.get(droneModel2));
}
```

**Test 4:** Ensure invalid quantities (zero or negative) are rejected.  
✓ An `IllegalArgumentException` is thrown.

```java
@Test
void ensureCannotAddZeroOrNegativeQuantityOfDrones() {
    assertThrows(IllegalArgumentException.class, () -> {
        proposal.addDronesOfModel(droneModel1, 0);
    });

    assertThrows(IllegalArgumentException.class, () -> {
        proposal.addDronesOfModel(droneModel1, -1);
    });
}
```

**Test 5:** Ensure clearing drone fleet works correctly.  
✓ The fleet is empty after clearing.

```java
@Test
void ensureClearingDroneFleetWorksCorrectly() {
    proposal.addDronesOfModel(droneModel1, 5);
    proposal.addDronesOfModel(droneModel2, 10);

    proposal.clearDroneFleet();

    assertTrue(proposal.getDroneFleet().isEmpty());
}
```

## Test Class: AddDronesToProposalTests.java

```java
package eapli.shodrone.showproposalmanagement.domain;

import eapli.shodrone.dronemanagement.domain.DroneModel;
import eapli.shodrone.dronemanagement.domain.DroneType;
import eapli.shodrone.showrequestmanagement.domain.ShowRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StubShowRequestForDrones extends ShowRequest {
    public StubShowRequestForDrones() {
        super();
    }
}

public class AddDronesToProposalTests {

    private ShowProposal proposal;
    private DroneModel droneModel1;
    private DroneModel droneModel2;

    private DroneModel createTestDroneModel(String name, String manufacturer) {
        return new DroneModel(
                name,
                manufacturer,
                0.1f, 0.1f, 0.1f,
                Calendar.getInstance(),
                10f, 5f,
                Set.of(DroneType.LED)
        );
    }

    @BeforeEach
    void setUp() {
        ShowRequest request = new StubShowRequestForDrones();
        proposal = new ShowProposal(new ShowProposalID(1), request);
        droneModel1 = createTestDroneModel("TestModel-A", "ManufacturerX");
        droneModel2 = createTestDroneModel("TestModel-B", "ManufacturerY");
    }

    @Test
    void ensureCanAddDronesToEmptyFleet() {
        assertTrue(proposal.getDroneFleet().isEmpty());

        proposal.addDronesOfModel(droneModel1, 5);

        Map<DroneModel, Integer> fleet = proposal.getDroneFleet();
        assertEquals(1, fleet.size());
        assertEquals(5, fleet.get(droneModel1));
    }

    @Test
    void ensureAddingSameDroneModelSumsQuantity() {
        proposal.addDronesOfModel(droneModel1, 3);
        proposal.addDronesOfModel(droneModel1, 7);

        Map<DroneModel, Integer> fleet = proposal.getDroneFleet();
        assertEquals(1, fleet.size());
        assertEquals(10, fleet.get(droneModel1));
    }

    @Test
    void ensureAddingDifferentDroneModelsCreatesSeparateEntries() {
        proposal.addDronesOfModel(droneModel1, 5);
        proposal.addDronesOfModel(droneModel2, 10);

        Map<DroneModel, Integer> fleet = proposal.getDroneFleet();
        assertEquals(2, fleet.size());
        assertEquals(5, fleet.get(droneModel1));
        assertEquals(10, fleet.get(droneModel2));
    }

    @Test
    void ensureCannotAddZeroOrNegativeQuantityOfDrones() {
        assertThrows(IllegalArgumentException.class, () -> {
            proposal.addDronesOfModel(droneModel1, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            proposal.addDronesOfModel(droneModel1, -1);
        });
    }

    @Test
    void ensureClearingDroneFleetWorksCorrectly() {
        proposal.addDronesOfModel(droneModel1, 5);
        proposal.addDronesOfModel(droneModel2, 10);

        proposal.clearDroneFleet();

        assertTrue(proposal.getDroneFleet().isEmpty());
    }
}
```
---
##  Class: AddDronesToProposalController
```java
package eapli.shodrone.showproposalmanagement.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.shodrone.dronemanagement.domain.AvailableDroneModelDTO;
import eapli.shodrone.dronemanagement.repository.DroneModelRepository;
import eapli.shodrone.dronemanagement.repository.DroneRepository;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;
import eapli.shodrone.usermanagement.domain.Roles;

import java.util.List;

@UseCaseController
public class AddDronesToProposalController {

  private final AuthorizationService authz;
  private final eapli.shodrone.showproposalmanagement.application.AddDronesToProposalService service;

  public AddDronesToProposalController(
          AuthorizationService authz,
          ShowProposalRepository proposalRepo,
          DroneRepository droneRepo,
          DroneModelRepository droneModelRepo
  ) {
    this.authz = authz;
    this.service = new eapli.shodrone.showproposalmanagement.application.AddDronesToProposalService(proposalRepo, droneRepo, droneModelRepo);
  }

  public List<AvailableDroneModelDTO> listAvailableDroneModels() {
    authz.ensureAuthenticatedUserHasAnyOf(Roles.CRM_COLLABORATOR, Roles.ADMIN);
    return service.getAvailableDroneModels();
  }

  public void addDronesToProposal(String proposalId, List<AvailableDroneModelDTO> selectedDrones) {
    authz.ensureAuthenticatedUserHasAnyOf(Roles.CRM_COLLABORATOR, Roles.ADMIN);
    service.addDronesToProposal(proposalId, selectedDrones);
  }
}

```
---
## Class: AddDronesToProposalService
```java
package eapli.shodrone.showproposalmanagement.application;

import eapli.shodrone.dronemanagement.domain.Drone;
import eapli.shodrone.dronemanagement.domain.DroneModel;
import eapli.shodrone.dronemanagement.domain.DroneStatus;
import eapli.shodrone.dronemanagement.domain.AvailableDroneModelDTO;
import eapli.shodrone.dronemanagement.repository.DroneModelRepository;
import eapli.shodrone.dronemanagement.repository.DroneRepository;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalID;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AddDronesToProposalService {

  private final ShowProposalRepository proposalRepository;
  private final DroneRepository droneRepository;
  private final DroneModelRepository droneModelRepository;

  public AddDronesToProposalService(
          ShowProposalRepository proposalRepository,
          DroneRepository droneRepository,
          DroneModelRepository droneModelRepository) {
    this.proposalRepository = proposalRepository;
    this.droneRepository = droneRepository;
    this.droneModelRepository = droneModelRepository;
  }

  public List<AvailableDroneModelDTO> getAvailableDroneModels() {
    List<AvailableDroneModelDTO> result = new ArrayList<>();

    List<DroneModel> models = droneModelRepository.findByActiveTrue();

    for (DroneModel model : models) {
      Iterable<Drone> dronesIterable = droneRepository.findByModelAndStatus(model, DroneStatus.ACTIVE);
      List<Drone> activeDrones = StreamSupport.stream(dronesIterable.spliterator(), false)
              .toList();

      if (!activeDrones.isEmpty()) {
        result.add(new AvailableDroneModelDTO(model.name(), activeDrones.size()));
      }
    }

    return result;
  }

  public void addDronesToProposal(String proposalIdStr, List<AvailableDroneModelDTO> selectedDrones) {
    ShowProposalID proposalId = ShowProposalID.valueOf(Integer.valueOf(proposalIdStr));

    ShowProposal proposal = proposalRepository.ofIdentity(proposalId)
            .orElseThrow(() -> new IllegalArgumentException("Show Proposal not found: " + proposalIdStr));

    for (AvailableDroneModelDTO dto : selectedDrones) {
      Optional<DroneModel> optModel = droneModelRepository.findByNameIgnoreCase(dto.modelName);
      if (optModel.isEmpty()) {
        throw new IllegalArgumentException("Drone model not found: " + dto.modelName);
      }

      DroneModel model = optModel.get();
      Iterable<Drone> availableDrones = droneRepository.findByModelAndStatus(model, DroneStatus.ACTIVE);
      List<Drone> activeList = StreamSupport.stream(availableDrones.spliterator(), false)
              .toList();

      if (activeList.size() < dto.availableCount) {
        throw new IllegalArgumentException("Not enough drones available for model: " + dto.modelName);
      }

      proposal.addDronesOfModel(model, dto.availableCount);
    }

    proposalRepository.save(proposal);
  }
}


```

---
## Class: AddDronesToProposalUI
```java
package eapli.shodrone.app.backoffice.console.presentation.crmcollaborator;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.shodrone.dronemanagement.domain.AvailableDroneModelDTO;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

public class AddDronesToProposalUI extends AbstractUI {

  private final eapli.shodrone.showproposalmanagement.application.AddDronesToProposalController controller =
          new eapli.shodrone.showproposalmanagement.application.AddDronesToProposalController(
                  AuthzRegistry.authorizationService(),
                  PersistenceContext.repositories().showProposalRepository(),
                  PersistenceContext.repositories().drone(),
                  PersistenceContext.repositories().droneModel());

  @Override
  protected boolean doShow() {
    try {
      String proposalId = Console.readLine("Enter Proposal ID: ");

      // Step 1: Show available drones by model
      Iterable<AvailableDroneModelDTO> available = controller.listAvailableDroneModels();

      System.out.println("\n--- Available Drones ---");
      for (AvailableDroneModelDTO dto : available) {
        System.out.printf("Model: %s | Available: %d%n", dto.modelName, dto.availableCount);
      }

      // Step 2: User selection
      List<AvailableDroneModelDTO> selected = new ArrayList<>();
      boolean done = false;

      while (!done) {
        String modelName = Console.readLine("\nEnter model name to add (or 'done' to finish): ");
        if ("done".equalsIgnoreCase(modelName)) break;

        int count = Console.readInteger("Enter number of drones to add: ");
        selected.add(new AvailableDroneModelDTO(modelName, count));
      }


      controller.addDronesToProposal(proposalId, selected);

      System.out.println("✅ Drones added to proposal successfully.");

    } catch (Exception e) {
      System.out.println("⚠ Error adding drones: " + e.getMessage());
    }

    return true;
  }

  @Override
  public String headline() {
    return "Add Drones to a Proposal";
  }
}

```


---

## Integration and Demo

Integration and Demo
- A new option was added to the CRM Collaborator menu: "Add Drones to Proposal."
When this option is selected, the UI allows the user to:
- Select a Show Proposal to configure with drones.
View available drone models and quantities from Shodrone’s inventory.
- Select the desired number and models of drones to add to the proposal.
- The system validates that the total number of drones of each model does not exceed the available inventory.
- The Show Proposal is updated with the selected drones.
- The change is saved and reflected in the proposal’s details.
- Attempts to add more drones than available in inventory will result in a validation error shown to the user.


---

## 7. Observations

n/a.



