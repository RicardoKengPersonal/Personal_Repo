package eapli.shodrone.app.customer.console.presentation;

import eapli.framework.actions.Action;
import eapli.shodrone.app.customer.console.application.ClientSession;

public class LogoutAction implements Action {

    @Override
    public boolean execute() {
        // Desconecta a sessão do cliente do servidor
        ClientSession.getInstance().disconnect();
        System.out.println("You have been logged out.");
        return false;
    }
}