# RCOMP 2024-2025 Project 2 - Planning

*Note: This file includes global technical decisions and details regarding team coordination.*

---

## Index

- [1. Sprint's backlog](#1-sprints-backlog)
- [2. Tasks assignment to team members](#2-tasks-assignment-to-team-members)
- [3. Some information](#3-some-information)
    - [3.1 Analyse a proposal](#31-us370--analyse-a-proposal)
    - [3.2 Accept/reject proposal](#32-us371---acceptreject-proposal)
    - [3.3 Check shows dates](#33-us372---check-shows-dates)
    - [3.4 Get show info](#34-us373---get-show-info)
    - [3.5 Show testing](#35-us376---show-testing)
    - [3.6 Running test](#36-us378---running-test)
    - [3.8 Teamwork organization](#38-teamwork-organization)
- [4. Some decisions](#4-some-decisions)
    - [4.1 Authentication requirement](#41-authentication-requirement)
    - [4.2 Separation of concerns](#42-separation-of-concerns)
    - [4.3 File handling](#43-file-handling)
- [5. Sprint Outputs](#5-sprint-outputs)

---

## 1. Sprint's backlog

| Task  | Description                     |
|-------|--------------------------------|
| T.2.1 | US370 - Analyse a proposal      |
| T.2.2 | US371 - Accept/reject proposal  |
| T.2.3 | US372 - Check shows dates       |
| T.2.4 | US373 - Get show info           |
| T.2.5 | US376 - Show testing            |
| T.2.6 | US378 - Running test            |

---

## 2. Tasks assignment to team members

For this sprint, the tasks are a follow-up of previous sprint’s tasks, thus already assigned. For example, a team member who worked on a structured cabling project in the previous sprint is now assigned the Packet Tracer layer two and three simulation for the same building.

| Task  | Student number | Student name   |
|-------|----------------|----------------|
| T.2.1 | 1230978        | Afonso Sousa   |
| T.2.2 | 1230540        | Maria Branco   |
| T.2.3 | 1230443        | Rodrigo Silva  |
| T.2.4 | 1220849        | Maria Pinho    |
| T.2.5 | 1230443        | Rodrigo Silva  |
| T.2.6 | 1231500        | Ricardo Silva  |

---

## 3. Some information

This sprint focuses on enhancing the Customer App and Show Simulation System by implementing new user stories related to proposal analysis, show management, and drone testing. These features improve user interaction and enable technical teams to simulate and validate show executions.

### Features included:

- **US370 – Analyse a Proposal**: Customer Representatives can download and view a show proposal using a code or link.
- **US371 – Accept/Reject Proposal**: Customers can accept or reject proposals within the app, optionally giving feedback.
- **US372 – Check Shows Dates**: Customers can list their upcoming scheduled shows.
- **US373 – Get Show Info**: Detailed info on scheduled or past shows including drone models, figures, duration, etc.
- **US376 – Show Testing**: Drone Technicians select shows to test in the Show Simulator from the Backoffice Server.
- **US378 – Running Test**: Connect a Remote Drone on a remote network to the Show Simulator for distributed testing.

---

### 3.1 US370 – Analyse a Proposal

[Documentation Folder](docs/us_370/readme.md)

| Step | Actor/Component          | Action                                                    |
|-------|-------------------------|-----------------------------------------------------------|
| 1     | Customer & UI           | Customer requests proposal analysis, enters access code.  |
| 2     | UI → Controller        | UI calls controller with access code.                      |
| 3     | Controller → Service   | Controller delegates to service.                           |
| 4     | Service & ClientSession| Service checks if customer is authenticated.               |
| 5     | Service → Backend Server| Service sends `GET_PROPOSAL <accessCode>` via TCP.         |
| 6     | Service ← Backend Server| Service reads proposal lines until `"---END---"`.          |
| 7     | Service → Controller    | Service returns proposal or error.                          |
| 8     | Controller → UI        | Controller returns response to UI.                          |
| 9     | UI → Customer          | UI shows error or saves proposal to file.                  |
| 10    | UI → Desktop (optional)| UI tries to open the file automatically.                   |
| 11    | Customer               | Customer views the proposal file.                           |

---

### 3.2 US371 – Accept/reject proposal

[Documentation Folder](docs/us_371/readme.md)

| Step | Actor/Component               | Action                                                       |
|-------|------------------------------|--------------------------------------------------------------|
| 1     | Customer & AcceptProposalAction | Customer starts accept/reject proposal flow.                |
| 2     | AcceptProposalUI             | Asks customer for proposal ID until valid.                   |
| 3     | AcceptProposalUI             | Asks customer to accept or reject the proposal.              |
| 4     | AcceptProposalUI → Controller| Accept chosen → calls `acceptProposal(proposalId)`.          |
| 5     | Controller → Service         | Delegates acceptance request to service.                     |
| 6     | Service                     | Opens TCP connection to backend server.                      |
| 7     | Service                     | Sends `"ACCEPT_PROPOSAL <proposalID>"` command.              |
| 8     | Backend                     | Spawns handler thread for TCP connection.                    |
| 9     | Handler                     | Retrieves proposal, request, event details.                  |
| 10    | Handler                     | Updates proposal status to `ACCEPTED_CUSTOMER`.              |
| 11    | Handler                     | Creates new show and saves it.                               |
| 12    | Handler → Service           | Sends show details back to service.                          |
| 13    | Service → Customer          | Notifies customer of success.                                |
| 4     | AcceptProposalUI            | Reject chosen → shows rejection message and exits.           |

---

### 3.3 US372 – Check shows dates

[Documentation Folder](docs/us_372/readme.md)

| Step | Actor/Component            | Action                                                           |
|-------|---------------------------|------------------------------------------------------------------|
| 1     | Customer Representative   | Selects "Check Shows Dates" in UI.                               |
| 2     | CheckShowsDatesUI         | Calls controller method `getAllShows()`.                        |
| 3     | CheckShowsDatesController | Calls service method `listMyScheduledShows()`.                  |
| 4     | CheckShowDatesService     | Opens TCP connection and sends `LIST_SHOWS <email>`.            |
| 5     | CustomerCommandServer     | Accepts TCP connection, spawns handler thread.                  |
| 6     | CustomerCommandHandler    | Queries `ShowRepository` for shows by representative email.     |
| 7     | CustomerCommandHandler    | Retrieves details from each `Show`.                             |
| 8     | CustomerCommandHandler → TCP | Sends show details lines followed by `"END"`.                |
| 9     | CheckShowDatesService     | Receives lines, creates `ShowDTO` for each show.                |
| 10    | CheckShowDatesService     | Filters by status = `SCHEDULED` and date ≥ today, sorts by date.|
| 11    | CheckShowsDatesController | Returns filtered, sorted `ShowDTO` list to UI.                   |
| 12    | CheckShowsDatesUI         | Displays the upcoming scheduled shows.                          |

---

### 3.4 US373 – Get show info

[Documentation Folder](docs/us_373/readme.md)

| Step | Actor/Component          | Action                                                                      |
|-------|-------------------------|-----------------------------------------------------------------------------|
| 1     | Customer                | Requests show details via UI.                                               |
| 2     | GetShowInfoUI           | Calls controller with showId.                                               |
| 3     | GetShowInfoController   | Calls service to get show info by showId.                                  |
| 4     | GetShowInfoService      | Opens TCP socket and sends `GET_SHOW_INFO` with showId and userEmail.       |
| 5     | CustomerCommandHandler  | Handles request, retrieves show aggregate.                                  |
| 6     | ShowRepository          | Returns Show entity by showId.                                              |
| 7     | CustomerCommandHandler  | Extracts show details and related objects (ShowRequest, ShowProposal).      |
| 8     | CustomerCommandHandler  | Retrieves proposal’s figure entries and drone fleet.                        |
| 9     | CustomerCommandHandler  | Sends formatted show data through socket.                                  |
| 10    | GetShowInfoService      | Receives data, maps to `ShowExtendedDTO`.                                  |
| 11    | GetShowInfoController   | Returns DTO to UI.                                                          |
| 12    | GetShowInfoUI           | Displays detailed show information.                                        |

---

### 3.5 US376 – Show testing

[Documentation Folder](docs/us_376/readme.md)

| Step | Actor/Component          | Action                                                                        |
| ---- | ------------------------ | ----------------------------------------------------------------------------- |
| 1    | Customer Representative  | Selects **"Test a Show Proposal"** in the UI.                                 |
| 2    | RunSimulationUI          | Calls `RunSimulationController.listAvailableShowsProposal()`.                 |
| 3    | RunSimulationController  | Calls `ListShowsProposalService.allShowsProposal()`.                          |
| 4    | ListShowsProposalService | Queries repository for proposals with status = `CREATED`.                     |
| 5    | RunSimulationController  | Filters proposals that have a `videoLink`.                                    |
| 6    | RunSimulationUI          | Displays proposals and receives user selection.                               |
| 7    | RunSimulationUI          | Calls `RunSimulationController.runSimulation(selectedProposal)`.              |
| 8    | RunSimulationController  | Calls `RunSimulationService.runSimulation(proposalID)`.                       |
| 9    | RunSimulationService     | Reads `simulator.server.host` from `AppConfig`.                               |
| 10   | RunSimulationService     | Opens TCP connection and sends command `START_SIMULATION_SHOW_PROPOSAL <ID>`. |
| 11   | SimulatorCommandServer   | Accepts connection, spawns `SimulatorCommandHandler` thread.                  |



---

### 3.6 US378 – Running test

[Documentation Folder](docs/us_378/readme.md)

| Step | Actor/Component               | Action                                                                  |
| ---- | ----------------------------- | ----------------------------------------------------------------------- |
| 12   | SimulatorCommandHandler       | Parses command, calls `runSimulation(proposalId)`.                      |
| 13   | SimulatorCommandHandler       | Reads `wsl.simulation.path` from `AppConfig`.                           |
| 14   | SimulatorCommandHandler       | Starts simulation process using `make run` in WSL.                      |
| 15   | SimulatorCommandHandler       | Bridges socket I/O: user ↔ simulation process.                          |
| 16   | C simulation process          | Runs interactively, receives commands from user.                        |
| 17   | SimulatorCommandHandler       | Waits for report file `relatorio_simulacao.txt`.                        |
| 18   | SimulatorCommandHandler       | Reads and saves report in `received_reports/`.                          |
| 19   | SimulatorCommandHandler → TCP | Sends `"SUCCESS"` or `"FAILURE"` to client.                             |
| 20   | RunSimulationService          | Receives response and returns `true` or `false`.                        |
| 21   | RunSimulationController       | If successful, calls `UpdateStatusService.updateStatusToReadyToSend()`. |
| 22   | UpdateStatusService           | Retrieves `ShowProposal`, calls `markAsReadyToTest()`, saves updated.   |
| 23   | RunSimulationUI               | Displays result: success or failure.                                    |


---

### 3.8 Teamwork organization

All members:

- Ensure the program runs smoothly.
- Manage individual user stories assigned previously.
- Document and explain how their user stories have been implemented.

---

## 4. Some decisions

### 4.1 Authentication requirement

The system requires users to be authenticated before analysing a proposal. If unauthenticated, an immediate error is returned.

### 4.2 Separation of concerns

The architecture clearly separates responsibilities:

- `UI` handles user interaction and file operations.
- `Controller` acts as intermediary.
- `Service` manages business logic and server communication.

### 4.3 File handling

Proposals are saved as text files in the system’s temporary directory, named based on the access code for easy retrieval.

Example path:  
`C:\Users\<User>\AppData\Local\Temp\proposal_(number).txt`

File handling logic is managed by the UI layer.

---

## 5. Sprint Outputs

Each team member must produce and commit the following deliverables into their personal sprint folder:

1. **Source Code**
    - Fully implemented features per assigned user stories.
    - All new and updated classes.
    - Proper code documentation and comments.
    - Backend services, controllers, UI components.
    - Communication mechanisms with servers/systems.

2. **Design Documentation**
    - Updated UML diagrams (sequence, class, activity).
    - Architecture documents explaining design changes.
    - User flow charts and interaction diagrams for UI stories.
    - Design decisions and rationale notes.

3. **User Documentation**
    - User guides/manuals for new functionalities.
    - ReadMe updates explaining setup, usage, troubleshooting.

---
