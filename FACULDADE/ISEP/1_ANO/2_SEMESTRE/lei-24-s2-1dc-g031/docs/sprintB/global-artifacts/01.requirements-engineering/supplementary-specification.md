# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:  
&nbsp; &nbsp; (i) are common across several US/UC;  
&nbsp; &nbsp; (ii) are not related to US/UC, namely: Audit, Reporting and Security._


 >- All images should be in svg format.
 >- The application must support the english language.
 >- The user must be registered in the portal in order to be able to use it to post comments, and report faults and malfunctions of equipment.
     

## Usability

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._


 >- The application must have a user-friendly environment.
 >- Let users post comments, and report faults and malfunctions of equipment.
     

## Reliability

Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures.


 >- The application needs to be secure and protected against attacks.
 >- The application must be able to recover from failures.
 >- The application should not crash.
 >- The application must support the English language.
     

## Performance

Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability.


 >- The application shouldn't have any delays when operating.
     

## Supportability

_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._


 >- The application must be able to support many users at a time.
 >- The application must be able to support various types of devices.
 >- The application must be easy to maintain and to correct bugs and glitches.
     



### Design Constraints

Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc.

>- Programming Language: The system must be developed in Java to ensure compatibility and interoperability with existing components and libraries.
>- Development Tools: To ensure uniformity and facilitate team member collaboration, the system development must make use of NetBeans or the IntelliJ IDE for Java development.


### Implementation Constraints

_Specifies or constraints the code or construction of a system such
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._
>- Implementation Languages: To meet project requirements and compatibility constraints, the system backend must be implemented in Java and the graphical user interface in JavaFX 11.
>- Database Integrity: To ensure database operations are consistent and reliable, the system must enforce data integrity constraints and use transaction management.

### Interface Constraints

_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._

### Physical Constraints

Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight.

>- Hardware Requirements: The system should be designed to run smoothly on standard computing hardware commonly found in office settings, with no restrictions on material, shape, size, or weight.