package eapli.shodrone.app.customer.console.application;

import eapli.shodrone.showmanagement.dto.ShowExtendedDTO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class GetShowInfoService {

    public ShowExtendedDTO getShowInfoById(String showId) {
        ClientSession session = ClientSession.getInstance();

        if (!session.isAuthenticated()) {
            System.err.println("You are not logged in. Please login first.");
            return null;
        }

        try {
            PrintWriter out = session.getWriter();
            BufferedReader in = session.getReader();


            out.println("GET_SHOW_INFO " + showId );

            String line = in.readLine();
            if (line == null || line.equalsIgnoreCase("NOT_FOUND")) {
                return null;
            }

            String[] parts = line.split(";", -1);
            if (parts.length == 9) {
                return new ShowExtendedDTO(parts[0], parts[1], parts[2], parts[3], parts[4],
                        parts[5], parts[6], parts[7], parts[8]);
            } else {
                System.err.println("Malformed response from server.");
            }

        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            session.disconnect();
        }

        return null;
    }
}