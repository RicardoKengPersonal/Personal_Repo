package eapli.shodrone.app.simulatorserver.tcp;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimulatorCommandServer {
    public static void main(String[] args) {
        final int PORT = 8891;
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Simulator Server listening on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new SimulatorCommandHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Failed to start Simulator Server: " + e.getMessage());
        }
    }
}
