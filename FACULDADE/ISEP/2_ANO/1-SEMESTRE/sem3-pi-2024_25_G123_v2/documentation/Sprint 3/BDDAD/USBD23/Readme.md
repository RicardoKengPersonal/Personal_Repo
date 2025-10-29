# USBD23 - As a Production Manager, I want to make sure that the expected execution time of an operation is not greater than the maximum execution time of every workstation type where it may be run. A trigger should be developed to avoid this issue in both insert and update operations.

## 1. Requirements engineering

### 1.1. User story description

As a Production Manager, I want to make sure that the expected execution time of an operation is not greater than the maximum execution time of every workstation type where it may be run. A trigger should be developed to avoid this issue in both insert and update operations.

### 1.2. Customer specifications and Clarifications

**From the specifications document:**

**From the client clarifications:**
> **Question:** Boa tarde, na USBD 23, devido à forma como a nossa base de dados está estruturada, é possível criar uma operação sem 
> que ela esteja associada às suas compatibilidades com os tipos de workstation. 
> Dessa forma, criámos um trigger que compara expectedExecutionTime e maximumExecutionTime quando tentamos criar a compatibilidade entre um tipo de workstation e uma operação. 
> Criámos ainda outro trigger que, após a compatibilidade ser estabelecida, verifica se o valor de expectedExecutionTime for atualizado se 
> o novo valor satisfaz a condição estabelecida na compatibilidade. Gostaria de perguntar se esta solução satisfaz os requisitos.
> 
> **Answer:** Boa tarde, Lamento, mas consegue explicar melhor o texto "devido à forma como a nossa base de dados está estruturada, é possível criar uma operação sem que ela esteja associada às suas compatibilidades com os tipos de workstation."?
> O que diz a seguir deixa-me extremamente assustado, mas posso estar a interpretar mal, uma vez que não percebi a primeira frase. 
> De qualquer forma, usar triggers para resolver problemas de modelação deficiente é mau, muito mau. Inaceitável. 
> Cumprimentos, Angelo Martins
> 
> **Question:** Boa noite, Na nossa base de dados, temos uma tabela para operações e outra para tipos de workstation. A relação entre 
> operações e tipos de workstation é de muitos-para-muitos, o que resulta numa terceira tabela intermediária que armazena as 
> compatibilidades entre operações e tipos de workstation. Os triggers são criados para satisfazer as condições da usbd23.
> 
> **Answer:** Boa tarde, Não sei como conseguem viver sem uma tabela OperationType... mas vocês é que sabem. O ficheiro "Dataset_S3_operations_V01.xml" contém o quê? 
> Mas concordo que são precisos dois triggers, um para a tabela Operations e outro para a que faz a ligação entre OperationType e WorkstationType. 
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

* Operation description 
* Expected Execution Time

**Output data**

* (In)Success Message

### 1.6. Other Relevant Remarks

n/a.