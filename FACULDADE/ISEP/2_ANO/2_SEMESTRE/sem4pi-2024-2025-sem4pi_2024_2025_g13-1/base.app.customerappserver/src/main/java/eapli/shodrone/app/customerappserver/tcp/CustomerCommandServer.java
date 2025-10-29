package eapli.shodrone.app.customerappserver.tcp;

import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CustomerCommandServer {

    public static void main(String[] args) {
        final int PORT = 8889;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("CustomerAppServer active on port " + PORT + "...");

            while (true) {
                ShowProposalRepository repo = PersistenceContext.repositories().showProposalRepository();
                Socket clientSocket = serverSocket.accept();
                new Thread(new CustomerCommandHandler(clientSocket, repo)).start();
            }

        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }
}
