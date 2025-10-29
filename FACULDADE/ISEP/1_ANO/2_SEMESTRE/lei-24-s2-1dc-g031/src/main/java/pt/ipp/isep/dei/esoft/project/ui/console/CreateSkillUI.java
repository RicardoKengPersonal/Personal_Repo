package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateSkillController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.HRMUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;

import java.util.Scanner;

/**
 * CreateSkillUI class represents the user interface for creating a skill.
 */
public class CreateSkillUI implements Runnable {
    private String skillName;
    private final CreateSkillController controller;

    /**
     * Constructs a new CreateSkillUI object.
     */
    public CreateSkillUI() {
        controller = new CreateSkillController();
    }

    /**
     * Prompts the user to input the skill name.
     */
    private void askForSkillName() {
        Scanner input = new Scanner(System.in);
        System.out.print("\nSkill name (write 0 to cancel): ");
        skillName = input.nextLine();
    }

    /**
     * Navigates back to the HRMUI menu.
     */
    private void backToHRMUI() {
        MenuItem item = new MenuItem(AuthenticationController.ROLE_HRM, new HRMUI());
        item.run();
    }

    /**
     * Runs the CreateSkillUI.
     */
    public void run() {
        System.out.println("\n\n--- Add Skill----------------------");
        askForSkillName();

        if (skillName.equals("0")) {
            backToHRMUI();
        } else {
            controller.createSkill(skillName);
        }
    }
}
