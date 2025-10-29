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
 * The GUI controller for the HRM (Human Resource Management) interface.
 */
public class HRMGUI {

    private final AuthenticationController ctrl;

    /**
     * Instantiates a new HRMGUI.
     */
    public HRMGUI() {
        this.ctrl = new AuthenticationController();
    }

    /**
     * Handles the action event when the logout button is clicked.
     *
     * @param event the action event
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
     * Handles the action event when the create skill button is clicked.
     *
     * @param event the action event
     */
    @FXML
    private void handleCreateSkillButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/CreateSkill.fxml"));
            Parent root = fxmlLoader.load();

            // Get the stage from the event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene to the loaded FXML
            stage.setScene(new Scene(root));
            stage.setTitle("Create Skill");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action event when the assign skill button is clicked.
     *
     * @param event the action event
     */
    @FXML
    private void handleAssignSkillButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/AssignSkillToCollaborator.fxml"));
            Parent root = fxmlLoader.load();

            // Get the stage from the event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene to the loaded FXML
            stage.setScene(new Scene(root));
            stage.setTitle("Assign Skill");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action event when the create job button is clicked.
     *
     * @param event the action event
     */
    @FXML
    private void handleCreateJobButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/CreateJob.fxml"));
            Parent root = fxmlLoader.load();

            // Get the stage from the event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene to the loaded FXML
            stage.setScene(new Scene(root));
            stage.setTitle("Create Job");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action event when the create collaborator button is clicked.
     *
     * @param event the action event
     */
    @FXML
    private void handleCreateCollaboratorButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/CreateCollaboratorAssignJob.fxml"));
            Parent root = fxmlLoader.load();

            // Get the stage from the event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene to the loaded FXML
            stage.setScene(new Scene(root));
            stage.setTitle("Create Collaborator and Assign Job");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action event when the generate team proposal button is clicked.
     *
     * @param event the action event
     */
    @FXML
    private void handleGenerateTeamProposalButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/GenerateTeamProposal.fxml"));
            Parent root = fxmlLoader.load();

            // Get the stage from the event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene to the loaded FXML
            stage.setScene(new Scene(root));
            stage.setTitle("Create Collaborator and Assign Job");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
