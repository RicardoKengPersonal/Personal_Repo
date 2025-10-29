# US240 - Drone Model Creation

## 1. Requirements Engineering

### 1.1. User Story Description

As a Drone Tech, I want to create a drone model in the system.

This must also be achieved by a bootstrap process. A drone model includes its behaviour under wind as a tolerance of 
the drone’s posiƟon (x, y, z) as a funcƟon of the wind speed (x, y, z). In a first crude approach, this can be done in 
steps. Here is an example in a given direcƟon (wind in m/s):
 - wind <= 5 -> 0,
 - 5 < wind <= 7 -> 0.3 m,
 - 7 < wind <= 10 -> 0.5 m,
 - 10 < wind <= 15, 0.8 m,
 - 15 < wind – not safe to fly.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

> Shodrone uses a special tool created in-house to design figures and shows and wants it to generate a figure/show 
description in a high-level DSL. This neutral description should be used in order to avoid vendor lock in and to be 
scalable, i.e. to allow its use with different number of drones. To be tested and run, the actual code for each drone 
must be generated from the high-level descripƟon (given the drone’s model and its role in the show).

> Each element of a figure, i.e. a geometric figure or a 3D bitmap, is implemented using a drone type. Please note that
the same drone type may be used by more than one element. To generate the drones’ code for simulaƟng a figure, one has 
to supply the actual drone model for each drone type in the figure. The same model can be supplied for all drone 
types, i.e. use a single drone model in the simulation. One must not forget that, currently, all figures in a show use 
the same drone set. No drone can be let out. The mapping of the drone types in the figure to drone models used in the 
simulaƟon is always a subjective function. Nevertheless, not all drone models have the same capabiliƟes and an error 
will be generated if an unsupported feature is used when generaƟng a drone’s code. 
 

**From the client clarifications:**

