package MDISC.US14;

import MDISC.Utils;
import MDISC.US13.InputReader;
import MDISC.US13.Station;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Maintenance {

    public static List<String> execute(RailroadNetwork network, boolean onlyElectrified, InputReader dataReader) {
        int[][] graph = network.getAdjacencyMatrix(onlyElectrified);
        int n = graph.length;
        int[] degree = calculateVertexDegrees(graph, n);
        List<String> path = new ArrayList<>();

        // check graph connection
        if (!isGraphConnected(graph)) {
            return path;
        }

        // 1 -> finds the vertices with odd degrees.
        List<Integer> oddVertices = findOddDegreeVertices(degree);
        // 2 -> if any vertices with odd degree different from 2 or 0 the algorithm ends
        if (!checkEulerian(oddVertices)) {
            return path;
        }

        // 3 -> if exactly two vertices have an odd degree, pick one of them to start the traversal.
        int startVertex = chooseInitialPoint(oddVertices, degree, dataReader.getAdjacencyStations(dataReader.getStations(), onlyElectrified));
        List<Integer>[] adjacencyList = buildAdjListFromMatrix(graph);

        // Run Fleury's algorithm to find the maintenance route
        System.out.println("##### Maintenance Route #####");
        fleuryAlgorithm(adjacencyList, startVertex, dataReader.getAdjacencyStations(dataReader.getStations(), onlyElectrified), path);
        visualizePath(path);
        return path;
    }


    // step  1 - For a graph to be Eulerian or Semi-Eulerian, it must be connected.
    private static boolean isGraphConnected(int[][] graph) {
        int n = graph.length;

        // 1 if there's a connection, 0 otherwise
        int[][] simplifiedGraph = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
              if (graph[i][j] > 0) {
                  simplifiedGraph[i][j] = 1;
              } else {
                  simplifiedGraph[i][j] = 0;
              }

            }
        }

        if (!Utils.isGraphConnected(simplifiedGraph)) {
            System.out.println("Graph connectivity check failed. The network is disconnected.");
            return false;
        }

        System.out.println("Connectivity confirmed: the graph is connected.");
        System.out.println("\n");
        return true;
    }

    // step 2 - Fleury's Algorithm: Determine the degree of all vertices.
    private static int[] calculateVertexDegrees(int[][] graph, int n) {
        int[] degree = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                degree[i] += graph[i][j];
            }
        }
        return degree;
    }


   // Step 3 - if it has the a vertice with an odd degree different from 2 or 0 the algorithm stops and returns

    // Find vertices with odd degree
    private static List<Integer> findOddDegreeVertices(int[] degree) {
        List<Integer> oddVertices = new ArrayList<>();
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] % 2 != 0) {
                oddVertices.add(i);
            }
        }
        return oddVertices;
    }

    // check if the graph is Eulerian or Semi-Eulerian
    private static boolean checkEulerian(List<Integer> oddVertices) {
        if (oddVertices.size() != 0 && oddVertices.size() != 2) {
            System.out.println("This graph does not satisfy Eulerian or Semi-Eulerian conditions. Path generation aborted.");
            return false;
        }
        return true;
    }

    // Step 4 - Choose the starting vertex based on odd degree vertices
    private static int chooseInitialPoint(List<Integer> oddVertices, int[] degree, List<Station> stations) {
        List<Integer> startOptions = new ArrayList<>();
        if (oddVertices.size() == 2) {
            startOptions.addAll(oddVertices);
            System.out.println("Semi-Eulerian graph detected. Please pick one of the two available starting points:");
        } else if (oddVertices.size() == 0) {
            System.out.println("Eulerian graph identified. Select a starting station from the list below:\n");

            for (int i = 0; i < degree.length; i++) {
                if (degree[i] > 0) {
                    startOptions.add(i);
                }
            }
        }

        System.out.println("Select the starting point for the route:");
        for (int i = 0; i < startOptions.size(); i++) {
            int stationIndex = startOptions.get(i);
            System.out.println("[" + i + "] " + stations.get(stationIndex).getName());
        }

        Scanner scanner = new Scanner(System.in);
        int chosenIndex = -1;
        while (chosenIndex < 0 || chosenIndex >= startOptions.size()) {
            System.out.print("Enter the ID of the station: ");
            if (scanner.hasNextInt()) {
                chosenIndex = scanner.nextInt();
                if (chosenIndex < 0 || chosenIndex >= startOptions.size()) {
                    System.out.println("That option is not valid. Please try again.");
                }
            } else {
                System.out.println("Input error: a numeric value is expected.");
                scanner.next();
            }
        }
        return startOptions.get(chosenIndex);
    }

    // Step 5 - Build the adjacency list from the matrix
    private static List<Integer>[] buildAdjListFromMatrix(int[][] matrix) {
        int n = matrix.length;
        List<Integer>[] adjacencyList = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adjacencyList[i] = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] > 0) { // Se houver uma conexão
                    adjacencyList[i].add(j);
                }
            }
        }

        return adjacencyList;
    }

    // Run Algorithm to generate the path
    private static void fleuryAlgorithm(List<Integer>[] adj, int start, List<Station> stations, List<String> path) {
        System.out.println("Starting at: " + stations.get(start).getName());

        while (!adj[start].isEmpty()) {
            boolean moved = false;

            for (int i = 0; i < adj[start].size(); i++) {
                int next = adj[start].get(i);
                System.out.println("Checking if edge is valid: ");
                System.out.println(isNextEdgeValid(start, next, adj));
                if (isNextEdgeValid(start, next, adj)) {
                    String step = stations.get(start).getName() + " -> " + stations.get(next).getName();
                    System.out.println(step);
                    path.add(step);

                    removeEdge(start, next, adj);
                    start = next;
                    moved = true;
                    break;
                }
            }

            if (!moved) {
                System.out.println("No further valid edges. Terminating path construction.");
                break;
            }
        }
    }

    // Helper - Check if an edge is valid for Fleury’s algorithm
    private static boolean isNextEdgeValid(int u, int v, List<Integer>[] adj) {
        // If v is the only adjacent vertex of u
        if (adj[u].size() == 1) {
            return true;
        }
        // Temporarily remove the edge and check connectivity
        removeEdge(u, v, adj);
        boolean connected = isGraphStillConnected(adj);
        addEdge(u, v, adj);

        return connected;
    }

    // Helper - Check if graph remains connected after edge removal
    private static boolean isGraphStillConnected(List<Integer>[] adj) {
        List<Integer> activeNodes = new ArrayList<>();
        for (int i = 0; i < adj.length; i++) {
            if (!adj[i].isEmpty()) activeNodes.add(i);
        }

        int n = activeNodes.size();
        int[][] reducedMatrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            int origI = activeNodes.get(i);
            for (int j = 0; j < n; j++) {
                int origJ = activeNodes.get(j);
                if (adj[origI].contains(origJ)) {
                    reducedMatrix[i][j]++;
                }
            }
        }

        return Utils.isGraphConnected(reducedMatrix);
    }


    // Helper - Remove an edge from the adjacency list
    private static void removeEdge(int u, int v, List<Integer>[] adj) {
        adj[u].remove((Integer) v);
        adj[v].remove((Integer) u);
    }

    // Helper - Add an edge back to the adjacency list
    private static void addEdge(int u, int v, List<Integer>[] adj) {
        adj[u].add(v);
        adj[v].add(u);
    }

    // Final Step - Print the Eulerian path found
    private static void visualizePath(List<String> path) {
        System.out.println("   ##### Complete Maintenance Route ######  ");
        if (path.isEmpty()) {
            System.out.println("Could not compute a valid path for maintenance.");
            return;
        }

        for (int i = 0; i < path.size(); i++) {
            System.out.println((i+1) + ". " + path.get(i));
        }
    }
}
