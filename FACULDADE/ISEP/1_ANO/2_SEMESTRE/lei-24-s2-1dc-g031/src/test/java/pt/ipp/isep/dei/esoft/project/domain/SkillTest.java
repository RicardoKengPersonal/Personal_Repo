package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The SkillTest class contains unit tests for the Skill class.
 */
class SkillTest {

    /**
     * Unit test for the toString method.
     * Tests if the method returns the correct string representation of a Skill object.
     */
    @Test
    void testToString_ValidSkill_CorrectOutput() {
        // Arrange
        Skill skill = new Skill("Java");

        // Act
        String expectedOutput = "Java";

        // Assert
        assertEquals(expectedOutput, skill.toString());
    }
}
