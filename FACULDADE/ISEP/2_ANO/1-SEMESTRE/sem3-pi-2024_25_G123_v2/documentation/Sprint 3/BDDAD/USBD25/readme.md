# USBD25 - As a Production Manager, I want to get a list of a product’s operations necessary for the production plan module.

## 1. Requirements engineering

### 1.1. User story description

As a Production Manager, I want to get a list of a product’s operations necessary for the production plan module.

### 1.2. Customer specifications and Clarifications

**From the specifications document:**

**From the client clarifications:**

> **Question:** "As a Production Manager, I want to get a list of a product’s operations necessary for the production plan module." what is the 
> production plan module supposed to mean, like should the production manager choose a order to see the operations of the product(s) 
> or what should the input be?
> 
> **Answer:** Dear Gabriela, USBD25 is only for groups involved in LAPR3. The production plan module is something developed 
> in LAPR3/ESINF. This US supplies the data needed for the LAPR3 application to call that module. Best regards,

### 1.3. Acceptance Criteria

* **AC1:** The results must be accurate.
* **AC2:** The results must be coherent with the inserted data.
* **AC3:** There should be tests created by the team that cover all scenarios.
* **AC4:** A function should return a cursor with all product operations. When a part is a subproduct made at the factory, its list of operations should be included. For each operation, the execution time, inputs and outputs should be included.

### 1.4. Found out dependencies

* There is a dependency on "USBD20 - As a Product Owner, I want an updated relational data model at the logical level. All constraints must be in the model."
* There is a dependency on "USBD21 - As a Product Owner, I want to automatically generate the physical level SQL code of the database from the Visual Paradigm model."
* There is a dependency on "USBD22 - As a Product Owner, I want the developed PL/SQL code to be thoroughly tested. The Product Owner will supply some testing data. Other may have to be created by the team."


### 1.5. Input and Output Data

**Input data**

* Product id (idProduct)

**Output data**

* Product ID 
* Operation ID 
* Sequence Number
* (In)Success Message

### 1.6. Other Relevant Remarks

n/a.