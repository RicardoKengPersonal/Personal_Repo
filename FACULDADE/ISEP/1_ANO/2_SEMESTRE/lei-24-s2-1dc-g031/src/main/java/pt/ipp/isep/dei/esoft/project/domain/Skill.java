/**
 * The Skill class represents a skill in the project.
 */
package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

public class Skill implements Serializable {
    private String nameOfTheSkill;

    /**
     * Constructs a new Skill with the specified name.
     *
     * @param nameOfTheSkill The name of the skill.
     */
    public Skill(String nameOfTheSkill) {
        this.nameOfTheSkill = nameOfTheSkill;
    }

    /**
     * Retrieves the name of the skill.
     *
     * @return The name of the skill.
     */
    public String getNameOfTheSkill() {
        return nameOfTheSkill;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj The object to be compared.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Skill skill = (Skill) obj;
        return Objects.equals(nameOfTheSkill, skill.nameOfTheSkill);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nameOfTheSkill);
    }

    /**
     * Returns a string representation of the skill.
     *
     * @return A string representation of the skill.
     */
    @Override
    public String toString() {
        return nameOfTheSkill;
    }
}
