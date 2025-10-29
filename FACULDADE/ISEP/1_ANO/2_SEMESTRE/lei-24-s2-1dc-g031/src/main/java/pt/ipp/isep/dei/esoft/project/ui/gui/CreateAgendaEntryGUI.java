/**
 * The CreateAgendaEntryGUI class represents the graphical user interface for creating agenda entries.
 * It allows the user to select a task and specify a date for the agenda entry.
 */
package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Task;

import java.io.IOException;

public class CreateAgendaEntryGUI {

    // JavaFX Controls
    @FXML
    private TextField entryDateField;
    @FXML
    private ComboBox<Task> entryTaskComboBox;

    // Agenda Controller
    private AgendaController controller;

    /**
     * Constructs an instance of CreateAgendaEntryGUI.
     */
    public CreateAgendaEntryGUI() {
        controller = new AgendaController();
    }

    /**
     * Initializes the GUI components.
     * Populates the task ComboBox with data from the controller.
     */
    @FXML
    private void initialize() {
        entryTaskComboBox.getItems().addAll(controller.getToDoListTasks());
    }

    /**
     * Handles the action when the submit button is clicked.
     *
     * @param event The ActionEvent triggered by the user.
     */
    @FXML
    private void submit(ActionEvent event) {
        try {
            Task task = entryTaskComboBox.getValue();
            String entryDate = entryDateField.getText();

            boolean result = controller.addAgendaEntry(task, entryDate);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (result) {
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Agenda entry added successfully");
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/Agenda.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("Agenda entry could not be added");
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
            Parent root = FXMLLoader.load(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/Agenda.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
