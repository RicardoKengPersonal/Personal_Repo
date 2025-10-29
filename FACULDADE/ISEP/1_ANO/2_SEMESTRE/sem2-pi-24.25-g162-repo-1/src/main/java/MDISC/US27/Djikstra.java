package MDISC.US27;

import MDISC.US13.*;

import java.util.*;

/**
 * Console-based UI for calculating the shortest path between stations,
 * allowing the user to select a start station, end station, and waypoints.
 * Reads station and connection data from a CSV file.
 * Displays the total distance and visualizes the route.
 */
public class Djikstra {
    private final Scanner sc = new Scanner(System.in);
    private final InputReader reader = new InputReader();
    private double[] dijkstraDistances;
    private int[] dijkstraPrevious;

    public void run() {
        boolean repeat;
        do {
            // === 1. Leitura do ficheiro CSV ===
            System.out.println("=== Metro Network Route Planner ===");
            String path = PathCheckerUI.requestPath();
            reader.readCSVNoUnderscore(path);
            System.out.println("Data loaded sucessfully.\n");

            // === 2. Construção da Rede de Estações ===
            List<Station> stations = reader.getStations();
            double[][] adjMatrix = buildAdjacencyMatrix(stations, reader.getLines());

            // === 3. Seleção de Estações ===
            System.out.println("=== Select the Stations ===");
            Station start = getStation("Starter:", stations);
            Station end = getStation("Ender:", stations);
            List<Station> waypoints = getWaypoints(stations, start, end);

            // === 4. Cálculo do Caminho Mais Curto ===
            System.out.println("Calculating the shortest path...");
            List<Station> fullPath = findPath(stations, start, end, waypoints, adjMatrix);

            // === 5. Resultados ===
            System.out.println("\n=== Route Summary ===");
            double totalDistance = distanceCalc(fullPath, reader.getLines());
            System.out.printf("Total route distance: %.2f km%n", totalDistance);
            showPath(fullPath, reader.getLines());

            // === 6. Visualização Gráfica ===
            GraphUI.Graph(stations, reader.getLines(), fullPath);

            // === 7. Perguntar se o utilizador quer repetir ===
            System.out.print("\nDo you want to calculate another route? (yes/no): ");
            String response = sc.nextLine().trim().toLowerCase();
            repeat = response.equals("yes") || response.equals("y");
        } while (repeat);

        System.out.println("Exiting Route Planner. Goodbye!");
    }

    private double[][] buildAdjacencyMatrix(List<Station> stations, List<Line> lines) {
        int n = stations.size();
        double[][] matrix = new double[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(matrix[i], Double.POSITIVE_INFINITY);
            matrix[i][i] = 0.0;
        }

        for (Line line : lines) {
            int i = findStationIndex(stations, line.getStationA());
            int j = findStationIndex(stations, line.getStationB());
            if (i != -1 && j != -1) {
                matrix[i][j] = line.getDistance();
                matrix[j][i] = line.getDistance();
            }
        }

        return matrix;
    }

    private Station getStation(String prompt, List<Station> stations) {
        showStations(stations);
        System.out.println(prompt);
        int idx;

        while (true) {
            System.out.print("Please enter the station number: ");
            if (sc.hasNextInt()) {
                idx = sc.nextInt() - 1;
                if (idx >= 0 && idx < stations.size()) break;
            } else {
                sc.next();
            }
            System.out.println("Invalid selection. Please try again.");
        }

        sc.nextLine();
        return stations.get(idx);
    }

