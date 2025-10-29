package org.example.structures.graph.pert;

import org.example.structures.graph.CommonGraph;
import org.example.structures.graph.Edge;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;

/**
 * The type Pert graph.
 *
 * @param <V> the type parameter
 * @param <E> the type parameter
 */
public class PertGraph<V, E> extends CommonGraph<V, E> {


    private Map<V, PertVertex<V, E>> pertVertices;

    /**
     * Instantiates a new Pert graph.
     *
     * @param directed the directed
     */
    public PertGraph(boolean directed) {
        super(directed);
        pertVertices = new LinkedHashMap<>();
    }

    /**
     * Gets pert vertices.
     *
     * @return the pert vertices
     */
    public Map<V, PertVertex<V, E>> getPertVertices() {
        return pertVertices;
    }

    @Override
    public boolean validVertex(V vert) {
        return (pertVertices.get(vert) != null);
    }

    @Override
    public Collection<V> adjVertices(V vert) {

        if (!validVertex(vert)) return null;

        PertVertex<V, E> mv = pertVertices.get(vert);

        return mv.getAdjVertices();
    }

    @Override
    public Collection<Edge<V, E>> edges() {

        ArrayList<Edge<V, E>> le = new ArrayList<>(numEdges);

        for (PertVertex<V, E> mv : pertVertices.values())
            le.addAll(mv.getAllOutEdges());

        return le;
    }

    @Override
    public Edge<V, E> edge(V vOrig, V vDest) {

        if (!validVertex(vOrig) || !validVertex(vDest)) return null;

        PertVertex<V, E> mv = pertVertices.get(vOrig);

        return mv.getEdge(vDest);
    }

    @Override
    public Edge<V, E> edge(int vOrigKey, int vDestKey) {

        V vOrig = vertex(vOrigKey);
        V vDest = vertex(vDestKey);

        return edge(vOrig, vDest);
    }

    @Override
    public int outDegree(V vert) {

        if (!validVertex(vert)) return -1;

        PertVertex<V, E> pv = pertVertices.get(vert);

        return pv.numAdjVertices();
    }

    @Override
    public int inDegree(V vert) {

        if (!validVertex(vert)) return -1;

        int degree = 0;

        for (V otherVert : pertVertices.keySet()) {
            if (edge(otherVert, vert) != null) degree++;
        }
        return degree;
    }

    @Override
    public Collection<Edge<V, E>> outgoingEdges(V vert) {

        if (!validVertex(vert)) return null;

        PertVertex<V, E> mv = pertVertices.get(vert);

        return mv.getAllOutEdges();
    }

    @Override
    public Collection<Edge<V, E>> incomingEdges(V vert) {

        if (!validVertex(vert)) return null;

        ArrayList<Edge<V, E>> edges = new ArrayList<>();

        for (V otherVert : pertVertices.keySet()) {
            Edge<V, E> edge = edge(otherVert, vert);
            if (edge != null) edges.add(edge);
        }
        return edges;
    }

    @Override
    public boolean addVertex(V vert) {

        if (vert == null) throw new RuntimeException("Vertices cannot be null!");

        if (validVertex(vert)) return false;

        PertVertex<V, E> mv = new PertVertex<>(vert);

        vertices.add(vert);
        pertVertices.put(vert, mv);
        numVerts++;

        return true;
    }

    @Override
    public boolean addEdge(V vOrig, V vDest, E weight) {

        if (vOrig == null || vDest == null) throw new RuntimeException("Vertices cannot be null!");

        if (edge(vOrig, vDest) != null) return false;

        if (!validVertex(vOrig)) addVertex(vOrig);

        if (!validVertex(vDest)) addVertex(vDest);

        PertVertex<V, E> mvo = pertVertices.get(vOrig);
        PertVertex<V, E> mvd = pertVertices.get(vDest);

        Edge<V, E> newEdge = new Edge<>(mvo.getElement(), mvd.getElement(), weight);
        mvo.addAdjVert(mvd.getElement(), newEdge);
        numEdges++;

        //if graph is not direct insert other edge in the opposite direction
        if (!isDirected)
            // if vDest different vOrig
            if (edge(vDest, vOrig) == null) {
                Edge<V, E> otherEdge = new Edge<>(mvd.getElement(), mvo.getElement(), weight);
                mvd.addAdjVert(mvo.getElement(), otherEdge);
                numEdges++;
            }

        return true;
    }

