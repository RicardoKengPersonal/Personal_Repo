# US13 - Train Connectivity Verification in the Railway Network

---

## Legend

- **n**: Number of stations
- **m**: Number of connections (lines)
- **t**: Number of station types

---

## Complexity Analysis by Class

---

### `US13` (Main)

#### Main Menu (`main`)
| Pseudocode Step                | Complexity      |
|-------------------------------|-----------------|
| 1. Loop forever               | O(1) per iter.  |
| 2. Show menu and get option   | O(1)            |
| 3. Switch on option           | O(1)            |
| 4. If 0: print exit and return| O(1)            |
| 5. If 1: call checkConnectivityByStationType | O(m × n) |
| 6. If 2: call checkConnectivityByStation     | O(m × n) |
| 7. If 3: call listConnections                | O(n + m) |
| 8. Else: print invalid option                | O(1)      |
| 9. Prompt user to press Enter                | O(1)      |
| **Total**                                   | **O(m × n)** (worst case) |

---

#### `checkConnectivityByStationType`
| Pseudocode Step | Complexity      |
|-----------------|-----------------|
| 1. Ask user for CSV file path (UI.requestPath) | O(1) |
| 2. Read CSV file and load stations/connections (reader.readCSV) | O(m) |
| 3. Ask user if only electrified lines should be used (UI.eletricTrainsOnly) | O(1) |
| 4. Ask user to select station types (UI.stationTypeSelection) | O(1) |
| 5. Get all stations of selected types (reader.getStations) | O(n × t) |
| 6. If less than 2 stations, print message and return | O(1) |
| 7. Build adjacency matrix with restrictions (reader.buildAdjMatrixRestricted) | O(m × n) |
| 8. Get list of stations in the matrix (reader.getAdjacencyStationsRestricted) | O(m + n) |
| 9. Check if group of stations is connected (Utils.isGroupConnected) | O(n + m) |
| 10. Show result (UI.displayIsMatrixConnected) | O(1) |
| 11. Print matrix (Printer.printMatrix) | O(n²) |
| **Total** | **O(m × n)** |

---

#### `checkConnectivityByStation`
| Pseudocode Step | Complexity      |
|-----------------|-----------------|
| 1. Ask user for CSV file path (UI.requestPath) | O(1) |
| 2. Read CSV file and load stations/connections (reader.readCSV) | O(m) |
| 3. Ask user if only electrified lines should be used (UI.eletricTrainsOnly) | O(1) |
| 4. Get all stations of all types (reader.getAdjacencyStationsRestricted) | O(m + n) |
| 5. If less than 2 stations, print message and return | O(1) |
| 6. Build adjacency matrix with restrictions (reader.buildAdjMatrixRestricted) | O(m × n) |
| 7. Ask user to select starting station (UI.requestStartingStation) | O(n) |
| 8. Ask user to select ending station (UI.requestEndingStation) | O(n) |
| 9. Find route between stations using DFS (Utils.dfsRoute) | O(n + m) |
| 10. If route is empty, print message and return | O(1) |
| 11. Display route (UI.displayRoute) | O(n) |
| **Total** | **O(m × n)** |

---

#### `listConnections`
| Pseudocode Step | Complexity      |
|-----------------|-----------------|
| 1. Ask user for CSV file path (UI.requestPath) | O(1) |
| 2. Read CSV file and load stations/connections (reader.readCSV) | O(m) |
| 3. Display all connections (UI.displayStationConnections) | O(m) |
| 4. Visualize graph from matrix (GraphUI.visualizeGraphFromMatrix) | O(n + m) |
| **Total** | **O(n + m)** |

---

#### `buildAdjMatrixRestricted`
| Pseudocode Step | Complexity      |
|-----------------|-----------------|
| 1. Get list of stations for adjacency matrix (getAdjacencyStationsRestricted) | O(m + n) |
| 2. Create n x n zero matrix | O(n²) |
| 3. For each connection: | O(m × n) |
| &nbsp;&nbsp;a. If electrified restriction and connection is not electrified, skip | O(1) per conn |
| &nbsp;&nbsp;b. If both stations in nodes, set matrix[i][j] and matrix[j][i] to 1 | O(1) per conn |
| **Total** | **O(m × n)** |

---

#### `getAdjacencyStationsRestricted`
| Pseudocode Step | Complexity      |
|-----------------|-----------------|
| 1. Get all stations (getStations) | O(n) |
| 2. If electrified: | O(1) |
| &nbsp;&nbsp;a. For each line: if electrified, add both stations to nodes (no duplicates) | O(m) |
| &nbsp;&nbsp;b. For each station: if type in selected types and not in nodes, add | O(n) |
| 3. Else: use all stations as nodes | O(1) |
| **Total** | **O(m + n)** |

---

