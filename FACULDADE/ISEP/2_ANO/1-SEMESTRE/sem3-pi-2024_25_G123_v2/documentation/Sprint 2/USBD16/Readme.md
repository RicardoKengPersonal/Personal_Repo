# USBD16 - As a Production Manager, I want to register a product in the system.

## 1. Requirements Engineering

### 1.1. User Story Description

As a Production Manager, I want to register a product in the system.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

**From the client clarifications:**
> **Question:** Bom dia, Relativamente à USBD16, quais são os dados de preenchimento considerados obrigatórios para a inserção de um produto? 
> Uma vez que este produto tem uma BOO em que cada operação tem um input e output, como é que fazemos a ligação a BOO e BOM no ato de registo?
> 
> **Answer:** Boa tarde, Acho que está redondamente enganado. Uma BOO corresponde a um produto, não é o produto que tem uma BOO!
> Assim sendo, a criação de um produto no sistema não obriga à inserção de uma BOO. 
> Mudando de assunto, a sua afirmação "como é que fazemos a ligação a BOO e BOM no ato de registo?" está fora da realidade. Eu já disse várias vezes que na BD não há qualquer entidade, ou conjunto de entidades, que represente a BOM. Esqueça o sprint 1, que os requisitos mudaram.
> Cumprimentos, Angelo Martins

### 1.3. Acceptance Criteria

* **AC1:** A function should be used to create the product and to return success or an error.


### 1.4. Found out Dependencies

* There is a dependency on "USBD10 - As a Product Owner, I want an updated relational data model of the system / logical and physical level).", as there needs to be an instantiated physical model.
* There is a dependency on "USBD11 - As a Product Owner, I want to insert the provided data into the system.", as there needs to have inserted data.

### 1.5 Input and Output Data

**Input Data:**

* Product Name
* Product Family ID
* Product Description

**Output Data:**

* (In)Success message

### 1.6. Other Relevant Remarks

n/a.