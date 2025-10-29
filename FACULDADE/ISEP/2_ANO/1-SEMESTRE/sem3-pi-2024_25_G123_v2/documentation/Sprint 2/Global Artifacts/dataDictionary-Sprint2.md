# Data Dictionary

| **_Entity_**  | **_Attribute_**    | **_Data Type_** | **_Description_**                                      | **_Constraints_**        |                                       
|:--------------|--------------------|-----------------|--------------------------------------------------------|:-------------------------|
| **Customer**  | customer_id        | NUMBER(10)      | Unique identifier for the customer                     | Primary Key              |
|               | customer_name      | VARCHAR2(255)   | The Customer´s name                                    |                          |
|               | phone_number       | VARCHAR2(50)    | The Customer's phone number                            |                          |
|               | email              | VARCHAR2(255)   | The Customer's email                                   |                          |
|               | IBAN               | VARCHAR2(255)   | The Customer's International Bank Account Number       |                          |
|               | NIF                | NUMBER(9)       | Taxpayer identification number                         |                          |
|               | customer_type      | VARCHAR2(255)   | The type of customer                                   |                          |
|               | address_id         | NUMBER(10)      | Customer's address                                     | Foreign key from Address |
|               | customer_legacy_id | VARCHAR2(255)   | Unique identifier of the customer on the legacy system |                          |


| **_Entity_** | **_Attribute_**     | **_Data Type_** | **_Description_**                                   | **_Constraints_**         |                                       
|:-------------|---------------------|-----------------|-----------------------------------------------------|:--------------------------|
| **Order**    | order_id            | NUMBER(10)      | Unique identifier of the order                      | Primary Key               |
|              | customer_id         | NUMBER(10)      | Identifier of the customer                          | Foreign Key from Customer |
|              | order_date          | DATE            | Date the order was placed                           |                           |
|              | delivery_date       | DATE            | Expected delivery date for the order                |                           |
|              | order_status        | VARCHAR2(255)   | Status of the order                                 |                           |
|              | delivery_address_id | NUMBER(10)      | Unique identifier for delivery address of the order | Foreign key from Address  |


| **_Entity_**         | **_Attribute_**     | **_Data Type_** | **_Description_**                                      | **_Constraints_**          |                                       
|:---------------------|---------------------|-----------------|--------------------------------------------------------|:---------------------------|
| **Production order** | production_order_id | NUMBER(10)      | Unique identifier for the production of an order       | Primary Key                |
|                      | Orderorder_id       | NUMBER(10)      | Unique identifier of an order                          | Foreign key from Order     |
|                      | variant_id          | NUMBER(10)      | Unique identifier for the variant of a product         | Foreign key from Variants  |
|                      | priority            | VARCHAR2(255)   | Priority for the production of an order                |                            |

| **_Entity_** | **_Attribute_** | **_Data Type_** | **_Description_**                       | **_Constraints_**         |                                       
|:-------------|-----------------|-----------------|-----------------------------------------|:--------------------------|
| **Address**  | address_id      | NUMBER(10)      | Unique identifier for the address       | Primary Key               |
|              | Street          | VARCHAR2(255)   | Street name of the address              |                           |
|              | Floor or Apt    | VARCHAR2(255)   | Floor of the address or apartment floor |                           |
|              | ZipCode         | VARCHAR2(20)    | Zip Code of the address                 |                           |
|              | Town            | VARCHAR2(255)   | Town's name of the address              |                           |
|              | City            | VARCHAR2(255)   | City's name of the address              |                           |
|              | Country         | VARCHAR2(255)   | Country's name of the address           |                           |

| **_Entity_**            | **_Attribute_**      | **_Data Type_**   | **_Description_**                              | **_Constraints_**         |                                       
|:------------------------|----------------------|-------------------|------------------------------------------------|:--------------------------|
| **Ordered items**       | order_id             | NUMBER(10)        | Unique identifier for the address              | Foreign Key from Order    |
|                         | variant_id           | NUMBER(10)        | Unique identifier for the variant of a product | Foreign Key from Variants |
|                         | quantity             | NUMBER(10)        | Quantity of items in an order                  |                           |
|                         | unidade              | VARCHAR2(255)     | The unit of the  ordered item                  |                           |

