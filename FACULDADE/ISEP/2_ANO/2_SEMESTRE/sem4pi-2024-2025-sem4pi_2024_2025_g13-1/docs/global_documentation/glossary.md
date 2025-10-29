**Glossary**

Terms, Expressions, and Acronyms (TEA) must be organized alphabetically.

| **_Term_**                           | **_Description_**                                                                                                                                          |
|:-------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Address**                          | A value object that represents the address of a Customer, including street, postal code, city, and country.                                                |
| **Admin**                            | A system role with the highest level of access, responsible for managing users, system configurations, and platform-wide operations.                       |
| **AggregateRoot**                    | A special type of entity that acts as the entry point to an aggregate, ensuring the consistency of changes within that aggregate.                          |
| **Category**                         | A value object that represents the category classification of a Figure.                                                                                    |
| **CollisionEvent**                   | Entity that logs a drone collision during simulation, including the 3D position, involved drones, and time step.                                           |
| **CRM**                              | Acronym for Customer Relationship Management.                                                                                                              |
| **CRM Collaborator**                 | A user role that assists CRM Managers by managing customer data, representatives, and show requests.                                                       |
| **CRM Manager**                      | A user role responsible for overseeing customer relationships and managing customer accounts and requests.                                                 |
| **Customer**                         | An entity that represents a client of Shodrone.                                                                                                            |
| **Customer Id**                      | A value object that uniquely identifies a Customer within the system.                                                                                      |
| **Customer Management Service**      | Application service responsible for managing customer-related operations, such as registration and status changes.                                         |
| **Customer Priority Service**        | Domain service that defines priority rules between customers, e.g., giving precedence to VIP clients over Regular ones.                                    |
| **Customer Representative**          | An entity representing an individual (employee or representative) associated with a Customer.                                                              |
| **CustomerName**                     | A value object encapsulating the name of a Customer with validation logic.                                                                                 |
| **Customer Repository**              | Interface to perform persistence operations on Customer entities, such as find by VAT number and save.                                                     |
| **Customer Relationship Management** | The full form of CRM, representing the tools and practices used to manage customer interactions.                                                           |
| **CustomerStatus**                   | An enumeration that defines a Customer's state: Created, Regular, VIP, Infringement, Deleted.                                                              |
| **Drone**                            | An entity representing a physical drone, with serial number, model, acquisition date, and maintenance history.                                             |
| **DroneAvailabilityService**         | Domain service responsible for checking drone availability when scheduling a show.                                                                         |
| **DroneConfiguration**               | A value object specifying how many drones of a given model are used for a FigureElement.                                                                   |
| **DroneCount**                       | A value object representing the number of drones requested for a show.                                                                                     |
| **DroneId**                          | A value object identifying a unique Drone in the system.                                                                                                   |
| **DroneInventoryService**            | Domain service managing the addition, repair marking, and maintenance scheduling of drones.                                                                |
| **DroneMaintenance**                 | Entity recording a drone's maintenance operation, including type (preventive or repair), Keyword, and next date.                                       |
| **DroneMaintenanceId**               | Value object uniquely identifying a DroneMaintenance entry.                                                                                                |
| **DroneModel**                       | A value object that defines the model of a drone, relevant for compatibility checks.                                                                       |
| **Drone Repository**                 | A repository interface for managing Drone entities, including search by status, availability, and ID.                                                      | 
| **DroneStatus**                      | Enum representing a drone's state: ACTIVE, IN_REPAIR, or DECOMMISSIONED.                                                                                   |
| **DroneTech**                        | A system user role responsible for configuring drones, maintaining inventory, generating simulation data, and handling drone-related technical operations. |
| **DSL Plugin**                       | Plugin that analyses and validates the syntax of a Figure's DSL Keyword.                                                                               |
| **DSLCode**                          | A value object representing the high-level DSL code used to describe a Figure, imported from an external tool.                                             |
| **Email**                            | A value object representing a user's email address, with validation.                                                                                       |
| **Email Representative**             | A value object for representing a Customer Representative's email, with validation.                                                                        |
| **ElementId**                        | A value object uniquely identifying a FigureElement.                                                                                                       |
| **ElementType**                      | Enum describing the type of element in a Figure: GEOMETRIC or 3D BITMAPS.                                                                                  |
| **Entity**                           | A domain object identified by its unique identity rather than its attributes.                                                                              |
| **Figure**                           | An aggregate root representing a drone figure used in a show. It may have multiple versions based on different DSL codes.                                  |
| **FigureCode**                       | Value object containing a unique identifier for a Figure.                                                                                                  |
| **FigureElement**                    | Entity describing an element of a FigureVersion, with a type, movements, and drone configuration.                                                          |
| **Figure Management Service**        | Application service for managing figures, including creation and validation of exclusivity and DSL.                                                        |
| **Figure Repository**                | Interface for data access operations on Figure entities.                                                                                                   |
| **Figure Total Drone**               | Value object that records the total number of drones used in a FigureVersion.                                                                              |
| **Figure Type**                      | Enumeration describing a figure version as STATIC (fixed positions) or DYNAMIC (movement).                                                                 |
| **Figure Version**                   | An entity representing a specific implementation/version of a Figure.                                                                                      |
| **Figure Version Ref**               | A value object referencing a specific version of a Figure used in a Show.                                                                                  |
| **Framework**                        | A reusable set of libraries/tools to structure application development, e.g., Spring or Hibernate.                                                         |
| **Hibernate**                        | A Java ORM framework implementing JPA for database interaction via objects.                                                                                |
| **Java Persistence API**             | A specification that defines ORM operations in Java.                                                                                                       |
| **JPA**                              | Acronym for Java Persistence API.                                                                                                                          |
| **Keyword**                          | A value object used for categorizing Figures with searchable keywords.                                                                                     |
| **MaintenanceType**                  | Enum indicating whether a drone maintenance is PREVENTIVE or REPAIR.                                                                                       |
| **Movement**                         | Enum describing types of movements for drones: ROTATION or TRANSLATION.                                                                                    |
| **Object Relational Mapping**        | Technique for mapping between object-oriented languages and relational databases.                                                                          |
| **ORM**                              | Acronym for Object Relational Mapping.                                                                                                                     |
| **Password**                         | A value object representing a secure password with validation.                                                                                             |
| **Place**                            | A value object indicating the location of a show request, including address, city, and country.                                                            |
| **Position3D**                       | Value object representing a spatial coordinate (x, y, z) used in simulation.                                                                               |
| **ProposalStatus**                   | Enum defining the current state of a ShowProposal: DRAFT, FINALIZED, ACCEPTED, or REJECTED.                                                                |
| **ProposalTemplateId**               | Value object identifying the template used to generate a ShowProposal.                                                                                     |
| **ProposalTotalDrone**               | A value object specifying the total number of drones proposed for a show.                                                                                  |
| **RepresentativeStatus**             | Enum defining whether a Customer Representative is ACTIVE or DISABLED.                                                                                     |
| **Repository**                       | A design pattern that abstracts the data access layer, providing methods to retrieve and persist domain entities.                                          |
| **Role**                             | Enum listing the roles a system user can have: ADMIN, CRM_MANAGER, etc.                                                                                    |
| **Service**                          | A design pattern that encapsulates domain logic not naturally belonging to an entity or value object.                                                      |
| **Shodrone**                         | A company that provides customised multimedia drone shows, using programmable UAVs.                                                                        |
| **Show**                             | An entity and aggregate root representing a scheduled or completed drone show.                                                                             |
| **ShowDateTime**                     | Value object containing both date and time of a scheduled show.                                                                                            |
| **ShowDescription**                  | Value object providing descriptive information about a requested show, including document references and exclusivity requirements.                         |
| **ShowDuration**                     | Value object specifying the intended duration of a show in minutes.                                                                                        |
| **Show Designer**                    | A user role responsible for creating and updating Figures based on customer requests, ensuring they meet the show requirements and technical constraints.  |
| **ShowId**                           | Value object uniquely identifying a Show entity.                                                                                                           |
| **ShowProposal**                     | Entity containing the final proposal details for a show, including figures, video preview, and total drones.                                               |
| **ShowProposalId**                   | Value object uniquely identifying a ShowProposal.                                                                                                          |
| **ShowRequest**                      | Entity representing a customer's request for a new show, including drone count, desired date, and requested figures.                                       |
| **ShowRequestId**                    | Value object uniquely identifying a ShowRequest.                                                                                                           |
| **ShowRequest Repository**           | A repository interface for storing and retrieving ShowRequest entities based on customer, ID, or request status.                                           |
| **ShowRequestStatus**                | Enum tracking the lifecycle of a show request: SUBMITTED, DESIGNER_ASSIGNED, PROPOSAL_READY, etc.                                                          |
| **ShowSchedulingService**            | Domain service responsible for turning accepted proposals into scheduled shows.                                                                            |
| **ShowStatus**                       | Enum indicating a show's current status: SCHEDULED, IN_PROGRESS, COMPLETED, CANCELED.                                                                      |
| **Simulation**                       | Entity representing a test simulation of a show or figure, including volume, collisions, and success result.                                               |
| **SimulationId**                     | Value object uniquely identifying a Simulation.                                                                                                            |
| **SimulationResult**                 | Entity storing the results of a simulation, including any detected collisions.                                                                             |
| **Simulation Repository**            | A repository interface responsible for storing and querying simulation data, such as simulations by ID or target type.                                     |
| **SimulationService**                | Domain service to run simulations for Figures and Shows, detecting possible collisions.                                                                    |
| **SimulationTarget**                 | Enum indicating whether a simulation is run for a Figure or a Show.                                                                                        |
| **SimulationTargetRef**              | Value object encapsulating a simulation target, with logic to determine target type and ID.                                                                |
| **SimulationVolume**                 | Value object defining the 3D space where simulation takes place, including dimensions and resolution.                                                      |
| **User**                             | An entity representing a system user, uniquely identified by an email.                                                                                     |
| **User Repository**                  | Interface for managing persistence of User entities.                                                                                                       |
| **UserStatus**                       | An enumeration indicating whether a user is Active or Inactive.                                                                                            |
| **UserId**                           | A value object uniquely identifying a User in the system.                                                                                                  |
| **ValueObject**                      | An immutable object defined solely by its attributes and without a unique identity.                                                                        |
| **VATNumber**                        | A value object representing the customer's VAT identification number.                                                                                      |
| **Video**                            | A value object containing the path or identifier for a video simulation preview included in a proposal.                                                    |
"""                                                                 