package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.ui.console.CreateVehicleUI;
import pt.ipp.isep.dei.esoft.project.ui.console.RegisterMaintenanceUI;
import pt.ipp.isep.dei.esoft.project.ui.console.AssignMaintenanceUI;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The VFMUI class represents the user interface for the Vehicle Fleet Management (VFM) system.
 * It displays a menu with options to register vehicles, register vehicle maintenance, and show vehicles needing maintenance.
 * The user can select an option from the menu to perform the corresponding action.
 */
class VFMUITest {
    private VFMUI vfmUI;
    private ByteArrayOutputStream outputStream;

    /**
     * Sets up the test environment before each test method execution.
     */
    @BeforeEach
    void setUp() {
        vfmUI = new VFMUI();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * Test method to verify that the menu is displayed correctly when the first option is selected.
     */
    @Test
    void testRun_DisplayMenuAndSelectFirstOption() {
        // Arrange
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Register a Vehicle", new CreateVehicleUI()));
        options.add(new MenuItem("Register vehicle's maintenance", new RegisterMaintenanceUI()));
        options.add(new MenuItem("Show vehicles needing maintenance", new AssignMaintenanceUI()));
        String expectedOutput = "\n\n--- VFM MENU -------------------------\n" +
                "0 - Register a Vehicle\n" +
                "1 - Register vehicle's maintenance\n" +
                "2 - Show vehicles needing maintenance\n";
        String input = "0";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        vfmUI.run();


        assertEquals(expectedOutput, outputStream.toString());
    }

    /**
     * Test method to verify that the menu is displayed correctly when the second option is selected.
     */
    @Test
    void testRun_DisplayMenuAndSelectSecondOption() {

        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Register a Vehicle", new CreateVehicleUI()));
        options.add(new MenuItem("Register vehicle's maintenance", new RegisterMaintenanceUI()));
        options.add(new MenuItem("Show vehicles needing maintenance", new AssignMaintenanceUI()));
        String expectedOutput = "\n\n--- VFM MENU -------------------------\n" +
                "0 - Register a Vehicle\n" +
                "1 - Register vehicle's maintenance\n" +
                "2 - Show vehicles needing maintenance\n";
        String input = "1";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);


        vfmUI.run();


        assertEquals(expectedOutput, outputStream.toString());
    }

    /**
     * Test method to verify that the menu is displayed correctly when the third option is selected.
     */
    @Test
    void testRun_DisplayMenuAndSelectThirdOption() {

        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Register a Vehicle", new CreateVehicleUI()));
        options.add(new MenuItem("Register vehicle's maintenance", new RegisterMaintenanceUI()));
        options.add(new MenuItem("Show vehicles needing maintenance", new AssignMaintenanceUI()));
        String expectedOutput = "\n\n--- VFM MENU -------------------------\n" +
                "0 - Register a Vehicle\n" +
                "1 - Register vehicle's maintenance\n" +
                "2 - Show vehicles needing maintenance\n";
        String input = "2"; // Select third option
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        vfmUI.run();

        assertEquals(expectedOutput, outputStream.toString());
    }
}

