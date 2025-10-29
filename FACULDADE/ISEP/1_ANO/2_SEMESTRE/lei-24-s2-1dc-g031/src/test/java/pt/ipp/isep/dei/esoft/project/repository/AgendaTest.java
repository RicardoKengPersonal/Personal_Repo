package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AgendaTest {
    private Agenda agenda;
    private Task task;
    private Team team;
    private Vehicle vehicle;

    @BeforeEach
    void setUp() {
        task = new Task("Task1 Description", "Task1", new GreenSpace("GreenSpace1", "Street1", GreenSpaceType.GARDEN, "email1@mail.com", 100), 480, TaskUrgencyDegree.LOW);
        team = new Team(new ArrayList<Collaborator>(), new ArrayList<Skill>());
        vehicle = new Vehicle("AA-00-BB", "Opel", "Corsa", "Gasoline", 1000, 1200, 250000, "01-01-2020", "01-01-2023", 10000);
        agenda = new Agenda();
    }

    @Test
    void testAddAgendaEntry() {
        assertTrue(agenda.addAgendaEntry(task, "31-12-2024"));
        assertEquals(1, agenda.getActiveAgendaEntries().size());
    }

    @Test
    void testAddAgendaEntry_Duplicate() {
        agenda.addAgendaEntry(task, "31-12-2024");
        assertThrows(IllegalArgumentException.class, () -> agenda.addAgendaEntry(task, "31-12-2024"));
    }
}