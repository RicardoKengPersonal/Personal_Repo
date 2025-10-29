package org.example.esinf.usei20;

import org.example.esinf.usei17.ImportActivitiesGraph;
import org.example.structures.graph.Edge;
import org.example.structures.graph.pert.PertAlgorithms;
import org.example.structures.graph.pert.PertGraph;
import org.example.structures.graph.pert.PertVertex;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class USEI20Test {

    private final String SMALL_FILE = "src/test/java/org/example/esinf/usei17/small_project.csv";

    @Test
    @DisplayName("Test Small Project")
    public void testSmallGraph() {

        PertGraph<PertVertex<String, Integer>, Integer> graph = ImportActivitiesGraph.importPertCpmGraph(SMALL_FILE);

        System.out.println("===== Small Project =====");

        System.out.println();
        System.out.println("===== Before Calculate Earliest Start/Finish ======");
        for (Object vertex : graph.vertices()) {
            System.out.println(vertex);
        }

        PertAlgorithms.calculateEarliestStartAndFinish(graph);
        PertAlgorithms.calculateLatestStartAndFinish(graph);

        System.out.println();
        System.out.println("===== After Calculate Earliest Start/Finish ======");
        for (Object vertex : graph.vertices()) {
            System.out.println(vertex);
        }
    }

    @Test
    @DisplayName("Test Calculate Earliest Start and Earliest Finish Times")
    public void testCalculateESEF() {

        // Create vertices
        PertVertex<String, Integer> start = new PertVertex<>("start", "Start", new HashMap<>(), 0, 0);
        PertVertex<String, Integer> finish = new PertVertex<>("finish", "Finish", new HashMap<>(), 0, 0);

        PertVertex<String, Integer> vertexA = new PertVertex<>("A", "Activity A", new HashMap<>(), 0, 20);
        PertVertex<String, Integer> vertexB = new PertVertex<>("B", "Activity B", new HashMap<>(), 0, 50);
        PertVertex<String, Integer> vertexC = new PertVertex<>("C", "Activity C", new HashMap<>(), 0, 25);
        PertVertex<String, Integer> vertexD = new PertVertex<>("D", "Activity D", new HashMap<>(), 0, 15);
        PertVertex<String, Integer> vertexE = new PertVertex<>("E", "Activity E", new HashMap<>(), 0, 60);

        // Create edges
        start.addAdjVert("START", new Edge<>(start.getElement(), vertexA.getElement(), 1));
        start.addAdjVert("START", new Edge<>(start.getElement(), vertexB.getElement(), 1));

        vertexA.addAdjVert("B", new Edge<>(vertexA.getElement(), vertexC.getElement(), 1));
        vertexA.addAdjVert("E", new Edge<>(vertexA.getElement(), vertexE.getElement(), 1));

        vertexB.addAdjVert("FINISH", new Edge<>(vertexB.getElement(), finish.getElement(), 1));

        vertexC.addAdjVert("D", new Edge<>(vertexC.getElement(), vertexD.getElement(), 1));

        vertexD.addAdjVert("END", new Edge<>(vertexD.getElement(), finish.getElement(), 1));

        vertexE.addAdjVert("FINISH", new Edge<>(vertexE.getElement(), finish.getElement(), 1));

        //Create graph
        PertGraph<PertVertex<String, Integer>, Integer> graph = new PertGraph<>(true);
        graph.addVertex(start);
        graph.addVertex(finish);
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addVertex(vertexC);
        graph.addVertex(vertexD);
        graph.addVertex(vertexE);

        // Add edges to graph
        graph.addEdge(start, vertexA, 1);
        graph.addEdge(start, vertexB, 1);
        graph.addEdge(vertexA, vertexC, 1);
        graph.addEdge(vertexA, vertexE, 1);
        graph.addEdge(vertexB, finish, 1);
        graph.addEdge(vertexC, vertexD, 1);
        graph.addEdge(vertexD, finish, 1);
        graph.addEdge(vertexE, finish, 1);

        // Calculate earliest start and finish times
        PertAlgorithms.calculateEarliestStartAndFinish(graph);

        // Check if the earliest start and finish times are correct

        // Vertex A
        Integer expectedEsVertexA = 0;
        Integer expectedEfVertexA = 20;

        Integer resultEsVertexA = vertexA.getEarliestStartTime();
        Integer resultEfVertexA = vertexA.getEarliestFinishTime();

        assertEquals(expectedEsVertexA, resultEsVertexA);
        assertEquals(expectedEfVertexA, resultEfVertexA);

        // Vertex B
        Integer expectedEsVertexB = 0;
        Integer expectedEfVertexB = 50;

        Integer resultEsVertexB = vertexB.getEarliestStartTime();
        Integer resultEfVertexB = vertexB.getEarliestFinishTime();

        assertEquals(expectedEsVertexB, resultEsVertexB);
        assertEquals(expectedEfVertexB, resultEfVertexB);

        // Vertex C
        Integer expectedEsVertexC = 20;
        Integer expectedEfVertexC = 45;

        Integer resultEsVertexC = vertexC.getEarliestStartTime();
        Integer resultEfVertexC = vertexC.getEarliestFinishTime();

        assertEquals(expectedEsVertexC, resultEsVertexC);
        assertEquals(expectedEfVertexC, resultEfVertexC);

        // Vertex D
        Integer expectedEsVertexD = 45;
        Integer expectedEfVertexD = 60;

        Integer resultEsVertexD = vertexD.getEarliestStartTime();
        Integer resultEfVertexD = vertexD.getEarliestFinishTime();

        assertEquals(expectedEsVertexD, resultEsVertexD);
        assertEquals(expectedEfVertexD, resultEfVertexD);

        // Vertex E
        Integer expectedEsVertexE = 20;
        Integer expectedEfVertexE = 80;

        Integer resultEsVertexE = vertexE.getEarliestStartTime();
        Integer resultEfVertexE = vertexE.getEarliestFinishTime();

        assertEquals(expectedEsVertexE, resultEsVertexE);
        assertEquals(expectedEfVertexE, resultEfVertexE);
    }

    @Test
    @DisplayName("Test Calculate Latest Start and Earliest Finish Times")
    public void testCalculateLSLF() {

        // Create vertices
        PertVertex<String, Integer> start = new PertVertex<>("start", "Start", new HashMap<>(), 0, 0);
        PertVertex<String, Integer> finish = new PertVertex<>("finish", "Finish", new HashMap<>(), 0, 0);

        PertVertex<String, Integer> vertexA = new PertVertex<>("A", "Activity A", new HashMap<>(), 0, 20);
        PertVertex<String, Integer> vertexB = new PertVertex<>("B", "Activity B", new HashMap<>(), 0, 50);
        PertVertex<String, Integer> vertexC = new PertVertex<>("C", "Activity C", new HashMap<>(), 0, 25);
        PertVertex<String, Integer> vertexD = new PertVertex<>("D", "Activity D", new HashMap<>(), 0, 15);
        PertVertex<String, Integer> vertexE = new PertVertex<>("E", "Activity E", new HashMap<>(), 0, 60);

        // Create edges
        start.addAdjVert("START", new Edge<>(start.getElement(), vertexA.getElement(), 1));
        start.addAdjVert("START", new Edge<>(start.getElement(), vertexB.getElement(), 1));

        vertexA.addAdjVert("B", new Edge<>(vertexA.getElement(), vertexC.getElement(), 1));
        vertexA.addAdjVert("E", new Edge<>(vertexA.getElement(), vertexE.getElement(), 1));

        vertexB.addAdjVert("FINISH", new Edge<>(vertexB.getElement(), finish.getElement(), 1));

        vertexC.addAdjVert("D", new Edge<>(vertexC.getElement(), vertexD.getElement(), 1));

        vertexD.addAdjVert("END", new Edge<>(vertexD.getElement(), finish.getElement(), 1));

        vertexE.addAdjVert("FINISH", new Edge<>(vertexE.getElement(), finish.getElement(), 1));

        // Create graph
        PertGraph<PertVertex<String, Integer>, Integer> graph = new PertGraph<>(true);
        graph.addVertex(start);
        graph.addVertex(finish);
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addVertex(vertexC);
        graph.addVertex(vertexD);
        graph.addVertex(vertexE);

        // Add edges to graph
        graph.addEdge(start, vertexA, 1);
        graph.addEdge(start, vertexB, 1);
        graph.addEdge(vertexA, vertexC, 1);
        graph.addEdge(vertexA, vertexE, 1);
        graph.addEdge(vertexB, finish, 1);
        graph.addEdge(vertexC, vertexD, 1);
        graph.addEdge(vertexD, finish, 1);
        graph.addEdge(vertexE, finish, 1);

        // Calculate earliest start and finish times
        PertAlgorithms.calculateEarliestStartAndFinish(graph);
        PertAlgorithms.calculateLatestStartAndFinish(graph);

        // Check if the latest start and finish times are correct

        // Vertex A
        Integer expectedLsVertexA = 0;
        Integer expectedLfVertexA = 20;

        Integer resultLsVertexA = vertexA.getLatestStartTime();
        Integer resultLfVertexA = vertexA.getLatestFinishTime();

        assertEquals(expectedLsVertexA, resultLsVertexA);
        assertEquals(expectedLfVertexA, resultLfVertexA);

        // Vertex B
        Integer expectedLsVertexB = 30;
        Integer expectedLfVertexB = 80;

        Integer resultLsVertexB = vertexB.getLatestStartTime();
        Integer resultLfVertexB = vertexB.getLatestFinishTime();

        assertEquals(expectedLsVertexB, resultLsVertexB);
        assertEquals(expectedLfVertexB, resultLfVertexB);

        // Vertex C
        Integer expectedLsVertexC = 40;
        Integer expectedLfVertexC = 65;

        Integer resultLsVertexC = vertexC.getLatestStartTime();
        Integer resultLfVertexC = vertexC.getLatestFinishTime();

        assertEquals(expectedLsVertexC, resultLsVertexC);
        assertEquals(expectedLfVertexC, resultLfVertexC);

        // Vertex D
        Integer expectedLsVertexD = 65;
        Integer expectedLfVertexD = 80;

        Integer resultLsVertexD = vertexD.getLatestStartTime();
        Integer resultLfVertexD = vertexD.getLatestFinishTime();

        assertEquals(expectedLsVertexD, resultLsVertexD);
        assertEquals(expectedLfVertexD, resultLfVertexD);

        // Vertex E
        Integer expectedLsVertexE = 20;
        Integer expectedLfVertexE = 80;

        Integer resultLsVertexE = vertexE.getLatestStartTime();
        Integer resultLfVertexE = vertexE.getLatestFinishTime();

        assertEquals(expectedLsVertexE, resultLsVertexE);
        assertEquals(expectedLfVertexE, resultLfVertexE);
    }
}
