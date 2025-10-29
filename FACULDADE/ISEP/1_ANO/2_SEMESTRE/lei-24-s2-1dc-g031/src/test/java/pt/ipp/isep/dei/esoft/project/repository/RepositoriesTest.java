package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The RepositoriesTest class contains unit tests for the Repositories class.
 */
class RepositoriesTest {

    /**
     * Unit test for testing the creation of Repositories instance.
     */
    @Test
    void testGetInstance() {
        // Act
        Repositories instance = Repositories.getInstance();

        // Assert
        assertNotNull(instance);
    }

    /**
     * Unit test for getting the JobsRepository from Repositories.
     */
    @Test
    void testGetJobsRepository() {
        // Arrange
        Repositories instance = Repositories.getInstance();

        // Act
        JobsRepository jobsRepository = instance.getJobsRepository();

        // Assert
        assertNotNull(jobsRepository);
    }

    /**
     * Unit test for getting the SkillsRepository from Repositories.
     */
    @Test
    void testGetSkillsRepository() {
        // Arrange
        Repositories instance = Repositories.getInstance();

        // Act
        SkillsRepository skillsRepository = instance.getSkillsRepository();

        // Assert
        assertNotNull(skillsRepository);
    }

    /**
     * Unit test for getting the TeamsRepository from Repositories.
     */
    @Test
    void testGetTeamsRepository() {
        // Arrange
        Repositories instance = Repositories.getInstance();

        // Act
        TeamsRepository teamsRepository = instance.getTeamsRepository();

        // Assert
        assertNotNull(teamsRepository);
    }

    /**
     * Unit test for getting the CollaboratorsRepository from Repositories.
     */
    @Test
    void testGetCollaboratorsRepository() {
        // Arrange
        Repositories instance = Repositories.getInstance();

        // Act
        CollaboratorsRepository collaboratorsRepository = instance.getCollaboratorsRepository();

        // Assert
        assertNotNull(collaboratorsRepository);
    }
    /**
     * Unit test for getting the VehicleRepository from Repositories.
     */
    @Test
    void testGetVehicleRepository() {
        Repositories instance = Repositories.getInstance();

        VehicleRepository vehicleRepository = instance.getVehicleRepository();

        assertNotNull(vehicleRepository);
    }
    /**
     * Unit test for getting the MaintenanceRepository from Repositories.
     */
    @Test
    void testGetMaintenanceRepository() {
        Repositories instance = Repositories.getInstance();

        MaintenanceRepository maintenanceRepository = instance.getMaintenanceRepository();

        assertNotNull(maintenanceRepository);
    }
}
