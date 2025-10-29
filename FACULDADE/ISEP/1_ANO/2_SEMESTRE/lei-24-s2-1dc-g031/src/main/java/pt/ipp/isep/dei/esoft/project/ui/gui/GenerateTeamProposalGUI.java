/**
 * The GenerateTeamProposalGUI class represents the graphical user interface for generating team proposals.
 * It allows the user to input criteria such as the maximum and minimum number of collaborators and select
 * specific skills for the team proposal.
 */
package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamProposalController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class GenerateTeamProposalGUI {

    // JavaFX Controls
    @FXML
    private TextField maxCollaboratorsField;
    @FXML
    private TextField minCollaboratorsField;
    @FXML
    private ListView<Skill> skillsListView;

    // Controller
    private GenerateTeamProposalController controller;

    // Selected Skills
    private ArrayList<Skill> selectedSkills;

    /**
     * Constructs an instance of GenerateTeamProposalGUI.
     */
    public GenerateTeamProposalGUI() {
        controller = new GenerateTeamProposalController();
        selectedSkills = new ArrayList<>();
    }

    /**
     * Initializes the GUI.
     */
    @FXML
    private void initialize() {
        var skills = controller.getSkillsList();
        skillsListView.getItems().addAll(skills);
        skillsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Add the CSS file to the ListView's stylesheets
        skillsListView.getStylesheets().add(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/css/GenerateTeamProposal.css").toExternalForm());
    }

    /**
     * Adds selected skills to the list of selectedSkills.
     */
    @FXML
    private void addSkill() {
        ObservableList<Skill> selectedSkillsList = skillsListView.getSelectionModel().getSelectedItems();
        for (Skill skill : selectedSkillsList) {
            if (!selectedSkills.contains(skill)) {
                selectedSkills.add(skill);
            }
        }
    }

    /**
     * Clears the list of selected skills.
     */
    @FXML
    private void clearSkills() {
        selectedSkills.clear();
        skillsListView.getSelectionModel().clearSelection();
    }

    /**
     * Generates team proposals based on the user's input criteria.
     */
    @FXML
    private void generateTeamProposal() {
        try {
            int max = Integer.parseInt(maxCollaboratorsField.getText());
            int min = Integer.parseInt(minCollaboratorsField.getText());

            var teams = controller.createTeamProposal(max, min, selectedSkills);

            // Inform the user about the number of available teams
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION, "You're about to see " + teams.size() + " teams. You'll be asked for confirmation for each team.", ButtonType.OK);
            infoAlert.showAndWait();

            // Iterate over the teams
            boolean showingTeams = true;
            for (int i = 0; i < teams.size() && showingTeams; i++) {
                ArrayList<Collaborator> team = teams.get(i);

                // Create a StringBuilder and append the team's information
                StringBuilder teamBuilder = new StringBuilder();
                teamBuilder.append("Team:\n");
                for (Collaborator collaborator : team) {
                    teamBuilder.append(collaborator.getName()).append(": ");
                    for (Skill skill : collaborator.getSkills()) {
                        teamBuilder.append(skill.getNameOfTheSkill()).append(", ");
                    }
                    teamBuilder.append("\n");
                }

                // Create an alert with the team's information and a confirmation button
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to accept this team?\n\n" + teamBuilder.toString(), ButtonType.YES, ButtonType.NO);
                alert.setTitle("Team Proposal");
                alert.setHeaderText(null);

                // Show the alert and wait for the user's response
                ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

                // If the user confirms, stop showing the teams
                if (result == ButtonType.YES) {
                    Team createdTeam = controller.createAndRegisterTeam(team, selectedSkills);
                    if (createdTeam != null) {
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Team created and registered successfully!", ButtonType.OK);
                        successAlert.showAndWait();
                    }
                    showingTeams = false;
                }
            }

        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (InputMismatchException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Handles the action when the go back button is clicked.
     */
    @FXML
    public void goBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/HRMGUI.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
