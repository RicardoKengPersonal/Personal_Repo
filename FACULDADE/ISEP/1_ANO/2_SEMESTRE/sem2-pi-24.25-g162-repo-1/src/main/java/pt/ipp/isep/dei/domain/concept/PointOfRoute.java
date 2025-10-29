package pt.ipp.isep.dei.domain.concept;

import java.io.Serializable;
import java.util.ArrayList;

import pt.ipp.isep.dei.domain.ClassType.CargoMode;
import pt.ipp.isep.dei.domain.ClassType.CargoType;
import pt.ipp.isep.dei.domain.objects.Station;

public class PointOfRoute implements Serializable {
    private Station station;
    private ArrayList<CargoType> cargoTypes;
    private CargoMode cargoMode;

    public PointOfRoute(Station station, ArrayList<CargoType> cargoTypes, CargoMode cargoMode) {
        this.station = station;
        this.cargoTypes = cargoTypes;
        this.cargoMode = cargoMode;
    }

    public Station getStation() {
        return this.station;
    }

    public ArrayList<CargoType> getCargoTypes() {
        return this.cargoTypes;
    }

    public CargoMode getCargoMode() {
        return this.cargoMode;
    }
}
