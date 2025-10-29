package eapli.shodrone.figuremanagement.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.shodrone.usermanagement.domain.Roles;
import eapli.shodrone.figuremanagement.repository.FigureCategoryRepository;

@UseCaseController
public class AddFigureCategoryController {

    private final AuthorizationService authz;
    private final FigureCategoryService service;

    public AddFigureCategoryController(final AuthorizationService authz, final FigureCategoryRepository repo) {
        this.authz = authz;
        this.service = new FigureCategoryService(repo);
    }

    public void addCategory(final String name) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.SHOW_DESIGNER, Roles.ADMIN);
        service.registerCategory(name);
    }
}
