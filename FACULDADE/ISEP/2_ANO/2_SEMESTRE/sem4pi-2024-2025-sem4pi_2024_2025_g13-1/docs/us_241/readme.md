# US241 - Add drone to the inventory

## 1. Requirements Engineering

### 1.1. User Story Description

As a Drone Tech, I want to add drones of an exisƟng type to inventory. For each drone the serial number has to be stored.

This must also be achieved by a bootstrap process.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

> Shodrone may use dozens to hundreds of drones in a single show. Each figure has a script for each drone involved,
> i.e. a program rouƟne run by the drone to implement that figure
> 
**From the client clarifications:**

> **Question:** Boa tarde,Surgiu me uma dúvida em relação ao estado de um drone. Quando um drone é adicionado ao 
> inventário podemos assumir sempre um estado como, por exemplo, o estado ativo, ou também pode ser de outro estado ao
> adicionar? É suposto o drone tech, ao adicionar o drone ao inventário, além de inserir o serial number do drone, 
> também inserir o estado do drone se não for possível assumir sempre um estado do drone ao adicionar o mesmo no 
> inventário?
> 
> **Answer:** Bom dia, Fará sentido adicionar um drone desactivado ao sistema? Acho que seria perder tempo. Adicionar 
> um drone em manutenção ao sistema seria um problema muito complexo, pois teria que incluir também o processo de 
> manutenção. Esqueça... Cumprimentos, Angelo Martins
> 
> **Question:** Bom dia, noutra pergunta o cliente referiu que era necessário guardar a data de aquisição dos drones. 
> Essa data corresponde à data de registo do drone no sistema?
> 
> **Answer:** Boa tarde, Data de aquisição. Acho que diz tudo, não? Cumprimentos, Angelo Martins
> 
> **Question:** Boa tarde Professor, Peço o esclarecimento das seguintes questões acerca do conceito Drone:
> -Que conceito/atributo identifica um Drone? Ou seja, o que deverá diferenciar uma drone de todos as outros? 
> -Após um Drone ser removido do inventário (US242), poderá o mesmo Drone ser posteriormente adicionado novamente ao 
> inventário? Se sim, é relevante para o domínio registar o motivo e a data, à semelhança da remoção?-Pode esclarecer os 
> conceitos de Drone Model e Drone Type pff? São sinónimos do mesmo conceito no domínio, ou são conceitos independentes? 
> -Poderão existir várias versões do mesmo DroneModel? Que conceito identifica um Drone Model? Obrigado
> 
> **Answer:** Boa tarde,
> 1. O que têm normalmente todos os dispositvos de diferente?
> 2. Não é relevante porque não faz parte dos requisitos.
> 3. Acho que já foi esclarecido anteriormente.
> 4. Versões diferentes serão modelos diferentes, não? 
> Cumprimentos, Angelo Martins
> 
> **Answer:** Boa tarde, Obrigado pela resposta.
> - Número de Série, iremos considerar como o identificador de Drone.
> - Apesar de não fazer parte dos requisitos, foi-nos questionado e pedido que esclarecêssemos com o cliente para 
> retificação do modelo de domínio.
> - Consideremos então o Drone Model e o Drone Type o mesmo conceito.
> - À semelhança do mercado automóvel, no nosso entendimento poderão existir várias versões de um modelo (reedições, 
> consoante o ano por exemplo). Devemos considerar nesse caso então como Drone Models diferentes? Obrigado
> 
> Boa tarde, Acho que devia consultar o fórum antes de fazer questões, porque algumas destas questões já foram 
> abordadas anteriormente no fórum. Há várias discussões sobre Drone Model e Drone Type. Agora, quando pretende ter uma 
> categoria de alguma coisa, a categoria terá de ser única. Pode ser a combinação de várias coisas, mas a categoria 
> será única. Por exemplo, o modelo BMW 320 2018. O seu modelo é a combinação do "modelo" BMW 320 com o ano do 
> modelo específico. Só BMW 320 não lhe diz nada. Nada o impede de usar a mesma abordagem com os drones. Mas se o Drone
> Model não for único então vai ter de o combinar com outra coisa para categorizar o drone. Cumprimentos, Angelo Martins
> 
> **Question:** Boa noite, "As a CRM Collaborator, I want to configure the list of drones (number and model) of a 
> show proposal.". Esta lista corresponde ao número de drones necessários para cada modelo ou aos drones em específico?
> 
> **Answer:** Boa tarde, Se fosse um drone em concreto, bastava indicar o ID dele, não? Se tenho de indicar o 
> modelo e o número de drones... Cumprimentos,Angelo Martins
> 
> **Question:** No enunciado, a única informação associada a um drone model que nos é dita é que um drone model tem uma 
> linguagem de programação associada (este facto é explicitado na US 253). 1- Esta "linguagem de programação" será 
> sempre uma já estabelecida (como C ou Java) ou uma criada pela Shodrone (como a DSL)? É importante á Shodrone saber 
> informação relacionada á linguagem para além do nome/identificação, e se sim, que informação? 2- Existe mais 
> qualquer informação sobre drone models, como um nome ou um número ID?
> 
> **Answer:** Boa tarde,
> 1. Os drones têm linguagens de programação próprias. Acontece que os fabricantes tendem a tentar usar a mesma 
> linguagem em todos os seus modelos. É suposto haver um plugin que é usado para gerar o código do drone a partir da DSL.
> 2. O fabricante do modelo de drone parece-me importante. O nome do modelo também. Cumprimentos, Angelo Martins
>
> **Question:** E relativamente apenas ao drone, que informações devem estar associadas além do estado?
> 
> **Answer:** Boa tarde, Um drone deve ter naturalmente um número de série e um modelo. Normalmente, guarda-se
> também a data de aquisição. Quando for desativado, a data em que isso acontece também é importante. Cumprimentos, 
> Angelo Martins
> 
> **Question:** Bom dia, a desativação de um drone corresponde à sua remoção do inventário?
> 
> **Answer:** Boa tarde, Desactivação é marcar como desactivado. Fico com dúvidas se devia ter passado a BDDAD. 
> Cumprimentos, Angelo Martins
> 
> **Question:** Bom dia, o que eu quis dizer foi que, noutra pergunta, o cliente concordou em haver um estado do drone chamado 
> "removido do inventário", e tava a perguntar se era a mesma coisa que estar desativado.
> 
> **Answer:** Boa tarde, Do ponto de vista do cliente, a implementação não é relevante. O nome que dá aos estados não é
> muito relevante para o cliente. Mas deve dar designações que façam algum sentido. Entre "dasactivado" e "removido do 
> inventário", claro que o primeiro parece fazer mais sentido. Quando colocam questões devem ser claros e objetivos. 
> Ou correm o risco de ficarmos com uma muito má opinião de quem faz a pergunta. Cumprimentos,Angelo Martins
>
> **Question:** Boa Tarde.Surgiram as seguintes dúvidas ao meu grupo ao ler o enunciado:
> - Quais são os estados possíveis que um Drone se pode encontrar? Visto que o mesmo pode ser removido do 
> inventário, estar ativo, em manutenção, isso seriam todos estados que devemos considerar?
> 
> **Answer:** Boa tarde, Esses 3 estados parecem-me bem. Ainda que, tendo em vista que estamos a falar de manutenção, 
> o estado "avariado" também faz sentido. Cumprimentos,Angelo Martins
> 
> **Question:** Boa tarde caro cliente, Segue-se as dúvidas que surgiram ao meu grupo na leitura do documento:
> - Qual a informação necessária para a "maintenance" de um drone(data da manutenção, data da próxima manutenção,...);
> - O que quer dizer com "role" do drone em um show, é algo que tem valores específicos? Se sim quais esses valores;
> - Qual a informação necessária no "show proposal"?
> - "Upon acceptance of the show proposal by the client, the show is scheduled by the CRM team. This probably involves 
> some negotiation with the customer. The date and time are stored in the system." A data e tempo neste excerto estão 
> relacionadas com o show, ou seja, a data em que ele irá ser realizado, ou com o show proposal, ou seja, quando este 
> foi aceite? Cumprimentos, Rui Lapa Grupo41
> 
> **Answer:** Bom dia, Antes de mais, quando se fazem perguntas sobre secções específicas de um documento é boa 
> prática indicar a secção. Em relação à manutenção, normalmente inclui manutenção preventiva e reparação. No caso das 
> reparações, são desplotadas por falhas. A manutenção preventiva é programada. Quando se envia um equipamento para 
> reparar (devido a falha) é necessário haver uma descrição da falha/problema, não? Relativamente ao papel de um drone 
> num show, assumindo que se está a referir à secção 3.1.3, não percebi a questão. Cada drone tem o seu papel no show, 
> executando um conjunto de movimentos. Quer atribuir um identificador específico a cada drone no show? Se isso facilitar, 
> não tenho nada contra. Desde que não seja um utilizador do sistema a fazê-lo. Um show pode ter centenas de drones. 
> Teria de ser um processo automático. Relativamente à show proposal, sinceramente, estava à espera que os grupos 
> apresentassem propostas concretas. Aliás, há user stories para especificar e validar a show proposal. Quanto à última 
> questão, será mesmo uma questão bem pensada? A data em que a proposta foi aceite deve ser registada no sistema? Óbvio! 
> Passa pela cabeça de alguém não fazer isso?! Aliás, a data de criação de um show request também deve ser registada, etc. 
> Mesmo que não seja parte dos requisitos, registam-se instantes de criação de muitos registos. Os SGBD até têm tipos de dados e 
> mecanismos específicos para criarem registos temporais (timestamp). Mas o texto refere-se especificamente à data de 
> agendamente do show? Parece-me bastante claro... Cumprimentos, Angelo Martins
### 1.3. Business Rules

