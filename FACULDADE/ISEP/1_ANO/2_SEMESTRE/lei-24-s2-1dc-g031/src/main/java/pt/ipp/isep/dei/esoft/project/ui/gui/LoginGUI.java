package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.*;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The GUI controller for the login interface.
 */
public class LoginGUI {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    private final AuthenticationController ctrl;

    /**
     * Instantiates a new LoginGUI.
     */
    public LoginGUI() {
        this.ctrl = new AuthenticationController();
    }

    /**
     * Handles the login button action.
     */
    @FXML
    private void handleLoginButtonAction() {
        String email = emailField.getText();
        String password = passwordField.getText();

        boolean success = ctrl.doLogin(email, password);

        if (success) {
            List<UserRoleDTO> roles = ctrl.getUserRoles();
            if ((roles == null) || (roles.isEmpty())) {
                showAlert("No role assigned to user.");
            } else {
                UserRoleDTO role = selectsRole(roles);
                if (role != null) {
                    List<MenuItem> rolesUI = getMenuItemForRoles();
                    redirectToRoleUI(rolesUI, role);
                } else {
                    showAlert("No role selected.");
                }
            }
        } else {
            showAlert("Invalid email and/or password.");
        }
    }

    /**
     * Gets the menu items for different roles.
     *
     * @return the list of menu items
     */
    private List<MenuItem> getMenuItemForRoles() {
        List<MenuItem> rolesUI = new ArrayList<>();
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_HRM, new HRMUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_VFM, new VFMUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_GSM, new GSMUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_COLLAB, new COLLABUI()));
        return rolesUI;
    }

    /**
     * Redirects to the UI corresponding to the user's role.
     *
     * @param rolesUI the list of menu items for different roles
     * @param role    the user's role
     */
    private void redirectToRoleUI(List<MenuItem> rolesUI, UserRoleDTO role) {
        boolean found = false;
        for (MenuItem item : rolesUI) {
            if (item.hasDescription(role.getDescription())) {
                switch (role.getDescription()) {
                    case AuthenticationController.ROLE_HRM:
                        openHRMGUI();
                        break;
                    case AuthenticationController.ROLE_GSM:
                        openGSMGUI();
                        break;
                    case AuthenticationController.ROLE_VFM:
                        openVFMGUI();
                        break;
                    case AuthenticationController.ROLE_COLLAB:
                        openCOLLABGUI();
                        break;
                    default:
                        item.run();
                        break;
                }
                found = true;
                break;
            }
        }
        if (!found) {
            showAlert("There is no UI for users with role '" + role.getDescription() + "'");
        }
    }

    /**
     * Opens the Collaborator GUI.
     */
    private void openCOLLABGUI() {
        openNewStage("/pt/ipp/isep/dei/esoft/project/ui/gui/COLLABGUI.fxml", "Collaborator Menu");
    }

    /**
     * Opens the HRM GUI.
     */
    private void openHRMGUI() {
        openNewStage("/pt/ipp/isep/dei/esoft/project/ui/gui/HRMGUI.fxml", "HRM Menu");
    }

    /**
     * Opens the VFM GUI.
     */
    private void openVFMGUI() {
        openNewStage("/pt/ipp/isep/dei/esoft/project/ui/gui/VFMGUI.fxml", "VFM Menu");
    }

    /**
     * Opens the GSM GUI.
     */
    private void openGSMGUI() {
        openNewStage("/pt/ipp/isep/dei/esoft/project/ui/gui/GSMGUI.fxml", "GSM Menu");
    }

    /**
     * Selects the first role from the list.
     *
     * @param roles the list of user roles
     * @return the first user role
     */
    private UserRoleDTO selectsRole(List<UserRoleDTO> roles) {
        if (!roles.isEmpty()) {
            return roles.get(0);
        }
        return null;
    }

    /**
     * Shows an alert with the given message.
     *
     * @param message the message to display in the alert
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Opens a new stage with the specified FXML resource and title.
     *
     * @param resource the path to the FXML resource
     * @param title    the title of the stage
     */
    private void openNewStage(String resource, String title) {
        Stage stage = (Stage) emailField.getScene().getWindow();
        if (stage != null) {
            stage.close();
            Stage newStage = new Stage();
            try {
                newStage.setScene(new Scene(FXMLLoader.load(getClass().getResource(resource))));
                newStage.setTitle(title);
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Stage is null.");
        }
    }
}
