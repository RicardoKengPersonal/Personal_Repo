package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamProposalController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillsRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.HRMUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * GenerateTeamProposalUI class represents the user interface for generating a team proposal.
 */
public class GenerateTeamProposalUI implements Runnable {
    private final GenerateTeamProposalController controller;
    private final SkillsRepository skillsRepository;
    private int min, max;
    private ArrayList<Skill> skills;

    /**
     * Constructs a new GenerateTeamProposalUI object.
     */
    public GenerateTeamProposalUI() {
        controller = new GenerateTeamProposalController();
        skillsRepository = Repositories.getInstance().getSkillsRepository();
    }

    /**
     * Retrieves the controller associated with this UI.
     *
     * @return The GenerateTeamProposalController.
     */
    private GenerateTeamProposalController getController() {
        return controller;
    }

    /**
     * Navigates back to the HRMUI menu.
     */
    private void backToHrmUI() {
        MenuItem item = new MenuItem(AuthenticationController.ROLE_HRM, new HRMUI());
        item.run();
    }

    /**
     * Runs the GenerateTeamProposalUI.
     */
    @Override
    public void run() {
        System.out.println("\n\n----------- Create Team Proposal----------------------");
        requestData();
        submitData();
    }

    /**
     * Requests input data from the user.
     */
    private void requestData() {
        try {
            this.max = Utils.readIntegerFromConsole("Type maximum number of collaborators:");
            this.min = Utils.readIntegerFromConsole("Type minimum number of collaborators:");
        } catch (NumberFormatException ex) {
            System.out.println("Number is not in a valid format");
            backToHrmUI();
        }

        if (this.min > this.max) {
            System.out.println("Minimum number cannot be higher than maximum number");
            backToHrmUI();
        }

        var skills = this.controller.getSkillsList();
        if (skills.isEmpty()) {
            System.out.println("No skills available");
            backToHrmUI();
        }

        displayAvailableSkills(skills);
        this.skills = requestSkills(skills);
    }

    /**
     * Submits the input data to generate the team proposal.
     */
    private void submitData() {
        try {
            var team = this.controller.createTeamProposal(this.max, this.min, this.skills);
            System.out.println("Team successfully created!");
            System.out.println(team.toString());
        } catch (InputMismatchException ex) {
            System.out.println(ex.getMessage());
            backToHrmUI();
        }
    }

    /**
     * Displays the available skills to the user.
     *
     * @param skills The list of available skills.
     */
    private void displayAvailableSkills(List<Skill> skills) {
        System.out.println("\nAvailable skills:");
        for (int i = 0; i < skills.size(); i++) {
            System.out.println((i + 1) + ". " + skills.get(i).getNameOfTheSkill());
        }
    }

    /**
     * Requests the user to select skills.
     *
     * @param skills The list of available skills.
     * @return The list of selected skills.
     */
    private ArrayList<Skill> requestSkills(ArrayList<Skill> skills) {
        Scanner input = new Scanner(System.in);
        ArrayList<Skill> selectedSkills = new ArrayList<>();

        boolean addingSkills = true;
        while (addingSkills) {
            System.out.print("Enter the index of the skill you want to add (or type 'done' to finish): ");
            System.out.println("type 'exit' to cancel");
            String userInput = input.nextLine();

            if (userInput.equalsIgnoreCase("done")) {
                addingSkills = false;
            } else if (userInput.equalsIgnoreCase("exit")) {
                backToHrmUI();
            } else {
                try {
                    int index = Integer.parseInt(userInput) - 1;
                    if (index >= 0 && index < skills.size()) {
                        selectedSkills.add(skills.get(index));
                    } else {
                        System.out.println("Invalid index. Please try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }
        }

        return selectedSkills;
    }
}
