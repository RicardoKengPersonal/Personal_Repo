package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The CollaboratorTest class contains unit tests for the Collaborator class.
 */
class CollaboratorTest {

    /**
     * Unit test for the addSkillToCollaboratorSkills method.
     * Tests if the method successfully adds a skill to the collaborator's skills list.
     */
    @Test
    void testAddSkillToCollaboratorSkills_ValidSkill_Success() {
        // Arrange
        Collaborator collaborator = new Collaborator("John Doe", "3031738", "255331584", "john.doe@email.com", 123456789, "1 Main St", "01-01-2023", "30-12-1990", DocumentType.PASSPORT, new Job("Software Engineer"));
        Skill skill = new Skill("Java");

        // Act
        collaborator.addSkillToCollaboratorSkills(skill);

        // Assert
        assertEquals(1, collaborator.getSkills().size());
        Skill addedSkill = collaborator.getSkills().get(0);
        assertEquals(skill.getNameOfTheSkill(), addedSkill.getNameOfTheSkill());
    }

    /**
     * Unit test for the addSkillToCollaboratorSkills method.
     * Tests if the method fails to add a duplicate skill to the collaborator's skills list.
     */
    @Test
    void testAddSkillToCollaboratorSkills_DuplicateSkill_Fails() {
        // Arrange
        Collaborator collaborator = new Collaborator("John Doe", "3031731", "255331584", "john.doe@email.com", 123456789, "1 Main St", "01-01-2023", "30-12-1990", DocumentType.PASSPORT, new Job("Software Engineer"));
        Skill skill = new Skill("Java");
        collaborator.addSkillToCollaboratorSkills(skill);

        // Act
        collaborator.addSkillToCollaboratorSkills(skill); // Adding the same skill again

        // Assert
        assertEquals(1, collaborator.getSkills().size()); // Only one skill should be added
    }

    @Test
    void testIsAtLeast18YearsOld_ValidDate_True() {
        // Arrange
        String dateString = "30-12-2000";

        // Act
        boolean isAtLeast18YearsOld = Collaborator.isAtLeast18YearsOld(dateString);

        // Assert
        assertTrue(isAtLeast18YearsOld);
    }

    /**
     * Unit test for the skillAlreadyAssigned method.
     * Tests if the method returns true for an existing skill assigned to the collaborator.
     */
    @Test
    void testSkillAlreadyAssigned_ExistingSkill_True() {
        // Arrange
        Collaborator collaborator = new Collaborator("John Doe", "3031735", "255331584", "john.doe@email.com", 123456789, "1 Main St", "01-01-2023", "30-12-1990", DocumentType.PASSPORT, new Job("Software Engineer"));
        Skill skill = new Skill("Java");
        collaborator.addSkillToCollaboratorSkills(skill);

        // Act
        boolean alreadyAssigned = collaborator.skillAlreadyAssigned(skill);

        // Assert
        assertTrue(alreadyAssigned);
    }

    /**
     * Unit test for the skillAlreadyAssigned method.
     * Tests if the method returns false for a non-existing skill assigned to the collaborator.
     */
    @Test
    void testSkillAlreadyAssigned_NonExistingSkill_False() {
        // Arrange
        Collaborator collaborator = new Collaborator("John Doe", "3031333", "255331584", "john.doe@email.com", 123456789, "1 Main St", "01-01-2023", "30-12-1990", DocumentType.PASSPORT, new Job("Software Engineer"));
        Skill skill = new Skill("Python"); // Not added previously

        // Act
        boolean alreadyAssigned = collaborator.skillAlreadyAssigned(skill);

        // Assert
        assertFalse(alreadyAssigned);
    }
}
