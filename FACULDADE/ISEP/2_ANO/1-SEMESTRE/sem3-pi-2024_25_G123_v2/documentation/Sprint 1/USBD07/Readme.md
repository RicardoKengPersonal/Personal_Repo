# USBD07 - As a Production Manager, I want to know the materials/components to be ordered to fulfill a given production order, including the quantity of each material/component.

## 1. Requirements Engineering

### 1.1. User Story Description

As a Production Manager, I want to know the materials/components to be ordered to fulfill a given production order, including the quantity of each material/component.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

> An order can lead to one or more production orders (there may be more than one
production order for the same item, but different items must have distinct production
orders). When generating production orders, it is essential to consider the BOM (Bill
of Materials) defined in Product Engineering, as this structure outlines the components/raw materials needed for producing the item.

**From the client clarifications:**

n/a.

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

* Production Order

**Output Data:**

* Material/Components necessary to fulfill a certain order and the quantity of each material/component.

### 1.6. Other Relevant Remarks

n/a.