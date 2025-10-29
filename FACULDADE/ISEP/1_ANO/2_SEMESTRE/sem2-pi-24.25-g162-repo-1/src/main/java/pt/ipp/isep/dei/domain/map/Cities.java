package pt.ipp.isep.dei.domain.map;

import java.io.Serializable;
import java.util.ArrayList;

import pt.ipp.isep.dei.domain.concept.Coordinates;
import pt.ipp.isep.dei.domain.objects.City;
import pt.ipp.isep.dei.domain.objects.HouseBlock;

public class Cities implements Serializable {
    private ArrayList<City> cities;

    public Cities() {
        this.cities = new ArrayList<>();
    }

    public void createCity(String name, Coordinates coordinates, ArrayList<HouseBlock> houseBlocks) {
        cities.add(new City(name, coordinates, houseBlocks));
    }

    public ArrayList<City> getAllCities() {
        return new ArrayList<>(cities);
    }
}
