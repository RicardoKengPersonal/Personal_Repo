package eapli.shodrone.figuremanagement.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.shodrone.dronemanagement.domain.DroneModel;
import eapli.shodrone.dronemanagement.domain.DroneType;
import eapli.shodrone.figuremanagement.domain.*;
import eapli.shodrone.figuremanagement.repository.FigureCategoryRepository;
import eapli.shodrone.figuremanagement.repository.FigureRepository;
import eapli.shodrone.usermanagement.domain.Roles;

import java.util.List;
import java.util.Optional;

@UseCaseController
public class AddFigureCatalogueController {

    private final AuthorizationService authz;
    private final FigureService service;

    public AddFigureCatalogueController(final AuthorizationService authz,
                                        final FigureRepository repo,
                                        final FigureCategoryRepository categoryRepo) {
        this.authz = authz;
        this.service = new FigureService(repo, categoryRepo);
    }

    public Optional<Figure> findByKeyword(String keyword) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.SHOW_DESIGNER, Roles.ADMIN);
        return service.findByKeyword(keyword);
    }

    public Optional<FigureCategoryID> findCategoryByName(String categoryName) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.SHOW_DESIGNER, Roles.ADMIN);
        return service.findCategoryByName(categoryName);
    }

    public List<Figure> findByCategory(FigureCategoryID categoryID) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.SHOW_DESIGNER, Roles.ADMIN);
        return service.findByCategory(categoryID);
    }

    public Optional<Figure> findByName(String figureName) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.SHOW_DESIGNER, Roles.ADMIN);
        return service.findByName(figureName);
    }

    public List<Figure> findAllFigures() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.SHOW_DESIGNER, Roles.ADMIN);
        return service.findAllFigures();
    }

    public FigureVersion addVersionToFigure(Figure figure,
                                            DSL dsl, FigureStatic figureStatic,
                                            FigureDynamic figureDynamic,
                                            SystemUser user) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.SHOW_DESIGNER, Roles.ADMIN);
        return service.addVersionToFigure(figure, dsl, figureStatic, figureDynamic, user);
    }

    public Figure createFigure(String name, String keyword, String description,
                               Optional<FigureCategoryID> categoryOpt,
                               FigureVisibility visibility) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.SHOW_DESIGNER, Roles.ADMIN);
        if (categoryOpt.isEmpty()) {
            throw new IllegalArgumentException("Category must be provided.");
        }
        return service.createFigure(name, keyword, description, categoryOpt, visibility);
    }


    public List<DroneModel> listDroneModels() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.SHOW_DESIGNER, Roles.ADMIN);
        return service.listDroneModels();
    }

    public void addElementToVersionByType(FigureVersion version,
                                          ElementType type,
                                          Movement movement,
                                          DroneType droneType,
                                          int totaldrone) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.SHOW_DESIGNER, Roles.ADMIN);
        service.addElementToVersionByType(version, type, movement, droneType, totaldrone);
    }

    public void saveFigure(Figure figure) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.SHOW_DESIGNER, Roles.ADMIN);
        service.saveFigure(figure);
    }

    public Iterable<FigureCategory> listCategoriesActive() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.SHOW_DESIGNER, Roles.ADMIN);
        return service.getAllCategoriesActive();
    }
}
