package org.example.structures.graph.pert;

import org.example.structures.graph.Edge;

import java.util.*;

public class PertAlgorithms {

    public static void calculateEarliestStartAndFinish(PertGraph<PertVertex<String, Integer>, Integer> graph) {

        // topological sort of the vertices
        List<PertVertex<String, Integer>> topologicalOrder = topologicalSortKahn(graph);

        // initialize earliest start and finish
        for (PertVertex<String, Integer> vertex : topologicalOrder) {
            vertex.setEarliestStartTime(0);
            vertex.setEarliestFinishTime(vertex.getDuration());
        }

        for (PertVertex<String, Integer> vertex : topologicalOrder) {

            Integer earliestStartTime = vertex.getEarliestStartTime();
            Integer earliestFinishTime = vertex.getEarliestFinishTime();

            for (Edge<PertVertex<String, Integer>, Integer> edge : graph.outgoingEdges(vertex)) {

                PertVertex<String, Integer> adjVertex = edge.getVDest();

                Integer newEarliestStartTime = earliestFinishTime;
                Integer newEarliestFinishTime = newEarliestStartTime + adjVertex.getDuration();

                if (newEarliestStartTime > adjVertex.getEarliestStartTime()) {
                    adjVertex.setEarliestStartTime(newEarliestStartTime);
                    adjVertex.setEarliestFinishTime(newEarliestFinishTime);
                }
            }
        }
    }

    public static void calculateLatestStartAndFinish(PertGraph<PertVertex<String, Integer>, Integer> graph) {

        // Perform topological sort of the vertices
        List<PertVertex<String, Integer>> topologicalOrder = topologicalSortKahn(graph);
        Collections.reverse(topologicalOrder);

        // Initialize latest start and finish times
        for (PertVertex<String, Integer> vertex : topologicalOrder) {
            vertex.setLatestFinishTime(Integer.MAX_VALUE);
            vertex.setLatestStartTime(Integer.MAX_VALUE);
        }

        // Set the latest finish time of the end vertex
        PertVertex<String, Integer> endVertex = topologicalOrder.get(0);
        endVertex.setLatestFinishTime(endVertex.getEarliestFinishTime());

        // Calculate latest start and finish times
        for (PertVertex<String, Integer> vertex : topologicalOrder) {

            int latestFinishTime = vertex.getLatestFinishTime();
            int latestStartTime = latestFinishTime - vertex.getDuration();

            vertex.setLatestStartTime(latestStartTime);

            for (Edge<PertVertex<String, Integer>, Integer> edge : graph.incomingEdges(vertex)) {

                PertVertex<String, Integer> adjVertex = edge.getVOrig();

                int newLatestFinishTime = latestStartTime;

                if (newLatestFinishTime < adjVertex.getLatestFinishTime()) {
                    adjVertex.setLatestFinishTime(newLatestFinishTime);
                    adjVertex.setLatestStartTime(newLatestFinishTime - adjVertex.getDuration());
                }
            }
        }
    }

    public void calculateSlack(PertGraph<PertVertex<String, Integer>, Integer> graph) {
        for (PertVertex<String, Integer> vertex : graph.vertices()) {
            int slack = vertex.getLatestStartTime() - vertex.getEarliestStartTime();
            vertex.setSlack(slack);
        }
    }

