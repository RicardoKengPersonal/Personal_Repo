package eapli.shodrone.app.testing.console.application;

import eapli.shodrone.app.simulatorserver.config.AppConfig;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalID;

import java.io.*;
import java.net.Socket;

public class RunSimulationService {

    private final int port = 8891;

    public boolean runSimulation(ShowProposalID showProposalID) {

        final String host = AppConfig.getSimulatorHost();

        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String command = "START_SIMULATION_SHOW_PROPOSAL " + showProposalID.toString();
            out.println(command);

            System.out.println("\n--- Simulation Console Bridge Activated ---");

            // Thread para ler do teclado e enviar para o servidor
            Thread keyboardInputThread = new Thread(() -> {
                try (BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in))) {
                    String line;
                    while (!Thread.currentThread().isInterrupted() && (line = keyboardReader.readLine()) != null) {
                        out.println(line);
                    }
                } catch (IOException e) { /* Thread a terminar */ }
            });
            keyboardInputThread.start();

            // Captura toda a saída para depois verificar o resultado
            StringBuilder fullOutput = new StringBuilder();
            int character;
            while ((character = in.read()) != -1) {
                System.out.print((char) character);
                fullOutput.append((char) character);
            }

            System.out.println("\n--- End of simulation ---");
            keyboardInputThread.interrupt();

            // Retorna true APENAS se a palavra "SUCCESS" foi recebida do servidor
            return fullOutput.toString().contains("SUCCESS");

        } catch (IOException e) {
            System.err.println("Error communicating with the simulation server: " + e.getMessage());
            return false;
        }
    }
}