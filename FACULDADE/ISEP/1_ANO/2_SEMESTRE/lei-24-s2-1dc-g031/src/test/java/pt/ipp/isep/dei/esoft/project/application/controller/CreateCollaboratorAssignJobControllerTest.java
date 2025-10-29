package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CreateCollaboratorAssignJobControllerTest {
    private CreateCollaboratorAssignJobController controller;
    private Job job;

    @BeforeEach
    void setUp() {
        job = new Job("Job");
        controller = new CreateCollaboratorAssignJobController();
        CreateJobController jobController = new CreateJobController();
        jobController.createJob("Job");
    }

    @Test
    void testGetCollaboratorsList() {
        controller.addCollaborator("John Doe", "1234567", "987654321", "johndoe@mail.com", 123456789, "Street1", "01-01-2023", "01-01-1980", DocumentType.PASSPORT, job);
        ArrayList<Collaborator> collaborators = controller.getCollaboratorsList();
        assertEquals("John Doe", collaborators.get(0).getName());
    }

    @Test
    void testAddCollaborator() {
        assertTrue(controller.addCollaborator("John Doe", "1234567", "987654321", "johndoe@mail.com", 123456789, "Street1", "01-01-2023", "01-01-1980", DocumentType.PASSPORT, job));
        assertEquals(2, controller.getCollaboratorsList().size());

    }

    @Test
    void testGetDocTypesList() {
        ArrayList<DocumentType> docTypes = controller.getDocTypesList();
        assertTrue(docTypes.contains(DocumentType.PASSPORT));
    }
}