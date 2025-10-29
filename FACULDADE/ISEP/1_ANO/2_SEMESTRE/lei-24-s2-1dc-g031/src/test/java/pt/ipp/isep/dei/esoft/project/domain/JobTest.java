package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import static org.junit.jupiter.api.Assertions.*;

class JobTest {
    private Job job;

    @BeforeEach
    void setUp() {
        job = new Job("Job1");
    }

    @Test
    void testEquals() {
        assertTrue(job.equals("Job1"));
        assertFalse(job.equals("Job2"));
    }

    @Test
    void testToString() {
        String expected = "Job name: Job1";
        String actual = job.toString();
        assertEquals(expected, actual);
    }
}