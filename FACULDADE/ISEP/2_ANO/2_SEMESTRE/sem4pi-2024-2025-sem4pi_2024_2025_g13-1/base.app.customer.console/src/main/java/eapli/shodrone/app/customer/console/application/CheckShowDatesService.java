package eapli.shodrone.app.customer.console.application;

import eapli.shodrone.showmanagement.dto.ShowDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CheckShowDatesService {

    public List<ShowDTO> listMyScheduledShows() {
        List<ShowDTO> shows = new ArrayList<>();
        ClientSession session = ClientSession.getInstance();

        // Verificar se o cliente está autenticado (tem uma sessão ativa)
        if (!session.isAuthenticated()) {
            System.err.println("You are not logged in. Please login first.");
            return shows;
        }

        try {
            PrintWriter out = session.getWriter();
            BufferedReader in = session.getReader();

            out.println("LIST_SHOWS");

            // Ler resposta
            String line;
            while ((line = in.readLine()) != null && !line.equals("---END---")) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    ShowDTO dto = new ShowDTO(parts[0], parts[1], parts[2], parts[3], parts[4]);
                    shows.add(dto);
                }
            }

        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            // Se a comunicação falhar, é boa prática terminar a sessão
            session.disconnect();
        }

        // A lógica de filtragem e ordenação permanece a mesma
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate hoje = LocalDate.now();

        return shows.stream()
                .filter(s -> "SCHEDULED".equalsIgnoreCase(s.getStatus()))
                .filter(s -> {
                    try {
                        LocalDate showDate = LocalDate.parse(s.getDate(), formatter);
                        return !showDate.isBefore(hoje);
                    } catch (Exception e) {
                        return false;
                    }
                })
                .sorted(Comparator.comparing(s -> LocalDate.parse(s.getDate(), formatter)))
                .collect(Collectors.toList());
    }
}