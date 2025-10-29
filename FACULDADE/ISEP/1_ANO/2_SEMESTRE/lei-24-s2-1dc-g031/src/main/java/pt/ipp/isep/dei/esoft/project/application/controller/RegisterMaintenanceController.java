package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.VehicleCheckup;
import pt.ipp.isep.dei.esoft.project.repository.MaintenanceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

/**
 * The RegisterMaintenanceController class manages the registration of vehicle maintenance checkups.
 * It interacts with the MaintenanceRepository to register vehicle checkups in the system.
 */
public class RegisterMaintenanceController {
    private MaintenanceRepository maintenanceRepository;

    /**
     * Constructs a new RegisterMaintenanceController object and initializes the maintenance repository.
     */
    public RegisterMaintenanceController() {
        this.maintenanceRepository = getMaintenanceRepository();
    }

    /**
     * Sets the maintenance repository instance.
     * This method is used for dependency injection during testing.
     *
     * @param maintenanceRepository The maintenance repository instance to set
     */
    public void setMaintenanceRepository(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }
    /**
     * Retrieves the maintenance repository instance.
     * If the repository instance is not yet initialized, it initializes it from Repositories singleton.
     * @return The maintenance repository instance
     */
    private MaintenanceRepository getMaintenanceRepository() {
        if (maintenanceRepository == null) {
            Repositories repositories = Repositories.getInstance();
            maintenanceRepository = repositories.getInstance().getMaintenanceRepository();
        }
        return maintenanceRepository;
    }

    /**
     * Registers a vehicle maintenance checkup in the system.
     * @param plateNumber The license plate number of the vehicle
     * @param date The date of the checkup
     * @param kmAtCheckup The kilometers at the time of the checkup
     * @return True if the checkup was successfully registered, false otherwise
     */
    public boolean registerVehicleMaintenance(String plateNumber, String date, String kmAtCheckup) {
        VehicleCheckup vehicleCheckup = new VehicleCheckup(plateNumber, date, kmAtCheckup);
        return maintenanceRepository.registerVehicleMaintenance(vehicleCheckup);
    }
}

