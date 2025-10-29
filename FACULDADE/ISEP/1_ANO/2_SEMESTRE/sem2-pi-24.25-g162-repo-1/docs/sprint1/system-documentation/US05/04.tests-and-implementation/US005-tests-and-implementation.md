@ -0,0 +1,137 @@
# US05 - Build a Station

## 4. Tests

### Test 1: Ensure overbuilding is not allowed (AC01)

```java
    @Test(expected = IllegalArgumentException.class)
    public void ensureOverbuildingIsNotAllowed() {
      Map map = new Map("MapName", 10);
      Position pos = new Position(2, 3);
      Station existing = new Station("Porto Station", StationType.STATION, pos, StationQuadrant.NE);
      map.addStation(existing);
    
      // Should throw exception because position is occupied
      Station newStation = map.buildStation("Another Station", StationType.STATION, pos, StationQuadrant.SE);
    }
```

## 5. Construction (Implementation)

### Class ViewStationDetailsController

```java
public class BuildStationController {

    private final StationRepository stationRepository;
    private final CityRepository cityRepository;
    private final BuildingTypeRepository buildingTypeRepository;
    private final Map map;

    public BuildStationController(StationRepository stationRepository,
                                  CityRepository cityRepository,
                                  BuildingTypeRepository buildingTypeRepository,
                                  Map map) {
        this.stationRepository = stationRepository;
        this.cityRepository = cityRepository;
        this.buildingTypeRepository = buildingTypeRepository;
        this.map = map;
    }

    public List<StationType> getStationTypes() {
        return StationType.values(); // or from StationTypeRepository
    }

    public List<BuildingType> getBuildingTypes() {
        return buildingTypeRepository.getAll();
    }

    public String getNearestCityName(Position pos) {
        return map.findNearestCity(pos).getName();
    }

    public void buildStation(String name, StationType type, Position position,
                             StationQuadrant quadrant, Building building) {
        Station newStation = new Station(name, type, position, quadrant);
        newStation.addBuilding(building);
        map.addStation(newStation); // validates internally
        stationRepository.addStation(newStation);
    }
}
```

```java
public class StationRepository {
private final Map<String, Station> stations = new HashMap<>();

    public void addStation(Station station) {
        stations.put(station.getId(), station);
    }

    public List<Station> getAllStations() {
        return new ArrayList<>(stations.values());
    }

    public Station getStationDetails(String stationId) {
        return stations.get(stationId);
    }
}
```

```java
public class Station {
    private final String id;
    private final String name;
    private final StationType type;
    private final Position position;
    private final StationQuadrant quadrant;
    private final List<Building> buildings = new ArrayList<>();

    public Station(String name, StationType type, Position position, StationQuadrant quadrant) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.type = type;
        this.position = position;
        this.quadrant = quadrant;
    }

    public void addBuilding(Building building) {
        buildings.add(building);
    }

    // getters...
}

```

### 6. Integration and Demo

* Player initiates the "Build Station" feature from the UI.

* System displays available station types.

* Player selects a station type (e.g., Depot, Terminal, Station).

* System requests the target location (XY).

* For Station, the system asks for the quadrant (NE, NW, SE, SW); for Depot/Terminal, geometric center is used.

* System finds the nearest city to that position and suggests a name like "Ovar Station".

* Player selects a building type from the list.

* Player provides a building name.

* The system shows a confirmation summary (name, type, building type, city).

* After confirmation, the controller validates position and creates the station.

* Station is added to the Map, and result is shown.

###  7. Observations

* The system validates that the position is unoccupied (no overbuilding allowed).

* Suggested station names are based on the nearest city.

* The station's center depends on the selected station type:

* Depot/Terminal: geometric center is used.

* Station: user must specify the quadrant (NE, NW, SE, SW).