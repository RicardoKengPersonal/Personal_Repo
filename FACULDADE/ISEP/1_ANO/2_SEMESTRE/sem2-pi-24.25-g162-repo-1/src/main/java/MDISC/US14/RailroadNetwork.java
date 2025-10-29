package MDISC.US14;

import MDISC.US13.InputReader;
import MDISC.US13.Station;
import MDISC.US13.Line;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RailroadNetwork {
    private final InputReader dataReader;
    private List<Station> networkStations;

    public RailroadNetwork(InputReader dataReader) {
        this.dataReader = dataReader;
        this.networkStations = new ArrayList<>(dataReader.getStations());

        for (int i = 0; i < networkStations.size(); i++) {
            networkStations.get(i).setId(i);
        }
    }

    public List<Station> getStation () {
        return networkStations;
    }

    public int[][] getAdjacencyMatrix(boolean onlyElectrified) {
        return dataReader.buildAdjacencyMatrix(networkStations, onlyElectrified);
    }

}
