package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;
import pt.ipp.isep.dei.esoft.project.domain.Task;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {
    private ToDoList toDoList;
    private Task task;

    @BeforeEach
    void setUp() {
        toDoList = new ToDoList();
        task = new Task("Task1 Description", "Task1", new GreenSpace("GreenSpace1", "Street1", GreenSpaceType.GARDEN, "email1@mail.com", 100), 10, TaskUrgencyDegree.HIGH);
    }

    @Test
    void testAddTask() {
        assertTrue(toDoList.addTask(task));
        assertEquals(1, toDoList.getTasks().size());
    }

    @Test
    void testAddTask_AlreadyExists() {
        toDoList.addTask(task);
        assertFalse(toDoList.addTask(task));
        assertEquals(1, toDoList.getTasks().size());
    }

    @Test
    void testGetPendingTasks() {
        toDoList.addTask(task);
        assertEquals(1, toDoList.getPendingTasks().size());
    }
}