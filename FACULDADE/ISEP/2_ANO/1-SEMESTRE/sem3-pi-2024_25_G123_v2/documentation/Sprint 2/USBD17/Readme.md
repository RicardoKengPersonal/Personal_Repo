# USBD17 - As a Production Manager, I want to register an order in the system

## 1. Requirements Engineering

### 1.1. User Story Description

As a Production Manager, I want to register an order in the system

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

**From the client clarifications:**

> **Question:** Boa tarde, Na USBD17: As a Production Manager, I want to register an order in the system. Acceptance criteria: A function should be used to create the order, and to return the Order ID or an error. An order must be from an active customer and a product in the current line-up. 
> Dúvida:
> Na função de registar uma nova ordem do cliente, é suposto passarmos por parâmetro o conjunto de Produtos que são para vincular a essa ordem que estamos a criar ou devemos de ter duas funções em separado? Uma para criar a ordem e outra para adicionar produtos a essa ordem? 
> Obrigado.
> 
> **Answer:** Boa tarde,Faria sentido criar uma encomenda sem todos os artigos envolvidos? A BD estaria num estado válido? Cumprimentos,
> Angelo Martins
### 1.3. Acceptance Criteria

* **AC1:**  A function should be used to create the order, and to return the Order ID or an error. 
* **AC2:**  An order must be from an active customer and a product in the current line-up.

### 1.4. Found out Dependencies

* There is a dependency on "USBD10 - As a Product Owner, I want an updated relational data model of the system / logical and physical level).", as there needs to be an instantiated physical model.
* There is a dependency on "USBD11 - As a Product Owner, I want to insert the provided data into the system.", as there needs to have inserted data.

### 1.5 Input and Output Data

**Input Data:**

* customer_id
* order_date
* delivery_date
* order_status

**Output Data:**

* (In)Success message.

### 1.6. Other Relevant Remarks

*Assumed customer can order partial and full version of products from the active pool

n/a.