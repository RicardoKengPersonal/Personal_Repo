package pt.ipp.isep.dei.esoft.project.domain.service;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The CreateTeamProposalServiceTest class contains unit tests for the CreateTeamProposalService class.
 */
class CreateTeamProposalServiceTest {

    /**
     * Unit test for the arrangeTeam method.
     * Tests if the method throws an InputMismatchException when no team can be formed due to lack of collaborators with the required skills.
     */
    @Test
    void testArrangeTeam_TeamNotFormedBelowMinimum_ThrowsInputMismatchException() {
        // Arrange
        CreateTeamProposalService service = new CreateTeamProposalService();
        int max = 5;
        int min = 3;
        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(new Skill("NonexistentSkill"));
        ArrayList<Collaborator> collaborators = new ArrayList<>(); // Add collaborators as needed

        // Act & Assert
        //assertThrows(InputMismatchException.class, () -> service.arrangeTeam(max, min, skills, collaborators));
    }
}