| **_Entity_**           | **_Attribute_**     | **_Data Type_** | **_Description_**                                  | **_Constraints_**          |                                       
|:-----------------------|---------------------|-----------------|----------------------------------------------------|:---------------------------|
| **Bill of operations** | boo_id              | NUMBER(10)      | Unique identifier for a given Bill of Operations   | Primary Key                |
|                        | finished_variant_id | NUMBER(10)      | Unique identifier for a finished variant           | Foreign Key from Variants  |
|                        | operation_id        | NUMBER(10)      | Unique identifier for a given operation            | Foreign Key from Operation |
|                        | input_id            | NUMBER(10)      | Unique identifier for a given input                | Foreign Key from Input     |
|                        | output_id           | NUMBER(10)      | Unique identifier for a given output               | Foreign Key from Output    |
|                        | sequence_number     | NUMBER(10)      | The sequence number for a given bill of operations |

| **_Entity_** | **_Attribute_** | **_Data Type_** | **_Description_**                    | **_Constraints_**          |                                       
|:-------------|-----------------|-----------------|--------------------------------------|:---------------------------|
| **Input**    | input_id        | NUMBER(10)      | Unique identifier for a given input  | Primary Key                |

| **_Entity_** | **_Attribute_** | **_Data Type_** | **_Description_**                    | **_Constraints_**          |                                       
|:-------------|-----------------|-----------------|--------------------------------------|:---------------------------|
| **Output**   | output_id       | NUMBER(10)      | Unique identifier for a given output | Primary Key                |

| **_Entity_**          | **_Attribute_** | **_Data Type_**   | **_Description_**                      | **_Constraints_**         |                                       
|:----------------------|-----------------|-------------------|----------------------------------------|:--------------------------|
| **Inputted Variants** | input_id        | NUMBER(10)        | Unique identifier for a given input    | Foreign Key from Input    |
|                       | variant_id      | NUMBER(10)        | Unique identifier for a given variant  | Foreign Key from Variants |
|                       | quantity        | NUMBER(10)        | The quantity of a given variant        |                           |
|                       | unit            | NUMBER(10)        | The unit of a given inputted variant   |                           |

| **_Entity_**        | **_Attribute_** | **_Data Type_**   | **_Description_**                     | **_Constraints_**         |                                       
|:--------------------|-----------------|-------------------|---------------------------------------|:--------------------------|
| **Output_Variants** | variant_id      | NUMBER(10)        | Unique identifier for a given variant | Foreign Key from Output   |
|                     | output_id       | NUMBER(10)        | Unique identifier for a given output  | Foreign Key from Variants |
|                     | quantity        | NUMBER(10)        | The quantity of a given variant       |                           |
|                     | unit            | NUMBER(10)        | The unit of a given inputted variant  |                           |


| **_Entity_**           | **_Attribute_**              | **_Data Type_**   | **_Description_**                                                      | **_Constraints_**         |                                       
| :--------------------- | ---------------------------- | ----------------- | ---------------------------------------------------------------------- | :------------------       |
| **Workstation type**   | workstation_type_id          | NUMBER(10)        | Unique identifier for type of a workstation                            | Primary Key               |
|                        | workstation_type_name        | VARCHAR2(255)     | Name of the type of a workstation                                      |                           |
|                        | workstation_type_legacy_id   | VARCHAR2(255)     | Unique identifier for the type of a workstation on the legacy system   |                           | 


| **_Entity_** | **_Attribute_** | **_Data Type_** | **_Description_**                  | **_Constraints_**        |                                       
|:-------------|-----------------|-----------------|------------------------------------|:-------------------------|
| **Facility** | facility_id     | NUMBER(10)      | Unique identifier for the facility | Primary Key              |
|              | facility_name   | VARCHAR2(255)   | The name of the facility           |                          |
|              | facility_type   | VARCHAR2(255)   | The type of facility               |                          |
|              | address_id      | NUMBER(10)      | Unique identifier of an address    | Foreign key from Address |

