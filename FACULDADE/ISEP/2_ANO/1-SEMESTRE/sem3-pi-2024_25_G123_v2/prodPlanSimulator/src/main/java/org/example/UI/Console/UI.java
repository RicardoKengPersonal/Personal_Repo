package org.example.UI.Console;

import org.example.Domain.*;
import org.example.Dto.WorkstationStats;
import org.example.FileIO.Files;
import org.example.ProductionSimulator.PrioritySimulator;
import org.example.ProductionSimulator.Simulator;
import org.example.esinf.usei13.ProductionCalculator;
import org.example.esinf.usei17.ImportActivitiesGraph;
import org.example.structures.graph.pert.PertAlgorithms;
import org.example.structures.graph.pert.PertGraph;
import org.example.structures.graph.pert.PertVertex;
import Lapr3.ProductionSimulator;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.example.Domain.CriticalPathAnalyzer.performCriticalPathAnalysis;
import static org.example.Utils.DisplayUtils.printSortedCalculateDependentCounts;
import static org.example.structures.graph.pert.PertAlgorithms.calculateDependentCounts;


/**
 * User Interface class for the console application to interact with the production simulator.
 */
public class UI {

    private static PertGraph<PertVertex<String, Integer>, Integer> graph;

    /**
     * Main method to run the simulation and display the user interface.
     */
    public static void run() throws Exception {
        List<Article> articleList = Files.readArticles("prodPlanSimulator/src/main/java/resources/articles.csv");
        Map<String, Workstation> workstations = Files.readWorkstations("prodPlanSimulator/src/main/java/resources/workstations.csv");

        Simulator simulator = new Simulator();
        simulator.computeSimulation(articleList, workstations); // Initialize and run the main simulation

        // Initialize the graph
        graph = new PertGraph<>(true); // or false, depending on whether the graph is directed

        // Display the menu
        displayMenu(simulator);
    }



