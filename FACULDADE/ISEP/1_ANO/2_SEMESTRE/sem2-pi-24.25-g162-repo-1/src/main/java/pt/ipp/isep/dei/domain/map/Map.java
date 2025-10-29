package pt.ipp.isep.dei.domain.map;

import pt.ipp.isep.dei.domain.ClassType.Building;
import pt.ipp.isep.dei.domain.ClassType.Ground;
import pt.ipp.isep.dei.domain.ClassType.IndustryGenerationFactor;
import pt.ipp.isep.dei.domain.ClassType.IndustryType;
import pt.ipp.isep.dei.domain.ClassType.LineType;
import pt.ipp.isep.dei.domain.ClassType.LocomotiveType;
import pt.ipp.isep.dei.domain.ClassType.Orientation;
import pt.ipp.isep.dei.domain.ClassType.ResourceType;
import pt.ipp.isep.dei.domain.ClassType.StationType;
import pt.ipp.isep.dei.domain.concept.Coordinates;
import pt.ipp.isep.dei.domain.concept.Tile;
import pt.ipp.isep.dei.domain.objects.BuildingUpgrade;
import pt.ipp.isep.dei.domain.objects.City;
import pt.ipp.isep.dei.domain.objects.HouseBlock;
import pt.ipp.isep.dei.domain.objects.Industry;
import pt.ipp.isep.dei.domain.objects.Locomotive;
import pt.ipp.isep.dei.domain.objects.RailwayLine;
import pt.ipp.isep.dei.domain.objects.Station;
import pt.ipp.isep.dei.domain.objects.Train;
import pt.ipp.isep.dei.domain.scenario.HistoricalEvent;
import pt.ipp.isep.dei.domain.scenario.Scenario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Map implements Serializable {
    private final String name;
    private final String description;
    private final int width;
    private final int height;
    private final double scale;
    private Tile[][] tiles;
    private Cities cities;
    private Industries industries;
    private Scenarios scenarios;
    private Stations stations;
    private Trains trains;
    private RailwayLines railwayLines;

    public Map(String name, String description, double scale, int width, int height) {
        if (!isValidName(name)) {
            throw new IllegalArgumentException(
                    "Map name must not be empty, and must be a valid file name (e.g., 'map1.ser')!");
        }

        if (scale <= 0) {
            throw new IllegalArgumentException("Scale must be positive!");
        }

        if (!isValidDescription(description)) {
            throw new IllegalArgumentException("Map description must not be empty!");
        }

        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Map dimensions must be positive integers!");
        }

        this.name = name;
        this.description = description;
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.cities = new Cities();
        this.industries = new Industries();
        this.scenarios = new Scenarios();
        this.stations = new Stations();
        this.trains = new Trains();
        this.tiles = new Tile[width][height];
        this.railwayLines = new RailwayLines();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[y][x] = new Tile(Ground.Grass, Building.None);
            }
        }

    }

    public ArrayList<Scenario> getScenarios() {
        return new ArrayList<>(scenarios.getAllScenarios());
    }

    public String getName() {
        return this.name;
    }

    private boolean isValidName(String name) {
        if (name == null || name.isBlank())
            return false;
        if (!name.matches("^[\\w\\-]+\\.(ser)$"))
            return false;
        return true;
    }

    private boolean isValidDescription(String description) {
        if (description == null || description.isBlank()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isPositionAvailable(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return false;
        }
        return tiles[x][y].isTileAvailable();
    }

    public Train getTrainByName(String name) {
        return trains.getTrainByName(name);
    }

    public void createCityManualy(String name, Coordinates coordinates, ArrayList<HouseBlock> houseBlocks) {
        if (!isPositionAvailable(coordinates.getX(), coordinates.getY())) {
            throw new IllegalArgumentException("Position not available.");
        }
        this.cities.createCity(name, coordinates, houseBlocks);
        for (HouseBlock houseBlock : houseBlocks) {
            tiles[houseBlock.getCoordinates().getX()][houseBlock.getCoordinates().getY()].setBuilding(Building.House);
        }
        tiles[coordinates.getX()][coordinates.getY()].setBuilding(Building.City);
    }

    public void createCityAuto(String name, Coordinates coordinates, int numberOfBlocks) {
        if (!isPositionAvailable(coordinates.getX(), coordinates.getY())) {
            throw new IllegalArgumentException("Position not available.");
        }
        ArrayList<HouseBlock> houseBlocks = distributeBlocksAuto(coordinates, numberOfBlocks, width, height);
        this.cities.createCity(name, coordinates, houseBlocks);
        tiles[coordinates.getX()][coordinates.getY()].setBuilding(Building.City);

    }

    public void createIndustry(Coordinates coordinates, ResourceType output, IndustryType industryType,
            IndustryGenerationFactor industryGenerationFactor) {
        if (!isPositionAvailable(coordinates.getX(), coordinates.getY())) {
            throw new IllegalArgumentException("Position not available.");
        }
        this.industries.createIndustry(coordinates, output, industryType, industryGenerationFactor);
        tiles[coordinates.getX()][coordinates.getY()].setBuilding(Building.Industry);
    }

    public void createStation(String name, Coordinates coordinates, StationType stationType, Orientation orientation) {
        if (!isPositionAvailable(coordinates.getX(), coordinates.getY())) {
            throw new IllegalArgumentException("Position not available.");
        }
        if (name == null) {
            name = findClosestCityName(coordinates);
        }

        this.stations.createStation(name, coordinates, stationType, orientation);
        switch (stationType) {
            case Depot:
                tiles[coordinates.getX()][coordinates.getY()].setBuilding(Building.Depot);
                break;
            case Station:
                tiles[coordinates.getX()][coordinates.getY()].setBuilding(Building.Station);
                break;
            case Terminal:
                tiles[coordinates.getX()][coordinates.getY()].setBuilding(Building.Terminal);
                break;
            default:
                break;
        }
        tiles[coordinates.getX()][coordinates.getY()].setBuilding(Building.Station);

    }

    public void createScenario(String name, String description, ArrayList<HistoricalEvent> historicalEvents,
            ArrayList<LocomotiveType> locomotiveTypes, ArrayList<ResourceType> industryTypes,
            LocalDate startingDate,
            LocalDate endingDate, double initialMoney,
            String editorName) {
        this.scenarios.createScenario(name, description, historicalEvents, locomotiveTypes, industryTypes,
                startingDate,
                endingDate, initialMoney, editorName);
    }

    public void createTrain(Locomotive locomotive) {
        this.trains.createTrain(locomotive);
    }

    public String findClosestCityName(Coordinates coordinates) {

        Coordinates target = new Coordinates(coordinates.getX(), coordinates.getY());
        ArrayList<City> cities = this.cities.getAllCities();
        City closestCity = cities.get(0);
        int minDistance = target.distanceTo(closestCity.getCoordinates());

        for (City city : cities) {
            int distance = target.distanceTo(city.getCoordinates());
            if (distance < minDistance) {
                closestCity = city;
                minDistance = distance;
            }
        }

        return closestCity.getName();
    }

    private ArrayList<HouseBlock> distributeBlocksAuto(Coordinates center, int numberOfBlocks, int mapWidth,
            int mapheight) {

        if (numberOfBlocks <= 0) {
            throw new IllegalArgumentException("City must have a positive number of house blocks.");
        }

        ArrayList<HouseBlock> blocks = new ArrayList<>();

        int centerX = center.getX();
        int centerY = center.getY();

        int attempts = 0;
        Random random = new Random();

        while (blocks.size() < numberOfBlocks && attempts < numberOfBlocks * 5) {

            int x = (int) Math.round(centerX + random.nextGaussian() * 2);
            int y = (int) Math.round(centerY + random.nextGaussian() * 2);

            if (x >= 0 && x < mapWidth && y >= 0 && y < mapheight) {
                if (isPositionAvailable(x, y)) {
                    HouseBlock block = new HouseBlock(new Coordinates(x, y));

                    if (!blocks.contains(block) && !(x == centerX && y == centerY)) {
                        blocks.add(block);
                        tiles[x][y].setBuilding(Building.House);
                    }
                }
            }

            attempts++;
        }

        return blocks;
    }

    public void upgradeStation(Station station, BuildingUpgrade buildingUpgrade) throws Exception {
        stations.upgradeStation(station, buildingUpgrade);
    }

    public Station getStationByName(String stationName) throws Exception {
        try {
            return stations.getStationByName(stationName);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public ArrayList<Station> getAllStations() {
        return new ArrayList<>(stations.getAllStations());
    }

    public ArrayList<Train> getAllTrains() {
        return new ArrayList<>(trains.getAllTrains());
    }

    public ArrayList<City> getAllCities() {
        return new ArrayList<>(cities.getAllCities());
    }

    public ArrayList<Industry> getAllIndustries() {
        return new ArrayList<>(industries.getAllIndustries());
    }

    public String getDescription() {
        return description;
    }

    public int getMapWith() {
        return width;
    }

    public int getMapHeight() {
        return height;
    }

    public Tile getTile(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return tiles[y][x];
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format(
                "Map: %s\nDescription: %s\nScale: %.2f km\nDimensions: %d x %d tiles",
                name,
                description,
                scale,
                width,
                height);
    }

    public String getGrid() {
        StringBuilder sb = new StringBuilder();

        // Geração da grelha
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                switch (tiles[i][j].getBuilding()) {
                    case None:
                        sb.append("- ");
                        break;
                    case City:
                        sb.append("C ");
                        break;
                    case House:
                        sb.append("H ");
                        break;
                    case Industry:
                        sb.append("I ");
                        break;
                    case Depot:
                        sb.append("D ");
                        break;
                    case Station:
                        sb.append("S ");
                        break;
                    case Terminal:
                        sb.append("T ");
                        break;
                    default:
                        sb.append("R ");
                        break;
                }
            }
            sb.append("\n"); // Fim da linha
        }

        // Legenda
        sb.append("\nLegenda:\n");
        sb.append("C = City\n");
        sb.append("H = House\n");
        sb.append("I = Industry\n");
        sb.append("D = Depot\n");
        sb.append("S = Station\n");
        sb.append("T = Terminal\n");
        sb.append("R = Railway\n");
        sb.append("- = Empty\n");

        return sb.toString();
    }

    public ArrayList<RailwayLine> getAllRailwayLines() {
        return new ArrayList<>(railwayLines.getAllRailwayLines());
    }

    public void createRailWayLine(Station stationA, Station stationB, LineType lineType) {
        railwayLines.createRailWayLine(stationA, stationB, lineType);
    }

    public double calculateDistance(Coordinates coordinatesA, Coordinates coordinatesB) {
        int dx = coordinatesB.getX() - coordinatesA.getX();
        int dy = coordinatesB.getY() - coordinatesA.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public ArrayList<Station> getConnectedStationsFrom(Station selectedStation) {
        ArrayList<Station> connected = new ArrayList<>();
        for (RailwayLine railwayLine : railwayLines.getAllRailwayLines()) {
            if (railwayLine.getStationA().equals(selectedStation)) {
                connected.add(railwayLine.getStationB());
            }
        }
        return connected;
    }

    public ArrayList<City> getNearbyCities(Station station) {
        ArrayList<City> nearby = new ArrayList<>();
        int radius = station.getStationType().getRadius();  // from enum
        Coordinates stationCoords = station.getCoordinates();

        for (City city : cities.getAllCities()) {
            double distance = stationCoords.distanceTo(city.getCoordinates());
            if (distance <= radius) {
                nearby.add(city);
            }
        }
        return nearby;
    }

    public ArrayList<Industry> getNearbyIndustries(Station station) {
        ArrayList<Industry> nearby = new ArrayList<>();
        int radius = station.getStationType().getRadius();
        Coordinates stationCoords = station.getCoordinates();

        for (Industry industry : industries.getAllIndustries()) {
            double distance = stationCoords.distanceTo(industry.getCoordinates());
            if (distance <= radius) {
                nearby.add(industry);
            }
        }
        return nearby;
    }

}