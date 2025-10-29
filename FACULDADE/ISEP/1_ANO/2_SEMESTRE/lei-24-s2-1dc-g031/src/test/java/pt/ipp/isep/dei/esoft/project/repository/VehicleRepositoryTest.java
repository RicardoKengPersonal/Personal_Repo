package pt.ipp.isep.dei.esoft.project.repository;


import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import static org.junit.jupiter.api.Assertions.*;

class VehicleRepositoryTest {

    @Test
    void testAddVehicle_Success() {
        VehicleRepository repository = new VehicleRepository();
        Vehicle vehicle = new Vehicle("AA-00-BB", "Toyota", "Corolla", "Sedan", 1200, 1500, 50000, "01-01-2022", "01-01-2021", 10000);

        assertTrue(repository.addVehicle(vehicle));
        assertFalse(repository.addVehicle(vehicle)); // Adding the same vehicle again should return false
    }
    @Test
    void testRemoveVehicle() {
        VehicleRepository repository = new VehicleRepository();
        Vehicle vehicle = new Vehicle("AA-00-BB", "Toyota", "Corolla", "Sedan", 1200, 1500, 50000, "01-01-2022", "01-01-2021", 10000);

        repository.addVehicle(vehicle);
        assertTrue(repository.removeVehicle(vehicle));
        assertFalse(repository.removeVehicle(vehicle)); // Removing the same vehicle again should return false
    }

    @Test
    void testUpdateVehicle() {
        VehicleRepository repository = new VehicleRepository();
        Vehicle vehicle = new Vehicle("AA-00-BB", "Toyota", "Corolla", "Sedan", 1200, 1500, 50000, "01-01-2022", "01-01-2021", 10000);

        repository.addVehicle(vehicle);

        // Modify some information
        vehicle.setBrand("Ford");
        vehicle.setModel("Focus");

        assertTrue(repository.updateVehicle(vehicle));

        // Check if the information is updated
        Vehicle updatedVehicle = repository.getVehicleByPlate("AA-00-BB");
        assertNotNull(updatedVehicle);
        assertEquals("Ford", updatedVehicle.getBrand());
        assertEquals("Focus", updatedVehicle.getModel());
    }

    @Test
    void testGetVehicleByPlate() {
        VehicleRepository repository = new VehicleRepository();
        Vehicle vehicle = new Vehicle("AA-00-BB", "Toyota", "Corolla", "Sedan", 1200, 1500, 50000, "01-01-2022", "01-01-2021", 10000);

        repository.addVehicle(vehicle);

        Vehicle retrievedVehicle = repository.getVehicleByPlate("AA-00-BB");
        assertNotNull(retrievedVehicle);
        assertEquals(vehicle, retrievedVehicle);
    }

    @Test
    void testGetAllVehicles() {
        VehicleRepository repository = new VehicleRepository();
        Vehicle vehicle1 = new Vehicle("AA-00-BB", "Toyota", "Corolla", "Sedan", 1200, 1500, 50000, "01-01-2022", "01-01-2021", 10000);
        Vehicle vehicle2 = new Vehicle("AB-01-CD", "Ford", "Focus", "Hatchback", 1100, 1400, 40000, "01-01-2022", "01-01-2021", 15000);

        repository.addVehicle(vehicle1);
        repository.addVehicle(vehicle2);

        assertEquals(2, repository.getAllVehicles().size());
    }
}
