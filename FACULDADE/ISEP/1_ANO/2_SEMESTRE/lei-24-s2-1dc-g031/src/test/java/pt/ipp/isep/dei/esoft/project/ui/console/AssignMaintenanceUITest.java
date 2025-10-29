package pt.ipp.isep.dei.esoft.project.ui.console;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignMaintenanceController;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The AssignMaintenanceUITest class contains unit tests for the AssignMaintenanceUI class.
 */

class AssignMaintenanceUITest {

    @Mock
    private AssignMaintenanceController mockController;

    private AssignMaintenanceUI assignMaintenanceUI;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        assignMaintenanceUI = new AssignMaintenanceUI();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * Test the run method.
     * It verifies that the run method correctly displays the list of vehicles and identifies those needing maintenance.
     */
    @Test
    void testRun() {
        // Arrange
        doNothing().when(mockController).displayVehiclesList();
        doNothing().when(mockController).getVehiclesNeedingMaintenance();
        String expectedOutput = "Vehicles registered:\n------------------------------------\n";

        // Act
        assignMaintenanceUI.run();

        // Assert
        assertEquals(expectedOutput, outputStream.toString());
        verify(mockController, times(1)).displayVehiclesList();
        verify(mockController, times(1)).getVehiclesNeedingMaintenance();
    }
}


