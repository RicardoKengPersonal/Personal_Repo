/**
 * The AssignSkillController class represents a controller for assigning skills to collaborators.
 */
package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorsRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillsRepository;

import java.util.ArrayList;

public class AssignSkillController {
    private SkillsRepository skillsRepository;
    private CollaboratorsRepository collaboratorsRepository;

    /**
     * Constructs a new AssignSkillController and initializes the repositories.
     */
    public AssignSkillController() {
        this.skillsRepository = getSkillsRepository();
        this.collaboratorsRepository = getCollaboratorsRepository();
    }

    private CollaboratorsRepository getCollaboratorsRepository() {
        if (collaboratorsRepository == null) {
            Repositories repositories = Repositories.getInstance();
            collaboratorsRepository = repositories.getInstance().getCollaboratorsRepository();
        }
        return collaboratorsRepository;
    }

    private SkillsRepository getSkillsRepository() {
        if (skillsRepository == null) {
            Repositories repositories = Repositories.getInstance();
            skillsRepository = repositories.getInstance().getSkillsRepository();
        }
        return skillsRepository;
    }

    /**
     * Retrieves a list of all skills from the repository.
     *
     * @return ArrayList of Skill objects.
     */
    public ArrayList<Skill> getSkillsList() {
        return skillsRepository.getSkillsArrayList();
    }

    /**
     * Retrieves a list of all collaborators from the repository.
     *
     * @return ArrayList of Collaborator objects.
     */
    public ArrayList<Collaborator> getCollaboratorsList() {
        return collaboratorsRepository.getCollaboratorsArrayList();
    }

    /**
     * Assigns a list of skills to a collaborator.
     *
     * @param skillsToAdd  The list of skills to be assigned.
     * @param collaborator The collaborator to whom the skills will be assigned.
     * @return True if the assignment was successful, false otherwise.
     */
    public boolean assignSkill(ArrayList<Skill> skillsToAdd, Collaborator collaborator) {
        return collaboratorsRepository.addSkill(collaborator, skillsToAdd);
    }
}
