/**
 * The CreateJobGUI class represents the graphical user interface for creating jobs.
 * It allows the user to input a job name and create a new job.
 */
package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateJobController;

import java.io.IOException;

public class CreateJobGUI {

    // JavaFX Controls
    @FXML
    private TextField jobNameField;

    // Controller
    private final CreateJobController controller;

    /**
     * Constructs an instance of CreateJobGUI.
     */
    public CreateJobGUI() {
        this.controller = new CreateJobController();
    }

    /**
     * Handles the action when the create job button is clicked.
     */
    @FXML
    private void handleCreateJobButtonAction() {
        String jobName = jobNameField.getText();

        if (jobName.isEmpty()) {
            showAlert("Please enter a job name.");
        } else {
            boolean success = controller.createJob(jobName);
            if (success) {
                showAlert("Job created successfully: " + jobName);
            } else {
                showAlert("Failed to create job. Please try again.");
            }
        }
    }

    /**
     * Handles the action when the go back button is clicked.
     */
    @FXML
    private void handleGoBackButtonAction() {
        // Get the stage for the current window and close it
        Stage stage = (Stage) jobNameField.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/HRMGUI.fxml"))));
            stage.setTitle("HRM Menu");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays an alert with the given message.
     *
     * @param message The message to display in the alert.
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Create Job");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
