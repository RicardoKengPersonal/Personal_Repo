# US24 - Postpone an Entry in the Agenda to a Specific Future Date

## 4. Tests

### Class AgendaControllerTest

**Test 1:** Check that an agenda entry can be successfully postponed to a future date.

```java
@Test
void testPostponeAgendaEntry() {
    AgendaController controller = new AgendaController();
    AgendaEntry entry = new AgendaEntry();
    String futureDate = "31-12-2024";

    assertTrue(controller.postponeAgendaEntry(entry, futureDate));
    assertEquals(futureDate, entry.getDate());
}
```

### Class AgendaTest

**Test 2:** Check that an agenda entry can be successfully added to an agenda.
    
```java
@Test
void testAddAgendaEntry() {
    assertTrue(agenda.addAgendaEntry(task, "31-12-2024"));
    assertEquals(1, agenda.getActiveAgendaEntries().size());
}
```

**Test 3:** Check that a duplicate agenda entry cannot be added to an agenda.

```java
@Test
void testAddAgendaEntry_Duplicate() {
    agenda.addAgendaEntry(task, "31-12-2024");
    assertThrows(IllegalArgumentException.class, () -> agenda.addAgendaEntry(task, "31-12-2024"));
}
```

## 5. Construction (Implementation)

### Class AgendaController

```java
public boolean postponeAgendaEntry(AgendaEntry entry, String futureDate) {
    if (entry == null || futureDate == null || futureDate.isEmpty()) {
        return false;
    }

    entry.setDate(futureDate);
    return true;
}
```

### Class AgendaGUI

```java
@FXML
private void handlePostponeButtonAction() {
    // Get the selected item from the agendaEntryList
    AgendaEntry selectedEntry = agendaEntryList.getSelectionModel().getSelectedItem();

    // Call the postponeAgendaEntry method on the selected item
    if (selectedEntry != null) {
        // Show a TextInputDialog for the user to enter a future date
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Postpone Agenda Entry");
        dialog.setHeaderText("Enter a future date to postpone the agenda entry to:");
        dialog.setContentText("Date:");

        // Show the TextInputDialog and get the user's input
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String futureDate = result.get();

            // Check if the future date is valid
            if (agendaController.postponeAgendaEntry(selectedEntry, futureDate)) {
                // Show a success alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Agenda entry successfully postponed to " + futureDate + ".");
                alert.showAndWait();
                // Refresh the list of agenda entries
                populateEntries();
            } else {
                // Show an error alert
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Invalid Date");
                alert.setContentText("The entered date is not a valid future date.");
                alert.showAndWait();
            }
        }
    }
}
```

## 6. Integration and Demo

 - A new option on the GSM menu options was added to postpone an agenda entry to a specific future date.
 - For demo purposes, some agenda entries are bootstrapped while the system starts.

## 7. Observations

 - n/a

