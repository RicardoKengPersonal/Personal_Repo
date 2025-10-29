Supplementary Specification (FURPS+)
Functionality
Specifies functionalities that:
(i) are common across several use cases;
(ii) are not related to specific use cases, such as: Audit, Reporting, and Security.

**Reporting**

- The system should support real-time reporting of drone show performance, including drone status, battery levels, and flight paths.
- The application must generate detailed reports of show outcomes, with configurable filters based on date, show type, and client.
- Logs must be captured for all drone movements and system interactions, enabling traceability and auditing.

**Security**

- All system users must authenticate with strong passwords, including seven alphanumeric characters, three capital letters, and two digits.
- Role-based access control (RBAC) must be implemented to ensure proper permission handling for Admin, Drone Tech, CRM Manager, and other roles.
- All sensitive data, including client details and drone telemetry, must be encrypted in transit and at rest.

**System Management**


- The development team must adopt best practices for identifying system requirements, software analysis, and design.
- The system must be designed for easy integration with other drone-related systems and APIs, supporting future updates.

**Workflow**


- The system must support the automated orchestration of drone flight paths, ensuring seamless transitions between figures during shows to avoid collisions.
- Business rules validation must be already validated by the program.

**Usability**


Evaluates the user interface. It has several subcategories, among them: error prevention; interface aesthetics and design; help and documentation; consistency and standards.

**Interface**


- The interface should be simple, intuitive, and responsive for all user roles, including Admin, Drone Tech, CRM Collaborator, and Client.

**Aesthetics:**

- The UI must be relatively appealing with a modern design, using a color scheme that enhances the user experience.

**Consistency:**
- All error messages must be clear, direct, and consistent across different parts of the system to avoid user confusion.
- The system should have responsive design principles to ensure a consistent user experience across different devices.

**Accessibility:**

- The interface must be developed and should be accessible in English. All user-facing messages, labels, and documentation should be written in clear English.

**Reliability:**

Refers to the integrity, compliance, and interoperability of the software. The requirements to be considered include: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, and average time between failures.

- Drone show performance data must be consistently accurate and monitored in real-time.
- The system should predict potential conflicts or collisions during drone shows and warn operators accordingly to fix them and also prevent them.

**Performance:**

Evaluates the performance requirements of the software, including: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity, and application availability.

- The system should handle real-time telemetry data from hundreds of drones simultaneously.
- The application must be capable of starting up soon for drone flight planning and configuration.

**Supportability:**

The supportability requirements gather several characteristics, such as: testability, adaptability, maintainability, compatibility, configurability, installability, scalability, and more.

- The system's class structure must be designed to allow easy maintenance and future feature additions, following the best object-oriented practices.
- The development team must implement unit tests for all key system components, excluding Input/Output operations."
- The system must be easily configurable to add new drone figures, change choreography etc... without code changes.


**Design Constraints:**


Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class libraries, etc.

- The application must be developed not only in one language but many of them.
- The DDD (Domain Driven Model) will be developed in PlantUML.

*Implementation Constraints:*

Specifies or constraints the code or construction of a system, such as: mandatory standards/patterns, implementation languages, database integrity, resource limits, operating system.

- The application must be developed using IntelliJ IDEA or NetBeans as the integrated development environment (IDE).
- All code must be version-controlled using Git, with commits pushed to a shared repository on GitHub.

*Physical Constraints:*

Specifies a limitation or physical requirement regarding the hardware used to house the system, such as: material, shape, size, or weight.

- The system must be designed to work on both cloud-based infrastructure and on-premise servers, with no dependency on specific physical hardware.
- The drone hardware must be capable of supporting GPS-based positioning and onboard flight path coordination.

