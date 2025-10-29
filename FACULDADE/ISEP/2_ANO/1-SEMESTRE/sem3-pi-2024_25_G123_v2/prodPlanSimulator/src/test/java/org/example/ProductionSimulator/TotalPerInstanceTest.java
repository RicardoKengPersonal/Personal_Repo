package org.example.ProductionSimulator;

import org.example.Domain.Article;
import org.example.Domain.OperationScheduled;
import org.example.Domain.Workstation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Simulator class.
 */
public class TotalPerInstanceTest {

    private Simulator simulator;
    private List<Article> articleList;
    private Map<String, Workstation> workstations;
    private List<OperationScheduled> operationsScheduled;

    /**
     * Sets up the Simulator instance for testing.
     */
    @BeforeEach
    public void setUp() {
        // Initialize articles
        Article article1 = new Article(1, "A1", 0, new LinkedList<>(List.of("Operation1", "Operation2", "Operation3")));
        Article article2 = new Article(2, "A2", 0, new LinkedList<>(List.of("Operation3", "Operation2", "Operation1")));
        Article article3 = new Article(3, "A3", 0, new LinkedList<>(List.of("Operation2", "Operation3", "Operation1")));

        articleList = new ArrayList<>(List.of(article1, article2, article3));

        // Initialize workstations
        Workstation workstation1 = new Workstation("W1", "Operation1", 8);
        Workstation workstation2 = new Workstation("W2", "Operation2", 10);
        Workstation workstation3 = new Workstation("W3", "Operation3", 6);
        Workstation workstation4 = new Workstation("W4", "Operation1", 7);

        workstations = new HashMap<>();
        workstations.put(workstation1.getIdWorkstation(), workstation1);
        workstations.put(workstation2.getIdWorkstation(), workstation2);
        workstations.put(workstation3.getIdWorkstation(), workstation3);
        workstations.put(workstation4.getIdWorkstation(), workstation4);

        // Initialize the simulator object
        simulator = new Simulator();
        simulator.setArticleList(articleList);
        simulator.setWorkstations(workstations);

        // Initialize operations scheduled list
        operationsScheduled = new ArrayList<>();
        operationsScheduled.add(new OperationScheduled(1, "Operation1", "W1", 0, 8, 0, "1"));
        operationsScheduled.add(new OperationScheduled(2, "Operation2", "W2", 0, 8, 0, "2"));
        operationsScheduled.add(new OperationScheduled(3, "Operation3", "W3", 0, 8, 0, "3"));
        simulator.getOperationsScheduled().addAll(operationsScheduled);
    }

    /**
     * Tests the display of total time per article instance.
     */
    @Test
    public void testDisplayTotalTimePerArticleType() {
        // Capture the output
        simulator.displayTotalTimePerArticleType();
        // Verify the output manually or use a library to capture and assert the console output
    }

    /**
     * Tests the display of total time per operation.
     */
    @Test
    public void testDisplayTotalTimePerInstanceWithArticleType() {
        // Capture the output
        simulator.displayTotalTimePerInstanceWithArticleType();
        // Verify the output manually or use a library to capture and assert the console output
    }
}