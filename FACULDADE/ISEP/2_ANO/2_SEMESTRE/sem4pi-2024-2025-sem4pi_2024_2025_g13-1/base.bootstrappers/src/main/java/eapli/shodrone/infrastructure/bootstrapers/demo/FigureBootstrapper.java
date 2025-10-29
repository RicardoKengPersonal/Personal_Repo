package eapli.shodrone.infrastructure.bootstrapers.demo;

import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.*;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.shodrone.dronemanagement.application.DroneInventoryService;
import eapli.shodrone.dronemanagement.domain.DroneType;
import eapli.shodrone.figuremanagement.application.AddFigureCatalogueController;
import eapli.shodrone.figuremanagement.application.AddFigureCategoryController;
import eapli.shodrone.figuremanagement.domain.*;
import eapli.shodrone.figuremanagement.repository.FigureCategoryRepository;
import eapli.shodrone.figuremanagement.repository.FigureRepository;
import eapli.shodrone.infrastructure.bootstrapers.AbstractUserBootstrapper;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.usermanagement.domain.Roles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;
import java.util.Set;
import java.util.stream.StreamSupport;


public class FigureBootstrapper extends AbstractUserBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(FigureBootstrapper.class);

    private final AddFigureCategoryController categoryController;
    private final AddFigureCatalogueController figureController;
    private final UserRepository userRepository = PersistenceContext.repositories().users();
    private final UserManagementService userManagementService = AuthzRegistry.userService();


    public FigureBootstrapper() {
        FigureCategoryRepository categoryRepo = PersistenceContext.repositories().figureCategory();
        FigureRepository figureRepo = PersistenceContext.repositories().figure();
        AuthorizationService authz = AuthzRegistry.authorizationService();
        this.categoryController = new AddFigureCategoryController(authz, categoryRepo);
        this.figureController = new AddFigureCatalogueController(authz, figureRepo, categoryRepo);
    }

    // Ficheiro: FigureBootstrapper.java

    @Override
    public boolean execute() {
        try {
            SystemUser designer = createShowDesigner();

            // --- Bloco para a Figura 1: Butterfly ---
            FigureCategory category1 = registerCategory("Animal", "Figures inspired by animals.");
            Figure figure1 = registerFigure("Butterfly", "butterfly", "A colorful butterfly.", category1, FigureVisibility.PUBLIC);
            FigureVersion version1 = registerFigureVersion(figure1, new DSL("script1"), new FigureStatic("yes"), new FigureDynamic("no"), designer);
            registerFigureElement(version1, ElementType.GEOMETRIC, Movement.ROTATION, DroneType.RGB, 5);
            figureController.saveFigure(figure1); // Salvar imediatamente

            // --- Bloco para a Figura 2: Robot ---
            FigureCategory category2 = registerCategory("Technology", "Figures inspired by technology.");
            Figure figure2 = registerFigure("Robot", "robot", "A futuristic robot.", category2, FigureVisibility.PRIVATE);
            FigureVersion version2 = registerFigureVersion(figure2, new DSL("script2"), new FigureStatic("no"), new FigureDynamic("yes"), designer);
            registerFigureElement(version2, ElementType.BITMAPS_3D, Movement.TRANSLATION, DroneType.LED, 3);
            figureController.saveFigure(figure2); // Salvar imediatamente

            System.out.println("Figure's created!");

        } catch (Exception e) {
            LOGGER.error("Error during bootstrap: {}", e.getMessage());
            return false;
        }
        return true;
    }

    private FigureCategory registerCategory(String name, String description) {
        try {
            categoryController.addCategory(name);
            return PersistenceContext.repositories().figureCategory().findByNameIgnoreCase(name)
                    .orElseThrow(() -> new IllegalStateException("Category not found after creation."));
        } catch (IntegrityViolationException | ConcurrencyException e) {
            LOGGER.error("Error registering category '{}': {}", name, e.getMessage());
            throw e;
        }
    }

    private Figure registerFigure(String name, String keyword, String description, FigureCategory category, FigureVisibility visibility) {
        try {
            return figureController.createFigure(name, keyword, description, java.util.Optional.of(category.identity()), visibility);
        } catch (IntegrityViolationException | ConcurrencyException e) {
            LOGGER.error("Error registering figure '{}': {}", name, e.getMessage());
            throw e;
        }
    }

    private FigureVersion registerFigureVersion(Figure figure, DSL dsl, FigureStatic figureStatic, FigureDynamic figureDynamic, SystemUser designer) {
        try {
            return figureController.addVersionToFigure(figure, dsl, figureStatic, figureDynamic, designer);
        } catch (IntegrityViolationException | ConcurrencyException e) {
            LOGGER.error("Error registering figure version: {}", e.getMessage());
            throw e;
        }
    }

    private void registerFigureElement(FigureVersion version, ElementType elementType, Movement movement, DroneType droneType, int totalDrones) {
        if (version == null) return;
        try {
            figureController.addElementToVersionByType(version, elementType, movement, droneType, totalDrones);
        } catch (Exception e) {
            LOGGER.error("Erro ao registar elemento para a versão '{}': {}", version.id(), e.getMessage(), e);
        }
    }

    private SystemUser createShowDesigner() {
        final String username = "ana.sd@shodrone.com";
        final String password = "Password1";
        final String firstName = "Ana";
        final String lastName = "Silva";
        final String email = username;
        final Set<Role> roles = Set.of(Roles.SHOW_DESIGNER);
        final Calendar createdOn = Calendar.getInstance();

        LOGGER.info("A criar utilizador SHOW_DESIGNER '{}'.", username);

        try {
            SystemUser user = userManagementService.registerNewUser(
                    username, password, firstName, lastName, email, roles, createdOn
            );

            // 🔐 Autenticar sessão
            AuthzRegistry.authenticationService().authenticate(username, password);

            return user;
        } catch (Exception e) {
            LOGGER.error("Erro ao criar utilizador SHOW_DESIGNER '{}': {}", username, e.getMessage(), e);
            throw new IllegalStateException("Não foi possível criar o utilizador SHOW_DESIGNER.");
        }
    }


    private void createDroneModel(String name, String manufacturer,
                                  float xAxisTolerance, float yAxisTolerance, float zAxisTolerance,
                                  float maxSpeed, float maxRotation,
                                  Set<DroneType> lightingOptions) {

        final var modelRepo = PersistenceContext.repositories().droneModel();
        final var droneRepo = PersistenceContext.repositories().drone();
        final var maintenanceRepo = PersistenceContext.repositories().maintenanceType();
        final var service = new DroneInventoryService(modelRepo, droneRepo, maintenanceRepo);

        if (modelRepo.findByNameIgnoreCase(name).isPresent()) {
            LOGGER.info("DroneModel '{}' already exists. Skipping creation.", name);
            return;
        }

        try {
            service.registerDroneModel(
                    name, manufacturer,
                    xAxisTolerance, yAxisTolerance, zAxisTolerance,
                    Calendar.getInstance(),
                    maxSpeed, maxRotation,
                    lightingOptions
            );
            LOGGER.info("✅ DroneModel '{}' created successfully.", name);
        } catch (IntegrityViolationException | ConcurrencyException e) {
            LOGGER.error("❌ Error creating DroneModel '{}': {}", name, e.getMessage(), e);
        }
    }





}