package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;

import java.io.IOException;

/**
 * Controller class for the VFM GUI.
 */
public class VFMGUI {

    private final AuthenticationController ctrl;

    /**
     * Constructor for the VFMGUI class.
     */
    public VFMGUI() {
        this.ctrl = new AuthenticationController();
    }

    /**
     * Handles the action when the "Logout" button is clicked.
     *
     * @param event The ActionEvent triggered
     */
    @FXML
    private void handleLogoutButtonAction(ActionEvent event) {
        try {
            ctrl.doLogout();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/MainMenu.fxml"));
            Parent root = fxmlLoader.load();

            // Get the stage from the event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene to the loaded FXML
            stage.setScene(new Scene(root));
            stage.setTitle("Integrative Project - Group 031");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action when the "Create Vehicle" button is clicked.
     *
     * @param event The ActionEvent triggered
     */
    @FXML
    private void handleCreateVehicleButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/CreateVehicle.fxml"));
            Parent root = fxmlLoader.load();

            // Get the stage from the event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene to the loaded FXML
            stage.setScene(new Scene(root));
            stage.setTitle("Register Vehicle");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action when the "Register Vehicle Maintenance" button is clicked.
     *
     * @param event The ActionEvent triggered
     */
    @FXML
    private void handleRegisterVehicleMaintenanceButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/RegisterMaintenance.fxml"));
            Parent root = fxmlLoader.load();

            // Get the stage from the event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene to the loaded FXML
            stage.setScene(new Scene(root));
            stage.setTitle("Register Vehicle Checkup");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action when the "Assign Maintenance" button is clicked.
     *
     * @param event The ActionEvent triggered
     */
    @FXML
    private void handleAssignMaintenanceButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/AssignMaintenance.fxml"));
            Parent root = fxmlLoader.load();

            // Get the stage from the event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene to the loaded FXML
            stage.setScene(new Scene(root));
            stage.setTitle("Show Vehicles");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
