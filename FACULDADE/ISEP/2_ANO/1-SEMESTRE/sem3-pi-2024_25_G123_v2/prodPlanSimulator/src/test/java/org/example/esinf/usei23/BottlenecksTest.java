package org.example.esinf.usei23;

import org.example.esinf.usei17.ImportActivitiesGraph;
import org.example.structures.graph.pert.PertAlgorithms;
import org.example.structures.graph.pert.PertGraph;
import org.example.structures.graph.pert.PertVertex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Map.entry;
import static org.example.esinf.usei17.ImportActivitiesGraph.importPertCpmGraph;
import static org.example.structures.graph.pert.PertAlgorithms.calculateDependentCounts;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BottlenecksTest {
    private final String SMALL_FILE = "src/test/java/org/example/esinf/usei17/small_project.csv";
    private final String LARGE_FILE = "src/test/java/org/example/esinf/usei17/large_project.csv";

    @Test
    @DisplayName("Test Identifying Bottleneck Activities (SMALL_FILE)")
    public void testIdentifyBottlenecksWithAdjVertices_SmallFile() {
        System.out.println("\nTest Identifying Bottleneck Activities (SMALL_FILE)");
        PertGraph<PertVertex<String, Integer>, Integer> graph = importPertCpmGraph(SMALL_FILE);

        assertNotNull(graph, "The graph should not be null");

        // Find START and END vertices
        PertVertex<String, Integer> start = null;
        PertVertex<String, Integer> end = null;

        for (PertVertex<String, Integer> vertex : graph.vertices()) {
            if (vertex.getElement().equals("START")) {
                start = vertex;
            } else if (vertex.getElement().equals("END")) {
                end = vertex;
            }
        }

        assertNotNull(start, "The START vertex should exist in the graph");
        assertNotNull(end, "The END vertex should exist in the graph");

        // Calculate dependent activity counts
        var dependentCounts = calculateDependentCounts(graph);

        // Analyze bottlenecks
        AtomicInteger count = new AtomicInteger();

        // Print bottlenecks
        dependentCounts.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) // Sort by number of dependents
                .limit(5) // Top 5 bottlenecks
                .forEach(entry -> {
                    count.incrementAndGet();
                    System.out.println(
                            count + "º Bottleneck (Dependencies): " +
                                    entry.getKey().getElement() + " -> " +
                                    entry.getKey().numAdjVertices() +
                                    " dependents"
                    );
                });
    }

    @Test
    @DisplayName("Test Identifying Bottleneck Activities (LARGE_FILE)")
    public void testIdentifyBottlenecksWithAdjVertices_LargeFile() {
        System.out.println("\nTest Identifying Bottleneck Activities (LARGE_FILE)");
        PertGraph<PertVertex<String, Integer>, Integer> graph = importPertCpmGraph(LARGE_FILE);

        assertNotNull(graph, "The graph should not be null");

        // Find START and END vertices
        PertVertex<String, Integer> start = null;
        PertVertex<String, Integer> end = null;

        for (PertVertex<String, Integer> vertex : graph.vertices()) {
            if (vertex.getElement().equals("START")) {
                start = vertex;
            } else if (vertex.getElement().equals("END")) {
                end = vertex;
            }
        }

        assertNotNull(start, "The START vertex should exist in the graph");
        assertNotNull(end, "The END vertex should exist in the graph");

        // Calculate dependent activity counts
        var dependentCounts = calculateDependentCounts(graph);

        // Analyze bottlenecks
        AtomicInteger count = new AtomicInteger();

        // Print bottlenecks
        dependentCounts.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) // Sort by number of dependents
                .limit(5) // Top 5 bottlenecks
                .forEach(entry -> {
                    count.incrementAndGet();
                    System.out.println(
                            count + "º Bottleneck (Dependencies): " +
                                    entry.getKey().getElement() + " -> " +
                                    entry.getKey().numAdjVertices() +
                                    " dependents"
                    );
                });
    }

}
