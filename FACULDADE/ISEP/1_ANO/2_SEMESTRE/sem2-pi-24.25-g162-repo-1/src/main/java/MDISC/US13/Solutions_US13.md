# US13 – Railway Network Path Validation and Route Finding

## Overview
The `PathCheckerUI` class provides a console-based interface for analyzing railway networks, checking connectivity between stations, and displaying network information.


## Class Responsibilities

### User Interface
- Presents a text-based menu system
- Handles all user input with validation
- Displays results in formatted tables

### Core Features
1. Network analysis by station type
2. Path finding between specific stations
3. Display of all station connections
4. Connectivity checking

## Main Methods

### `showMenu()`
Displays the main menu:

```markdown
|                                   |
|:---------------------------------:|
|           **MAIN MENU**           |
|                                   |
| 1 - Check network by station type |
| 2 - Check network by station      |
| 3 - View all station connections  |
| 0 - Exit                          |
|                                   |
```

# Depth-First Search (DFS) Algorithm

## Conceptual Overview
DFS is a graph traversal algorithm that explores as far as possible along each branch before backtracking.

### Railway Network Application
- **Nodes** = Stations
- **Edges** = Railway connections
- **Paths** = Possible routes between stations

# Depth-First Search (DFS) Algorithm Mathematical Explanation

## How DFS Works for Railway Networks

### 1. The Graph Structure
- **Stations as Nodes**: Each station is a point (vertex) in the network
- **Tracks as Edges**: Each connection between stations is a line (edge)
- **The Railway Map**: The complete network forms a mathematical graph `G = (V, E)`

### 2. The DFS Process
DFS explores one route completely before backtracking:
1. **Start at any station** (mark it as visited)
2. **Move to a connected station** (following tracks)
3. **Continue deeper** until reaching a dead-end
4. **Backtrack** to the last decision point and try alternate paths

### 3. Key Mathematical Concepts
- **Recursion/Stack**: Uses Last-In-First-Out (LIFO) ordering
- **Tree Formation**: Builds a search tree of possible routes
- **Termination**: Stops when a target is found or all options are exhausted

### 4. Railway-Specific Applications
- **Pathfinding**: Finds any valid route between two stations
- **Connectivity**: Checks if all stations are reachable
- **Constraints**: Can ignore non-electrified tracks if needed

### 5. Strengths and Limitations
| Strength                                    | Limitation                             |
|---------------------------------------------|----------------------------------------|
| Memory efficient (only tracks current path) | May find long routes before short ones |
| Simple to implement                         | Not guaranteed to find shortest path   |
| Works well with constraints                 | Can get "stuck" down deep paths        |


**DFS is this "always go deeper first" strategy** applied to railway networks!

## Algorithm Characteristics

```markdown
graph TD
    A[Start Station] --> B
    B --> C
    C --> D
    D --> E(Dead End)
    C --> F
    F --> G[Destination]
```

### Input Handling Methods
| Method                     | Description                             |
|----------------------------|-----------------------------------------|
| `requestPath()`            | Validates CSV file path input           |
| `eletricTrainsOnly()`      | Asks about electrified lines preference |
| `requestStartingStation()` | Gets starting station selection         |
| `requestEndingStation()`   | Gets ending station selection           |
| `stationTypeSelection()`   | Handles multiple station type selection |

### Display Methods
| Method                        | Output Format                      |
|-------------------------------|------------------------------------|
| `displayStationList()`        | Tabular list of stations           |
| `displayRoute()`              | Numbered path with station details |
| `displayStationConnections()` | Formatted connection table         |
| `displayIsMatrixConnected()`  | Network connectivity status        |

---

### Utils

# `isGroupConnected` Method Analysis

## Purpose
Checks if all stations of the selected types form a connected subgraph within the railway network.

## Parameters
| Parameter              | Type                | Description                                         |
|------------------------|---------------------|-----------------------------------------------------|
| `adjacencyMatrix`      | `int[][]`           | 2D matrix representing connections between stations |
| `adjacencyMatrixNodes` | `List<Station>`     | List of stations corresponding to matrix indices    |
| `selectedType`         | `List<StationType>` | Types of stations to check connectivity for         |

## Method Logic

### 1. Filter Relevant Stations

```markdown
List<Integer> requiredIndices = new ArrayList<>();
for (int i = 0; i < n; i++) {
    if (selectedType.contains(adjacencyMatrixNodes.get(i).getType())) {
        requiredIndices.add(i);
    }
}
```

## `dfsRoute` Method Analysis

## Purpose
Finds a path between two stations in a railway network using Depth-First Search (DFS).

## Parameters
| Parameter         | Type            | Description                                      |
|-------------------|-----------------|--------------------------------------------------|
| `adjacencyMatrix` | `int[][]`       | 2D matrix where `1` indicates connected stations |
| `stations`        | `List<Station>` | Complete list of all stations                    |
| `current`         | `Station`       | Starting station for the current DFS recursion   |
| `end`             | `Station`       | Target station to find                           |
| `visited`         | `boolean[]`     | Tracks which stations have been visited          |
| `dataReader`      | `InputReader`   | Helper to get station indices                    |

## Method Logic

### 1. Base Case - Found Target
```markdown
if (currentIndex == endIndex) {
    List<Station> path = new ArrayList<>();
    path.add(current);
    return path;
}
```
## Recursive Exploration

```markdown
for (int neighbor = 0; neighbor < adjacencyMatrix.length; neighbor++) {
if (adjacencyMatrix[currentIndex][neighbor] == 1 && !visited[neighbor]) {
List<Station> path = dfsRoute(..., stations.get(neighbor), ...);
if (!path.isEmpty()) {
path.add(0, current);
return path;
}
}
}
```
- **Checks all connected neighbors (adjacencyMatrix[currentIndex][neighbor] == 1)**

- **Skips already visited stations (!visited[neighbor])**

- **Recursively explores each valid neighbor**

- **Builds path by prepending current station when target is found**