> **Question:** Boa tarde, Na criação de um drone model, na parte de adicionar a tolerância ao vento, podemos 
> assumir um número mínimo de intervalos a existir para a criação? Muito obrigado, Gabriel Sotto Mayor
> 
> **Answer:** Boa tarde, Não percebi. Podia explicar melhor? Obrigado, Angelo Martins
> 
> **Question:** Boa tarde, Por exemplo, se existir apenas um intervalo relativamente à tolerância do vento(e.g., vento > 
> 5 — não é seguro voar), isso é válido ou será necessário definir um número mínimo para evitar estes casos? Obrigado, 
> Gabriel Sotto Mayor
> 
> **Answer:** Boa tarde,
> 1. O exemplo do documento refere-se a uma dada direção. Pode haver valores para 3 direções (x, y, z).
> 2. Como cliente, não tenho nada contra o o exemplo que dá, mas acredito que vai ter alguns desafios quando 
> implementar o simulador. O meu conselho é que não invente. Cumprimentos, Angelo Martins
>
> **Question:** Boa tarde, dois drone models podem ter o mesmo nome? Se sim, podem ter o mesmo nome e o mesmo fabricante?
> 
> **Answer:** Bom dia, Um modelo terá um designação, que provavelmente será única. Pode incluir a versão no nome. 
> Cumprimentos, Angelo Martins
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
> **Question:** Boa tarde, Obrigado pela resposta.
> - Número de Série, iremos considerar como o identificador de Drone.
> - Apesar de não fazer parte dos requisitos, foi-nos questionado e pedido que esclarecêssemos com o cliente para 
> retificação do modelo de domínio.
> - Consideremos então o Drone Model e o Drone Type o mesmo conceito.
> - À semelhança do mercado automóvel, no nosso entendimento poderão existir várias versões de um modelo (reedições, 
> consoante o ano por exemplo). Devemos considerar nesse caso então como Drone Models diferentes? Obrigado
> 
> **Answer:** Boa tarde, Acho que devia consultar o fórum antes de fazer questões, porque algumas destas questões já 
> foram abordadas anteriormente no fórum. Há várias discussões sobre Drone Model e Drone Type. Agora, quando pretende ter 
> uma categoria de alguma coisa, a categoria terá de ser única. Pode ser a combinação de várias coisas, mas a categoria 
> será única. Por exemplo, o modelo BMW 320 2018. O seu modelo é a combinação do "modelo" BMW 320 com o ano do modelo 
> específico. Só BMW 320 não lhe diz nada. Nada o impede de usar a mesma abordagem com os drones. Mas se o Drone Model 
> não for único então vai ter de o combinar com outra coisa para categorizar o drone. Cumprimentos, Angelo Martins
> 
> **Quesion:** No enunciado, a única informação associada a um drone model que nos é dita é que um drone model tem uma 
> linguagem de programação associada (este facto é explicitado na US 253). 
> 1- Esta "linguagem de programação" será sempre uma já estabelecida (como C ou Java) ou uma criada pela Shodrone 
> (como a DSL)? É importante á Shodrone saber informação relacionada á linguagem para além do nome/identificação, e se sim, 
> que informação? 2- Existe mais qualquer informação sobre drone models, como um nome ou um número ID?
> 
> **Answer:** Boa tarde, 1. Os drones têm linguagens de programação próprias. Acontece que os fabricantes tendem a 
> tentar usar a mesma linguagem em todos os seus modelos. É suposto haver um plugin que é usado para gerar o código do 
> drone a partir da DSL. 
> 2. O fabricante do modelo de drone parece-me importante. O nome do modelo também. Cumprimentos, Angelo Martins
> 
> **Question:** E relativamente apenas ao drone, que informações devem estar associadas além do estado?
> 
> **Answer:** Boa tarde, Um drone deve ter naturalmente um número de série e um modelo. Normalmente, guarda-se também a 
> data de aquisição. Quando for desativado, a data em que isso acontece também é importante. Cumprimentos, Angelo Martins
> 
> **Question:** Bom dia, a desativação de um drone corresponde à sua remoção do inventário?
> 
> **Answer:** Boa tarde, Desactivação é marcar como desactivado. Fico com dúvidas se devia ter passado a BDDAD. Cumprimentos, 
> Angelo Martins
> 
> **Question:** Bom dia, o que eu quis dizer foi que, noutra pergunta, o cliente concordou em haver um estado do drone chamado 
> "removido do inventário", e tava a perguntar se era a mesma coisa que estar desativado.
> 
> **Answer:** Boa tarde, Do ponto de vista do cliente, a implementação não é relevante. O nome que dá aos estados não é 
> muito relevante para o cliente. Mas deve dar designações que façam algum sentido. Entre "dasactivado" e "removido do 
> inventário", claro que o primeiro parece fazer mais sentido. Quando colocam questões devem ser claros e objetivos. 
> Ou correm o risco de ficarmos com uma muito má opinião de quem faz a pergunta. Cumprimentos, Angelo Martins
> 
> **Question:** Boa noite, Gostava de esclarecer os conceitos de "drone's model" (secção 3.1.3) e "drone brand/type" 
> (secção 3.1.5)? Além disso, quais seriam as implicações destes conceitos no processo de teste e geração dos scripts.
> 
> **Answer:** Bom dia, "Drone model" e "drone type" serão a mesma coisa. Cumprimentos, Angelo Martins
> 
> **Question:** Boa noite, professor. Peço desculpa pelo incómodo, mas no enunciado (pág. 9) está escrito o seguinte:
> "To generate the drone's code for a figure, one has to supply the actual drone model for each element. The same model 
> can be supplied for all drone types, i.e., use a single drone model in the show."Então só para confirmar "drone type" 
> e "drone model" são mesma coisa? Obrigada pela atenção!
> 
> **Answer:** Bom dia, Os termos usados pela empresa podem ter diferentes significados em contextos distintos. 
> No caso das figuras, a cada drone type da figura temos de fazer corresponder um drone model. É assim que vai poder 
> gerar o código para os drones utilizados. Cumprimentos, Angelo Martins
> 
> **Question:** Boa Tarde, Relativamente a esta resposta comparativamente à anterior, queria perceber se existe uma 
> contradição. Então o Drone Type e o Drone Model não serão a mesmo coisa, certo? Pelo meu entender, dentro de cada 
> Drone Model, vai existir um Drone Type. Por exemplo, um Drone poderá ter um modelo e dentro desse modelo várias 
> funcões. Estou correto ? Obrigado e Cumprimentos.
> 
> **Answer:** Boa tarde, "Pelo meu entender, dentro de cada Drone Model, vai existir um Drone Type. Por exemplo,
> um Drone poderá ter um modelo e dentro desse modelo várias funcões. Estou correto ?"Acho que discordo totalmente. 
> Está a baralhar tudo. Mais uma vez, no caso das figuras, a cada drone type da figura temos de fazer corresponder 
> um drone model. É assim que vai poder gerar o código para os drones utilizados. Cumprimentos, Angelo Martins
> 
> **Question:** Bom dia, como é que os modelos de drones são identificados no sistema, ou seja, quando se pesquisa 
> por um modelo por exemplo, pesquisa-se pelo nome ou outra coisa?
>
> **Answer:** Boa tarde, A pesquisa pode ser por designação do modelo (mesmo aprcial), fabricante, etc. Cumprimentos, 
> Angelo Martins
> 
> **Question:** No enunciado, na secção 3.1.3 na página 9, é descrito que cada elemento de uma figura é implementado 
> ao usar um drone type. A equipa está a assumir, devido ao frisamento desta secção, que um elemento só pode usar 
> um drone type (mas se isto estiver errado, por favor corrija-me). Também é explicado que vários elementos podem 
> implementar o mesmo drone type, e que um drone model pode ser usado para vários drone types (se possível). A 
> pergunta da equipa é, o cliente acha necessário considerar a possibilidade de, numa situação onde vários elementos 
> usam o mesmo drone type, usar vários drone models também? Ou todos os elementos deveriam usar o mesmo drone model 
> se usarem o mesmo drone type?
> 
> **Answer:** Bom dia, Fiquei completamente baralhado! :-( Vamos lá ver se consigo dar uma resposta útil. Figura, descrita 
> usando uma DSL, tem vários componentes, sendo que a cada um é atribuído um tipo de drone (drone type). É uma 
> classificação apenas interna à figura, algo como TypeA, TypeB, TypeC, etc. A certa altura, é preciso simular a figura 
> para ver se é exequível. Então, será necessário indicar o mapeamento dos tipos de drones da figura nos drones que 
> vamos usar na simulação. Se for usado apenas um modelo na simulação, então todos os tipos de drones da figura mapeam 
> no mesmo modelo. Se forem usados vários modelos na simulação, então será necessário indicar a correspondência entre 
> tipos de drones na figura e modelos de drones na simulação. O mapeamento é uma função matemática válida e sobrejetiva. 
> Cumprimentos,Angelo Martins
> 
> **Question:** "Figura, descrita usando uma DSL, tem vários componentes, sendo que a cada um é atribuído um tipo de 
> drone (drone type)"Exatamente, e é possível vários componentes usarem o mesmo tipo de drone (ou pelo menos foi o 
> que a equipa assumiu: se isto estiver errado, por favor corrija-me)"Então, será necessário indicar o mapeamento dos 
> tipos de drones da figura nos drones que vamos usar na simulação" E na situação onde vários componentes usam o 
> mesmo tipo de drones, deveria ser possível mapear modelos diferentes para cada desses componentes, ou não?
> 
> **Answer:** Boa tarde, "E na situação onde vários componentes usam o mesmo tipo de drones, deveria ser possível 
> mapear modelos diferentes para cada desses componentes, ou não?" Quer dizer que o mesmo tipo de drone na figura 
> poderia ser mapeado em diferentes modelos de drone? Lamento, mas acho que vai ter de estudar novamente o conceito de 
> função. Cumprimentos, Angelo Martins
> 
> **Question:** Boa noite, no enunciado é referido que os modelos não têm todos as mesmas capacidades. 
> Estas capacidades são os movimentos que o código DSL pode ter, por exemplo translação?
> 
> **Answer:** Bom dia, A DSL é independente do modelo de drone. As capacidades dos drones estão definidas implicitamente 
> na linguagem de programação do modelo. Claro que isto é uma enorme simplificação, pois além disso deveríamos ter 
> em conta as limitações físicas dos dispositivos, mas a realidade é muito complexa... Cumprimentos,Angelo Martins
> 
> **Question:** Bom dia, Quais são as informações inseridas no modelo do drone? Só o nome do modelo basta? 
> Cumprimentos, Grupo 41
> 
> **Answer:** Cumprimentos, US253 Configuration of a drone’s language As a Drone Tech, I want to specify in the system 
> the programming language for a given drone model, so that drones of this model can be used in figures/shows. A 
> given programming language can be supported by several drone models. Cumprimentos, Angelo Martins
>
### 1.3. Business Rules

