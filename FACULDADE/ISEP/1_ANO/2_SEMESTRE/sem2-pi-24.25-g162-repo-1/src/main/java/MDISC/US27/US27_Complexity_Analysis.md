
| Linha | Pseudocódigo                                                                     |
|-------|----------------------------------------------------------------------------------|
| 1     | buildAdjancencyMatrix(stations: List<Station>, lines: List<Line>)                |
| 2     | path := PathCheckerUI.getPath()                                              |
| 3     | reader.readCSV(path)                                                             |
| 4     | stations := reader.getStations()                                                 |
| 5     | adjMatrix := buildAdjacencyMatrix(stations, reader.getLines())                   |
| 6     | start := getStation("Select Start station: ")                                |
| 7     | end := getStation("Select Start station: ")                                  |
| 8     | waypoints := getWaypoints(stations, start, end)                              | 
| 9     | fullPath := findPathThroughWaypoints(stations, start, waypoints, end, adjMatrix) |
| 10    | totalDistance := distanceCalc(fullPath, reader.getLines())                       |
| 11    | print("Total distance of the shortest route: %.2f km%n", totalDistance)          |
| 12    | displayFullPath(fullPath, reader.getLines());                                    |
| 13    | GraphUI.Graph(stations, reader.getLines(), fullPath);                            |


# Calculate Total Distance

| Line | Pseudocode                                                                            |
|------|---------------------------------------------------------------------------------------|
| 0    | distanceCalc(path: List<Station>, lines: List<Line>)                                  |
| 1    | total := 0.0                                                                          |
| 2    | for i := 0 to size(path) - 2                                                          |
| 3    | a := path[i]                                                                          |
| 4    | b := path[i + 1]                                                                      |
| 5    | for line in lines                                                                     |
| 6    | if line.stationA = a and line.stationB = b or line.stationA = b and line.stationB = a |
| 7    | total := total + line.distance                                                        |
| 8    | break                                                                                 |
| 9    | return total                                                                          |

| Linha | Algoritmo distanceCalc         |       
|-------|--------------------------------|
| 1     | 1A                             |
| 2     | (n - 1)A + (n - 1)C + (n - 1)I |
| 3     | (n - 1)A                       |
| 4     | (n - 1)A                       |
| 5     | (n - 1)(mA + mC + mI)          |
| 6     | (n - 1)(mC)                    |
| 7     | (n - 1)(mOp + mA)              |
| 8     | - (sem custo, break no fim)    |
| 9     | 1R                             |
| total | O(n * m)                       |

# Find Station Index

| Line | Pseudocode                                 |
|------|--------------------------------------------|
| 0    | findStationIndex(stations: List, target)   |
| 1    | for i := 0 to stations.size() - 1          |
| 2    | if stations[i] == target then return i     |
| 3    | return -1                                  |

---
| Linha | Operações Realizadas | 
|-------|----------------------|
| 1     | nA + nC + nI         |
| 2     | nC                   |
| 3     | 1R                   |
| total | O(n)                 |

# Display Station List

| Linha | Pseudocódigo                                       |
|-------|----------------------------------------------------|
| 0     | displayStationList(stations: List<Station>)        |
| 1     | print header                                       |
| 2     | for i := 0 to stations.size() - 1                  |
| 3     | print i + 1 and stations[i].getName()              |

---
| Linha | Operações Realizadas           |
|-------|--------------------------------|
| 1     | 3 Ops de saída (System.out)    |
| 2     | nA + nC + nI                   |
| 3     | n × (1A + 1Op + 1Output)       |
| total | O(n)                           |


# Show Path

| Line | Pseudocode                                                 |
|------|------------------------------------------------------------|
| 0    | showPath(path: List<Station>, lines: List<Line>)           |
| 1    | print "Route Overview:"                                    |
| 2    | distanceSoFar := 0.0                                       |
| 3    | for i := 0 to path.size() - 1                              |
| 4    | if i > 0 then                                              |
| 5    | segment := calcDistanceBetweenStations(path[i-1], path[i]) |
| 6    | distanceSoFar += segment                                   |
| 7    | print (i+1), path[i].name, distanceSoFar                   |
| 8    | print total distance                                       |

---

| Linha | Operações Realizadas                                        |
|-------|-------------------------------------------------------------|
| 1     | 1 Output                                                    |
| 2     | 1 Assignment (distanceSoFar = 0.0)                          |
| 3     | n Assignments + n Comparisons + n Increments (loop control) |
| 4     | (n-1) Comparisons                                           |
| 5     | (n-1) × (m × C + 1 Retrieval) [calcDistance call cost]      |
| 6     | (n-1) Arithmetic operations (addition)                      |
| 7     | n Outputs (prints inside loop)                              |
| 8     | 1 Output (final print)                                      |
| total | O(n × m)                                                    |
---

# Calculate Distance Between Two Stations

| Line | Pseudocode                                                         |
|------|--------------------------------------------------------------------|
| 0    | calcDistanceBetweenStations(a: Station, b: Station, lines: List)   |
| 1    | for line in lines                                                  |
| 2    | if (line connects a and b in either direction)                     |
| 3    | return line.distance                                               |
| 4    | return 0.0                                                         |

---
| Linha | Operações Realizadas          |
|-------|-------------------------------|
| 1     | mA + mC + mI                  |
| 2     | m × 4C                        |
| 3     | 1R (executado se encontrar)   |
| 4     | 1R (pior caso, não encontrou) |
| total | O(m)                          |

