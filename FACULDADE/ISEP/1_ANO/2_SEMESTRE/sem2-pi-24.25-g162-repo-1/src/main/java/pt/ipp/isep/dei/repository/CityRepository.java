package pt.ipp.isep.dei.repository;

import java.util.ArrayList;
import java.util.List;

import pt.ipp.isep.dei.domain.objects.City;

public class CityRepository {
    private List<City> cities;

    private City currentCity;

    public CityRepository() {
        cities = new ArrayList<>();
    }

    public void add(City city) {
        cities.add(city);
    }

    public List<City> getCitiesList() {
        return List.copyOf(cities);
    }

    public City getCitybyName(String name) {
        for (City city : cities) {
            if (city.getName().equals(name)) {
                return city;
            }
        }
        return null;
    }

    public void setCurrentCity(City currentCity) {
        this.currentCity = currentCity;
    }

    public City getCurrentCity() {
        return currentCity;
    }
}
