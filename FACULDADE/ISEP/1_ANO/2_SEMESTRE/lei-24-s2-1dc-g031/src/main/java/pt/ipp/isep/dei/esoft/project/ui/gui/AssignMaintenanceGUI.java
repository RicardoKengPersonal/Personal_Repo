/**
 * The AssignMaintenanceGUI class represents the graphical user interface for assigning maintenance to vehicles.
 * It allows the user to view a list of all vehicles and a list of vehicles needing maintenance.
 * Users can navigate back to the main menu using the "Go Back" button.
 */
        package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignMaintenanceController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.VehicleCheckup;
import pt.ipp.isep.dei.esoft.project.repository.MaintenanceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AssignMaintenanceGUI {

    // Map to store vehicles by plate
    private Map<String, Vehicle> vehicleMap = new HashMap<>();

    // JavaFX Controls
    @FXML
    private ListView<String> vehiclesListView;

    @FXML
    private Button goBackButton;

    @FXML
    private ListView<String> vehiclesNeedingMaintenanceListView;

    // Controller and repositories
    private AssignMaintenanceController assignMaintenanceController;
    private VehicleRepository vehicleRepository;
    private MaintenanceRepository maintenanceRepository;

    /**
     * Constructs an instance of AssignMaintenanceGUI.
     */
    public AssignMaintenanceGUI() {
        this.assignMaintenanceController = new AssignMaintenanceController();
        this.vehicleRepository = getVehicleRepository();
        this.maintenanceRepository = getMaintenanceRepository();
    }

    /**
     * Retrieves the vehicle repository instance.
     *
     * @return The VehicleRepository instance.
     */
    private VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();
            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }

    /**
     * Retrieves the maintenance repository instance.
     *
     * @return The MaintenanceRepository instance.
     */
    private MaintenanceRepository getMaintenanceRepository() {
        if (maintenanceRepository == null) {
            Repositories repositories = Repositories.getInstance();
            maintenanceRepository = repositories.getMaintenanceRepository();
        }
        return maintenanceRepository;
    }

    /**
     * Initializes the GUI components and populates the list views.
     */
    @FXML
    public void initialize() {
        populateVehicleListView();
        populateVehiclesNeedingMaintenanceListView();
    }

    /**
     * Populates the vehicle list view with all vehicles.
     */
    private void populateVehicleListView() {
        ArrayList<Vehicle> vehicles = vehicleRepository.getAllVehicles();
        ObservableList<String> vehicleItems = FXCollections.observableArrayList();
        for (Vehicle vehicle : vehicles) {
            vehicleItems.add(formatAllVehicleDetails(vehicle));
        }
        vehiclesListView.setItems(vehicleItems);
    }

    /**
     * Populates the list view of vehicles needing maintenance.
     */
    private void populateVehiclesNeedingMaintenanceListView() {
        ArrayList<Vehicle> vehicles = vehicleRepository.getAllVehicles();
        ArrayList<VehicleCheckup> checkups = maintenanceRepository.getAllVehiclesMaintenances();
        ObservableList<String> maintenanceItems = FXCollections.observableArrayList();

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

            if (lastCheckup != null) {
                float kmAtLastCheckup = Float.parseFloat(lastCheckup.getKmAtCheckup());
                if (kmAtLastCheckup + vehicle.getMaintenanceFrequency() < vehicle.getCurrentKm() ||
                        kmAtLastCheckup + vehicle.getMaintenanceFrequency() - (vehicle.getMaintenanceFrequency() * 0.05) < vehicle.getCurrentKm()) {
                    maintenanceItems.add(formatVehicleDetails(vehicle));
                }
            }
        }

        vehiclesNeedingMaintenanceListView.setItems(maintenanceItems);
    }

    /**
     * Formats vehicle details for display.
     *
     * @param vehicle The vehicle to format.
     * @return A string containing formatted vehicle details.
     */
    private String formatVehicleDetails(Vehicle vehicle) {
        return "Plate: " + vehicle.getPlate() +
                ", Brand: " + vehicle.getBrand() +
                ", Model: " + vehicle.getModel() +
                ", Curr. KM: " + vehicle.getCurrentKm() +
                ", Maintenance frequency: " + vehicle.getMaintenanceFrequency();
    }

    /**
     * Formats all vehicle details for display.
     *
     * @param vehicle The vehicle to format.
     * @return A string containing formatted vehicle details.
     */
    private String formatAllVehicleDetails(Vehicle vehicle) {
        return "Plate: " + vehicle.getPlate() +
                ", Brand: " + vehicle.getBrand() +
                ", Model: " + vehicle.getModel() +
                ", Type: " + vehicle.getType() +
                ", Tare Weight (kg): " + vehicle.getTareWeight() +
                ", Gross weight (kg): " + vehicle.getModel() +
                ", Register Date: " + vehicle.getRegisterDate() +
                ", Acquisition Date: " + vehicle.getAcquisitionDate() +
                ", Curr. KM: " + vehicle.getCurrentKm() +
                ", Maintenance frequency: " + vehicle.getMaintenanceFrequency();
    }

    /**
     * Handles the action when the "Go Back" button is clicked.
     */
    @FXML
    public void goBack() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/VFMGUI.fxml"));

            // Create a new scene with the loaded root
            Scene scene = new Scene(root);

            // Get the current stage
            Stage stage = (Stage) goBackButton.getScene().getWindow();

            // Set the new scene to the stage
            stage.setScene(scene);

            // Show the stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}