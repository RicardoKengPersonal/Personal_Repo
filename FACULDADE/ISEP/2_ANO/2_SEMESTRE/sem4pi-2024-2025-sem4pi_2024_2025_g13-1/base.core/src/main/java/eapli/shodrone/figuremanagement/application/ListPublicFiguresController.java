package eapli.shodrone.figuremanagement.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.shodrone.figuremanagement.domain.Figure;
import eapli.shodrone.figuremanagement.repository.FigureCategoryRepository;
import eapli.shodrone.figuremanagement.repository.FigureRepository;
import eapli.shodrone.usermanagement.domain.Roles;

import java.util.Set;

public class ListPublicFiguresController {

    private final AuthorizationService authz;
    private final FigureService service;

    public ListPublicFiguresController(AuthorizationService authz, FigureRepository figRep, FigureCategoryRepository catRepo) {
        this.authz = authz;
        this.service = new FigureService(figRep, catRepo);
    }

    public Iterable<Figure> listPublicFigures() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.CRM_COLLABORATOR, Roles.ADMIN);
        return service.listPublicFigures();
    }
}
