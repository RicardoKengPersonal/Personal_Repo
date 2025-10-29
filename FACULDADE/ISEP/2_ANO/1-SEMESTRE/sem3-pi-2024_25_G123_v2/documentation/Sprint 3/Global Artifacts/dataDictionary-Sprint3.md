# Data Dictionary

| **_Entity_** | **_Attribute_**        | **_Data Type_** | **_Description_**                               | **_Constraints_**                     |                                       
|:-------------|------------------------|-----------------|-------------------------------------------------|:--------------------------------------|
| **customer** | IdCustomer             | NUMBER(10)      | Unique identifier for the customer              | Primary Key                           |
|              | name                   | VARCHAR2(255)   | The Customer's name                             |                                       |
|              | email                  | VARCHAR2(255)   | The customer's email                            |                                       |
|              | vatin                  | VARCHAR2(255)   | The customer's VATIN                            |                                       |
|              | phoneNumber            | VARCHAR2(255)   | The customer's phone number                     |                                       |
|              | idAdress               | NUMBER(10)      | Unique identifier for the customer's address    | Foreign Key from address              |
|              | idCustomerType         | NUMBER(10)      | Unique identifier for the customer type         | Foreign Key from customerType         |
|              | idCustomerStatus       | NUMBER(10)      | Unique identifier for the customer status       | Foreign Key from customerStatus       |
|              | idLegacyIdentification | NUMBER(10)      | Unique identifier for the Legacy identification | Foreign Key from legacyIdentification | 

| **_Entity_**     | **_Attribute_** | **_Data Type_** | **_Description_**                       | **_Constraints_**                     |                                       
|:-----------------|-----------------|-----------------|-----------------------------------------|:--------------------------------------|
| **customerType** | IdCustomerType  | NUMBER(10)      | Unique identifier for the customer type | Primary Key                           |
|                  | name            | VARCHAR2(255)   | The Customer's type name                |                                       |

| **_Entity_**       | **_Attribute_**  | **_Data Type_** | **_Description_**                         | **_Constraints_**                     |                                       
|:-------------------|------------------|-----------------|-------------------------------------------|:--------------------------------------|
| **customerStatus** | IdCustomerStatus | NUMBER(10)      | Unique identifier for the customer status | Primary Key                           |
|                    | name             | VARCHAR2(255)   | The Customer's status name                |                                       |

| **_Entity_**   | **_Attribute_** | **_Data Type_** | **_Description_**                 | **_Constraints_**        |                                       
|:---------------|-----------------|-----------------|-----------------------------------|:-------------------------|
| **subproduct** | IdProduct       | NUMBER(10)      | Unique identifier for the product | Foreign Key from product |

| **_Entity_**      | **_Attribute_**        | **_Data Type_** | **_Description_**                               | **_Constraints_**                     |                                       
|:------------------|------------------------|-----------------|-------------------------------------------------|:--------------------------------------|
| **productFamily** | IdProductFamily        | NUMBER(10)      | Unique identifier for the customer              | Primary Key                           |
|                   | name                   | VARCHAR2(255)   | The Customer's name                             |                                       |
|                   | IdLegacyIdentification | NUMBER(10)      | Unique identifier for the legacy identification | Foreign Key from legacyIdentification |

| **_Entity_**    | **_Attribute_**  | **_Data Type_** | **_Description_**                    | **_Constraints_**         |                                       
|:----------------|------------------|-----------------|--------------------------------------|:--------------------------|
| **procurement** | idSupplier       | NUMBER(10)      | Unique identifier for the supplier   | Foreign Key from supplier |
|                 | idProduct        | NUMBER(10)      | Unique identifier for the product    | Foreign Key from product  |
|                 | unitCost         | NUMBER(10)      | The cost per unit                    |                           |
|                 | minimumOrderSize | NUMBER(10)      | The minimum size for an order        |                           |
|                 | startDate        | date            | The starting date of the procurement | Primary Key               |
|                 | endDate          | date            | The ending date of the procurement   |                           |

| **_Entity_** | **_Attribute_**        | **_Data Type_** | **_Description_**                                               | **_Constraints_**                     |                                       
|:-------------|------------------------|-----------------|-----------------------------------------------------------------|:--------------------------------------|
| **supplier** | idSupplier             | NUMBER(10)      | Unique identifier for the supplier                              | Primary Key                           |
|              | name                   | VARCHAR2(255)   | The Supplier's name                                             |                                       |
|              | IdLegacyIdentification | NUMBER(10)      | Unique identifier for the legacy identification of the supplier | Foreign Key from legacyIdentification |