    /**
     * Displays the menu for the user to select options related to the simulation.
     *
     * @param sim the Simulator object that contains the simulation logic
     */
    private static void displayMenu(Simulator sim) throws IOException {
        Scanner scanner = new Scanner(System.in);

        Map<String, TreeNode> nodeMap = new HashMap<>();
        Map<String, String> items = Files.readItemsFile("prodPlanSimulator/src/main/java/resources/items.csv");
        Map<String, String> operations = Files.readOperationsFile();
        String booFile = "prodPlanSimulator/src/main/java/resources/boo_v2.csv";
        TreeNode root = Files.readBooFile(nodeMap, items, operations, booFile);
        Tree tree = new Tree(root, nodeMap);
        tree.CreateTree();

        do {
            System.out.println("Select an option:");
            System.out.println("1. Display the scheduling table.");
            System.out.println("2. Display total time per article instance.");
            System.out.println("3. Display total time per operation.");
            System.out.println("4. Display Workstation times per percentage.");
            System.out.println("5. Display average execution and waiting times per operation.");
            System.out.println("6. Display flow dependency between machines.");
            System.out.println("7. Display priority simulator.");
            System.out.println("8. Build production Tree.");
            System.out.println("9. Search tree using name/id");
            System.out.println("10. Display Materials needed sorted by quantity");
            System.out.println("11. Display Critical Path Analysis.");
            System.out.println("12. Update material quantities in the production tree");
            System.out.println("13. Display the Materials needed for production.");
            System.out.println("14. Generate BOM svg.");
            System.out.println("15. Generate BOO svg.");
            System.out.println("-----------Sprint 3-----------");
            System.out.println("16. Import Small Activities Graph.(USEI17)");
            System.out.println("17. Import Large Activities Graph.(USEI17)");
            System.out.println("18. Simulate Project Delay.(USEI24)");
            System.out.println("19. Identify Critical Path.(USEI22)");
            System.out.println("20. Identify Bottlenecks Activities.(USEI19)");
            System.out.println("21. Calculate Earliest Start Times and Latest Finish Times.(USEI20)");
            System.out.println("22. Export graph to CSV.(USEI21)");
            System.out.println("0. Exit.");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt(); // Get user choice

            // Handle user choice
            switch (choice) {
                case 1:
                    sim.displayScheduledOperationsTable(); // Display scheduled operations
                    break;
                case 2:
                    sim.displayTotalTimePerInstanceWithArticleType(); // Show total time per article
                    break;
                case 3:
                    sim.timeSpentPerOperation(); // Display time spent per operation
                    break;
                case 4:
                    List<WorkstationStats> workstationStats = sim.calculateWorkstationOperationTimes();
                    sim.displayWorkstationOperationTimes(workstationStats); // Show workstation stats
                    break;
                case 5:
                    Map<String, Map<String, Double>> avgTimes = sim.calculateAverageExecutionAndWaitingTimes();
                    sim.displayAverageExecutionAndWaitingTimes(avgTimes); // Display average times
                    break;
                case 6:
                    sim.produceFlowDependencyListing(); // Show flow dependencies
                    break;
                case 7:
                    // Instantiate and run the priority-based simulator
                    PrioritySimulator prioritySimulator = new PrioritySimulator();
                    prioritySimulator.computePrioritySimulation(); // Run the simulation

                    // Display results from the priority simulator
                    System.out.println("Scheduled Operations Table:");
                    prioritySimulator.displayScheduledOperationsTable();
                    System.out.println("\nTotal Time Per Article Type:");
                    prioritySimulator.displayTotalTimePerArticleType();
                    System.out.println("\nTime Spent Per Operation:");
                    prioritySimulator.timeSpentPerOperation();
                    System.out.println("\nWorkstation Operation Times and Percentages:");
                    prioritySimulator.displayWorkstationOperationTimes();
                    break;
                case 8:
                    tree.displayTree(root, "");
                    break;
                case 9:
                    searchTree(tree);
                    break;
                case 10:
                    boolean ascending = getAscendingChoice();
                    List<TreeNode> materials = tree.getMaterialsInOrder(ascending);

                    for (TreeNode material : materials) {
                        System.out.println(material.getName() + " (" + material.getQuantity() + ")");
                    }
                    break;
                case 11:
                    performCriticalPathAnalysis(root); // New critical path analysis feature
                    break;
                case 12:
                    updateMaterialQuantities(tree);
                    break;
                case 13:
                    ProductionCalculator.calculateMaterialTotals(root);
                    break;
                case 14:
                    DotGeneration.generateMaterialTreeFiles(root, "BOM.dot", "BOM.svg");
                    break;
                case 15:
                    DotGeneration.generateOperationTreeFiles(root, "BOO.dot", "BOO.svg");
                    break;
                case 16:
                    graph = ImportActivitiesGraph.importPertCpmGraph("prodPlanSimulator/src/test/java/org/example/esinf/usei17/small_project.csv");
                    break;
                case 17:
                    graph = ImportActivitiesGraph.importPertCpmGraph("prodPlanSimulator/src/test/java/org/example/esinf/usei17/large_project.csv");
                    break;
                case 18:
                    if (graph == null || graph.vertices().isEmpty()) {
                        System.out.println("\nError: The graph is not properly initialized or is empty.\n");
                    } else {
                        System.out.println("Project Delay Simulator:");
                        delaySimulator(graph);
                    }
                    break;
                case 19:
                    if (graph == null || graph.vertices().isEmpty()) {
                        System.out.println("\nError: The graph is not properly initialized or is empty.\n");
                    } else {
                        System.out.println("Critical Path Analysis:");
                        try {
                            PertAlgorithms algorithms = new PertAlgorithms();
                            algorithms.calculateSlack(graph);

                            System.out.println("Critical Path:");
                            for (PertVertex<String, Integer> vertex : graph.vertices()) {
                                if (vertex.getSlack() == 0) {
                                    System.out.println("Activity ID: " + vertex.getElement());
                                    System.out.println("ES: " + vertex.getEarliestStartTime() + " unit(s)");
                                    System.out.println("LS: " + vertex.getLatestStartTime() + " unit(s)");
                                    System.out.println("EF: " + vertex.getEarliestFinishTime() + " unit(s)");
                                    System.out.println("LF: " + vertex.getLatestFinishTime() + " unit(s)");
                                    System.out.println("Slack: " + vertex.getSlack() + " unit(s)");
                                }
                            }

                            int totalProjectDuration = graph.vertices().stream()
                                    .mapToInt(PertVertex::getLatestFinishTime)
                                    .max()
                                    .orElse(0);

                            System.out.println("Total Project Duration: " + totalProjectDuration + " unit(s)");
                        } catch (Exception e) {
                            System.out.println("Error performing critical path analysis: " + e.getMessage());
                        }
                    }
                    break;

                case 20:
                    if (graph == null || graph.vertices().isEmpty()) {
                        System.out.println("\nError: The graph is not properly initialized or is empty.\n");
                    } else {
                    Map<PertVertex<String, Integer>, Integer> dependentCounts = calculateDependentCounts(graph);
                    printSortedCalculateDependentCounts(dependentCounts);
                    }
                    break;
                case 21:
                    if (graph == null || graph.vertices().isEmpty()) {
                        System.out.println("\nError: The graph is not properly initialized or is empty.\n");
                    } else {

                        PertAlgorithms.calculateEarliestStartAndFinish(graph);
                        PertAlgorithms.calculateLatestStartAndFinish(graph);

                        System.out.println();

                        for (Object vertex : graph.vertices()) {
                            System.out.println(vertex);
                        }
                    }
                    break;

                case 22:
                    if (graph == null || graph.vertices().isEmpty()) {
                        System.out.println("\nError: The graph is not properly initialized or is empty.\n");
                    } else {
                        PertGraph.exportPertGraphToCSV(graph);
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        } while (true); // Loop until the user decides to exit
    }

    public static boolean getAscendingChoice() {
        System.out.println("Do you want the materials displayed by ascending order of quantity?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        Scanner scanner = new Scanner(System.in);
        int ascending = 0;
        while (ascending != 1 && ascending != 2) {
            ascending = scanner.nextInt();
        }

        return ascending == 1;

    }

    public static void searchTree(Tree tree){
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter the name or Id of the operation/material you want to search:");
            String identifier = scanner.nextLine();

            // Search for the node
            TreeNode node = tree.searchNode(identifier);
            if (node == null) {
                System.out.println("Material/Operation not found in the production tree. Please try again.");
                break; // Prompt the user again
            }

            System.out.println(tree.searchNodeE(node));
        }
    }

    /*----------------------------------US12----------------------------------*/
    /**
     * Updates the quantity of a material in the production tree.
     *
     * @param tree The production tree containing the materials
     */
    public static void updateMaterialQuantities(Tree tree) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter the name or ID of the material you want to update:");
            String materialIdentifier = scanner.nextLine(); // Read and trim the input

            // Search for the material node
            TreeNode materialNode = tree.searchNode(materialIdentifier);
            if (materialNode == null) {
                System.out.println("Material not found in the production tree. Please try again.");
                continue; // Prompt the user again
            }

            // Display the current quantity of the material
            System.out.println(materialIdentifier + " has a current quantity of " + materialNode.getQuantity());

            double newQuantity = 0;

            while (newQuantity <= 0) {
                System.out.println("Enter the new quantity:");

                newQuantity = scanner.nextDouble();

                // Check for negative quantities
                if (newQuantity <= 0) {
                    System.out.println("Quantity cannot be negative or zero. Please enter a positive value.");
                }
            }
            scanner.nextLine(); // Clear

            // Update the material quantity in the production tree
            Tree.updateMaterialQuantities(materialNode, newQuantity);

            break;
        }

    }

