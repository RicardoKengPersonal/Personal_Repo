package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;

/**
 * Controller class for the Register Green Space GUI.
 */
public class RegisterGreenSpaceGUI {

    @FXML
    private TextField greenSpaceName;

    @FXML
    private TextField greenSpaceStreet;

    @FXML
    private ComboBox<String> greenSpaceType; // Change the type to String

    @FXML
    private TextField greenSpaceArea;

    private final RegisterGreenSpaceController registerGreenSpaceController;

    /**
     * Constructor for the RegisterGreenSpaceGUI class.
     */
    public RegisterGreenSpaceGUI() {
        this.registerGreenSpaceController = new RegisterGreenSpaceController();
    }

    /**
     * Handles the action when the "Go Back" button is clicked.
     */
    @FXML
    public void goBack() {
        // Get the stage for the current window and close it
        Stage stage = (Stage) greenSpaceArea.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/GSMGUI.fxml"))));
            stage.setTitle("GSM Menu");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action when the "Register Green Space" button is clicked.
     */
    @FXML
    private void registerGreenSpace() {
        try{
            String name = greenSpaceName.getText();
            String street = greenSpaceStreet.getText();
            GreenSpaceType type = GreenSpaceType.fromString(greenSpaceType.getValue()); // Convert the String to GreenSpaceType
            String area = greenSpaceArea.getText();

            boolean isCreated = registerGreenSpaceController.createGreenSpace(name, street, type, area);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if(isCreated){
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Green Space added successfully");
            }else{
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("Green Space could not be added");
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
}
