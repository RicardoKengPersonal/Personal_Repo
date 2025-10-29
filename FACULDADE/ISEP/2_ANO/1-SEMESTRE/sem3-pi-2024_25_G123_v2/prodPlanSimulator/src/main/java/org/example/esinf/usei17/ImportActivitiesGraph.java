package org.example.esinf.usei17;

import org.example.structures.graph.Edge;
import org.example.structures.graph.pert.PertGraph;
import org.example.structures.graph.pert.PertVertex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class ImportActivitiesGraph {

    public static PertGraph<PertVertex<String, Integer>, Integer> importPertCpmGraph(String file) {

        PertGraph<PertVertex<String, Integer>, Integer> graph = new PertGraph<>(true);

        // Add START Vertex
        PertVertex<String, Integer> startVertex = new PertVertex<>("START");
        graph.addVertex(startVertex);

        // Add END Vertex
        PertVertex<String, Integer> endVertex = new PertVertex<>("END");
        graph.addVertex(endVertex);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            br.readLine(); // Skip Header

            String line;

            Map<String, PertVertex<String, Integer>> vertices = new HashMap<>();

            // loop to add vertices
            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");

                String activityId = values[0];
                String desc = values[1];
                Integer duration = Integer.parseInt(values[2]);
                Integer cost = Integer.parseInt(values[4]);

                // Create Vertex
                PertVertex<String, Integer> pertVertex = new PertVertex<>(activityId, desc, new HashMap<>(), cost, duration);

                vertices.put(activityId, pertVertex);

                // Add Vertex
                graph.addVertex(pertVertex);
            }

            br.close();
            BufferedReader br2 = new BufferedReader(new FileReader(file));
            br2.readLine(); // Skip Header

            String line2;

            // loop to add Edges
            while ((line2 = br2.readLine()) != null) {

                String[] values = line2.split(",");

                String activityId = values[0];

                PertVertex<String, Integer> pertVertex = vertices.get(activityId);

                List<String> predecessors = new LinkedList<>();

                if (values.length > 5 && !values[5].isEmpty()) {

                    for (int i = 5; i < values.length; i++) {
                        String preds = values[i].replaceAll("\"", "");
                        predecessors.add(preds);
                    }
                }

                for (String dependency : predecessors) {

                    PertVertex<String, Integer> predVertex = vertices.get(dependency);

                    if (predVertex != null) {
                        graph.addEdge(predVertex, pertVertex, 1);
                        Edge<String, Integer> edge = new Edge<>(predVertex.getElement(), pertVertex.getElement(), 1);
                        predVertex.addAdjVert(pertVertex.getElement(), edge);
                    }
                }

                // vertices without predecessors connect to START
                if (predecessors.isEmpty()) {
                    Edge<String, Integer> edge = new Edge<>(startVertex.getElement(), pertVertex.getElement(), 1);
                    graph.addEdge(startVertex, pertVertex, 1);
                    startVertex.addAdjVert(pertVertex.getElement(), edge);
                }
            }

            // Add edges from vertices without successors to END
            for (PertVertex<String, Integer> vertex : graph.vertices()) {

                if (vertex.getElement().equals("END")) continue;

                if (graph.outgoingEdges(vertex).isEmpty()) {
                    Edge<String, Integer> edge = new Edge<>(vertex.getElement(), endVertex.getElement(), 1);
                    graph.addEdge(vertex, endVertex, 1);
                    vertex.addAdjVert(endVertex.getElement(), edge);
                }
            }

            br2.close();

            if (graph.hasCircularDependency()) {
                System.out.println("Graph has circular dependencies");
                return null;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return graph;
    }
}
