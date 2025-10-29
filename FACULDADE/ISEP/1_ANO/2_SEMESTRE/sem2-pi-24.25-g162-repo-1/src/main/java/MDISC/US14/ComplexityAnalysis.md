# US14 - Maintenance Route for Railway Lines

---

## Pseudocode and Complexity of Main Methods

---

### executeMaintenance

| Line(s) | Pseudocode Step                                 | Complexity |
|---------|-------------------------------------------------|------------|
| 1       | Print calculation message                       | O(1)       |
| 2       | Call Maintenance.execute                        | O(m²)      |
| 3       | If path is empty, print message                 | O(1)       |
| **Total**|                                                | **O(m²)**  |

---

### execute

| Line(s) | Pseudocode Step                                                                                  | Complexity   |
|---------|--------------------------------------------------------------------------------------------------|--------------|
| 1       | Get adjacency matrix from network (network.getAdjacencyMatrix)                                   | O(n²)        |
| 2       | Get number of stations (graph.length)                                                            | O(1)         |
| 3       | Calculate vertex degrees (sum each row of matrix)                                                | O(n²)        |
| 4       | Initialize empty path list                                                                       | O(1)         |
| 5       | If graph is not connected:                                                                       | O(n²)        |
| 5.1     | &nbsp;&nbsp;Simplify matrix to 0/1                                                               | O(n²)        |
| 5.2     | &nbsp;&nbsp;Call Utils.isGraphConnected                                                          | O(n + m)     |
| 5.3     | &nbsp;&nbsp;If not connected, print message and return empty path                                | O(1)         |
| 6       | Find vertices with odd degree (iterate degree array)                                             | O(n)         |
| 7       | If not Eulerian (odd degree count != 0 or 2):                                                    | O(1)         |
| 7.1     | &nbsp;&nbsp;Print message and return empty path                                                  | O(1)         |
| 8       | Choose initial point:                                                                            | O(n)         |
| 8.1     | &nbsp;&nbsp;If 2 odd vertices, print options and prompt user                                     | O(1)         |
| 8.2     | &nbsp;&nbsp;If 0 odd vertices, print options and prompt user                                     | O(n)         |
| 8.3     | &nbsp;&nbsp;Read user input and validate                                                         | O(1)         |
| 9       | Build adjacency list from matrix (for each cell, add edge if exists)                             | O(n²)        |
| 10      | Print "##### Maintenance Route #####"                                                            | O(1)         |
| 11      | Run Fleury's algorithm to find the maintenance route                                             | O(m²)        |
| 11.1    | &nbsp;&nbsp;While edges remain:                                                                  | O(m) iters   |
| 11.2    | &nbsp;&nbsp;&nbsp;&nbsp;For each adjacent edge:                                                  | O(n) per iter|
| 11.3    | &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Check if edge is valid (may check connectivity)              | O(n + m)     |
| 11.4    | &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Remove edge, add to path                                     | O(1)         |
| 12      | Visualize path (print each step)                                                                 | O(m)         |
| 13      | Return path                                                                                      | O(1)         |
| **Total**|                                                                                                 | **O(m²)**    |

---

### fleuryAlgorithm

| Line(s) | Pseudocode Step                                                                 | Complexity      |
|---------|---------------------------------------------------------------------------------|-----------------|
| 1       | Print starting station                                                          | O(1)            |
| 2       | While adjacency list for current node is not empty:                             | O(m) iterations |
| 2.1     | &nbsp;&nbsp;Set moved = false                                                   | O(1)            |
| 2.2     | &nbsp;&nbsp;For each neighbor in adjacency list:                                | O(n) per iter.  |
| 2.3     | &nbsp;&nbsp;&nbsp;&nbsp;Print checking edge                                     | O(1)            |
| 2.4     | &nbsp;&nbsp;&nbsp;&nbsp;Check if edge is valid (isNextEdgeValid)               | O(n + m)        |
| 2.5     | &nbsp;&nbsp;&nbsp;&nbsp;If valid:                                              | O(1)            |
| 2.6     | &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Print step                                 | O(1)            |
| 2.7     | &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Add step to path                           | O(1)            |
| 2.8     | &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Remove edge from adjacency list            | O(1)            |
| 2.9     | &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Set start = next                           | O(1)            |
| 2.10    | &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Set moved = true, break                    | O(1)            |
| 2.11    | &nbsp;&nbsp;If not moved, print message and break                              | O(1)            |
| **Total**|                                                                                | **O(m²)**       |

---

### isGraphConnected

