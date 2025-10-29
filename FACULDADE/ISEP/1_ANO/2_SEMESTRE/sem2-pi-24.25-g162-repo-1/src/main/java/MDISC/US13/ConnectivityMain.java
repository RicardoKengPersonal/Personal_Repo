package MDISC.US13;

import MDISC.Utils;
import MDISC.Printer;

import java.util.List;
import java.util.Scanner;


public class ConnectivityMain {
    public static void main(String[] args) {
        PathCheckerUI UI = new PathCheckerUI();
        InputReader reader = new InputReader();

        while (true) {
            int option = UI.showMenu();

            switch (option) {
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                case 1 -> checkConnectivityByStationType(UI, reader);
                case 2 -> checkConnectivityByStation(UI, reader);
                case 3 -> listConnections(UI, reader);
                default -> System.out.println("Invalid option!");
            }

            System.out.println("\nPress Enter to continue...");
            new Scanner(System.in).nextLine();
        }
    }

    private static void checkConnectivityByStationType(PathCheckerUI UI, InputReader reader) {
        // gets path
        String path = UI.requestPath();

        // reads the CSV file and loads the stations and connections
        reader.readCSV(path);

        // asks to the user if he wants electrified lines
        boolean electrified = UI.eletricTrainsOnly();

        // asks the user to select the station types
        List<StationType> types = UI.stationTypeSelection();

        // gets the stations of the selected types
        List<Station> askedStations = reader.getStations(types);

        // checks if there are at least two stations of the selected types (to create a connection)
        if (askedStations.size() < 2) {
            System.out.println("No stations of the selected types to proceed.");
            return;
        }

        // builds the adjacency matrix with the due restrictions and checks if the stations are connected
        int[][] matrix = reader.buildAdjMatrixRestricted(electrified,types);

        // gets the nodes (stations)
        List<Station> stations = reader.getAdjacencyStationsRestricted(electrified, types);

        // checks if the stations are connected
        boolean connected = Utils.isGroupConnected(matrix, stations, types);

        // shows the result
        UI.displayIsMatrixConnected(connected);
        System.out.println("\nMatrix Representation:\n");
        Printer.printMatrix(matrix);
    }

    private static void checkConnectivityByStation(PathCheckerUI UI, InputReader reader) {
        String path = UI.requestPath();
        reader.readCSV(path);

        boolean electrified = UI.eletricTrainsOnly();
        List<Station> filteredStations = reader.getAdjacencyStationsRestricted(electrified, List.of(StationType.values())); //All types

        if (filteredStations.size() < 2) {
            System.out.println("Not enough stations of the selected types to proceed.");
            return;
        }

        int[][] matrix = reader.buildAdjMatrixRestricted(electrified, List.of(StationType.values()));
        Station startingStation = UI.requestStartingStation(filteredStations);
        Station endingStation = UI.requestEndingStation(filteredStations, startingStation);

        List<Station> route = Utils.dfsRoute(matrix, filteredStations, startingStation, endingStation, new boolean[matrix.length], reader);
        if(route.isEmpty()) {
            System.out.println("No route found between the selected stations.");
            return;
        }
        UI.displayRoute(route);
    }


    private static void listConnections(PathCheckerUI UI, InputReader reader) {
        String path = UI.requestPath();
        reader.readCSV(path);
        UI.displayStationConnections(reader);
        GraphUI.visualizeGraphFromMatrix(reader.getStations(), reader.getLines());
    }
}