| **_Entity_**    | **_Attribute_**         | **_Data Type_**    | **_Description_**                                          | **_Constraints_**                 |                                       
|:----------------|-------------------------|--------------------|------------------------------------------------------------|:----------------------------------|
| **Workstation** | workstation_id          | NUMBER(10)         | Unique identifier for a workstation                        | Primary Key                       |
|                 | workstation_name        | VARCHAR2(255)      | Name of a workstation                                      |                                   |
|                 | workstation_description | VARCHAR2(255)      | Description of a workstation                               |                                   |
|                 | workstation_type_id     | NUMBER(10)         | Unique identifier for a workstation type                   | Foreign Key from Workstation      |
|                 | workstation_legacy_id   | VARCHAR2(255)      | Unique identifier for a workstation on the legacy system   |                                   |
|                 | facility_id             | NUMBER(10)         | Unique identifier for a facility                           | Foreign key from Facility         |
|                 | machine_id              | NUMBER(10)         | Unique identifier for a machine                            | Foreign Key from Machine          |
|                 | workstation_kind_id     | VARCHAR2(255)      | Unique identifier for a kind of workstation                | Foreign Key from Workstation Kind |

| **_Entity_** | **_Attribute_**      | **_Data Type_** | **_Description_**                                          | **_Constraints_**             |                                       
|:-------------|----------------------|-----------------|------------------------------------------------------------|:------------------------------|
| **Machine**  | Machine_id           | NUMBER(10)      | Unique identifier for a machine kind                       | Primary Key                   |
|              | machine_description_ | VARCHAR2(255)   | The description of a machine                               |                               |
|              | machine_type_id      | NUMBER(10)      | Unique identifier of a given type of machine               | Foreign Key from Machine Type |
|              | machine_legacy_id    | VARCHAR2(255)   | Unique identifier for a given machine in the legacy system |                               |

| **_Entity_**     | **_Attribute_**        | **_Data Type_** | **_Description_**                                            | **_Constraints_** |                                       
|:-----------------|------------------------|-----------------|--------------------------------------------------------------|:------------------|
| **Machine Type** | Machine_type_id        | NUMBER(10)      | Unique identifier for a type of machine                      | Primary Key       |
|                  | machine_type_name      | VARCHAR2(255)   | The name of a type of machine                                |                   |
|                  | machine_type_legacy_id | VARCHAR2(255)   | Unique identifier for a type of machine in the legacy system |                   |



| **_Entity_**         | **_Attribute_**       | **_Data Type_** | **_Description_**                          | **_Constraints_** |                                       
|:---------------------|-----------------------|-----------------|--------------------------------------------|:------------------|
| **Workstation Kind** | workstation_kind_id   | NUMBER(10)      | Unique identifier for a workstation kind   | Primary Key       |
|                      | workstation_kind_name | NUMBER(10)      | The name of the workstation kind           |                   |

| **_Entity_**                                 | **_Attribute_**     | **_Data Type_** | **_Description_**                           | **_Constraints_**            |                                       
|:---------------------------------------------|---------------------|-----------------|---------------------------------------------|:-----------------------------|
| **Operation_Workstation_Type_Compatibility** | operation_id        | NUMBER(10)      | Unique identifier for an operation          | Foreign Key from Operation   |
|                                              | workstation_type_id | NUMBER(10)      | Unique identifier for a type of workstation | Foreign Key from Workstation |

| **_Entity_**  | **_Attribute_**       | **_Data Type_** | **_Description_**                                      | **_Constraints_** |                                       
|:--------------|-----------------------|-----------------|--------------------------------------------------------|:------------------|
| **Operation** | operation_id          | NUMBER(10)      | Unique identifier for an operation                     | Primary Key       |
|               | legacy_operation_id   | VARCHAR2(255)   | Unique identifier of an operation on the legacy system |                   |
|               | operation_description | VARCHAR2(255)   | Description of the operation                           |                   |
|               | time_required         | NUMBER(10)      | The time required for an operation                     |                   |

| **_Entity_** | **_Attribute_**    | **_Data Type_** | **_Description_**                     | **_Constraints_**         |                                       
|:-------------|--------------------|-----------------|---------------------------------------|:--------------------------|
| **Stock**    | variant_id         | NUMBER(10)      | Unique identifier of a variant        | Foreign Key from Variant  |
|              | quantity_available | NUMBER(10)      | Quantity available of a giver product |                           |


