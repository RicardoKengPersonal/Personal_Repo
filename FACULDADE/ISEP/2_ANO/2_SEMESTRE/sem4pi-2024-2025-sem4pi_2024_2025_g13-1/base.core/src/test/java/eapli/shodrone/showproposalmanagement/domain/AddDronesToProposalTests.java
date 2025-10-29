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

// Helper class for dependencies, similar to the one in the other test file.
class StubShowRequestForDrones extends ShowRequest {
    public StubShowRequestForDrones() {
        super();
    }
}

public class AddDronesToProposalTests {

    private ShowProposal proposal;
    private DroneModel droneModel1;
    private DroneModel droneModel2;

    // Helper method to create a valid DroneModel instance for tests,
    // satisfying its complex constructor.
    private DroneModel createTestDroneModel(String name, String manufacturer) {
        return new DroneModel(
                name,
                manufacturer,
                0.1f, 0.1f, 0.1f,
                Calendar.getInstance(),
                10f, 5f,
                Set.of(DroneType.LED) // Assuming DroneType is an enum with this value
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
        assertTrue(proposal.getDroneFleet().isEmpty(), "Drone fleet should be initially empty.");

        // Action
        proposal.addDronesOfModel(droneModel1, 5);

        // Assert
        Map<DroneModel, Integer> fleet = proposal.getDroneFleet();
        assertEquals(1, fleet.size(), "Fleet should contain one entry.");
        assertEquals(5, fleet.get(droneModel1), "Quantity of the added drone model should be 5.");
    }

    @Test
    void ensureAddingSameDroneModelSumsQuantity() {
        // Arrange: Add an initial quantity
        proposal.addDronesOfModel(droneModel1, 3);
        assertEquals(3, proposal.getDroneFleet().get(droneModel1));

        // Action: Add more drones of the same model
        proposal.addDronesOfModel(droneModel1, 7);

        // Assert
        Map<DroneModel, Integer> fleet = proposal.getDroneFleet();
        assertEquals(1, fleet.size(), "Fleet should still contain only one entry.");
        assertEquals(10, fleet.get(droneModel1), "Quantities should be summed up.");
    }

    @Test
    void ensureAddingDifferentDroneModelsCreatesSeparateEntries() {
        // Arrange
        proposal.addDronesOfModel(droneModel1, 5);

        // Action
        proposal.addDronesOfModel(droneModel2, 10);

        // Assert
        Map<DroneModel, Integer> fleet = proposal.getDroneFleet();
        assertEquals(2, fleet.size(), "Fleet should contain two separate entries.");
        assertEquals(5, fleet.get(droneModel1), "Quantity for model 1 should be correct.");
        assertEquals(10, fleet.get(droneModel2), "Quantity for model 2 should be correct.");
    }

    @Test
    void ensureCannotAddZeroOrNegativeQuantityOfDrones() {
        // The domain object uses Preconditions.ensure(quantity > 0, ...)
        // This should throw an IllegalArgumentException.
        assertThrows(IllegalArgumentException.class, () -> {
            proposal.addDronesOfModel(droneModel1, 0);
        }, "Adding zero drones should be rejected.");

        assertThrows(IllegalArgumentException.class, () -> {
            proposal.addDronesOfModel(droneModel1, -1);
        }, "Adding a negative quantity of drones should be rejected.");
    }

    @Test
    void ensureClearingDroneFleetWorksCorrectly() {
        // Arrange: Add some drones
        proposal.addDronesOfModel(droneModel1, 5);
        proposal.addDronesOfModel(droneModel2, 10);
        assertFalse(proposal.getDroneFleet().isEmpty(), "Fleet should not be empty before clearing.");

        // Action
        proposal.clearDroneFleet();

        // Assert
        assertTrue(proposal.getDroneFleet().isEmpty(), "Fleet should be empty after clearing.");
    }
}