package eapli.shodrone.figuremanagement.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.shodrone.figuremanagement.domain.FigureVersion;
import eapli.shodrone.figuremanagement.domain.FigureVersionID;
import eapli.shodrone.figuremanagement.repository.FigureCategoryRepository;
import eapli.shodrone.usermanagement.domain.Roles;
import eapli.shodrone.figuremanagement.domain.Figure;
import eapli.shodrone.figuremanagement.domain.FigureID;
import eapli.shodrone.figuremanagement.repository.FigureRepository;

import java.util.Set;

@UseCaseController
public class DecommissionFigureController {

    private final AuthorizationService authz;
    private final FigureService service;

    public DecommissionFigureController(final AuthorizationService authz,
                                        final FigureRepository repo,
                                        final FigureCategoryRepository categoryRepo) {
        this.authz = authz;
        this.service = new FigureService(repo, categoryRepo);
    }

    public void decommissionFigure(FigureVersionID id) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.CRM_MANAGER, Roles.ADMIN);
        service.decommissionFigure(id);
    }

    public Iterable<FigureVersion> listAllFigureVersions() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.CRM_MANAGER, Roles.ADMIN);
        return service.listAllFigureVersions();
    }
}