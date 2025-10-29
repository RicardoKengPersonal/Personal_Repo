# US105 - As Project Manager, I want the team to add to the project the necessary scripts, so that builds/executions/deployments can be executed effortlessly.

## 1. Requirements Engineering

### 1.1. User Story Description

**As** Project Manager,  
**I want** the team to include the necessary scripts in the project,  
**So that** builds, executions, and deployments can be performed easily and consistently across development environments.

> Scripts must support at least Linux and Windows environments.

---

### 1.2. Customer Specifications and Clarifications

- According to the non-functional requirements (**NFR06**), the repository must include scripts for building and deploying the solution.
- The scripts must allow running the application and testing components with minimal manual configuration.
- A `README.md` must be available at the root of the project with clear instructions for usage of the scripts and execution of the system.
- Scripts may include commands to compile the Java backend, launch servers, populate databases, or execute frontend modules.
- The solution must work with both in-memory and persistent (RDBMS) databases (related to **NFR07**).

**From the client clarifications:**

> **Question:**
Boa tarde,
A minha equipa tem algumas dúvidas relativamente à US105:
"As Project Manager, I want the team to add to the project the necessary scripts, so that build/executions/deployments/... can be executed effortlessly. Include scripts for all the major tasks and execution of applications."
Especificamente, não conseguimos compreender com clareza quais os tipos de scripts que estão a ser referidos e qual o seu propósito dentro do projeto. Poderia esclarecer quais as automações esperadas e em que cenários estes scripts devem ser utilizados?
Obrigado.
>
> **Answer:**
Bom dia,
Têm um conjunto de scripts na raiz do vosso repositório. Devem fazer evoluir esses scripts e ter scripts análogos para as diferentes aplicações. O deployment automático noutras máquinas não se enquadra no sprint 1, pelo que fica fora do âmbito da US105.
Cumprimentos,
Angelo Martins

---

### 1.3. Acceptance Criteria

- **AC1**: The project includes scripts to build and execute the application.
- **AC2**: Scripts work in both Linux and Windows environments.
- **AC3**: The scripts are placed in a clearly named folder (e.g., `/scripts`).
- **AC4**: A `README.md` file at the root of the repository explains how to build, run, and deploy the system using the scripts.
- **AC5**: Scripts allow switching between in-memory and persistent database configuration.
- **AC6**: All scripts are tested and documented.

---

### 1.4. Found out Dependencies

- Depends on the existence of a working project structure (**US103**) and project repository (**US102**).
- Scripts may invoke tasks that depend on CI/CD pipelines (**US104**) or authentication setup (**US210**).
- The database configuration is aligned with **NFR07** (in-memory and RDBMS support).
- The overall solution is developed in Java (**NFR09**) and may require scripts compatible with Maven or Gradle.

---

### 1.5. Suggested Tasks

- Create build scripts for Java backend using Maven or Gradle.
- Create platform-specific scripts:
    - `.sh` scripts for Linux.
    - `.bat` or PowerShell scripts for Windows.
- Create scripts for:
    - Starting/stopping the application.
    - Initialising the database (both in-memory and remote).
    - Running automated tests.
    - (Optional) Dockerfile and/or Docker Compose setup.
- Write usage instructions in `README.md`.
- Test all scripts on both platforms.

---

### 1.6. Deliverables

- Scripts located in `/scripts/` directory.
- Updated `README.md` with:
    - How to run the application.
    - How to deploy the application.
    - How to use the database options.
- All scripts committed and versioned in the GitHub repository.

---

### 1.7. Other Relevant Remarks

- Scripts must be executable with minimal configuration to reduce friction in development and testing.
- These scripts will be reused by the CI pipeline and potentially for deployment in a future environment.
- Proper comments and error messages should be included within the scripts.