| **_Entity_**          | **_Attribute_**        | **_Data Type_** | **_Description_**                  | **_Constraints_**                     |                                       
|:----------------------|------------------------|-----------------|------------------------------------|:--------------------------------------|
| **reservationStatus** | idReservationStatus    | NUMBER(10)      | Unique identifier for the customer | Primary Key                           |
|                       | name                   | VARCHAR2(255)   | The Reservation status name        |                                       |

| **_Entity_** | **_Attribute_**         | **_Data Type_** | **_Description_**                               | **_Constraints_**                     |                                       
|:-------------|-------------------------|-----------------|-------------------------------------------------|:--------------------------------------|
| **product**  | idProduct               | NUMBER(10)      | Unique identifier for the product               | Primary Key                           |
|              | description             | VARCHAR2(255)   | The product's description                       |                                       |
|              | idProductFamily         | NUMBER(10)      | Unique identifier for the product family        | Foreign Key from productFamily        |
|              | idUnit                  | NUMBER(10)      | Unique identifier for the unit of the product   | Foreign Key from unit                 |
|              | IdLegacyIdentification  | NUMBER(10)      | Unique identifier for the legacy identification | Foreign Key from legacyIdentification |

| **_Entity_**             | **_Attribute_**        | **_Data Type_** | **_Description_**                              | **_Constraints_**                     |                                       
|:-------------------------|------------------------|-----------------|------------------------------------------------|:--------------------------------------|
| **legacyIdentification** | idLegacyIdentification | NUMBER(10)      | Unique identifier for the legacyIdentification | Primary Key                           |
|                          | legacyCode             | VARCHAR2(255)   | The legacy code from the legacy system         |                                       |

| **_Entity_**    | **_Attribute_**     | **_Data Type_** | **_Description_**                                 | **_Constraints_**                  |                                       
|:----------------|---------------------|-----------------|---------------------------------------------------|:-----------------------------------|
| **Reservation** | idOrder             | NUMBER(10)      | Unique identifier for the order                   | Foreign Key from customerOrder     |
|                 | idProduct           | NUMBER(10)      | Unique identifier for the product                 | Foreign Key from product           |
|                 | quantity            | NUMBER(10)      | The quantity of a given reserved product          |                                    |
|                 | idReservationStatus | NUMBER(10)      | Unique identifier for the status of a reservation | Foreign Key from reservationStatus |

| **_Entity_**            | **_Attribute_**        | **_Data Type_** | **_Description_**                            | **_Constraints_**                     |                                       
|:------------------------|------------------------|-----------------|----------------------------------------------|:--------------------------------------|
| **customerOrderStatus** | idOrderStatus          | NUMBER(10)      | Unique identifier for the status of an order | Primary Key                           |
|                         | name                   | VARCHAR2(255)   | The Customer's order status name             |                                       |

| **_Entity_**      | **_Attribute_**        | **_Data Type_** | **_Description_**                               | **_Constraints_**                    |                                       
|:------------------|------------------------|-----------------|-------------------------------------------------|:-------------------------------------|
| **customerOrder** | idOrder                | NUMBER(10)      | Unique identifier for a given order             | Primary Key                          |
|                   | idCustomer             | NUMBER(10)      | Unique identifier for a given customer          | Foreign Key from customer            |
|                   | orderDate              | date            | The date of order placement                     |                                      |
|                   | deliveryDate           | date            | The date of delivery                            |                                      |
|                   | idDeliveryAddress      | NUMBER(10)      | Unique identifier for a delivery address        | Foreign Key from address             |
|                   | idOrderStatus          | NUMBER(10)      | Unique identifier for the status of an order    | Foreign Key from customerOrderStatus |

| **_Entity_**        | **_Attribute_**   | **_Data Type_** | **_Description_**                                    | **_Constraints_**              |                                       
|:--------------------|-------------------|-----------------|------------------------------------------------------|:-------------------------------|
| **productionOrder** | idProductionOrder | NUMBER(10)      | Unique identifier for a given production order       | Primary Key                    |
|                     | idOrder           | VARCHAR2(255)   | Unique identifier of an order                        | Foreign Key from customerOrder |
|                     | idVariant         | NUMBER(10)      | Unique identifier for the variant of a given product | Foreign Key from variant       |
|                     | priority          | VARCHAR2(255)   | The priority of an order                             |                                |

| **_Entity_** | **_Attribute_** | **_Data Type_** | **_Description_**                      | **_Constraints_**         |                                       
|:-------------|-----------------|-----------------|----------------------------------------|:--------------------------|
| **part**     | idProduct       | NUMBER(10)      | Unique identifier for the customer     | Foreign Key from product  |
|              | minimumStock    | VARCHAR2(255)   | The minimum stock of a given part      |                           |
|              | availableStock  | NUMBER(10)      | The available stock of a given part    |                           |
|              | idPartType      | NUMBER(10)      | Unique identifier for the type of part | Foreign Key from partType |

