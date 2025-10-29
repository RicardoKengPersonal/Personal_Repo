package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateJobController;

import static org.junit.jupiter.api.Assertions.*;

class CreateJobControllerTest {
    private CreateJobController controller;

    @BeforeEach
    void setUp() {
        controller = new CreateJobController();
    }

    @Test
    void testCreateJob_AlreadyExists() {
        controller.createJob("Job");
        assertFalse(controller.createJob("Job"));
    }
}