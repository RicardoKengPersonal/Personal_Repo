package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.Task;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller class for the To-Do List GUI.
 */
public class ToDoListGUI implements Initializable {

    @FXML
    private ListView<Task> toDoListTasks;


    private final ToDoListController toDoListController;

    /**
     * Constructor for the ToDoListGUI class.
     */
    public ToDoListGUI() {
        this.toDoListController = new ToDoListController();
    }

    /**
     * Populates the task entries in the ListView.
     */
    public void populateEntries() {
        ArrayList<Task> toDoList = toDoListController.getToDoListTasks();
        toDoListTasks.setItems(FXCollections.observableArrayList(toDoList));
    }

    /**
     * Initializes the GUI.
     *
     * @param location  The URL location
     * @param resources The ResourceBundle resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateEntries();
    }

    /**
     * Handles the action when the "Go Back" button is clicked.
     */
    @FXML
    private void goBack() {
        // Get the stage for the current window and close it
        Stage stage = (Stage) toDoListTasks.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/GSMGUI.fxml"))));
            stage.setTitle("GSM Menu");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action when the "Create ToDoList Task" button is clicked.
     */
    public void handleCreateToDoListTask() {
        // Get the stage for the current window and close it
        Stage stage = (Stage) toDoListTasks.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/CreateToDoListTask.fxml"))));
            stage.setTitle("New Task");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