    private List<Station> getWaypoints(List<Station> stations, Station start, Station end) {
        List<Station> waypoints = new ArrayList<>();

        System.out.print("Enter the number of waypoints (0 for none): ");
        int n;
        while (true) {
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                if (n >= 0) break;
            } else {
                sc.next();
            }
            System.out.print("Please enter a valid non-negative number: ");
        }
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            Station wp;
            do {
                System.out.printf("Select waypoint %d:\n", i + 1);
                wp = getStation(">> Choose a waypoint from the list above:", stations);
                if (wp.equals(start) || wp.equals(end)) {
                    System.out.println("Waypoint cannot be the start or end station. Please choose another.");
                } else if (waypoints.contains(wp)) {
                    System.out.println("You have already selected this waypoint. Please choose a different one.");
                }
            } while (wp.equals(start) || wp.equals(end) || waypoints.contains(wp));
            waypoints.add(wp);
        }

        return waypoints;
    }

    private List<Station> dijkstraAlgorithm(List<Station> stations, Station start, Station end, double[][] adjMatrix) {
        int n = stations.size();
        dijkstraDistances = new double[n];
        dijkstraPrevious = new int[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            dijkstraDistances[i] = Double.POSITIVE_INFINITY;
            dijkstraPrevious[i] = -1;
            visited[i] = false;
        }

        int startIdx = findStationIndex(stations, start);
        dijkstraDistances[startIdx] = 0;

        for (int count = 0; count < n; count++) {
            int u = -1;
            double minDist = Double.POSITIVE_INFINITY;

            for (int i = 0; i < n; i++) {
                if (!visited[i] && dijkstraDistances[i] < minDist) {
                    minDist = dijkstraDistances[i];
                    u = i;
                }
            }

            if (u == -1) break;
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && adjMatrix[u][v] != Double.POSITIVE_INFINITY) {
                    double alt = dijkstraDistances[u] + adjMatrix[u][v];
                    if (alt < dijkstraDistances[v]) {
                        dijkstraDistances[v] = alt;
                        dijkstraPrevious[v] = u;
                    }
                }
            }
        }

        List<Station> path = new ArrayList<>();
        int at = findStationIndex(stations, end);
        if (dijkstraDistances[at] == Double.POSITIVE_INFINITY) return path;

        while (at != -1) {
            path.add(0, stations.get(at));
            at = dijkstraPrevious[at];
        }

        if (!path.isEmpty() && path.get(0).equals(start)) return path;
        return new ArrayList<>();
    }
    private List<Station> findPath(List<Station> stations, Station start, Station end, List<Station> waypoints, double[][] adjMatrix) {
        List<Station> fullPath = new ArrayList<>();
        Station curr = start;
        List<Station> targets = new ArrayList<>(waypoints);
        targets.add(end);

        for (Station next : targets) {
            List<Station> segment = dijkstraAlgorithm(stations, curr, next, adjMatrix);
            if (segment.isEmpty()) return new ArrayList<>();
            if (!fullPath.isEmpty()) segment.remove(0); // Avoid duplicating nodes
            fullPath.addAll(segment);
            curr = next;
        }

        return fullPath;
    }
    private double distanceCalc(List<Station> path, List<Line> lines) {
        double total = 0.0;
        for (int i = 0; i < path.size() - 1; i++) {
            Station a = path.get(i);
            Station b = path.get(i + 1);
            for (Line line : lines) {
                if ((line.getStationA().equals(a) && line.getStationB().equals(b)) ||
                        (line.getStationA().equals(b) && line.getStationB().equals(a))) {
                    total += line.getDistance();
                    break;
                }
            }
        }
        return total;
    }
    private int findStationIndex(List<Station> stations, Station target) {
        for (int i = 0; i < stations.size(); i++) {
            if (stations.get(i).equals(target)) return i;
        }
        return -1;
    }
    public void showStations(List<Station> stations) {
        System.out.println("\nAvailable Stations:");
        System.out.println("===== No. | Station =====");
        for (int i = 0; i < stations.size(); i++) {
            System.out.println(String.format("  [%2d]  %s", i + 1, stations.get(i).getName()));
        }
    }

    private void showPath(List<Station> path, List<Line> lines) {
        System.out.println("\nRoute Overview:");
        double distanceSoFar = 0.0;

        for (int i = 0; i < path.size(); i++) {
            if (i > 0) {
                distanceSoFar += calcDistanceBetweenStations(path.get(i - 1), path.get(i), lines);
            }
            System.out.println(String.format("%d) %s  - Distance traveled: %.2f km", i + 1, path.get(i).getName(), distanceSoFar));
        }

        System.out.println(String.format("\nTotal distance traveled: %.2f km", distanceSoFar));
    }

    private double calcDistanceBetweenStations(Station a, Station b, List<Line> lines) {
        for (Line conn : lines) {
            if ((conn.getStationA().equals(a) && conn.getStationB().equals(b)) ||
                    (conn.getStationA().equals(b) && conn.getStationB().equals(a))) {
                return conn.getDistance();
            }
        }
        return 0.0;
    }
}