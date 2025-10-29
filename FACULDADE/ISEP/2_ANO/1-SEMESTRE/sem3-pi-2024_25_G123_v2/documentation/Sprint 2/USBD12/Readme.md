#  USBD12 - As a Production Manager, I want to get the list of parts used in a product.

## 1. Requirements Engineering

### 1.1. User Story Description

As a Production Manager, I want to get the list of parts used in a product.

### 1.2. Customer Specifications and Clarifications
**From the specifications document:**

* n/a

**From the client clarifications:**

> **Question:** Boa tarde, Por favor esclareça-me uma dúvida em relação à USBD12: USBD12 As a Production Manager, I want to get the list of parts used in a product.
>Ao pesquisar todas as "product parts" do produto X, caso uma dessas partes seja outro produto (por exemplo Y), devemos mostrar todas as partes desse produto Y também? 
> Como por exemplo:
> Pesquisa do Produto AS12945S22:
> - Parte AS12947S22 - q: 1
> - Parte AS12946S22 - q: 1
>Mostramos as partes para AS12947S22 e AS12946S22 também? Caso a resposta seja sim:
> Mostramos os resultados todos juntos (quantidade total para cada parte)
> Pesquisa do Produto AS12945S22:
> - parte A = total
> - parte B = total 
> - parte C = Total
> (....)
> Parte n = Total (penso que seria desta forma o que pede no enunciado) ou separadamente?

> Pesquisa do Produto AS12945S22:
> - Parte AS12947S22 
> - Parte A - q: 1 
> - Parte B - q: 2 
> - Parte C - q: 1
> - Parte AS12946S22 
> - Parte A - q: 2 
> - Parte F - q: 2 
>Obrigado, Cumprimentos
> 
> **Answer:** Boa tarde, O critério de aceitação da USBD12 diz: "Acceptance criteria: A function should return a cursor with all the product parts and their quantity. The individual components should be included when a part is a subproduct made at the factory."
> É claro que devem ser incluídos todos os Components e RawMaterials de produtos que façam parte da BOO. Note-se que é pretendida uma lista, não uma árvore, e os valores devem ser agregados. Ou seja, um componente não pode aparece mais do que uma vez na lista. 
> Cumprimentos, Angelo Martins
> 
> 
### 1.3. Acceptance Criteria

* **AC1:**  A function should return a cursor with all the product parts and their quantity. The individual components should be included when a part is a subproduct made at the factory.

### 1.4. Found out Dependencies

* There is a dependency on "USBD10 - As a Product Owner, I want an updated relational data model of the system / logical and physical level).", as there needs to be an instantiated physical model.
* There is a dependency on "USBD11 - As a Product Owner, I want to insert the provided data into the system.", as there needs to have inserted data.

### 1.5 Input and Output Data

**Input Data**

* Data provided by the client.

**Output Data**

* A cursor with all the product parts and their quantity.

### 1.6. Other Relevant Remarks

* n/a