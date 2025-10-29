# US110 - As Project Manager, I want the team to elaborate a Domain Model using DDD.

## 1. Requirements Engineering

### 1.1. User Story Description

**As** Project Manager,  
**I want** the team to elaborate a domain model using Domain-Driven Design (DDD),  
**So that** the project's business logic and key entities are clearly structured and aligned with the business needs.

---

### 1.2. Customer Specifications and Clarifications

- The domain model must follow the Domain-Driven Design (DDD) principles as covered in the semester’s theoretical and practical classes.
- It must represent the core business concepts related to the problem domain (Shodrone's figure/show management, customer relations, drone types, etc.).
- A supporting glossary of domain terms must be created and maintained to ensure consistent understanding across the team.
- The model should be revised throughout the sprints as the team’s understanding of the domain evolves.

**From the client clarifications:**

> **Question:**
Muito boa tarde caro cliente, 1) Na página 8, no ponto 3.1.2. refere que apenas o CRM Manager regista clientes no sistema, no entanto, na US220, o CRM Collaborator regista também. Ambos podem registar? 2) O customer é um utilizador na Customer APP, nesse sentido, quem faz o login na aplicação é o representante dessa empresa, com um email de acesso da showdrone.com certo?
Ou seja, como representante da empresa, tem o email próprio da sua empresa e como utilizador da aplicação tem o email de utilizador com dominio shodrone. 3) Se a questão 2 é aplicada, o email "próprio" do representante exige que o dominio seja da empresa onde trabalha?
exemplo: representante da empresa "XPTO" o email precisa ser ...@XPTO.com/pt ou será livre? 4) Se um cliente pode ter mais do que um representante e cada representante terá um login na aplicação, como são representantes da mesma empresa, ambos podem ver as proposta associadas a essa empresa ou cada representante só poderá ver propostas que ele próprio solicitou?
Obrigado.
>
> **Answer:**
Boa tarde, 1. Acho que têm de ler melhor a US220. 2. Não acho que faça qualquer sentido atribuir um email da Shodrone a representantes dos clientes. 3. Não é possível validar emails contra o nome do cliente. Um cliente não precisa de ter um domínio próprio e, caso o tenha, não quer dizer que seja igual ao nome, etc. 4. O cliente é a empresas, pelo que parece natural que todos os representantes vejam as propostas da empresa. Mas se fizerem de forma diferente também ninguém se chateia. É um detalhe...
Cumprimentos,
Angelo Martins

> **Question:** Boa noite. O que seria uma sequence? Um conjunto de figuras ordenado com durações específicas para cada figura e intervalos de tempo definidos entre elas?
>
> **Answer:** Bom dia,
Por favor ver a versão atualizada dos requisitos. A sequência foi eliminada do texto.
Cumprimentos,
Angelo Martins


> **Question:** Boa noite,
Gostava de esclarecer os conceitos de "drone's model" (secção 3.1.3) e "drone brand/type" (secção 3.1.5)?
Além disso, quais seriam as implicações destes conceitos no processo de teste e geração dos scripts.
>
> **Answer:** Bom dia,
"Drone model" e "drone type" serão a mesma coisa.
Cumprimentos,
Angelo Martins

> **Question:** Boa noite,
Gostaríamos de esclarecer o seguinte(Referente à informação Figure 1 de eapli.shodrone.integrations.plugins Use cases na secção de Show Management nos use cases "Generate show proposal and send to client" e " Submit show request"):
É necessário que um Show Request tenha associado o colaborador que o criou? Da mesma forma, um Show Proposal deve estar sempre ligado ao CRM Manager que o gerou?
Aguardamos o seu feedback.
Cumprimentos,
Luis Gonzalez
>
> **Answer:** Bom dia,
Normalmente regista-se essa informação. Note-se que a Show Proposal não é gerada por um CRM Manager.
Cumprimentos,
Angelo Martins


> **Question:** Boa noite.
O que seria uma figura no seu entendimento? Seria uma imagem ilustrativa ou uma descrição?
Teria também um conjunto de scripts para cada drone como sugerido na secção 3 "Shodrone may use dozens to hundreds of drones in a single show. Each figure/sequence has a script for each drone involved, i.e. a program routine run by the drone to implement that figure/sequence." certo?
>
> **Answer:** Bom dia,
Por favor consulte a versão v1b do enunciado.
Cumprimentos,
Angelo Martins

> **Question:** Olá, Prof.
Surgiram-nos as seguintes durante o desenvolvimento do modelo de domínio e a leitura do enunciado:
Quais são os "execution status" possíveis para um drone?
Qual é o propósito do "execution status" no relatório de simulação?
Qual a relevância deste relatório para o negócio?
>
> **Answer:** Boa tarde,
O relatório dá-nos o resultado do teste. E claro que isso será crítico para o negócio. Não passa pela cabeça de ninguém enviar a um cliente uma proposta de um show que não passa o teste de funcionamento.
Cumprimentos,
Angelo Martins


> **Question:**
Boa tarde,
É referido que um show request tem um número pedido de drones (tentativa). Este número é o número total de drones ou é necessário perceber número total por tipo/modelo de drone pedido?
Obrigado
>
> **Answer:**
Bom dia,
Faz sentido que seja o número total. Caso contrário, seria indicado que tinha os modelos e o número de drones de cada modelo. Não é?
Cumprimentos,
Angelo Martins