- Each DroneModel must have a unique designation (name/version).
- A DroneModel must have a specified manufacturer.
- A DroneModel must define its tolerance to wind along each axis: x, y, z. 
- A DroneModel can be reused across multiple figures or simulations.
- Only authorized users (e.g., users with Drone Tech role) can create or modify DroneModels.

### 1.4 Acceptance Criteria

- A drone Model is successfully added if it's name is unique
- The Drone Model must be available to be assigned to different drones

## Domain

Entity: DroneModel

    class DroneModel {
    private Long id;
    private String name;
    private String manufacturer;
    private float xAxisTolerance;
    private float yAxisTolerance;
    private float zAxisTolerance;
    private boolean active;
    protected DroneModel() {};

    public DroneModel(String name,String manufacturer,float xAxisTolerance,float yAxisTolerance,float zAxisTolerance) {
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name.trim();
        this.manufacturer = manufacturer;
        this.xAxisTolerance = xAxisTolerance;
        this.yAxisTolerance = yAxisTolerance;
        this.zAxisTolerance = zAxisTolerance;
        this.active = true;
    }

    public String name(){return name;}
    public String manufacturer(){return manufacturer;}
    public float xAxisTolerance(){return xAxisTolerance;}
    public float yAxisTolerance(){return yAxisTolerance;}
    public float zAxisTolerance(){return zAxisTolerance;}

    @Override
    public boolean sameAs(Object other){
        if(!(other instanceof DroneModel)) return false;
        DroneModel that = (DroneModel) other;
        return this.name.equalsIgnoreCase(that.name);
    }

    @Override
    public Long identity() {return this.id;};

    @Override
    public int hashCode() {return name.toLowerCase().hashCode();}

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (!(o instanceof DroneModel)) return false;
        DroneModel that = (DroneModel) o;
        return name.equalsIgnoreCase(that.name);
    }

    @Override
    public String toString() {
        return "DroneModel{" +
                "id=" + id +
                ", manufacturer='" + manufacturer +
                "name='" + name + '\'' +
                ", active=" + active +
                '}';
    }

