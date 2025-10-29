# US03 - As an HRM, I want to assign a team to an agenda entry

## 3. Design - User Story Realization

### 3.1. Rationale

| Interaction ID | Question: Which class is responsible for... | Answer                                 | Justification (with patterns)                                                                                 |
|:---------------|:--------------------------------------------|:---------------------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?             | AgendaGUI                              | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
|                | ... coordinating the US?                    | AgendaController                       | Controller                                                                                                    |
|                | ... having all the repositories?            | Repositories                           | IE: has all the different repositories.                                                                       |
| Step 2         | ... initiating a new AgendaEntry?           | AgendaController                       | Creator (Rule 1), Pure Fabrication: no existing class in Domain Model can initialize a new AgendaEntry.       |
| Step 3         | ... validating the input data?              | AgendaGUI                              | IE: is responsible for user interaction.                                                                      |
| Step 4         | ... knowing all the teams?                  | TeamsRepository                        | IE: knows all its teams.                                                                                      |
| Step 5         | ... displaying the team proposal?           | AgendaGUI                              | IE: is responsible for user interaction.                                                                      |
|                | ... saving the created AgendaEntry?         | AgendaController                       | IE: owns its data.                                                                                            |
| Step 6         | ... informing operation success?            | AgendaGUI                              | IE: is responsible for user interaction.                                                                      |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* AgendaEntry
* Team

Other software classes (i.e. Pure Fabrication) identified:

* AgendaGUI
* AgendaController
* Repositories
* TeamsRepository


## 3.2. Sequence Diagram (SD)

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us23-sequence-diagram-full.svg)


## 3.3. Class Diagram (CD)

![Class Diagram](svg/us23-class-diagram.svg)