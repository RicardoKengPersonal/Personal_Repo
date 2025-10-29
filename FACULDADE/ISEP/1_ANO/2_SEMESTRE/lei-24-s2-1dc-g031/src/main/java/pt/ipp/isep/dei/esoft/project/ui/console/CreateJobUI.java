package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateJobController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.HRMUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;

import java.util.Scanner;

/**
 * CreateJobUI class represents the user interface for creating a job.
 */
public class CreateJobUI implements Runnable {
    private String jobName;
    private final CreateJobController controller;

    /**
     * Constructs a new CreateJobUI object.
     */
    public CreateJobUI() {
        controller = new CreateJobController();
    }

    /**
     * Prompts the user to input the job name.
     */
    private void askForJobName() {
        Scanner input = new Scanner(System.in);
        System.out.print("\nJob name (write 0 to cancel): ");
        jobName = input.nextLine();
    }

    /**
     * Navigates back to the HRMUI menu.
     */
    private void backToHRMUI() {
        MenuItem item = new MenuItem(AuthenticationController.ROLE_HRM, new HRMUI());
        item.run();
    }

    /**
     * Runs the CreateJobUI.
     */
    public void run() {
        System.out.println("\n\n--- Add Job----------------------");
        askForJobName();

        if (jobName.equals("0")) {
            backToHRMUI();
        } else {
            controller.createJob(jobName);
        }
    }
}
