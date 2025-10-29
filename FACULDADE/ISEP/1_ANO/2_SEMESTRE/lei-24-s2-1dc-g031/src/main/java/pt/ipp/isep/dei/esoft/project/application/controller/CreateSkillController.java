/**
 * The CreateSkillController class represents a controller for creating skills.
 */
package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillsRepository;

public class CreateSkillController {
    private SkillsRepository skillsRepository;

    /**
     * Constructs a new CreateSkillController and initializes the skills repository.
     */
    public CreateSkillController() {
        this.skillsRepository = getSkillsRepository();
    }

    private SkillsRepository getSkillsRepository() {
        if (skillsRepository == null) {
            Repositories repositories = Repositories.getInstance();
            skillsRepository = repositories.getInstance().getSkillsRepository();
        }
        return skillsRepository;
    }

    /**
     * Creates a new skill with the specified name.
     *
     * @param skillName The name of the skill to be created.
     * @return True if the skill is successfully created, false otherwise.
     */
    public boolean createSkill(String skillName) {
        return skillsRepository.addSkill(skillName);
    }
}