| **_Entity_** | **_Attribute_**     | **_Data Type_** | **_Description_**                                    | **_Constraints_**             |                                       
|:-------------|---------------------|-----------------|------------------------------------------------------|:------------------------------|
| **Variants** | variant_id          | NUMBER(10)      | Unique identifier of a variant                       | Primary Key                   |
|              | variant_description | VARCHAR2(255)   | Description of the variant                           |                               |
|              | variant_type_id     | NUMBER(10)      | Unique identifier of a variant type                  | Foreign Key from Variant Type |
|              | product_id          | NUMBER(10)      | Unique identifier of a product                       | Foreign key from Product      |
|              | variant_legacy_id   | VARCHAR2(255)   | Unique identifier of a variant for the legacy system |                               |

| **_Entity_**     | **_Attribute_**   | **_Data Type_**  | **_Description_**                   | **_Constraints_** |                                       
|:-----------------|-------------------|------------------|-------------------------------------|:------------------|
| **Variant type** | variant_type_id   | NUMBER(10)       | Unique identifier of a variant type | Primary Key       |
|                  | variant_type_name | VARCHAR2(255)    | The name of a type of variant       |                   |


| **_Entity_**                | **_Attribute_** | **_Data Type_** | **_Description_**              | **_Constraints_**               |                                       
|:----------------------------|-----------------|-----------------|--------------------------------|:--------------------------------|
| **Variants_options_values** | variant_id      | NUMBER(10)      | Unique identifier of a variant | Foreign Key from Variants       |
|                             | value_id        | NUMBER(10)      | Unique identifier of a value   | Foreign key from Options Values |

| **_Entity_**       | **_Attribute_** | **_Data Type_** | **_Description_**              | **_Constraints_**                |                                       
|:-------------------|-----------------|-----------------|--------------------------------|:---------------------------------|
| **Options values** | value_id        | NUMBER(10)      | Unique identifier of a value   | Primary Key                      |
|                    | value_name      | VARCHAR2(255)   | Name of a value                |                                  |
|                    | option_id       | NUMBER(10)      | Unique identifier of an option | Foreign key from Product Options |

| **_Entity_**        | **_Attribute_**   | **_Data Type_** | **_Description_**              | **_Constraints_**        |                                       
|:--------------------|-------------------|-----------------|--------------------------------|:-------------------------|
| **Product options** | option_id         | NUMBER(10)      | Unique identifier of an option | Primary Key              |
|                     | option_name       | VARCHAR2(255)   | Name of an option              |                          |

| **_Entity_** | **_Attribute_**     | **_Data Type_** | **_Description_**                                   | **_Constraints_**               |                                       
|:-------------|---------------------|-----------------|-----------------------------------------------------|:--------------------------------|
| **Product**  | product_id          | NUMBER(10)      | Unique identifier of a product                      | Primary Key                     |
|              | product_name        | VARCHAR2(255)   | The name of a product                               |                                 |
|              | category_id         | NUMBER(10)      | Unique identifier of a category                     | Foreign key from Category       |
|              | product_description | VARCHAR2(10)    | Description of a product                            |                                 |
|              | product_legacy_id   | VARCHAR2(10)    | Unique identifier of a product on the legacy system |                                 |
|              | family_id           | NUMBER(10)      | Unique identifier of the family of a product        | Foreign key from Product Family |



| **_Entity_** | **_Attribute_** | **_Data Type_** | **_Description_**                 | **_Constraints_**         |                                       
|:-------------|-----------------|-----------------|-----------------------------------|:--------------------------|
| **Category** | category_id     | NUMBER(10)      | Unique identifier of a category   | Primary Key               |
|              | category_name   | VARCHAR2(255)   | Name of the category of a product |                           |



| **_Entity_**       | **_Attribute_**  | **_Data Type_** | **_Description_**                                                 | **_Constraints_**         |                                       
|:-------------------|------------------|-----------------|-------------------------------------------------------------------|:--------------------------|
| **Product family** | family_id        | NUMBER(10)      | Unique identifier of the family of a product                      | Primary Key               |
|                    | family_name      | VARCHAR2(10)    | The name of the family of a product                               |                           |
|                    | family_legacy_id | NUMBER(10)      | Unique identifier of the family of a product on the legacy system |                           |
