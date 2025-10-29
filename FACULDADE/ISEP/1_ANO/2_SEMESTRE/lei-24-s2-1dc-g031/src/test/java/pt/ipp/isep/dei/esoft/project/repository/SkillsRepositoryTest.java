package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The SkillsRepositoryTest class contains unit tests for the SkillsRepository class.
 */
class SkillsRepositoryTest {

    /**
     * Unit test for adding a valid skill to the SkillsRepository.
     */
    @Test
    void testAddSkill_ValidSkill_Success() {
        // Arrange
        SkillsRepository skillsRepository = new SkillsRepository();
        String skillName = "Java";

        // Act
        skillsRepository.addSkill(skillName);

        // Assert
        assertEquals(1, skillsRepository.getSkillsArrayList().size());
        Skill addedSkill = skillsRepository.getSkillsArrayList().get(0);
        assertEquals(skillName, addedSkill.getNameOfTheSkill());
    }

    /**
     * Unit test for adding a skill with an empty name to the SkillsRepository.
     */
    @Test
    void testAddSkill_EmptyName_Fails() {
        // Arrange
        SkillsRepository skillsRepository = new SkillsRepository();
        String skillName = "";

        // Act
        skillsRepository.addSkill(skillName);

        // Assert
        assertEquals(0, skillsRepository.getSkillsArrayList().size()); // Skill not added
    }

    /**
     * Unit test for adding a skill with invalid characters to the SkillsRepository.
     */
    @Test
    void testAddSkill_InvalidCharacters_Fails() {
        // Arrange
        SkillsRepository skillsRepository = new SkillsRepository();
        String skillName = "Java123";

        // Act
        skillsRepository.addSkill(skillName);

        // Assert
        assertEquals(0, skillsRepository.getSkillsArrayList().size()); // Skill not added
    }

    /**
     * Unit test for adding a duplicate skill to the SkillsRepository.
     */
    @Test
    void testAddSkill_DuplicateSkill_Fails() {
        // Arrange
        SkillsRepository skillsRepository = new SkillsRepository();
        String skillName = "testName";

        // Act
        skillsRepository.addSkill(skillName);
        skillsRepository.addSkill(skillName);

        // Assert
        assertEquals(1, skillsRepository.getSkillsArrayList().size());
    }
}