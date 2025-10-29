# USBD15 - As a Plant Floor Manager, I want to register a workstation in the system.

## 1. Requirements Engineering

### 1.1. User Story Description

As a Plant Floor Manager, I want to register a workstation in the system.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**
**From the client clarifications:**

> **Question:** Dear Client, Apart from the acceptance criteria: Function should return success or error. Do we need to do any more registering the workstation? 
> Apart from verifying the attributes are not null, should we apply any specific business rules for each attribute: (Workstation) name and description workstation and machine Type ?
>
> **Answer:** Dear Bernardo, You have to abide to the rules defined by you data model. You can´t register a workstation if you don't have a valid workstation type, do you? 
> Don't forget that you need to use transactions. Best regards, Angelo Martins
>
### 1.3. Acceptance Criteria

* **AC1:** A function should be used to create the workstation and to return success or an error

### 1.4. Found out Dependencies

* There is a dependency on "USBD10 - As a Product Owner, I want an updated relational data model of the system / logical and physical level).", as there needs to be an instantiated physical model.
* There is a dependency on "USBD11 - As a Product Owner, I want to insert the provided data into the system.", as there needs to have inserted data.

### 1.5 Input and Output Data

**Input Data:**

* Workstation type_name
* Workstation description
* Workstation type_id
* Workstation legacy_id

**Output Data:**

* (In)Success message

### 1.6. Other Relevant Remarks

*