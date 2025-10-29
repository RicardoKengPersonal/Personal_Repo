# USBD05 - As a Production Manager, I want to know, for each product, the orders to be delivered (customer, product, quantity, date) within a given time frame.

## 1. Requirements Engineering

### 1.1. User Story Description

As a Production Manager, I want to know, for each product, the orders to be delivered (customer, product, quantity, date) within a given time frame.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

> The Production Manager must be able to retrieve a list of orders for a specific product, filtered by a time frame, showing detailed information about the customers, product, quantity, and delivery date.

**From the client clarifications:**


### 1.3. Acceptance Criteria

* **AC1:** The system must allow the Production Manager to view orders filtered by a time frame and product.
* **AC2:** The system must display the following fields: customer, product, quantity, and delivery date.
* **AC3:** It should be possible to demonstrate the system using data imported from the legacy system to validate the functionality.

### 1.4. Found out Dependencies

* There is a dependency on "USBD03 - As a Product Owner, I want the relational model to be instantiated (physical
    level).", as there needs to be a database to store the information.
* There is a dependeny on "USBDO4 - As a Product Owner, I want to import data from a legacy system and
  deliver it on a spreadsheet."

### 1.5 Input and Output Data

**Input Data:**

* Product ID

**Output Data:**

* List of orders filtered by product and time frame, including:
  * Customer name
  * Product name
  * Quantity ordered
  * Expected delivery date

### 1.6. Other Relevant Remarks

* The system must ensure that orders are properly filtered by the selected time frame and product, and that relevant data is returned.
* Appropriate feedback should be provided to the user if no orders are found within the specified time frame.