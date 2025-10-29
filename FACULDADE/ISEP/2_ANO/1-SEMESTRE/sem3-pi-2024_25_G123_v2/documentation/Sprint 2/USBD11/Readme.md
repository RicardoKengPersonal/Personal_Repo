### USBD11 - As a Product Owner, I want to insert the provided data into the system.

## 1. Requirements Engineering
###  1.1. User Story Description

As a Product Owner, I want to insert the provided data into the system.

### 1.2. Customer Specifications and Clarifications
**From the specifications document:**

* n/a

**From the client clarifications:**

> **Question:** Boa tarde, Nos datasets muitas vezes aparecem campos id com @ e #. O que quer dizer cada um destes prefixos? (Se é que significam algo e não são apenas formas de distinguir 2 id's)
> Obrigado.

> **Answer:** Boa tarde, Pode informar-me a linha do ficheiro em que isso acontece? Espero que não esteja a importarr ficheiros XML para o Microsoft Excel...
>Cumprimentos, Angelo Martins

> **Question:** Olá professor, Reparei que vai adicionar o nome do produto e a sua família ao dataset. Penso que será também importante o Nome do tipo de worksation para que evitemos ter nullables em campos que já tivemos dados no anterior sprint.
> E o Nome da familia do produto.

> **Answer:** Bom dia, O senhor já tem os dados importados no sprint 1 referentes a famílias, tipos de workstation e workstation. Não há dados novos.
>Cumprimentos, Angelo Martins

> **Question:** Boa noite,Gostaria de esclarecer uma dúvida relativa aos novos dados fornecidos nos ficheiros XML, que dizem respeito a parts, operations e à BOO. A minha questão é se posteriormente ainda iremos receber informações adicionais sobre outros aspetos do modelo?
>Por exemplo, identificámos que agora temos novos produtos, mas não nos foram fornecidos quaisquer dados em relação ao seu nome e à sua ProductFamily (exemplo: AS12947S20). Anteriormente, no modelo de dados do meu grupo do 1º sprint, tínhamos assumido que esses atributos seriam obrigatórios (não nulos). Caso não venhamos a receber mais informações, devemos considerar a possibilidade de permitir que esses campos sejam nulos? 
> Obrigado pela atenção!
> 
> **Answer:** Boa tarde, Eu vou atualizar o dataset para incluir os nomes dos produtos e repstiva família.
>Cumprimentos, Angelo Martins

> **Question:** Bom dia. Relativamente ao objetivo da USBD11
> - USBD11 As a Product Owner, I want to insert the provided data into the system. Pelo feedback que temos tido nas aulas, os dados não são para ser inseridos na BD da mesma forma pedida no Sprint 1, isto é, através de um bloco de inserts gerado de forma manual ou de forma automática através de script. 
> - Para além disso, nas USBD15, 16 e 17, pede especificamente para registar informação específica.
>Assim sendo, pode clarificar melhor o seu objetivo? Em que medida é que se diferencia das US 15, 16 e 17?
> 
> **Answer:** Boa tarde, A BD do seu grupo tem de ter os dados do dataset do sprint 2 inseridos. Como não há qualquer US que peça uma função para inserir uma BOO na BD, acho que o mais fácil será usar um ficheiro SQL com os INSERT necessários. 
> A alternativa seria desenvolver funções PL/SQL para o fazer.
> Cumprimentos, Angelo Martins
> 
> 
### 1.3. Acceptance Criteria

* **AC1:** The data must be all inserted correctly into the system.

### 1.4. Found out Dependencies
* There is a dependency on "USBD10 - As a Product Owner, I want an updated relational data model of the system / logical and physical level).", as there needs to be an instantiated physical model.
### 1.5 Input and Output Data
**Input Data:**

* Data provided by the client.

### 1.6. Other Relevant Remarks
The model should be continuously updated as new requirements emerge, and it should be validated against the physical 
model to ensure correctness and consistency.