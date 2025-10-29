/**
 * The CreateToDoListTaskGUI class represents the graphical user interface for creating tasks in the to-do list.
 * It allows the user to input task details such as name, description, duration, green space, and urgency degree.
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
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.TaskUrgencyDegree;

import java.io.IOException;

public class CreateToDoListTaskGUI {

    // JavaFX Controls
    @FXML
    private TextField taskNameField;
    @FXML
    private TextField taskDescriptionField;
    @FXML
    private TextField taskDurationField;
    @FXML
    private ComboBox<String> taskDurationUnitComboBox;
    @FXML
    private ComboBox<GreenSpace> taskGreenSpaceComboBox;
    @FXML
    private ComboBox<TaskUrgencyDegree> taskUrgencyDegreeComboBox;

    // Controller
    private ToDoListController controller;

    /**
     * Constructs an instance of CreateToDoListTaskGUI.
     */
    public CreateToDoListTaskGUI() {
        controller = new ToDoListController();
    }

    /**
     * Initializes the GUI, populating ComboBoxes with data from the controller.
     */
    @FXML
    private void initialize() {
        taskGreenSpaceComboBox.getItems().addAll(controller.getMyGreenSpaces());
        taskUrgencyDegreeComboBox.getItems().addAll(controller.getUrgencyDegrees());
    }

    /**
     * Handles the action when the submit button is clicked.
     */
    @FXML
    private void submit(ActionEvent event) {
        try {
            GreenSpace greenSpace = taskGreenSpaceComboBox.getValue();
            String taskName = taskNameField.getText();
            String taskDescription = taskDescriptionField.getText();
            String taskDuration = taskDurationField.getText();
            String taskDurationUnit = taskDurationUnitComboBox.getValue();
            int taskDurationInMinutes = convertToMinutes(Integer.parseInt(taskDuration), taskDurationUnit);
            TaskUrgencyDegree taskUrgencyDegree = taskUrgencyDegreeComboBox.getValue();

            boolean result = controller.addTask(taskName, taskDescription, taskDurationInMinutes, greenSpace, taskUrgencyDegree);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (result) {
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Task added successfully");
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/ToDoList.fxml"));
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
                alert.setContentText("Task could not be added");
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
     * Converts the task duration to minutes based on the selected duration unit.
     *
     * @param duration The task duration value.
     * @param unit     The task duration unit (Hours, Days, or Minutes).
     * @return The task duration converted to minutes.
     */
    private int convertToMinutes(int duration, String unit) {
        switch (unit) {
            case "Hours":
                return duration * 60;
            case "Days":
                return duration * 8 * 60;
            default: // Minutes
                return duration;
        }
    }

    /**
     * Handles the action when the go back button is clicked.
     */
    @FXML
    public void goBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/ToDoList.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
