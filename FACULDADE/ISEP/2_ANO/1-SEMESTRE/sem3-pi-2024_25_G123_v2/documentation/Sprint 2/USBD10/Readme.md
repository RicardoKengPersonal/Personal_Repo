### USBD10 As a Product Owner, I want an updated relational data model of the system / logical and physical level).

## 1. Requirements Engineering
###  1.1. User Story Description

As a Product Owner, I want an updated relational data model of the system / logical and physical level).

### 1.2. Customer Specifications and Clarifications
**From the specifications document:**

> We want to have a logical and a physical model of the database.

**From the client clarifications:**

> **Question:** Boa noite, estive a reler uma mensagem antiga do professor cujo título é "Esclarecimentos sobre PI" onde abordava os conceitos da PI para BDDAD.
>Dois deles são os seguintes:
>"10 - Operação de uma ordem de fabrico
>11 - Workstation onde a operação da ordem de fabrico é realizada"
>Em relação a eles é dito "Os pontos 10 e 11, bem como os inputs de uma operação da BOO não são para considerar no modelo de dados do sprint 1.".  Pelo que já entraria para este sprint2.
>Além disso era também anteriormente referido para considerar BOM e BOO listas, que tal já não acontece para este Sprint2.
>Uma vez que o texto do enunciado manteve se, podemos considerar essas as únicas mudanças no modelo para este Sprint2, ou tem mais a qual eu não referi?
>Além disso, na mensagem que referir anteriormente também é dito: "Para já, podemos dizer que o produto é uma variante da família de produtos", algo mudou em relação a isto?
>Obrigado. 

> **Answer:** Dear students, An operation has 1 or more inputs and only one output. The inputs and the product can be components, raw materials, products [sold by the company] 
>or intermediate products [not sold by the company].It is evident that, in this second sprint, each product has a BOO. Whether the product family has a BOO template or not is a 
>question that must be clarified with the customer.
>Best regards,
>Angelo Martins

> **Question:** Boa tarde,Nos datasets muitas vezes aparecem campos id com @ e #. O que quer dizer cada um destes prefixos? (Se é que significam algo e não são apenas formas de distinguir 2 id's)
>Obrigado. 

> **Answer:** Boa tarde,Pode informar-me a linha do ficheiro em que isso acontece? Espero que não esteja a importarr ficheiros XML para o Microsoft Excel...
>Cumprimentos, Ângelo Martins 

> **Question:** Boa tarde,É correto associar as entidades boo e bom com uma entidade intermediária sendo que utilizamos as parts no processo de um boo?
>Obrigado

> **Answer:** Boa tarde, No modelo de dados do sprint 2 não existe a entidade BOM. Garantidamente. A entidade BOO pode existir, ou não. Vai depender do nome que der às entidades.
>Cumprimentos, Ângelo Martins

> 
### 1.3. Acceptance Criteria
* **AC1:** The relational model must be correctly updated according to the new criteria.
* **AC2:** The model must be fully functional.

### 1.4. Found out Dependencies
* n/a

### 1.5 Input and Output Data
* n/a

### 1.6. Other Relevant Remarks
* n/a