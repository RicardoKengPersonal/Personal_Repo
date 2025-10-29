package org.example.structures.graph.pert;

import org.example.structures.graph.Edge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The type Pert vertex.
 *
 * @param <V> the type parameter
 * @param <E> the type parameter
 */
public class PertVertex<V, E> {

    private final V element;

    private final Map<V, Edge<V, E>> pertOutVertices;

    private final String description;

    private final Integer cost;

    private final Integer duration;

    private Integer earliestStartTime;

    private Integer earliestFinishTime;

    private Integer latestStartTime;

    private Integer latestFinishTime;

    private Integer slack;

    /**
     * Instantiates a new Pert vertex.
     *
     * @param element     the element
     * @param description the description
     * @param outVertices the out vertices
     * @param cost        the cost
     * @param duration    the duration
     */
    public PertVertex(V element, String description, Map<V, Edge<V, E>> outVertices, Integer cost, Integer duration) {
        this.element = element;
        this.description = description;
        this.cost = cost;
        this.duration = duration;
        this.pertOutVertices = outVertices;
        this.earliestStartTime = 0;
        this.earliestFinishTime = 0;
        this.latestStartTime = 0;
        this.latestFinishTime = 0;
    }

    /**
     * Instantiates a new Pert vertex.
     *
     * @param vert the vert
     */
    public PertVertex(V vert) {

        if (vert == null) throw new RuntimeException("Vertex information cannot be null!");

        element = vert;
        pertOutVertices = new LinkedHashMap<>();
        description = "";
        cost = 0;
        duration = 0;
        earliestStartTime = 0;
        earliestFinishTime = 0;
        latestStartTime = 0;
        latestFinishTime = 0;
    }

    /**
     * Adj vertices collection.
     *
     * @param vertex the vertex
     * @return the collection
     */
    public Collection<PertVertex<String, Integer>> adjVertices(PertVertex<String, Integer> vertex) {
        Collection<PertVertex<String, Integer>> adjVertices = new ArrayList<>();
        for (String key : vertex.getPertOutVertices().keySet()) {
            adjVertices.add(new PertVertex<>(key));
        }
        return adjVertices;
    }

    /**
     * Gets element.
     *
     * @return the element
     */
    public V getElement() {
        return element;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets cost.
     *
     * @return the cost
     */
    public Integer getCost() {
        return cost;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * Gets earliest finish time.
     *
     * @return the earliest finish time
     */
    public Integer getEarliestFinishTime() {
        return earliestFinishTime;
    }

    /**
     * Gets earliest start time.
     *
     * @return the earliest start time
     */
    public Integer getEarliestStartTime() {
        return earliestStartTime;
    }

    /**
     * Gets latest finish time.
     *
     * @return the latest finish time
     */
    public Integer getLatestFinishTime() {
        return latestFinishTime;
    }

    /**
     * Gets latest start time.
     *
     * @return the latest start time
     */
    public Integer getLatestStartTime() {
        return latestStartTime;
    }

    /**
     * Sets earliest finish time.
     *
     * @param earliestFinishTime the earliest finish time
     */
    public void setEarliestFinishTime(Integer earliestFinishTime) {
        this.earliestFinishTime = earliestFinishTime;
    }

    /**
     * Sets earliest start time.
     *
     * @param earliestStartTime the earliest start time
     */
    public void setEarliestStartTime(Integer earliestStartTime) {
        this.earliestStartTime = earliestStartTime;
    }

    /**
     * Sets latest finish time.
     *
     * @param latestFinishTime the latest finish time
     */
    public void setLatestFinishTime(Integer latestFinishTime) {
        this.latestFinishTime = latestFinishTime;
    }

    /**
     * Sets latest start time.
     *
     * @param latestStartTime the latest start time
     */
    public void setLatestStartTime(Integer latestStartTime) {
        this.latestStartTime = latestStartTime;
    }

    /**
     * Sets slack.
     *
     * @param slack the slack
     */
    public void setSlack(Integer slack) {
        this.slack = slack;
    }

    /**
     * Gets slack.
     *
     * @return the slack
     */
    public Integer getSlack() {
        return slack;
    }

    /**
     * Gets pert out vertices.
     *
     * @return the pert out vertices
     */
    public Map<V, Edge<V, E>> getPertOutVertices() {
        return pertOutVertices;
    }

    /**
     * Add adj vert.
     *
     * @param vAdj the v adj
     * @param edge the edge
     */
    public void addAdjVert(V vAdj, Edge<V, E> edge) {
        pertOutVertices.put(vAdj, edge);
    }

    /**
     * Adj vertices collection.
     *
     * @return the collection
     */
    public Collection<PertVertex<V, E>> adjVertices() {
        // Retorna os vértices adjacentes do vértice atual
        Collection<PertVertex<V, E>> adjVertices = new ArrayList<>();
        for (V key : pertOutVertices.keySet()) {
            // Para cada chave (vértice adjacente), cria o vértice com a aresta associada
            adjVertices.add(new PertVertex<>(key, "", new LinkedHashMap<>(), 0, 0));
        }
        return adjVertices;
    }

    /**
     * Rem adj vert.
     *
     * @param vAdj the v adj
     */
    public void remAdjVert(V vAdj) {
        pertOutVertices.remove(vAdj);
    }

    /**
     * Gets edge.
     *
     * @param vAdj the v adj
     * @return the edge
     */
    public Edge<V, E> getEdge(V vAdj) {
        return pertOutVertices.get(vAdj);
    }

    /**
     * Num adj vertices int.
     *
     * @return the int
     */
    public int numAdjVertices() {
        return pertOutVertices.size();
    }

    /**
     * Gets all adj vertices.
     *
     * @return the all adj vertices
     */
    public Collection<V> getAllAdjVertices() {
        return new ArrayList<>(pertOutVertices.keySet());
    }

    /**
     * Gets all out edges.
     *
     * @return the all out edges
     */
    public Collection<Edge<V, E>> getAllOutEdges() {
        return new ArrayList<>(pertOutVertices.values());
    }

    /**
     * Gets adj vertices.
     *
     * @return the adj vertices
     */
    public Collection<V> getAdjVertices() {
        return new ArrayList<>(pertOutVertices.keySet());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PertVertex = ");
        sb.append("Element: ").append(String.format("%-5s", element));
        sb.append("  Out Vertices: ").append(String.format("%-10s", pertOutVertices.keySet()));
        sb.append("  Duration: ").append(String.format("%-5s", duration));
        sb.append("  Cost: ").append(String.format("%-5s", cost));
        sb.append(" | ES: ").append(String.format("%-5s", earliestStartTime));
        sb.append("  EF: ").append(String.format("%-5s", earliestFinishTime));
        sb.append("  LS: ").append(String.format("%-5s", latestStartTime));
        sb.append("  LF: ").append(String.format("%-5s", latestFinishTime));
        return sb.toString();
    }

}
