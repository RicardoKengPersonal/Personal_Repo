# US24 - As a GSM, I want to Postpone an entry in the Agenda to a specific future date

## 3. Design - User Story Realization

### 3.1. Rationale

| Interaction ID | Question: Which class is responsible for... | Answer                                 | Justification (with patterns)                                                                                 |
|:---------------|:--------------------------------------------|:---------------------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?             | AgendaGUI                              | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
|                | ... coordinating the US?                    | AgendaController                       | Controller                                                                                                    |
|                | ... having all the repositories?            | Repositories                           | IE: has all the different repositories.                                                                       |
| Step 2         | ... initiating a new AgendaEntry?           | AgendaController                       | Creator (Rule 1), Pure Fabrication: no existing class in Domain Model can initialize a new AgendaEntry.       |
| Step 3         | ... validating the input data?              | AgendaGUI                              | IE: is responsible for user interaction.                                                                      |
| Step 4         | ... knowing all the agenda entries?         | AgendaEntriesRepository                | IE: knows all its agenda entries.                                                                             |
| Step 5         | ... displaying the postponed entry?         | AgendaGUI                              | IE: is responsible for user interaction.                                                                      |
|                | ... saving the postponed AgendaEntry?       | AgendaController                       | IE: owns its data.                                                                                            |
| Step 6         | ... informing operation success?            | AgendaGUI                              | IE: is responsible for user interaction.                                                                      |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* AgendaEntry
* GSM

Other software classes (i.e. Pure Fabrication) identified:

* AgendaGUI
* AgendaController
* Repositories
* AgendaEntriesRepository


## 3.2. Sequence Diagram (SD)

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us24-sequence-diagram-full.svg)


## 3.3. Class Diagram (CD)

![Class Diagram](svg/us24-class-diagram.svg)