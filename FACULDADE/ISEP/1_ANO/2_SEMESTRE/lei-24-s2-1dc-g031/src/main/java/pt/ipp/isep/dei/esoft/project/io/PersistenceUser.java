package pt.ipp.isep.dei.esoft.project.io;

import java.io.Serializable;

/**
 * This class represents a PersistenceUser in the system.
 * It contains information about the persistence user such as name, email, password, and role ID.
 */
public class PersistenceUser implements Serializable {
    private String name;
    private String email;
    private String pwd;
    private String roleId;

    /**
     * Constructor for the PersistenceUser class.
     * @param name The name of the user.
     * @param email The email of the user.
     * @param pwd The password of the user.
     * @param roleId The role ID of the user.
     */
    public PersistenceUser(String name, String email, String pwd, String roleId) {
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.roleId = roleId;
    }

    /**
     * Gets the name of the user.
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the email of the user.
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the password of the user.
     * @return The password of the user.
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * Gets the role ID of the user.
     * @return The role ID of the user.
     */
    public String getRoleId() {
        return roleId;
    }
}