| **_Entity_**        | **_Attribute_** | **_Data Type_** | **_Description_**                        | **_Constraints_**        |                                       
|:--------------------|-----------------|-----------------|------------------------------------------|:-------------------------|
| **finishedProduct** | IdProduct       | NUMBER(10)      | Unique identifier for a finished product | Foreign Key from product |

| **_Entity_** | **_Attribute_** | **_Data Type_** | **_Description_**                               | **_Constraints_**         |                                       
|:-------------|-----------------|-----------------|-------------------------------------------------|:--------------------------|
| **unit**     | idUnit          | NUMBER(10)      | Unique identifier for the unit                  | Primary Key               |
|              | name            | VARCHAR2(255)   | The unit's name                                 |                           |
|              | idUnitType      | NUMBER(10)      | Unique identifier for the legacy identification | Foreign Key from unitType |

| **_Entity_** | **_Attribute_**        | **_Data Type_** | **_Description_**                   | **_Constraints_**                     |                                       
|:-------------|------------------------|-----------------|-------------------------------------|:--------------------------------------|
| **unitType** | idUnitType             | NUMBER(10)      | Unique identifier for the unit type | Primary Key                           |
|              | name                   | VARCHAR2(255)   | The unit type's name                |                                       |

| **_Entity_** | **_Attribute_**        | **_Data Type_** | **_Description_**                   | **_Constraints_**                     |                                       
|:-------------|------------------------|-----------------|-------------------------------------|:--------------------------------------|
| **partType** | idPartType             | NUMBER(10)      | Unique identifier for the part type | Primary Key                           |
|              | name                   | VARCHAR2(255)   | The part type's name                |                                       |

| **_Entity_**         | **_Attribute_**     | **_Data Type_** | **_Description_**                                   | **_Constraints_**           |                                       
|:---------------------|---------------------|-----------------|-----------------------------------------------------|:----------------------------|
| **billOfOperations** | idBillOfOperations  | NUMBER(10)      | Unique identifier of a given bill of operations     | Primary Key                 |
|                      | idProduct           | VARCHAR2(255)   | Unique identifier of a given product                | Foreign Key from product    |
|                      | idOperation         | NUMBER(10)      | Unique identifier of a given operation              | Foreign Key from operation  |
|                      | idOperationType     | NUMBER(10)      | Unique identifier of a given operation type         | Foreign Key from operations |
|                      | idInput             | NUMBER(10)      | Unique identifier of a given input                  | Foreign Key from input      |
|                      | idOutput            | NUMBER(10)      | Unique identifier of a given output                 | Foreign Key from output     |
|                      | idNextOperation     | NUMBER(10)      | Unique identifier for the next operation of the BOO | Foreign Key from operation  |
|                      | idNextOperationType | NUMBER(10)      | Unique identifier for the next operation type       | Foreign Key from operation  |
|                      | sequenceNumber      | NUMBER(10)      | The sequence number of a given Bill Of Operations   |                             |

| **_Entity_**    | **_Attribute_** | **_Data Type_** | **_Description_**                    | **_Constraints_**              |                                       
|:----------------|-----------------|-----------------|--------------------------------------|:-------------------------------|
| **orderedItem** | idOrder         | NUMBER(10)      | Unique identifier for a given order  | Foreign Key from customerOrder |
|                 | idVariant       | NUMBER(10)      | Unique identifier of a given variant | Foreign Key from variant       |
|                 | quantity        | NUMBER(10)      | The quantity of an item of an order  |                                |

| **_Entity_** | **_Attribute_** | **_Data Type_** | **_Description_**                      | **_Constraints_**        |                                       
|:-------------|-----------------|-----------------|----------------------------------------|:-------------------------|
| **variant**  | idVariant       | NUMBER(10)      | Unique identifier of a given variant   | Primary Key              |
|              | idProduct       | VARCHAR2(255)   | Unique identifier of a given product   | Foreign Key from product |
|              | name            | NUMBER(10)      | The name of a given variant            |                          |
|              | description     | VARCHAR2(255)   | The description of a given variant     |                          |
|              | availableStock  | NUMBER(10)      | The number of a given variant in stock |                          |

