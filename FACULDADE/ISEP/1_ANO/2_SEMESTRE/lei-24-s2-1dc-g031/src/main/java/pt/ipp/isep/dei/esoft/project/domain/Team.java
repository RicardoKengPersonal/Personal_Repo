package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a team of collaborators with specific skills.
 */
public class Team implements Serializable {

    /** List of skills possessed by the team
     *
     */
    private ArrayList<Skill> skills;
    /** List of collaborators in the team
     *
     */
    private ArrayList<Collaborator> collaborators;

    /**
     * Constructs a team with given collaborators and skills.
     *
     * @param collaborators The list of collaborators in the team.
     * @param skills        The list of skills possessed by the team.
     */
    public Team(ArrayList<Collaborator> collaborators, ArrayList<Skill> skills){
        this.collaborators = collaborators;
        this.skills = skills;
    }

    /**
     * Retrieves the list of skills possessed by the team.
     *
     * @return The list of skills.
     */
    public ArrayList<Skill> getSkills() {
        return skills;
    }

    /**
     * Sets the list of skills possessed by the team.
     *
     * @param skills The list of skills to set.
     */
    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    /**
     * Retrieves the list of collaborators in the team.
     *
     * @return The list of collaborators.
     */
    public ArrayList<Collaborator> getCollaborators(){
        return collaborators;
    }

    /**
     * Sets the list of collaborators in the team.
     *
     * @param collaborators The list of collaborators to set.
     */
    public void setCollaborators(ArrayList<Collaborator> collaborators){
        this.collaborators = collaborators;
    }


    /**
     * Generates a string representation of the Team object.
     * @return A string containing information about the team, including its collaborators and skills.
     */
    @Override
    public String toString() {
        String string = "Team: ";
        for (Collaborator collaborator : collaborators) {
            string += collaborator.getName() + " ";
        }
        string += "\nSkills: ";
        for (Skill skill : skills) {
            string += skill.getNameOfTheSkill() + " ";
        }
        return string;
    }
}
