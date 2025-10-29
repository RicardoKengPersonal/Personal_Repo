package eapli.shodrone.app.simulatorserver.tcp;

import eapli.shodrone.app.simulatorserver.config.AppConfig;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class SimulatorCommandHandler implements Runnable {
    private final Socket clientSocket;

    // Caminhos e nomes de ficheiros corrigidos e centralizados
    private static final String REPORT_NAME = "relatorio_simulacao.txt"; // NOME CORRIGIDO
    private static final String SPRINT3_FOLDER = "base.daemon.simulation/Sprint3";
    private static final Path LOCAL_REPORT_PATH = Path.of(SPRINT3_FOLDER, REPORT_NAME);

    public SimulatorCommandHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String request = in.readLine();
            if (request == null) return;

            String[] parts = request.split(" ", 2);
            String command = parts[0];

            if ("START_SIMULATION_SHOW_PROPOSAL".equalsIgnoreCase(command) && parts.length > 1) {
                String proposalId = parts[1];
                System.out.println("[Handler] Command received for proposal: " + proposalId);

                try {
                    boolean success = runSimulation(proposalId);

                    if (success) {
                        waitForFile(LOCAL_REPORT_PATH);
                        byte[] reportBytes = Files.readAllBytes(LOCAL_REPORT_PATH);
                        saveReport(reportBytes);
                        out.println("SUCCESS"); // Envia o estado final para o cliente
                        System.out.println("[✔] Simulation for " + proposalId + " completed successfully.");
                    } else {
                        out.println("FAILURE");
                        System.out.println("[✘] C simulation for " + proposalId + " failed.");
                    }

                } catch (Exception e) {
                    System.err.println("[Handler] Error during simulation execution: " + e.getMessage());
                    out.println("FAILURE");
                }

            } else {
                out.println("FAILURE - Invalid command");
            }
        } catch (IOException e) {
            System.err.println("Error handling simulation request: " + e.getMessage());
        }
    }

    private boolean runSimulation(String proposalId) throws IOException, InterruptedException {

        final String wslPath = AppConfig.getWslSimulationPath();
        System.out.println("[Handler] Executing interactive C simulation for proposal: " + proposalId);

        ProcessBuilder builder = new ProcessBuilder("wsl", "bash", "-c", "cd " + wslPath + " && make run");

        // Redireciona o stream de erro para o de saída para capturarmos tudo
        builder.redirectErrorStream(true);

        Process process = builder.start();

        // Streams para a "ponte"
        InputStream processOut = process.getInputStream();
        OutputStream processIn = process.getOutputStream();
        InputStream clientIn = clientSocket.getInputStream();
        OutputStream clientOut = clientSocket.getOutputStream();

        // Thread para encaminhar input do Cliente -> Processo C
        Thread clientToProcess = new Thread(() -> {
            try {
                int b;
                while ((b = clientIn.read()) != -1) {
                    processIn.write(b);
                    processIn.flush();
                }
            } catch (IOException e) { /* Socket fechado */ }
        });

        // Thread para encaminhar output do Processo C -> Cliente
        Thread processToClient = new Thread(() -> {
            try {
                int b;
                while ((b = processOut.read()) != -1) {
                    clientOut.write(b);
                    clientOut.flush();
                }
            } catch (IOException e) { /* Socket fechado */ }
        });

        clientToProcess.start();
        processToClient.start();

        int exitCode = process.waitFor();

        clientToProcess.interrupt();
        processToClient.interrupt();

        System.out.println("[Handler] C simulation finished with exit code: " + exitCode);
        return exitCode == 0;
    }

    private void waitForFile(Path path) throws InterruptedException {
        int retries = 30; // Espera até 30 segundos
        while (!Files.exists(path) && retries-- > 0) {
            Thread.sleep(1000);
        }
        if (!Files.exists(path)) {
            throw new RuntimeException("Report file not found after timeout.");
        }
    }

    private void saveReport(byte[] data) throws IOException {
        File reportsDir = new File("received_reports");
        if (!reportsDir.exists()) {
            reportsDir.mkdirs();
        }
        File reportFile = new File(reportsDir, REPORT_NAME);
        Files.write(reportFile.toPath(), data);
        System.out.println("[Handler] Report saved at: " + reportFile.getAbsolutePath());
    }
}