package MDISC.US13;

import MDISC.ConsoleColor;

import java.io.File;
import java.util.*;

public class PathCheckerUI {
    private final static Scanner sc = new Scanner(System.in);


    public int showMenu() {
        System.out.print(ConsoleColor.RESET);
        System.out.println("╔════════════════════════════════════╗");
        System.out.print("║");
        System.out.print(ConsoleColor.BLUE + "             MAIN MENU            ");
        System.out.println(ConsoleColor.RESET + "  ║");
        System.out.println("╠════════════════════════════════════╣");

        System.out.println("║ 1 - Check network by station type  ║");
        System.out.println("║ 2 - Check network by station       ║");
        System.out.println("║ 3 - View all station connections   ║");
        System.out.println("║ 0 - Exit                           ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.print("👉 Choose an option: " + ConsoleColor.RESET);

        int option = sc.nextInt();
        sc.nextLine(); // consume leftover newline
        return option;
    }

    public static String requestPath() {
        while (true) {
            System.out.print("Enter the path to the CSV file: ");
            String path = sc.nextLine();
            sc.nextLine();

            File file = new File(path);
            if (file.exists() && file.isFile() && file.canRead()) {
                return path;
            } else {
                System.out.println("Invalid file path or file cannot be read. Please try again.");
            }
        }
    }

    public boolean eletricTrainsOnly() {
        String input = "";
        while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
            System.out.print("Use only electrified lines? (Y/N): ");
            input = sc.nextLine().trim();
        }
        return input.equalsIgnoreCase("y");
    }

    public Station requestStartingStation(List<Station> stations) {
        displayStationList(stations);
        int index;
        while (true) {
            try {
                System.out.print("Select the starting station: ");
                index = sc.nextInt() - 1;
                sc.nextLine();

                if (index < 0 || index >= stations.size()) {
                    System.out.println("Invalid index. Please select a valid number.");
                } else {
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }
        return stations.get(index);
    }

    public Station requestEndingStation(List<Station> stations, Station startingStation) {
        displayStationList(stations);
        int index;
        System.out.println("Select the ending station:");

        while (true) {
            try {
                System.out.print("Select the ending station: ");
                index = sc.nextInt() - 1;
                sc.nextLine();

                if (index < 0 || index >= stations.size()) {
                    System.out.println("Invalid index. Please select a valid number.");
                } else if (stations.get(index).equals(startingStation)) {
                    System.out.println("Ending station cannot be the same as starting station.");
                } else {
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }

        return stations.get(index);
    }

    public List<StationType> stationTypeSelection() {
        List<StationType> stationTypes = new ArrayList<>();
        int type = -1;

        while (!(type == 3 && !stationTypes.isEmpty())) {
            System.out.println("Select station types to analyze:");
            displayStationTypeList();
            type = sc.nextInt();

            if (type == 3 && stationTypes.isEmpty()) {
                System.out.println("You must select at least one type before finishing.");
                continue;
            }

            if (type >= 0 && type < StationType.values().length) {
                StationType selectedType = StationType.values()[type];
                if (!stationTypes.contains(selectedType)) {
                    stationTypes.add(selectedType);
                }
            } else if (type != 3) {
                System.out.println("Invalid selection. Please try again.");
            }
        }

        sc.nextLine(); // clear buffer
        return stationTypes;
    }

    public void displayStationTypeList() {
        for (int i = 0; i < StationType.values().length; i++) {
            System.out.printf("%d. %s%n", i, StationType.values()[i]);
        }
        System.out.println("3. Done");
    }

    public void displayRoute(List<Station> route) {
        if (route.isEmpty()) {
            System.out.println("No path found between the selected stations.");
        } else {
            System.out.println("\nRoute:");
            for (int i = 0; i < route.size(); i++) {
                System.out.printf("%d. %s (%s)%n", i + 1, route.get(i).getName(), route.get(i).getType());
            }
        }
    }

    private void displayStationList(List<Station> stations) {
        System.out.println("\n##### List of Stations ######");
        System.out.printf("%-5s | %-25s | %-15s%n", "Index", "Station Name", "Station Type");
        System.out.println("---------------------------------------------------------");

        for (int i = 0; i < stations.size(); i++) {
            System.out.printf("%-5d | %-25s | %-15s%n",
                    i + 1,
                    stations.get(i).getName(),
                    stations.get(i).getType().name());
        }
    }

    public void displayStationConnections(InputReader reader) {
        List<Line> connections = reader.getLines();

        System.out.println("\n##### List of Connections #####");
        System.out.printf("%-25s → %-25s | %-12s | %-10s%n", "Station A", "Station B", "Electrified", "Distance");
        System.out.println("-----------------------------------------------------------------------");

        for (Line conn : connections) {
            System.out.printf("%-25s → %-25s | %-12s | %7.2f km%n",
                    conn.getStationA().getName(),
                    conn.getStationB().getName(),
                    conn.isElectrified() ? "Yes" : "No",
                    conn.getDistance());
        }
    }

    public void displayIsMatrixConnected(boolean connected) {
        System.out.println();
        if (connected) {
            System.out.println("Network status: CONNECTED");
        } else {
            System.out.println("Network status: NOT CONNECTED");
        }
    }

}