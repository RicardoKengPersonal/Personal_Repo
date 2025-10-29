# US25 - As a GSM, I want to Cancel an entry in the Agenda

## 4. Tests 

**Test 1:** If the vehicle is present in the list, the test passes; otherwise, it fails.

	@Test
    void testAddVehicle() {
        agendaEntry.addVehicle(vehicle);
        ArrayList<Vehicle> vehicles = agendaEntry.getVehicles();
        assertTrue(vehicles.contains(vehicle));
    }
	

**Test 2:** If the actual string representation matches the expected one, the test passes; otherwise, it fails

	@Test
    void testToString() {
        String expected = "Task1 - Task1 Description - 31-12-2024 - GreenSpace1 - email1@mail.com - 31-12-2024 - PENDING";
        String actual = agendaEntry.toString();
        assertEquals(expected, actual);
    }

_It is also recommended to organize this content by subsections._ 


## 5. Construction (Implementation)

### Class CreateTaskController 

```java
public Task createTask(String reference, String description, String informalDescription, String technicalDescription,
                       Integer duration, Double cost, String taskCategoryDescription) {

	TaskCategory taskCategory = getTaskCategoryByDescription(taskCategoryDescription);

	Employee employee = getEmployeeFromSession();
	Organization organization = getOrganizationRepository().getOrganizationByEmployee(employee);

	newTask = organization.createTask(reference, description, informalDescription, technicalDescription, duration,
                                      cost,taskCategory, employee);
    
	return newTask;
}
```

### Class Organization

```java
public Optional<Task> createTask(String reference, String description, String informalDescription,
                                 String technicalDescription, Integer duration, Double cost, TaskCategory taskCategory,
                                 Employee employee) {
    
    Task task = new Task(reference, description, informalDescription, technicalDescription, duration, cost,
                         taskCategory, employee);

    addTask(task);
        
    return task;
}
```


## 6. Integration and Demo 

* A new option on the Employee menu options was added.

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a