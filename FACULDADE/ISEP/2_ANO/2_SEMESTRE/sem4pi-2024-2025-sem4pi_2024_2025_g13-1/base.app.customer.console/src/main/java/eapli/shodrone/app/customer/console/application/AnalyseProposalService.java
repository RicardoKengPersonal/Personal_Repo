package eapli.shodrone.app.customer.console.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class AnalyseProposalService {

    public String getProposalByAccessCode(String accessCode) {
        ClientSession session = ClientSession.getInstance();
        if (!session.isAuthenticated()) {
            return "ERROR: You are not authenticated. Please login first.";
        }

        StringBuilder response = new StringBuilder();
        try {
            PrintWriter out = session.getWriter();
            BufferedReader in = session.getReader();

            out.println("GET_PROPOSAL " + accessCode);

            String serverLine;
            // CORREÇÃO: O ciclo agora para quando encontra "---END---"
            while ((serverLine = in.readLine()) != null && !serverLine.equals("---END---")) {
                response.append(serverLine).append(System.lineSeparator());
            }

        } catch (IOException e) {
            response.append("ERROR: Could not communicate with the server. (").append(e.getMessage()).append(")");
            session.disconnect();
        }

        // Remove a última nova linha em excesso, se houver
        if (response.length() > 0) {
            response.setLength(response.length() - System.lineSeparator().length());
        }

        return response.toString();
    }
}