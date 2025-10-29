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
 * The GUI controller for the GSM (Green Space Manager) interface.
 */
public class GSMGUI {

    private final AuthenticationController ctrl;

    /**
     * Instantiates a new GSMGUI.
     */
    public GSMGUI() {
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
     * Handles the action event when the register green space button is clicked.
     *
     * @param event the action event
     */
    @FXML
    private void handleRegisterGreenSpaceButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/RegisterGreenSpace.fxml"));
            Parent root = fxmlLoader.load();

            // Get the stage from the event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene to the loaded FXML
            stage.setScene(new Scene(root));
            stage.setTitle("Register Green Space");
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
     * Handles the action event when the my green spaces button is clicked.
     *
     * @param event the action event
     */
    @FXML
    private void handleMyGreenSpacesButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/MyGreenSpaces.fxml"));
            Parent root = fxmlLoader.load();

            // Get the stage from the event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene to the loaded FXML
            stage.setScene(new Scene(root));
            stage.setTitle("My Green Spaces");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action event when the to-do list button is clicked.
     *
     * @param event the action event
     */
    @FXML
    private void handleToDoListButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/ToDoList.fxml"));
            Parent root = fxmlLoader.load();

            // Get the stage from the event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene to the loaded FXML
            stage.setScene(new Scene(root));
            stage.setTitle("To-Do List");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action event when the agenda button is clicked.
     *
     * @param event the action event
     */
    @FXML
    private void handleAgendaButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/Agenda.fxml"));
            Parent root = fxmlLoader.load();

            // Get the stage from the event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene to the loaded FXML
            stage.setScene(new Scene(root));
            stage.setTitle("Agenda");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
