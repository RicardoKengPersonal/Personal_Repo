package pt.ipp.isep.dei.interfaces;

import java.util.ArrayList;

import pt.ipp.isep.dei.domain.objects.City;
import pt.ipp.isep.dei.domain.objects.Industry;
import pt.ipp.isep.dei.domain.objects.RailwayLine;
import pt.ipp.isep.dei.domain.objects.Station;
import pt.ipp.isep.dei.domain.objects.Train;

public interface GameObjectProvider {
    ArrayList<Train> getTrains();

    ArrayList<Station> getStations();

    ArrayList<City> getCities();

    ArrayList<Industry> getIndustries();

    ArrayList<RailwayLine> getRailwayLines();
}