| **_Entity_**                    | **_Attribute_** | **_Data Type_** | **_Description_**                    | **_Constraints_**                  |                                       
|:--------------------------------|-----------------|-----------------|--------------------------------------|:-----------------------------------|
| **productionOptionTypeVariant** | idOption        | NUMBER(10)      | Unique identifier of a given option  | Foreign Key from productOptionType |
|                                 | idVariant       | NUMBER(10)      | Unique identifier of a given variant | Foreign Key from variant           |
|                                 | value           | VARCHAR2(255)   | The value of a given option          |                                    |

| **_Entity_**          | **_Attribute_**        | **_Data Type_** | **_Description_**                        | **_Constraints_**                     |                                       
|:----------------------|------------------------|-----------------|------------------------------------------|:--------------------------------------|
| **productOptionType** | idOption               | NUMBER(10)      | Unique identifier of a given option type | Primary Key                           |
|                       | name                   | VARCHAR2(255)   | The option type's name                   |                                       |

| **_Entity_** | **_Attribute_** | **_Data Type_** | **_Description_**                   | **_Constraints_**                     |                                       
|:-------------|-----------------|-----------------|-------------------------------------|:--------------------------------------|
| **input**    | idInput         | NUMBER(10)      | Unique identifier for a given input | Primary Key                           |

| **_Entity_** | **_Attribute_** | **_Data Type_** | **_Description_**                    | **_Constraints_**                     |                                       
|:-------------|-----------------|-----------------|--------------------------------------|:--------------------------------------|
| **output**   | idOutput        | NUMBER(10)      | Unique identifier for a given output | Primary Key                           |

| **_Entity_**     | **_Attribute_** | **_Data Type_** | **_Description_**                       | **_Constraints_**        |                                       
|:-----------------|-----------------|-----------------|-----------------------------------------|:-------------------------|
| **inputProduct** | idInput         | NUMBER(10)      | Unique identifier for a given input     | Foreign Key from input   |
|                  | idProduct       | NUMBER(10)      | Unique identifier of a given product    | Foreign Key from product |
|                  | quantity        | NUMBER(10)      | The quantity of a given input product   |                          |

| **_Entity_**      | **_Attribute_** | **_Data Type_** | **_Description_**                      | **_Constraints_**        |                                       
|:------------------|-----------------|-----------------|----------------------------------------|:-------------------------|
| **outputProduct** | idOutput        | NUMBER(10)      | Unique identifier for a given output   | Primary Key              |
|                   | idProduct       | NUMBER(10)      | Unique identifier of a given product   | Foreign Key from product |
|                   | quantity        | NUMBER(10)      | The quantity of a given output product |                          |

| **_Entity_** | **_Attribute_** | **_Data Type_** | **_Description_**                    | **_Constraints_** |                                       
|:-------------|-----------------|-----------------|--------------------------------------|:------------------|
| **address**  | idAddress       | NUMBER(10)      | Unique identifier for a given output | Primary Key       |
|              | street          | VARCHAR2(255)   | The street of the address            |                   |
|              | floor           | VARCHAR2(255)   | The floor of a given address         |                   |
|              | apartment       | NVARCHAR2(255)  | The apartment of a given address     |                   |
|              | zipCode         | VARCHAR2(20)    | The zip code of a given address      |                   |
|              | town            | VARCHAR2(255)   | The town of a given address          |                   |
|              | city            | VARCHAR2(255)   | The city of a given address          |                   |
|              | country         | VARCHAR2(255)   | The country of a given address       |                   |

| **_Entity_**  | **_Attribute_**        | **_Data Type_** | **_Description_**                                  | **_Constraints_**                     |                                       
|:--------------|------------------------|-----------------|----------------------------------------------------|:--------------------------------------|
| **operation** | idOperation            | NUMBER(10)      | Unique identifier for a given operation            | Primary Key                           |
|               | idOperationType        | NUMBER(10)      | Unique identifier of a given operation type        | Foreign Key from operationType        |
|               | idLegacyIdentification | NUMBER(10)      | Unique identifier of a given legacy identification | Foreign Key from legacyIdentification |
|               | expectedExecutionTime  | NUMBER(10)      | The expected execution time of a given operation   |                                       |

| **_Entity_**      | **_Attribute_**        | **_Data Type_** | **_Description_**                                  | **_Constraints_**                     |                                       
|:------------------|------------------------|-----------------|----------------------------------------------------|:--------------------------------------|
| **operationType** | idOperationType        | NUMBER(10)      | Unique identifier for a given operation type       | Primary Key                           |
|                   | description            | VARCHAR(255)    | The description of the type of operation           |                                       |
|                   | idLegacyIdentification | NUMBER(10)      | Unique identifier of a given legacy identification | Foreign Key from legacyIdentification |


