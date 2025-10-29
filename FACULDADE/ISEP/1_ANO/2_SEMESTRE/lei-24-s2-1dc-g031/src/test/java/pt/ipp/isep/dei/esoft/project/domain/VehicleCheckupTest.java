package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the VehicleCheckup class.
 * This class tests all the methods of the VehicleCheckup class to ensure they behave as expected.
 */
class VehicleCheckupTest {

    /**
     * Tests the constructor with valid parameters.
     * Verifies that the object is correctly initialized.
     */
    @Test
    void testConstructorWithValidParameters() {
        // Arrange
        VehicleCheckup checkup;

        // Act
        checkup = new VehicleCheckup("AA-12-BB", "01-01-2022", "10000");

        // Assert
        assertEquals("AA-12-BB", checkup.getPlate());
        assertEquals("01-01-2022", checkup.getDate());
        assertEquals("10000", checkup.getKmAtCheckup());
    }

    /**
     * Tests the getPlate method.
     * Verifies that it returns the correct plate.
     */
    @Test
    void testGetPlate() {
        // Arrange
        VehicleCheckup checkup = new VehicleCheckup("AA-12-BB", "01-01-2022", "10000");

        // Act & Assert
        assertEquals("AA-12-BB", checkup.getPlate());
    }

    /**
     * Tests the setPlate method.
     * Verifies that it correctly sets a new plate.
     */
    @Test
    void testSetPlate() {
        // Arrange
        VehicleCheckup checkup = new VehicleCheckup("AA-12-BB", "01-01-2022", "10000");

        // Act
        checkup.setPlate("BB-34-CC");

        // Assert
        assertEquals("BB-34-CC", checkup.getPlate());
    }

    /**
     * Tests the getDate method.
     * Verifies that it returns the correct date.
     */
    @Test
    void testGetDate() {
        // Arrange
        VehicleCheckup checkup = new VehicleCheckup("AA-12-BB", "01-01-2022", "10000");

        // Act & Assert
        assertEquals("01-01-2022", checkup.getDate());
    }

    /**
     * Tests the setDate method.
     * Verifies that it correctly sets a new date.
     */
    @Test
    void testSetDate() {
        // Arrange
        VehicleCheckup checkup = new VehicleCheckup("AA-12-BB", "01-01-2022", "10000");

        // Act
        checkup.setDate("02-02-2022");

        // Assert
        assertEquals("02-02-2022", checkup.getDate());
    }

    /**
     * Tests the getKmAtCheckup method.
     * Verifies that it returns the correct kilometers at checkup.
     */
    @Test
    void testGetKmAtCheckup() {
        // Arrange
        VehicleCheckup checkup = new VehicleCheckup("AA-12-BB", "01-01-2022", "10000");

        // Act & Assert
        assertEquals("10000", checkup.getKmAtCheckup());
    }

    /**
     * Tests the setKmAtCheckup method.
     * Verifies that it correctly sets a new kilometers at checkup.
     */
    @Test
    void testSetKmAtCheckup() {
        // Arrange
        VehicleCheckup checkup = new VehicleCheckup("AA-12-BB", "01-01-2022", "10000");

        // Act
        checkup.setKmAtCheckup("11000");

        // Assert
        assertEquals("11000", checkup.getKmAtCheckup());
    }

    /**
     * Tests the constructor with an invalid date format.
     * Verifies that it throws an IllegalArgumentException.
     */
    @Test
    void testInvalidDateFormat() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new VehicleCheckup("AA-12-BB", "2022-01-01", "10000"));
    }

    /**
     * Tests the constructor with an invalid kilometers at checkup format.
     * Verifies that it throws an IllegalArgumentException.
     */
    @Test
    void testInvalidKmAtCheckupFormat() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new VehicleCheckup("AA-12-BB", "01-01-2022", "-10000"));
    }
}
