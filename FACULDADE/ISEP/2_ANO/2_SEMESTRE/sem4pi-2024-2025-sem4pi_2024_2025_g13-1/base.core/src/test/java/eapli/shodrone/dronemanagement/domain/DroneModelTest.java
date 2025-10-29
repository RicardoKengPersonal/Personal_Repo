package eapli.shodrone.dronemanagement.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DroneModelTest {

    private static final String VALID_NAME = "Model A";
    private static final String VALID_MANUFACTURER = "Manufacturer A";
    private static final float VALID_X_AXIS_TOLERANCE = 0.5f;
    private static final float VALID_Y_AXIS_TOLERANCE = 0.5f;
    private static final float VALID_Z_AXIS_TOLERANCE = 0.5f;
    private static final float VALID_MAX_SPEED = 10f;
    private static final float VALID_MAX_ROTATION_SPEED = 10f;
    private static final Set<DroneType> VALID_LIGHTING_OPTIONS = Set.of(DroneType.LED,DroneType.RGB);

    private final Calendar validDate = Calendar.getInstance();

    private DroneModel createValidDroneModel() {
        return new DroneModel(
                VALID_NAME, VALID_MANUFACTURER,
                VALID_X_AXIS_TOLERANCE, VALID_Y_AXIS_TOLERANCE, VALID_Z_AXIS_TOLERANCE,
                validDate, VALID_MAX_SPEED, VALID_MAX_ROTATION_SPEED,
                VALID_LIGHTING_OPTIONS
        );
    }

    @Test
    void testValidDroneModelCreation() {
        var drone = createValidDroneModel();
        assertNotNull(drone);
        assertEquals(VALID_NAME, drone.name());
        assertEquals(VALID_MANUFACTURER, drone.manufacturer());
        assertEquals(VALID_X_AXIS_TOLERANCE, drone.xAxisTolerance());
        assertEquals(VALID_Y_AXIS_TOLERANCE, drone.yAxisTolerance());
        assertEquals(VALID_Z_AXIS_TOLERANCE, drone.zAxisTolerance());
        assertEquals(validDate, drone.day());
        assertEquals(VALID_MAX_SPEED, drone.maxSpeed());
        assertEquals(VALID_MAX_ROTATION_SPEED, drone.maxRotationSpeed());
        assertEquals(VALID_LIGHTING_OPTIONS, drone.lightingOptions());
        assertTrue(drone.isActive());
    }

    @Test
    void testNameValidation() {
        assertThrows(IllegalArgumentException.class, () -> new DroneModel(
                null, VALID_MANUFACTURER, VALID_X_AXIS_TOLERANCE, VALID_Y_AXIS_TOLERANCE,
                VALID_Z_AXIS_TOLERANCE, validDate, VALID_MAX_SPEED, VALID_MAX_ROTATION_SPEED, VALID_LIGHTING_OPTIONS));

        assertThrows(IllegalArgumentException.class, () -> new DroneModel(
                "   ", VALID_MANUFACTURER, VALID_X_AXIS_TOLERANCE, VALID_Y_AXIS_TOLERANCE,
                VALID_Z_AXIS_TOLERANCE, validDate, VALID_MAX_SPEED, VALID_MAX_ROTATION_SPEED, VALID_LIGHTING_OPTIONS));
    }

    @Test
    void testManufacturerValidation() {
        assertThrows(IllegalArgumentException.class, () -> new DroneModel(
                VALID_NAME, null, VALID_X_AXIS_TOLERANCE, VALID_Y_AXIS_TOLERANCE,
                VALID_Z_AXIS_TOLERANCE, validDate, VALID_MAX_SPEED, VALID_MAX_ROTATION_SPEED, VALID_LIGHTING_OPTIONS));

        assertThrows(IllegalArgumentException.class, () -> new DroneModel(
                VALID_NAME, "", VALID_X_AXIS_TOLERANCE, VALID_Y_AXIS_TOLERANCE,
                VALID_Z_AXIS_TOLERANCE, validDate, VALID_MAX_SPEED, VALID_MAX_ROTATION_SPEED, VALID_LIGHTING_OPTIONS));
    }

    @Test
    void testToleranceValidation() {
        assertThrows(IllegalArgumentException.class, () -> new DroneModel(
                VALID_NAME, VALID_MANUFACTURER, -0.1f, VALID_Y_AXIS_TOLERANCE,
                VALID_Z_AXIS_TOLERANCE, validDate, VALID_MAX_SPEED, VALID_MAX_ROTATION_SPEED, VALID_LIGHTING_OPTIONS));

        assertThrows(IllegalArgumentException.class, () -> new DroneModel(
                VALID_NAME, VALID_MANUFACTURER, VALID_X_AXIS_TOLERANCE, -0.1f,
                VALID_Z_AXIS_TOLERANCE, validDate, VALID_MAX_SPEED, VALID_MAX_ROTATION_SPEED, VALID_LIGHTING_OPTIONS));

        assertThrows(IllegalArgumentException.class, () -> new DroneModel(
                VALID_NAME, VALID_MANUFACTURER, VALID_X_AXIS_TOLERANCE, VALID_Y_AXIS_TOLERANCE,
                -0.1f, validDate, VALID_MAX_SPEED, VALID_MAX_ROTATION_SPEED, VALID_LIGHTING_OPTIONS));
    }

    @Test
    void testRegistrationDateValidation() {
        assertThrows(IllegalArgumentException.class, () -> new DroneModel(
                VALID_NAME, VALID_MANUFACTURER, VALID_X_AXIS_TOLERANCE, VALID_Y_AXIS_TOLERANCE,
                VALID_Z_AXIS_TOLERANCE, null, VALID_MAX_SPEED, VALID_MAX_ROTATION_SPEED, VALID_LIGHTING_OPTIONS));
    }

    @Test
    void testSpeedValidation() {
        assertThrows(IllegalArgumentException.class, () -> new DroneModel(
                VALID_NAME, VALID_MANUFACTURER, VALID_X_AXIS_TOLERANCE, VALID_Y_AXIS_TOLERANCE,
                VALID_Z_AXIS_TOLERANCE, validDate, -1f, VALID_MAX_ROTATION_SPEED, VALID_LIGHTING_OPTIONS));

        assertThrows(IllegalArgumentException.class, () -> new DroneModel(
                VALID_NAME, VALID_MANUFACTURER, VALID_X_AXIS_TOLERANCE, VALID_Y_AXIS_TOLERANCE,
                VALID_Z_AXIS_TOLERANCE, validDate, VALID_MAX_SPEED, -2f, VALID_LIGHTING_OPTIONS));
    }

    @Test
    void testLightingOptionsValidation() {
        assertThrows(IllegalArgumentException.class, () -> new DroneModel(
                VALID_NAME, VALID_MANUFACTURER, VALID_X_AXIS_TOLERANCE, VALID_Y_AXIS_TOLERANCE,
                VALID_Z_AXIS_TOLERANCE, validDate, VALID_MAX_SPEED, VALID_MAX_ROTATION_SPEED, null));

        assertThrows(IllegalArgumentException.class, () -> new DroneModel(
                VALID_NAME, VALID_MANUFACTURER, VALID_X_AXIS_TOLERANCE, VALID_Y_AXIS_TOLERANCE,
                VALID_Z_AXIS_TOLERANCE, validDate, VALID_MAX_SPEED, VALID_MAX_ROTATION_SPEED, Set.of()));
    }

    @Test
    void testEqualsAndHashCodeBasedOnNameIgnoreCase() {
        DroneModel drone1 = createValidDroneModel();
        DroneModel drone2 = new DroneModel("model a", VALID_MANUFACTURER, VALID_X_AXIS_TOLERANCE, VALID_Y_AXIS_TOLERANCE,
                VALID_Z_AXIS_TOLERANCE, validDate, VALID_MAX_SPEED, VALID_MAX_ROTATION_SPEED, VALID_LIGHTING_OPTIONS);

        assertEquals(drone1, drone2);
        assertEquals(drone1.hashCode(), drone2.hashCode());
    }

    @Test
    void testSameAsMethod() {
        DroneModel drone1 = createValidDroneModel();
        DroneModel drone2 = new DroneModel("model a", VALID_MANUFACTURER, VALID_X_AXIS_TOLERANCE, VALID_Y_AXIS_TOLERANCE,
                VALID_Z_AXIS_TOLERANCE, validDate, VALID_MAX_SPEED, VALID_MAX_ROTATION_SPEED, VALID_LIGHTING_OPTIONS);

        assertTrue(drone1.sameAs(drone2));
    }

    @Test
    void testToStringContainsImportantFields() {
        DroneModel drone = createValidDroneModel();
        String output = drone.toString();
        assertTrue(output.contains("Model A"));
        assertTrue(output.contains("Manufacturer A"));
        assertTrue(output.contains("maxSpeed=10.0"));
        assertTrue(output.contains("lightingOptions="));
    }

    @Test
    void testIdentityReturnsNullBeforePersistence() {
        DroneModel drone = createValidDroneModel();
        assertNull(drone.identity());
    }
}
