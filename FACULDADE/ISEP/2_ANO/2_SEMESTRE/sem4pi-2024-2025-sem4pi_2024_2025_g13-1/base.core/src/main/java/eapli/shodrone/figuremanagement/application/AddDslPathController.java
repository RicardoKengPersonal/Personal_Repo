package eapli.shodrone.figuremanagement.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.shodrone.figuremanagement.domain.Figure;
import eapli.shodrone.figuremanagement.repository.FigureCategoryRepository;
import eapli.shodrone.figuremanagement.repository.FigureRepository;

public class AddDslPathController {
    private final AuthorizationService authz;
    private final FigureService service;

    public AddDslPathController(AuthorizationService authz, FigureRepository figRep, FigureCategoryRepository catRepo) {
        this.authz = authz;
        this.service = new FigureService(figRep, catRepo);
    }

    public void updateDsl(Figure figure, String dslLink) {
        service.updateDsl(figure, dslLink);
    }
}
