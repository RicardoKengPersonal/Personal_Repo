package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.MaintenanceRepository;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The VehicleRepository class represents a repository for managing vehicles.
 * It provides methods to add, remove, update, and retrieve vehicles.
 */
public class VehicleRepository implements Serializable {
    private ArrayList<Vehicle> vehicles;

    /**
     * Constructs a new VehicleRepository object.
     */
    public VehicleRepository() {
        this.vehicles = new ArrayList<>();
    }

    /**
     * Adds a new vehicle to the repository.
     *
     * @param vehicle the vehicle to be added
     * @return true if the vehicle was successfully added, false if the vehicle already exists in the repository
     */
    public boolean addVehicle(Vehicle vehicle) {
        for (Vehicle v : vehicles) {
            if (v.getPlate().equals(vehicle.getPlate())) {
                return false; // Vehicle with the same plate already exists
            }
        }
        return vehicles.add(vehicle); // Add the vehicle as it does not exist in the list
    }

    /**
     * Removes a vehicle from the repository.
     *
     * @param vehicle the vehicle to be removed
     * @return true if the vehicle was successfully removed, false if the vehicle was not found in the repository
     */
    public boolean removeVehicle(Vehicle vehicle) {
        Vehicle vehicleToRemove = getVehicleByPlate(vehicle.getPlate());
        if (vehicleToRemove != null) {
            return vehicles.remove(vehicleToRemove);
        }
        return false;
    }

    /**
     * Updates information of a vehicle in the repository.
     *
     * @param vehicle the updated vehicle information
     * @return true if the vehicle information was successfully updated, false if the vehicle was not found in the repository
     */
    public boolean updateVehicle(Vehicle vehicle) {
        Vehicle vehicleExists = getVehicleByPlate(vehicle.getPlate());
        if (vehicleExists != null) {
            vehicleExists.setPlate(vehicle.getPlate());
            vehicleExists.setBrand(vehicle.getBrand());
            vehicleExists.setModel(vehicle.getModel());
            vehicleExists.setType(vehicle.getType());
            vehicleExists.setTareWeight(vehicle.getTareWeight());
            vehicleExists.setGrossWeight(vehicle.getGrossWeight());
            vehicleExists.setCurrentKm(vehicle.getCurrentKm());
            vehicleExists.setRegisterDate(vehicle.getRegisterDate());
            vehicleExists.setAcquisitionDate(vehicle.getAcquisitionDate());
            vehicleExists.setMaintenanceFrequency(vehicle.getMaintenanceFrequency());
            return true;
        }
        return false;
    }

    /**
     * Retrieves a vehicle from the repository by its plate number.
     *
     * @param plate the plate number of the vehicle to retrieve
     * @return the vehicle with the specified plate number, or null if no such vehicle is found
     */
    public Vehicle getVehicleByPlate(String plate) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPlate().equals(plate)) {
                return vehicle;
            }
        }
        return null;
    }

    /**
     * Retrieves a copy of all vehicles stored in the repository.
     *
     * @return a list containing copies of all vehicles in the repository
     */
    public ArrayList<Vehicle> getAllVehicles() {
        return new ArrayList<>(vehicles);
    }
}