Domain: DroneRepository


    public interface DroneRepository extends DomainRepository<Long, DroneModel> 
    {
        Optional<DroneModel> findByNameIgnoreCase(String name);
    }

Domain Service: DroneInventoryService

    public class DroneInventoryService {

    private final DroneRepository categoryRepository;

    public DroneInventoryService(final DroneRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void registerDroneModel(final String name,String manufacturer,final float xAxisTolerance,final float yAxisTolerance,final float zAxisTolerance) {
        final Optional<DroneModel> existing = categoryRepository.findByNameIgnoreCase(name);
        if (existing.isPresent()) {
            throw new IllegalArgumentException("A category with this name already exists.");
        }

        final DroneModel newCategory = new DroneModel(name,manufacturer,xAxisTolerance,yAxisTolerance,zAxisTolerance);
        categoryRepository.save(newCategory);
    }


## Application

Controller: DroneModelCreationController

    public class DroneModelCreationController {
    private final AuthorizationService authz;
    private final DroneInventoryService service;

    private static final Set<Role> ALLOWED_ROLES = Set.of(Roles.DRONE_TECH);

    public DroneModelCreationController(final AuthorizationService authz,final DroneRepository repo) {
        this.authz = authz;
        this.service = new DroneInventoryService(repo);
    }

    public void addDroneModel(final String name,final String manufacturer,final float xAxisTolerance,final float yAxisTolerance,final float zAxisTolerance) {
        //authz.ensureAuthenticatedUserHasAnyOf((Role) ALLOWED_ROLES);
        service.registerDroneModel(name,manufacturer,xAxisTolerance,yAxisTolerance,zAxisTolerance);
    }
}

## UI (CLI/Backoffice)

---

    - Command in the menu: "Add drone model"
    - Input: 
            - Field for the drone model name
            - Field for the drone model's manufacturer
            - Field for the drone model's x-axis tolerance for the wind
            - Field for the drone model's y-axis tolerance for the wind
            - Field for the drone model's z-axis tolerance for the wind
            - Filed for the drone model's register/creation date in the system
    - Error messages shown for duplicate name entries.

## Testing

---

Unit Tests

    - Add new unique drone model -> Success
    - Add drone model with already existing name -> Fail with error
    - Verify drone model is created active by default
    - Verify the drone model has a date of creation/registry in the valid format

Repository Test

    - Validate persistence with in-memory DB
