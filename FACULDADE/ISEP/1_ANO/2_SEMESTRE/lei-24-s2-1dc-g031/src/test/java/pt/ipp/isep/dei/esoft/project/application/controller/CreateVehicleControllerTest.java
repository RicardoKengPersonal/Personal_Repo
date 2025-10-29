package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The CreateVehicleControllerTest class represents the unit tests for the CreateVehicleController class.
 */
class CreateVehicleControllerTest {

    @Mock
    private VehicleRepository vehicleRepository;
    private CreateVehicleController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new CreateVehicleController();
        controller.setVehicleRepository(vehicleRepository);
    }

    /**
     * Tests the addVehicle method with valid vehicle data.
     */
    @Test
    void testAddVehicle_WithValidData() {
        // Arrange
        String plate = "AB-12-34";
        String brand = "Toyota";
        String model = "Corolla";
        String type = "Sedan";
        float tareWeight = 1200;
        float grossWeight = 1500;
        float currentKm = 10000;
        String registerDate = "2022-01-01";
        String acquisitionDate = "2022-01-01";
        float maintenanceFrequency = 5000;

        // Act
        when(vehicleRepository.addVehicle(any(Vehicle.class))).thenReturn(true);
        boolean result = controller.addVehicle(plate, brand, model, type, tareWeight, grossWeight, currentKm,
                registerDate, acquisitionDate, maintenanceFrequency);

        // Assert
        assertTrue(result);
        verify(vehicleRepository, times(1)).addVehicle(any(Vehicle.class));
    }

    /**
     * Tests the addVehicle method with invalid vehicle data.
     */
    @Test
    void testAddVehicle_WithInvalidData() {
        // Arrange
        // Invalid data: empty plate
        String plate = "";
        String brand = "Toyota";
        String model = "Corolla";
        String type = "Sedan";
        float tareWeight = 1200;
        float grossWeight = 1500;
        float currentKm = 10000;
        String registerDate = "2022-01-01";
        String acquisitionDate = "2022-01-01";
        float maintenanceFrequency = 5000;

        // Act
        boolean result = controller.addVehicle(plate, brand, model, type, tareWeight, grossWeight, currentKm,
                registerDate, acquisitionDate, maintenanceFrequency);

        // Assert
        assertFalse(result);
        verify(vehicleRepository, never()).addVehicle(any(Vehicle.class));
    }
}
