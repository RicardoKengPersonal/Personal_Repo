package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.VehicleCheckup;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The MaintenanceRepository class represents a repository for storing and managing vehicle checkup records.
 * It allows the registration of vehicle maintenance checkups, retrieval of the most recent checkup for a specific vehicle,
 * and retrieval of all vehicle maintenance checkups stored in the repository.
 */
public class MaintenanceRepository implements Serializable {
    private ArrayList<VehicleCheckup> checkups;

    /**
     * Constructs a new MaintenanceRepository instance.
     * Initializes the list of vehicle checkups.
     */
    public MaintenanceRepository(){
        this.checkups = new ArrayList<>();
    }

    /**
     * Registers a vehicle maintenance checkup in the repository.
     *
     * @param vehicleCheckup The vehicle checkup to be registered.
     * @return True if the vehicle checkup was successfully registered, false otherwise.
     */
    public boolean registerVehicleMaintenance(VehicleCheckup vehicleCheckup){
        if (!checkups.contains(vehicleCheckup)) {
            return checkups.add(vehicleCheckup);
        }
        return false;
    }

    /**
     * Retrieves the most recent checkup for a specific vehicle.
     *
     * @param plateNumber The plate number of the vehicle.
     * @return The most recent vehicle checkup for the specified plate number, or null if no checkup is found.
     */
    public VehicleCheckup getMostRecentCheckup(String plateNumber) {
        VehicleCheckup mostRecentCheckup = null;
        float maxKm = Float.MIN_VALUE;

        for (VehicleCheckup checkup : checkups) {
            if (checkup.getPlate().equals(plateNumber)) {
                float kmAtCheckup = Float.parseFloat(checkup.getKmAtCheckup());
                if (kmAtCheckup > maxKm) {
                    maxKm = kmAtCheckup;
                    mostRecentCheckup = checkup;
                }
            }
        }

        return mostRecentCheckup;
    }

    /**
     * Retrieves all vehicle maintenance checkups stored in the repository.
     *
     * @return A list containing all vehicle maintenance checkups.
     */
    public ArrayList<VehicleCheckup> getAllVehiclesMaintenances() {
        return new ArrayList<>(checkups);
    }
}


