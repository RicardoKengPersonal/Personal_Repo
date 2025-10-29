package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.repository.TaskStatus;
import pt.ipp.isep.dei.esoft.project.repository.TaskUrgencyDegree;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task("Task1 Description", "Task1", new GreenSpace("GreenSpace1", "Street1", GreenSpaceType.GARDEN, "email1@mail.com", 100), 10, TaskUrgencyDegree.HIGH);
    }

    @Test
    void testToString() {
        String expected = "Task1 - Task1 Description - GreenSpace1 - Street1 - GARDEN - 100 - PENDING - 10 - HIGH";
        String actual = task.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testTaskStatus() {
        task.setTaskStatus(TaskStatus.DONE);
        assertEquals(TaskStatus.DONE, task.getTaskStatus());
    }
}