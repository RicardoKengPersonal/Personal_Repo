package org.example.ProductionSimulator;

import org.example.Domain.Article;
import org.example.Domain.OperationScheduled;
import org.example.Domain.Workstation;
import org.example.Dto.WorkstationStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Simulator class.
 */
public class TotalWorkstationsTimeTest {

    private Simulator simulator;
    private List<Article> articleList;
    private Map<String, Workstation> workstations;
    private List<OperationScheduled> operationsScheduled;

    /**
     * Sets up the Simulator instance for testing.
     */
    @BeforeEach
    public void setUp() {
        // Initialize the simulator object
        simulator = new Simulator();

        // Initialize operations scheduled list
        operationsScheduled = new ArrayList<>();
        operationsScheduled.add(new OperationScheduled(1, "Operation1", "W1", 0, 8, 0, "1"));
        operationsScheduled.add(new OperationScheduled(2, "Operation2", "W2", 8, 18, 1, "1"));
        operationsScheduled.add(new OperationScheduled(3, "Operation3", "W3", 18, 24, 2, "1"));
        operationsScheduled.add(new OperationScheduled(4, "Operation3", "W3", 24, 30, 0, "2"));
        operationsScheduled.add(new OperationScheduled(5, "Operation2", "W2", 30, 40, 1, "2"));
        operationsScheduled.add(new OperationScheduled(6, "Operation1", "W1", 40, 48, 2, "2"));
        operationsScheduled.add(new OperationScheduled(7, "Operation2", "W2", 48, 58, 0, "3"));
        operationsScheduled.add(new OperationScheduled(8, "Operation3", "W3", 58, 64, 1, "3"));
        operationsScheduled.add(new OperationScheduled(9, "Operation1", "W4", 64, 73, 2, "3"));

        simulator.getOperationsScheduled().addAll(operationsScheduled);
    }

    /**
     * Tests the total time spent per operation.
     */
    @Test
    public void testTimeSpentPerOperation() {
        // Capture the output
        List<WorkstationStats> workstationStats = simulator.calculateWorkstationOperationTimes();
        // Check the results
        // total time for W1 = 8 + 8 = 16
        // total time for W2 = 10 + 10 + 10 = 30
        // total time for W3 = 6 + 6 + 6 = 18
        // total time for W4 = 9
        // total time = 16 + 30 + 18 + 9 = 73

        assertEquals("W1", workstationStats.get(0).getWorkstationId());
        assertEquals(16, workstationStats.get(0).getTotalTime());
        // (16/73)*100 = 21.91780821917808
        assertEquals(21.91780821917808 , workstationStats.get(0).getPercentageOfTotalTime());

        assertEquals("W2", workstationStats.get(1).getWorkstationId());
        assertEquals(30, workstationStats.get(1).getTotalTime());
        // (30/73)*100 = 41.0958904109589
        assertEquals(41.0958904109589 , workstationStats.get(1).getPercentageOfTotalTime());

        assertEquals("W3", workstationStats.get(2).getWorkstationId());
        assertEquals(18, workstationStats.get(2).getTotalTime());
        // (18/73)*100 = 24.65753424657534
        assertEquals(24.65753424657534 , workstationStats.get(2).getPercentageOfTotalTime());

        assertEquals("W4", workstationStats.get(3).getWorkstationId());
        assertEquals(9, workstationStats.get(3).getTotalTime());
        // (9/73)*100 = 12.32876712328767
        assertEquals(12.32876712328767 , workstationStats.get(3).getPercentageOfTotalTime());

        simulator.displayWorkstationOperationTimes(workstationStats);
    }
}