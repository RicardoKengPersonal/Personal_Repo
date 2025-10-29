# USBD18 -  As a Production Manager, I want to deactivate a customer from the system.

## 1. Requirements Engineering

### 1.1. User Story Description

As a Production Manager, I want to deactivate a customer from the system.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

**From the client clarifications:**

> **Question:** Boa tarde, Na USBD18, para desativar um cliente precisamos de verificar se o mesmo tem encomendas por entregar ou não. Para verificar se a encomenda foi entregue, o mais correto seria criar uma tabela de estado da encomenda, ou na função PL/SQL da USBD18 verificar a data de entrega com a data atual?
>Obrigado.
> 
> **Answer:** Boa tarde, Não sei se faz muito sentido confiar que os prazos da encomenda são cumpridos. Mas não vejo isso como um problema relevante. Defina um critério e seja coerente com ele. 
> Cumprimentos, Angelo Martins
>
### 1.3. Acceptance Criteria

* **AC1:** A function should be used to deactivate the customer and return success or an error. 
* **AC2:** A customer with orders that have not yet been delivered/fulfilled cannot be deactivated.

### 1.4. Found out Dependencies

* There is a dependency on "USBD10 - As a Product Owner, I want an updated relational data model of the system / logical and physical level).", as there needs to be an instantiated physical model.
* There is a dependency on "USBD11 - As a Product Owner, I want to insert the provided data into the system.", as there needs to have inserted data.

### 1.5 Input and Output Data

**Input Data:**

* Customer_ID

**Output Data:**

* (In)Success message

### 1.6. Other Relevant Remarks

n/a.