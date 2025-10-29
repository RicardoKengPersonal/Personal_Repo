package pt.ipp.isep.dei.esoft.project.io;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for persisting repositories in the system.
 * It contains methods for persisting and loading repositories, users, and roles.
 */
public class RepositoryPresister {

    public static final String BIN_ROLES_BIN_PATH = "./bin/roles.bin";
    public static final String BIN_REPOSITORIES_BIN_PATH = "./bin/repositories.bin";
    public static final String BIN_USERS_BIN_PATH = "./bin/users.bin";

    /**
     * Default constructor for the RepositoryPresister class.
     */
    public RepositoryPresister() {
    }

    /**
     * Persists the given repositories.
     * @param repositories The repositories to be persisted.
     * @throws IOException if an I/O error occurs.
     */
    public void persist(Repositories repositories) throws IOException {
        File dir = new File("./bin");
        dir.mkdirs();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BIN_REPOSITORIES_BIN_PATH));
        oos.writeObject(repositories);
        oos.close();
    }

    /**
     * Loads the repositories.
     * @return The loaded repositories.
     * @throws IOException if an I/O error occurs.
     * @throws ClassNotFoundException if the class of a serialized object cannot be found.
     */
    public Repositories load() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BIN_REPOSITORIES_BIN_PATH));
        Repositories instance = (Repositories) ois.readObject();
        ois.close();
        ois = new ObjectInputStream(new FileInputStream(BIN_ROLES_BIN_PATH));
        List<PersistenceRole> roles = ((List<PersistenceRole>) ois.readObject());
        ois.close();
        ois = new ObjectInputStream(new FileInputStream(BIN_USERS_BIN_PATH));
        List<PersistenceUser> users = ((List<PersistenceUser>) ois.readObject());
        for (PersistenceRole role : roles) {
            instance.getAuthenticationRepository().facade().addUserRole(role.getRoleId(), role.getDescription());
        }
        for (PersistenceUser user : users) {
            instance.getAuthenticationRepository().facade().addUserWithRole(user.getName(), user.getEmail(), user.getPwd(), user.getRoleId());
        }
        return instance;
    }

    /**
     * Persists the given user.
     * @param name The name of the user.
     * @param email The email of the user.
     * @param pwd The password of the user.
     * @param role The role of the user.
     */
    public void persistUser(String name, String email, String pwd, String role) {
        File dir = new File("./bin");
        dir.mkdirs();
        ObjectInputStream ois = null;
        List<PersistenceUser> users = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(BIN_USERS_BIN_PATH));
            users = (List<PersistenceUser>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            users = new ArrayList<>();
        }
        users.add(new PersistenceUser(name, email, pwd, role));
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BIN_USERS_BIN_PATH));
            oos.writeObject(users);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Persists the given role.
     * @param roleId The ID of the role.
     * @param description The description of the role.
     */
    public void persistRole(String roleId, String description) {
        File dir = new File("./bin");
        dir.mkdirs();
        ObjectInputStream ois = null;
        List<PersistenceRole> roles = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(BIN_ROLES_BIN_PATH));
            roles = (List<PersistenceRole>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            roles = new ArrayList<>();
        }
        roles.add(new PersistenceRole(roleId, description));
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BIN_ROLES_BIN_PATH));
            oos.writeObject(roles);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}