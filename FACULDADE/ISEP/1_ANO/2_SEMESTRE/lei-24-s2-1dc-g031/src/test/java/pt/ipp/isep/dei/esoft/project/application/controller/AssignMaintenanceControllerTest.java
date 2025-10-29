package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.VehicleCheckup;
import pt.ipp.isep.dei.esoft.project.repository.MaintenanceRepository;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * The AssignMaintenanceControllerTest class represents the unit tests for the AssignMaintenanceController class.
 */
class AssignMaintenanceControllerTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private MaintenanceRepository maintenanceRepository;

    private AssignMaintenanceController controller;

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    /**
     * Initializes mock objects and sets up the controller and console output stream before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new AssignMaintenanceController();
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * Tests the displayVehiclesList method when there are no vehicles in the repository.
     */
    @Test
    void testDisplayVehiclesList_WithNoVehicles() {
        // Arrange
        when(vehicleRepository.getAllVehicles()).thenReturn(new ArrayList<>());
        String expectedOutput = "No vehicles found.";

        // Act
        controller.displayVehiclesList();

        // Assert
        assertEquals(expectedOutput, getConsoleOutput());
    }

    /**
     * Tests the displayVehiclesList method when there are vehicles in the repository.
     */
    @Test
    void testDisplayVehiclesList_WithVehicles() {
        // Arrange
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("AB-12-34", "Toyota", "Corolla", "Sedan", 1200, 1500, 10000, "2022-01-01", "2022-01-01", 5000));
        vehicles.add(new Vehicle("CD-56-78", "Honda", "Civic", "Hatchback", 1100, 1400, 8000, "2022-01-02", "2022-01-02", 6000));
        when(vehicleRepository.getAllVehicles()).thenReturn(vehicles);
        String expectedOutput = "Vehicles List:\n" +
                "Plate: AB-12-34\n" +
                "Brand: Toyota\n" +
                "Model: Corolla\n" +
                "Type: Sedan\n" +
                "Tare weight: 1200\n" +
                "Gross weight: 1500\n" +
                "Current km: 10000\n" +
                "Register date: 2022-01-01\n" +
                "Acquisition Date: 2022-01-01\n" +
                "Maintenance Frequency:5000\n" +
                "------------------------------\n" +
                "Plate: CD-56-78\n" +
                "Brand: Honda\n" +
                "Model: Civic\n" +
                "Type: Hatchback\n" +
                "Tare weight: 1100\n" +
                "Gross weight: 1400\n" +
                "Current km: 8000\n" +
                "Register date: 2022-01-02\n" +
                "Acquisition Date: 2022-01-02\n" +
                "Maintenance Frequency:6000\n" +
                "------------------------------\n";

        // Act
        controller.displayVehiclesList();

        // Assert
        assertEquals(expectedOutput, getConsoleOutput());
    }

    /**
     * Tests the getVehiclesNeedingMaintenance method when there are no vehicles needing maintenance.
     */
    @Test
    void testGetVehiclesNeedingMaintenance_WithNoVehiclesNeedingMaintenance() {
        // Arrange
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("AB-12-34", "Toyota", "Corolla", "Sedan", 1200, 1500, 10000, "2022-01-01", "2022-01-01", 5000));
        when(vehicleRepository.getAllVehicles()).thenReturn(vehicles);
        when(maintenanceRepository.getAllVehiclesMaintenances()).thenReturn(new ArrayList<>());
        String expectedOutput = "No vehicles need maintenance at the moment.";

        // Act
        controller.getVehiclesNeedingMaintenance();

        // Assert
        assertEquals(expectedOutput, getConsoleOutput());
    }

    /**
     * Tests the getVehiclesNeedingMaintenance method when there are vehicles needing maintenance.
     */
    @Test
    void testGetVehiclesNeedingMaintenance_WithVehiclesNeedingMaintenance() {
        // Arrange
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("AB-12-34", "Toyota", "Corolla", "Sedan", 1200, 1500, 15000, "2022-01-01", "2022-01-01", 5000));
        vehicles.add(new Vehicle("CD-56-78", "Honda", "Civic", "Hatchback", 1100, 1400, 10000, "2022-01-02", "2022-01-02", 6000));
        when(vehicleRepository.getAllVehicles()).thenReturn(vehicles);

        ArrayList<VehicleCheckup> checkups = new ArrayList<>();
        checkups.add(new VehicleCheckup("AB-12-34", "2023-01-01", "12000"));
        checkups.add(new VehicleCheckup("CD-56-78", "2023-01-01", "9000"));
        when(maintenanceRepository.getAllVehiclesMaintenances()).thenReturn(checkups);

        String expectedOutput = "Vehicles needing maintenance:\n" +
                "Plate: AB-12-34\n" +
                "Brand: Toyota\n" +
                "Model: Corolla\n" +
                "Curr. KM: 15000\n" +
                "Maintenance frequency: 5000\n" +
                "Kilometers at last checkup: 12000\n" +
                "Next checkup: 27000.0\n" +
                "-----------------------------\n";

        // Act
        controller.getVehiclesNeedingMaintenance();

        // Assert
        assertEquals(expectedOutput, getConsoleOutput());
    }

    // Helper method to capture console output
    private String getConsoleOutput() {
        return outputStream.toString().trim();
    }
}


