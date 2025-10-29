package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamProposalController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GenerateTeamProposalControllerTest {
    private GenerateTeamProposalController controller;
    private ArrayList<Skill> skills;
    private ArrayList<Collaborator> collaborators;

    @BeforeEach
    void setUp() {
        controller = new GenerateTeamProposalController();
        skills = new ArrayList<>();
        Skill skill = new Skill("Skill");
        skills.add(skill);
        collaborators = new ArrayList<>();
        Collaborator collab = new Collaborator("John Doe", "1234567", "987654321", "johndoe@mail.com", 123456789, "Street1", "01-01-2023", "01-01-1980", DocumentType.PASSPORT, new Job("Job"));
        collab.addSkillToCollaboratorSkills(skill);
        collaborators.add(collab);
    }

    @Test
    void testCreateAndRegisterTeam() {
        Team team = controller.createAndRegisterTeam(collaborators, skills);
        assertNotNull(team);
    }
}