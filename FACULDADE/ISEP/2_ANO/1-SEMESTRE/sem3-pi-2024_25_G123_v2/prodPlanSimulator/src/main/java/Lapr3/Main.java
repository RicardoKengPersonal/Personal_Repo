package Lapr3;

import org.example.Domain.Article;
import org.example.Domain.Tree;
import org.example.Domain.TreeNode;
import org.example.Domain.Workstation;
import org.example.FileIO.Files;
import org.example.ProductionSimulator.Simulator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.io.IOException;

import static Lapr3.orderSimulation.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize maps and objects to store the necessary data
        Map<String, Workstation> workstationsList = new HashMap<>();
        Map<String, String> operationDescriptions = new HashMap<>();
        Map<String, String> productsName = new HashMap<>();
        Map<Integer, Order> ordersInformation = new HashMap<>();
        Map<String, TreeNode> nodeMap = new HashMap<>();

        // Create a simulator instance
        Simulator simulator = new Simulator();

        // Create a ProductionSimulator instance
        ProductionSimulator productionSimulator = new ProductionSimulator(simulator);

        // Display Menu
        int choice;
        do {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Update Average Production Times");
            System.out.println("2. Load and Display Workstation List / Operations (USLP08)");
            System.out.println("3. Simulate Order Production (USLP06)");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1:
                        // Update Workstation Times in the Database
                        productionSimulator.updateWorkstationExecutionTimes();
                        break;
                    case 2:
                        int option;
                        // Load and display workstation list when option 7 is selected
                        String workstationFilePath = "./prodPlanSimulator/src/main/java/Lapr3/workstations.csv"; // Replace with your CSV file path
                        workstationsList = WorkstationLoader.loadWorkstations(workstationFilePath);  // Load workstations

                        // Load operations
                        String operationFilePath = "./prodPlanSimulator/src/main/java/Lapr3/operations.csv"; // Replace with your operations CSV file path
                        operationDescriptions = OperationLoader.loadOperations(operationFilePath);  // Load operations


                        do {
                            System.out.println("1. Display Workstations registered through .csv file.");
                            System.out.println("2. Display Operations registered through .csv file.");
                            System.out.println("3. Associate operations to workstations.");
                            System.out.println("4. Generate instructions file for USAC17.");
                            System.out.println("5. Return to main menu.");
                            System.out.print("Choose an option: ");
                            option = scanner.nextInt();

                            try {
                                switch (option) {
                                    case 1:
                                        // Display loaded workstations
                                        if (workstationsList != null && !workstationsList.isEmpty()) {
                                            workstationsList.forEach((key, workstation) -> System.out.println(workstation));
                                        } else {
                                            System.out.println("No workstations found or failed to load.");
                                        }
                                        break;
                                    case 2:
                                        // Display loaded operations
                                        if (operationDescriptions != null && !operationDescriptions.isEmpty()) {
                                            operationDescriptions.forEach((operationId, description) -> System.out.println(operationId + ": " + description));
                                        } else {
                                            System.out.println("No operations found or failed to load.");
                                        }
                                        break;
                                    case 3:
                                        // Associate operations to workstations
                                        associateOperationsToWorkstations(workstationsList, operationDescriptions);
                                        break;
                                    case 4:
                                        // Ensure that the workstationsList is not null or empty before attempting to export
                                        if (workstationsList != null && !workstationsList.isEmpty()) {
                                            // Call the export method to write operations to CSV
                                            exportWorkstationOperationsToCSV(workstationsList, operationDescriptions);
                                        } else {
                                            // Display a message if there are no workstations to export
                                            System.out.println("No workstations available to export.");
                                        }
                                        break;

                                    case 5:
                                        System.out.println("Returning to main menu...");
                                        break;
                                    default:
                                        System.out.println("Invalid option, please try again.");
                                }
                            } catch (Exception e) {
                                System.err.println("Error: " + e.getMessage());
                            }
                        } while (option != 5);
                        break;
                    case 3:
                        String csvFilePath = "./prodPlanSimulator/src/main/java/Lapr3/orders.csv";
                        List<Order> orders = readOrdersCSV(csvFilePath);
                        Map<Integer, Integer> operationTimes = fetchOperationTimes();
                        simulateProduction(orders, operationTimes);
                        break;
                    case 4:
                        // Exit program
                        System.out.println("Exiting program...");
                        scanner.close();
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        } while (choice != 4);

    }

    private static void associateOperationsToWorkstations(Map<String, Workstation> workstationsList, Map<String, String> operationDescriptions) {
        // Iterate over each operation in operationDescriptions
        for (Map.Entry<String, String> entry : operationDescriptions.entrySet()) {
            String operationId = entry.getKey();  // Get operation ID (e.g., "11")
            String operationDescription = entry.getValue();  // Get operation description (e.g., "Packaging 05")

            // Use the operation ID to map to the correct workstation
            String workstationId = getWorkstationIdForOperation(operationId);  // Map operation ID to workstation ID

            Workstation workstation = workstationsList.get(workstationId);
            if (workstation != null) {
                // Associate the operation ID with the workstation
                workstation.setOperation(operationDescription);  // You can store operation description, or better yet, store the operation ID
                System.out.println("Associated operation " + operationId + " with workstation " + workstationId);
            } else {
                System.out.println("No workstation found for operation " + operationId);
            }
        }
    }


    // Example mapping method to get workstation for a given operation (customize as needed)
    private static String getWorkstationIdForOperation(String operationId) {
        // Example logic for mapping operationId to workstationId. You can modify this to suit your business logic.
        switch (operationId) {
            case "1":
                return "1";
            case "2":
                return "2";  // Workstation 1
            case "3":
                return "3";
            default:
                return "4";  // Default to workstation 1 for other operations
        }
    }

    // Write content to a file
    private static void writeToFile(String filePath, String content) {
        // Create a File object with the specified path
        File file = new File(filePath);

        // Create a BufferedWriter to write content to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);  // Write the content to the file
            System.out.println("File written successfully to " + filePath);
        } catch (IOException e) {
            // Handle any I/O errors
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void exportWorkstationOperationsToCSV(Map<String, Workstation> workstationsList, Map<String, String> operationDescriptions) {
        StringBuilder content = new StringBuilder();

        // Iterate over each workstation
        for (Workstation workstation : workstationsList.values()) {
            String operationDescription = workstation.getOperation(); // Get operation description

            // Find the operationId using operationDescriptions map
            String operationId = getOperationIdByDescription(operationDescriptions, operationDescription);

            // If the operationId exists, proceed
            if (operationId != null) {
                content.append(workstation.getIdWorkstation()).append(";")
                        .append("ON,").append(operationId)
                        .append(";OP,").append(operationId)
                        .append(";OFF;")
                        .append("\n");
            } else {
                System.out.println("Operation description '" + operationDescription + "' not found in operationDescriptions.");
            }
        }

        // Specify the file path for saving the CSV
        String filePath = "./prodPlanSimulator/src/main/java/Lapr3/instrucoes.txt";
        writeToFile(filePath, content.toString().trim()); // Save operations to CSV
    }

    private static String getOperationIdByDescription(Map<String, String> operationDescriptions, String description) {
        // Log the description you're trying to find for better debugging
        System.out.println("Looking for operation description: " + description);

        for (Map.Entry<String, String> entry : operationDescriptions.entrySet()) {
            if (entry.getValue().equals(description)) {
                return entry.getKey();  // Return the operationId if the description matches
            }
        }
        // If no matching description is found, return null
        System.out.println("Operation description'" + description + "' not found in operationDescriptions.");
        return null;
    }
}