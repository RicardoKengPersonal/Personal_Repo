# USBD30 - As a Factory Manager, I want to use/consume a material/component, i.e. to deduct a given amount from the stock. The operation should not be allowed if the remaining stock falls below the currently reserved quantity.

## 1. Requirements engineering

### 1.1. User story description

As a Factory Manager, I want to use/consume a material/component, i.e. to deduct a given amount from the stock. The operation should not be allowed 
if the remaining stock falls below the currently reserved quantity.

### 1.2. Customer specifications and Clarifications

**From the specifications document:**

**From the client clarifications:**

> **Question:** Boa tarde, No enunciado da USBD30 diz pra subtrair material/componente do stock a partir de uma dada quantidade. Esta quantidade é a que está descrita nos materiais reservados ou é introduzida pelo utilizador quando vai usar a funcionalidade? 
> Cumprimentos, Diogo Amorim
> 
> **Answer:** Bom dia, A tradução para Português da USB30 é qualquer coisa como: "USBD30 Enquanto Responsável de Fábrica, desejo utilizar/consumir um material/componente, ou seja, descontar um determinado valor ao stock. A operação não deverá ser permitida se o stock remanescente for inferior à quantidade atualmente reservada."
> Claro que a quantidade a consumir será um argumento da função PL/SQL. Ter em atenção que também não é possível descer abaixo do stock mínimo. 
> Cumprimentos, Angelo Martins
>
### 1.3. Acceptance Criteria

* **AC1:** The results must be accurate.
* **AC2:** The results must be coherent with the inserted data.
* **AC3:** There should be tests created by the team that cover all scenarios.
* **AC4:**: A PL/SQL function must be developed. It may use other functions and/or procedures.

### 1.4. Found out dependencies

* There is a dependency on "USBD20 - As a Product Owner, I want an updated relational data model at the logical level. All constraints must be in the model."
* There is a dependency on "USBD21 - As a Product Owner, I want to automatically generate the physical level SQL code of the database from the Visual Paradigm model."
* There is a dependency on "USBD22 - As a Product Owner, I want the developed PL/SQL code to be thoroughly tested. The Product Owner will supply some testing data. Other may have to be created by the team."


### 1.5. Input and Output Data

**Input data**

* Product ID

**Output data**

* Available stock
* Product ID
* Remaining Stock
* (In)Success Message

### 1.6. Other Relevant Remarks

n/a.