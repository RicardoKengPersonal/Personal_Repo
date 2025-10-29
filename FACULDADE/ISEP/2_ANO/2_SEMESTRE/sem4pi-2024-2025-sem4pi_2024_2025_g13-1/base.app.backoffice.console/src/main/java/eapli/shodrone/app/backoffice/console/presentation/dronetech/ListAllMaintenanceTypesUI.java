package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.shodrone.dronemanagement.application.ListAllMaintenanceTypesController;
import eapli.shodrone.dronemanagement.domain.MaintenanceType;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;

public class ListAllMaintenanceTypesUI extends AbstractUI {

    private final ListAllMaintenanceTypesController controller = new ListAllMaintenanceTypesController(
            AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().drone(),
            PersistenceContext.repositories().droneModel(),
            PersistenceContext.repositories().maintenanceType()
    );

    @Override
    protected boolean doShow() {
        Iterable<MaintenanceType> maintenanceTypes = controller.findAllMaintenanceTypes();

        System.out.println("\nMaintenance Types registered in the system:");

        boolean any = false;
        for (MaintenanceType mt : maintenanceTypes) {
            System.out.println(" - Name: " + mt.name() + ", Description: " + mt.description());
            any = true;
        }

        if (!any) {
            System.out.println("No maintenance types found.");
        }

        return true;
    }

    @Override
    public String headline() {
        return "List All Maintenance Types";
    }
}
