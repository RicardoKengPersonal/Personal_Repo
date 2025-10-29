# USBD28 - As a Production Manager, I want to have a list of all reserved materials and components, their quantity and the ID of the supplier.

## 1. Requirements engineering

### 1.1. User story description

As a Production Manager, I want to have a list of all reserved materials and components, their quantity and the ID of the supplier.

### 1.2. Customer specifications and Clarifications

**From the specifications document:**

**From the client clarifications:**
> **Question:** Bom dia, A USBD28 pede para mostrarmos a lista de partes reservadas bem como a quantidade e o id do supplier. 
> No caso de termos encomendas terminadas, fará sentido mostrar na mesma essa informação, como um histórico das partes reservadas? 
> Ou apenas mostramos as partes reservadas para as encomendas ativas? 
> Outra dúvida, no caso da reserva não ter um supplier associado, mostramos na mesma essa part com o supplier a null? 
> Cumprimentos, Tiago Costa
>
> **Answer:** Bom dia, Vamos por partes:
> 1. "A USBD28 pede para mostrarmos a lista de partes reservadas bem como a quantidade e o id do supplier. "
> Já foi explicado de devia estar "[...] o(s) id do(s) supplier(s)".
> 2. "No caso de termos encomendas terminadas, fará sentido mostrar na mesma essa informação, como um histórico das partes reservadas? Ou apenas mostramos as partes reservadas para as encomendas ativas?"
> Não creio que faça sentido ter reservas de encomendas produzidas. Mas, se o faz feliz, pode restringir às encomendas ainda não produzidas.
> 3. "Outra dúvida, no caso da reserva não ter um supplier associado, mostramos na mesma essa part com o supplier a null?"
> Esta afirmação é um disparate pegado e deixa-me extremamente preocupado. Uma reserva não está associada a um fornecedor, mas a um componente ou material.
> Cumprimentos,Angelo Martins
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

* Order id (idOrder)
* Product id (idProduct)
* Quantity (quantity)
* Reservation Status ID (idReservationStatus)

**Output data**

* List of all reserved materials and components
* (In)Success Message

### 1.6. Other Relevant Remarks

n/a.