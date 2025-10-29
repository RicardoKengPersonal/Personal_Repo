package pt.ipp.isep.dei.domain.game;

import java.time.LocalDate;
import java.util.ArrayList;
import java.io.*; // Added for serialization

import pt.ipp.isep.dei.domain.ClassType;
import pt.ipp.isep.dei.domain.ClassType.LineType;
import pt.ipp.isep.dei.domain.ClassType.Orientation;
import pt.ipp.isep.dei.domain.ClassType.StationType;
import pt.ipp.isep.dei.domain.concept.Coordinates;
import pt.ipp.isep.dei.domain.concept.PointOfRoute;
import pt.ipp.isep.dei.domain.concept.Route;
import pt.ipp.isep.dei.domain.concept.Tile;
import pt.ipp.isep.dei.domain.map.Map;
import pt.ipp.isep.dei.domain.map.Routes;
import pt.ipp.isep.dei.domain.objects.BuildingUpgrade;
import pt.ipp.isep.dei.domain.objects.Carriage;
import pt.ipp.isep.dei.domain.objects.City;
import pt.ipp.isep.dei.domain.objects.Industry;
import pt.ipp.isep.dei.domain.objects.Locomotive;
import pt.ipp.isep.dei.domain.objects.RailwayLine;
import pt.ipp.isep.dei.domain.objects.Station;
import pt.ipp.isep.dei.domain.objects.Train;
import pt.ipp.isep.dei.domain.scenario.Scenario;

public class CurrentGame implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Map currentMap;
    private final Scenario currentScenario;
    private double currentMoney;
    private LocalDate currentDate;
    private ArrayList<Train> playerTrains;
    private ArrayList<Locomotive> ownedLocomotives = new ArrayList<>();
    private final Routes routes = new Routes();


    public CurrentGame(Map map, Scenario scenario) {
        this.currentMap = map;
        this.currentScenario = scenario;
        this.currentDate = scenario.getStartingDate();
        this.currentMoney = scenario.getInitialMoney();
        this.playerTrains = new ArrayList<>();
    }

    public Map getCurrentMap() {
        return currentMap;
    }

    public void addPlayerTrain(Train train) {
        playerTrains.add(train);
    }

    public ArrayList<Train> getPlayerTrain() {
        return playerTrains;
    }

    public double getCurrentMoney() {
        return this.currentMoney;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public boolean isFinished() {
        return currentDate.isAfter(currentScenario.getEndingDate());
    }

    public void buyLocomotive(Locomotive locomotive) throws Exception {
        double cost = locomotive.getAcquisitionPrice();
        if (currentMoney >= cost) {
            ownedLocomotives.add(locomotive);  // Only store locomotive, don't create train
            currentMoney -= cost;
        } else {
            throw new Exception("Insufficient funds!");
        }
    }


    public void buildStation(String name, StationType type, Coordinates coordinates, Orientation orientation)
            throws Exception {
        double cost = type.getConstructionCost();
        if (currentMoney >= cost) {
            currentMap.createStation(name, coordinates, type, orientation);
            currentMoney -= cost;
        } else {
            throw new Exception("Insuficient funds!");
        }
    }

    public void addCarriageToTrain(Train train, Carriage carriage) {
        train.addCarriage(carriage);
    }

    public void upgradeStation(Station station, BuildingUpgrade buildingUpgrade) throws Exception {
        double cost = buildingUpgrade.getCost();
        if (currentMoney >= cost) {
            currentMap.upgradeStation(station, buildingUpgrade);
            currentMoney -= cost;
        } else {
            throw new Exception("Insuficient funds!");
        }
    }

    public ArrayList<Train> getTrains() {
        return currentMap.getAllTrains();
    }

    public ArrayList<Station> getStations() {
        return currentMap.getAllStations();
    }

    public ArrayList<City> getCities() {
        return currentMap.getAllCities();
    }

    public ArrayList<Industry> getIndustries() {
        return currentMap.getAllIndustries();
    }

    public ArrayList<RailwayLine> getRailwayLines() {
        return currentMap.getAllRailwayLines();
    }

    public int getMapWith() {
        return currentMap.getMapWith();
    }

    public int getMapHeight() {
        return currentMap.getMapHeight();
    }

    public Tile getTile(int x, int y) {
        return currentMap.getTile(x, y);
    }

    public String findClosestCityName(Coordinates coordinates) {
        return currentMap.findClosestCityName(coordinates);
    }

    public void createRailWayLine(Station stationA, Station stationB, LineType lineType) throws Exception {
        double cost = getRailwayLineCost(stationA, stationB, lineType);
        if (currentMoney >= cost) {
            currentMap.createRailWayLine(stationA, stationB, lineType);
            currentMoney -= cost;
        } else {
            throw new Exception("Insuficient funds!");
        }

    }

    public double getRailwayLineCost(Station stationA, Station stationB, LineType lineType) {
        double distance = currentMap.calculateDistance(stationA.getCoordinates(), stationB.getCoordinates());
        return distance * lineType.getCost();
    }

    public ArrayList<Station> getConnectedStationsFrom(Station selectedStation) {
        return currentMap.getConnectedStationsFrom(selectedStation);
    }

    public void setTrain(ArrayList<Train> trains) {
        for (Train train : trains) {
            playerTrains.add(train);
        }
    }


    public ArrayList<Train> getPlayerTrains() {
        return playerTrains;
    }

    public ArrayList<Locomotive> getOwnedLocomotives() {
        return ownedLocomotives;
    }

    public void buildTrain(Locomotive locomotive, ArrayList<Carriage> carriages) throws Exception {
        if (!ownedLocomotives.contains(locomotive)) {
            throw new Exception("You do not own this locomotive.");
        }

        Train train = new Train(locomotive);
        for (Carriage carriage : carriages) {
            train.addCarriage(carriage);
        }

        playerTrains.add(train);
        ownedLocomotives.remove(locomotive);  // Remove it from inventory after using
    }

    public void createRouteFromLines(ArrayList<RailwayLine> selectedLines) throws Exception {
        if (selectedLines == null || selectedLines.isEmpty()) {
            throw new IllegalArgumentException("No lines selected.");
        }
        routes.createRouteFromRailwayLines(selectedLines);
    }

    public Routes getRoutes() {
        return routes;
    }

    public void setCurrentMoney(double money) {
        this.currentMoney = money;
    }

    public void advanceOneDay() {
        this.currentDate = this.currentDate.plusDays(1);

        // Optional: Add logic for day-based events
        // updateStationStatus();
        // generateDailyRevenue();
        // moveTrains();
    }

    // Remover métodos de save/load diretos, pois agora são responsabilidade do controller
    // public void saveToFile(String fileName) throws IOException {
    //     pt.ipp.isep.dei.domain.saves.saveFile.saveGame(this, fileName);
    // }

    // public static CurrentGame loadFromFile(String filePath) throws IOException, ClassNotFoundException {
    //     return pt.ipp.isep.dei.domain.saves.saveFile.loadGame(filePath);
    // }

    public void saveToFile(String fileName) throws IOException {
        pt.ipp.isep.dei.domain.saves.saveFile.saveGame(this, fileName);
    }


    public static CurrentGame loadFromFile(String fileName) throws IOException, ClassNotFoundException {
        return pt.ipp.isep.dei.domain.saves.saveFile.loadGame(fileName);
    }
}