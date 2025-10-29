/**
 * The CreateVehicleGUI class represents the graphical user interface for creating vehicles.
 * It allows the user to input vehicle details such as plate, brand, model, type, weight, mileage,
 * registration date, acquisition date, and maintenance frequency.
 */
package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateVehicleController;

import java.io.IOException;

public class CreateVehicleGUI {

    // JavaFX Controls
    @FXML
    private TextField plateField;
    @FXML
    private TextField brandField;
    @FXML
    private TextField modelField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField tareWeightField;
    @FXML
    private TextField grossWeightField;
    @FXML
    private TextField currentKmField;
    @FXML
    private TextField registerDateField;
    @FXML
    private TextField acquisitionDateField;
    @FXML
    private TextField maintenanceFrequencyField;

    // Controller
    private CreateVehicleController controller;

    /**
     * Constructs an instance of CreateVehicleGUI.
     */
    public CreateVehicleGUI() {
        controller = new CreateVehicleController();
    }

    /**
     * Handles the action when the submit button is clicked.
     */
    @FXML
    private void submit() {
        try {
            String plate = plateField.getText();
            String brand = brandField.getText();
            String model = modelField.getText();
            String type = typeField.getText();
            float tareWeight = Float.parseFloat(tareWeightField.getText());
            float grossWeight = Float.parseFloat(grossWeightField.getText());
            float currentKm = Float.parseFloat(currentKmField.getText());
            String registerDate = registerDateField.getText();
            String acquisitionDate = acquisitionDateField.getText();
            float maintenanceFrequency = Float.parseFloat(maintenanceFrequencyField.getText());

            boolean result = controller.addVehicle(plate, brand, model, type, tareWeight, grossWeight,
                    currentKm, registerDate, acquisitionDate, maintenanceFrequency);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (result) {
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Vehicle registered successfully");
            } else {
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("Vehicle could not be registered");
            }
            alert.showAndWait();
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Handles the action when the go back button is clicked.
     */
    @FXML
    public void goBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/VFMGUI.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
