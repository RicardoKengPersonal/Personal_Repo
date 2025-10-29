# US006 - Create a Task 

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...  | Answer                          | Justification (with patterns)                                                                                 |
|:---------------|:---------------------------------------------|:--------------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?             | AssignSkillUI                   | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                    | AssignSkillController | Controller                                                                                                    |
| 			  		        | 	... instantiating a new Skill?              | SkillsRepository                | Creator: in the DM SkillsRepository has stores multilple Skills.                                              |
| 			  		        | ... knowing the user using the system?       | UserSession                     | IE: cf. A&A component documentation.                                                                          |
| 			  		        | 							                                      | CollaboratorsRepository         | IE: knows/has its own Collaborators                                                                           |
| 			  		        | 							                                      | Collaborator                    | IE: knows its own data (e.g. email)                                                                           |
| Step 2  		     | 							                                      |                                 |                                                                                                               |
| Step 3  		     | 	...knowing the skills to show?              | SkillsRepository                | IE: Skills are stored in a skills repository.                                                                 |
| Step 4  		     | 							                                      |                                 |                                                                                                               |              
| Step 5  		     | 	... validating all data (local validation)? | Skill                           | IE: owns its data.                                                                                            | 
| 			  		        | 	... saving the assigned skill?              | Collaborator                    | IE: owns all its skills.                                                                                      | 
| Step 6  		     | 	... informing operation success?            | AssignSkillUI                   | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Skill
* Collaborator
* SkillsRepository
* CollaboratorsRepository

Other software classes (i.e. Pure Fabrication) identified: 

* AssignSkillUI  
* AssignSkillController

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us04-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![Sequence Diagram - split](svg/us04-sequence-diagram-split.svg)

**Get Collaborator List Partial SD**

![Sequence Diagram - Partial - Get Collaborator List](svg/us04-sequence-diagram-partial-get-collaborator-list.svg)

**Get Skill List**

![Sequence Diagram - Partial - Get Skill List](svg/us04-sequence-diagram-partial-get-skill-list.svg)

**Assign Skill to Collaborator**

![Sequence Diagram - Partial - Assign Skill to Collaborator](svg/us04-sequence-diagram-partial-assign-skill.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us04-class-diagram.svg)