    public void criticalPath(PertGraph<PertVertex<String, Integer>, Integer> graph) {



        List<PertVertex<String, Integer>> criticalPath = new ArrayList<>();
        int totalProjectDuration = 0;

        // Identify the critical path
        for (PertVertex<String, Integer> vertex : graph.vertices()) {
            // The critical path consists of vertices with zero slack
            if (vertex.getSlack() == 0) {
                criticalPath.add(vertex);
            }
        }

        // Calculate the total project duration by finding the maximum finish time
        for (PertVertex<String, Integer> vertex : criticalPath) {
            totalProjectDuration = Math.max(totalProjectDuration, vertex.getEarliestFinishTime());
        }

        // Display the critical path and its key metrics (earliest and latest times)
        System.out.println("Critical Path: ");
        for (PertVertex<String, Integer> vertex : criticalPath) {
            System.out.println("Activity: " + vertex.getElement() +
                    " | Earliest Start: " + vertex.getEarliestStartTime() +
                    " | Earliest Finish: " + vertex.getEarliestFinishTime() +
                    " | Latest Start: " + vertex.getLatestStartTime() +
                    " | Latest Finish: " + vertex.getLatestFinishTime() +
                    " | Slack: " + vertex.getSlack());
        }

        // Output the total project duration
        System.out.println("Total Project Duration: " + totalProjectDuration + " units");
    }

    /*------------------------------------------USEI19------------------------------------------*/

    /**
     * Performs topological sorting using Kahn's Algorithm(iterative approach).
     *
     * @param graph PertGraph instance to be sorted.
     * @return sortedOrder - List of vertices in topological order.
     */
    public static List<PertVertex<String, Integer>> topologicalSortKahn(PertGraph<PertVertex<String, Integer>, Integer> graph) {
        // Initialize the input degree of all vertices
        Map<PertVertex<String, Integer>, Integer> inDegree = new HashMap<>();

        // Starts to process all vertices with the input degree of 0
        for (PertVertex<String, Integer> vertex : graph.vertices()) {
            inDegree.put(vertex, 0);
        }

        // Calculate the input degree of all vertices
        for (PertVertex<String, Integer> vertex : graph.vertices()) {
            for (PertVertex<String, Integer> adj : graph.adjVertices(vertex)) {
                inDegree.put(adj, inDegree.get(adj) + 1);
            }
        }

        // Queue to store vertices with input degree of 0(vertices with no incoming edges)
        Queue<PertVertex<String, Integer>> queue = new LinkedList<>();

        // Add vertices with in-degree of 0 to the queue
        for (Map.Entry<PertVertex<String, Integer>, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        // List to store the topological order and a counter to keep track of visited vertices
        List<PertVertex<String, Integer>> sortedOrder = new ArrayList<>();
        int verticesVisited = 0;

        // Process vertices in the queue
        while (!queue.isEmpty()) {
            // Get the vertex with in-degree of 0 and remove it from the queue
            PertVertex<String, Integer> current = queue.poll();

            // Add the vertex to the topological order list and increment the counter
            sortedOrder.add(current);
            verticesVisited++;

            // Process all adjacent vertices
            for (PertVertex<String, Integer> adj : graph.adjVertices(current)) {
                // Decrease in-degree of adjacent vertex
                inDegree.put(adj, inDegree.get(adj) - 1);
                // If the in-degree of the adjacent vertex is 0, add it to the queue
                if (inDegree.get(adj) == 0) {
                    queue.add(adj);
                }
            }
        }

        // Check for cycles (if not all vertices are visited, there is a cycle)
        if (verticesVisited < graph.numVertices()) {
            throw new RuntimeException("Graph contains a cycle.");
        }

        return sortedOrder;
    }

    /*------------------------------------------USEI23------------------------------------------*/

    /**
     * Calculates the dependent counts of all vertices in the graph, to identify the bottleneck vertices.
     *
     * @param graph PertGraph instance to calculate dependent counts.
     * @return dependentCounts - Map with vertices as keys and their dependent counts as values.
     */
    public static Map<PertVertex<String, Integer>, Integer> calculateDependentCounts(PertGraph<PertVertex<String, Integer>, Integer> graph) {
        Map<PertVertex<String, Integer>, Integer> dependentCounts = new HashMap<>();

        for (PertVertex<String, Integer> vertex : graph.vertices()) {
            dependentCounts.put(vertex, vertex.numAdjVertices());
        }
        return dependentCounts;
    }

}
