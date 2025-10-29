package org.example.esinf.usei19;

import org.example.structures.graph.Graph;
import org.example.structures.graph.map.MapGraph;
import org.example.structures.graph.pert.PertGraph;
import org.example.structures.graph.pert.PertVertex;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.example.esinf.usei17.ImportActivitiesGraph.importPertCpmGraph;
import static org.example.structures.graph.pert.PertAlgorithms.topologicalSortKahn;
import static org.junit.jupiter.api.Assertions.*;

public class TopologicalSortTest {

    private final String SMALL_FILE = "src/test/java/org/example/esinf/usei17/small_project.csv";
    private final String BIG_FILE = "src/test/java/org/example/esinf/usei17/large_project.csv";

    @Test
    @DisplayName("Test Small Graph Topological Sort")
    public void testSmallGraphTopologicalSort() {
        // Import graph
        PertGraph<PertVertex<String, Integer>, Integer> graph = importPertCpmGraph(SMALL_FILE);

        assertNotNull(graph, "The imported graph should not be null");

        // Perform topological sort
        var sortedOrder = topologicalSortKahn(graph);

        assertNotNull(sortedOrder, "Topological sort result should not be null");

        System.out.println("===== Topological Sort of Small Graph =====");
        System.out.println(sortedOrder);

        // Verify topological order by checking edge constraints
        for (PertVertex<String, Integer> vertex : graph.vertices()) {
            // Check all adjacent vertices
            for (PertVertex<String, Integer> adj : graph.adjVertices(vertex)) {
                /* Check if the vertex appears before its adjacent vertex, if so,
                   the topological order is wrong*/
                assertTrue(sortedOrder.indexOf(vertex) < sortedOrder.indexOf(adj),
                        "Topological order is violated: " + vertex + " appears after " + adj);
            }
        }
    }

    @Test
    @DisplayName("Test Graph with a Cycle Detection")
    public void testCycleDetection() {
        // Import graph
        PertGraph<PertVertex<String, Integer>, Integer> graph = importPertCpmGraph(SMALL_FILE);

        assertNotNull(graph, "The imported graph should not be null");

        // Create a cycle in the graph by adding an edge from the last vertex to the first vertex
        PertVertex<String, Integer> lastVertex = null;
        for (PertVertex<String, Integer> vertex : graph.vertices()) {
            lastVertex = vertex;
        }
        // Add an edge from the last vertex to the first vertex
        graph.addEdge(lastVertex, graph.vertices().iterator().next(), 1);

        // Assert thath the graph has a cycle, it should return the exception.
        assertThrows(RuntimeException.class, () -> topologicalSortKahn(graph));
    }



    @Test
    @DisplayName("Test Large Graph Topological Sort")
    public void testLargeGraphTopologicalSort() {
        // Import large graph
        PertGraph<PertVertex<String, Integer>, Integer> graph = importPertCpmGraph(BIG_FILE);

        assertNotNull(graph, "The imported graph should not be null");

        // Perform topological sort
        var sortedOrder = topologicalSortKahn(graph);

        assertNotNull(sortedOrder, "Topological sort result should not be null");

        System.out.println("===== Topological Sort of Large Graph =====");
        System.out.println("Number of vertices sorted: " + sortedOrder.size());

        // Verify topological order by checking edge constraints
        for (PertVertex<String, Integer> vertex : graph.vertices()) {
            for (PertVertex<String, Integer> adj : graph.adjVertices(vertex)) {
                assertTrue(sortedOrder.indexOf(vertex) < sortedOrder.indexOf(adj),
                        "Topological order is violated: " + vertex + " appears after " + adj);
            }
        }
    }
}
