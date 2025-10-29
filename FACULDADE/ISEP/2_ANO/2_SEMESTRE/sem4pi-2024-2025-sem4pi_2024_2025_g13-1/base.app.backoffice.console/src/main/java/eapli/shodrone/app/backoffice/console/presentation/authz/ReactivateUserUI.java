package eapli.shodrone.app.backoffice.console.presentation.authz;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.shodrone.usermanagement.application.ReactivateUserController;

import java.util.ArrayList;
import java.util.List;

public class ReactivateUserUI extends AbstractUI {

    private final ReactivateUserController controller = new ReactivateUserController();

    @Override
    protected boolean doShow() {
        final List<SystemUser> list = new ArrayList<>();
        final Iterable<SystemUser> iterable = controller.inactiveUsers();

        if (!iterable.iterator().hasNext()) {
            System.out.println("There are no inactive users.");
        } else {
            int cont = 1;
            System.out.println("SELECT User to reactivate\n");
            System.out.printf("%-6s%-10s%-30s%-30s%n", "Nº:", "Username", "Firstname", "Lastname");

            for (final SystemUser user : iterable) {
                list.add(user);
                System.out.printf("%-6d%-10s%-30s%-30s%n", cont, user.username(),
                        user.name().firstName(), user.name().lastName());
                cont++;
            }

            final int option = Console.readInteger("Enter user nº to reactivate or 0 to cancel ");
            if (option == 0) {
                System.out.println("No user selected.");
            } else {
                controller.activateUser(list.get(option - 1));
                System.out.println("User successfully reactivated.");
            }
        }
        return true;
    }

    @Override
    public String headline() {
        return "Reactivate User";
    }
}
