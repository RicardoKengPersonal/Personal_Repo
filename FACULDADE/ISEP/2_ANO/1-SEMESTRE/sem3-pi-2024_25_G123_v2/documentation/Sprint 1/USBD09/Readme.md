# USBD09 - As a Plant Floor Manager, I want to get the operations sequence as well as get the respective type of workstation, from a BOO of a given product.

## 1. Requirements Engineering

### 1.1. User Story Description

As a Plant Floor Manager, I want to get the operations sequence as well as get the respective type of workstation, from a BOO of a given product.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

> The Production Manager needs to retrieve a list of workstations that were involved in processing a specific production order. 
> Each workstation can be a machine, robotic unit, or human-operated station, and it is important to know what types of 
> workstations were used for different operations within the order.

**From the client clarifications:**

### 1.3. Acceptance Criteria

* **AC1:** Minimum acceptance criteria: only the User Stories with data allowing their proper functioning will be evaluated.
* **AC2:** Minimum expected requirement: demonstrated with data imported from the legacy system.
* **AC3:** Minimum requirement above the expected: demonstrated with data provided for Sprint 1 evaluation

### 1.4. Found out Dependencies

* There is a dependency on "USBD03 - As a Product Owner, I want the relational model to be instantiated (physical
  level).", as there needs to be a database to store the information.
* There is a dependeny on "USBDO4 - As a Product Owner, I want to import data from a legacy system and
  deliver it on a spreadsheet."

### 1.5 Input and Output Data

**Input Data:**

* Family ID

**Output Data:**

* Operation sequence
* Operation ID
* Operation description
* Workstation Type ID
* Workstation Type Name

### 1.6. Other Relevant Remarks

* The system should ensure that the correct workstations are displayed for each operation within the selected product.
* If no workstations are found for a particular product, the system should provide feedback to the user.