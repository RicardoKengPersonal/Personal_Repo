# Complexity Analysis

### User Story
**USEI01** - Define the adequate data structures to store the information imported
from the files

### Análise para o Método `readArticles()`

- **Complexidade de Tempo**: `O(n * k)`

  - O método lê `n` linhas do arquivo CSV, onde cada linha representa um artigo.
  - Para cada linha, a função:
    - Divide a linha em valores usando `line.split(";")`, que tem um custo de \(O(k)\), onde \(k\) é o número médio de caracteres por linha.
    - Realiza operações constantes para criar uma nova instância de `Article`, resultando em \(O(1)\).
  - Portanto, a complexidade de tempo para `readArticles()` é \(O(n * k)\), onde:
    - `n` é o número de artigos.
    - `k` é o número médio de caracteres por linha.

- **Complexidade de Espaço**: `O(n)`

  - A lista `articles` armazena todos os artigos lidos do arquivo, resultando em um espaço \(O(n)\), onde `n` é o número de artigos.

### Análise para o Método `readWorkstations()`

- **Complexidade de Tempo**: `O(m * k)`

  - O método lê `m` linhas do arquivo CSV, onde cada linha representa uma estação de trabalho.
  - Para cada linha, a função:
    - Divide a linha em valores com `line.split(";")`, que tem um custo de \(O(k)\).
    - Realiza operações constantes para criar uma nova instância de `Workstation` e a inserção no mapa, resultando em \(O(1)\) em média.
  - Portanto, a complexidade de tempo para `readWorkstations()` é \(O(m * k)`, onde:
    - `m` é o número de estações de trabalho.
    - `k` é o número médio de caracteres por linha.

- **Complexidade de Espaço**: `O(m)`

  - O mapa `workstations` armazena todas as estações de trabalho lidas do arquivo, resultando em um espaço \(O(m)\), onde `m` é o número de estações de trabalho.

---

### User Story
**USEI02** - Implement a simulator that processes all the items
## Análise de Complexidade para `computeSimulation`

- **Complexidade de Tempo**: `O(n * q * (k + b))`

    - A função `computeSimulation` itera sobre `n` artigos, onde cada artigo possui `q` operações.
    - Para cada operação, a função:
        - Procura a estação de trabalho correspondente no mapa `workstations`, que tem um custo de \(O(k)\), onde `k` é o número médio de caracteres por linha.
        - Adiciona a operação à lista `operationsScheduled`, que tem um custo de \(O(1)\) em média.
        - Atualiza o tempo total de execução para a estação de trabalho correspondente, que tem um custo de \(O(1)\) em média.
        - Atualiza o tempo total de execução para a operação, que tem um custo de \(O(1)\) em média.
    - Portanto, a complexidade de tempo para `computeSimulation` é \(O(n * q * (k + b))\), onde:
        - `n` é o número de artigos.
        - `q` é o número médio de operações por artigo.
        - `k` é o número médio de caracteres por linha.
        - `b` é o número médio de caracteres por operação.

- **Complexidade de Espaço**: `O(n + m + n * q)`

    - A lista `operationsScheduled` armazena todas as operações agendadas, resultando em um espaço \(O(n * q)\), onde `n` é o número de artigos e `q` é o número médio de operações por artigo.
    - O mapa `workstations` armazena todas as estações de trabalho lidas do arquivo, resultando em um espaço \(O(m)\), onde `m` é o número de estações de trabalho.
    - A lista `articles` armazena todos os artigos lidos do arquivo, resultando em um espaço \(O(n)\), onde `n` é o número de artigos.


---


### User Story
**USEI03** - Calculate the total production time for the items.

#### Complexity Analysis for `displayTotalTimePerInstanceWithArticleType`

- **Time Complexity**: `O(m * n)`
  - The function iterates over `operationsScheduled` (`m` operations). For each operation, it may compare elements within `totalTimePerInstance` (up to `n` unique type-operation pairs), resulting in a total time complexity of `O(m * n)`.
  - Additionally, it formats and prints each entry in `totalTimePerInstance`, adding `O(n)` to the overall complexity. Thus, the total time complexity is `O(m * n + n)`, which simplifies to `O(m * n)`.

- **Space Complexity**: `O(n)`
  - The list `totalTimePerInstance` stores each unique type-operation pair and their cumulative time. This list grows with the number of unique type-operation pairs in `operationsScheduled`, giving a space complexity of `O(n)`.

---

### User Story
**USEI04** - Calculate execution times by each operation.

#### Complexity Analysis for `timeSpentPerOperation`

- **Time Complexity**: `O(m)`
  - This function iterates over `operationsScheduled` (size `m`) only once. For each entry, it performs a constant-time `HashMap` operation (either lookup or insertion), resulting in `O(m)` time complexity overall.

- **Space Complexity**: `O(k)`
  - The `HashMap<String, Integer> map` stores each unique operation with its accumulated time, where `k` represents the number of unique operations. Thus, space complexity is `O(k)`.

---

### User Story
**USEI05** - Calculate the Workstations times per percentage.

#### Complexity Analysis for `calculateWorkstationOperationTimes`

- **Time Complexity**: `O(m)`
  - The function iterates over the `operationsScheduled` list (size `m`) . Performs `HashMap` operations to update `totalWorkstationTime` and `totalExecutionTime`, which has a time complexity of `O(1)`. Thus, the overall time complexity is `O(m)`, where `m` is the number of operations.

- **Space Complexity**: `O(k)`
  - The `HashMap<String, Integer> totalWorkstationTime` stores the total time spent on each workstation, where `k` represents the number of unique workstations. The `HashMap<String, Integer> totalExecutionTime` stores the total execution time for each workstation, where `k` represents the number of unique workstations. Thus, the space complexity is `O(k)`.

---

### User Story
**USEI06** - Calculate the Workstations times per percentage.

#### Complexity Analysis for `calculateWorkstationOperationTimes`

- **Time Complexity**: `O(m)`
  - The function iterates over the `operationsScheduled` list (size `m`) . Performs `HashMap` operations to update `totalWorkstationTime` and `totalExecutionTime`, which has a time complexity of `O(1)`. Thus, the overall time complexity is `O(m)`, where `m` is the number of operations.

- **Space Complexity**: `O(k)`
  - The `HashMap<String, Integer> totalWorkstationTime` stores the total time spent on each workstation, where `k` represents the number of unique workstations. The `HashMap<String, Integer> totalExecutionTime` stores the total execution time for each workstation, where `k` represents the number of unique workstations. Thus, the space complexity is `O(k)`.
---

### Summary Table

| User Story | Time Complexity | Space Complexity |
|------------|-----------------|--------------|
| USEI02     | `O(n * q * (k + b))`      | `O(n + m + n * q)`          |
| USEI03     | `O(m * n)`      | `O(n)`      |
| USEI04     | `O(m)`          | `O(k)`      |
| USEI05     | `O(m)`          | `O(k)`      |
| USEI06     | `O(m)`          | `O(k)`      |
