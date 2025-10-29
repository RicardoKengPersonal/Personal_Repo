package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AgendaControllerTest {
    private AgendaController controller;
    private Task task;
    private Team team;
    private Vehicle vehicle;

    @BeforeEach
    void setUp() {
        controller = new AgendaController();
        task = new Task("Task1 Description", "Task1", new GreenSpace("GreenSpace1", "Street1", GreenSpaceType.GARDEN, "email1@mail.com", 100), 10, TaskUrgencyDegree.HIGH);
        team = new Team(new ArrayList<Collaborator>(), new ArrayList<Skill>());
        vehicle = new Vehicle("11-AA-11", "Toyota", "Corolla", "Sedan", 1200, 1500, 15000, "01-01-2022", "01-01-2020", 5000);
    }

    @Test
    void testAddAgendaEntry() {
        assertTrue(controller.addAgendaEntry(task, "12-12-2024"));
        assertEquals(4, controller.getActiveAgendaEntries().size());
    }

    @Test
    void testCancelTask() {
        controller.addAgendaEntry(task, "12-12-2024");
        controller.cancelTask(task);
        assertEquals(TaskStatus.CANCELLED, task.getTaskStatus());
    }

    @Test
    void testPostponeAgendaEntry() {
        controller.addAgendaEntry(task, "01-12-2024");
        AgendaEntry agendaEntry = controller.getActiveAgendaEntries().get(0);
        assertTrue(controller.postponeAgendaEntry(agendaEntry, "12-12-2026"));
        assertEquals("12-12-2026", agendaEntry.getDate());
    }
}