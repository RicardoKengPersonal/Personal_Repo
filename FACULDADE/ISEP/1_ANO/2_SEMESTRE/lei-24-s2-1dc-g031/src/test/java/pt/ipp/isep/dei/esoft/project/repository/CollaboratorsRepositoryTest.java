package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.DocumentType;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The CollaboratorsRepositoryTest class contains unit tests for the CollaboratorsRepository class.
 */
class CollaboratorsRepositoryTest {

    /**
     * Unit test for adding a collaborator with valid data.
     */
    @Test
    void testAddCollaborator_ValidData_Success() {
        // Arrange
        CollaboratorsRepository collaboratorsRepository = new CollaboratorsRepository();
        String name = "John Doe";
        String documentIdentificationNumber = "9283746";
        String taxPayerNumber = "187044040";
        String email = "john.doe@email.com";
        int mobileNumber = 123456789;
        String address = "1 Main St";
        String admissionDate = "01-01-2023";
        String birthDate = "30-12-1990";
        DocumentType documentType = DocumentType.PASSPORT;
        Job job = new Job("Software Engineer");

        // Act
        boolean success = collaboratorsRepository.addCollaborator(name, documentIdentificationNumber, taxPayerNumber, email, mobileNumber, address, admissionDate, birthDate, documentType, job);

        // Assert
        assertTrue(success);
        assertEquals(1, collaboratorsRepository.getCollaboratorsArrayList().size());
        Collaborator addedCollaborator = collaboratorsRepository.getCollaboratorsArrayList().get(0);
        assertEquals(name, addedCollaborator.getName());
        // Assert other collaborator attributes using getters (omitted for brevity)
    }

    /**
     * Unit test for adding a collaborator with an invalid name.
     */
    @Test
    void testAddCollaborator_InvalidName_Fails() {
        // Arrange
        CollaboratorsRepository collaboratorsRepository = new CollaboratorsRepository();
        String name = "John Doe123"; // Invalid name with numbers

        // Act

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            collaboratorsRepository.addCollaborator(name, "303674001ZY7", "227159470", "john.doe@email.com", 123456789, "1 Main St", "01-01-2023", "30-12-1990", DocumentType.PASSPORT, new Job("Software Engineer"));
        });
        assertEquals(0, collaboratorsRepository.getCollaboratorsArrayList().size());
    }

    /**
     * Unit test for adding a collaborator with an invalid phone number.
     */
    @Test
    void testAddCollaborator_InvalidPhone_Fails() {
        // Arrange
        CollaboratorsRepository collaboratorsRepository = new CollaboratorsRepository();
        int mobileNumber = 1234567; // Invalid phone number (too short)

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            collaboratorsRepository.addCollaborator("John Doe", "303674001ZY7", "227159470", "john.doe@email.com", mobileNumber, "1 Main St", "01-01-2023", "30-12-1990", DocumentType.PASSPORT, new Job("Software Engineer"));
        });
        assertEquals(0, collaboratorsRepository.getCollaboratorsArrayList().size());
    }

    /**
     * Unit test for adding a collaborator with an invalid date.
     */
    @Test
    void testAddCollaborator_InvalidDate_Fails() {
        // Arrange
        CollaboratorsRepository collaboratorsRepository = new CollaboratorsRepository();
        String admissionDate = "invalid-date"; // Invalid admission date

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            collaboratorsRepository.addCollaborator("John Doe","3031731", "255331584", "john.doe@email.com", 123456789, "1 Main St", admissionDate, "30-12-1990", DocumentType.PASSPORT, new Job("Software Engineer"));
        });
        assertEquals(0, collaboratorsRepository.getCollaboratorsArrayList().size());
    }

    /**
     * Unit test for adding a skill to a collaborator.
     */
    @Test
    void testAddSkill_ValidSkill_Success() {
        // Arrange
        CollaboratorsRepository collaboratorsRepository = new CollaboratorsRepository();
        Collaborator collaborator = new Collaborator("John Doe", "9283746", "217594832", "john.doe@email.com", 123456789, "1 Main St", "01-01-2023", "30-12-1990", DocumentType.PASSPORT, new Job("Software Engineer"));
        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(new Skill("Java"));

        // Act
        boolean success = collaboratorsRepository.addSkill(collaborator, skills);

        // Assert
        assertTrue(collaborator.skillAlreadyAssigned(skills.get(0))); // Check if skill was added to collaborator
        assertTrue(success);
    }

    /**
     * Unit test for adding a duplicate skill to a collaborator.
     */
    @Test
    void testAddSkill_DuplicateSkill_Fails() {
        // Arrange
        CollaboratorsRepository collaboratorsRepository = new CollaboratorsRepository();
        Collaborator collaborator = new Collaborator("John Doe", "9283746", "255331584", "john.doe@email.com", 123456789, "1 Main St", "01-01-2023", "30-12-1990", DocumentType.PASSPORT, new Job("Software Engineer"));
        Skill existingSkill = new Skill("Java");
        collaborator.addSkillToCollaboratorSkills(existingSkill);
        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(existingSkill);

        // Act
        boolean success = collaboratorsRepository.addSkill(collaborator, skills);

        // Assert
        assertTrue(collaborator.skillAlreadyAssigned(skills.get(0))); // Check if skill wasn't added again
        assertFalse(success);
    }
}
