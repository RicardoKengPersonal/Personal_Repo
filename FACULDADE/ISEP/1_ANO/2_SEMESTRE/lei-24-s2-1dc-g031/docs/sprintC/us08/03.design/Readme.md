# US008 - List the vehicles needing the check-up

## 3. Design - User Story Realization

### 3.1. Rationale

*Note that SSD - Alternative One is adopted.*

| Interaction ID                                             | Question: Which class is responsible for...                                                                                   | Answer                      | Justification (with patterns)                                                                                 |
|:-----------------------------------------------------------|:------------------------------------------------------------------------------------------------------------------------------|:----------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1 (requests list of vehicles needing the check-up)  		 | 	... interacting with the actor?                                                                                              | AssignMaintenanceUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		                                                    | 	... coordinating the US?                                                                                                     | AssignMaintenanceController | Controller                                                                                                    |
| 			  		                                                    | ...getting the vehicles list?                                                                                                 | VehicleRepository           | IE: has the data                                                                                              |
|Step 2 (lists the vehicles needing the check-up)	                                        | ...displaying the list and form for input data?	                                                                              | RegisterMaintenanceUI       | Pure Fabrication                                                                                              |

According to the taken rationale, the conceptual classes promoted to software classes are:

* Vehicle


Other software classes (i.e. Pure Fabrication) identified:

*  AssignMaintenanceUI
*  AssignMaintenanceController
*  VehicleRepository


## 3.2. Sequence Diagram (SD)
### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us008-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us008-class-diagram.svg)