package pt.ipp.isep.dei.domain.map;

import java.io.Serializable;
import java.util.ArrayList;

import pt.ipp.isep.dei.domain.ClassType.LineType;
import pt.ipp.isep.dei.domain.objects.RailwayLine;
import pt.ipp.isep.dei.domain.objects.Station;

public class RailwayLines implements Serializable {

    private ArrayList<RailwayLine> railwayLines;

    public RailwayLines() {
        railwayLines = new ArrayList<>();
    }

    public void createRailWayLine(Station stationA, Station stationB, LineType lineType) {
        railwayLines.add(new RailwayLine(stationA, stationB, lineType));
    }

    public RailwayLine getById(int id) throws Exception {
        for (RailwayLine railwayLine : railwayLines) {
            if (id == railwayLine.getId()) {
                return railwayLine;
            }
        }
        throw new Exception("No railway line with that ID!");
    }

    public ArrayList<RailwayLine> getAllRailwayLines() {
        return new ArrayList<>(railwayLines);
    }
}
