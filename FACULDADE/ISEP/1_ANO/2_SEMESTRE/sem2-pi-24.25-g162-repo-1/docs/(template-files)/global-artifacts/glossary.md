# Glossary

---

## 1. Project & Process Terms

| **_TEA_** (EN)                   | **_TEA_** (PT) | **_Description_** (EN)                                                                                            |
|:---------------------------------|:---------------|:------------------------------------------------------------------------------------------------------------------|
| **Acceptance Criteria (AC)**     |                | Conditions that a software product must satisfy to be accepted by a user or system.                               |
| **Backlog**                      |                | A prioritized list of tasks/features (user stories) to be implemented.                                            |
| **MVP (Minimal Viable Product)** |                | A version of the software that includes only essential features, developed iteratively across sprints.            |
| **Scrum Board**                  |                | A digital or physical board used to visualize and manage tasks in a Scrum workflow.                               |
| **Scrum Master**                 |                | A team member responsible for facilitating the Scrum process, ensuring productive and organized sprint execution. |
| **Sprint**                       |                | A fixed time period (4 weeks) during which specific project work is planned, developed, and delivered.            |
| **User Story (US)**              |                | A brief, simple description of a software feature from the perspective of the end user.                           |

---

## 2. Simulation Domain Concepts

| **_TEA_** (EN)      | **_TEA_** (PT) | **_Description_** (EN)                                                                                                                |
|:--------------------|:---------------|:--------------------------------------------------------------------------------------------------------------------------------------|
| **Carriage**        |                | Attached to locomotives; used to transport specific cargo types (e.g., coal, passengers).                                             |
| **Cargo**           |                | Goods (mail, passengers, coal, etc.) that are transported via the railway system.                                                     |
| **City**            |                | A map element with a name, coordinates, and housing blocks; produces/consumes passengers, mail, and final products.                   |
| **Depot**           |                | Smallest station type, cheapest, 3x3 radius.                                                                                          |
| **Electrification** |                | Property of a railway line allowing electric locomotives to operate.                                                                  |
| **Industry**        |                | A map element classified into Primary, Transforming, or Mixed (e.g., Coal Mine, Steel Mill, Port), which handles resources and cargo. |
| **Line**            |                | A railway connection between two stations. Can be electrified and single/double.                                                      |
| **Locomotive**      |                | Provides traction; types include steam, diesel, and electric.                                                                         |
| **Map Editor**      |                | Tool to create maps by placing elements like cities and industries with specific coordinates.                                         |
| **Player**          |                | A user who interacts with the simulator, builds railways, and manages trains.                                                         |
| **Product Owner**   |                | A representative of the organization responsible for defining and validating product requirements.                                    |
| **Railway Line**    |                | Connection between stations. Properties: single/double track, electrified or not, length.                                             |
| **Route**           |                | A list of stations that a train will follow to pick up and deliver cargo.                                                             |
| **Scenario**        |                | A set of rules and context (e.g., time period, available technologies, historical events) defining simulation constraints.            |
| **Station**         |                | Infrastructure where cargo/passengers are loaded/unloaded. Types: Depot, Station, Terminal.                                           |
| **Station Upgrade** |                | Additional buildings (e.g., Post Office, Hotel, Silo) that improve station functionality.                                             |
| **Train**           |                | A locomotive (engine) plus carriages that move cargo/passengers between stations.                                                     |

---

## 3. Technical & Statistical Tools

| **_TEA_** (EN)                      | **_TEA_** (PT) | **_Description_** (EN)                                                                                                   |
|:------------------------------------|:---------------|:-------------------------------------------------------------------------------------------------------------------------|
| **Boxplot**                         |                | A graphical method to represent distribution (used for passenger arrivals in stations).                                  |
| **GraphStream**                     |                | A visualization library used for representing graph-like railway networks.                                               |
| **Graphviz**                        |                | A tool used for visualizing graph data structures.                                                                       |
| **Histogram**                       |                | A visual distribution chart for a single variable (e.g., cargo arrival counts).                                          |
| **JaCoCo**                          |                | A tool to measure test code coverage in Java projects.                                                                   |
| **Java**                            |                | The programming language used to implement the simulator.                                                                |
| **Jupyter Notebook**                |                | A development environment used for creating and sharing documents that contain live code, equations, and visualizations. |
| **JUnit 5**                         |                | A framework for writing and running unit tests in Java.                                                                  |
| **KPI (Key Performance Indicator)** |                | Quantitative measures used to evaluate the performance of the railway network.                                           |
| **Object Serialization**            |                | Java mechanism to persist data between program runs.                                                                     |
| **Password Requirements**           |                | All users must log in using a password with 7 alphanumeric characters, including 3 capital letters and 2 digits.         |
| **Pie Chart**                       |                | Circular statistical graphic, used here for train distribution and mail/passenger allocation.                            |
| **SVG**                             |                | A vector image format used for saving diagrams and figures in the project.                                               |
| **TDD (Test-Driven Development)**   |                | A software development approach where tests are written before the code.                                                 |
                                            
