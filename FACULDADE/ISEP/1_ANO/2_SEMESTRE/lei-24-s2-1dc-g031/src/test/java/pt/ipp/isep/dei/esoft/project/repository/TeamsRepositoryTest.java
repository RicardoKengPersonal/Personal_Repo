package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TeamsRepositoryTest {
    private TeamsRepository repository;
    private Team team;

    @BeforeEach
    void setUp() {
        repository = new TeamsRepository();
        team = new Team(new ArrayList<Collaborator>(), new ArrayList<Skill>());
    }

    @Test
    void testRegisterTeam() {
        assertTrue(repository.registerTeam(team));
        assertEquals(1, repository.getTeams().size());
    }

    @Test
    void testRegisterTeam_AlreadyExists() {
        repository.registerTeam(team);
        assertFalse(repository.registerTeam(team));
        assertEquals(1, repository.getTeams().size());
    }
}