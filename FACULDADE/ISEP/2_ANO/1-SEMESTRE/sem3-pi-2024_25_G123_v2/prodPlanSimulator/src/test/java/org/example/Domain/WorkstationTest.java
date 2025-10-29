package org.example.Domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class WorkstationTest {
    private Workstation workstation;
    private Article article;

    @BeforeEach
    void setUp() {
        workstation = new Workstation("W1", "CUT", 30);
        article = new Article(1, "A1", 5, Arrays.asList("CUT", "SCREW"));
    }

    @Test
    void testWorkstationConstructorAndGetters() {
        assertEquals("W1", workstation.getIdWorkstation());
        assertEquals("CUT", workstation.getOperation());
        assertEquals(30, workstation.getTime());
        assertTrue(workstation.getQueuedProductionOrders().isEmpty());
    }

    @Test
    void testAddArticleNoAvailableBlocks() {
        // Test adding an article when no available time blocks
        workstation.addArticle(article, "CUT", 10); // Assuming no previous operation ends at 10
        assertTrue(workstation.getQueuedProductionOrders().isEmpty()); // Should still be empty
    }

    @Test
    void testEqualsAndHashCode() {
        Workstation workstation2 = new Workstation("W1", "CUT", 30);
        Workstation workstation3 = new Workstation("W2", "SCREW", 20);

        assertEquals(workstation, workstation2);
        assertNotEquals(workstation, workstation3);
        assertEquals(workstation.hashCode(), workstation2.hashCode());
        assertNotEquals(workstation.hashCode(), workstation3.hashCode());
    }

    @Test
    void testToString() {
        String expectedString = "Workstation{idWorkstation='W1', operation='CUT', time=30}";
        assertEquals(expectedString, workstation.toString());
    }
}
