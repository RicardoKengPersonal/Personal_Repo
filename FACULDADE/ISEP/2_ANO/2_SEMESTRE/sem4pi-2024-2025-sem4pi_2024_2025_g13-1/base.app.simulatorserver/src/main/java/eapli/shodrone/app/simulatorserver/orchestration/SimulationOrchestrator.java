package eapli.shodrone.app.simulatorserver.orchestration; // Novo package

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SimulationOrchestrator implements Runnable {

    // Porta dedicada para os drones se conectarem
    private static final int DRONE_CONNECTION_PORT = 9090;

    private final String proposalId;
    private final int expectedDrones; // Número de drones esperado para esta proposta
    private final List<Thread> droneHandlers = new ArrayList<>();

    public SimulationOrchestrator(String proposalId, int expectedDrones) {
        this.proposalId = proposalId;
        this.expectedDrones = expectedDrones;
    }

    @Override
    public void run() {
        System.out.println("[Orchestrator] Starting for proposal " + proposalId + ". Waiting for " + expectedDrones + " drones on port " + DRONE_CONNECTION_PORT);

        try (ServerSocket droneServerSocket = new ServerSocket(DRONE_CONNECTION_PORT)) {
            // Aceita conexões até atingir o número esperado de drones
            while (droneHandlers.size() < expectedDrones) {
                Socket droneSocket = droneServerSocket.accept();
                System.out.println("[Orchestrator] Drone connected from: " + droneSocket.getInetAddress().getHostAddress());

                // TODO: Para cada drone, criar um handler numa nova thread
                // Thread droneThread = new Thread(new DroneConnectionHandler(droneSocket));
                // droneThread.start();
                // droneHandlers.add(droneThread);
            }

            System.out.println("[Orchestrator] All drones connected! Starting simulation...");

            // TODO: 1. Enviar a mensagem 'START_SIMULATION' para todos os drones
            // TODO: 2. Entrar num loop para receber 'POSITION' e verificar colisões
            // TODO: 3. No final, enviar 'END_SIMULATION' para todos

        } catch (IOException e) {
            System.err.println("[Orchestrator] Error: " + e.getMessage());
        }

        System.out.println("[Orchestrator] Simulation for " + proposalId + " finished.");
        // TODO: Gerar o relatório final
    }
}