# US002 - As an Editor, I want to add an industry (selected from the available industries) in a position XY of the selected (current) map.

## 4. Tests

### 4.1. Domain Tests

**Test 1:** Ensure that it is not possible to create an Industry with a null location.
      
      @Test(expected = IllegalArgumentException.class)
          public void ensureNullLocationIsNotAllowed() {
          new Industry(null, new IndustryType("Factory"));
      }

**Test 2:** Ensure that it is not possible to create an Industry with a null industry type.
      
      @Test(expected = IllegalArgumentException.class)
        public void ensureNullTypeIsNotAllowed() {
        new Industry(new Location(5, 10), null);
      }

**Test 3:** Ensure that a valid industry is correctly instantiated.

    @Test
    public void ensureValidIndustryIsCreated() {
      Location location = new Location(5, 10);
      IndustryType type = new IndustryType("Factory");
    
        Industry industry = new Industry(location, type);
    
        assertEquals(location, industry.location());
        assertEquals(type, industry.industryType());
    }

**Test 4:** Ensure that creating a Location outside map bounds is rejected.

    @Test(expected = IllegalArgumentException.class)
      public void ensureInvalidCoordinatesThrowException() {
      Map map = new Map(20, 20); // example size: 20x20
      map.validateCoordinates(new Location(30, 10)); // x = 30 exceeds width
    }

**Test 5:**  Ensure the controller fetches the correct list of available maps.

      @Test
      public void ensureAvailableMapsAreFetched() {
          List<Map> maps = controller.getAvailableMaps();
      
          assertNotNull(maps);
          assertTrue(maps.size() > 0);
      }

**Test 6:** Ensure selected map is stored correctly in session.

    @Test
    public void ensureSelectedMapIsStoredInSession() {
        Map map = new Map(20, 20);
        controller.selectMap(map);
    
        assertEquals(map, MapSession.instance().getCurrentMap());
    }

**Test 7:** Ensure industry is created successfully when valid data is provided.

    @Test
    public void ensureIndustryIsCreatedSuccessfully() {
        Map map = new Map(20, 20);
        controller.selectMap(map);
        Location location = new Location(10, 10);
        IndustryType type = new IndustryType("Factory");
    
        Industry industry = controller.createIndustry(location.x(), location.y(), type.name());
    
        assertNotNull(industry);
        assertEquals(location, industry.location());
        assertEquals(type.name(), industry.industryType().name());
    }


## 5. Construction (Implementation)

### Class AddIndustryController

```java
public Industry createIndustry(int x, int y, String typeName) {
  Location location = new Location(x, y);

  Map currentMap = MapSession.instance().getCurrentMap();
  currentMap.validateCoordinates(location);

  IndustryType type = currentMap.currentScenario().getIndustryTypeByName(typeName);

  return new Industry(location, type);
}
```

### Class Map

```java
public void validateCoordinates(Location loc) {
  if (loc.x() < 0 || loc.x() >= width || loc.y() < 0 || loc.y() >= height) {
    throw new IllegalArgumentException("Invalid location");
  }
}

```


## 6. Integration and Demo

* A new option "Add Industry" was added to the Map Configuration menu.

* For demonstration purposes, several industry types and maps are bootstrapped at system startup.

## 7. Observations
n/a.