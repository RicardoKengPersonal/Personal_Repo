# USBD03 - As a Product Owner, I want the relational model to be instantiated (physical level).

## 1. Requirements Engineering

### 1.1. User Story Description

As a Product Owner, I want the relational model to be instantiated at the physical level, in order to have the database
implemented based on the relational design created at the logical level.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

> The customer wants the database to be physically instantiated from the existing relational model. It should integrate
> seamlessly with the existing systems and be capable of handling the expected workload without performance bottlenecks.
> The model should be generated from Visual Paradigm to ensure centralized management of schema changes.

**From the client clarifications:**

* n/a

### 1.3. Acceptance Criteria

* **AC1:** Must be instantiated on Oracle LiveSQL.
* **AC2:** Minimum expected requirement: automatic generation from Visual Paradigm (centralized change management).

### 1.4. Found out Dependencies

* There is a dependency on "USBD02 - As a Product Owner, I want the relational model to be created (logical level)", as
  there needs to be a relational model to generate the database.

### 1.5 Input and Output Data

**Input Data**
* Relational model developed in Visual Paradigm
**Output Data**
* SQL code automatically generated from the Relational Model previously developed.

### 1.6. Other Relevant Remarks

* n/a