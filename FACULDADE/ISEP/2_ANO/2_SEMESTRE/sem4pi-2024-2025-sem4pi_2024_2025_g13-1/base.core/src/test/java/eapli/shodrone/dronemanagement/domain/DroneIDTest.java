package eapli.shodrone.dronemanagement.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DroneIDTest {

    private DroneID droneID1;
    private DroneID droneID2;
    private DroneID droneID3;

    @BeforeEach
    void setUp() {
        droneID1 = new DroneID("SN123456");
        droneID2 = new DroneID("SN123456");
        droneID3 = new DroneID("SN654321");
    }

    @Test
    void testConstructorAndGetters() {
        assertNotNull(droneID1);
        assertEquals("SN123456", droneID1.serialNumber());
    }

    @Test
    void testToString() {
        assertEquals("SN123456", droneID1.toString());
    }

    @Test
    void testEquality() {
        assertEquals(droneID1, droneID2);  // Same serial numbers
        assertNotEquals(droneID1, droneID3); // Different serial numbers
    }

    @Test
    void testHashCode() {
        assertEquals(droneID1.hashCode(), droneID2.hashCode());
        assertNotEquals(droneID1.hashCode(), droneID3.hashCode());
    }

    @Test
    void testCompareTo() {
        assertTrue(droneID1.compareTo(droneID2) == 0); // Same serial numbers
        assertTrue(droneID1.compareTo(droneID3) < 0);  // SN123456 < SN654321
    }

    @Test
    void testConstructorValidation() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new DroneID(null));
        assertEquals("Drone serial number must not be null or empty.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> new DroneID(" "));
        assertEquals("Drone serial number must not be null or empty.", exception.getMessage());
    }

    @Test
    void testEmptyConstructor() {
        DroneID droneID = new DroneID(); // For UUID generation
        assertNotNull(droneID);  // We don't check if UUID is set here, just if object creation works
    }
}
