package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.application.controller.SendMailController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class represents the GUI for the Agenda.
 * It provides methods to handle user interactions with the GUI.
 */
public class AgendaGUI implements Initializable {
    @FXML
    private ListView<AgendaEntry> agendaEntryList;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;

    private AgendaController agendaController;

    /**
     * Constructor for the AgendaGUI class.
     * Initializes the AgendaController.
     */
    public AgendaGUI() {
        this.agendaController = new AgendaController();
    }

    /**
     * Populates the list of agenda entries in the GUI.
     */
    public void populateEntries(){
        ArrayList<AgendaEntry> agendaEntries = agendaController.getActiveAgendaEntries();
        agendaEntryList.setItems(FXCollections.observableArrayList(agendaEntries));
    }

    /**
     * Initializes the GUI.
     * This method is called after the FXML file has been loaded.
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateEntries();
        button1.setDisable(true);
        button2.setDisable(true);
        button3.setDisable(true);
        button4.setDisable(true);

        // Add a listener to the selected item property
        agendaEntryList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // If the selected item is null or already has a team, disable the buttons. Otherwise, enable them.
            boolean isDisabled = newValue == null;
            button1.setDisable(isDisabled);
            button2.setDisable(isDisabled);
            button3.setDisable(isDisabled);
            button4.setDisable(isDisabled);


            boolean alreadyHasTeam = true;
            if (newValue != null) {
                alreadyHasTeam = (newValue.getTeam() != null);
            }
            button3.setDisable(alreadyHasTeam);
        });
    }

    /**
     * Handles the action of the postpone button.
     * Calls the postpone method on the selected agenda entry.
     */
    @FXML
    private void handlePostponeButtonAction() {
        // Get the selected item from the agendaEntryList
        AgendaEntry selectedEntry = agendaEntryList.getSelectionModel().getSelectedItem();

        // Call the postpone method on the selected item
        if (selectedEntry != null) {
            postpone(selectedEntry);
        }
    }

    /**
     * Postpones an agenda entry.
     * @param agendaEntry The agenda entry to be postponed.
     */
    private void postpone(AgendaEntry agendaEntry) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Postpone Agenda Entry");
        dialog.setHeaderText("Enter the new date for the agenda entry:");
        dialog.setContentText("Date:");

        boolean validDate = false;
        boolean continueLoop = true;
        while (!validDate && continueLoop) {
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                String newDate = result.get();
                try {
                    agendaController.postponeAgendaEntry(agendaEntry, newDate);
                    populateEntries();

                    // Show a success message
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("The date has been successfully postponed.");
                    alert.showAndWait();

                    validDate = true;
                } catch (IllegalArgumentException e) {
                    // Show an error message if the new date is not valid
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Invalid Date");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            } else {
                // User cancelled the dialog
                continueLoop = false;
            }
        }
    }

    /**
     * Handles the action of the deactivate button.
     * Calls the deactivateAgendaEntry method on the selected agenda entry.
     */
    @FXML
    private void handleDeactivateButtonAction() {
        // Get the selected item from the agendaEntryList
        AgendaEntry selectedEntry = agendaEntryList.getSelectionModel().getSelectedItem();

        // Call the deactivateAgendaEntry method on the selected item
        if (selectedEntry != null) {
            // Show a confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to deactivate this agenda entry?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                agendaController.cancelTask(selectedEntry.getAssociatedTask());
                populateEntries();
            }
        }
    }

    /**
     * Handles the action of the assign team button.
     * Calls the assignTeam method on the selected agenda entry.
     */
    @FXML
    private void handleAssignTeamButtonAction() {
        // Get the selected item from the agendaEntryList
        AgendaEntry selectedEntry = agendaEntryList.getSelectionModel().getSelectedItem();
        ArrayList<Team> teams = agendaController.getTeams();

        // Call the assignTeam method on the selected item
        if (selectedEntry != null) {

            try {


                // Show a ChoiceDialog for the user to select a team
                ChoiceDialog<Team> dialog = new ChoiceDialog<>(null, teams);
                dialog.setTitle("Assign Team");
                dialog.setHeaderText("Select a team to assign to the agenda entry:");
                dialog.setContentText("Team:");

                // Show the ChoiceDialog and get the user's choice
                Optional<Team> result = dialog.showAndWait();
                if (result.isPresent()) {
                    Team selectedTeam = result.get();

                    // Check if the selected team is available
                    if (agendaController.assignTeamToAgendaEntry(selectedEntry, selectedTeam)) {
                        // Show a success alert
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Team successfully assigned to the agenda entry.");
                        alert.showAndWait();
                        // Refresh the list of agenda entries
                        populateEntries();
                    } else {
                        // Show an error alert
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Dialog");
                        alert.setHeaderText("Team Unavailable");
                        alert.setContentText("The selected team is not available for the duration of the task.");
                        alert.showAndWait();
                    }
                }
            }catch (Exception e){
                // Show an error alert
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    /**
     * Handles the action of the assign vehicles button.
     * Calls the assignVehicles method on the selected agenda entry.
     */
    @FXML
    private void handleAssignVehiclesButtonAction() {
        // Implementation omitted for brevity
    }

    /**
     * Assigns vehicles to an agenda entry.
     * @param agendaEntry The agenda entry to which vehicles will be assigned.
     */
    private void assignVehicles(AgendaEntry agendaEntry) {
        // Get the list of teams
        ArrayList<Vehicle> vehicles = agendaController.getUnassignedVehicles(agendaEntry);

        ChoiceDialog<Vehicle> dialog = new ChoiceDialog<>(null, vehicles);
        dialog.setTitle("Assign Vehicles");
        dialog.setHeaderText("Select one or more vehicles to assign to the agenda entry:");
        dialog.setContentText("Vehicle:");

        Optional<Vehicle> result = dialog.showAndWait();
        if (result.isPresent()) {
            Vehicle selectedVehicle = result.get();

            agendaEntry.addVehicle(selectedVehicle);
            populateEntries();
        }
    }

    /**
     * Handles the action of the go back button.
     * Closes the current window and opens the GSM Menu.
     */
    @FXML
    private void goBack() {
        // Get the stage for the current window and close it
        Stage stage = (Stage) agendaEntryList.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/GSMGUI.fxml"))));
            stage.setTitle("GSM Menu");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action of the create entry button.
     * Closes the current window and opens the New Agenda Entry window.
     */
    public void handleCreateEntryButtonAction() {
        // Get the stage for the current window and close it
        Stage stage = (Stage) agendaEntryList.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/CreateAgendaEntry.fxml"))));
            stage.setTitle("New Agenda Entry");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}