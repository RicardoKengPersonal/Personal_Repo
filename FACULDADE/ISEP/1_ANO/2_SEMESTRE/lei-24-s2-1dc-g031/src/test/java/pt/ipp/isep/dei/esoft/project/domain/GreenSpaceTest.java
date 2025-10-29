package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;

import static org.junit.jupiter.api.Assertions.*;

class GreenSpaceTest {
    private GreenSpace greenSpace;

    @BeforeEach
    void setUp() {
        greenSpace = new GreenSpace("GreenSpace1", "Street1", GreenSpaceType.GARDEN, "email1@mail.com", 100);
    }

    @Test
    void testToString() {
        String expected = "GreenSpace1 - Street1 - GARDEN - 100";
        String actual = greenSpace.toString();
        assertEquals(expected, actual);
    }
}