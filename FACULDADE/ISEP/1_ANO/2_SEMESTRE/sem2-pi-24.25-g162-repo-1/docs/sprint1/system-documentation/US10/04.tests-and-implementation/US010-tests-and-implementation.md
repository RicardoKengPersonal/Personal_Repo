# US010 - As a Player, I want to assign a selected train to a route with valid stations and the respective list of cargoes to be picked up in each station.

## 4. Tests

### 4.1 Domain Layer Unit Tests

**Test 1:** Ensure that a Route cannot be created with null locomotive or stations list.

        @Test(expected = IllegalArgumentException.class)
        public void ensureRouteCreationFailsWithNullParameters() {
            new Route(null, null, null);
        }

**Test 2:** Verify that a Locomotive is only available for scenarios within its operational date range.

        @Test
        public void testLocomotiveAvailabilityForScenario() {
        Locomotive loco = new Locomotive(...);
        Scenario scenario = new Scenario(...);
        Date currentDate = new Date();
        
            boolean available = loco.isAvailableForScenario(scenario, currentDate);
            assertTrue(available);
        }


**Test 3:** Verify that Player debit method correctly updates virtual currency and prevents overdraft.

        @Test
        public void testPlayerDebit() {
        Player player = new Player("user1", "user1@example.com", 1000);
        boolean success = player.debit(500);
        assertTrue(success);
        assertEquals(500, player.getVirtualCurrency());
        
            success = player.debit(600);
            assertFalse(success);
            assertEquals(500, player.getVirtualCurrency());
        }

**Test 4:** Confirm AssignTrainController returns list of available scenarios.

        @Test
        public void testGetListOfAvailableScenarios() {
        AssignTrainController ctrl = new AssignTrainController();
        List<Scenario> scenarios = ctrl.getListOfAvailableScenarios();
        assertNotNull(scenarios);
        assertFalse(scenarios.isEmpty());
        }


**Test 5:** Ensure AssignTrainController validates connectivity between stations when adding stations.

        @Test(expected = IllegalStateException.class)
        public void testAddStationConnectivityValidation() {
        AssignTrainController ctrl = new AssignTrainController();
        ctrl.addStation(new Station("Station A", ...));
        ctrl.addStation(new Station("Station B", ...)); // Not connected to Station A
        }

**Test 6:** Validate AssignTrainController creates route successfully when all inputs are valid.

        @Test
        public void testCreateRouteSuccess() {
        AssignTrainController ctrl = new AssignTrainController();
        Locomotive loco = new Locomotive(...);
        List<Station> stations = List.of(new Station("A", ...), new Station("B", ...));
        List<Cargo> cargo = List.of(new Cargo("Coal", 10));
        
            Route route = ctrl.createRoute(loco, stations, cargo);
            assertNotNull(route);
            assertEquals(loco, route.getLocomotive());
        }

## 5. Construction (Implementation)

### Class AssignTrainController

```java
public class AssignTrainController {

    private Scenario selectedScenario;
    private Locomotive selectedLocomotive;
    private List<Station> selectedStations = new ArrayList<>();
    private List<Cargo> selectedCargo = new ArrayList<>();

    public List<Scenario> getListOfAvailableScenarios() {
        ScenarioRepository scenarioRepo = Repositories.getInstance().getScenarioRepository();
        return scenarioRepo.getListOfAvailableScenarios();
    }

    public void setScenario(Scenario scenario) {
        this.selectedScenario = scenario;
    }

    public List<Locomotive> getListOfAvailableLocomotive() {
        LocomotiveRepository locoRepo = Repositories.getInstance().getLocomotiveRepository();
        return locoRepo.getListOfAvailableLocomotives().stream()
                .filter(l -> l.isAvailableForScenario(selectedScenario, new Date()))
                .collect(Collectors.toList());
    }

    public void setLocomotive(Locomotive locomotive) {
        this.selectedLocomotive = locomotive;
    }

    public List<Station> getListOfAvailableStations() {
        StationRepository stationRepo = Repositories.getInstance().getStationRepository();
        return stationRepo.getListOfAvailableStations();
    }

    public void addStation(Station station) {
        if (!selectedStations.isEmpty()) {
            Station previous = selectedStations.get(selectedStations.size() - 1);
            RailwayLine railwayLine = new RailwayLine(previous, station, List.of());
            if (!railwayLine.areConnected(previous, station)) {
                throw new IllegalStateException("Stations are not connected.");
            }
        }
        selectedStations.add(station);
    }

    public List<Cargo> checkCargo(Station station) {
        return station.getCargo();
    }

    public void setCargo(List<Cargo> cargo) {
        this.selectedCargo = cargo;
    }

    public Route createRoute(Locomotive locomotive, List<Station> stations, List<Cargo> cargo) {
        // Assume player has enough virtual currency and all validations passed
        Player player = ApplicationSession.getInstance().getCurrentSession().getLoggedInPlayer();

        if (!player.debit(locomotive.getAcquisitionPrice())) {
            throw new IllegalStateException("Player cannot afford this locomotive.");
        }

        return new Route(locomotive, stations, cargo);
    }
}

```

### Class Player

```java
public class Player {
    private String username;
    private String email;
    private int virtualCurrency;

    public Player(String username, String email, int virtualCurrency) {
        this.username = username;
        this.email = email;
        this.virtualCurrency = virtualCurrency;
    }

    public boolean debit(int amount) {
        if (amount > virtualCurrency) {
            return false;
        }
        virtualCurrency -= amount;
        return true;
    }

    public int getVirtualCurrency() {
        return virtualCurrency;
    }
}

```


## 6. Integration and Demo

* Added "Assign Train" option to the Player's main UI.

* Bootstrapped scenarios, locomotives, stations, and cargo for demo.

* Users can now select scenario, locomotives, stations, and cargo, and assign trains with validations.

* Player's virtual currency is deducted upon successful train assignment.

## 7. Observations
n/a.
