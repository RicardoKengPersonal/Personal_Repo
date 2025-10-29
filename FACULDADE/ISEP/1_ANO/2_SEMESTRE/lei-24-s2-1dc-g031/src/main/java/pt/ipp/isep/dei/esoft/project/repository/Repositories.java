package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.io.RepositoryPresister;

import java.io.IOException;
import java.io.Serializable;

/**
 * Singleton class for managing repositories.
 * It contains instances of all the repositories in the system and provides methods to get these instances.
 */
public class Repositories implements Serializable {

    private static Repositories instance;
    private static final RepositoryPresister presister = new RepositoryPresister();
    private final CollaboratorsRepository collaboratorsRepository;
    private final SkillsRepository skillsRepository;
    private final JobsRepository jobsRepository;
    private final AuthenticationRepository authenticationRepository;
    private final TeamsRepository teamsRepository;
    private final VehicleRepository vehicleRepository;
    private final MaintenanceRepository maintenanceRepository;
    private final GreenSpacesRepository greenSpaceRepository;
    private final Agenda agenda;
    private final ToDoList toDoList;

    /**
     * Private constructor for the Repositories class.
     * Initializes all the repositories.
     */
    private Repositories() {
        skillsRepository = new SkillsRepository();
        collaboratorsRepository = new CollaboratorsRepository();
        jobsRepository = new JobsRepository();
        authenticationRepository = new AuthenticationRepository();
        teamsRepository = new TeamsRepository();
        vehicleRepository = new VehicleRepository();
        maintenanceRepository = new MaintenanceRepository();
        greenSpaceRepository = new GreenSpacesRepository();
        agenda = new Agenda();
        toDoList = new ToDoList();
    }

    /**
     * Gets the singleton instance of Repositories.
     * If the instance is null, it tries to load it from the persistence unit.
     * If the persistence unit is not found, it creates a new instance.
     *
     * @return The singleton instance of Repositories.
     */
    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                try {
                    instance = presister.load();
                    return instance;
                } catch (IOException e) {
                    System.out.println("No persistence unit found! Creating a new one!");
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                instance = new Repositories();
            }
        }
        return instance;
    }

    /**
     * Gets the SkillsRepository.
     *
     * @return The SkillsRepository.
     */
    public SkillsRepository getSkillsRepository() {
        return skillsRepository;
    }

    /**
     * Gets the TeamsRepository.
     *
     * @return The TeamsRepository.
     */
    public TeamsRepository getTeamsRepository() {
        return teamsRepository;
    }

    /**
     * Gets the JobsRepository.
     *
     * @return The JobsRepository.
     */
    public JobsRepository getJobsRepository() {
        return jobsRepository;
    }

    /**
     * Gets the CollaboratorsRepository.
     *
     * @return The CollaboratorsRepository.
     */
    public CollaboratorsRepository getCollaboratorsRepository() {
        return collaboratorsRepository;
    }

    /**
     * Gets the AuthenticationRepository.
     *
     * @return The AuthenticationRepository.
     */
    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    /**
     * Gets the VehicleRepository.
     *
     * @return the VehicleRepository.
     */
    public VehicleRepository getVehicleRepository() {
        return vehicleRepository;
    }

    /**
     * Gets the MaintenanceRepository.
     *
     * @return the MaintenanceRepository.
     */
    public MaintenanceRepository getMaintenanceRepository() {
        return maintenanceRepository;
    }

    /**
     * Gets the GreenSpacesRepository.
     *
     * @return the GreenSpacesRepository.
     */
    public GreenSpacesRepository getGreenSpaceRepository() {
        return greenSpaceRepository;
    }

    /**
     * Gets the Agenda.
     *
     * @return the Agenda.
     */
    public Agenda getAgenda() {
        return agenda;
    }

    /**
     * Gets the ToDoList.
     *
     * @return the ToDoList.
     */
    public ToDoList getToDoList() {
        return toDoList;
    }

    /**
     * Saves the current state of the Repositories instance to the persistence unit.
     */
    public static void save() {
        try {
            presister.persist(Repositories.getInstance());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the RepositoryPresister.
     *
     * @return the RepositoryPresister.
     */
    public static RepositoryPresister presister(){
        return presister;
    }
}