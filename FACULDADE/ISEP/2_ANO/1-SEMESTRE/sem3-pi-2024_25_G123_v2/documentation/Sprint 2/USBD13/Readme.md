# USBD13 - As a Production Manager, I want to get a list of operations involved in the production of a product, as well as each workstation type.

## 1. Requirements Engineering

### 1.1. User Story Description

As a Production Manager, I want to get a list of operations involved in the production of a product, as well as each workstation type.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

**From the client clarifications:**

> **Question:** Boa noite, 1) Quando menciona a "lista de operações", refere-se aos ids ex. 100, 103, 112 etc ou aos ids das operation types (5647, 5649, entre outros...)?
> 2) Se o produto a pesquisar tiver a operação 5647, devemos incluir as 3 WTID (A4578, A4588, A4598) ou apenas (A4578, A4588) já que A4598 está nessa operação também mas não tem máquina? 
> Cumprimentos.
> 
> **Answer:** Boa noite, 1. Deviam ler o enunciado: "Acceptance criteria: A function should return a cursor with all product operations. When a part is a subproduct made at the factory, its list of operations should be included. For each operation, the inputs and outputs should be included."
> Os tipos de operações têm entradas e saídas?
> 2. Por mim pode incluir todas, não vale a pena estar a chatear-se com esses detalhes. 
> Cumprimentos, Angelo Martins
> 
> **Question:** Caro Professor, Na descrição do acceptance criteria da user story: "A function should return a cursor with all product operations. When a part is a subproduct made at the factory"
> Gostaria de saber: em que situações as parts poderiam ser subprodutos que não são produzidos na fábrica? 
> Com os meus melhores cumprimentos, Bernardo Barbosa
>
> **Answer:** Boa tarde, Pode assumir que se uma part é um produto usado numa BOO, então este também deve ter uma BOO e é feito na fábrica. 
> Note-se que um produto não é um componente, nem um raw material nem um produto intermédio. 
> Cumprimentos, Angelo Martins
>
> **Question:** Boa noite, Poderia clarificar "When a part is a subproduct made at the factory, its list of operations should be included.", por favor? Não consegui perceber bem o output esperado. 
> Obrigado.
> 
> **Answer:** Bom dia, Veja o exemplo disponibilizado em XML. A BOO do produto AS12945S22 tem dois produtos/subprodutos com entradas da operação 130. Portanto, tem de incluir as BOO destes dois produtos/subprodutos. 
> Cumprimentos, Angelo Martins
> 
### 1.3. Acceptance Criteria

* **AC1:** A function should return a cursor with all product operations. When a part is a subproduct made at the factory, its list of operations should be included. For each operation, the inputs and outputs should be included
### 1.4. Found out Dependencies

* There is a dependency on "USBD10 - As a Product Owner, I want an updated relational data model of the system / logical and physical level).", as there needs to be an instantiated physical model.
* There is a dependency on "USBD11 - As a Product Owner, I want to insert the provided data into the system.", as there needs to have inserted data.

### 1.5 Input and Output Data
**Input Data**
* Data imported from the legacy system (Excel document).

**Output Data**
* A cursor with all product operations.
### 1.6. Other Relevant Remarks
