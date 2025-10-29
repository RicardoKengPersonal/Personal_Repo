package org.example.ProductionSimulator;

import org.example.ProductionSimulator.PrioritySimulator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for the PrioritySimulator class.
 */
class PrioritySimulatorTest {

    private PrioritySimulator prioritySimulator;

    /**
     * Sets up the PrioritySimulator instance for testing.
     */
    @BeforeEach
    void setUp() {
        prioritySimulator = new PrioritySimulator();
    }

    /**
     * Tests the initialization of the PrioritySimulator.
     */
    @Test
    void testPrioritySimulatorInitialization() {
        // Verify that the simulator initializes without any errors
        assertNotNull(prioritySimulator, "PrioritySimulator should be instantiated");
    }

    /**
     * Tests the computation of the priority simulation.
     */
    @Test
    void testDisplayScheduledOperationsTable() {
        // Ensure the displayScheduledOperationsTable method works without exceptions
        assertDoesNotThrow(() -> prioritySimulator.displayScheduledOperationsTable(),
                "displayScheduledOperationsTable should execute without errors");
    }

    /**
     * Tests the display of total time per article instance.
     */
    @Test
    void testDisplayTotalTimePerArticleType() {
        // Verify that displayTotalTimePerArticleType executes without exceptions
        assertDoesNotThrow(() -> prioritySimulator.displayTotalTimePerArticleType(),
                "displayTotalTimePerArticleType should execute without errors");
    }

    /**
     * Tests the calculation of time spent per operation.
     */
    @Test
    void testTimeSpentPerOperation() {
        // Check that timeSpentPerOperation executes without exceptions
        assertDoesNotThrow(() -> prioritySimulator.timeSpentPerOperation(),
                "timeSpentPerOperation should execute without errors");
    }

    /**
     * Tests the calculation of workstation operation times.
     */
    @Test
    void testDisplayWorkstationOperationTimes() {
        // Ensure displayWorkstationOperationTimes runs without issues
        assertDoesNotThrow(() -> prioritySimulator.displayWorkstationOperationTimes(),
                "displayWorkstationOperationTimes should execute without errors");
    }
}
