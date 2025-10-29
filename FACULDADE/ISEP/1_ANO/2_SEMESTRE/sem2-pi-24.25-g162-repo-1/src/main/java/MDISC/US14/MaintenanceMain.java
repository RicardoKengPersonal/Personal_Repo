package MDISC.US14;

import MDISC.US13.InputReader;

import java.util.Scanner;

public class MaintenanceMain {
    private static final Scanner scanner = new Scanner(System.in);
    private static InputReader reader;
    private static RailroadNetwork network;

    public static void main(String[] args) {

        System.out.println("🌟=======================================================================🌟");
        System.out.println("                 🚆 Welcome to the  Maintenance System 🚆                  ");
        System.out.println("🌟=======================================================================🌟");


        //loads the content from the CSV
        if (buildRailRoadNetwork()) {
            MaintenanceUI UI = new MaintenanceUI(reader, network);
            UI.showMenu();
        } else {
            System.out.println("It was not possible to load the railroad network. Closing the program.");
        }
    }

    private static boolean buildRailRoadNetwork() {
        System.out.println("Please insert the CSV path to the railroad network:");
        String filePath = scanner.nextLine();

        try {
            reader = new InputReader();
            reader.readCSV(filePath);

            if (reader.getStations().isEmpty()) {
                System.out.println("No stations found!!!");
                return false;
            }

            network = new RailroadNetwork(reader);

            System.out.println("Railroad network loaded.");

            return true;

        } catch (Exception e) {
            System.out.println("Error loading railroad network from file: " + e.getMessage());
            e.printStackTrace();
            return false;
        }


    }

}