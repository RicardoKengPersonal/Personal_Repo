# Documentation of Algorithms for US14

This document provides a comprehensive explanation of the algorithms implemented to determine maintenance routes for 
railway networks. The core objective is to identify Eulerian or Semi-Eulerian paths in a graph-based 
representation of the railway system.

---

## **Railway Maintenance System**

## **Overview**
This system calculates optimal maintenance routes for railway networks. The goal is to ensure that every railway segment
is traversed exactly once. The system supports both all railway lines and only electrified lines, in accordance with 
the requirements of US14. It includes functionality for graph connectivity checks, Eulerian path detection, and 
route visualization.

-------------
## **Key Features**

### **1. Maintenance Route Calculation**
- Supports maintenance for all railway lines or only electrified lines.
- Identifies Eulerian or Semi-Eulerian paths in the railway network.
- Displays only 2 starting station options for Semi-Eulerian graphs (the ones with odd degrees). When the graph is 
- Eulerian, the programs allows the user to select any station to start the maintenance route.

### **2. Graph Connectivity**
- Validates whether the railway network is fully connected before attempting to compute a route.
- Utilizes the isGraphConnected method.

### **3. Graph Visualization**
- Provides a graphical view of the railway network:

  - Electrified lines are highlighted in turquoise green.

  - Depots are shown in burnt orange.

  - Regular stations are shown in light blue.

  - Terminal stations are shown in golden yellow.

-------------
#### **Classes**

 ### **1. Maintenance**
This class encapsulates the core business logic for computing maintenance routes over a railway network, using Fleury's
Algorithm to identify Eulerian or Semi-Eulerian paths for traversal.

✅ Responsibilities
- Contains the main logic for calculating maintenance routes.
- Checks if the graph is Eulerian or Semi-Eulerian.
- Executes Fleury's Algorithm.
- Interacts with the user to select the starting station.
- Displays the maintenance route.

## 🔑 **Key Methods**

### `execute(RailroadNetwork network, boolean onlyElectrified, InputReader dataReader)`
Main method that executes the maintenance route algorithm.

#### Steps:
1. Retrieves the adjacency matrix of the railway network.
2. Checks if the graph is connected.
3. Calculates the degree of each vertex.
4. Identifies odd-degree vertices.
5. Verifies if the graph is Eulerian or Semi-Eulerian.
6. Allows the user to choose the starting vertex.
7. Executes Fleury’s Algorithm.
8. Displays the route.

---

### `isGraphConnected(int[][] graph)`

**Mathematical Concept**:  
A graph must be **connected** to contain an Eulerian path or circuit.

**What the method does**:  
This method verifies whether there is a **path between every pair of vertices** in the graph, using a helper method
from `Utils`.

**Purpose**:  
Ensures the graph is valid for Eulerian analysis.

---

### `calculateVertexDegrees(int[][] graph, int n)`

**Mathematical Concept**:  
To apply Euler’s Theorem, we need to **calculate the degree** (number of edges) for each vertex.

**What the method does**:  
Iterates through the adjacency matrix to count how many edges are connected to each vertex.

**Purpose**:  
Used to determine whether the graph is Eulerian (all degrees even) or Semi-Eulerian (exactly 2 odd degrees).

---

### `findOddDegreeVertices(int[] degree)`

**Mathematical Concept**:  
Eulerian paths/circuits are classified based on the number of **vertices with odd degree**.

**What the method does**:  
Filters and returns the list of vertices with an odd number of connections.

**Purpose**:  
Used in `checkEulerian()` to determine the graph's classification.

---

### `checkEulerian(List<Integer> oddVertices)`

**Mathematical Concept**:
- **Eulerian Graph**: All vertices have even degree.
- **Semi Eulerian Graph**: Exactly 2 vertices have odd degree.

**What the method does**:  
Returns a boolean indicating if the graph is valid for Fleury’s Algorithm.

**Purpose**:  
Ensures the graph meets the mathematical requirements for solving the problem.

---

### `chooseInitialPoint(List<Integer> oddVertices, int[] degree, List<Station> stations)`
Prompts the user to choose a starting vertex depending on whether the graph is Eulerian or Semi-Eulerian.

**Mathematical Concept**:  
The Eulerian path must start at an **odd-degree vertex** (if any), or **any vertex** if it's Eulerian.

**What the method does**:  
Prompts the user to choose the correct starting station based on the graph's classification.

**Purpose**:  
Aligns with the correct theoretical starting condition of the Eulerian path/circuit.

---

### `buildAdjListFromMatrix(int[][] matrix)`
Converts an adjacency matrix to an adjacency list for easier traversal.

**Mathematical Concept**:  
Fleury's Algorithm is implemented using an **adjacency list** to allow dynamic edge removal and traversal.

