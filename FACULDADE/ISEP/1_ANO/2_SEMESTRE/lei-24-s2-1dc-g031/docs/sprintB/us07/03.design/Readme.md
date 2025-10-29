# US07 - Register a vehicle check-up 
## 3. Design - User Story Realization

### 3.1. Rationale

*Note that SSD - Alternative One is adopted.*

| Interaction ID | Question: Which class is responsible for...       | Answer                        | Justification (with patterns)                                                                                 |
|:---------------|:--------------------------------------------------|:------------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1         | 	... interacting with the actor?                  | RegisterMaintenanceUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                         | RegisterMaintenenceController | Controller                                                                                                    |
| Step 2         | 	displaying the form for the actor to input data? | RegisterMaintenanceUI         | IE: is responsible for interacting with the user (Fleet Manager)                                              |
| Step 3	        | 	...instaliating a new vehicle's checkup?         | MaintenanceRepository         | IE                                                                                                            |
| 		             | 	...saving the inputted data?                     | VehicleCheckup                | IE                                                                                                           |
| 		             | 	... validating all data (local validation)       | VehicleCheckup                | IE                                                                                                           |
| 			  		        | 	... validating all data (global validation)?     | MaintenanceRepository         | IE: knows all its vehicle's checkups.                                                                         | 
| 			  		        | 	... saving the registered vehicle checkup?       | MaintenanceRepository         | IE: owns all its vehicle's checkups.                                                                          | 
| Step 4  		     | 	... informing operation success?                 | RegisterMaintenanceUI         | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* VehicleCheckup
* Vehicle

Other software classes (i.e. Pure Fabrication) identified:

* RegisterMaintenanceUI
* RegisterMaintenenceController
* Repositories


## 3.2. Sequence Diagram (SD)

*Note that SSD - Alternative Two is adopted.*

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us07-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![Sequence Diagram - split](svg/us07-sequence-diagram-split.svg)

*Create Maintenance*

![Sequence Diagram - Partial - Get Task Category List](svg/us07-sequence-diagram-partial-create-maintenance.svg)

*Get Maintenance Repository*

![Sequence Diagram - Partial - Get Task Category Object](svg/us07-sequence-diagram-partial-get-vehicle-list.svg)


## 3.3. Class Diagram (CD)

![Class Diagram](svg/us07-class-diagram.svg)