    @Override
    public boolean removeVertex(V vert) {

        if (vert == null) throw new RuntimeException("Vertices cannot be null!");

        if (!validVertex(vert)) return false;

        //remove all edges that point to vert
        for (Edge<V, E> edge : incomingEdges(vert)) {
            removeEdge(edge.getVOrig(), vert);
        }

        PertVertex<V, E> mv = pertVertices.get(vert);

        //The edges that live from vert are removed with the vertex
        numEdges -= mv.numAdjVertices();
        pertVertices.remove(vert);
        vertices.remove(vert);

        numVerts--;

        return true;
    }

    @Override
    public boolean removeEdge(V vOrig, V vDest) {

        if (vOrig == null || vDest == null) throw new RuntimeException("Vertices cannot be null!");

        if (!validVertex(vOrig) || !validVertex(vDest)) return false;

        Edge<V, E> edge = edge(vOrig, vDest);

        if (edge == null) return false;

        PertVertex<V, E> mvo = pertVertices.get(vOrig);

        mvo.remAdjVert(vDest);
        numEdges--;

        //if graph is not directed
        if (!isDirected) {
            edge = edge(vDest, vOrig);
            if (edge != null) {
                PertVertex<V, E> mvd = pertVertices.get(vDest);
                mvd.remAdjVert(vOrig);
                numEdges--;
            }
        }
        return true;
    }

    @Override
    public PertGraph<V, E> clone() {
        PertGraph<V, E> g = new PertGraph<>(this.isDirected);
        copy(this, g);
        return g;
    }

    @Override
    public String toString() {

        String s;

        if (numVerts == 0) {
            s = "\nGraph not defined!!";
        } else {

            s = "Number of vertices: " + numVerts + "\n";

            for (PertVertex<V, E> v : pertVertices.values()) {
                s += v.toString() + "\n";
            }
        }
        return s;
    }

    public PertVertex<V, E> getVertex(V element) {
        return pertVertices.get(element);
    }

    /**
     * Detects if the graph contains a circular dependency.
     *
     * @return true if a circular dependency is found, false otherwise
     */
    public boolean hasCircularDependency() {
        Map<V, String> colors = new HashMap<>(); // Mapeia o estado dos vértices: "WHITE", "GRAY", "BLACK"

        // Inicializa todos os vértices como "WHITE"
        for (V vertex : pertVertices.keySet()) {
            colors.put(vertex, "WHITE");
        }

        // Realiza DFS para detectar ciclos
        for (V vertex : pertVertices.keySet()) {
            if (colors.get(vertex).equals("WHITE")) {
                if (dfsCycleDetection(vertex, colors)) {
                    return true; // Ciclo detectado
                }
            }
        }
        return false; // Nenhum ciclo encontrado
    }

    private boolean dfsCycleDetection(V vertex, Map<V, String> colors) {
        // Marca o vértice atual como "GRAY" (em processamento)
        colors.put(vertex, "GRAY");

        // Obtém o vértice correspondente no mapa de `PertVertex`
        PertVertex<V, E> currentVertex = pertVertices.get(vertex);

        // Itera pelos vértices adjacentes
        for (V adjacent : currentVertex.getAdjVertices()) {
            if (colors.get(adjacent).equals("GRAY")) {
                return true; // Ciclo detectado
            } else if (colors.get(adjacent).equals("WHITE")) {
                if (dfsCycleDetection(adjacent, colors)) {
                    return true;
                }
            }
        }

        // Após visitar todos os adjacentes, marca o vértice como "BLACK"
        colors.put(vertex, "BLACK");
        return false;
    }





        public static <V, E> void exportPertGraphToCSV(PertGraph<V, E> pertGraph) {
            String filePath = "pertgraph_export.csv"; // Default file path

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                // Write the header line
                writer.write("act_id,desc,duration,cost,es,ls,ef,lf,prev_act_id1,...,prev_act_idN\n");

                // Iterate through all vertices in the graph
                for (Map.Entry<V, PertVertex<V, E>> entry : pertGraph.getPertVertices().entrySet()) {
                    PertVertex<V, E> vertex = entry.getValue();
                    writer.write(entry.getKey().toString());

                    for (Edge<V, E> entry1 : vertex.getAllOutEdges()){
                            PertVertex<?, ?> v = (PertVertex<?, ?>) entry1.getVDest();
                            writer.write(v.getElement() + ",");
                    }
                    writer.newLine();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }




}