**What the method does**:  
Converts the adjacency matrix into a more flexible adjacency list.

**Purpose**:  
Prepares the data structure for efficient traversal and edge manipulation.

---

### `fleuryAlgorithm(List<Integer>[] adj, int start, List<Station> stations, List<String> path)`
Implements Fleury’s Algorithm to build a valid Eulerian path or circuit.

**Mathematical Concept**:  
This is the core of **Fleury’s Algorithm**:
    ***1. Traverse Edges One at a Time***
        We start at a vertex and choose one edge to traverse. At each step, the algorithm only moves through one edge, updating 
        the graph by removing that edge.
    ***2. Remove Each Edge After Traversing It***
        Once we move across an edge, we remove it from the graph.
        This avoids traversing the same edge more than once — preserving the Eulerian property.
    ***3. Continue Until All Edges Are Traversed***
        We continue the process — choosing a valid edge, moving to the next vertex, and removing the edge — until there are no 
        edges left.

Internally, this modifies the graph's structure (adjacency list or matrix), reducing the available edges.

**What the method does**:  
Traverses the graph recursively or iteratively from the starting vertex, constructing the Eulerian path and updating the `path` list.

**Purpose**:  
Implements the **step-by-step traversal** that constructs the solution according to Fleury’s method.

---

### `isNextEdgeValid(int u, int v, List<Integer>[] adj)`

**Mathematical Concept**
This method is grounded in the concept of a bridge in graph theory. A bridge is an edge that, if removed, increases the 
number of connected components in the graph — meaning the graph would no longer be fully connected. 
Fleury’s Algorithm avoids using bridges until no other options are available, ensuring the graph remains traversable.

**What the method does**
This method checks whether an edge between two vertices u and v can be safely traversed without disconnecting the graph.

**Purpose**
It determines if the edge (u, v) is a non-bridge (safe to traverse). If it is the only edge from u, it must be taken.

---

### `isGraphStillConnected(List<Integer>[] adj)`

**Mathematical Concept**:  
These methods simulate edge removals and ensure that connectivity is not lost, which is vital for bridge detection.

**What the methods do**:
- `removeEdge`: Deletes edge from adjacency list.
- `addEdge`: Restores edge.
- `isGraphStillConnected`: Verifies connectivity post-removal.

**Purpose**:  
Used internally by `isNextEdgeValid()` to **simulate and evaluate** the impact of edge traversal.

---

### `visualizePath(List<String> path)`
Displays the computed maintenance path step-by-step.

**Mathematical Concept**:  
Provides a **sequential Eulerian path**, visiting every edge once.

**What the method does**:  
Outputs the full path to the user, step-by-step, optionally showing station names.

**Purpose**:  
Represents the **final result** of Fleury’s Algorithm in a user-friendly format.

---

## 📌 Summary

| Mathematical Principle          | Method(s) Involved                                | Purpose                     |
|---------------------------------|---------------------------------------------------|-----------------------------|
| Graph must be connected         | `isGraphConnected`                                | Validates graph structure   |
| Degree classification           | `calculateVertexDegrees`, `findOddDegreeVertices` | Prepares for Eulerian check |
| Eulerian path/circuit detection | `checkEulerian`                                   | Determines feasibility      |
| Proper starting point           | `chooseInitialPoint`                              | Aligns with graph theory    |
| Edge-by-edge traversal          | `fleuryAlgorithm`                                 | Constructs path             |
| Avoid breaking connectivity     | `isNextEdgeValid`, `isGraphStillConnected`        | Follows Fleury's rule       |
| Edge manipulation               | `removeEdge`, `addEdge`                           | Maintains structure         |
| Result visualization            | `visualizePath`                                   | Communicates solution       |

-------

-------
 ### **RailroadNetwork**
- This class represents the railway network as a graph and provides methods to generate the adjacency matrix and check if a line is electrified.
    
#### **Key Methods**:
- **`getAdjacencyMatrix`**:  Generates the adjacency matrix for the network, filtering for electrified lines if selected.

-------
 ### **4. RailwayDataReader**
 - This class reads railway network data from a CSV file and builds the graph representation.

    #### **Key Methods**:
    - **`getStations`**:Retrieves the list of stations.
    - **`getLines`**:  Retrieves the list of connections between stations.

---
 ### **5. Utils**
- Provides utility methods to check graph connectivity.

---

## **Conclusion**

The implementation of Fleury’s Algorithm in this system demonstrates the powerful synergy between mathematical graph 
theory and software engineering. By modeling the railway network as an undirected graph and carefully applying 
principles from graph connectivity and Eulerian paths, the system ensures that every maintenance route visits all 
required tracks exactly once, when possible.


