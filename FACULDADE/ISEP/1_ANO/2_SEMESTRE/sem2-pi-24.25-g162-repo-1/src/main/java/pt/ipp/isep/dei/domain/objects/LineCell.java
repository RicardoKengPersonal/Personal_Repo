package pt.ipp.isep.dei.domain.objects;

import java.io.Serializable;

import pt.ipp.isep.dei.domain.ClassType.Orientation;
import pt.ipp.isep.dei.domain.concept.Coordinates;

public class LineCell implements Serializable {
    private final Coordinates coordinates;
    private final Orientation orientation;

    public LineCell(Coordinates coordinates, Orientation orientation) {
        this.coordinates = coordinates;
        this.orientation = orientation;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
