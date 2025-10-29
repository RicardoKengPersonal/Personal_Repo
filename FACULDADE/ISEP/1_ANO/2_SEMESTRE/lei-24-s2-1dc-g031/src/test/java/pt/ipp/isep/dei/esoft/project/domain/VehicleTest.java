package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Vehicle class.
 * This class tests all the methods of the Vehicle class to ensure they behave as expected.
 */
class VehicleTest {

    private Vehicle vehicle;

    /**
     * Sets up a valid Vehicle object before each test.
     */
    @BeforeEach
    void setUp() {
        vehicle = new Vehicle("AA-12-BB", "Toyota", "Corolla", "Sedan",
                1500.0f, 2000.0f, 10000.0f, "01-01-2022", "01-01-2021", 6.0f);
    }

    /**
     * Tests the constructor with valid parameters.
     * Verifies that the object is correctly initialized.
     */
    @Test
    void testConstructorWithValidParameters() {
        assertEquals("AA-12-BB", vehicle.getPlate());
        assertEquals("Toyota", vehicle.getBrand());
        assertEquals("Corolla", vehicle.getModel());
        assertEquals("Sedan", vehicle.getType());
        assertEquals(1500.0f, vehicle.getTareWeight());
        assertEquals(2000.0f, vehicle.getGrossWeight());
        assertEquals(10000.0f, vehicle.getCurrentKm());
        assertEquals("01-01-2022", vehicle.getRegisterDate());
        assertEquals("01-01-2021", vehicle.getAcquisitionDate());
        assertEquals(6.0f, vehicle.getMaintenanceFrequency());
    }

    /**
     * Tests the getPlate method.
     * Verifies that it returns the correct plate.
     */
    @Test
    void testGetPlate() {
        assertEquals("AA-12-BB", vehicle.getPlate());
    }

    /**
     * Tests the getBrand method.
     * Verifies that it returns the correct brand.
     */
    @Test
    void testGetBrand() {
        assertEquals("Toyota", vehicle.getBrand());
    }

    /**
     * Tests the getModel method.
     * Verifies that it returns the correct model.
     */
    @Test
    void testGetModel() {
        assertEquals("Corolla", vehicle.getModel());
    }

    /**
     * Tests the getType method.
     * Verifies that it returns the correct type.
     */
    @Test
    void testGetType() {
        assertEquals("Sedan", vehicle.getType());
    }

    /**
     * Tests the getTareWeight method.
     * Verifies that it returns the correct tare weight.
     */
    @Test
    void testGetTareWeight() {
        assertEquals(1500.0f, vehicle.getTareWeight());
    }

    /**
     * Tests the getGrossWeight method.
     * Verifies that it returns the correct gross weight.
     */
    @Test
    void testGetGrossWeight() {
        assertEquals(2000.0f, vehicle.getGrossWeight());
    }

    /**
     * Tests the getCurrentKm method.
     * Verifies that it returns the correct current kilometers.
     */
    @Test
    void testGetCurrentKm() {
        assertEquals(10000.0f, vehicle.getCurrentKm());
    }

    /**
     * Tests the getRegisterDate method.
     * Verifies that it returns the correct register date.
     */
    @Test
    void testGetRegisterDate() {
        assertEquals("01-01-2022", vehicle.getRegisterDate());
    }

    /**
     * Tests the getAcquisitionDate method.
     * Verifies that it returns the correct acquisition date.
     */
    @Test
    void testGetAcquisitionDate() {
        assertEquals("01-01-2021", vehicle.getAcquisitionDate());
    }

    /**
     * Tests the getMaintenanceFrequency method.
     * Verifies that it returns the correct maintenance frequency.
     */
    @Test
    void testGetMaintenanceFrequency() {
        assertEquals(6.0f, vehicle.getMaintenanceFrequency());
    }

    /**
     * Tests the setPlate method.
     * Verifies that it correctly sets a new plate.
     */
    @Test
    void testSetPlate() {
        vehicle.setPlate("BB-34-CC");
        assertEquals("BB-34-CC", vehicle.getPlate());
    }

    /**
     * Tests the setBrand method.
     * Verifies that it correctly sets a new brand.
     */
    @Test
    void testSetBrand() {
        vehicle.setBrand("Ford");
        assertEquals("Ford", vehicle.getBrand());
    }

    /**
     * Tests the setModel method.
     * Verifies that it correctly sets a new model.
     */
    @Test
    void testSetModel() {
        vehicle.setModel("Focus");
        assertEquals("Focus", vehicle.getModel());
    }

    /**
     * Tests the setType method.
     * Verifies that it correctly sets a new type.
     */
    @Test
    void testSetType() {
        vehicle.setType("Hatchback");
        assertEquals("Hatchback", vehicle.getType());
    }

    /**
     * Tests the setTareWeight method.
     * Verifies that it correctly sets a new tare weight.
     */
    @Test
    void testSetTareWeight() {
        vehicle.setTareWeight(1600.0f);
        assertEquals(1600.0f, vehicle.getTareWeight());
    }

    /**
     * Tests the setGrossWeight method.
     * Verifies that it correctly sets a new gross weight.
     */
    @Test
    void testSetGrossWeight() {
        vehicle.setGrossWeight(2100.0f);
        assertEquals(2100.0f, vehicle.getGrossWeight());
    }

    /**
     * Tests the setCurrentKm method.
     * Verifies that it correctly sets new current kilometers.
     */
    @Test
    void testSetCurrentKm() {
        vehicle.setCurrentKm(11000.0f);
        assertEquals(11000.0f, vehicle.getCurrentKm());
    }

    /**
     * Tests the setRegisterDate method.
     * Verifies that it correctly sets a new register date.
     */
    @Test
    void testSetRegisterDate() {
        vehicle.setRegisterDate("02-02-2022");
        assertEquals("02-02-2022", vehicle.getRegisterDate());
    }

    /**
     * Tests the setAcquisitionDate method.
     * Verifies that it correctly sets a new acquisition date.
     */
    @Test
    void testSetAcquisitionDate() {
        vehicle.setAcquisitionDate("03-03-2021");
        assertEquals("03-03-2021", vehicle.getAcquisitionDate());
    }

    /**
     * Tests the setMaintenanceFrequency method.
     * Verifies that it correctly sets a new maintenance frequency.
     */
    @Test
    void testSetMaintenanceFrequency() {
        vehicle.setMaintenanceFrequency(7.0f);
        assertEquals(7.0f, vehicle.getMaintenanceFrequency());
    }
}
