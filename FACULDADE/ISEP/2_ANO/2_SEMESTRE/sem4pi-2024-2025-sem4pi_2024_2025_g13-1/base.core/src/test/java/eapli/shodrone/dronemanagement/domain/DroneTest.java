package eapli.shodrone.dronemanagement.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DroneTest {

    private DroneID droneID;
    private DroneModel model;
    private Drone drone;
    private LocalDate acquisitionDate;

    @BeforeEach
    void setUp() {
        droneID = new DroneID("SN123456");
        acquisitionDate = LocalDate.of(2025, 5, 4);
        model = new DroneModel(
                "TestModel",
                "DroneMaker",
                0.1f,
                0.1f,
                0.1f,
                Calendar.getInstance(),
                0.1f,
                0.1f,
                Set.of(DroneType.LED, DroneType.RGB)
        );
        drone = new Drone(droneID, "Java", "TestDrone", model, acquisitionDate);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(droneID, drone.id());
        assertEquals("TestDrone", drone.droneName());
        assertEquals(model, drone.model());
        assertEquals(acquisitionDate, drone.acquisitionDate());
        assertEquals(DroneStatus.ACTIVE, drone.status());
        assertEquals("Java", drone.programmingLanguage());
    }

    @Test
    void testMarkAsInRepair() {
        drone.markAsInRepair();
        assertEquals(DroneStatus.IN_REPAIR, drone.status());
    }

    @Test
    void testMarkAsDecommissioned() {
        drone.markAsDecommissioned();
        assertEquals(DroneStatus.DECOMMISSIONED, drone.status());
    }

    @Test
    void testMarkAsActive() {
        drone.markAsInRepair();
        drone.markAsActive();
        assertEquals(DroneStatus.ACTIVE, drone.status());
    }

    @Test
    void testRemoveFromInventoryToDecommissioned() {
        LocalDate removalDate = LocalDate.of(2025, 5, 5);
        String reason = "Old and damaged";

        drone.removeFromInventory(reason, removalDate, DroneStatus.DECOMMISSIONED);

        assertEquals(DroneStatus.DECOMMISSIONED, drone.status());
        assertEquals(reason, drone.removalReason());
        assertEquals(removalDate, drone.removalDate());
    }

    @Test
    void testRemoveFromInventoryToInRepair() {
        LocalDate removalDate = LocalDate.of(2025, 5, 6);
        String reason = "Camera malfunction";

        drone.removeFromInventory(reason, removalDate, DroneStatus.IN_REPAIR);

        assertEquals(DroneStatus.IN_REPAIR, drone.status());
        assertEquals(reason, drone.removalReason());
        assertEquals(removalDate, drone.removalDate());
    }

    @Test
    void testRemoveFromInventoryFailsIfStatusActive() {
        LocalDate removalDate = LocalDate.of(2025, 5, 7);
        String reason = "Invalid operation";

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> drone.removeFromInventory(reason, removalDate, DroneStatus.ACTIVE));
        assertEquals("Cannot set status to ACTIVE on removal.", ex.getMessage());
    }

    @Test
    void testEquality() {
        Drone another = new Drone(new DroneID("SN123456"), "Java", "TestDrone", model, acquisitionDate);
        assertEquals(drone, another);
        assertEquals(drone.hashCode(), another.hashCode());
    }

    @Test
    void testNotEqualDifferentID() {
        Drone different = new Drone(new DroneID("DIFFERENT"), "Java", "TestDrone", model, acquisitionDate);
        assertNotEquals(drone, different);
    }

    @Test
    void testIdentity() {
        assertEquals(droneID, drone.identity());
    }

    @Test
    void testSameAs() {
        Drone sameDrone = new Drone(new DroneID("SN123456"), "Java", "Other", model, acquisitionDate);
        assertTrue(drone.sameAs(sameDrone));
    }

    @Test
    void testSameAsFalse() {
        Drone differentDrone = new Drone(new DroneID("DIFFERENT"), "Java", "Other", model, acquisitionDate);
        assertFalse(drone.sameAs(differentDrone));
    }

    @Test
    void testToString() {
        String output = drone.toString();
        assertTrue(output.contains("Drone{"));
        assertTrue(output.contains("TestDrone"));
        assertTrue(output.contains("SN123456"));
    }

    @Test
    void testConstructorRejectsNullId() {
        assertThrows(IllegalArgumentException.class, () ->
                new Drone(null, "Java", "TestDrone", model, acquisitionDate));
    }

    @Test
    void testConstructorRejectsNullName() {
        assertThrows(IllegalArgumentException.class, () ->
                new Drone(droneID, "Java", null, model, acquisitionDate));
    }

    @Test
    void testConstructorRejectsNullModel() {
        assertThrows(IllegalArgumentException.class, () ->
                new Drone(droneID, "Java", "TestDrone", null, acquisitionDate));
    }

    @Test
    void testConstructorRejectsNullAcquisitionDate() {
        assertThrows(IllegalArgumentException.class, () ->
                new Drone(droneID, "Java", "TestDrone", model, null));
    }

    @Test
    void testAddValidMaintenanceRecord() {
        DroneMaintenance record = new DroneMaintenance(drone,
                new MaintenanceType("Battery Check", "Routine battery inspection"),
                LocalDate.of(2025, 5, 20));

        drone.addMaintenanceRecord(record);
        assertEquals(1, drone.maintenanceRecords().size());
        assertEquals(record, drone.maintenanceRecords().get(0));
    }

    @Test
    void testAddMaintenanceRecordFailsIfNull() {
        assertThrows(IllegalArgumentException.class, () -> drone.addMaintenanceRecord(null));
    }

    @Test
    void testAddMaintenanceRecordFailsIfDifferentDrone() {
        Drone otherDrone = new Drone(new DroneID("OTHER"), "Java", "Other", model, acquisitionDate);
        DroneMaintenance record = new DroneMaintenance(otherDrone,
                new MaintenanceType("Battery Check", "Routine battery inspection"),
                LocalDate.of(2025, 5, 20));

        assertThrows(IllegalArgumentException.class, () -> drone.addMaintenanceRecord(record));
    }

    @Test
    void testCannotAddMaintenanceToDecommissionedDrone() {
        drone.markAsDecommissioned();
        DroneMaintenance record = new DroneMaintenance(drone,
                new MaintenanceType("Motor Check", "Engine inspection"),
                LocalDate.of(2025, 5, 20));

        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> drone.addMaintenanceRecord(record));

        assertEquals("Cannot add maintenance to a decommissioned drone.", ex.getMessage());
    }

    @Test
    void testMaintenanceRecordsIsUnmodifiable() {
        DroneMaintenance record = new DroneMaintenance(drone,
                new MaintenanceType("Sensor Check", "Sensor calibration"),
                LocalDate.of(2025, 5, 21));

        drone.addMaintenanceRecord(record);
        var records = drone.maintenanceRecords();

        assertThrows(UnsupportedOperationException.class, () -> records.add(record));
    }
}
