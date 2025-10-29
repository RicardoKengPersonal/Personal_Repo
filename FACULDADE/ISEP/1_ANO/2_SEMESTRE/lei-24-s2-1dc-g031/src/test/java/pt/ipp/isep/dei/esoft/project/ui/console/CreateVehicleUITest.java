package pt.ipp.isep.dei.esoft.project.ui.console;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateVehicleController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * The CreateVehicleUITest class contains unit tests for the CreateVehicleUI class.
 */
class CreateVehicleUITest {

    @Mock
    private CreateVehicleController mockController;

    private CreateVehicleUI createVehicleUI;
    private ByteArrayOutputStream outputStream;
    private ByteArrayInputStream inputStream;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        createVehicleUI = new CreateVehicleUI();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * Test the run method.
     * It verifies that the run method correctly displays the vehicle form, gets data from the user,
     * and displays success or failure messages.
     */
    @Test
    void testRun() {
        // Arrange
        String input = "AB-12-34\nToyota\nCorolla\nSedan\n1200\n1500\n10000\n2022-01-01\n2022-01-01\n5000\n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        when(mockController.addVehicle(anyString(), anyString(), anyString(), anyString(),
                anyFloat(), anyFloat(), anyFloat(), anyString(), anyString(), anyFloat())).thenReturn(true);
        String expectedOutput = "Vehicle Form:\nVehicle Plate:\nBrand:\nModel:\nType:\nTare weight:\nGross Weight:\nCurrent Km:\nRegister Date:\nAcquisition Date:\nMaintenance Frequency:\nOperation successful.\n";

        // Act
        createVehicleUI.run();

        // Assert
        assertEquals(expectedOutput, outputStream.toString());
        verify(mockController, times(1)).addVehicle(anyString(), anyString(), anyString(), anyString(),
                anyFloat(), anyFloat(), anyFloat(), anyString(), anyString(), anyFloat());
    }
}
