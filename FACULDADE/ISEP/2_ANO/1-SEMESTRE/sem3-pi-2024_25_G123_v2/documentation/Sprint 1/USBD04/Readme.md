# USBD04 - As a Product Owner, I want to import data from a legacy system, on a spreadsheet, and load it into the new system.

## 1. Requirements Engineering

### 1.1. User Story Description

As a Product Owner, I want to import data from a legacy system and deliver it on a spreadsheet, in order to facilitate
data analysis, reporting, or further processing in a familiar format like Excel.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

> The legacy system contains valuable data that needs to be extracted, transformed (if necessary), and presented in a
> format that can be easily read and manipulated, such as Excel. The customer expects that the data imported from the
> legacy system should be accurate, complete, and organized in a manner that makes sense for further use.

**From the client clarifications:**

> **Question:** I did not find it clear what is supposed to be done. Should we be capable of exporting the DB contents
> to excel/spreadsheet? And i do not understand with "Automatic generation of SQL input code from the spreadsheet", are
> we supposed to import data from or to a spreadsheet?
>
> **Answer:** This user story covers an ETL process.\
> You will be provided a spreedsheet with data from a legacy system and you will have to import that data into the new
> database model you are developing. The data will not be normalized and will not fit directly into the new model. You
> will be required to process that data in order to be able to load it into the new system.
> 
>**Question:**
Hello, in the BOM sheet provided in the legacy system file the parts don't have a name, only a description. 
> Assuming that the parts are also products, how are we able to turn them into products in the new data base system 
> without knowing their name?
> 
> **Answer:** Not all parts are products. If a part is also a product, I would design the model so that the ProductID 
> of that product is the same as the PartNumber (one to one relationship between Product and Part).
> 
> **Question:** Good afternoon, we are having some trouble understanding how we should import the data from the legacy 
> system. So, basically, in the assignment, we were told that a customer should have their contact information, which is
> not present in the datasheet, we were also told that the identifier of a customer should be the "VAT"/"NIF", 
> should we completely ignore the clientID? We were also told that the customers could be individuals or enterprises, 
> and the datasheet also lacks that information.
>
> **Answer:** Do whatever you have to do to load the data into the new system. If some data is mandatory in the new 
> system and is absent in the legacy data, you may have to add it.

### 1.3. Acceptance Criteria

* **AC1:** Minimum expected requirement: manual creation of the data input scripts.
* **AC2:** Minimum requirement above the expected: automatic generation of SQL input code from the spreadsheet (e.g.,
  Excel formulas, scripts in any other language, etc.).

### 1.4. Found out Dependencies

* There is a dependency on "USBD03 - As a Product Owner, I want the relational model to be instantiated (physical
  level).", as there needs to be a database for the old data to be imported to.

### 1.5 Input and Output Data
**Input Data**
* Data imported from the legacy system (Excel document).

**Output Data**
* Created Database with the SQL scripts for inserts, selects and alters.
### 1.6. Other Relevant Remarks

* The approach can either be manual or automated based on the scope and complexity of the data extraction.
* Performance considerations may include how quickly the data can be extracted and formatted.