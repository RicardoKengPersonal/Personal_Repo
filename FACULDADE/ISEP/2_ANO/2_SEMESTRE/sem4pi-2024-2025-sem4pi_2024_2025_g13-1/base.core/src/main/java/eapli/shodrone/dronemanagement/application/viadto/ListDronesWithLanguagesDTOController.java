package eapli.shodrone.dronemanagement.application.viadto;

import eapli.framework.application.UseCaseController;
import eapli.shodrone.dronemanagement.dto.DroneLanguagesDTO;

@UseCaseController
public class ListDronesWithLanguagesDTOController {
    private final ListDronesWithLanguagesDTOService service = new ListDronesWithLanguagesDTOService();

    public Iterable<DroneLanguagesDTO> dronesWithLanguages()
    {
        return this.service.listAllDronesWithLanguages();
    }
}
