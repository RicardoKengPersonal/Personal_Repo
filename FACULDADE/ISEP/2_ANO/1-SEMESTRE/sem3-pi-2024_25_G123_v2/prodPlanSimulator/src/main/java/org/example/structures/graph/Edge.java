package org.example.structures.graph;

import org.example.structures.graph.pert.PertVertex;

import java.util.Objects;

public class Edge<V, E> {

    final private V vOrig;        // vertex origin

    final private V vDest;        // vertex destination

    private E weight;             // Edge weight

    public Edge(V vOrig, V vDest, E weight) {
        if ((vOrig == null) || (vDest == null)) throw new RuntimeException("Edge vertices cannot be null!");
        this.vOrig = vOrig;
        this.vDest = vDest;
        this.weight = weight;
    }

    public V getVOrig() {
        return vOrig;
    }

    public V getVDest() {
        return vDest;
    }

    public E getWeight() {
        return weight;
    }

    public void setWeight(E weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Edge{");
        if (vOrig instanceof PertVertex<?, ?>){
            PertVertex<?, ?> v = (PertVertex<?, ?>) vOrig;
            sb.append(", vOrig=").append(v.getElement());
        } else sb.append(", vOrig=").append(vOrig);

        if (vDest instanceof PertVertex<?, ?>){
            PertVertex<?, ?> v = (PertVertex<?, ?>) vDest;
            sb.append(", vDest=").append(v.getElement());
        } else sb.append(", vDest=").append(vDest);

        //sb.append("vDest=").append(vDest);
        sb.append(", weight=").append(weight);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        @SuppressWarnings("unchecked") Edge<V, E> edge = (Edge<V, E>) o;
        return vOrig.equals(edge.vOrig) &&
                vDest.equals(edge.vDest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vOrig, vDest);
    }
}
