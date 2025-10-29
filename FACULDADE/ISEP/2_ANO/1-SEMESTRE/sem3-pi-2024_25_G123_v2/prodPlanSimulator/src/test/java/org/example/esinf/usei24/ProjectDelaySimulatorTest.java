package org.example.esinf.usei24;

import org.example.Domain.ProjectDelaySimulator;
import org.example.esinf.usei17.ImportActivitiesGraph;
import org.example.structures.graph.pert.PertGraph;
import org.example.structures.graph.pert.PertVertex;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectDelaySimulatorTest {

    private final String SMALL_FILE = "src/test/java/org/example/esinf/usei17/small_project.csv";

    @Test
    @DisplayName("Test delays and critical path on the graph")
    public void testGraphDelays() {
        // Import the small graph
        PertGraph<PertVertex<String, Integer>, Integer> graph = ImportActivitiesGraph.importPertCpmGraph(SMALL_FILE);
        assertNotNull(graph, "The imported graph should not be null");

        // Initialize the simulator
        ProjectDelaySimulator simulator = new ProjectDelaySimulator(graph);

        // Perform calculations
        simulator.calculateEarliestTimes();
        simulator.calculateLatestTimes();

        // Verify critical path before delay
        int criticalPathDuration = simulator.calculateCriticalPath();
        System.out.println("Critical Path Duration Before Delay: " + criticalPathDuration);

        // Apply delay to an activity and recalculate
        assertTrue(simulator.applyDelay("A", 3), "Delay should be successfully applied to activity A");
        simulator.calculateEarliestTimes();
        simulator.calculateLatestTimes();

        // Verify critical path after delay
        int newCriticalPathDuration = simulator.calculateCriticalPath();
        System.out.println("Critical Path Duration After Delay: " + newCriticalPathDuration);

        assertTrue(newCriticalPathDuration > criticalPathDuration, "Critical path duration should increase after delay");
    }

    /**
     * Helper method to find a vertex by its activity ID.
     */
    private PertVertex<String, Integer> findVertexById(PertGraph<PertVertex<String, Integer>, Integer> graph, String activityId) {
        for (PertVertex<String, Integer> vertex : graph.vertices()) {
            if (vertex.getElement().equals(activityId)) {
                return vertex;
            }
        }
        return null; // Return null if no vertex is found
    }

    @Test
    @DisplayName("Test applyDelay method with valid and invalid inputs")
    public void testApplyDelay() {
        PertGraph<PertVertex<String, Integer>, Integer> graph = ImportActivitiesGraph.importPertCpmGraph(SMALL_FILE);
        assertNotNull(graph, "The imported graph should not be null");

        ProjectDelaySimulator simulator = new ProjectDelaySimulator(graph);

        // Valid delay
        assertTrue(simulator.applyDelay("A", 5), "Applying delay to a valid activity should return true");

        // Invalid delay
        assertFalse(simulator.applyDelay("InvalidActivity", 5), "Applying delay to an invalid activity should return false");
    }

    @Test
    @DisplayName("Test resetDelays method")
    public void testResetDelays() {
        PertGraph<PertVertex<String, Integer>, Integer> graph = ImportActivitiesGraph.importPertCpmGraph(SMALL_FILE);
        assertNotNull(graph, "The imported graph should not be null");

        ProjectDelaySimulator simulator = new ProjectDelaySimulator(graph);

        // Apply delays
        simulator.applyDelay("A", 5);
        simulator.applyDelay("B", 3);

        // Reset delays
        simulator.resetDelays();

        PertVertex<String, Integer> vertexA = findVertexById(graph, "A");
        PertVertex<String, Integer> vertexB = findVertexById(graph, "B");

        assertNotNull(vertexA, "Vertex A should exist");
        assertNotNull(vertexB, "Vertex B should exist");

        // Verify delays reset
        assertEquals(true, simulator.applyDelay("A", 0), "Delays for A should reset to 0");
        assertEquals(true, simulator.applyDelay("B", 0), "Delays for B should reset to 0");
    }

    @Test
    @DisplayName("Test calculateEarliestTimes method")
    public void testCalculateEarliestTimes() {
        PertGraph<PertVertex<String, Integer>, Integer> graph = ImportActivitiesGraph.importPertCpmGraph(SMALL_FILE);
        assertNotNull(graph, "The imported graph should not be null");

        ProjectDelaySimulator simulator = new ProjectDelaySimulator(graph);

        simulator.calculateEarliestTimes();

        PertVertex<String, Integer> vertexA = findVertexById(graph, "A");
        PertVertex<String, Integer> vertexB = findVertexById(graph, "B");

        assertNotNull(vertexA, "Vertex A should exist");
        assertNotNull(vertexB, "Vertex B should exist");

        assertEquals(0, vertexA.getEarliestStartTime(), "Earliest start time of A should be 0");
        assertEquals(1, vertexA.getEarliestFinishTime(), "Earliest finish time of A should be 5");

        assertEquals(0, vertexB.getEarliestStartTime(), "Earliest start time of B should be 5");
        assertEquals(4, vertexB.getEarliestFinishTime(), "Earliest finish time of B should be 8");
    }

    @Test
    @DisplayName("Test calculateLatestTimes method")
    public void testCalculateLatestTimes() {
        PertGraph<PertVertex<String, Integer>, Integer> graph = ImportActivitiesGraph.importPertCpmGraph(SMALL_FILE);
        assertNotNull(graph, "The imported graph should not be null");

        ProjectDelaySimulator simulator = new ProjectDelaySimulator(graph);

        simulator.calculateEarliestTimes();
        simulator.calculateLatestTimes();

        PertVertex<String, Integer> vertexA = findVertexById(graph, "A");
        PertVertex<String, Integer> vertexB = findVertexById(graph, "B");

        assertNotNull(vertexA, "Vertex A should exist");
        assertNotNull(vertexB, "Vertex B should exist");

        assertEquals(2, vertexA.getLatestStartTime(), "Latest start time of A should be 0");
        assertEquals(3, vertexA.getLatestFinishTime(), "Latest finish time of A should be 5");

        assertEquals(0, vertexB.getLatestStartTime(), "Latest start time of B should be 5");
        assertEquals(4, vertexB.getLatestFinishTime(), "Latest finish time of B should be 8");
    }

    @Test
    @DisplayName("Test calculateCriticalPath method")
    public void testCalculateCriticalPath() {
        PertGraph<PertVertex<String, Integer>, Integer> graph = ImportActivitiesGraph.importPertCpmGraph(SMALL_FILE);
        assertNotNull(graph, "The imported graph should not be null");

        ProjectDelaySimulator simulator = new ProjectDelaySimulator(graph);

        simulator.calculateEarliestTimes();
        simulator.calculateLatestTimes();

        int criticalPathDuration = simulator.calculateCriticalPath();

        System.out.println("Critical Path Duration: " + criticalPathDuration);

        assertEquals(17, criticalPathDuration, "The critical path duration for the small graph should be 17");
    }
}
