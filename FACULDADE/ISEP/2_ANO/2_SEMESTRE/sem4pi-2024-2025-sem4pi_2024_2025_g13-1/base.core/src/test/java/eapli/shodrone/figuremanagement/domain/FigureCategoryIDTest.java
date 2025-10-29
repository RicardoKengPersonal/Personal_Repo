package eapli.shodrone.figuremanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FigureCategoryIDTest {

    @Test
    void testValueOfAndToInteger() {
        FigureCategoryID id = FigureCategoryID.valueOf(5);
        assertEquals(5, id.toInteger());
    }

    @Test
    void testEqualsAndHashCode() {
        FigureCategoryID id1 = FigureCategoryID.valueOf(10);
        FigureCategoryID id2 = FigureCategoryID.valueOf(10);
        FigureCategoryID id3 = FigureCategoryID.valueOf(20);

        assertEquals(id1, id2);
        assertNotEquals(id1, id3);
        assertEquals(id1.hashCode(), id2.hashCode());
    }

    @Test
    void testCompareTo() {
        FigureCategoryID id1 = FigureCategoryID.valueOf(5);
        FigureCategoryID id2 = FigureCategoryID.valueOf(10);

        assertTrue(id1.compareTo(id2) < 0);
        assertTrue(id2.compareTo(id1) > 0);
        assertEquals(0, id1.compareTo(FigureCategoryID.valueOf(5)));
    }

    @Test
    void testToString() {
        FigureCategoryID id = FigureCategoryID.valueOf(42);
        assertEquals("42", id.toString());
    }
}
