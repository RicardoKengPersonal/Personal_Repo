package pt.ipp.isep.dei.esoft.project.io;

import java.io.Serializable;

/**
 * This class represents a PersistenceRole in the system.
 * It contains information about the persistence role such as role ID and description.
 */
public class PersistenceRole implements Serializable {
    private String roleId;
    private String description;

    /**
     * Constructor for the PersistenceRole class.
     * @param roleId The ID of the role.
     * @param description The description of the role.
     */
    public PersistenceRole(String roleId, String description) {
        this.roleId = roleId;
        this.description = description;
    }

    /**
     * Gets the ID of the role.
     * @return The ID of the role.
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * Sets the ID of the role.
     * @param roleId The new ID of the role.
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * Gets the description of the role.
     * @return The description of the role.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the role.
     * @param description The new description of the role.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}