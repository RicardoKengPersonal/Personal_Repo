# US03 - Register a collaborator

## 1. Requirements Engineering

### 1.1. User Story Description

As a Human Resources Manager (HRM), I want to register a collaborator with a job and fundamental characteristics


### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> Every collaborator registration includes crucial details that form a distinctive reference within the 
organizational structure. These details encompass their name, contact information, and designated job position.
Moreover, essential attributes such as skills, qualifications, and experience are documented.

> Access to these collaborator records is limited to authorized Human Resource Managers (HRMs) within the organization until registration is finalized.



**From the client clarifications:**

> **Question:**  Which information is mandatory to insert a collaborator in the program (fundamental characteristics)?
>
> **Answer:** name, birth date, admission date, address, contact info (mobile and email), taxpayer number, ID doc type and respective number

> **Question:**  Does the HRM select the job from a list that we display?

> **Answer:** displaying or not, It's a matter of UX, the dev team should decide about it, but the valid jobs are the ones created within the US02.

> **Question:**  When creating a collaborator with an existing name ... What the system do?
What characteristics are important to success the register?

> **Answer:** It's not common and most improbable to have different individual with same name in the same context, however it’s ID documentation number should be unique for sure.
I believe that question was already answered, name, birthdate, admission date, id doc type, id doc number, contact info (email, mobile), address









### 1.3. Acceptance Criteria

* **AC1:** The system should display a comprehensive form where the collaborator details can be entered, encompassing their 
name, contact information, and job position.
* **AC2:** HRMs should have the capability to designate a particular job role to the collaborator from a predefined list
of available positions within the system.
* **AC3:** HRMs need to be capable of entering essential attributes of the collaborator, such as skills, qualifications,
and experience.
* **AC4:** Upon completion, the system must reliably store the collaborator's information for future reference.
* **AC5:** Data validation mechanisms should guarantee the precision and entirety of entered information.
* **AC6:** Authorized users should have the ability to access and modify collaborator records after they have been saved.
* **AC7:** Collaborators need to possess unique identification within the system to prevent duplication of records.
* **AC8:** After registration, the system should provide confirmation that all the data that you entered has been saved 
accurately.




### 1.4. Found out Dependencies 

* Before effectively and systematically registering employees, as described in this user story (US03), it is necessary
to first create and register jobs, as specified in "US02 - As an HRM, I want to register a job."
The presence of documented job positions is essential for subsequently linking them with collaborators during the 
registration procedure. Therefore, guaranteeing the presence of documented job roles is crucial for streamlining the 
process of employee registration in an organized fashion.
* Also, before registering employees, as described in this user story (US03), it is possible to have registered skills to
a future collaborator, as specified in "US01 - Registers skills that may be appointed to a collaborator."
  
### 1.5 Input and Output Data

**Input Data:**

* Typed data:
  * Collaborator's name
  * Collaborator's contact information
  * Essential attributes such as skills, qualifications, and experience

* Selected data:
    * Job category selected from a predetermined list


**Output Data:**

* List / catalog of current collaborator registrations
* Verification of (un)successful registration


### 1.6. System Sequence Diagram (SSD)
![System Sequence Diagram - Alternative One](svg/us03-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks

* Highlighting the importance of the collaborator registration process, as outlined in User Story US03, it holds a central
position within the organizational structure for human resource management. 
* This process entails gathering vital information, such as the collaborator's name, contact information, assigned job 
position, and key attributes
* To conclude, the system aids in enhancing organizational efficiency and promoting comprehensive personnel management.