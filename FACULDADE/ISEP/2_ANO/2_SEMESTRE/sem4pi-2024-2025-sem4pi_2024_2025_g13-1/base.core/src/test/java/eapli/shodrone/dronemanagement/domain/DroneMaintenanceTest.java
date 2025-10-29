package eapli.shodrone.dronemanagement.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DroneMaintenanceTest {

    private Drone drone;
    private MaintenanceType maintenanceType;
    private LocalDate date;

    @BeforeEach
    void setUp() {
        drone = mock(Drone.class);
        when(drone.id()).thenReturn(new DroneID("DRONE123"));

        maintenanceType = mock(MaintenanceType.class);
        when(maintenanceType.name()).thenReturn("Battery Check");

        date = LocalDate.of(2024, 5, 20);
    }

    @Test
    void ensureValidMaintenanceRecordIsCreated() {
        DroneMaintenance record = new DroneMaintenance(drone, maintenanceType, date);
        assertNotNull(record);
        assertEquals(drone, record.drone());
        assertEquals(maintenanceType, record.maintenanceType());
        assertEquals(date, record.date());
    }

    @Test
    void ensureNullDroneIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DroneMaintenance(null, maintenanceType, date);
        });
    }

    @Test
    void ensureNullMaintenanceTypeIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DroneMaintenance(drone, null, date);
        });
    }

    @Test
    void ensureNullDateIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DroneMaintenance(drone, maintenanceType, null);
        });
    }

    @Test
    void testEqualityWithSameId() {
        DroneMaintenance r1 = new DroneMaintenance(drone, maintenanceType, date);
        DroneMaintenance r2 = new DroneMaintenance(drone, maintenanceType, date);

        // Using reflection to simulate DB-assigned ID
        var id = 1L;
        setId(r1, id);
        setId(r2, id);

        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void testInequalityWithDifferentIds() {
        DroneMaintenance r1 = new DroneMaintenance(drone, maintenanceType, date);
        DroneMaintenance r2 = new DroneMaintenance(drone, maintenanceType, date);

        setId(r1, 1L);
        setId(r2, 2L);

        assertNotEquals(r1, r2);
    }

    // Helper method to set ID via reflection (used only in tests)
    private void setId(DroneMaintenance record, Long idValue) {
        try {
            var field = DroneMaintenance.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(record, idValue);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set ID via reflection", e);
        }
    }
}
