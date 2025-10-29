package MDISC;

import MDISC.US13.InputReader;
import MDISC.US13.Station;
import MDISC.US13.StationType;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static boolean isGraphConnected(int[][] adjacencyMatrix) {
        int numberOfNodes = adjacencyMatrix.length;
        if (numberOfNodes == 0) {
            return true; // Empty graph is "connected"
        }

        boolean[] visitedNodes = new boolean[numberOfNodes];
        depthFirstSearch(adjacencyMatrix, 0, visitedNodes);

        for (boolean visited : visitedNodes) {
            if (!visited) return false;
        }
        return true;
    }

    public static void depthFirstSearch(int[][] adjacencyMatrix, int currentNode, boolean[] visitedNodes) {
        visitedNodes[currentNode] = true;

        for (int neighbor = 0; neighbor < adjacencyMatrix.length; neighbor++) {
            if (adjacencyMatrix[currentNode][neighbor] == 1 && !visitedNodes[neighbor]) {
                depthFirstSearch(adjacencyMatrix, neighbor, visitedNodes);
            }
        }
    }

    public static boolean isGroupConnected(int[][] adjacencyMatrix, List<Station> adjacencyMatrixNodes, List<StationType> selectedType) {
        int n = adjacencyMatrix.length;

        List<Integer> requiredIndices = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (selectedType.contains(adjacencyMatrixNodes.get(i).getType())) {
                requiredIndices.add(i);
            }
        }

        if (requiredIndices.size() <= 1) {
            return true;
        }

        // DFS from the first station
        boolean[] visited = new boolean[n];
        dfs(adjacencyMatrix, requiredIndices.get(0), visited);

        // if not all visited not connected
        for (int idx : requiredIndices) {
            if (!visited[idx]) {
                return false;
            }
        }

        return true;
    }

    private static void dfs(int[][] matrix, int current, boolean[] visited) {
        visited[current] = true; // mark the current node as visited
        for (int i = 0; i < matrix.length; i++) {
            // if there is an edge and the node is not visited
            if (matrix[current][i] > 0 && !visited[i]) {
                dfs(matrix, i, visited);
            }
        }
    }

    public static List<Station> dfsRoute(int[][] adjacencyMatrix, List<Station> stations, Station current, Station end, boolean[] visited, InputReader dataReader) {
        int currentIndex = dataReader.getStationIndex(current, stations);
        int endIndex = dataReader.getStationIndex(end, stations);

        visited[currentIndex] = true;
        if (currentIndex == endIndex) {
            // end station
            List<Station> path = new ArrayList<>();
            path.add(current);
            return path;
        }

        for (int neighbor = 0; neighbor < adjacencyMatrix.length; neighbor++) {
            if (adjacencyMatrix[currentIndex][neighbor] == 1 && !visited[neighbor]) {
                List<Station> path = dfsRoute(adjacencyMatrix, stations, stations.get(neighbor), end, visited, dataReader);
                if (!path.isEmpty()) {
                    path.add(0, current); // add current to the front
                    return path;
                }
            }
        }
        // return empty list if no path found
        return new ArrayList<>();
    }
}