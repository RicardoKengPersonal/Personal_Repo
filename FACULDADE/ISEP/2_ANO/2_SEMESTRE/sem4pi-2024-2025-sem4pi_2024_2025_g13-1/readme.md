# Project Shodrone

## 1. Description of the Project

Shodrone is a company focused on delivering customized multimedia drone shows for corporate and public sector clients.
The project involves the development of a comprehensive back-office system to manage clients, customer requests, a library of figures (drone choreographies), and the simulation and testing of drone shows. 
The system must also support the creation of exclusive figures based on client requests, allowing for scalable, large-scale drone shows.

Key functionalities include:
- Managing client registrations and requests.
- Handling drone figure creation, testing, and validation.
- Supporting a specialized Domain-Specific Language (DSL) for figure descriptions.
- Implementing a 3D simulation system for drone movement to prevent collisions during shows.
- Deploying a scalable system for handling large shows with potentially hundreds of drones.

## 2. Planning and Technical Documentation
You can refer to the detailed project planning and technical documentation in the [Planning and Technical Documentation](docs/planning.md).
This document outlines the project phases, technical requirements, and development process for each sprint.

## 3. How to Build
Building the system requires setting up the project environment and dependencies, and compiling the system components. 
This can be done by following the instructions below:
1. Install Required Dependencies: Ensure the system has the required programming languages (Java for main components, C for simulation) and libraries (e.g., ANTLR for DSL parsing).
2. Clone the Repository:

       git clone https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g13.git
       cd shodrone

3. Compile the Code: For Java components:

       rebuild-all.bat

4. For C simulation components (compile and generate executables):

       gcc -o simulation simulation.c -lpthread

5. Accessing the Database via H2 Console

- Start the server using `start-h2.bat`.
- Open your browser and go to: [http://localhost:8082](http://localhost:8082)
- Fill in the following fields:
  - **Driver Class**: `org.h2.Driver`
  - **JDBC URL**: `jdbc:h2:tcp://localhost/./data/shodrone`
  - **User Name**: `sa`
  - **Password**: (leave blank if not set)

4. Click on **"Connect"**.

5. For Refresh using `refresh-h2.bat`.
6. For Close using `stop-h2.bat`.


## 4. How to Execute Tests

To execute the tests:

1. Unit Tests:
   -  Run Java unit tests using Maven:
   
      
    run-tests.bat

2. Integration Tests:
   - Run integration tests to validate interaction between components, including the system's ability to handle drone simulation and figure validation.

3. Simulation Tests:
   - For testing drone collision detection, you can use the simulation executable created during the build process. Run the simulation and monitor logs for potential collisions.

4. End-to-End Testing:
   - Perform manual testing through the back-office interface to simulate full workflows, from client registration to show simulation.

## 5. How to Run

Run Script

      run-customer.bat

## 6. How to Install/Deploy into Another Machine (or Virtual Machine)

*To Do*

## 7. How to Generate PlantUML Diagrams

To generate plantuml diagrams for documentation execute the script (for the moment, only for linux/unix/macos):

    ./generate-plantuml-diagrams.sh

This script will generate the UML diagrams from the source files and place them in the appropriate output directory.
