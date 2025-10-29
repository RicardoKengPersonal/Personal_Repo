package org.example.esinf.usei22;

import org.example.structures.graph.map.MapGraph;
import org.example.structures.graph.pert.PertAlgorithms;
import org.example.structures.graph.pert.PertGraph;
import org.example.structures.graph.pert.PertVertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PertAlgorithmsTest {

    private PertGraph<PertVertex<String, Integer>, Integer> graph;
    private PertAlgorithms pertAlgorithms;

    @BeforeEach
    public void setup() {
        graph = new MapGraph<>(true);  // Directed graph
        pertAlgorithms = new PertAlgorithms();
    }

    @Test
    public void testCriticalPathAndSlack() {
        // Setup: Create a simple PERT graph
        PertVertex<String, Integer> A = new PertVertex<>("A", 10);
        PertVertex<String, Integer> B = new PertVertex<>("B", 20);
        PertVertex<String, Integer> C = new PertVertex<>("C", 30);
        PertVertex<String, Integer> D = new PertVertex<>("D", 5);

        graph.addVertex(A.getElement());
        graph.addVertex(B.getElement());
        graph.addVertex(C.getElement());
        graph.addVertex(D.getElement());

        graph.addEdge(A.getElement(), B.getElement(), 20);
        graph.addEdge(A.getElement(), C.getElement(), 30);
        graph.addEdge(B.getElement(), D.getElement(), 5);
        graph.addEdge(C.getElement(), D.getElement(), 5);

        // Calculate Earliest Start and Finish, Latest Start and Finish, and Slack
        pertAlgorithms.calculateEarliestStartAndFinish(graph);
        pertAlgorithms.calculateLatestStartAndFinish(graph);
        pertAlgorithms.calculateSlack(graph);

        // Test: Check the critical path (activities with zero slack)
        pertAlgorithms.criticalPath(graph);

        // Example assertions: check if slack for activities is correctly calculated
        assertEquals(0, A.getSlack());
        assertEquals(0, B.getSlack());
        assertEquals(0, C.getSlack());
        assertEquals(0, D.getSlack());

        // Check the total project duration
        int totalDuration = 0;
        for (PertVertex<String, Integer> vertex : graph.vertices()) {
            totalDuration = Math.max(totalDuration, vertex.getEarliestFinishTime());
        }
        assertEquals(60, totalDuration);  // Total project duration should be 60
    }

    @Test
    public void testMultipleCriticalPaths() {
        // Setup: Create a graph with multiple critical paths
        PertVertex<String, Integer> A = new PertVertex<>("A", 10);
        PertVertex<String, Integer> B = new PertVertex<>("B", 10);
        PertVertex<String, Integer> C = new PertVertex<>("C", 20);
        PertVertex<String, Integer> D = new PertVertex<>("D", 10);
        PertVertex<String, Integer> E = new PertVertex<>("E", 10);

        graph.addVertex(A.getElement());
        graph.addVertex(B.getElement());
        graph.addVertex(C.getElement());
        graph.addVertex(D.getElement());
        graph.addVertex(E.getElement());

        graph.addEdge(A.getElement(), C.getElement(), 10);
        graph.addEdge(B.getElement(), C.getElement(), 10);
        graph.addEdge(C.getElement(), D.getElement(), 10);
        graph.addEdge(C.getElement(), E.getElement(), 10);

        // Calculate Earliest Start and Finish, Latest Start and Finish, and Slack
        pertAlgorithms.calculateEarliestStartAndFinish(graph);
        pertAlgorithms.calculateLatestStartAndFinish(graph);
        pertAlgorithms.calculateSlack(graph);

        // Test: Check the critical path
        pertAlgorithms.criticalPath(graph);

        // Validate: Multiple critical paths should be detected
        List<PertVertex<String, Integer>> criticalPath = graph.vertices(); // This should be the critical path vertices
        assertTrue(criticalPath.contains(A));
        assertTrue(criticalPath.contains(B));
        assertTrue(criticalPath.contains(C));
        assertTrue(criticalPath.contains(D));
        assertTrue(criticalPath.contains(E));
    }

    @Test
    public void testNoCriticalPath() {
        // Setup: Create a graph where all activities have slack
        PertVertex<String, Integer> A = new PertVertex<>("A", 10);
        PertVertex<String, Integer> B = new PertVertex<>("B", 5);
        PertVertex<String, Integer> C = new PertVertex<>("C", 5);

        graph.addVertex(A.getElement());
        graph.addVertex(B.getElement());
        graph.addVertex(C.getElement());

        graph.addEdge(A.getElement(), B.getElement(), 5);
        graph.addEdge(B.getElement(), C.getElement(), 5);

        // Calculate Earliest Start and Finish, Latest Start and Finish, and Slack
        pertAlgorithms.calculateEarliestStartAndFinish(graph);
        pertAlgorithms.calculateLatestStartAndFinish(graph);
        pertAlgorithms.calculateSlack(graph);

        // Test: No activities with zero slack
        pertAlgorithms.criticalPath(graph);

        // Validate: No critical path should exist
        for (PertVertex<String, Integer> vertex : graph.vertices()) {
            assertTrue(vertex.getSlack() > 0, "Activity " + vertex.getElement() + " should have slack > 0");
        }
    }

    @Test
    public void testTotalProjectDuration() {
        // Setup: Create a simple graph with a known total project duration
        PertVertex<String, Integer> A = new PertVertex<>("A", 10);
        PertVertex<String, Integer> B = new PertVertex<>("B", 5);
        PertVertex<String, Integer> C = new PertVertex<>("C", 15);

        graph.addVertex(A.getElement());
        graph.addVertex(B.getElement());
        graph.addVertex(C.getElement());

        graph.addEdge(A.getElement(), B.getElement(), 5);
        graph.addEdge(B.getElement(), C.getElement(), 5);

        // Calculate Earliest Start and Finish, Latest Start and Finish, and Slack
        pertAlgorithms.calculateEarliestStartAndFinish(graph);
        pertAlgorithms.calculateLatestStartAndFinish(graph);
        pertAlgorithms.calculateSlack(graph);

        // Test: Verify the total project duration
        pertAlgorithms.criticalPath(graph);

        int totalDuration = 0;
        for (PertVertex<String, Integer> vertex : graph.vertices()) {
            totalDuration = Math.max(totalDuration, vertex.getEarliestFinishTime());
        }
        assertEquals(30, totalDuration);  // Total project duration should be 30
    }
}