> **Question:**
Boa tarde,
Uma figura pode ter várias versões. De que forma o cliente pretende que seja feito o registo destas diferentes versões?
Cada figura tem o seu indicativo de versão e são vistas como "figuras diferentes" ou todas as versões precisam ficar agregadas à primeira versão dessa figura criada e todas têm o mesmo nome?
Obrigado.
>
> **Answer:**
Bom dia,
Do ponto de vista da Shodrone isso apenas poderá ter reflexos na UI. Por exemplo, ao pesquisar por uma figura poderem aparecer todas as versões dessa mesma figura. Mas isso nada tem a ver com a implementação no backend.
O cliente não tem qualquer opinião sobre o assunto.
Cumprimentos,
Angelo Martins



> **Question:**
Boa tarde,
Um representante pode atender vários clientes, ou cada cliente tem seus próprios representantes, sem compartilhamento entre eles?
>
> **Answer:**
Bom dia,
Simplifiquemos: os representantes não são partilhados entre clientes. Não encontro user stories que apontem noutro sentido.
Cumprimentos,
Angelo Martins

> **Question:**
Boa tarde caro cliente,
Segue-se as dúvidas que surgiram ao meu grupo na leitura do documento:
Qual a informação necessária para a "maintenance" de um drone(data da manutenção, data da próxima manutenção,...);
O que quer dizer com "role" do drone em um show, é algo que tem valores específicos? Se sim quais esses valores;
Qual a informação necessária no "show proposal"?
"Upon acceptance of the show proposal by the client, the show is scheduled by the CRM team. This probably involves some negotiation with the customer. The date and time are stored in the system." A data e tempo neste excerto estão relacionadas com o show, ou seja, a data em que ele irá ser realizado, ou com o show proposal, ou seja, quando este foi aceite?
Cumprimentos,
Rui Lapa Grupo41
>
> **Answer:**
Bom dia,
Antes de mais, quando se fazem perguntas sobre secções específicas de um documento é boa prática indicar a secção.
Em relação à manutenção, normalmente inclui manutenção preventiva e reparação. No caso das reparações, são desplotadas por falhas. A manutenção preventiva é programada.
Quando se envia um equipamento para reparar (devido a falha) é necessário haver uma descrição da falha/problema, não?
Relativamente ao papel de um drone num show, assumindo que se está a referir à secção 3.1.3, não percebi a questão. Cada drone tem o seu papel no show, executando um conjunto de movimentos. Quer atribuir um identificador específico a cada drone no show? Se isso facilitar, não tenho nada contra. Desde que não seja um utilizador do sistema a fazê-lo. Um show pode ter centenas de drones. Teria de ser um processo automático.
Relativamente à show proposal, sinceramente, estava à espera que os grupos apresentassem propostas concretas. Aliás, há user stories para especificar e validar a show proposal.
Quanto à última questão, será mesmo uma questão bem pensada? A data em que a proposta foi aceite deve ser registada no sistema? Óbvio! Passa pela cabeça de alguém não fazer isso?! Aliás, a data de criação de um show request também deve ser registada, etc. Mesmo que não seja parte dos requisitos, registam-se instantes de criação de muitos registos. Os SGBD até têm tipos de dados e mecanismos específicos para criarem registos temporais (timestamp).
Mas o texto refere-se especificamente à data de agendamente do show? Parece-me bastante claro...
Cumprimentos,
Angelo Martins

---

### 1.3. Acceptance Criteria

- **AC1**: The domain model should be done using Domain-Driven Design (DDD).
- **AC2**: The domain model can be developed using any platform or modelling tool, as long as it respects AC1.
- **AC3**: The model must identify and distinguish between Entities, Value Objects, Aggregates, Repositories, and Services.
- **AC4**: The domain model must be stored in the GitHub repository under the `docs` folder and versioned accordingly.
- **AC5**: The domain glossary must be provided in markdown format and include all key business terms used in the domain model.
- **AC6**: The model should be updated if significant changes occur during the development of the system.

---

### 1.4. Found out Dependencies

- Tightly connected to the overall analysis of the system (US101 – Technical constraints).
- Should be aligned with the use cases defined for Shodrone (see section 3.1 of the project requirements).
- The glossary and domain model will support the implementation of features in upcoming sprints.

---

### 1.5. Suggested Tasks

- Review the use cases and extract the main business entities and their relationships.
- Identify aggregate roots and boundaries.
- Model entities, value objects, and services accordingly.
- Create the domain glossary in a `glossary.md` file.
- Store the model diagrams in `docs/domain_model/` with both source and image formats.
- Review and update the domain model as the project progresses.

---

### 1.6. Deliverables

- Domain model diagram (in PlantUML or any other suitable modelling tool).
- Glossary file in markdown (`glossary.md`) describing the domain terms.
- Documentation committed to the GitHub repository in the appropriate folder.

---

### 1.7. Other Relevant Remarks

- The domain model is a **living artefact** and should evolve as the team gains deeper insights into the business domain.
- The team should ensure that all members understand the concepts represented in the domain model.
- The glossary can be reused for functional documentation, testing, and future onboarding.
