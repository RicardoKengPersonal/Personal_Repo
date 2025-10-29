package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Repository class for managing teams.
 */
public class TeamsRepository implements Serializable {
    private final ArrayList<Team> teams = new ArrayList<>();

    /**
     * Registers a new team.
     *
     * @param team The team to register.
     * @return True if the team is successfully registered, false if the team is not successfully created.
     */
    public boolean registerTeam(Team team){
        if (teamAlreadyExists(team)){
            throw new IllegalArgumentException("Team already exists with that collaborators and skills set.");
        }
        return teams.add(team);
    }

    /**
     * Checks if a team already exists.
     *
     * @param team The team to check.
     * @return True if the team already exists, false otherwise.
     */
    private boolean teamAlreadyExists(Team team) {
        for (var t : this.teams){
            if (t.getCollaborators().containsAll(team.getCollaborators()) &&
                    t.getSkills().containsAll(team.getSkills())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the Teams list.
     * @return
     */
    public ArrayList<Team> getTeams(){
        return teams;
    }
}