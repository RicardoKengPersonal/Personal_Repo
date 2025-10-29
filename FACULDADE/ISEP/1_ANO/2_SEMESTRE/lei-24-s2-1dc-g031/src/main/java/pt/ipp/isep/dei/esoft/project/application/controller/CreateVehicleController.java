package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
/**
 * The CreateVehicleController class manages the creation of Vehicle objects.
 * It interacts with the VehicleRepository to add vehicles to the system.
 */
public class CreateVehicleController {
    private VehicleRepository vehicleRepository;

    /**
     * Constructs a new CreateVehicleController object and initializes the vehicle repository.
     */
    public CreateVehicleController() {
        this.vehicleRepository = getVehicleRepository();
    }

    /**
     * Retrieves the vehicle repository instance.
     * If the repository instance is not yet initialized, it initializes it from Repositories singleton.
     * @return The vehicle repository instance
     */
    private VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();
            vehicleRepository = repositories.getInstance().getVehicleRepository();
        }
        return vehicleRepository;
    }

    /**
     * Sets the vehicle repository instance.
     * This method is used for dependency injection during testing.
     * @param vehicleRepository The vehicle repository instance to set
     */
    public void setVehicleRepository(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Adds a new vehicle to the system.
     * @param plate The license plate of the vehicle
     * @param brand The brand of the vehicle
     * @param model The model of the vehicle
     * @param type The type of the vehicle
     * @param tareWeight The tare weight of the vehicle
     * @param grossWeight The gross weight of the vehicle
     * @param currentKm The current kilometers traveled by the vehicle
     * @param registerDate The registration date of the vehicle
     * @param acquisitionDate The acquisition date of the vehicle
     * @param maintenanceFrequency The maintenance frequency of the vehicle
     * @return True if the vehicle was successfully added, false otherwise
     */
    public boolean addVehicle(String plate, String brand, String model, String type, float tareWeight,
                              float grossWeight, float currentKm, String registerDate, String acquisitionDate,
                              float maintenanceFrequency) {
        Vehicle vehicle = new Vehicle(plate, brand, model, type, tareWeight, grossWeight,
                currentKm, registerDate, acquisitionDate, maintenanceFrequency);
        return vehicleRepository.addVehicle(vehicle);
    }
}
