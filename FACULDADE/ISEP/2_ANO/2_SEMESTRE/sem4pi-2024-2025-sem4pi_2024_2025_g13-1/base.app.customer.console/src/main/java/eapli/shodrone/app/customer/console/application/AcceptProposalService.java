package eapli.shodrone.app.customer.console.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class AcceptProposalService {

    public void acceptProposal(String accessCode) {
        ClientSession session = ClientSession.getInstance();
        if (!session.isAuthenticated()) {
            System.err.println("Error: You are not authenticated.");
            return;
        }

        try {
            PrintWriter out = session.getWriter();
            BufferedReader in = session.getReader();

            // Enviar o comando na sessão já autenticada
            out.println("ACCEPT_PROPOSAL " + accessCode);

            System.out.println("\n--- Create show ---");
            String line;
            while ((line = in.readLine()) != null) {
                if (line.equals("---END---")) break;
                System.out.println(line);
            }
            System.out.println("--- Show created ---\n");

        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            session.disconnect();
        }
    }

    public void rejectProposal(String accessCode) {
        ClientSession session = ClientSession.getInstance();
        if (!session.isAuthenticated()) {
            System.err.println("Error: You are not authenticated.");
            return;
        }

        try {
            PrintWriter out = session.getWriter();
            BufferedReader in = session.getReader();

            out.println("REJECT_PROPOSAL " + accessCode);

            String serverResponse = in.readLine();
            System.out.println("\nServer response: " + serverResponse);

        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            session.disconnect();
        }
    }
}