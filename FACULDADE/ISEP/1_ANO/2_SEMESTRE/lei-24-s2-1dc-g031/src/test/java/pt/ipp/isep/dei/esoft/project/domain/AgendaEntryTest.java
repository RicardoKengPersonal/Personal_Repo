package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.TaskUrgencyDegree;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AgendaEntryTest {
    private AgendaEntry agendaEntry;
    private Task task;
    private Team team;
    private Vehicle vehicle;

    @BeforeEach
    void setUp() {
        task = new Task("Task1 Description", "Task1", new GreenSpace("GreenSpace1", "Street1", GreenSpaceType.GARDEN, "email1@mail.com", 100), 480, TaskUrgencyDegree.LOW);
        team = new Team(new ArrayList<Collaborator>(), new ArrayList<Skill>());
        vehicle = new Vehicle("AA-00-BB", "Opel", "Corsa", "Gasoline", 1000, 1200, 250000, "01-01-2020", "01-01-2023", 10000);
        agendaEntry = new AgendaEntry(task, "31-12-2024");
    }

    @Test
    void testAddVehicle() {
        agendaEntry.addVehicle(vehicle);
        ArrayList<Vehicle> vehicles = agendaEntry.getVehicles();
        assertTrue(vehicles.contains(vehicle));
    }

    @Test
    void testToString() {
        String expected = "Task1 - Task1 Description - 31-12-2024 - GreenSpace1 - email1@mail.com - 31-12-2024 - PENDING";
        String actual = agendaEntry.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testIsValidDateFormat() {
        assertTrue(agendaEntry.isValidDateFormat("31-12-2024"));
        assertFalse(agendaEntry.isValidDateFormat("01/01/2024"));
    }


    @Test
    void testValidateDate() {
        assertThrows(IllegalArgumentException.class, () -> agendaEntry.validateDate("01/01/2022"));
        assertThrows(IllegalArgumentException.class, () -> agendaEntry.validateDate("31-12-2022"));
    }
}