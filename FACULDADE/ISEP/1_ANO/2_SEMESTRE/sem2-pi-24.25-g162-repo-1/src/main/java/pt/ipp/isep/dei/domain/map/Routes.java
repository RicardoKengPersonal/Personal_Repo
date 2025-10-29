package pt.ipp.isep.dei.domain.map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import pt.ipp.isep.dei.domain.ClassType.CargoMode;
import pt.ipp.isep.dei.domain.ClassType.CargoType;
import pt.ipp.isep.dei.domain.concept.PointOfRoute;
import pt.ipp.isep.dei.domain.concept.Route;
import pt.ipp.isep.dei.domain.objects.RailwayLine;
import pt.ipp.isep.dei.domain.objects.Station;

public class Routes implements Serializable {
    private ArrayList<Route> routes;

    public Routes() {
        this.routes = new ArrayList<>();
    }

    public void createRoute(ArrayList<PointOfRoute> pointsOfRoute) {
        routes.add(new Route(pointsOfRoute));
    }

    public ArrayList<Route> getRoutes() {
        return new ArrayList<>(routes);
    }

    public void createRouteFromRailwayLines(ArrayList<RailwayLine> lines) throws Exception {
        ArrayList<Station> orderedStations = orderStationsFromLines(lines);

        ArrayList<PointOfRoute> points = new ArrayList<>();
        for (Station station : orderedStations) {
            points.add(new PointOfRoute(station, new ArrayList<CargoType>(), CargoMode.Full));
        }

        createRoute(points);
    }

    private ArrayList<Station> orderStationsFromLines(ArrayList<RailwayLine> lines) throws Exception {
        if (lines == null || lines.isEmpty()) {
            throw new IllegalArgumentException("No lines provided");
        }

        // Build adjacency map
        Map<Station, ArrayList<Station>> adjacencyMap = new HashMap<>();
        for (RailwayLine line : lines) {
            adjacencyMap.computeIfAbsent(line.getStationA(), k -> new ArrayList<>()).add(line.getStationB());
            adjacencyMap.computeIfAbsent(line.getStationB(), k -> new ArrayList<>()).add(line.getStationA());
        }

        // Find start station (one with only 1 neighbor)
        Station start = null;
        for (Map.Entry<Station, ArrayList<Station>> entry : adjacencyMap.entrySet()) {
            if (entry.getValue().size() == 1) {
                start = entry.getKey();
                break;
            }
        }

        if (start == null) {
            throw new Exception("Invalid route: no unique start station found");
        }

        ArrayList<Station> orderedStations = new ArrayList<>();
        Set<Station> visited = new HashSet<>();
        Station current = start;
        Station previous = null;

        while (current != null && !visited.contains(current)) {
            orderedStations.add(current);
            visited.add(current);
            ArrayList<Station> neighbors = adjacencyMap.getOrDefault(current, new ArrayList<>());

            Station next = null;
            for (Station neighbor : neighbors) {
                if (!neighbor.equals(previous)) {
                    next = neighbor;
                    break;
                }
            }
            previous = current;
            current = next;
        }

        return orderedStations;
    }
}
