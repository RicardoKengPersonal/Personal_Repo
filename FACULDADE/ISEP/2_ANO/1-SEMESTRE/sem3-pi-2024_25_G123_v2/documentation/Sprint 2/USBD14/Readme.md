# USBD14 - As a Plant Manager, I want to know which product uses all types of machines available in the factory.

## 1. Requirements Engineering

### 1.1. User Story Description

As a Plant Manager, I want to know which product uses all types of machines available in the factory
### 1.2. Customer Specifications and Clarifications

**From the specifications document:**
**From the client clarifications:**

> **Question:** After reading the answers on the forum im confused in the us 14 do we need to see if a product uses all types of machines or all the types of workstations?
>
> **Answer:** Dear Queiroz, We don't have machines in the project. Only workstations. Best regards
> 
> **Question:** Boa noite, A USBD14 não tem nenhum critério de aceitação? É a única que não tem daí a minha questão. 
> Obrigado.
> 
> **Answer:** Bom dia, Claro que tem: fazer o que é pedido. Já agora, tend sida explicada a arquitetura da solução, parece-me óbvio que tem de implementar uma função PL/SQL. 
> Cumprimentos, Angelo Martins
> 
> **Question:** Relativamente ainda à US14, o que retornar perante a situação de haver mais que um produto que usa todas os tipos de workstations? E se não houver nenhum, retornar que não foi encontrado? (Que penso que é o que vai acontecer visto que não temos dados de nenhum produto que use todos os tipos de workstations).
> Obrigado.
> 
> **Answer:** Boa tarde, Pode retornar todos.Relativamente ao teste da sua função, este deve cobrir as diferentes situações. Não é só o "está vivo". Assim, tem obrigatoriamente de testar o caso em que a função retorna pelo menos um produto. Provavelmente terá de inserir os dados necessários para testar este caso.
> Cumprimentos, Angelo Martins
> 
> **Question:** Boa noite, Neste tópico, tenho uma dúvida em relação a "uses all types of machines available in the factory". Q5478 Teflon application station
> De acordo com os dados fornecidos, este workstation type é usado na operação 5671.No entanto, não há nenhuma workstation que tenha este workstation type. Neste caso, consideramos que Q5478 é um workstation type "disponível" na fábrica ou não?
> Cria confusão pois a operação precisa desse workstation type mas na realidade não há máquinas com esse tipo e, isso até pode influenciar a USBD13 também, pois nessa US pede a lista de operações envolvidas (incluindo workstation types) na produção do produto, sendo que na operação 5671 a workstation type Q5478 não tem máquina. 
> Obrigado
> 
> **Answer:** Bom dia, Peço desculpa pela franqueza, mas vai aí uma confusão! Tem de distinguir entre "Operation" e "OperationType". A "operação" que refere nos dados fornecidos, a "5671", é claramente uma "OperationType". 
> Cumprimentos, Angelo Martins
> 
>
### 1.3. Acceptance Criteria

* **AC1:** The output must be coherent with the data in the system.

### 1.4. Found out Dependencies

* There is a dependency on "USBD10 - As a Product Owner, I want an updated relational data model of the system / logical and physical level).", as there needs to be an instantiated physical model.
* There is a dependency on "USBD11 - As a Product Owner, I want to insert the provided data into the system.", as there needs to have inserted data.

### 1.5 Input and Output Data

**Input Data:**

* Operation_Workstation_Type_Compatibility data
* Bill of Operations data

**Output Data:**

* Product(s) that use all types of machines in a facility 

### 1.6. Other Relevant Remarks