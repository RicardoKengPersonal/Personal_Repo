package pt.ipp.isep.dei.domain.objects;

import java.io.Serializable;

import pt.ipp.isep.dei.domain.ClassType.LineType;

public class RailwayLine implements Serializable {

    private static int idCounter = 0;

    private final int id;
    private final Station stationA;
    private final Station stationB;
    private final LineType lineType;

    public RailwayLine(Station stationA, Station stationB, LineType lineType) {
        this.stationA = stationA;
        this.stationB = stationB;
        this.id = ++idCounter;
        this.lineType = lineType;
    }

    public int getId() {
        return id;
    }

    public Station getStationA() {
        return stationA;
    }

    public Station getStationB() {
        return stationB;
    }

    public LineType getLineType() {
        return lineType;
    }

    @Override
    public String toString() {
        return String.format(
                "RailwayLine ID: %d\nStation A: %s\nStation B: %s\nLine Type: %s",
                id,
                stationA.getName(),
                stationB.getName(),
                lineType);
    }

}
