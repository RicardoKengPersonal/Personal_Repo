package pt.ipp.isep.dei.esoft.project.mdisc.sprintC;

public class Route {
    private Point origin;
    private Point destination;
    private double cost;

    public Route(Point origin, Point destination, double cost) {
        this.origin = origin;
        this.destination = destination;
        this.cost = cost;
    }

    public Point getOrigin() {
        return origin;
    }

    public Point getDestination() {
        return destination;
    }

    public double getCost() {
        return cost;
    }
}
