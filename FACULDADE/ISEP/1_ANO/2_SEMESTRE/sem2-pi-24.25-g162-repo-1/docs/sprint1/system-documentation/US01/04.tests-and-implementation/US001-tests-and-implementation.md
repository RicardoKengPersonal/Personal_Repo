# US001 - As an editor, I want to create a map with a size and a name

## 4. Tests

### 4.1. Domain Tests


**Test 1:** Ensure that map dimensions are positive.

```java
@Test
public void ensureMapDimensionsArePositive() {
    assertThrows(IllegalArgumentException.class, () -> {
        new Map("ValidName", 0, 10);
    });

    assertThrows(IllegalArgumentException.class, () -> {
        new Map("ValidName", 10, -5);
    });

    assertDoesNotThrow(() -> {
        new Map("ValidName", 20, 20);
    });
}
```

**Test 2:** Ensure that map name is valid

```java
@Test
public void ensureMapNameIsValid() {
    assertThrows(IllegalArgumentException.class, () -> {
        new Map("Invalid/Name", 10, 10);
    });

    assertThrows(IllegalArgumentException.class, () -> {
        new Map("", 10, 10);
    });

    assertDoesNotThrow(() -> {
        new Map("Map_01-Valid", 10, 10);
    });
}
```

## 5. Construction (Implementation)

### Class CreateMapController

```java
public Map createMap(String name, int size) {
    if (!isValidName(name)) {
        throw new IllegalArgumentException("Invalid file name.");
    }
    if (size <= 0) {
        throw new IllegalArgumentException("Size must be positive.");
    }

    Map map = new Map(name, size, size); // Assume square

    mapRepository.add(map);

    return map;
}
```

```java
private boolean isValidName(String name) {
    return name != null && name.matches("^[\\w,\\s-]+\\.[A-Za-z]{3,4}$");
}
```

### Class MapRepository

```java
public Map getMapByName(String name) {
    for (Map map : maps) {
        if (map.getName().equals(name)) {
            return map;
        }
    }

    return null;
}
```


## 6. Integration and Demo

* The option "Add map" was added to the EditorUI

* For demonstration purposes, a generic map was added to the bootstrap

## 7. Observations

* n/a
