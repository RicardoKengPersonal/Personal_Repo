package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.VehicleCheckup;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the MaintenanceRepository class.
 */
class MaintenanceRepositoryTest {
    private MaintenanceRepository repository;
    private VehicleCheckup checkup1;
    private VehicleCheckup checkup2;
    private VehicleCheckup checkup3;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        repository = new MaintenanceRepository();
        checkup1 = new VehicleCheckup("AB-12-CD", "01-05-2023", "50000");
        checkup2 = new VehicleCheckup("XY-78-ZZ", "01-06-2023", "60000");
        checkup3 = new VehicleCheckup("AB-12-CD", "01-07-2023", "55000");
    }

    /**
     * Tests the registerVehicleMaintenance() method of the MaintenanceRepository class.
     */
    @Test
    void testRegisterVehicleMaintenance() {
        assertTrue(repository.registerVehicleMaintenance(checkup1));
        assertTrue(repository.registerVehicleMaintenance(checkup2));
        assertFalse(repository.registerVehicleMaintenance(checkup1)); // Already registered
    }

    /**
     * Tests the getMostRecentCheckup() method of the MaintenanceRepository class.
     */
    @Test
    void testGetMostRecentCheckup() {
        repository.registerVehicleMaintenance(checkup1);
        repository.registerVehicleMaintenance(checkup2);
        repository.registerVehicleMaintenance(checkup3);

        VehicleCheckup mostRecent1 = repository.getMostRecentCheckup("AB-12-CD");
        VehicleCheckup mostRecent2 = repository.getMostRecentCheckup("XY-78-ZZ");
        VehicleCheckup mostRecent3 = repository.getMostRecentCheckup("AA-11-AA");

        assertEquals("01-07-2023", mostRecent1.getDate());
        assertEquals("01-06-2023", mostRecent2.getDate());
        assertNull(mostRecent3);
    }

    /**
     * Tests the getAllVehiclesMaintenances() method of the MaintenanceRepository class.
     */
    @Test
    void testGetAllVehiclesMaintenances() {
        repository.registerVehicleMaintenance(checkup1);
        repository.registerVehicleMaintenance(checkup2);
        repository.registerVehicleMaintenance(checkup3);

        ArrayList<VehicleCheckup> allCheckups = repository.getAllVehiclesMaintenances();

        assertEquals(3, allCheckups.size());
        assertTrue(allCheckups.contains(checkup1));
        assertTrue(allCheckups.contains(checkup2));
        assertTrue(allCheckups.contains(checkup3));
    }
}