# Dijkstra

| Line | Pseudocode                                         |
|------|----------------------------------------------------|
| 0    | dijkstraAlgorithm(stations, start, end, adjMatrix) |
| 1    | n := stations.size()                               |
| 2    | for i := 0 to n-1                                  |
| 3    | startIdx := findStationIndex(start)                |
| 4    | distances[startIdx] := 0                           |
| 5    | for count := 0 to n-1                              |
| 6    | u := findMinDistanceNode()                         |
| 7    | if u == -1 break                                   |
| 8    | Update neighbors                                   |
| 9    | path := reconstructPath()                          |
| 10   | return path                                        |

---
| Line      | Operations Performed             |
|-----------|----------------------------------|
| 1         | `1A`                             |
| 2         | `nA`                             |
| 3         | `nC + nA + nI`                   |
| 4         | `1A`                             |
| 5         | `n × (1A + 1C + 1I)`             |
| 6         | `n × (n × (2C + 1A))`            |
| 7         | `n × 1C`                         |
| 8         | `n × (n × (3C + 1A + 1Op + 2A))` |
| 9         | `n × (1A + 1Insert + 1A)`        |
| 10        | `1C + 1R`                        |
| **Total** | **O(n²)**                        |

# Get Station

| Line | Pseudocode                                                |
|------|-----------------------------------------------------------|
| 0    | getStation(prompt: String, stations: List<Station>)       |
| 1    | showStations(stations)                                    |
| 2    | print prompt                                              |
| 3    | while true                                                |
| 4    | idx := input - 1                                          |
| 5    | if valid idx break                                        |
| 6    | else print error                                          |
| 7    | return stations[idx]                                      |

---
| Linha | Operações Realizadas |
|-------|----------------------|
| 1     | nA                   |
| 2     | 1P                   |
| 3     | 1C                   |
| 4     | 1I + 1S              |
| 5     | 1C + 1B              |
| 6     | 1P                   |
| 7     | 1R                   |
| total | O(n)                 |


---

# Get Waypoints

| Line | Pseudocode                                                        |
|------|-------------------------------------------------------------------|
| 0    | getWaypoints(stations: List, start, end)                          |
| 1    | waypoints := empty list                                           |
| 2    | get number of waypoints from user                                 |
| 3    | for each waypoint:                                                |
| 4    | do                                                                |
| 5    | wp := getStation()                                                |
| 6    | while wp is invalid (start/end/duplicate)                         |
| 7    | add to waypoints                                                  |
| 8    | return waypoints                                                  |

---

| Linha | Operações Realizadas                 |
|-------|--------------------------------------|
| 1     | 1A                                   |
| 2     | 1P                                   |
| 3     | 1C                                   |
| 4     | 1C                                   |
| 5     | O(getStation)                        |
| 6     | 2C + 2L + 1B                         |
| 7     | 1A                                   |
| 8     | 1R                                   |
| total | O(n)                                 |

# Find Path

| Linha | Operações Realizadas |
|-------|----------------------|
| 1     | 1A                   |
| 2     | 1A                   |
| 3     | 1A + 1C              |
| 4     | 1C                   |
| 5     | n × O(dijkstra)      |
| 6     | n × 1C + 1R          |
| 7     | n × M                |
| 8     | n × 1A               |
| 9     | 1R                   |
| total | O(n^3)               |

# Run (Main Controller Method)

| Line | Pseudocode                                                       |
|------|------------------------------------------------------------------|
| 0    | run()                                                            |
| 1    | path := PathCheckerUI.getPath()                                  |
| 2    | reader.readCSV(path)                                             |
| 3    | stations := reader.getStations()                                 |
| 4    | adjMatrix := buildAdjacencyMatrix(stations, reader.getLines())   |
| 5    | start := getStation("START", stations)                           |
| 6    | end := getStation("END", stations)                               |
| 7    | waypoints := getWaypoints(stations, start, end)                  |
| 8    | fullPath := findPath(stations, start, waypoints, end, adjMatrix) |
| 9    | totalDistance := distanceCalc(fullPath, reader.getLines())       |
| 10   | print totalDistance                                              |
| 11   | showPath(fullPath, reader.getLines())                            |
| 12   | GraphUI.Graph(stations, reader.getLines(), fullPath)             |

---
| Linha | Operações Realizadas                                                               |
|-------|------------------------------------------------------------------------------------|
| 1-2   | 1C + O(readCSV)                                                                    |
| 3     | 1A                                                                                 |
| 4     | O(n² + m × n) *(buildAdjacencyMatrix)*                                             |
| 5-6   | 2 × O(n) *(getStation)*                                                            |
| 7     | O(n × n) *(getWaypoints chama getStation internamente até n vezes)*                |
| 8     | O(w × (n² + n²)) = O(w × n²) *(findPathThroughWaypoints chama dijkstra w+1 vezes)* |
| 9     | O(p × m) *(distanceCalc)*                                                          |
| 10    | 1P                                                                                 |
| 11    | O(p × m) *(displayFullPath chama calcDistanceBetweenStations p-1 vezes)*           |
| 12    | O(GraphUI.Graph_US27) *(não detalhado)*                                            |
| total | O(n² + m × n + n² × w + p × m) = **O(n² × w + m × n + p × m)**                     |