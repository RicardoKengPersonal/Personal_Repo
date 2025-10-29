package eapli.shodrone.dronemanagement.application.viadto;


import eapli.framework.application.UseCaseController;
import eapli.shodrone.dronemanagement.dto.MaintenanceTypeDTO;

@UseCaseController
public class ListMaintenanceTypesDTOController {

    private final ListMaintenaceTypesDTOService service = new ListMaintenaceTypesDTOService();

    public Iterable<MaintenanceTypeDTO> allTypes()
    {
        return this.service.listAllMaintenanceTypes();
    }
}
