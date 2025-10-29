package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.MyTasksController;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.repository.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TaskStatus;

import java.util.ArrayList;

/**
 * Controller class for the My Tasks GUI.
 */
public class MyTasksGUI {

    @FXML
    private TextField startDateField;

    @FXML
    private TextField endDateField;

    @FXML
    private TableView<AgendaEntry> agendaEntriesTable;

    @FXML
    private TableColumn<AgendaEntry, String> entryColumn;

    @FXML
    private ComboBox<TaskStatus> taskStatusComboBox;

    @FXML
    private Button markAsDoneButton;

    private final MyTasksController myTasksController;

    /**
     * Constructor for the MyTasksGUI class.
     */
    public MyTasksGUI(){
        this.myTasksController = new MyTasksController();
    }

    /**
     * Initializes the GUI components.
     */
    public void initialize() {
        // Get the agenda entries that the collaborator is part of the team that is assigned to the agenda entry
        populateTable();
        taskStatusComboBox.getItems().setAll(TaskStatus.values());
        agendaEntriesTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                TaskStatus status = (TaskStatus) newValue.getAssociatedTask().getTaskStatus();
                if (status == TaskStatus.DONE || status == TaskStatus.CANCELLED) {
                    markAsDoneButton.setDisable(true);
                } else {
                    markAsDoneButton.setDisable(false);
                }
            } else {
                markAsDoneButton.setDisable(true);
            }
        });
    }

    /**
     * Populates the agenda entries table with data.
     */
    public void populateTable(){
        Agenda agendaRepository = Repositories.getInstance().getAgenda();
        ArrayList<AgendaEntry> collaboratorEntries = myTasksController.getMyAgendaEntries();

        // Populate the TableView
        agendaEntriesTable.getItems().setAll(collaboratorEntries);
        entryColumn.setCellValueFactory(cellData -> (cellData.getValue().toStringProperty()));
    }

    /**
     * Handles the action when the "Apply Filter" button is clicked.
     */
    @FXML
    public void handleApplyFilterButtonAction(){

        try {
            // Get the selected filter
            TaskStatus selectedFilter = taskStatusComboBox.getSelectionModel().getSelectedItem();
            String startDate = startDateField.getText();
            String endDate = endDateField.getText();

            ArrayList<AgendaEntry> collaboratorEntries;

            // Get the agenda entries that the collaborator is part of the team that is assigned to the agenda entry
            if(startDate != null && endDate != null){
                collaboratorEntries = myTasksController.getMyAgendaEntries(selectedFilter, startDate, endDate);
            }else{
                collaboratorEntries = myTasksController.getMyAgendaEntries(selectedFilter);
            }

            // Populate the TableView
            agendaEntriesTable.getItems().setAll(collaboratorEntries);
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Handles the action when the "Go Back" button is clicked.
     */
    @FXML
    private void goBack() {
        // Get the stage for the current window and close it
        Stage stage = (Stage) agendaEntriesTable.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/COLLABGUI.fxml"))));
            stage.setTitle("Collaborator Menu");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action when the "Mark Task as Done" button is clicked.
     */
    @FXML
    private void handleMarkTaskAsDone() {
        // Get the selected agenda entry
        AgendaEntry selectedEntry = agendaEntriesTable.getSelectionModel().getSelectedItem();

        if (selectedEntry != null) {
            // Set the task status to DONE
            selectedEntry.getAssociatedTask().setTaskStatus(TaskStatus.DONE);

            // Refresh the TableView
            populateTable();
        }
    }
}