| Line(s) | Pseudocode Step                                         | Complexity |
|---------|---------------------------------------------------------|------------|
| 1       | Get size n                                              | O(1)       |
| 2       | Create simplifiedGraph matrix                           | O(n²)      |
| 3       | For each i, j: set 1 if connection exists, else 0       | O(n²)      |
| 4       | Call Utils.isGraphConnected(simplifiedGraph)            | O(n + m)   |
| 5       | If not connected, print message and return false        | O(1)       |
| 6       | Print confirmation and return true                      | O(1)       |
| **Total**|                                                        | **O(n²)**  |

---

### calculateVertexDegrees

| Line(s) | Pseudocode Step                                 | Complexity |
|---------|-------------------------------------------------|------------|
| 1       | Initialize degree array                         | O(n)       |
| 2       | For each i in 0..n-1:                          | O(n)       |
| 2.1     | &nbsp;&nbsp;For each j in 0..n-1:              | O(n)       |
| 2.2     | &nbsp;&nbsp;&nbsp;&nbsp;Add graph[i][j] to degree[i] | O(1)  |
| 3       | Return degree array                             | O(1)       |
| **Total**|                                                | **O(n²)**  |

---

### buildAdjListFromMatrix

| Line(s) | Pseudocode Step                                         | Complexity |
|---------|---------------------------------------------------------|------------|
| 1       | Get size n                                              | O(1)       |
| 2       | Initialize adjacencyList array                          | O(n)       |
| 3       | For each i in 0..n-1:                                  | O(n)       |
| 3.1     | &nbsp;&nbsp;Initialize adjacencyList[i] as new list     | O(1)       |
| 3.2     | &nbsp;&nbsp;For each j in 0..n-1:                      | O(n)       |
| 3.3     | &nbsp;&nbsp;&nbsp;&nbsp;If matrix[i][j] > 0, add j     | O(1)       |
| 4       | Return adjacencyList                                    | O(1)       |
| **Total**|                                                        | **O(n²)**  |

---

### findOddDegreeVertices

| Line(s) | Pseudocode Step                                         | Complexity |
|---------|---------------------------------------------------------|------------|
| 1       | Initialize empty list oddVertices                       | O(1)       |
| 2       | For i from 0 to degree.length-1:                        | O(n)       |
| 2.1     | &nbsp;&nbsp;If degree[i] is odd, add i to oddVertices   | O(1) per i |
| 3       | Return oddVertices                                      | O(1)       |
| **Total**|                                                        | **O(n)**   |

---

### chooseInitialPoint

| Line(s) | Pseudocode Step                                                                 | Complexity      |
|---------|---------------------------------------------------------------------------------|-----------------|
| 1       | Initialize empty list startOptions                                              | O(1)            |
| 2       | If oddVertices.size() == 2:                                                     | O(1)            |
| 2.1     | &nbsp;&nbsp;Add both odd vertices to startOptions                               | O(1)            |
| 2.2     | &nbsp;&nbsp;Print message                                                       | O(1)            |
| 3       | Else if oddVertices.size() == 0:                                                | O(1)            |
| 3.1     | &nbsp;&nbsp;Print message                                                       | O(1)            |
| 3.2     | &nbsp;&nbsp;For i = 0 to degree.length-1:                                       | O(n)            |
| 3.3     | &nbsp;&nbsp;&nbsp;&nbsp;If degree[i] > 0, add i to startOptions                 | O(1) per i      |
| 4       | Print "Select the starting point for the route:"                                | O(1)            |
| 5       | For i = 0 to startOptions.size()-1:                                             | O(n)            |
| 5.1     | &nbsp;&nbsp;Print option index and station name                                 | O(1) per i      |
| 6       | Initialize scanner and chosenIndex                                              | O(1)            |
| 7       | While chosenIndex invalid:                                                      | O(1) per iter.  |
| 7.1     | &nbsp;&nbsp;Prompt user for input                                               | O(1)            |
| 7.2     | &nbsp;&nbsp;If input is int and valid, set chosenIndex                          | O(1)            |
| 7.3     | &nbsp;&nbsp;Else, print error and repeat                                        | O(1)            |
| 8       | Return startOptions.get(chosenIndex)                                            | O(1)            |
| **Total**|                                                                                | **O(n)**        |

---

### isNextEdgeValid

| Line(s) | Pseudocode Step                                                                 | Complexity      |
|---------|---------------------------------------------------------------------------------|-----------------|
| 1       | If adj[u] has only one neighbor, return true                                    | O(1)            |
| 2       | Remove edge (u, v) from adj                                                     | O(1)            |
| 3       | Check if graph is still connected (isGraphStillConnected)                       | O(n²)           |
| 4       | Add edge (u, v) back to adj                                                     | O(1)            |
| 5       | Return result                                                                   | O(1)            |
| **Total**|                                                                                | **O(n²)**       |


---

### visualizePath

