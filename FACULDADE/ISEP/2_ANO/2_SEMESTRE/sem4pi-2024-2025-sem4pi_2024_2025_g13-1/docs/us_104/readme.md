# US104 - As Project Manager, I want the team to setup a continuous integration server.

## 1. Requirements Engineering

### 1.1. User Story Description

**As** Project Manager,  
**I want** the team to setup a Continuous Integration (CI) server,  
**So that** the integration and validation of code is automated and streamlined throughout the project development.

> GitHub Actions/Workflows must be used for this purpose.

---

### 1.2. Customer Specifications and Clarifications

- According to the project requirements (`Sem4PI_Project_Requirements_v01.pdf`), continuous integration is mandatory and will be evaluated as part of project deliverables.
- The setup must be integrated into the GitHub repository provided to the team and triggered on each push or pull request.
- The system must support automatic nightly builds and publish results and metrics (**NFR05**).

---

### 1.3. Acceptance Criteria

-  A GitHub Actions workflow is configured to build and test the system automatically.
-  The workflow runs successfully on push to the `main` or `develop` branches.
-  The workflow is triggered on pull requests.
-  The outcome of each execution (builds, tests, metrics) is visible in the GitHub Actions logs.
-  The repository includes documentation in `README.md` explaining the CI setup.
-  If automated tests exist, they must be executed as part of the CI pipeline.

---

### 1.4. Dependencies

- **NFR03** - Test-Driven Development: tests must be integrated into the CI pipeline.
- **NFR05** - Continuous Integration with automatic nightly builds.
- **NFR06** - Scripts must be available to support building and running the solution.
- **NFR09** - The project is developed in Java, so the CI must support Java (e.g., Maven or Gradle).

---

### 1.5. Suggested Tasks

- Create `.github/workflows/ci.yml` with the necessary steps to build and test the application.
- Ensure that the build works for both Linux and Windows (consider using a matrix build).
- Add build status badges to the top of the `README.md`.
- Configure the workflow to trigger on both `push` and `pull_request` events.
- Integrate a test coverage tool (e.g., JaCoCo).
- Generate and archive test coverage reports.

---

### 1.6. Deliverables

- A working `ci.yml` file in the `.github/workflows/` directory.
- Working CI process visible in the GitHub Actions tab.
- Documentation in `README.md` describing how CI is configured and triggered.

---

### 1.7. Other Relevant Remarks

- Failure to implement and maintain the CI process may negatively affect the project’s evaluation.
- CI must be active and functional before the Sprint 1 deadline (April 6th, 2025 at 20:00).
- The CI setup will be used to validate each sprint’s delivery and track development progress.
