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
 * Test class for the AverageTimes class.
 */
public class AverageTimesTest {

    private Simulator simulator;
    private List<OperationScheduled> operationsScheduled;

    @BeforeEach
    public void setUp() {
        // Initialize the simulator object
        simulator = new Simulator();

        // Initialize operations scheduled list
        operationsScheduled = new ArrayList<>();
        operationsScheduled.add(new OperationScheduled(1, "Operation1", "W1", 0, 8, 0, "1"));
        operationsScheduled.add(new OperationScheduled(2, "Operation2", "W2", 20, 30, 1, "1"));
        operationsScheduled.add(new OperationScheduled(3, "Operation3", "W3", 12, 18, 2, "1"));
        operationsScheduled.add(new OperationScheduled(4, "Operation3", "W3", 0, 6, 0, "2"));
        operationsScheduled.add(new OperationScheduled(5, "Operation2", "W2", 10, 20, 1, "2"));
        operationsScheduled.add(new OperationScheduled(6, "Operation1", "W1", 8, 16, 2, "2"));
        operationsScheduled.add(new OperationScheduled(7, "Operation2", "W2", 0, 10, 0, "3"));
        operationsScheduled.add(new OperationScheduled(8, "Operation3", "W3", 6, 12, 1, "3"));
        operationsScheduled.add(new OperationScheduled(9, "Operation1", "W4", 0, 9, 2, "3"));

        simulator.getOperationsScheduled().addAll(operationsScheduled);
    }

    /**
     * Test the calculation of average execution and waiting times per operation.
     */
    @Test
    public void testTimeSpentPerOperation() {
        Map<String, Map<String, Double>> AvrgExecAndWaitTimes = simulator.calculateAverageExecutionAndWaitingTimes();

        // Check the results
        // Execution - 8 + 8 + 9 = 25 -> 25/3 = 8.333333333333334
        // Waiting - 0 + 8 + 0 = 8 -> 8/3 = 2.6666666666666665
        Map<String, Double> operation1Times = new HashMap<>();
        operation1Times.put("Execution", 8.333333333333334);
        operation1Times.put("Waiting", 2.6666666666666665);

        assertEquals(operation1Times.get("Execution"), AvrgExecAndWaitTimes.get("averageExecutionTime").get("Operation1"));
        assertEquals(operation1Times.get("Waiting"), AvrgExecAndWaitTimes.get("averageWaitingTime").get("Operation1"));

        /*---------------------------------------------------------------*/

        // Execution - 10 + 10 + 10 = 30 -> 30/3 = 10
        // Waiting - 12 + 4 + 0 = 16 -> 16/3 = 5.333333333333333
        Map<String, Double> operation2Times = new HashMap<>();
        operation2Times.put("Execution", 10.0);
        operation2Times.put("Waiting", 5.333333333333333);

        assertEquals(operation2Times.get("Execution"), AvrgExecAndWaitTimes.get("averageExecutionTime").get("Operation2"));
        assertEquals(operation2Times.get("Waiting"), AvrgExecAndWaitTimes.get("averageWaitingTime").get("Operation2"));

        /*---------------------------------------------------------------*/

        // Execution - 6 + 6 + 6 = 18 -> 18/3 = 6
        // Waiting - 12 + 0 + 6 = 18 -> 18/3 = 6
        Map<String, Double> operation3Times = new HashMap<>();
        operation3Times.put("Execution", 6.0);
        operation3Times.put("Waiting", 6.0);

        assertEquals(operation3Times.get("Execution"), AvrgExecAndWaitTimes.get("averageExecutionTime").get("Operation3"));
        assertEquals(operation3Times.get("Waiting"), AvrgExecAndWaitTimes.get("averageWaitingTime").get("Operation3"));

        simulator.displayAverageExecutionAndWaitingTimes(AvrgExecAndWaitTimes);
    }
}