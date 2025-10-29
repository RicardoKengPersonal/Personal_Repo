# US243 - List Active Drones By model 

## 1. Requirements Engineering

### 1.1. User Story Description

As a Drone Tech, I want to list active drones of a given model in the inventory.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

n/a.

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
> 4. Versões diferentes serão modelos diferentes, não? Cumprimentos, Angelo Martins
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
>
### 1.3. Business Rules

- Only drones currently in the system can be queried.

- The drone model name must match an existing model (case-insensitive).

- Only drones with the selected DroneStatus are shown.

- DroneStatus.ACTIVE indicates a drone is in service and available in the inventory.

- The search is strictly read-only; no state or data changes are allowed during this operation.

### 1.4 Acceptance Criteria

- The user is prompted to input a drone model name.

- If the model doesn't exist, a clear error message is shown.

- The user is presented with all available drone statuses to choose from.

- The system lists all drones that match both the model and the status.

Drones are displayed with meaningful details (id, name, status, acquisitionDate).

- The process handles invalid input gracefully (e.g., typos, wrong status names).
## Domain
Entity: Drone

    class Drone {

    private String serialNumber;
    private DroneModel model;
    private LocalDate acquisitionDate;
    private DroneStatus status;
    private String removalReason;
    private LocalDate removalDate;

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
        this.status = DroneStatus.ACTIVE;
    }

    public void removeFromInventory(String reason, LocalDate date, DroneStatus newStatus) {
        if (newStatus == DroneStatus.ACTIVE)
            throw new IllegalArgumentException("Cannot remove a drone with ACTIVE status");

        if (this.status != DroneStatus.ACTIVE)
            throw new IllegalArgumentException("Only ACTIVE drones can be removed");

        this.status = newStatus;
        this.removalReason = reason;
        this.removalDate = date;
    }

    public String serialNumber() {
        return serialNumber;
    }

    public DroneModel model() {
        return model;
    }

    public LocalDate acquisitionDate() {
        return acquisitionDate;
    }

    public DroneStatus status() {
        return status;
    }

    public String removalReason() {
        return removalReason;
    }

    public LocalDate removalDate() {
        return removalDate;
    }

    public boolean sameAs(Object other) {
        if (!(other instanceof Drone)) return false;
        return serialNumber.equalsIgnoreCase(((Drone) other).serialNumber);
    }

    public String identity() {
        return serialNumber;
    }
}
Entity: DroneRepository

    public interface DroneRepository extends DomainRepository<DroneID, Drone>{
        Optional<Drone> findByDroneNameIgnoreCase(String name);
        Iterable<Drone> findByModelAndStatus(DroneModel model, DroneStatus status);
    }
Domain: DroneInventoryService

    class DroneInventoryService {

    private final DroneModelRepository categoryRepository;
    private final DroneRepository droneRepository;

    public DroneInventoryService(final DroneModelRepository categoryRepository, final DroneRepository droneRepository) {
        this.categoryRepository = categoryRepository;
        this.droneRepository = droneRepository;
    }

    public void registerDroneModel(final String name, String manufacturer, final float xAxisTolerance, final float yAxisTolerance, final float zAxisTolerance, final Calendar ofDay) {
        final Optional<DroneModel> existing = categoryRepository.findByNameIgnoreCase(name);
        if (existing.isPresent()) {
            throw new IllegalArgumentException("A category with this name already exists.");
        }

        final DroneModel newCategory = new DroneModel(name,manufacturer,xAxisTolerance,yAxisTolerance,zAxisTolerance,ofDay);
        categoryRepository.save(newCategory);
    }

    public void addToInventoryDrone(DroneID id, String name, DroneModel model, LocalDate acquisitionDate, DroneStatus status) {
        final Optional<Drone> existing = droneRepository.findByDroneNameIgnoreCase(name);
        if (existing.isPresent()) {
            throw new IllegalArgumentException("A Drone with this Serial Number already exists.");
        }

        // Create new Drone
        Drone drone = new Drone(id, name, model, acquisitionDate);

        // Set the status
        switch (status) {
            case ACTIVE -> drone.markAsActive();
            case IN_REPAIR -> drone.markAsInRepair();
            case DECOMMISSIONED -> drone.markAsDecommissioned();
        }

        // Save the drone to the repository
        droneRepository.save(drone);  // <---- This was missing
    }

    public void removeFromInventory(String droneName, String reason, LocalDate date, DroneStatus newStatus) {
        final Optional<Drone> existing = droneRepository.findByDroneNameIgnoreCase(droneName);

        if (existing.isEmpty()) {
            throw new IllegalArgumentException("Drone not found.");
        }

        Drone drone = existing.get();
        drone.removeFromInventory(reason, date,newStatus); // This method must exist in Drone
        droneRepository.save(drone); // Save updated status and removal info
    }

    public Optional<DroneModel> findDroneModelByName(final String name) {
        return categoryRepository.findByNameIgnoreCase(name);
    }

    public Optional<Drone> findDroneByName(final String name) {
        return droneRepository.findByDroneNameIgnoreCase(name);
    }

    public Iterable<Drone> findByModelAndStatus(DroneModel model, DroneStatus status) {
        return droneRepository.findByModelAndStatus(model,status);
    }
}

## Application

Entity: ListActiveDronesByModelController

    class ListActiveDronesByModelController {
    private final DroneRepository droneRepo;
    private final DroneModelRepository modelRepo;

    public ListActiveDronesByModelController(DroneRepository droneRepo, DroneModelRepository modelRepo) {
        this.droneRepo = droneRepo;
        this.modelRepo = modelRepo;
    }

    public Optional<DroneModel> findDroneModelByName(String name) {
        return modelRepo.findByNameIgnoreCase(name);
    }

    public Iterable<Drone> findByModelAndStatus(DroneModel model, DroneStatus status) {
        return droneRepo.findByModelAndStatus(model, status);
    }
}

## UI (CLI/Backoffice)
Prompt user for:

    - Drone model name (free text input)

    - Desired status (ACTIVE, IN_REPAIR, DECOMMISSIONED, etc.)

Display:

    - If model doesn't exist → "No model found with that name."

    - If no drones match → "No drones found for model <modelName> with status <status>."

    - If matches found → print details of each matching drone (id, name, status, acquisitionDate)