    // US 24

    public static void delaySimulator(PertGraph<PertVertex<String, Integer>, Integer> graph) {
        if (graph == null || graph.vertices().isEmpty()) {
            System.out.println("Error: The graph is not properly initialized or is empty.");
            return;
        }

        ProjectDelaySimulator simulator = new ProjectDelaySimulator(graph);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display available activities
            System.out.println("\nAvailable Activities:");
            for (PertVertex<String, Integer> vertex : graph.vertices()) {
                System.out.printf("Activity ID: %s, Duration: %d%n", vertex.getElement(), vertex.getDuration());
            }

            // Get activity ID
            System.out.print("Enter the activity ID to delay (or type 'exit' to quit): ");
            String activityId = scanner.nextLine().trim();

            if (activityId.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the simulator.");
                break;
            }

            // Validate activity ID
            boolean validActivity = graph.vertices().stream()
                    .anyMatch(vertex -> vertex.getElement().equals(activityId));

            if (!validActivity) {
                System.out.println("Error: Invalid activity ID. Please enter a valid ID from the list.");
                continue;
            }

            // Get delay amount
            System.out.print("Enter the delay amount (in units): ");
            try {
                int delayAmount = Integer.parseInt(scanner.nextLine().trim());

                if (delayAmount < 0) {
                    System.out.println("Error: Delay must be a non-negative value.");
                    continue;
                }

                // Apply delay
                if (simulator.applyDelay(activityId, delayAmount)) {
                    System.out.println("Delay applied successfully.");

                    // Recalculate schedule
                    simulator.resetDelays();
                    simulator.applyDelay(activityId, delayAmount);
                    simulator.calculateEarliestTimes();
                    simulator.calculateLatestTimes();

                    // Display updated schedule
                    System.out.println("\nUpdated Schedule:");
                    for (PertVertex<String, Integer> vertex : graph.vertices()) {
                        System.out.printf(
                                "Activity ID: %s, Earliest Start: %d, Earliest Finish: %d, Latest Start: %d, Latest Finish: %d%n",
                                vertex.getElement(),
                                vertex.getEarliestStartTime(),
                                vertex.getEarliestFinishTime(),
                                vertex.getLatestStartTime(),
                                vertex.getLatestFinishTime()
                        );
                        // Calculate and display slack time for each activity
                        int slack = vertex.getLatestStartTime() - vertex.getEarliestStartTime();
                        System.out.printf("Slack for activity %s: %d%n", vertex.getElement(), slack);
                    }

                    // Display critical path details
                    int criticalPathDuration = simulator.calculateCriticalPath();
                    System.out.println("Total project duration (critical path): " + criticalPathDuration);
                } else {
                    System.out.println("Error: Failed to apply delay to activity ID '" + activityId + "'.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid delay amount. Please enter a valid integer.");
            }
        }

        // Final summary after exiting
        System.out.println("\nSimulation Summary:");
        System.out.println("Total project duration (critical path): " + simulator.calculateCriticalPath());
        for (PertVertex<String, Integer> vertex : graph.vertices()) {
            int slack = vertex.getLatestStartTime() - vertex.getEarliestStartTime();
            System.out.printf("Activity ID: %s, Slack: %d%n", vertex.getElement(), slack);
        }
    }


}
