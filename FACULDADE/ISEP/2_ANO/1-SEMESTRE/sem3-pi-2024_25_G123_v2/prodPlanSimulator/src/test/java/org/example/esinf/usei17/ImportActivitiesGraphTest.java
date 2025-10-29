package org.example.esinf.usei17;

import org.example.structures.graph.Edge;
import org.example.structures.graph.pert.PertGraph;
import org.example.structures.graph.pert.PertVertex;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;


import static org.junit.jupiter.api.Assertions.*;

public class ImportActivitiesGraphTest {

    private final String SMALL_FILE = "src/test/java/org/example/esinf/usei17/small_project.csv";

    private final String BIG_FILE = "src/test/java/org/example/esinf/usei17/large_project.csv";

    // SMALL GRAPH
    @Test
    @DisplayName("Verify small graph is not null and print graph")
    public void testGraphNotNullAndPrint() {

        PertGraph<PertVertex<String, Integer>, Integer> graph = ImportActivitiesGraph.importPertCpmGraph(SMALL_FILE);

        assertNotNull(graph, "The imported graph should not be null");

        System.out.println("Vertex");
        for (PertVertex<String, Integer> vertex : graph.vertices()) {
            System.out.println(vertex);
        }
        System.out.println();
        System.out.println("===============================================");

        for (Object edge : graph.edges()) {
            System.out.println(edge);
        }
        System.out.println();
        System.out.println("===============================================");
    }

    @Test
    @DisplayName("Verify small graph has correct num of vertices")
    public void testNumberOfVerticesSmall() {

        PertGraph<PertVertex<String, Integer>, Integer> graph = ImportActivitiesGraph.importPertCpmGraph(SMALL_FILE);

        Integer expectedNumVertices = 14;

        Integer resultNumVertices = graph.numVertices();

        assertEquals(expectedNumVertices, resultNumVertices);
    }

    @Test
    @DisplayName("Verify edges are correctly added for small graph")
    public void testEdgesSmallGraph() {

        PertGraph<PertVertex<String, Integer>, Integer> graph = ImportActivitiesGraph.importPertCpmGraph(SMALL_FILE);

        Integer expectedNumEdges = 18;

        Integer resultNumEdges = graph.numEdges();

        assertEquals(expectedNumEdges, resultNumEdges);
    }

    // ================================================================================================================

    // BIG GRAPH
    @Test
    @DisplayName("Verify big graph is not null and print graph")
    public void testGraphNotNullAndPrint2() {

        PertGraph<PertVertex<String, Integer>, Integer> graph = ImportActivitiesGraph.importPertCpmGraph(BIG_FILE);

        assertNotNull(graph, "The imported graph should not be null");

        System.out.println("Vertex");
        for (PertVertex vertex : graph.vertices()) {
            System.out.println(vertex);
        }
        System.out.println();
        System.out.println("===============================================");

        System.out.println("Edges");
        for (Edge edge : graph.edges()) {
            System.out.println(edge);
        }
        System.out.println("================================================");
    }

    @Test
    @DisplayName("Verify big graph has correct num of vertices")
    public void testNumberOfVerticesBig() {

        PertGraph<PertVertex<String, Integer>, Integer> graph = ImportActivitiesGraph.importPertCpmGraph(BIG_FILE);

        Integer expectedNumVertices = 1002;

        Integer resultNumVertices = graph.numVertices();

        assertEquals(expectedNumVertices, resultNumVertices);
    }
}
