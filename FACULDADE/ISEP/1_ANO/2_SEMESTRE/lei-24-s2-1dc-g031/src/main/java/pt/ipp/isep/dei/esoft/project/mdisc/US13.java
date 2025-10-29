package pt.ipp.isep.dei.esoft.project.mdisc;

import pt.ipp.isep.dei.esoft.project.mdisc.dependencies.Edge;

import java.io.*;
import java.util.*;

/**
 * The US13 class represents a collection of methods for processing graph data and running the Kruskal's algorithm.
 * This class provides methods to read input files, generate minimum spanning trees, and generate Graphviz images.
 */
public class US13 {

    /**
     * Reads a list of input file names from the specified file.
     *
     * @param filename The name of the file containing input file names.
     * @return A list of input file names.
     */
    public static List<String> readInputFiles(String filename) {
        List<String> fileNames = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileNames.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileNames;
    }

    /**
     * Reads a graph from a CSV file and constructs a list of edges.
     *
     * @param filename The name of the CSV file containing graph data.
     * @return A list of edges representing the graph.
     */
    public static List<Edge> readGraphFromCSV(String filename) {
        List<Edge> edges = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileReader("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/" + filename))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(";");
                String source = parts[0];
                String destination = parts[1];
                int weight = Integer.parseInt(parts[2]);
                edges.add(new Edge(source, destination, weight));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return edges;
    }

    /**
     * Runs the Kruskal's algorithm on the provided list of edges.
     *
     * @param edges The list of edges representing the graph.
     * @return A list of edges representing the minimum spanning tree.
     */
    public static List<Edge> runAlgorithm(List<Edge> edges) {
        List<Edge> mstEdges = new ArrayList<>();
        return calculateMinimumSpanningTree(edges, mstEdges);
    }

    /**
     * Calculates the minimum spanning tree using Kruskal's algorithm.
     *
     * @param edges    The list of edges representing the graph.
     * @param mstEdges An empty list to store the minimum spanning tree edges.
     * @return A list of edges representing the minimum spanning tree.
     */
    public static List<Edge> calculateMinimumSpanningTree(List<Edge> edges, List<Edge> mstEdges) {
        edges.sort(Comparator.comparingInt(Edge::getWeight));

        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> rank = new HashMap<>();
        Set<String> vertices = new HashSet<>();

        for (Edge edge : edges) {
            vertices.add(edge.getFrom());
            vertices.add(edge.getTo());
        }

        for (Edge edge : edges) {
            String rootX = find(parent, edge.getFrom());
            String rootY = find(parent, edge.getTo());

            // Check if adding this edge creates a cycle
            if (!rootX.equals(rootY)) {
                mstEdges.add(edge);

                // Union by rank to optimize the tree structure
                union(parent, rank, rootX, rootY);

                // If we have added enough edges, stop
                if (mstEdges.size() == vertices.size() - 1) {
                    break;
                }
            }
        }

        return mstEdges;
    }

    /**
     * Finds the root of the set containing the specified node.
     *
     * @param parent The parent map representing the disjoint-set forest.
     * @param node   The node whose root is to be found.
     * @return The root of the set containing the specified node.
     */
    private static String find(Map<String, String> parent, String node) {
        if (!parent.containsKey(node)) {
            parent.put(node, node);
        }
        while (!parent.get(node).equals(node)) {
            node = parent.get(node);
        }
        return node;
    }

    /**
     * Combines two sets represented by the specified roots.
     *
     * @param parent The parent map representing the disjoint-set forest.
     * @param rank   The rank map representing the ranks of the trees.
     * @param x      The root of the first set.
     * @param y      The root of the second set.
     */
    private static void union(Map<String, String> parent, Map<String, Integer> rank, String x, String y) {
        String rootX = find(parent, x);
        String rootY = find(parent, y);

        if (rootX.equals(rootY)) {
            return;
        }

        if (rank.getOrDefault(rootX, 0) < rank.getOrDefault(rootY, 0)) {
            parent.put(rootX, rootY);
        } else if (rank.getOrDefault(rootX, 0) > rank.getOrDefault(rootY, 0)) {
            parent.put(rootY, rootX);
        } else {
            parent.put(rootY, rootX);
            rank.put(rootX, rank.getOrDefault(rootX, 0) + 1);
        }
    }

    /**
     * Generates Graphviz images for the input and minimum spanning tree graphs.
     *
     * @param fileName The name of the input CSV file.
     * @param edges    The list of edges representing the graph.
     */
    public static void generateGraphvizImages(String fileName, List<Edge> edges) {
        fileName = fileName.replace(".csv", "");
        String dotFileNameInput = "src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/output/input_" + fileName + ".dot";
        try (PrintWriter writer = new PrintWriter(new FileWriter(dotFileNameInput))) {
            writer.println("graph G {");
            for (Edge edge : edges) {
                writer.println(edge.getFrom() + " -- " + edge.getTo() + " [label=" + edge.getWeight() + "];");
            }
            writer.println("}");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Generate MST graph
        List<Edge> mstEdges = runAlgorithm(edges);
        String dotFileNameOutput = "src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/output/mst_" + fileName + ".dot";
        try (PrintWriter writer = new PrintWriter(new FileWriter(dotFileNameOutput))) {
            writer.println("graph G {");
            for (Edge edge : mstEdges) {
                writer.println(edge.getFrom() + " -- " + edge.getTo() + " [label=" + edge.getWeight() + "];");
            }
            writer.println("}");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Generate images
        String inputImageFileName = "input_" + fileName + ".png";
        String mstImageFileName = "mst_" + fileName + ".png";

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("dot", "-Tpng", dotFileNameInput, "-o", "src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/output/" + inputImageFileName);
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new IOException("Graphviz process exited with non-zero status: " + exitCode);
            }

            processBuilder = new ProcessBuilder("dot", "-Tpng", dotFileNameOutput, "-o", "src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/output/" + mstImageFileName);
            process = processBuilder.start();
            exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new IOException("Graphviz process exited with non-zero status: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method to execute the operations on the input files.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file name: ");
        String fileName = scanner.nextLine();
        List<String> inputFiles = readInputFiles("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/files/" + fileName);

        for (String inputFile : inputFiles) {
            List<Edge> edges = readGraphFromCSV(inputFile);
            generateGraphvizImages(inputFile, edges);
        }
    }
}
