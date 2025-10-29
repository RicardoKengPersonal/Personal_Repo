# US004 - As an Editor, I want to create a scenario for a selected map.

## 4. Tests

### 4.1 Domain Object Validation

**Test 1:** Ensure that a Scenario cannot be created with a null name.

        @Test(expected = IllegalArgumentException.class)
        public void ensureScenarioNameIsNotNull() {
        new Scenario(null, "A description", new Date(), new Date(), new Map("Map A", 10, 10), new Editor("John", "john@email.com", 123456789));
        }


**Test 2:** Ensure that scenario start time cannot be after end time.

        @Test(expected = IllegalArgumentException.class)
        public void ensureStartTimeIsBeforeEndTime() {
        Date start = new GregorianCalendar(2024, Calendar.JUNE, 1).getTime();
        Date end = new GregorianCalendar(2024, Calendar.MAY, 1).getTime();
        new Scenario("Scenario X", "Description", start, end, new Map("Map A", 10, 10), new Editor("John", "john@email.com", 123456789));
        }
**Test 3:** Ensure a valid scenario is correctly instantiated.

        @Test
        public void ensureValidScenarioIsCreated() {
        Map map = new Map("Europe", 20, 20);
        Editor editor = new Editor("Alice", "alice@mail.com", 987654321);
        Date start = new Date();
        Date end = new Date(start.getTime() + 100000);
        
            Scenario scenario = new Scenario("Logistics Plan", "Detailed description", start, end, map, editor);
        
            assertEquals("Logistics Plan", scenario.getName());
            assertEquals(map, scenario.getMap());
            assertEquals(editor, scenario.getCreatedBy());
        }

**Test 4:** Ensure that the controller fetches the list of available maps.

        @Test
        public void ensureMapsAreFetched() {
        List<Map> maps = controller.getListOfMaps();
        
            assertNotNull(maps);
            assertTrue(maps.size() > 0);
        }

**Test 5:** Ensure the scenario is persisted to the repository.

        @Test
        public void ensureScenarioIsSavedToRepository() {
        Scenario scenario = controller.createScenario("Demo", "Test", start, end, map, locomotives, ports, industries);
        
            verify(scenarioRepository).addScenario(scenario);
        }

**Test 6:** Ensure controller returns the created Scenario.

        @Test
        public void ensureScenarioIsReturned() {
        Scenario scenario = controller.createScenario("NewScenario", "Metadata", start, end, map, locomotives, ports, industries);
        
            assertNotNull(scenario);
            assertEquals("NewScenario", scenario.getName());
        }



_It is also recommended to organize this content by subsections._

## 5. Construction (Implementation)

### Class CreateScenarioController

```java
public Scenario createScenario(String name, String description, Date startTime, Date endTime,
                               Map map, List<LocomotiveType> locomotives, List<Port> ports, List<Industry> industries) {
    Editor editor = getEditorFromSession();

    Scenario scenario = new Scenario(name, description, startTime, endTime, map, editor);
    scenario.setAvailableLocomotives(locomotives);
    scenario.setPorts(ports);
    scenario.setIndustries(industries);

    Repositories.getInstance().getScenarioRepository().addScenario(scenario);
    return scenario;
}

```

### Class Scenario

```java
public Scenario(String name, String description, Date startTime, Date endTime, Map map, Editor createdBy) {
    if (name == null || startTime == null || endTime == null || map == null || createdBy == null) {
        throw new IllegalArgumentException("Scenario parameters must not be null");
    }
    if (startTime.after(endTime)) {
        throw new IllegalArgumentException("Start time must be before end time");
    }

    this.name = name;
    this.description = description;
    this.startTime = startTime;
    this.endTime = endTime;
    this.map = map;
    this.createdBy = createdBy;
}

```

## 6. Integration and Demo

* A new option "Create Scenario" is available in the main menu.

* Upon selecting it, the UI fetches the list of maps from the repository and displays them.

* Editor fills in the scenario metadata and confirms the creation.

* A success message is shown after the scenario is persisted.

## 7. Observations

n/a.