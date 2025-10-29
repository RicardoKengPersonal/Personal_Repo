
| Linha | Pseudocódigo                                                                     |
|-------|----------------------------------------------------------------------------------|
| 1     | buildAdjancencyMatrix(stations: List<Station>, lines: List<Line>)                |
| 2     | path := PathCheckerUI.requestPath()                                              |
| 3     | reader.readCSV(path)                                                             |
| 4     | stations := reader.getStations()                                                 |
| 5     | adjMatrix := buildAdjacencyMatrix(stations, reader.getLines())                   |
| 6     | start := requestStation("Select Start station: ")                                |
| 7     | end := requestStation("Select Start station: ")                                  |
| 8     | waypoints := requestWaypoints(stations, start, end)                              | 
| 9     | fullPath := findPathThroughWaypoints(stations, start, waypoints, end, adjMatrix) |
| 10    | totalDistance := distanceCalc(fullPath, reader.getLines())                       |
| 11    | print("Total distance of the shortest route: %.2f km%n", totalDistance)          |
| 12    | displayFullPath(fullPath, reader.getLines());                                    |
| 13    | GraphUI.Graph(stations, reader.getLines(), fullPath);                            |


# Calculate Total Distance

| Linha | Pseudocódigo                                                                           |
|-------|----------------------------------------------------------------------------------------|
| 0     | distanceCalc(path: List<Station>, lines: List<Line>)                                   |
| 1     | total := 0.0                                                                           |
| 2     | for i := 0 to size(path) - 2                                                           |
| 3     | a := path[i]                                                                           |
| 4     | b := path[i + 1]                                                                       |
| 5     | for line in lines                                                                      |
| 6     | if line.stationA = a e line.stationB = b ou line.stationA = b e line.stationB = a then |
| 7     | total := total + conn.distance                                                         |
| 8     | break                                                                                  |
| 9     | return total                                                                           |

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

| Linha | Pseudocódigo                                 |
|-------|----------------------------------------------|
| 0     | findStationIndex(stations: List, target)     |
| 1     | for i := 0 to stations.size() - 1            |
| 2     | if stations[i] == target then return i       |
| 3     | return -1                                    |

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

| Linha | Pseudocódigo                                                    |
|-------|-----------------------------------------------------------------|
| 0     | showPath(path: List<Station>, lines: List<Line>)                |
| 1     | print "Route Overview:"                                         |
| 2     | distanceSoFar := 0.0                                            |
| 3     | for i := 0 to path.size() - 1                                   |
| 4     |   if i > 0 then                                                 |
| 5     |     segment := calcDistance(path[i-1], path[i], lines)          |
| 6     |     distanceSoFar := distanceSoFar + segment                    |
| 7     |   print (i+1), path[i].name, distanceSoFar                      |
| 8     | print total distance traveled                                   |

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

| Linha | Pseudocódigo                                                                 |
|-------|------------------------------------------------------------------------------|
| 0     | calcDistanceBetweenStations(a: Station, b: Station, connections: List<Line>) |
| 1     | for conn in connections                                                      |
| 2     | if (conn.stationA == a && conn.stationB == b) or vice-versa                  |
| 3     | return conn.getDistance()                                                    |
| 4     | return 0.0                                                                   |

---
| Linha | Operações Realizadas          |
|-------|-------------------------------|
| 1     | mA + mC + mI                  |
| 2     | m × 4C                        |
| 3     | 1R (executado se encontrar)   |
| 4     | 1R (pior caso, não encontrou) |
| total | O(m)                          |

# Dijkstra

| Linha | Pseudocódigo                                                           |
|-------|------------------------------------------------------------------------|
| 0     | dijkstra(stations: List<Station>, start, end, adjMatrix: double[][])   |
| 1     | n := stations.size()                                                   |
| 2     | dijkstraDistances := [∞, ∞, ..., ∞]                                    |
| 3     | dijkstraPrevious := [-1, -1, ..., -1]                                  |
| 4     | visited := [false, false, ..., false]                                  |
| 5     | startIdx := findStationIndex(stations, start)                          |
| 6     | dijkstraDistances[startIdx] := 0                                       |
| 7     | for count := 0 to n - 1                                                |
| 8     | minDist := ∞, u := -1                                                  |
| 9     | for i := 0 to n - 1                                                    |
| 10    | if not visited[i] and dijkstraDistances[i] < minDist then              |
| 11    | minDist := dijkstraDistances[i], u := i                                |
| 12    | if u = -1 then break                                                   |
| 13    | visited[u] := true                                                     |
| 14    | for v := 0 to n - 1                                                    |
| 15    | if not visited[v] and adjMatrix[u][v] ≠ ∞ then                         |
| 16    | alt := dijkstraDistances[u] + adjMatrix[u][v]                          |
| 17    | if alt < dijkstraDistances[v] then                                     |
| 18    | dijkstraDistances[v] := alt, dijkstraPrevious[v] := u                  |
| 19    | path := empty list                                                     |
| 20    | at := findStationIndex(stations, end)                                  |
| 21    | if dijkstraDistances[at] = ∞ then return path                          |
| 22    | while at ≠ -1                                                          |
| 23    | insert stations[at] at start of path                                   |
| 24    | at := dijkstraPrevious[at]                                             |
| 25    | if path[0] == start then return path                                   |
| 26    | else return empty list                                                 |

