package org.example.ProductionSimulator;

import org.example.Domain.Article;
import org.example.Domain.OperationScheduled;
import org.example.Domain.Workstation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SimulatorTest {

    private List<Article> articleList;
    private Map<String, Workstation> workstations;
    private List<OperationScheduled> expectedOperationsScheduled;
    private Simulator simulator;

    @BeforeEach
    public void setUp() {
        // Initialize articles
        Article article1 = new Article(0, "1", 1, Arrays.asList("CUT", "SCREW", "POLISH"));
        Article article2 = new Article(1, "2", 2, Arrays.asList("SCREW", "CUT"));
        articleList = new ArrayList<>(Arrays.asList(article1, article2));

        // Initialize workstations
        Workstation workstation1 = new Workstation("WS1", "CUT", 10);
        Workstation workstation2 = new Workstation("WS2", "SCREW", 15);
        Workstation workstation3 = new Workstation("WS3", "POLISH", 20);
        workstations = new HashMap<>();
        workstations.put("WS1", workstation1);
        workstations.put("WS2", workstation2);
        workstations.put("WS3", workstation3);

        // Initialize the simulator object
        simulator = new Simulator();
        simulator.setArticleList(articleList);
        simulator.setWorkstations(workstations);

        // Initialize expected operations scheduled list (adjusted based on your simulation logic)
        expectedOperationsScheduled = new ArrayList<>();
        expectedOperationsScheduled.add(new OperationScheduled(0, "CUT", "WS1", 0, 10, 0, "1"));
        expectedOperationsScheduled.add(new OperationScheduled(0, "SCREW", "WS2", 10, 25, 1, "1"));
        expectedOperationsScheduled.add(new OperationScheduled(0, "POLISH", "WS3", 25, 45, 2, "1"));
        expectedOperationsScheduled.add(new OperationScheduled(1, "SCREW", "WS2", 25, 40, 0, "2"));
        expectedOperationsScheduled.add(new OperationScheduled(1, "CUT", "WS1", 10, 20, 1, "2"));
    }

    @Test
    public void testComputeSimulation() throws Exception {
        // Perform the simulation
        simulator.computeSimulation(articleList, workstations);

        // Check the results
        List<OperationScheduled> operationsScheduled = simulator.getOperationsScheduled();

        // Check that the size matches the expected size
        assertEquals(expectedOperationsScheduled.size(), operationsScheduled.size(), "Scheduled operations size mismatch");

        // Validate each scheduled operation
        for (int i = 0; i < expectedOperationsScheduled.size(); i++) {
            OperationScheduled expected = expectedOperationsScheduled.get(i);
            OperationScheduled actual = operationsScheduled.get(i);
            assertEquals(expected.getProductionOrderId(), actual.getProductionOrderId());
            assertEquals(expected.getOperation(), actual.getOperation());
            assertEquals(expected.getWorkstationId(), actual.getWorkstationId());
            assertEquals(expected.getStartTime(), actual.getStartTime());
            assertEquals(expected.getEndTime(), actual.getEndTime());
            assertEquals(expected.getOperationNumber(), actual.getOperationNumber());
            assertEquals(expected.getType(), actual.getType());
        }
    }
}
