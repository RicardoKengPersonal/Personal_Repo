package pt.ipp.isep.dei.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import pt.ipp.isep.dei.domain.ClassType.LineType;
import pt.ipp.isep.dei.domain.ClassType.Orientation;
import pt.ipp.isep.dei.domain.ClassType.StationType;
import pt.ipp.isep.dei.domain.concept.*;
import pt.ipp.isep.dei.domain.game.CurrentGame;
import pt.ipp.isep.dei.domain.game.GameEngine;
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
import pt.ipp.isep.dei.interfaces.GameObjectProvider;
import pt.ipp.isep.dei.repository.GamesRepository;
import pt.ipp.isep.dei.repository.MapRepository;
import pt.ipp.isep.dei.repository.Repositories;
import pt.isep.lei.esoft.auth.UserSession;

public class GameController implements GameObjectProvider {
    private final GamesRepository gameRepo;
    private final MapRepository mapRepo;
    private final GameEngine engine;
    private final UpgradeCatalog upgradeCatalog;
    private final LocomotiveCatalog locomotiveCatalog;
    private final CurrentGame currentGame;
    private final UserSession userSession;
    private Routes routes = new Routes();

    public GameController(CurrentGame currentGame) throws IOException {
        this.userSession = Repositories.getInstance().getAuthenticationRepository()
                .getCurrentUserSession();
        this.gameRepo = Repositories.getInstance().getGamesRepository();
        this.mapRepo = Repositories.getInstance().getMapRepository();
        this.engine = new GameEngine();
        this.upgradeCatalog = new UpgradeCatalog();
        this.locomotiveCatalog = new LocomotiveCatalog();
        this.engine.setObjectProvider(this);
        this.currentGame = currentGame;
    }

    public ArrayList<BuildingUpgrade> getBuildingUpgrades() {
        return upgradeCatalog.getUpgradesCatalog();
    }


    public StationType[] getStationTypes() {
        return StationType.values();
    }

    public Map getCurrentMap() {
        return currentGame.getCurrentMap();
    }

    public LocalDate getCurrentDate() {
        return currentGame.getCurrentDate();
    }

    public double getCurrentMoney() {
        return currentGame.getCurrentMoney();
    }

    public void buyLocomotive(Locomotive locomotive) throws Exception {
        try {
            currentGame.buyLocomotive(locomotive);  // No train creation here anymore
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public void addCarriageToTrain(Train train, Carriage carriage) throws Exception {
        currentGame.addCarriageToTrain(train, carriage);
    }

    public void buildStation(String name, StationType type, Coordinates coordinates, Orientation orientation)
            throws Exception {
        try {
            currentGame.buildStation(name, type, coordinates, orientation);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void upgradeStation(Station station, BuildingUpgrade upgrade) throws Exception {
        currentGame.upgradeStation(station, upgrade);
    }

    public ArrayList<Map> getMapList() {
        return mapRepo.getMapsList();
    }


    @Override
    public ArrayList<Train> getTrains() {
        return currentGame.getPlayerTrains(); // <-- return player's trains
    }

    @Override
    public ArrayList<Station> getStations() {
        return currentGame.getStations();
    }

    @Override
    public ArrayList<City> getCities() {
        return currentGame.getCities();
    }

    @Override
    public ArrayList<Industry> getIndustries() {
        return currentGame.getIndustries();
    }

    @Override
    public ArrayList<RailwayLine> getRailwayLines() {
        return currentGame.getRailwayLines();
    }

    public void update() {
        engine.update();
    }

    public int getMapWidth() {
        return currentGame.getMapWith();
    }

    public int getMapHeight() {
        return currentGame.getMapHeight();
    }

    public Tile getTile(int x, int y) {
        return currentGame.getTile(x, y);
    }

    public String findClosestCityName(Coordinates coordinates) {
        return currentGame.findClosestCityName(coordinates);
    }

    public String getCurrentUserName() {
        return userSession.getUserName();
    }

    public void buildRailwayLine(Station stationA, Station stationB, LineType lineType) throws Exception {
        if (stationA.equals(stationB)) {
            throw new IllegalArgumentException("Two distinct stations must be selected!");
        }
        currentGame.createRailWayLine(stationA, stationB, lineType);
    }

    public double getRailwayLineCost(Station stationA, Station stationB, LineType lineType) {
        return currentGame.getRailwayLineCost(stationA, stationB, lineType);
    }

    public ArrayList<Cargo> getCargosAtStation(Station selected) {
        return selected.getCargoList();
    }

    public ArrayList<Station> getConnectedStationsFrom(Station selectedStation) {
        return currentGame.getConnectedStationsFrom(selectedStation);
    }

    public ArrayList<City> getNearbyCities(Station station) {
        return getCurrentMap().getNearbyCities(station);
    }

    public ArrayList<Industry> getNearbyIndustries(Station station) {
        return getCurrentMap().getNearbyIndustries(station);
    }

    public ArrayList<Locomotive> getOwnedLocomotives() {
        return currentGame.getOwnedLocomotives();
    }


    public void addPlayerTrain(Train train) {
        currentGame.addPlayerTrain(train);
    }

    public void buildTrain(Locomotive locomotive, ArrayList<Carriage> carriages) throws Exception {
        currentGame.buildTrain(locomotive, carriages);
    }


    public void createRouteFromSelectedLines(ArrayList<RailwayLine> selectedLines) throws Exception {
        if (selectedLines == null || selectedLines.isEmpty()) {
            throw new IllegalArgumentException("No railway lines selected.");
        }
        try {
            currentGame.createRouteFromLines(selectedLines);
        } catch (Exception e) {
            throw new Exception("Failed to create route: " + e.getMessage());
        }
    }

    public ArrayList<Route> getRoutes() {
        return currentGame.getRoutes().getRoutes();  // currentGame.getRoutes() returns Routes object, then call getRoutes()
    }

    public void deductMoney(double amount) throws Exception {
        double currentMoney = getCurrentMoney();
        if (amount > currentMoney) {
            throw new Exception("Not enough money!");
        }
        currentGame.setCurrentMoney(currentMoney - amount);
    }

    public Scenario getCurrentScenario() {
        return currentGame.getCurrentMap().getScenarios().get(0); // or whichever scenario is chosen
    }


    public CurrentGame getCurrentGame() {
        return this.currentGame;  // assuming currentGame is a field in GameController
    }

    public boolean isDoubleTrackBetween(Station s1, Station s2) {
        List<RailwayLine> railwayLines = getRailwayLines();

        for (RailwayLine line : railwayLines) {
            boolean connectsStations =
                    (line.getStationA().equals(s1) && line.getStationB().equals(s2)) ||
                            (line.getStationA().equals(s2) && line.getStationB().equals(s1));

            if (connectsStations) {
                return line.getLineType().isDoubleTrack();
            }
        }
        return false;
    }
    public ArrayList<Locomotive> getAllLocomotives() {
        return locomotiveCatalog.getLocomotiveCatalog();
    }

    public ArrayList<Locomotive> getAvailableLocomotives() {
        ArrayList<Locomotive> availableLocomotives = new ArrayList<>();
        ArrayList<Locomotive> allLocomotives = locomotiveCatalog.getLocomotiveCatalog();
        for (Locomotive loco : allLocomotives) {
            if (loco.getAvailableFrom().isBefore(getCurrentDate())
                    || loco.getAvailableFrom().isEqual(getCurrentDate())) {
                availableLocomotives.add(loco);
            }
        }
        return availableLocomotives;
    }
}