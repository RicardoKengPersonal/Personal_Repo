package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.shodrone.dronemanagement.application.MaintenanceTypeCreationController;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;

public class MaintenanceTypeCreationUI extends AbstractUI {

    private final MaintenanceTypeCreationController controller = new MaintenanceTypeCreationController(
            AuthzRegistry.authorizationService(), PersistenceContext.repositories().droneModel(),PersistenceContext.repositories().drone()
            ,PersistenceContext.repositories().maintenanceType());

    @Override
    protected boolean doShow() {
        boolean valid = false;

        while(!valid){
            try{
                final String name = Console.readLine("Name:");
                final String description = Console.readLine("Description:");

                this.controller.addMaintenanceType(name,description);

                System.out.println("Maintenance Type created");
                valid = true;
            } catch (IllegalArgumentException e)
            {
                System.out.println("Error: "+ e.getMessage());
            }
        }
        return true;
    }

    @Override
    public String headline(){ return "Add Maintenance Type";}
}
