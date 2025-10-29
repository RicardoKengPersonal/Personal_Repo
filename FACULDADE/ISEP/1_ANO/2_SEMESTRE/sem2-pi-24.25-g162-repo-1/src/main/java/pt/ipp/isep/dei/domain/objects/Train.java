package pt.ipp.isep.dei.domain.objects;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import pt.ipp.isep.dei.domain.concept.Coordinates;
import pt.ipp.isep.dei.domain.concept.Route;


public class Train implements Serializable {
    private final Locomotive locomotive;
    private ArrayList<Carriage> carriages;
    private Coordinates coordinates;
    private Route route;
    private boolean hasRoute = false;

    private Station currentStation;
    private int currentRouteIndex = 0;
    private int daysTravelingBetweenStations = 0;
    private LocalDate simulationArrivalDate;
    private LocalDate simulationDepartureDate;
    private Station simulationDepartureStation;

    public Train(Locomotive locomotive) {
        this.locomotive = locomotive;
        this.carriages = new ArrayList<>();
    }

    public String getTrainName() {
        return this.locomotive.getName();
    }

    public void addCarriage(Carriage carriage) {
        this.carriages.add(carriage);
    }

    public ArrayList<Carriage> getCarriages() {
        return this.carriages;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Route getRoute() {
        return route;
    }

    public boolean hasRoute() {
        return hasRoute;
    }

    public void setRoute(Route route) {
        this.route = route;
        this.hasRoute = true;
    }

    public Locomotive getLocomotive() {
        return this.locomotive;
    }

    public Station getCurrentStation() {
        return currentStation;
    }

    public void setCurrentStation(Station currentStation) {
        this.currentStation = currentStation;
        if (currentStation != null) {
            this.coordinates = currentStation.getCoordinates();
        }
    }

    public int getCurrentRouteIndex() {
        return currentRouteIndex;
    }

    public void setCurrentRouteIndex(int currentRouteIndex) {
        this.currentRouteIndex = currentRouteIndex;
    }

    public int getDaysTravelingBetweenStations() {
        return daysTravelingBetweenStations;
    }

    public void setDaysTravelingBetweenStations(int days) {
        this.daysTravelingBetweenStations = days;
    }
    public LocalDate getSimulationArrivalDate() {
        return simulationArrivalDate;
    }

    public void setSimulationArrivalDate(LocalDate simulationArrivalDate) {
        this.simulationArrivalDate = simulationArrivalDate;
    }

    public LocalDate getSimulationDepartureDate() {
        return simulationDepartureDate;
    }

    public void setSimulationDepartureDate(LocalDate simulationDepartureDate) {
        this.simulationDepartureDate = simulationDepartureDate;
    }

    public Station getSimulationDepartureStation() {
        return simulationDepartureStation;
    }

    public void setSimulationDepartureStation(Station station) {
        this.simulationDepartureStation = station;
    }
    @Override
    public String toString() {
        return getTrainName(); // or any meaningful representation
    }
}