| **_Entity_**                              | **_Attribute_**   | **_Data Type_** | **_Description_**                             | **_Constraints_**                |                                       
|:------------------------------------------|-------------------|-----------------|-----------------------------------------------|:---------------------------------|
| **operationWorkstationTypeCompatibility** | idOperation       | NUMBER(10)      | Unique identifier for a given operation       | Foreign Key from operation       |
|                                           | idWorkstationType | NUMBER(10)      | Unique identifier of a given workstation type | Foreign Key from workstationType |

| **_Entity_**        | **_Attribute_**        | **_Data Type_** | **_Description_**                                         | **_Constraints_**                     |                                       
|:--------------------|------------------------|-----------------|-----------------------------------------------------------|:--------------------------------------|
| **workstationType** | idWorkstationType      | NUMBER(10)      | Unique identifier for a workstation type                  | Primary Key                           |
|                     | name                   | VARCHAR2(255)   | The name of a workstation type                            |                                       |
|                     | maximumExecutionTime   | NUMBER(10)      | The maximum execution time of a workstation type          |                                       |
|                     | setupTime              | NUMBER(10)      | The time needed for the setup of a given workstation type |                                       |
|                     | idLegacyIdentification | NUMBER(10)      | Unique identifier of a given legacy identification        | Foreign Key from legacyIdentification |

| **_Entity_**    | **_Attribute_**        | **_Data Type_** | **_Description_**                            | **_Constraints_**                     |                                       
|:----------------|------------------------|-----------------|----------------------------------------------|:--------------------------------------|
| **workstation** | idWorkstation          | NUMBER(10)      | Unique identifier for a workstation          | Primary Key                           |
|                 | name                   | VARCHAR2(255)   | The name of a given workstation              |                                       |
|                 | description            | VARCHAR2(255)   | The description of a workstation             |                                       |
|                 | idWorkstationType      | NUMBER(10)      | Unique identifier of a workstation type      | Foreign Key from workstationType      |
|                 | idFacility             | NUMBER(10)      | Unique identifier of a facility              | Foreign Key from facility             |
|                 | idMachine              | NUMBER(10)      | Unique identifier of a machine               | Foreign Key from machine              |
|                 | idWorkstationKind      | NUMBER(10)      | Unique identifier of a workstation kind      | Foreign Key from workstationKind      |
|                 | idLegacyIdentification | NUMBER(10)      | Unique identifier of a legacy Identification | Foreign Key form legacyIdentification |

| **_Entity_**        | **_Attribute_**   | **_Data Type_** | **_Description_**                           | **_Constraints_** |                                       
|:--------------------|-------------------|-----------------|---------------------------------------------|:------------------|
| **workstationKind** | idWorkstationKind | NUMBER(10)      | Unique identifier for a kind of workstation | Primary Key       |
|                     | name              | VARCHAR2(255)   | The name of a given workstation kind        |                   |

| **_Entity_** | **_Attribute_** | **_Data Type_** | **_Description_**                     | **_Constraints_** |                                       
|:-------------|-----------------|-----------------|---------------------------------------|:------------------|
| **facility** | idFacility      | NUMBER(10)      | Unique identifier of a given facility | Primary Key       |
|              | name            | VARCHAR2(255)   | The name of the facility              |                   |
|              | type            | VARCHAR2(255)   | The type of facility                  |                   |
|              | idAddress       | NUMBER(10)      | Unique identifier of an address       |                   |

| **_Entity_** | **_Attribute_**        | **_Data Type_** | **_Description_**                               | **_Constraints_**                     |                                       
|:-------------|------------------------|-----------------|-------------------------------------------------|:--------------------------------------|
| **machine**  | idMachine              | NUMBER(10)      | Unique identifier of a given machine            | Primary Key                           |
|              | description            | VARCHAR2(255)   | The description of a given machine              |                                       |
|              | idMachineType          | NUMBER(10)      | Unique identifier of the type of machine        | Foreign Key from machineType          |
|              | idLegacyIdentification | NUMBER(10)      | Unique identifier for the legacy Identification | Foreign Key from legacyIdentification |

| **_Entity_**    | **_Attribute_**        | **_Data Type_** | **_Description_**                               | **_Constraints_**                     |                                       
|:----------------|------------------------|-----------------|-------------------------------------------------|:--------------------------------------|
| **machineType** | idMachineType          | NUMBER(10)      | Unique identifier of a given machine type       | Primary Key                           |
|                 | name                   | VARCHAR2(255)   | The name of a given machine type                |                                       |
|                 | idLegacyIdentification | NUMBER(10)      | Unique identifier for the legacy Identification | Foreign Key from legacyIdentification |
