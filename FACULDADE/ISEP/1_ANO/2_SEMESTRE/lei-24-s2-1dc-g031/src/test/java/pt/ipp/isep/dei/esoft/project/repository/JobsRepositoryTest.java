package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The JobsRepositoryTest class contains unit tests for the JobsRepository class.
 */
class JobsRepositoryTest {

    /**
     * Unit test for adding a job with a valid name.
     */
    @Test
    void testAddJob_ValidName_Success() {
        // Arrange
        JobsRepository jobsRepository = new JobsRepository();
        String jobName = "Software Engineer";

        // Act
        jobsRepository.addJob(jobName);

        // Assert
        assertEquals(1, jobsRepository.getJobsArrayList().size());
        Job addedJob = jobsRepository.getJobsArrayList().get(0);
        assertEquals(jobName, addedJob.getjobName());
    }

    /**
     * Unit test for adding a job with an empty name.
     */
    @Test
    void testAddJob_EmptyName_Fails() {
        // Arrange
        JobsRepository jobsRepository = new JobsRepository();
        String jobName = "";

        // Act
        jobsRepository.addJob(jobName);

        // Assert
        assertEquals(0, jobsRepository.getJobsArrayList().size());
    }

    /**
     * Unit test for adding a job with an invalid name.
     */
    @Test
    void testAddJob_InvalidName_Fails() {
        // Arrange
        JobsRepository jobsRepository = new JobsRepository();
        String jobName = "Software Engineer123"; // Invalid name with numbers

        // Act
        jobsRepository.addJob(jobName);

        // Assert
        assertEquals(0, jobsRepository.getJobsArrayList().size());
    }

    /**
     * Unit test for adding a duplicate job name.
     */
    @Test
    void testAddJob_DuplicateName_Fails() {
        // Arrange
        JobsRepository jobsRepository = new JobsRepository();
        String jobName = "Software Engineer";

        // Act
        jobsRepository.addJob(jobName);
        jobsRepository.addJob(jobName); // Adding the same job twice

        // Assert
        assertEquals(1, jobsRepository.getJobsArrayList().size());
    }
}
