package pt.ipp.isep.dei.esoft.project.mdisc.sprintC;

import java.util.ArrayList;
import java.util.List;

public abstract class DijkstraAlgorithm {
    private static List<Route> edges;

    public static List<Route> findShortestPathToAnyEndPoint(Point origin, List<Point> endPoints, List<Route> edges) {
        DijkstraAlgorithm.edges = edges;

        List<Point> points = new ArrayList<>();
        for (Route route : edges) {
            addUniquePoint(points, route.getOrigin());
            addUniquePoint(points, route.getDestination());
        }

        double[] costs = new double[points.size()];
        String[] previousNodes = new String[points.size()];
        boolean[] visitedNodes = new boolean[points.size()];

        // Initializing arrays with default values
        for (int i = 0; i < points.size(); i++) {
            costs[i] = Double.MAX_VALUE;
            previousNodes[i] = null;
            visitedNodes[i] = false;
        }

        int originIndex = getPointIndex(points, origin);
        costs[originIndex] = 0.0;

        List<NodeCost> nodeCosts = new ArrayList<>();
        nodeCosts.add(new NodeCost(origin, 0.0));

        while (!nodeCosts.isEmpty()) {
            NodeCost currentNodeCost = getMinimumDistancePoint(nodeCosts);
            Point currentPoint = currentNodeCost.point;
            int currentIndex = getPointIndex(points, currentPoint);

            if (visitedNodes[currentIndex]) continue;
            visitedNodes[currentIndex] = true;

            for (Route route : edges) {
                if (route.getOrigin().equals(currentPoint)) {
                    Point neighbor = route.getDestination();
                    int neighborIndex = getPointIndex(points, neighbor);
                    double newCost = costs[currentIndex] + route.getCost();

                    if (newCost < costs[neighborIndex]) {
                        costs[neighborIndex] = newCost;
                        previousNodes[neighborIndex] = currentPoint.getId();
                        nodeCosts.add(new NodeCost(neighbor, newCost));
                    }
                }
            }
        }

        Point closestEndPoint = null;
        double minCost = Double.MAX_VALUE;
        for (Point endPoint : endPoints) {
            int endIndex = getPointIndex(points, endPoint);
            if (costs[endIndex] < minCost) {
                minCost = costs[endIndex];
                closestEndPoint = endPoint;
            }
        }

        return buildPath(origin, closestEndPoint, points, previousNodes);
    }

    private static void addUniquePoint(List<Point> points, Point point) {
        for (Point p : points) {
            if (p.getId().equals(point.getId())) {
                return;
            }
        }
        points.add(point);
    }

    private static int getPointIndex(List<Point> points, Point point) {
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).getId().equals(point.getId())) {
                return i;
            }
        }
        return -1; // Should not happen if points list is properly built
    }

    private static NodeCost getMinimumDistancePoint(List<NodeCost> nodeCosts) {
        NodeCost minNodeCost = null;
        for (NodeCost nc : nodeCosts) {
            if (minNodeCost == null || nc.cost < minNodeCost.cost) {
                minNodeCost = nc;
            }
        }
        nodeCosts.remove(minNodeCost);
        return minNodeCost;
    }

    private static List<Route> buildPath(Point origin, Point destination, List<Point> points, String[] previousNodes) {
        List<Route> path = new ArrayList<>();
        Point currentPoint = destination;

        while (currentPoint != null && !currentPoint.getId().equals(origin.getId())) {
            int currentIndex = getPointIndex(points, currentPoint);
            String previousNodeId = previousNodes[currentIndex];
            if (previousNodeId == null) {
                return new ArrayList<>(); // No path found
            }

            Point previousNode = null;
            for (Point point : points) {
                if (point.getId().equals(previousNodeId)) {
                    previousNode = point;
                    break;
                }
            }

            Route route = getRoute(previousNode, currentPoint, edges);
            if (route != null) {
                path.add(0, route); // Add to the beginning of the list
            }

            currentPoint = previousNode;
        }

        return path;
    }

    private static Route getRoute(Point origin, Point destination, List<Route> edges) {
        for (Route route : edges) {
            if (route.getOrigin().equals(origin) && route.getDestination().equals(destination)) {
                return route;
            }
        }
        return null;
    }

    private static class NodeCost {
        Point point;
        double cost;

        NodeCost(Point point, double cost) {
            this.point = point;
            this.cost = cost;
        }
    }
}
