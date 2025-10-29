# USBD06 - As a Production Manager, I want to know the types of workstations used in a given order.

## 1. Requirements Engineering

### 1.1. User Story Description

As a Production Manager, I want to know the types of workstations used in a given order.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

> The Production Manager needs to retrieve a list of workstations that were involved in processing a specific production order. Each workstation can be a machine, robotic unit, or human-operated station, and it is important to know what types of workstations were used for different operations within the order.

**From the client clarifications:**


### 1.3. Acceptance Criteria

* **AC1:** The system must allow the Production Manager to select an order and view the types of workstations involved in fulfilling it.
* **AC2:** The system must display the types of workstations, such as robotic units, automated machines, or human-operated stations.
* **AC3:** The system should retrieve and display the information based on data imported from the legacy system.

### 1.4. Found out Dependencies

* There is a dependency on "USBD03 - As a Product Owner, I want the relational model to be instantiated (physical
  level).", as there needs to be a database to store the information.
* There is a dependeny on "USBDO4 - As a Product Owner, I want to import data from a legacy system and
  deliver it on a spreadsheet."

### 1.5 Input and Output Data

**Input Data:**

* Selected order (Order ID)

**Output Data:**

* List with the workstations used in a given order (including the workstation type_id and the workstation_type_name)

### 1.6. Other Relevant Remarks

* The system should ensure that the correct workstations are displayed for each operation within the selected order.
* If no workstations are found for a particular order, the system should provide feedback to the user.