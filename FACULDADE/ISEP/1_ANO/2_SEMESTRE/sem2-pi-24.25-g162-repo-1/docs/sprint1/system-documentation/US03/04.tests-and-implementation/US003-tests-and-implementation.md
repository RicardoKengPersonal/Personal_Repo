# US02 - Add City to the Map

## 4. Tests

**Test 1:** Test 1: Ensure city name does not contain special characters or digits (AC01)

```java
@Test(expected = IllegalArgumentException.class)
public void ensureCityNameHasNoSpecialCharsOrDigits() {
  Position position = new Position(3, 3);
  String invalidName = "City#123";
  int blocks = 5;

  City city = new City(invalidName, position, blocks); // Should throw exception
}
```

**Test 2:** Ensure manual block assignment works correctly (AC02)

```java
	@Test
    public void ensureManualBlockAssignmentWorks() {
      City city = new City("ValidCity", new Position(5, 5), 3);
      city.addBlock(new HouseBlock(new Position(6, 5)));
      city.addBlock(new HouseBlock(new Position(5, 6)));
      city.addBlock(new HouseBlock(new Position(6, 6)));
    
      assertEquals(3, city.getBlocks().size());
    }
```

## 5. Construction (Implementation)

### Class AddCityController

```java
    public City createCity(String name, Position position, int numberBlocks, boolean manualAssignment, List<Position> manualBlocksPositions) {
      if (!isValidName(name)) {
        throw new IllegalArgumentException("Invalid city name: no special chars or digits allowed.");
      }
      if (!currentMap.isPositionValid(position)) {
        throw new IllegalArgumentException("Invalid city position.");
      }
    
      City city = new City(name, position, numberBlocks);
    
      if (manualAssignment) {
        for (Position blockPos : manualBlocksPositions) {
          city.addBlock(new HouseBlock(blockPos));
        }
      } else {
        city.distributeBlocksAutomatically();
      }
    
      currentMap.addCity(city);
    
      return city;
   }

    private boolean isValidName(String name) {
      return name.matches("^[A-Za-z ]+$");
    }

```

### Class City

```java

public class City {
  private String name;
  private Position position;
  private List<HouseBlock> blocks = new ArrayList<>();
  private int numberBlocks;

  public City(String name, Position position, int numberBlocks) {
    if (!name.matches("^[A-Za-z ]+$")) {
      throw new IllegalArgumentException("City name invalid.");
    }
    this.name = name;
    this.position = position;
    this.numberBlocks = numberBlocks;
  }

  public void addBlock(HouseBlock block) {
    blocks.add(block);
  }

  public void distributeBlocksAutomatically() {
    // Example: generate blocks around city position with normal distribution
    Random rand = new Random();
    for (int i = 0; i < numberBlocks; i++) {
      int x = (int) Math.round(position.getX() + rand.nextGaussian());
      int y = (int) Math.round(position.getY() + rand.nextGaussian());
      blocks.add(new HouseBlock(new Position(x, y)));
    }
  }

  public List<HouseBlock> getBlocks() {
    return Collections.unmodifiableList(blocks);
  }
}


```

### Class Map

```java
public void addCity(City city) {
  cities.add(city);
}
```


## 6. Integration and Demo

* A new "Add City" option is available in the Editor menu.

* The user interface requests:

* Map selection

* City name (validated to contain only letters and spaces)

* Coordinates for the city tag

* Method for assigning house blocks (manual or automatic)

* Confirmation is requested before creating the city.

* In manual mode, the user inputs each house block position.

* In automatic mode, house blocks are distributed randomly around the city tag position using a normal distribution.

* Success or error messages are displayed after submission.

## 7. Observations

* City names must contain only letters and spaces.

* House blocks must be a positive number.

* Positions are validated for map boundaries.