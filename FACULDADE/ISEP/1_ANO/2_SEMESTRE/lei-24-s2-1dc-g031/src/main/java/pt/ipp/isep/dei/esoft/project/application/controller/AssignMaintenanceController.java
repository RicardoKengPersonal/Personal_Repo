package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.VehicleCheckup;
import pt.ipp.isep.dei.esoft.project.repository.MaintenanceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.ArrayList;

/**
 * The AssignMaintenanceController class represents the controller responsible for managing the assignment of maintenance tasks to vehicles.
 * It interacts with the repositories to retrieve information about vehicles and maintenance checkups, and it provides methods to display lists of vehicles
 * and determine which vehicles need maintenance based on certain criteria.
 */
public class AssignMaintenanceController {
    private VehicleRepository vehicleRepository;
    private MaintenanceRepository maintenanceRepository;

    /**
     * Constructs a new AssignMaintenanceController.
     */
    public AssignMaintenanceController(){
        this.vehicleRepository = getVehicleRepository();
        this.maintenanceRepository = getMaintenanceRepository();
    }

    /**
     * Retrieves the vehicle repository instance.
     * If the repository instance is not initialized, it initializes it using the Repositories singleton.
     *
     * @return The vehicle repository instance.
     */
    private VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();
            vehicleRepository = repositories.getInstance().getVehicleRepository();
        }
        return vehicleRepository;
    }

    /**
     * Retrieves the maintenance repository instance.
     * If the repository instance is not initialized, it initializes it using the Repositories singleton.
     *
     * @return The maintenance repository instance.
     */
    private MaintenanceRepository getMaintenanceRepository() {
        if (maintenanceRepository == null) {
            Repositories repositories = Repositories.getInstance();
            maintenanceRepository = repositories.getInstance().getMaintenanceRepository();
        }
        return maintenanceRepository;
    }

    /**
     * Displays the list of vehicles.
     * If no vehicles are found, it prints a message indicating so.
     */
    public void displayVehiclesList() {
        ArrayList<Vehicle> vehicles = vehicleRepository.getAllVehicles();

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            System.out.println("Vehicles List:");
            for (Vehicle vehicle : vehicles) {
                System.out.println("Plate: " + vehicle.getPlate());
                System.out.println("Brand: " + vehicle.getBrand());
                System.out.println("Model: " + vehicle.getModel());
                System.out.println("Type: " + vehicle.getType());
                System.out.println("Tare weight: " + vehicle.getTareWeight());
                System.out.println("Gross weight: " + vehicle.getGrossWeight());
                System.out.println("Current km: " + vehicle.getCurrentKm());
                System.out.println("Register date: " + vehicle.getRegisterDate());
                System.out.println("Acquisition Date: " + vehicle.getAcquisitionDate());
                System.out.println("Maintenance Frequency:"+vehicle.getMaintenanceFrequency());
                System.out.println("------------------------------");
            }
        }
    }

    /**
     * Determines which vehicles need maintenance and displays them.
     * If no vehicles need maintenance, it prints a message indicating so.
     */
    public void getVehiclesNeedingMaintenance() {
        ArrayList<Vehicle> vehicles = vehicleRepository.getAllVehicles();
        ArrayList<VehicleCheckup> checkups = maintenanceRepository.getAllVehiclesMaintenances();

        ArrayList<Vehicle> vehiclesNeedingMaintenance = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            VehicleCheckup lastCheckup = null;
            float maxKm = Float.MIN_VALUE;
            for (VehicleCheckup checkup : checkups) {
                if (checkup.getPlate().equals(vehicle.getPlate())) {
                    float kmAtCheckup = Float.parseFloat(checkup.getKmAtCheckup());
                    if (kmAtCheckup > maxKm) {
                        maxKm = kmAtCheckup;
                        lastCheckup = checkup;
                    }
                }
            }

            // Determine if the vehicle needs maintenance
            if (lastCheckup != null) {
                float kmAtLastCheckup = Float.parseFloat(lastCheckup.getKmAtCheckup());
                if (kmAtLastCheckup + vehicle.getMaintenanceFrequency() < vehicle.getCurrentKm() ||
                        kmAtLastCheckup + vehicle.getMaintenanceFrequency() - (vehicle.getMaintenanceFrequency() * 0.05) < vehicle.getCurrentKm()) {
                    vehiclesNeedingMaintenance.add(vehicle);
                }
            }
        }

        // Print the vehicles needing maintenance
        if (!vehiclesNeedingMaintenance.isEmpty()) {
            System.out.println("Vehicles needing maintenance:");
            for (Vehicle vehicle : vehiclesNeedingMaintenance) {
                System.out.println("Plate: " + vehicle.getPlate());
                System.out.println("Brand: " + vehicle.getBrand());
                System.out.println("Model: " + vehicle.getModel());
                System.out.println("Curr. KM: " + vehicle.getCurrentKm());
                System.out.println("Maintenance frequency: " + vehicle.getMaintenanceFrequency());

                // Find the last checkup for this vehicle (the one with the highest kilometers)
                VehicleCheckup lastCheckup = null;
                float maxKm = Float.MIN_VALUE;
                for (VehicleCheckup checkup : checkups) {
                    if (checkup.getPlate().equals(vehicle.getPlate())) {
                        float kmAtCheckup = Float.parseFloat(checkup.getKmAtCheckup());
                        if (kmAtCheckup > maxKm) {
                            maxKm = kmAtCheckup;
                            lastCheckup = checkup;
                        }
                    }
                }

                // Print the kilometers at the last checkup
                if (lastCheckup != null) {
                    System.out.println("Kilometers at last checkup: " + lastCheckup.getKmAtCheckup());
                    System.out.println("Next checkup: "+(lastCheckup.getKmAtCheckup()+vehicle.getCurrentKm()));
                } else {
                    System.out.println("No checkup found for this vehicle.");
                }

                System.out.println("-----------------------------");
            }
        } else {
            System.out.println("No vehicles need maintenance at the moment.");
        }
    }
}
