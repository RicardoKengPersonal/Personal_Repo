package org.example.ProductionSimulator;

import org.example.Domain.Article;
import org.example.Domain.OperationScheduled;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Simulator class.
 */
class SimulatorFlowDependencyTest {
    private Simulator simulator;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Sets up the Simulator instance for testing.
     */
    @BeforeEach
    void setUp() {
        simulator = new Simulator();
        System.setOut(new PrintStream(outputStreamCaptor)); // Redirect System.out to capture output
    }

    /**
     * Tests the production of flow dependency listing for a single article and single flow.
     */
    @Test
    void testProduceFlowDependencyListing_SingleArticle_SingleFlow() {
        Article article = new Article(1, "A1", 5, List.of("Op1", "Op2"));
        OperationScheduled op1 = new OperationScheduled(1, "Op1", "WS1", 0, 10, 1, "A1");
        OperationScheduled op2 = new OperationScheduled(2, "Op2", "WS2", 10, 20, 2, "A1");

        simulator.setArticleList(List.of(article));
        simulator.getOperationsScheduled().addAll(List.of(op1, op2));

        simulator.produceFlowDependencyListing();

        String expectedOutput = "WS1 : [(WS2,1)]";
        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }

    /**
     * Tests the production of flow dependency listing for multiple articles and multiple flows.
     */
    @Test
    void testProduceFlowDependencyListing_MultipleArticles_MultipleFlows() {
        Article article1 = new Article(1, "A1", 3, List.of("Op1", "Op2"));
        Article article2 = new Article(2, "A2", 4, List.of("Op1", "Op2"));

        OperationScheduled op1 = new OperationScheduled(1, "Op1", "WS1", 0, 10, 1, "A1");
        OperationScheduled op2 = new OperationScheduled(2, "Op2", "WS2", 10, 20, 2, "A1");
        OperationScheduled op3 = new OperationScheduled(3, "Op1", "WS2", 0, 10, 1, "A2");
        OperationScheduled op4 = new OperationScheduled(4, "Op2", "WS3", 10, 20, 2, "A2");

        simulator.setArticleList(List.of(article1, article2));
        simulator.getOperationsScheduled().addAll(List.of(op1, op2, op3, op4));

        simulator.produceFlowDependencyListing();

        String expectedOutput = """
        WS1 : [(WS2,1)]\s
        WS2 : [(WS3,1)]
        """;
        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }

    /**
     * Tests the production of flow dependency listing for a single article with multiple flows.
     */
    @Test
    void testProduceFlowDependencyListing_NoFlowForSingleOperation() {
        Article article = new Article(1, "A1", 2, List.of("Op1"));
        OperationScheduled op1 = new OperationScheduled(1, "Op1", "WS1", 0, 10, 1, "A1");

        simulator.setArticleList(List.of(article));
        simulator.getOperationsScheduled().add(op1);

        simulator.produceFlowDependencyListing();

        String expectedOutput = "";  // Or the expected representation of no dependencies
        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }

    /**
     * Tests the production of flow dependency listing for multiple transitions in the same flow.
     */
    @Test
    void testProduceFlowDependencyListing_MultipleTransitionsSameFlow() {
        Article article = new Article(1, "A1", 1, List.of("Op1", "Op2", "Op3", "Op4"));

        OperationScheduled op1 = new OperationScheduled(1, "Op1", "WS1", 0, 10, 1, "A1");
        OperationScheduled op2 = new OperationScheduled(2, "Op2", "WS2", 10, 20, 2, "A1");
        OperationScheduled op3 = new OperationScheduled(3, "Op3", "WS1", 20, 30, 3, "A1");
        OperationScheduled op4 = new OperationScheduled(4, "Op4", "WS2", 30, 40, 4, "A1");

        simulator.setArticleList(List.of(article));
        simulator.getOperationsScheduled().addAll(List.of(op1, op2, op3, op4));

        simulator.produceFlowDependencyListing();

        String expectedOutput = """
        WS1 : [(WS2,2)]
        WS2 : [(WS1,1)]
        """;
        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }
}