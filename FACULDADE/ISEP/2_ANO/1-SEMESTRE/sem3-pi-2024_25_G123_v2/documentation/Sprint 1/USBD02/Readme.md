### USBD02 - As a Product Owner, I want the relational model to be created (logical level).

## 1. Requirements Engineering
###  1.1. User Story Description

As a Product Owner, I want the relational model to be created (logical level), so that the industrial facility’s activities are fully represented, and the system can automatically generate the physical model.

### 1.2. Customer Specifications and Clarifications
**From the specifications document:**

> The relational model must accurately cover the industrial facility’s operations, including production orders, 
> materials management, and other processes described in the provided documentation. It should include all necessary 
> connections and constraints to ensure the integrity of the system.

**From the client clarifications:**

>**Question:** Podemos assumir que os ids sempre vão ter a mesma quantidade de caracteres que têm no excel?
> 
> **Answer:** Claro que não. Os dados fornecidos são de um sistema legacy. Não servem para definir as características 
> do novo sistema.
> 
> **Question:** BOO e BOM são conceitos que precisam de tabela na base de dados?
> 
> **Answer:** Depende.


### 1.3. Acceptance Criteria

* **AC1:** The data model should cover the industrial facility’s activity described in the 
document, as well as any additional requirements resulting from the provided user stories.
* **AC2:** Minimum expected requirement: a complete model in Visual Paradigm, including all connections and constraints,
which allows the physical model script to be automatically generated.
* **AC3:** Minimum requirement above the expected: the presentation of a conceptual
  model developed in Visual Paradigm in addition to the expected level.

### 1.4. Found out Dependencies
* The relational model should use the terms specified in the data dictionary/glossary.
### 1.5 Input and Output Data
**Input Data:**

* Data from the industrial facility’s documentation, including the BOM and other user stories.

  **Output Data:**

* A relational model (logical level) that covers all relevant activities.
* Automatic physical model script generation from the logical model.

### 1.6. Other Relevant Remarks
The model should be continuously updated as new requirements emerge, and it should be validated against the physical 
model to ensure correctness and consistency.