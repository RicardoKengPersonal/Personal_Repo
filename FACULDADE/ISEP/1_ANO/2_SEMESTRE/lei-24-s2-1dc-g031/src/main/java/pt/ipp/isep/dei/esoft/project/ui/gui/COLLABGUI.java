/**
 * The COLLABGUI class represents the graphical user interface for collaborators.
 * It provides functionality for handling user actions such as logging out and accessing personal tasks.
 */
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

public class COLLABGUI {

    // Authentication Controller
    private final AuthenticationController ctrl;

    /**
     * Constructs an instance of COLLABGUI.
     */
    public COLLABGUI() {
        this.ctrl = new AuthenticationController();
    }

    /**
     * Handles the action when the logout button is clicked.
     *
     * @param event The ActionEvent triggered by the user.
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
     * Handles the action when the "My Tasks" button is clicked.
     *
     * @param event The ActionEvent triggered by the user.
     */
    @FXML
    private void handleMyTasksActionButton(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/MyTasks.fxml"));
            Parent root = fxmlLoader.load();

            // Get the stage from the event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene to the loaded FXML
            stage.setScene(new Scene(root));
            stage.setTitle("My Tasks");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
