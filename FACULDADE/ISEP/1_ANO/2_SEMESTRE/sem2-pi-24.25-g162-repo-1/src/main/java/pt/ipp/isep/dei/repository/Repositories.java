package pt.ipp.isep.dei.repository;

/**
 * Inspired on https://refactoring.guru/design-patterns/singleton/java/example
 *
 * The Repositories class works as a Singleton. It defines the getInstance
 * method that serves as an alternative
 * to the constructor and lets client classes access the same instance of this
 * class over and over.
 */
public class Repositories {
    private static Repositories instance;
    private final OrganizationRepository organizationRepository;
    private final TaskCategoryRepository taskCategoryRepository;
    private final AuthenticationRepository authenticationRepository;
    private final MapRepository mapRepository;
    private final GamesRepository gamesRepository;
    private final IndustryRepository industryRepository;
    private final ScenarioRepository scenarioRepository;

    private final CityRepository cityRepository;

    /**
     * The Singleton's constructor should always be private to prevent direct
     * construction calls with the new operator.
     */
    private Repositories() {
        organizationRepository = new OrganizationRepository();
        taskCategoryRepository = new TaskCategoryRepository();
        authenticationRepository = new AuthenticationRepository();
        mapRepository = new MapRepository();
        gamesRepository = new GamesRepository();
        industryRepository = new IndustryRepository();
        scenarioRepository = new ScenarioRepository();
        cityRepository = new CityRepository();
    }

    /**
     * This is the static method that controls the access to the singleton instance.
     * On the first run, it creates a singleton object and places it into the static
     * attribute.
     * On subsequent runs, it returns the existing object stored in the static
     * attribute.
     */
    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }

    public OrganizationRepository getOrganizationRepository() {
        return organizationRepository;
    }

    public TaskCategoryRepository getTaskCategoryRepository() {
        return taskCategoryRepository;
    }

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    public MapRepository getMapRepository() {
        return mapRepository;
    }

    public GamesRepository getGamesRepository() {
        return gamesRepository;
    }

    public IndustryRepository getIndustryRepository() {
        return industryRepository;
    }

    public ScenarioRepository getScenarioRepository() {
        return scenarioRepository;
    }

    public CityRepository getCityRepository() {
        return cityRepository;
    }
}