# US05 - Build a Station

## 4. Tests

### Test 1: Retrieve list of stations available to view

```java
    @Test
    public void testGetAvailableStationsReturnsStationsList() {
        StationRepository stationRepo = new StationRepository();
        Position pos1 = new Position(1, 1);
        Station station1 = new Station("Station1", StationType.STATION, pos1);
        stationRepo.addStation(station1);
    
        ViewStationDetailsController controller = new ViewStationDetailsController(stationRepo, new Map("TestMap", 10, 10));
    
        List<Station> stations = controller.getAvailableStations();
        assertNotNull(stations);
        assertTrue(stations.contains(station1));
    }
```
```java
    @Test
    public void testGetStationDetailsReturnsCorrectDetails() {
        StationRepository stationRepo = new StationRepository();
        Position pos1 = new Position(5, 5);
        Station station = new Station("StationA", StationType.STATION, pos1);
        Building building = new Building("Building1", BuildingType.OFFICE);
        station.addBuilding(building);
        stationRepo.addStation(station);
    
        Map map = new Map("TestMap", 10, 10);
        Industry industry = new Industry(new Position(6, 6), IndustryType.PRIMARY_SECTOR, new ResourceType("Coal"));
        map.addIndustry(industry);
    
        ViewStationDetailsController controller = new ViewStationDetailsController(stationRepo, map);
    
        StationDetails details = controller.getStationDetails(station);
        assertNotNull(details);
        assertTrue(details.getBuildings().contains(building));
        assertTrue(details.getNearbyIndustries().contains(industry));
    }
```

## 5. Construction (Implementation)

### Class ViewStationDetailsController

```java
public class ViewStationDetailsController {

    private final StationRepository stationRepository;

    public ViewStationDetailsController(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public List<Station> getAvailableStations() {
        return stationRepository.getAllStations();
    }

    public StationDetails getStationDetails(Station station) {
        List<Building> buildings = station.getBuildings();
        List<Industry> industries = station.getIndustries();  // agora vem direto da Station

        return new StationDetails(buildings, industries);
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
import java.util.ArrayList;

public class Station {
    private final String name;
    private final StationType type;
    private final Position position;
    private final List<Building> buildings = new ArrayList<>();
    private final List<Industry> industries = new ArrayList<>();

    public Station(String name, StationType type, Position position) {
        this.name = name;
        this.type = type;
        this.position = position;
        buildings = new ArrayList<>();
        industries = new ArrayList<>();
    }

    public void addBuilding(Building building) {
        buildings.add(building);
    }

    // getters...
}

```

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

### 6. Integration and Demo

* Player initiates the "List Stations" feature from the user interface (UI).

* The system retrieves and displays a list of all available stations.

* Player selects a station from the list to view its details.

* The system shows detailed information for the selected station, including:

* Station name and type.

* List of existing buildings at the station.

* Information about the demand and supply cargoes associated with the station.

* Player can browse the details to understand the station’s structure and activity.

###  7. Observations

* The displayed list of stations is updated based on the current station repository.

* Station details are retrieved directly from the selected station to ensure consistent information.

* Related cargoes (demand and supply) are shown to provide full context of the station’s activity.

* The system must ensure the displayed data is synchronized with the current state of the map and repository.

