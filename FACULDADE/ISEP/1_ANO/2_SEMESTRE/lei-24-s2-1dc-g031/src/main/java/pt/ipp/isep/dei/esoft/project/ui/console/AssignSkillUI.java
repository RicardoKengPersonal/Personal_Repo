package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * AssignSkillUI class represents the user interface for assigning skills to collaborators.
 */
public class AssignSkillUI implements Runnable {

    private final AssignSkillController controller;

    /**
     * Constructs a new AssignSkillUI object.
     */
    public AssignSkillUI() {
        controller = new AssignSkillController();
    }

    /**
     * Gets the controller associated with this UI.
     *
     * @return The AssignSkillController instance.
     */
    private AssignSkillController getController() {
        return controller;
    }

    /**
     * Displays the list of available skills.
     *
     * @param skills The list of skills to display.
     */
    public void displaySkillsList(ArrayList<Skill> skills) {
        int i = 1;
        System.out.println();
        for (Skill skill : skills) {
            System.out.println(i + " - " + skill.getNameOfTheSkill());
            i++;
        }
        System.out.println("0 - Stop/None");
    }

    /**
     * Displays the list of collaborators.
     *
     * @param collaborators The list of collaborators to display.
     */
    public void displayCollaboratorsList(ArrayList<Collaborator> collaborators) {
        int i = 1;
        System.out.println();
        for (Collaborator collab : collaborators) {
            System.out.println(i + " - " + collab.getName() + " - " + collab.getDocumentIdentificationNumber());
            i++;
        }
        System.out.println("0 - Stop/None");
    }

    /**
     * Allows the user to select and add skills.
     *
     * @return The list of selected skills.
     */
    public ArrayList<Skill> selectAndAddSkill() {
        ArrayList<Skill> skills;
        ArrayList<Skill> selectedSkills = new ArrayList<Skill>();
        skills = this.controller.getSkillsList();
        int numberOfSkills = skills.size();
        Scanner scanner = new Scanner(System.in);

        int option = -1;
        while (option != 0) {
            displaySkillsList(skills);
            System.out.println("\nSelect a skill to be added (0 for none/stop)");
            option = scanner.nextInt();

            if(option >= 1 && option <= numberOfSkills) {
                selectedSkills.add(skills.get(option - 1));
            }
        }
        return selectedSkills;
    }

    /**
     * Allows the user to select a collaborator.
     *
     * @return The selected collaborator.
     */
    public Collaborator selectCollaborator() {
        ArrayList<Collaborator> collaborators;
        collaborators = this.controller.getCollaboratorsList();
        int numberOfCollaborators = collaborators.size();
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        while (option != 0) {
            displayCollaboratorsList(collaborators);
            System.out.println("\nSelect a collaborator (0 for none/stop)");
            option = scanner.nextInt();
            if(option >= 1 && option <= numberOfCollaborators) {
                return collaborators.get(option - 1);
            }
        }
        return null;
    }

    /**
     * Runs the Assign Skill user interface.
     */
    public void run() {
        Collaborator collaborator = selectCollaborator();
        if(collaborator != null){
            System.out.println("\nSelected Collaborator: :");
            System.out.println("- Name: " + collaborator.getName());
            ArrayList<Skill> chosenSkills = selectAndAddSkill();
            if (!chosenSkills.isEmpty()) {
                this.controller.assignSkill(chosenSkills, collaborator);
                System.out.println("\nUpdated skill list:");
                for (Skill skill : collaborator.getSkills()) {
                    System.out.println("- " + skill.getNameOfTheSkill());
                }
            } else {
                System.out.println("No skill was assigned.");
            }
        }
    }
}
