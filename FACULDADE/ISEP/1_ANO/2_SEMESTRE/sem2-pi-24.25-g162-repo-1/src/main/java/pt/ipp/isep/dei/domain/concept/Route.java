package pt.ipp.isep.dei.domain.concept;

import java.util.List;

import pt.ipp.isep.dei.domain.ClassType.CargoType;
import pt.ipp.isep.dei.domain.objects.Station;

import java.io.Serializable;
import java.util.ArrayList;

public class Route implements Serializable {
    private static int counter = 0;
    private final int id;
    private final ArrayList<PointOfRoute> pointsOfRoute;

    public Route(ArrayList<PointOfRoute> pointOfRoutes) {
        this.id = ++counter;
        this.pointsOfRoute = pointOfRoutes;
    }

    public int getId() {
        return id;
    }

    public ArrayList<PointOfRoute> getPointOfRoutes() {
        return pointsOfRoute;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Points of Route:\n");
        for (PointOfRoute point : pointsOfRoute) {
            Station station = point.getStation();
            List<CargoType> cargoTypes = point.getCargoTypes();
            sb.append(" - Estação: ").append(station.getName());
            sb.append(" | Tipos de carga: ").append(cargoTypes);
            sb.append("\n");
        }
        return sb.toString();
    }
}