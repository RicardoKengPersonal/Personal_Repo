# US242 - Remove drone from the inventory

## 1. Requirements Engineering

### 1.1. User Story Description

As a Drone Tech, I want to remove a specific drone from the inventory. The reason for removal and the date must be stored.

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

- Only drones currently marked as ACTIVE can be removed from inventory.

- A removal reason and removal date must be provided.

- Upon removal, the drone's status must be updated to either IN_REPAIR or DECOMMISSIONED.

- The status ACTIVE must not be used when removing a drone.

- Drones marked as DECOMMISSIONED or IN_REPAIR are considered out of inventory.

- The system must store the removal reason and date for auditing purposes.

- Duplicate drone names are not allowed; drone name must uniquely identify a drone.

### 1.4 Acceptance Criteria

- User can input the drone name, removal reason, and date.

- System checks if the drone exists and is currently ACTIVE.

- User is prompted to select the new status: IN_REPAIR or DECOMMISSIONED.

- If ACTIVE is selected, the operation is rejected.

- The drone's status, removal reason, and date are updated in the database.

- A success message is shown with updated drone details.

- If the drone does not exist or is not ACTIVE, an error message is shown.

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

Domain: DroneRepository

    public interface DroneRepository {
        Optional<Drone> findBySerialNumber(String serialNumber);
        Drone save(Drone drone);
    }

Domain: DroneInventoryService

    class DroneInventoryService {

    private final DroneRepository droneRepo;

    public DroneInventoryService(DroneRepository droneRepo) {
        this.droneRepo = droneRepo;
    }

    public Drone removeDrone(String serialNumber, String reason, LocalDate removalDate, DroneStatus newStatus) {
        Drone drone = droneRepo.findBySerialNumber(serialNumber)
            .orElseThrow(() -> new IllegalArgumentException("Drone not found: " + serialNumber));

        drone.removeFromInventory(reason, removalDate, newStatus);
        return droneRepo.save(drone);
    }
}

## Application

Entity: DroneRemovalController

    class DroneRemovalController {

    private final AuthorizationService authz;
    private final DroneInventoryService service;

    public DroneRemovalController(AuthorizationService authz, DroneRepository droneRepo) {
        this.authz = authz;
        this.service = new DroneInventoryService(droneRepo);
    }

    public Drone removeDrone(String serialNumber, String reason, LocalDate date, DroneStatus newStatus) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH);
        return service.removeDrone(serialNumber, reason, date, newStatus);
    }
}

## UI (CLI/Backoffice)

Prompt user for:

    Drone name

    Removal reason

    Removal date

    Desired new status (IN_REPAIR or DECOMMISSIONED)

Display:

    Success message with drone info on valid input

    Error message if:

    Drone not found

    Drone is not ACTIVE

    Status is invalid (e.g., ACTIVE)

## Testing

Unit Tests:

    Drone domain logic rejects ACTIVE as new status.

    Removal stores correct reason and date.

    Controller handles non-existent drones gracefully.

Integration Tests:

    Removing a drone updates status, reason, and date in the database.

    UI flow works correctly with valid and invalid inputs.





