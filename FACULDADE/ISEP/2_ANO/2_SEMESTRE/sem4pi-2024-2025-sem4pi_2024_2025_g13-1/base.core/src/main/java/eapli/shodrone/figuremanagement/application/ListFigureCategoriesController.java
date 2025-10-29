package eapli.shodrone.figuremanagement.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.shodrone.figuremanagement.domain.FigureCategory;
import eapli.shodrone.figuremanagement.repository.FigureCategoryRepository;
import eapli.shodrone.usermanagement.domain.Roles;

@UseCaseController
public class ListFigureCategoriesController {

    private final AuthorizationService authz;
    private final FigureCategoryService service;

    public ListFigureCategoriesController(AuthorizationService authz, FigureCategoryRepository repo) {
        this.authz = authz;
        this.service = new FigureCategoryService(repo);
    }

    public Iterable<FigureCategory> listAllCategories() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.CRM_COLLABORATOR, Roles.ADMIN);
        return service.listAllCategories();
    }
}
