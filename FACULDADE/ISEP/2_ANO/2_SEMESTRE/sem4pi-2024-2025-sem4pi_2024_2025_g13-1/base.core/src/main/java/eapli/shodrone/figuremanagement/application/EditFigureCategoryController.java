package eapli.shodrone.figuremanagement.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.shodrone.figuremanagement.domain.FigureCategory;
import eapli.shodrone.figuremanagement.repository.FigureCategoryRepository;
import eapli.shodrone.usermanagement.domain.Roles;

@UseCaseController
public class EditFigureCategoryController {


    private final AuthorizationService authz;
    private final FigureCategoryService service;


    public EditFigureCategoryController(AuthorizationService authz, final FigureCategoryRepository repo) {
        this.authz = authz;
        this.service = new FigureCategoryService(repo);
    }

    public void editCategory(String oldName, String newName) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.SHOW_DESIGNER, Roles.ADMIN);
        service.editCategory(oldName, newName);
    }

    public Iterable<FigureCategory> listAllCategories() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.SHOW_DESIGNER, Roles.ADMIN);
        return service.listAllCategories();
    }


}
