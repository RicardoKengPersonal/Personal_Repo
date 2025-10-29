package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.MyGreenSpacesController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller class for the My Green Spaces GUI.
 */
public class MyGreenSpacesGUI implements Initializable {

    @FXML
    private ListView<GreenSpace> greenSpacesList;

    private final MyGreenSpacesController myGreenSpacesController;
    private String currentUserEmail; // The email of the current user

    /**
     * Constructor for the MyGreenSpacesGUI class.
     */
    public MyGreenSpacesGUI() {
        this.myGreenSpacesController = new MyGreenSpacesController();
    }

    /**
     * Sets the email of the current user.
     *
     * @param currentUserEmail the email of the current user
     */
    public void setCurrentUserEmail(String currentUserEmail) {
        this.currentUserEmail = currentUserEmail;
    }

    /**
     * Initializes the GUI components.
     *
     * @param location  the location of the FXML file to initialize
     * @param resources the resources for the FXML file
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<GreenSpace> myGreenSpaces = myGreenSpacesController.getMyGreenSpaces();
        greenSpacesList.setItems(FXCollections.observableArrayList(myGreenSpaces));
    }

    /**
     * Handles the action when the "Go Back" button is clicked.
     */
    @FXML
    private void goBack() {
        // Get the stage for the current window and close it
        Stage stage = (Stage) greenSpacesList.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/GSMGUI.fxml"))));
            stage.setTitle("GSM Menu");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
