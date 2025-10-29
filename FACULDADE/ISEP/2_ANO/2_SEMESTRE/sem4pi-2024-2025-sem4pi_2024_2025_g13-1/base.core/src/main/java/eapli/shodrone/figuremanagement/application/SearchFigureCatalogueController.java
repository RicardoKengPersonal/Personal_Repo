package eapli.shodrone.figuremanagement.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.shodrone.dronemanagement.repository.DroneModelRepository;
import eapli.shodrone.figuremanagement.domain.Figure;
import eapli.shodrone.figuremanagement.domain.FigureCategory;
import eapli.shodrone.figuremanagement.domain.FigureCategoryID;
import eapli.shodrone.figuremanagement.domain.FigureID;
import eapli.shodrone.figuremanagement.repository.FigureCategoryRepository;
import eapli.shodrone.figuremanagement.repository.FigureRepository;
import eapli.shodrone.usermanagement.domain.Roles;

import java.util.List;
import java.util.Optional;

public class SearchFigureCatalogueController {

    private final AuthorizationService authz;
    private final FigureService service;

    public SearchFigureCatalogueController(AuthorizationService authz, FigureRepository figRep, FigureCategoryRepository catRepo) {
        this.authz = authz;
        this.service = new FigureService(figRep, catRepo);
    }

    public Iterable<Figure> searchByKeywords(List<String> keywords) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN, Roles.CRM_COLLABORATOR);
        return service.searchByKeywords(keywords);
    }

    public Optional<FigureCategoryID> findCategoryByName(String categoryName) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN, Roles.CRM_COLLABORATOR);
        return service.findCategoryByName(categoryName);
    }

    public Iterable<Figure> searchByCategory(FigureCategoryID categoryID) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN, Roles.CRM_COLLABORATOR);
        return service.searchByCategory(categoryID);
    }

    public Iterable<Figure> searchByCategoryAndKeywords(FigureCategoryID categoryID, List<String> keywords) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN, Roles.CRM_COLLABORATOR);
        return service.searchByCategoryAndKeywords(categoryID, keywords);
    }

    public Iterable<FigureCategory> listCategoriesActive() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN, Roles.CRM_COLLABORATOR);
        return service.getAllCategoriesActive();
    }
}