---
| Linha  | Operações Realizadas                                               |
|--------|--------------------------------------------------------------------|
| 1-4    | 3 × nA                                                             |
| 5-6    | 1 Call + 1A                                                        |
| 7-18   | n × (nC + nC + C + A + (n × (C + Op + C + A + A))) = O(n²)         |
| 19-21  | 2A + 1C + 1Return                                                  |
| 22-24  | Até n × (1A + 1Insert + 1A)                                        |
| 25-26  | 1C + 1Return                                                       |
| total  | O(n²)                                                              |

# Request Station

| Linha | Pseudocódigo                                                |
|-------|-------------------------------------------------------------|
| 0     | requestStation(prompt: String, stations: List<Station>)     |
| 1     | displayStationList(stations)                                |
| 2     | print prompt                                                |
| 3     | while true                                                  |
| 4     |   idx := sc.nextInt() - 1                                   |
| 5     |   if idx in range then break                                |
| 6     |   else print "Invalid selection"                            |
| 7     | sc.nextLine()                                               |
| 8     | return stations[idx]                                        |

---
| Linha | Operações Realizadas            |
|-------|---------------------------------|
| 1     | nA                              |
| 2     | 1P                              |
| 3-6   | C + A + (C + C + P) × k         |
| 7     | 1C                              |
| 8     | 1R                              |
| total | O(k)                            |

---

# Request Waypoints

| Linha | Pseudocódigo                                                        |
|-------|---------------------------------------------------------------------|
| 0     | requestWaypoints(stations: List, start, end)                        |
| 1     | waypoints := empty list                                             |
| 2     | print "Enter number of waypoints:"                                  |
| 3     | n := sc.nextInt()                                                   |
| 4     | sc.nextLine()                                                       |
| 5     | for i := 0 to n - 1                                                 |
| 6     |   do                                                                |
| 7     |     print "Select waypoint i:"                                      |
| 8     |     wp := requestStation("Waypoint:", stations)                     |
| 9     |   while wp == start or wp == end or wp in waypoints                 |
| 10    |   waypoints.add(wp)                                                 |
| 11    | return waypoints                                                    |

---
| Linha | Operações Realizadas                               |
|-------|----------------------------------------------------|
| 1     | 1A                                                 |
| 2-3   | 1P + 1C                                            |
| 4     | 1C                                                 |
| 5-10  | n × (k × (P + O(requestStation) + 2C) + 1A)        |
| 11    | 1R                                                 |
| total | O(n × k)                                           |

# Run (Main Controller Method)

| Linha | Pseudocódigo                                                                     |
|-------|----------------------------------------------------------------------------------|
| 0     | run()                                                                            |
| 1     | path := PathCheckerUI.requestPath()                                              |
| 2     | reader.readCSV(path)                                                             |
| 3     | stations := reader.getStations()                                                 |
| 4     | adjMatrix := buildAdjacencyMatrix(stations, reader.getLines())                   |
| 5     | start := requestStation("START", stations)                                       |
| 6     | end := requestStation("END", stations)                                           |
| 7     | waypoints := requestWaypoints(stations, start, end)                              |
| 8     | fullPath := findPathThroughWaypoints(stations, start, waypoints, end, adjMatrix) |
| 9     | totalDistance := distanceCalc(fullPath, reader.getLines())                       |
| 10    | print totalDistance                                                              |
| 11    | displayFullPath(fullPath, reader.getLines())                                     |
| 12    | GraphUI.Graph_US27(stations, reader.getLines(), fullPath)                        |

---
| Linha | Operações Realizadas                                                               |
|-------|------------------------------------------------------------------------------------|
| 1-2   | 1C + O(readCSV)                                                                    |
| 3     | 1A                                                                                 |
| 4     | O(n² + m × n) *(buildAdjacencyMatrix)*                                             |
| 5-6   | 2 × O(n) *(requestStation)*                                                        |
| 7     | O(n × n) *(requestWaypoints chama requestStation internamente até n vezes)*        |
| 8     | O(w × (n² + n²)) = O(w × n²) *(findPathThroughWaypoints chama dijkstra w+1 vezes)* |
| 9     | O(p × m) *(distanceCalc)*                                                          |
| 10    | 1P                                                                                 |
| 11    | O(p × m) *(displayFullPath chama calcDistanceBetweenStations p-1 vezes)*           |
| 12    | O(GraphUI.Graph_US27) *(não detalhado)*                                            |
| total | O(n² + m × n + n² × w + p × m) = **O(n² × w + m × n + p × m)**                     |