#### `getStations(List<StationType>)`
| Pseudocode Step | Complexity      |
|-----------------|-----------------|
| 1. Create empty result list | O(1) |
| 2. For each station in stations: | O(n) |
| &nbsp;&nbsp;a. For each type in types: | O(t) |
| &nbsp;&nbsp;&nbsp;&nbsp;i. If station type matches, add to result | O(1) |
| **Total** | **O(n × t)** |

---

#### `getAdjacencyStations(List<Station>, boolean)`
| Pseudocode Step | Complexity      |
|-----------------|-----------------|
| 1. Copy input stations to nodes list | O(n) |
| 2. If onlyElectrified: | O(1) |
| &nbsp;&nbsp;a. Create empty electrifiedNodes list | O(1) |
| &nbsp;&nbsp;b. For each connection: | O(m) |
| &nbsp;&nbsp;&nbsp;&nbsp;i. If electrified and both stations in input list: | O(n) |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Add both stations to electrifiedNodes (no duplicates) | O(1) |
| &nbsp;&nbsp;c. Set nodes = electrifiedNodes | O(1) |
| 3. Return nodes list | O(1) |
| **Total** | **O(m × n)** |

---

#### `getStationIndex(Station, List<Station>)`
| Pseudocode Step | Complexity      |
|-----------------|-----------------|
| 1. For each station in stations: | O(n) |
| &nbsp;&nbsp;a. If station equals target, return index | O(1) |
| 2. If not found, return -1 | O(1) |
| **Total** | **O(n)** |

---

### `Utils`

#### `dfsRoute`
| Pseudocode Step | Complexity      |
|-----------------|-----------------|
| 1. Initialize visited array and path list | O(n) |
| 2. Call recursive DFS from starting station: | O(n + m) |
| &nbsp;&nbsp;a. Mark current as visited, add to path | O(1) per node |
| &nbsp;&nbsp;b. If current is ending station, return path | O(1) |
| &nbsp;&nbsp;c. For each neighbor: | O(n) per node |
| &nbsp;&nbsp;&nbsp;&nbsp;i. If not visited, recurse | O(1) per neighbor |
| &nbsp;&nbsp;d. If no path found, backtrack (remove from path) | O(1) per node |
| 3. Return path (empty if not found) | O(1) |
| **Total** | **O(n + m)** |

---

#### `isGroupConnected`
| Pseudocode Step | Complexity      |
|-----------------|-----------------|
| 1. Initialize visited array | O(n) |
| 2. Start BFS/DFS from first node | O(n + m) |
| 3. For each reachable node, mark as visited | O(1) per node |
| 4. After traversal, check if all nodes are visited | O(n) |
| 5. Return true if all visited, false otherwise | O(1) |
| **Total** | **O(n + m)** |

---

### `PathCheckerUI`

#### `showMenu`
| Pseudocode Step | Complexity      |
|-----------------|-----------------|
| 1. Print menu header and options | O(1) |
| 2. Prompt user for option | O(1) |
| 3. Read integer input | O(1) |
| 4. Consume leftover newline | O(1) |
| 5. Return option | O(1) |
| **Total** | **O(1)** |

---

#### `requestPath`
| Pseudocode Step | Complexity      |
|-----------------|-----------------|
| 1. Loop: | O(1) per iter. |
| &nbsp;&nbsp;a. Prompt user for file path | O(1) |
| &nbsp;&nbsp;b. Read input | O(1) |
| &nbsp;&nbsp;c. Check if file exists, is file, and can read | O(1) |
| &nbsp;&nbsp;d. If valid, return path; else, print error and repeat | O(1) |
| **Total** | **O(1) per iter.** |

---

#### `eletricTrainsOnly`
| Pseudocode Step | Complexity      |
|-----------------|-----------------|
| 1. Loop: | O(1) per iter. |
| &nbsp;&nbsp;a. Prompt user for Y/N | O(1) |
| &nbsp;&nbsp;b. Read input and trim | O(1) |
| &nbsp;&nbsp;c. If input is Y or N, break loop | O(1) |
| 2. Return true if Y, false if N | O(1) |
| **Total** | **O(1) per iter.** |

---

#### `requestStartingStation`
| Pseudocode Step | Complexity      |
|-----------------|-----------------|
| 1. Display list of stations (displayStationList) | O(n) |
| 2. Loop: | O(1) per iter. |
| &nbsp;&nbsp;a. Prompt user for index | O(1) |
| &nbsp;&nbsp;b. Read integer input | O(1) |
| &nbsp;&nbsp;c. If index invalid, print error and repeat | O(1) |
| &nbsp;&nbsp;d. Else, break loop | O(1) |
| 3. Return station at selected index | O(1) |
| **Total** | **O(n) + O(1) per iter.** |

---

