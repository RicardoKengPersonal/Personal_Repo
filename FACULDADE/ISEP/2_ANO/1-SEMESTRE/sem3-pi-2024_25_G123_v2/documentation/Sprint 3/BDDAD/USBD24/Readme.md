# USBD24 - As a Production Manager, I don’t want it to be possible to add a product as an input in its own BOO. A trigger should be developed to avoid such circular references. This doesn’t apply to the BOOs of subproducts.

## 1. Requirements engineering

### 1.1. User story description

As a Production Manager, I don’t want it to be possible to add a product as an input in its own BOO. A trigger should be developed to avoid such circular references. This doesn’t apply to the BOOs of subproducts.

### 1.2. Customer specifications and Clarifications

**From the specifications document:**

**From the client clarifications:**

> **Question:** Boa noite professor Ângelo, Pesquisei as perguntas já respondidas e peço desculpa se já respondeu a algo relacionado. 
> Na USBD24 temos de desenvolver um trigger para evitar que a BOO de um produto tenha como InputPart o próprio produto (para evitar referências circulares), a minha dúvida reside na última frase do enunciado em que se afirma que este trigger não se aplica a subprodutos. Considerando a USBD13 do sprint 2, se o trigger não se aplicar subprodutos vão ocorrer referências circulares nas BOO's dos subprodutos e nos produtos que os incluírem. 
> Pode por favor confirmar que é realmente este o critério ou corrigir o meu entendimento por favor? 
> Desde já agradeço a sua atenção. Festas felizes para todos!
> 
> **Answer:** Boa tarde, Na verdade, a USBD24 restringe o campo de aplicação do trigger à árvore direta do produto porque a aplicação às árvores dos subprodutos seria inútil e disparatada. É um problema que não se consegue resolver com um trigger. 
> Cumprimentos, Angelo Martins

### 1.3. Acceptance Criteria

* **AC1:** The results must be accurate.
* **AC2:** The results must be coherent with the inserted data.
* **AC3:** There should be tests created by the team that cover all scenarios.

### 1.4. Found out dependencies

* There is a dependency on "USBD20 - As a Product Owner, I want an updated relational data model at the logical level. All constraints must be in the model."
* There is a dependency on "USBD21 - As a Product Owner, I want to automatically generate the physical level SQL code of the database from the Visual Paradigm model."
* There is a dependency on "USBD22 - As a Product Owner, I want the developed PL/SQL code to be thoroughly tested. The Product Owner will supply some testing data. Other may have to be created by the team."

### 1.5. Input and Output Data

**Input data**

* Input ID (input)
* Input ID (inputProduct)
* Product ID
* Quantity

**Output data**

* (In)Success Message

### 1.6. Other Relevant Remarks

n/a.