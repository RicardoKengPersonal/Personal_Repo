package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.esoft.project.domain.VehicleCheckup;
import pt.ipp.isep.dei.esoft.project.repository.MaintenanceRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The RegisterMaintenanceControllerTest class contains unit tests for the RegisterMaintenanceController class.
 */

class RegisterMaintenanceControllerTest {

    @Mock
    private MaintenanceRepository maintenanceRepository;
    private RegisterMaintenanceController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new RegisterMaintenanceController();
        controller.setMaintenanceRepository(maintenanceRepository);
    }

    /**
     * Tests the registerVehicleMaintenance method when the registration is successful.
     */

    @Test
    void testRegisterVehicleMaintenance_SuccessfulRegistration() {
        // Arrange
        String plateNumber = "AB-12-34";
        String date = "2022-05-10";
        String kmAtCheckup = "15000";

        // Act
        when(maintenanceRepository.registerVehicleMaintenance(any(VehicleCheckup.class))).thenReturn(true);
        boolean result = controller.registerVehicleMaintenance(plateNumber, date, kmAtCheckup);

        // Assert
        assertTrue(result);
        verify(maintenanceRepository, times(1)).registerVehicleMaintenance(any(VehicleCheckup.class));
    }

    /**
     * Tests the registerVehicleMaintenance method when the registration fails.
     */

    @Test
    void testRegisterVehicleMaintenance_FailedRegistration() {
        // Arrange
        String plateNumber = "AB-12-34";
        String date = "2022-05-10";
        String kmAtCheckup = "15000";

        // Act
        when(maintenanceRepository.registerVehicleMaintenance(any(VehicleCheckup.class))).thenReturn(false);
        boolean result = controller.registerVehicleMaintenance(plateNumber, date, kmAtCheckup);

        // Assert
        assertFalse(result);
        verify(maintenanceRepository, times(1)).registerVehicleMaintenance(any(VehicleCheckup.class));
    }
}
