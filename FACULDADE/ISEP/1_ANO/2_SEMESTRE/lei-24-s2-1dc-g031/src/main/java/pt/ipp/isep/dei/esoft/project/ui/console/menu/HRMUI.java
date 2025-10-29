package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * HRMUI class represents the Human Resources Management user interface.
 */
public class HRMUI implements Runnable {

    /**
     * Constructs a new instance of HRMUI.
     */
    public HRMUI() {
    }

    /**
     * Runs the HRM user interface.
     */
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Create a Skill", new CreateSkillUI()));
        options.add(new MenuItem("Assign Skill to Collaborator", new AssignSkillUI()));
        options.add(new MenuItem("Create a Job", new CreateJobUI()));
        options.add(new MenuItem("Create Collaborator", new CreateCollaboratorAssignJobUI()));
        options.add(new MenuItem("Generate Team Proposal", new GenerateTeamProposalUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- HRM MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
