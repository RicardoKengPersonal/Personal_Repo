package pt.ipp.isep.dei.esoft.project.repository;

import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * This class represents an AuthenticationRepository in the system.
 * It provides methods for user authentication and user role management.
 */
public class AuthenticationRepository implements Serializable {
    private static final long serialVersionUID = 3713261853538591398L;

    private transient AuthFacade authenticationFacade;

    /**
     * Constructor for the AuthenticationRepository class.
     */
    public AuthenticationRepository() {
        authenticationFacade = new AuthFacade();
    }

    /**
     * Reads an object from the ObjectInputStream.
     * @param ois The ObjectInputStream.
     * @throws IOException if an I/O error occurs.
     * @throws ClassNotFoundException if the class of a serialized object cannot be found.
     */
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        authenticationFacade = new AuthFacade();
    }

    /**
     * Performs a login operation.
     * @param email The email of the user.
     * @param pwd The password of the user.
     * @return true if the login was successful, false otherwise.
     */
    public boolean doLogin(String email, String pwd) {
        return authenticationFacade.doLogin(email, pwd).isLoggedIn();
    }

    /**
     * Performs a logout operation.
     */
    public void doLogout() {
        authenticationFacade.doLogout();
    }

    /**
     * Gets the current user session.
     * @return The current user session.
     */
    public UserSession getCurrentUserSession() {
        return authenticationFacade.getCurrentUserSession();
    }

    /**
     * Adds a user role.
     * @param id The ID of the role.
     * @param description The description of the role.
     * @return true if the role was added successfully, false otherwise.
     */
    public boolean addUserRole(String id, String description) {
        Repositories.presister().persistRole(id, description);
        return authenticationFacade.addUserRole(id, description);
    }

    /**
     * Adds a user with a role.
     * @param name The name of the user.
     * @param email The email of the user.
     * @param pwd The password of the user.
     * @param roleId The ID of the role.
     * @return true if the user was added successfully, false otherwise.
     */
    public boolean addUserWithRole(String name, String email, String pwd, String roleId) {
        Repositories.presister().persistUser(name, email, pwd, roleId);
        return authenticationFacade.addUserWithRole(name, email, pwd, roleId);
    }

    /**
     * Gets the authentication facade.
     * @return The authentication facade.
     */
    public AuthFacade facade() {
        return authenticationFacade;
    }
}