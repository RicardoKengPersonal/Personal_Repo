/**
 * The GenerateTeamProposalController class represents a controller for generating team proposals.
 */
package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.domain.service.CreateTeamProposalService;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillsRepository;
import pt.ipp.isep.dei.esoft.project.repository.TeamsRepository;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class GenerateTeamProposalController {
    private SkillsRepository skillRepository;
    private TeamsRepository teamRepository;
    private CreateTeamProposalService teamProposalService;

    /**
     * Constructs a new GenerateTeamProposalController and initializes the repositories and service.
     */
    public GenerateTeamProposalController() {
        this.skillRepository = Repositories.getInstance().getSkillsRepository();
        this.teamRepository = Repositories.getInstance().getTeamsRepository();
        this.teamProposalService = new CreateTeamProposalService();
    }

    /**
     * Retrieves a list of all skills from the repository.
     *
     * @return ArrayList of Skill objects.
     */
    public ArrayList<Skill> getSkillsList() {
        return skillRepository.getSkillsArrayList();
    }

    /**
     * Creates a team proposal based on specified criteria.
     *
     * @param max    The maximum number of team members.
     * @param min    The minimum number of team members.
     * @param skills The list of skills required for the team.
     * @return The created Team object.
     * @throws InputMismatchException if the team proposal cannot be created.
     */
    public ArrayList<ArrayList<Collaborator>> createTeamProposal(int max, int min, ArrayList<Skill> skills) {
        var collaborators = teamProposalService.arrangeCollaborattorsBySkill(skills);
        return teamProposalService.arrangeTeams(max, min, skills, collaborators);
    }

    /**
     * Creates and registers a new team.
     * @param collaborators
     * @param skills
     * @return
     */
    public Team createAndRegisterTeam(ArrayList<Collaborator> collaborators, ArrayList<Skill> skills) {
        // Create a new team
        Team team = new Team(collaborators, skills);
        try {
            // Register the team
            boolean registered = Repositories.getInstance().getTeamsRepository().registerTeam(team);

            if (registered) {
                return team;
            } else {
                return null;
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Team already exists.");
        }
    }
}
