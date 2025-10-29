package pt.ipp.isep.dei.domain.map;

import java.io.Serializable;
import java.util.ArrayList;

import pt.ipp.isep.dei.domain.ClassType.Orientation;
import pt.ipp.isep.dei.domain.ClassType.StationType;
import pt.ipp.isep.dei.domain.concept.Coordinates;
import pt.ipp.isep.dei.domain.objects.BuildingUpgrade;
import pt.ipp.isep.dei.domain.objects.Station;

public class Stations implements Serializable {

    private ArrayList<Station> stations;

    public Stations() {
        this.stations = new ArrayList<>();
    }

    public void createStation(String name, Coordinates coordinates, StationType stationType, Orientation orientation) {
        try {
            stations.add(new Station(name, coordinates, stationType, orientation));
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void upgradeStation(Station station, BuildingUpgrade buildingUpgrade) throws Exception {
        getStationByName(station.getName()).addUpgrade(buildingUpgrade);

    }

    public Station getStationByName(String stationName) throws Exception {
        for (Station station : stations) {
            if (station.getName().equals(stationName)) {
                return station;
            }
        }
        throw new Exception("There is no station with that name!");
    }

    public ArrayList<Station> getAllStations() {
        return new ArrayList<>(stations);
    }
}