#### `requestEndingStation`
| Pseudocode Step | Complexity      |
|-----------------|-----------------|
| 1. Display list of stations (displayStationList) | O(n) |
| 2. Print prompt for ending station | O(1) |
| 3. Loop: | O(1) per iter. |
| &nbsp;&nbsp;a. Prompt user for index | O(1) |
| &nbsp;&nbsp;b. Read integer input | O(1) |
| &nbsp;&nbsp;c. If index invalid or same as starting, print error and repeat | O(1) |
| &nbsp;&nbsp;d. Else, break loop | O(1) |
| 4. Return station at selected index | O(1) |
| **Total** | **O(n) + O(1) per iter.** |

---

#### `stationTypeSelection`
| Pseudocode Step | Complexity      |
|-----------------|-----------------|
| 1. Initialize empty list for selected types | O(1) |
| 2. Loop until user selects "Done" and at least one type: | O(t × k), k = user selections |
| &nbsp;&nbsp;a. Print prompt for station types | O(1) |
| &nbsp;&nbsp;b. Display station type list (displayStationTypeList) | O(t) |
| &nbsp;&nbsp;c. Read integer input | O(1) |
| &nbsp;&nbsp;d. If "Done" and none selected, print error and repeat | O(1) |
| &nbsp;&nbsp;e. If valid type and not already selected, add to list | O(1) |
| &nbsp;&nbsp;f. If invalid, print error | O(1) |
| 3. Clear input buffer | O(1) |
| 4. Return list of selected types | O(1) |
| **Total** | **O(t × k), k = user selections** |

---

#### `displayStationTypeList`
| Pseudocode Step | Complexity      |
|-----------------|-----------------|
| 1. For each station type: | O(t) |
| &nbsp;&nbsp;a. Print index and type name | O(1) per type |
| 2. Print "Done" option | O(1) |
| **Total** | **O(t)** |

---

#### `displayRoute(List<Station>)`
| Pseudocode Step | Complexity      |
|-----------------|-----------------|
| 1. If route is empty, print "No path found" | O(1) |
| 2. Else: | O(1) |
| &nbsp;&nbsp;a. Print "Route:" header | O(1) |
| &nbsp;&nbsp;b. For each station in route: | O(n) |
| &nbsp;&nbsp;&nbsp;&nbsp;i. Print index, station name, and type | O(1) per station |
| **Total** | **O(n)** |

---

#### `displayStationConnections(InputReader)`
| Pseudocode Step | Complexity      |
|-----------------|-----------------|
| 1. Get all connections from reader (reader.getLines) | O(m) |
| 2. Print header for connections | O(1) |
| 3. Print column headers | O(1) |
| 4. Print separator line | O(1) |
| 5. For each connection: | O(m) |
| &nbsp;&nbsp;a. Print station A name, station B name, electrified status, and distance | O(1) per connection |
| **Total** | **O(m)** |

---

#### `displayIsMatrixConnected`
| Pseudocode Step | Complexity      |
|-----------------|-----------------|
| 1. Print empty line | O(1) |
| 2. If connected, print "Network status: CONNECTED" | O(1) |
| 3. Else, print "Network status: NOT CONNECTED" | O(1) |
| **Total** | **O(1)** |

---

### `GraphUI`

#### `visualizeGraphFromMatrix`
| Pseudocode Step | Complexity      |
|-----------------|-----------------|
| 1. Create new graph object | O(1) |
| 2. Set graph stylesheet attributes | O(1) |
| 3. For each station: | O(n) |
| &nbsp;&nbsp;a. Add node to graph | O(1) per station |
| &nbsp;&nbsp;b. Set node label and class based on type | O(1) per station |
| 4. For each connection: | O(m) |
| &nbsp;&nbsp;a. If both stations in list, add edge (if not already present) | O(1) per connection |
| &nbsp;&nbsp;b. If electrified, set edge class | O(1) per connection |
| 5. Display graph and enable auto layout | O(1) |
| **Total** | **O(n + m)** |

---

#### `Graph` (with shortest path)
| Pseudocode Step | Complexity      |
|-----------------|-----------------|
| 1. Create new graph object | O(1) |
| 2. Set graph stylesheet attributes | O(1) |
| 3. For each station: | O(n) |
| &nbsp;&nbsp;a. Add node to graph | O(1) per station |
| &nbsp;&nbsp;b. Set node label | O(1) per station |
| 4. For each connection: | O(m) |
| &nbsp;&nbsp;a. If both stations in list, add edge (if not already present) | O(1) per connection |
| 5. For each consecutive pair in shortestPath: | O(p) |
| &nbsp;&nbsp;a. Get edge between nodes (either direction) | O(1) per pair |
| &nbsp;&nbsp;b. If edge exists, set class to "shortest" | O(1) per pair |
| 6. Display graph and enable auto layout | O(1) |
| **Total** | **O(n + m + p)** |

---
