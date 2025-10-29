package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterMaintenanceController;

import java.io.IOException;

/**
 * Controller class for the Register Maintenance GUI.
 */
public class RegisterMaintenanceGUI {

    @FXML
    private TextField plateNumberField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField kmAtCheckupField;

    private final RegisterMaintenanceController controller;

    /**
     * Constructor for the RegisterMaintenanceGUI class.
     */
    public RegisterMaintenanceGUI() {
        controller = new RegisterMaintenanceController();
    }

    /**
     * Handles the action when the "Submit" button is clicked.
     */
    @FXML
    private void submit() {
        try {
            String plateNumber = plateNumberField.getText();
            String date = dateField.getText();
            String kmAtCheckup = kmAtCheckupField.getText();

            boolean result = controller.registerVehicleMaintenance(plateNumber, date, kmAtCheckup);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (result) {
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Checkup registered successfully");
            } else {
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("Checkup could not be registered");
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
     * Handles the action when the "Go Back" button is clicked.
     *
     * @param event the ActionEvent triggered by clicking the button
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
