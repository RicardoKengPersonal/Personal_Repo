# USBD27 -  Reserve the necessary materials and components to fulfil a given order. 


## 1. Requirements engineering

### 1.1. User story description

As a Production Manager, I want to reserve the necessary materials
and components to fulfil a given order. The reserved materials and components
should be registered in the database, but not automatically deducted from stock.
The reservation should be created only if the whole order can be fulfilled.

### 1.2. Customer specifications and Clarifications

**From the specifications document:**

**From the client clarifications:**
No clarifications were requested.


### 1.3. Acceptance Criteria

* **AC1:** The reservation should be created only if the whole order can be fulfilled.
* **AC2:** The reservation should be registered in the database.
* **AC3:** The reservation should not be automatically deducted from stock.

### 1.4. Found out dependencies

* There is a dependency on "USBD20 - As a Product Owner, I want an updated relational data model at the logical level. All constraints must be in the model."
* There is a dependency on "USBD21 - As a Product Owner, I want to automatically generate the physical level SQL code of the database from the Visual Paradigm model."
* There is a dependency on "USBD22 - As a Product Owner, I want the developed PL/SQL code to be thoroughly tested. The Product Owner will supply some testing data. Other may have to be created by the team."

### 1.5. Input and Output Data

**Input data**

* Order id (idOrder)

**Output data**

* List of all reserved materials and components
* (In)Success Message

### 1.6. Other Relevant Remarks

n/a.