- A Drone must have a unique serial number.

- A Drone must be associated with a valid and existing DroneModel.

- A Drone must be registered as active by default.

- The date of acquisition is mandatory and must reflect the actual acquisition date, not system registration date.

- Only authorized users (e.g., Drone Techs) can add drones to the inventory.

- Drones cannot be added with a status other than active.

### 1.4 Acceptance Criteria

- A drone is successfully added if the serial number is unique and the drone model exists.

- Attempting to add a drone with an already registered serial number fails.

- The drone must appear as active immediately upon creation.

- The acquisition date must be recorded.

- The feature must support batch registration (bootstrap).

- Only authorized users (Drone Tech) can access this operation.

## Domain
Entity: Drone

    class Drone {


    private String serialNumber;

    private DroneModel model;

    private boolean active;

    private LocalDate acquisitionDate;


    public Drone(String serialNumber, DroneModel model, LocalDate acquisitionDate) {
        if (serialNumber == null || serialNumber.trim().isEmpty())
            throw new IllegalArgumentException("Serial number cannot be null or empty");
        if (model == null)
            throw new IllegalArgumentException("Drone model must be provided");
        if (acquisitionDate == null)
            throw new IllegalArgumentException("Acquisition date is required");

        this.serialNumber = serialNumber.trim();
        this.model = model;
        this.acquisitionDate = acquisitionDate;
        this.active = true;
    }

    public String serialNumber() {
        return serialNumber;
    }

    public DroneModel model() {
        return model;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDate acquisitionDate() {
        return acquisitionDate;
    }

    public void deactivate() {
        this.active = false;
    }

    public boolean sameAs(Object other) {
        if (!(other instanceof Drone)) return false;
        return serialNumber.equalsIgnoreCase(((Drone) other).serialNumber);
    }

    public String identity() {
        return serialNumber;
    }
}

Domain: DroneRepository

    public interface DroneRepository {
    Optional<Drone> findBySerialNumber(String serialNumber);
    }


Domain Service: DroneInventoryService

    class DroneRegistrationService {
    
        private final DroneRepository droneRepo;
        private final DroneModelRepository modelRepo;
    
        public DroneRegistrationService(DroneRepository droneRepo, DroneModelRepository modelRepo) {
            this.droneRepo = droneRepo;
            this.modelRepo = modelRepo;
        }
    
        public Drone registerDrone(String serialNumber, String modelName, LocalDate acquisitionDate) {
            if (droneRepo.findBySerialNumber(serialNumber).isPresent()) {
                throw new IllegalArgumentException("A drone with this serial number already exists.");
            }
    
            DroneModel model = modelRepo.findByNameIgnoreCase(modelName)
                .orElseThrow(() -> new IllegalArgumentException("Drone model not found: " + modelName));
    
            Drone drone = new Drone(serialNumber, model, acquisitionDate);
            return droneRepo.save(drone);
        }
    }

## Application

    class DroneRegistrationController {
    private final AuthorizationService authz;
    private final DroneRegistrationService service;
    
        public DroneRegistrationController(AuthorizationService authz, DroneRepository droneRepo, DroneModelRepository modelRepo) {
            this.authz = authz;
            this.service = new DroneRegistrationService(droneRepo, modelRepo);
        }
    
        public void registerDrone(String serialNumber, String modelName, LocalDate acquisitionDate) {
            authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH);
            service.registerDrone(serialNumber, modelName, acquisitionDate);
        }
    }


## UI (CLI/Backoffice)

---

        - Command in Menu:
                - Add Drone to Inventory
        
        - CLI Fields
            - Input:
        
                - Serial Number
        
                - Drone Model Name
        
                - Acquisition Date (yyyy-mm-dd)


## Testing

---

    Unit Tests

        - Register a new drone with a unique serial number -> success.
        
        - Register a drone with an existing serial number -> failure with proper error.
        
        - Register a drone with non-existent model -> failure.
        
        - Drone is created with status active and has an acquisition date -> verified.

    Repository Test

        - Ensure drone with given serial number is persisted.
        
        - Check foreign key relationship with DroneModel.
        
        - Verify drone is retrievable from in-memory DB using DroneRepository.





