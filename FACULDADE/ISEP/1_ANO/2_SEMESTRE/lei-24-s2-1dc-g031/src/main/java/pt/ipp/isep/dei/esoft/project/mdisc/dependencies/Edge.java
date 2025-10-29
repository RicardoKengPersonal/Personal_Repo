/**
 * The Edge class represents a weighted edge in a graph.
 */
package pt.ipp.isep.dei.esoft.project.mdisc.dependencies;

public class Edge {

    private String from;
    private String to;
    private int weight;

    /**
     * Constructs a new Edge with the specified source, destination, and weight.
     *
     * @param from   The source vertex of the edge.
     * @param to     The destination vertex of the edge.
     * @param weight The weight of the edge.
     */
    public Edge(String from, String to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    /**
     * Retrieves the weight of the edge.
     *
     * @return The weight of the edge.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Retrieves the source vertex of the edge.
     *
     * @return The source vertex of the edge.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Retrieves the destination vertex of the edge.
     *
     * @return The destination vertex of the edge.
     */
    public String getTo() {
        return to;
    }
}
