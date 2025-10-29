package pt.ipp.isep.dei.esoft.project.ui.console;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterMaintenanceController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * The RegisterMaintenanceUITest class contains unit tests for the RegisterMaintenanceUI class.
 */
class RegisterMaintenanceUITest {

    @Mock
    private RegisterMaintenanceController mockController;

    private RegisterMaintenanceUI registerMaintenanceUI;
    private ByteArrayOutputStream outputStream;
    private ByteArrayInputStream inputStream;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        registerMaintenanceUI = new RegisterMaintenanceUI();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * Test the run method.
     * It verifies that the run method correctly gets vehicle checkup data from the user,
     * registers the maintenance checkup, and displays success or failure messages.
     */
    @Test
    void testRun() {
        // Arrange
        String input = "AB-12-34\n2022-05-10\n15000\n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        when(mockController.registerVehicleMaintenance(anyString(), anyString(), anyString())).thenReturn(true);
        String expectedOutput = "Enter Vehicle details:\nOperation successful.\n";

        // Act
        registerMaintenanceUI.run();

        // Assert
        assertEquals(expectedOutput, outputStream.toString());
        verify(mockController, times(1)).registerVehicleMaintenance(anyString(), anyString(), anyString());
    }
}
