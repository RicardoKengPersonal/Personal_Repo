/**
 * The CreateCollaboratorAssignJobGUI class represents the graphical user interface for creating collaborators and assigning jobs to them.
 * It allows the user to input collaborator details such as name, birth date, address, etc., and assign them to a job.
 */
package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateCollaboratorAssignJobController;
import pt.ipp.isep.dei.esoft.project.domain.DocumentType;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.io.IOException;

public class CreateCollaboratorAssignJobGUI {

    // JavaFX Controls
    @FXML
    private TextField nameField;
    @FXML
    private TextField birthDateField;
    @FXML
    private TextField admissionDateField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField mobileNumberField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField taxPayerNumberField;
    @FXML
    private ComboBox<DocumentType> documentTypeComboBox;
    @FXML
    private TextField documentIdentificationNumberField;
    @FXML
    private ComboBox<Job> jobComboBox;

    // Controller
    private CreateCollaboratorAssignJobController controller;

    /**
     * Constructs an instance of CreateCollaboratorAssignJobGUI.
     */
    public CreateCollaboratorAssignJobGUI() {
        controller = new CreateCollaboratorAssignJobController();
    }

    /**
     * Initializes the GUI components.
     * Populates the ComboBoxes with data from the controller.
     */
    @FXML
    private void initialize() {
        documentTypeComboBox.getItems().addAll(controller.getDocTypesList());
        jobComboBox.getItems().addAll(controller.getJobsList());
    }

    /**
     * Handles the action when the submit button is clicked.
     */
    @FXML
    private void submit() {
        try {
            String name = nameField.getText();
            String birthDate = birthDateField.getText();
            String admissionDate = admissionDateField.getText();
            String address = addressField.getText();
            int mobileNumber = Integer.parseInt(mobileNumberField.getText());
            String email = emailField.getText();
            String taxPayerNumber = taxPayerNumberField.getText();
            DocumentType identificationDocumentType = documentTypeComboBox.getValue();
            String documentIdentificationNumber = documentIdentificationNumberField.getText();
            Job chosenJob = jobComboBox.getValue();

            boolean result = controller.addCollaborator(name, documentIdentificationNumber, taxPayerNumber, email, mobileNumber, address, admissionDate, birthDate, identificationDocumentType, chosenJob);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (result) {
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Collaborator added successfully");
            } else {
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("Collaborator could not be added");
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
     * @param event The ActionEvent triggered by the user.
     */
    @FXML
    public void goBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/HRMGUI.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
