package eapli.shodrone.app.customer.console.presentation;

import eapli.framework.actions.Action;
import eapli.framework.io.util.Console;
import eapli.shodrone.app.customer.console.application.AppSettings;
import eapli.shodrone.app.customer.console.application.ClientSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginAction implements Action {

    @Override
    public boolean execute() {
        // Carrega as configurações do ficheiro
        final AppSettings settings = new AppSettings();
        final String host = settings.getServerHost();
        final int port = settings.getServerPort();

        System.out.println("== Login ==");
        String username = Console.readLine("Username: ");
        String password = Console.readLine("Password: ");

        try {
            ClientSession session = ClientSession.getInstance();
            // Usa os valores carregados para se conectar
            if (!session.connect(host, port)) {
                System.err.println("Could not connect to the server at " + host + ":" + port);
                return false;
            }

            PrintWriter out = session.getWriter();
            BufferedReader in = session.getReader();

            out.println("AUTH " + username + " " + password);

            String response = in.readLine();

            if (response != null && "AUTH_SUCCESS".equalsIgnoreCase(response)) {
                System.out.println("Login successful!");
                return true;
            } else {
                System.out.println("Login failed: Invalid credentials or server error.");
                session.disconnect();
                return false;
            }

        } catch (IOException e) {
            System.err.println("Error connecting to server " + host + ":" + port + " - " + e.getMessage());
            return false;
        }
    }
}