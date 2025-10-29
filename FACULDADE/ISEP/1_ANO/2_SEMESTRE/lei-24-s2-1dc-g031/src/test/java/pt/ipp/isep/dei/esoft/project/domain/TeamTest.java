package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The TeamTest class contains unit tests for the Team class.
 */
class TeamTest {

    /**
     * Unit test for the constructor.
     * Tests if the constructor initializes a Team object with the given collaborators and skills.
     */
    @Test
    void testConstructor_ValidLists_CreatesTeam() {
        // Arrange
        ArrayList<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(new Collaborator("John Doe", "3031739", "255331584", "john.doe@email.com", 123456789, "1 Main St", "01-01-2023", "30-12-1990", DocumentType.PASSPORT, new Job("Software Engineer")));

        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(new Skill("Java"));

        // Act
        Team team = new Team(collaborators, skills);

        // Assert
        assertEquals(collaborators, team.getCollaborators());
        assertEquals(skills, team.getSkills());
    }

    /**
     * Unit test for the setCollaborators method.
     * Tests if the method successfully changes the collaborators list of the team.
     */
    @Test
    void testSetCollaborators_NewList_ChangesCollaborators() {
        // Arrange
        ArrayList<Collaborator> collaborators1 = new ArrayList<>();
        collaborators1.add(new Collaborator("John Doe", "3031743", "255331584", "john.doe@email.com", 123456789, "1 Main St", "01-01-2023", "30-12-1990", DocumentType.PASSPORT, new Job("Software Engineer")));

        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(new Skill("Java"));

        Team team = new Team(collaborators1, skills);

        ArrayList<Collaborator> collaborators2 = new ArrayList<>();
        collaborators2.add(new Collaborator("John Doe", "3031712", "255331584", "john.doe@email.com", 123456789, "1 Main St", "01-01-2023", "30-12-1990", DocumentType.PASSPORT, new Job("Software Engineer")));

        // Act
        team.setCollaborators(collaborators2);

        // Assert
        assertEquals(collaborators2, team.getCollaborators());
        assertNotEquals(collaborators1, team.getCollaborators()); // Ensure original list not modified
    }

    /**
     * Unit test for the setSkills method.
     * Tests if the method successfully changes the skills list of the team.
     */
    @Test
    void testSetSkills_NewList_ChangesSkills() {
        // Arrange
        ArrayList<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(new Collaborator("John Doe", "3031730", "255331584", "john.doe@email.com", 123456789, "1 Main St", "01-01-2023", "30-12-1990", DocumentType.PASSPORT, new Job("Software Engineer")));

        ArrayList<Skill> skills1 = new ArrayList<>();
        skills1.add(new Skill("Java"));

        Team team = new Team(collaborators, skills1);

        ArrayList<Skill> skills2 = new ArrayList<>();
        skills2.add(new Skill("Python"));

        // Act
        team.setSkills(skills2);

        // Assert
        assertEquals(skills2, team.getSkills());
        assertNotEquals(skills1, team.getSkills()); // Ensure original list not modified
    }
}
