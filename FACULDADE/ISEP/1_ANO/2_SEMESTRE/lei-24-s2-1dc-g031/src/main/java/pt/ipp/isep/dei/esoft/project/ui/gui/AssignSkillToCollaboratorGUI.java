/**
 * The AssignSkillToCollaboratorGUI class represents the graphical user interface for assigning skills to collaborators.
 * It allows the user to select a collaborator and assign one or more skills to them.
 * Users can navigate back to the HRM menu using the "Go Back" button.
 */
package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorsRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillsRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssignSkillToCollaboratorGUI {

    // JavaFX Controls
    @FXML
    private ListView<String> collaboratorListView;
    @FXML
    private Button assignSkillButton;
    @FXML
    private Button goBackButton;
    @FXML
    private ListView<String> skillListView;

    // Map to store collaborators by name
    private Map<String, Collaborator> collaboratorMap = new HashMap<>();

    // Controller and repositories
    private AssignSkillController assignSkillController;
    private SkillsRepository skillsRepository;
    private CollaboratorsRepository collaboratorsRepository;
    private ArrayList<Skill> skills;
    private ArrayList<Collaborator> collaborators;

    /**
     * Constructs an instance of AssignSkillToCollaboratorGUI.
     */
    public AssignSkillToCollaboratorGUI() {
        this.skillsRepository = getSkillsRepository();
        this.collaboratorsRepository = getCollaboratorsRepository();
        this.assignSkillController = new AssignSkillController();
    }

    /**
     * Retrieves the skills repository instance.
     *
     * @return The SkillsRepository instance.
     */
    private SkillsRepository getSkillsRepository() {
        if (skillsRepository == null) {
            Repositories repositories = Repositories.getInstance();
            skillsRepository = repositories.getInstance().getSkillsRepository();
        }
        return skillsRepository;
    }

    /**
     * Retrieves the collaborators repository instance.
     *
     * @return The CollaboratorsRepository instance.
     */
    private CollaboratorsRepository getCollaboratorsRepository() {
        if (collaboratorsRepository == null) {
            Repositories repositories = Repositories.getInstance();
            collaboratorsRepository = repositories.getInstance().getCollaboratorsRepository();
        }
        return collaboratorsRepository;
    }

    /**
     * Initializes the GUI components and populates the list views.
     */
    @FXML
    public void initialize() {
        skills = skillsRepository.getSkillsArrayList();
        collaborators = collaboratorsRepository.getCollaboratorsArrayList();
        for (Collaborator collaborator : collaborators) {
            collaboratorMap.put(collaborator.getName(), collaborator);
        }
        populateCollaboratorListView();
        populateSkillListView();
        // Set selection mode to multiple for skillListView
        skillListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    /**
     * Populates the collaborator list view with all collaborators.
     */
    private void populateCollaboratorListView() {
        ObservableList<String> collaboratorItems = FXCollections.observableArrayList();
        for (Collaborator collaborator : collaborators) {
            collaboratorItems.add(collaborator.getName()); // Assuming Collaborator has a 'getName' method
        }
        collaboratorListView.setItems(collaboratorItems);
    }

    /**
     * Handles the action when the "Go Back" button is clicked.
     */
    @FXML
    public void goBack() {
        try {
            // Load the HRMGUI FXML file
            Parent root = FXMLLoader.load(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/HRMGUI.fxml"));

            // Create a new scene with the loaded root
            Scene scene = new Scene(root);

            // Get the current stage
            Stage stage = (Stage) goBackButton.getScene().getWindow();

            // Set the new scene to the stage
            stage.setScene(scene);

            // Show the stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Populates the skill list view with all skills.
     */
    private void populateSkillListView() {
        ObservableList<String> skillItems = FXCollections.observableArrayList();
        for (Skill skill : skills) {
            skillItems.add(skill.getNameOfTheSkill()); // Assuming Skill has a 'getName' method
        }
        skillListView.setItems(skillItems);
    }

    /**
     * Handles the action when the "Assign Skill" button is clicked.
     * Attempts to assign selected skills to the selected collaborator.
     * Displays success or error messages accordingly.
     */
    @FXML
    public void assignSkill() {
        List<String> selectedSkills = skillListView.getSelectionModel().getSelectedItems();
        String selectedCollaboratorName = collaboratorListView.getSelectionModel().getSelectedItem();

        if (selectedSkills != null && selectedCollaboratorName != null) {
            ArrayList<Skill> skillsToAdd = new ArrayList<>();
            for (String skillName : selectedSkills) {
                Skill skill = skillsRepository.getSkillByName(skillName);
                if (skill != null) {
                    skillsToAdd.add(skill);
                }
            }

            // Retrieve the Collaborator object from the map using the selected name
            Collaborator collaborator = collaboratorMap.get(selectedCollaboratorName);
            if (collaborator != null) {
                boolean success = assignSkillController.assignSkill(skillsToAdd, collaborator);
                if (success) {
                    // Build success message
                    StringBuilder skillsBuilder = new StringBuilder();
                    for (Skill skill : collaborator.getSkills()) {
                        skillsBuilder.append(skill.getNameOfTheSkill()).append("\n");
                    }
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    TextArea textArea = new TextArea("Skill(s) assigned successfully! The collaborator now has the following skills:\n" + skillsBuilder.toString());
                    textArea.setEditable(false);
                    textArea.setWrapText(true);
                    textArea.setMaxWidth(Double.MAX_VALUE);
                    textArea.setMaxHeight(Double.MAX_VALUE);
                    alert.getDialogPane().setContent(textArea);
                    alert.showAndWait();
                } else {
                    // Build error message
                    StringBuilder skillsBuilder = new StringBuilder();
                    for (Skill skill : collaborator.getSkills()) {
                        skillsBuilder.append(skill.getNameOfTheSkill()).append("\n");
                    }
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    TextArea textArea = new TextArea("Failed to assign skill(s). They might already be assigned. The collaborator currently has the following skills:\n" + skillsBuilder.toString());
                    textArea.setEditable(false);
                    textArea.setWrapText(true);
                    textArea.setMaxWidth(Double.MAX_VALUE);
                    textArea.setMaxHeight(Double.MAX_VALUE);
                    alert.getDialogPane().setContent(textArea);
                    alert.showAndWait();
                }
            }
        }
    }
}