| Line(s) | Pseudocode Step                                         | Complexity |
|---------|---------------------------------------------------------|------------|
| 1       | Print route header                                      | O(1)       |
| 2       | If path is empty, print message and return              | O(1)       |
| 3       | For i = 0 to path.size()-1:                            | O(m)       |
| 3.1     | &nbsp;&nbsp;Print step i                                | O(1) per i |
| **Total**|                                                        | **O(m)**   |

---

### Graphical Visualization (`GraphUI.visualizeGraphFromMatrix`)

| Line(s) | Pseudocode Step      | Complexity   |
|---------|----------------------|--------------|
| 1       | Create graph object  | O(1)         |
| 2       | Add nodes (stations) | O(n)         |
| 3       | Add edges (lines)    | O(m)         |
| 4       | Display graph        | O(1)         |
| **Total**|                     | **O(n + m)** |

---

### showConnectivity

| Line(s) | Pseudocode Step                                                                 | Complexity      |
|---------|---------------------------------------------------------------------------------|-----------------|
| 1       | Set exit = false                                                                | O(1)            |
| 2       | While not exit:                                                                 | O(1) per iter.  |
| 2.1     | &nbsp;&nbsp;Print menu header and options                                       | O(1)            |
| 2.2     | &nbsp;&nbsp;Prompt user for option                                              | O(1)            |
| 2.3     | &nbsp;&nbsp;Read user input                                                     | O(1)            |
| 2.4     | &nbsp;&nbsp;If input invalid, print error and continue                          | O(1)            |
| 2.5     | &nbsp;&nbsp;Switch on choice:                                                   | O(1)            |
| 2.5.1   | &nbsp;&nbsp;&nbsp;&nbsp;Case 1:                                                |                 |
| 2.5.1.1 | &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Print header for all lines                 | O(1)            |
| 2.5.1.2 | &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Get adjacency matrix for all lines          | O(n²)           |
| 2.5.1.3 | &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Print the matrix                           | O(n²)           |
| 2.5.1.4 | &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Show connectivity for the matrix            | O(n²)           |
| 2.5.2   | &nbsp;&nbsp;&nbsp;&nbsp;Case 2:                                                |                 |
| 2.5.2.1 | &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Print header for electrified lines         | O(1)            |
| 2.5.2.2 | &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Get adjacency matrix for electrified lines  | O(n²)           |
| 2.5.2.3 | &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Print the matrix                           | O(n²)           |
| 2.5.2.4 | &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Show connectivity for the matrix            | O(n²)           |
| 2.5.3   | &nbsp;&nbsp;&nbsp;&nbsp;Case 0: set exit = true, print closing message         | O(1)            |
| 2.5.4   | &nbsp;&nbsp;&nbsp;&nbsp;Default: print invalid choice message                  | O(1)            |
| **Total**|                                                                                | **O(n²)** per menu operation |

---

### showMatrixConnectivity

| Line(s) | Pseudocode Step                                                                 | Complexity      |
|---------|---------------------------------------------------------------------------------|-----------------|
| 1       | Get size n                                                                      | O(1)            |
| 2       | Initialize simplifiedMatrix n x n                                               | O(n²)           |
| 3       | For i = 0 to n-1:                                                               | O(n)            |
| 3.1     | &nbsp;&nbsp;For j = 0 to n-1:                                                   | O(n)            |
| 3.2     | &nbsp;&nbsp;&nbsp;&nbsp;If matrix[i][j] > 0, set simplifiedMatrix[i][j] = 1     | O(1) per cell   |
| 4       | If Utils.isGraphConnected(simplifiedMatrix):                                    | O(n + m)        |
| 4.1     | &nbsp;&nbsp;Print "The graph is CONNECTED."                                     | O(1)            |
| 5       | Else: print "The graph is NOT CONNECTED."                                       | O(1)            |
| **Total**|                                                                                | **O(n²)**       |

---

### buildRailRoadNetwork

| Line(s) | Pseudocode Step                                                                 | Complexity      |
|---------|---------------------------------------------------------------------------------|-----------------|
| 1       | Print prompt for CSV path                                                       | O(1)            |
| 2       | Read file path from user                                                        | O(1)            |
| 3       | Try:                                                                            | O(1)            |
| 3.1     | &nbsp;&nbsp;Create new InputReader                                              | O(1)            |
| 3.2     | &nbsp;&nbsp;Read CSV file (reader.readCSV)                                      | O(m × n)        |
| 3.3     | &nbsp;&nbsp;If no stations, print message and return false                      | O(1)            |
| 3.4     | &nbsp;&nbsp;Create new RailroadNetwork                                          | O(n)            |
| 3.5     | &nbsp;&nbsp;Print success message                                               | O(1)            |
| 3.6     | &nbsp;&nbsp;Return true                                                         | O(1)            |
| 4       | Catch exception: print error and return false                                   | O(1)            |
| **Total**|                                                                                | **O(m × n)**    |
