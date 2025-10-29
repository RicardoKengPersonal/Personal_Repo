/**
 * The CreateSkillGUI class represents the graphical user interface for creating skills.
 * It allows the user to input a skill name and create a new skill.
 */
package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateSkillController;

import java.io.IOException;

public class CreateSkillGUI {

    // JavaFX Controls
    @FXML
    private TextField skillNameField;

    // Controller
    private final CreateSkillController controller;

    /**
     * Constructs an instance of CreateSkillGUI.
     */
    public CreateSkillGUI() {
        this.controller = new CreateSkillController();
    }

    /**
     * Handles the action when the create skill button is clicked.
     */
    @FXML
    private void handleCreateSkillButtonAction() {
        String skillName = skillNameField.getText();

        if (skillName.isEmpty()) {
            showAlert("Please enter a skill name.");
        } else {
            boolean success = controller.createSkill(skillName);
            if (success) {
                showAlert("Skill created successfully: " + skillName);
            } else {
                showAlert("Failed to create skill. Please try again.");
            }
        }
    }

    /**
     * Handles the action when the go back button is clicked.
     */
    @FXML
    private void handleGoBackButtonAction() {
        // Get the stage for the current window and close it
        Stage stage = (Stage) skillNameField.getScene().getWindow();
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
        alert.setTitle("Create Skill");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
