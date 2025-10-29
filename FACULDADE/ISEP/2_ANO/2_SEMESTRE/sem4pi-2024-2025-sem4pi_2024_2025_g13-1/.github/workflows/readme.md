# Integração Contínua Java com Maven (GitHub Actions)

Este repositório está configurado com um **workflow de Integração Contínua (CI)** para projetos Java baseados em Maven, utilizando o **GitHub Actions**. O objetivo deste workflow é automatizar a construção, validação, análise de qualidade de código e cobertura de testes de uma base de código Java, garantindo fiabilidade e qualidade ao longo do ciclo de desenvolvimento.

---

## Tecnologias e Ferramentas Utilizadas

- **GitHub Actions** – Plataforma de automação para integração contínua.
- **Maven** – Ferramenta de gestão e construção de projetos Java.
- **JUnit** – Framework de testes unitários.
- **JaCoCo** – Ferramenta para análise de cobertura de testes.
- **Checkstyle** – Ferramenta de análise estática de código Java.
- **GitInspector** – Ferramenta para análise estatística de contribuições no repositório.
- **Docker** – Utilizado para correr o GitInspector.
- **Java 11** – Versão do JDK utilizada para compilação e testes.

---

## Quando é que o workflow é executado?

Este workflow corre automaticamente:

1. **Sempre que há um `push` para a branch `main`**
2. **Diariamente, à meia-noite (UTC)** (via agendamento com cron)
3. **Manual** – Pode ser iniciado manualmente via o botão *"Run workflow"* no GitHub (evento `workflow_dispatch`)

---

## Estrutura das Tarefas (`jobs`)

### Condição de Execução

O job principal (`build`) **só corre se o repositório pertencer ao utilizador/organização `Departamento-de-Engenharia-Informatica`**.

---

### Etapas do Workflow

#### 1. **Checkout do Código**
- Clona a branch `main` com histórico completo (`fetch-depth: 0`).

#### 2. **Configuração do Java**
- Instala o JDK 11 com Temurin (Adoptium).
- Ativa cache do Maven para acelerar builds subsequentes.

#### 3. **Compilação e Validação com Maven**
```bash
mvn clean verify -Dmaven.test.failure.ignore=true
```
- Compila o projeto e corre todos os testes.
- Ignora falhas de testes para permitir que os relatórios sejam gerados mesmo se existirem testes com erro.

#### 4. **Geração de Relatórios**
Executa os seguintes plugins do Maven:
- `surefire-report`: Relatório HTML dos testes.
- `checkstyle-aggregate`: Relatório de qualidade do código.
- `jacoco:report-aggregate`: Relatório de cobertura de código.

#### 5. **Arquivamento de Resultados**
- Faz upload dos relatórios Checkstyle e Testes para o GitHub como artefactos.

#### 6. **Relatório de Testes JUnit**
- Utiliza a ação `test-reporting` para apresentar resultados de testes em formato legível no GitHub Actions UI.

#### 7. **Relatório de Cobertura (JaCoCo)**
- Gera e publica o relatório de cobertura, definindo um limiar mínimo de 80%.
- Não falha o “Build” se a cobertura estiver abaixo desse valor, mas regista-o.

#### 8. **Resumo de Cobertura**
- Adiciona um resumo da cobertura ao separador "Summary" da execução do workflow no GitHub.

#### 9. **Upload de Relatórios de Cobertura**
-Guarda os relatórios de cobertura como artefactos.

#### 10. **Relatórios GitInspector (HTML e Texto)**
Corre duas versões do relatório GitInspector (HTML e texto), que avaliam:
- Número de contribuições por autor
- Linhas de código adicionadas/removidas
- Distribuição de esforço

#### 11. **Resumo Markdown do GitInspector**
- O relatório em texto é incluído diretamente no separador "Summary" da execução no GitHub.