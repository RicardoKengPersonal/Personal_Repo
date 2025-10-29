package MDISC.US14;

import MDISC.Utils;
import MDISC.ConsoleColor;
import MDISC.Printer;
import MDISC.US13.GraphUI;
import MDISC.US13.InputReader;
import MDISC.US13.Station;

import java.util.List;
import java.util.Scanner;

public class MaintenanceUI {

    private final Scanner scanner;
    private final InputReader dataReader;
    private final RailroadNetwork network;

    public MaintenanceUI(InputReader dataReader, RailroadNetwork network) {
        this.scanner = new Scanner(System.in);
        this.dataReader = dataReader;
        this.network = network;
    }

    public void showMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("╔═══════════════════════════  Maintenance System ═══════════════════════════════╗");
            System.out.println("║                                                                               ║");
            System.out.println("║                      Welcome to the Maintenance System!                       ║");
            System.out.println("║                                                                               ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════════════╝");

            System.out.println("\n   1 - View network                                 \n");
            System.out.println("   2 - Check connectivity              \n");
            System.out.println("   3 - Maintenance route                      \n");
            System.out.println("   4 - Maintenance route (electrified lines only)               \n");
            System.out.println("   0 - Exit                                                  \n");


            System.out.print(ConsoleColor.BLUE + "Select an option: " + ConsoleColor.RESET);

            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
                System.out.println(ConsoleColor.RED + "Please enter a valid option!" + ConsoleColor.RESET);
                continue;
            }

            switch (choice) {
                case 1:
                    showGraph();
                    break;
                case 2:
                    showConnectivity();
                    break;
                case 3:
                    showGraph();
                    executeMaintenance(false);
                    break;
                case 4:
                    showElectrifiedGraph();
                    executeMaintenance(true);
                    break;
                case 0:
                    exit = true;
                    System.out.println(ConsoleColor.YELLOW + "Closing the program..." + ConsoleColor.RESET);
                    break;
                default:
                    System.out.println(ConsoleColor.RED + "Invalid choice. Please try again." + ConsoleColor.RESET);
            }
        }
    }

    private void showGraph() {
        System.out.println("===== Displaying graph for ALL LINES on GraphStream tab =====\n");
        List<Station> stations = dataReader.getStations();
        GraphUI.visualizeGraphFromMatrix(stations, dataReader.getLines());
        System.out.println("Yellow represents the electrified lines.\nThe depots are represented in red, stations in green and terminal in blue.");
        System.out.println("\n");
    }

    private void showElectrifiedGraph(){
        System.out.println("===== Displaying graph for ELECTRIFIED LINES on GraphStream tab =====\n");
        GraphUI.visualizeGraphFromMatrix(dataReader.getAdjacencyStations(dataReader.getStations(), true), dataReader.getLines());
        System.out.println("Yellow represents the electrified lines\nThe depots are represented in red, stations in green and terminal in blue.");
        System.out.println("\n");
    }

    /**
     * Executes the maintenance route calculation for the railway network.
     *
     * @param onlyElectrified Indicates if only electrified lines should be considered.
     */
    private void executeMaintenance(boolean onlyElectrified) {
        System.out.println(onlyElectrified ?
                "Calculating maintenance route for electrified lines..." :
                "Calculating maintenance route for all lines...");

        try {
            List<String> path = Maintenance.execute(network, onlyElectrified, dataReader);
            if (path.isEmpty()) {
                System.out.println("No valid maintenance route could be found.");
            }
        } catch (Exception e) {
            System.out.println("Error calculating the maintenance route: " + e.getMessage());
        }
    }

    /**
     * Displays the adjacency matrix and checks connectivity for the railway network.
     */
    private void showConnectivity() {
        boolean exit = false;

        while (!exit) {
            System.out.println("===========================================================================");
            System.out.println("                             View Connectivity                             ");
            System.out.println("===========================================================================");

            System.out.println("1. For all");
            System.out.println("2. Electrified only");
            System.out.println("0. Exit");
            System.out.print("Choose an option. ");

            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
                System.out.println("Please enter a valid option!");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("===========================================================================");
                    System.out.println("                              Adjacency Matrix                             ");
                    System.out.println("===========================================================================");
                    int[][] fullMatrix = network.getAdjacencyMatrix(false);
                    Printer.printMatrix(fullMatrix);
                    System.out.println();
                    showMatrixConnectivity(fullMatrix);
                    break;

                case 2:
                    System.out.println("===========================================================================");
                    System.out.println("                       Electrified Adjacency Matrix                        ");
                    System.out.println("===========================================================================");
                    int[][] electrifiedMatrix = network.getAdjacencyMatrix(true);
                    Printer.printMatrix(electrifiedMatrix);
                    System.out.println();
                    showMatrixConnectivity(electrifiedMatrix);
                    break;

                case 0:
                    exit = true;
                    System.out.println("Closing the program.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");

            }
        }
    }

    private void showMatrixConnectivity(int[][] matrix) {
        int n = matrix.length;
        int[][] simplifiedMatrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] > 0) {
                    simplifiedMatrix[i][j] = 1;
                }
            }
        }

        if (Utils.isGraphConnected(simplifiedMatrix)) {
            System.out.println("The graph is CONNECTED.");
        } else {
            System.out.println("The graph is NOT CONNECTED.");
        }
    }
}


