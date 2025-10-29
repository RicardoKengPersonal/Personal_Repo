package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Repository class for managing skills.
 */
public class SkillsRepository implements Serializable {

    private final ArrayList<Skill> skillsArrayList = new ArrayList<>();

    /**
     * Gets the list of skills.
     *
     * @return The list of skills.
     */
    public ArrayList<Skill> getSkillsArrayList() {
        return skillsArrayList;
    }

    /**
     * Checks if a skill already exists.
     *
     * @param skill The skill to check.
     * @return True if the skill already exists, false otherwise.
     */
    private boolean skillAlreadyExist(Skill skill) {
        for (Skill s : skillsArrayList){
            if (s.equals(skill)){
                return true;
            }
        }
        return false;
    }

    /**
     * Gets a skill by name.
     *
     * @param skillName The name of the skill to get.
     * @return The skill with the specified name, or null if no skill is found.
     */
    public Skill getSkillByName(String skillName) {
        for (Skill skill : skillsArrayList) {
            if (skill.getNameOfTheSkill().equals(skillName)) {
                return skill;
            }
        }
        return null;
    }

    /**
     * Checks if a skill name is valid.
     *
     * @param skillName The name of the skill to validate.
     * @return True if the skill name is valid, false otherwise.
     */
    private boolean skillNameIsValid(String skillName) {
        String regex = "^[a-zA-Z\\s]+$";
        return skillName.matches(regex);
    }

    /**
     * Adds a new skill.
     *
     * @param skillName The name of the skill to add.
     */
    public boolean addSkill(String skillName) {
        Skill sk = new Skill(skillName);
        if (!skillAlreadyExist(sk) && skillNameIsValid(sk.getNameOfTheSkill())){
            skillsArrayList.add(sk);
            return true;
        };
        return false;
    }
}
