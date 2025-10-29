package pt.ipp.isep.dei.esoft.project.mdisc;

import pt.ipp.isep.dei.esoft.project.mdisc.dependencies.Edge;
import pt.ipp.isep.dei.esoft.project.mdisc.dependencies.ExecutionResult;

import java.io.*;
import java.util.*;

public class US14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file name: ");
        String fileName = scanner.nextLine();
        List<String> inputFiles = US13.readInputFiles("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/" + fileName);
        List<ExecutionResult> results = new ArrayList<>();

        // Check if there are no input files
        if (inputFiles.isEmpty()) {
            System.out.println("No input files found in GraphsUS14.txt");
            return; // Exit the method
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/output/execution_times.csv"))) {
            writer.println("Input Size,Execution Time (s)");
            for (String inputFile : inputFiles) {
                List<Edge> edges = US13.readGraphFromCSV(inputFile);
                long startTime = System.nanoTime();
                US13.runAlgorithm(edges);
                long endTime = System.nanoTime();
                double executionTime = (endTime - startTime) / 1_000_000.0;
                int inputSize = edges.size();
                results.add(new ExecutionResult(inputFile, inputSize, executionTime));
                writer.println(inputSize + "," + executionTime);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        plotExecutionTimes(results);
    }

    public static void plotExecutionTimes(List<ExecutionResult> results) {
        try {
            String csvFilename = "src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/output/execution_times.csv";
            String outputImageFile = "src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/output/execution_times.png";

            ProcessBuilder processBuilder = new ProcessBuilder("gnuplot");
            Process process = processBuilder.start();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            writer.write("set terminal png\n");
            writer.write("set output '" + outputImageFile + "'\n");
            writer.write("set xlabel 'File Size'\n"); // Use File Size as x-axis label
            writer.write("set ylabel 'Execution Time (s)'\n");
            writer.write("set title 'Execution Time vs. File Size'\n");
            writer.write("set datafile separator ','\n");
            writer.write("plot '" + csvFilename + "' using 1:2 with points title 'Execution Time', '' using 1:2 smooth bezier notitle\n");
            writer.write("exit\n");
            writer.flush();

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new IOException("Gnuplot process exited with non-zero status: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
