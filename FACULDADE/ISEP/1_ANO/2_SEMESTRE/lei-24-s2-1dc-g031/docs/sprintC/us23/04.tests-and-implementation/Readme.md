# US23 - Assign a Team to an Agenda Entry

## 4. Tests

### Class AgendaControllerTest

**Test 1:** Check that a team can be successfully assigned to an agenda entry.

```java
@Test
void testAssignTeamToAgendaEntry() {
    AgendaController controller = new AgendaController();
    AgendaEntry entry = new AgendaEntry();
    Team team = new Team();

    assertTrue(controller.assignTeamToAgendaEntry(entry, team));
    assertEquals(team, entry.getTeam());
}

```
### Class AgendaControllerTest
Test 2: Check that an agenda entry can be successfully added to an agenda.

```java
@Test
void testAddAgendaEntry() {
    assertTrue(agenda.addAgendaEntry(task, "31-12-2024"));
    assertEquals(1, agenda.getActiveAgendaEntries().size());
}
```
Test 3: Check that a duplicate agenda entry cannot be added to an agenda.

```java
@Test
void testAddAgendaEntry_Duplicate() {
    agenda.addAgendaEntry(task, "31-12-2024");
    assertThrows(IllegalArgumentException.class, () -> agenda.addAgendaEntry(task, "31-12-2024"));
}
```

Test 4: Check that the agenda entries by user email can be successfully retrieved.

```java
@Test
void testGetAgendaEntriesByUserEmail() {
    Collaborator collaborator = new Collaborator("John Doe", "johndoe@mail.com");
    team.getCollaborators().add(collaborator);
    task.setTeam(team);
    agenda.addAgendaEntry(task, "31-12-2024");
    assertEquals(1, agenda.getAgendaEntriesByUserEmail("johndoe@mail.com").size());
}
```


Test 5: Check that the agenda entries by user email and date range can be successfully retrieved.

```java
@Test
void testGetAgendaEntriesByUserEmailAndDateRange() {
    Collaborator collaborator = new Collaborator("John Doe", "johndoe@mail.com");
    team.getCollaborators().add(collaborator);
    task.setTeam(team);
    agenda.addAgendaEntry(task, "31-12-2024");
    assertEquals(1, agenda.getAgendaEntriesByUserEmailAndDateRange("johndoe@mail.com", TaskStatus.PENDING, "01-01-2024", "31-12-2025").size());
}
```

Test 6: Check that the agenda entries by user email and task status can be successfully retrieved.

```java
@Test
void testGetAgendaEntriesByUserEmailAndTaskStatus() {
    Collaborator collaborator = new Collaborator("John Doe", "johndoe@mail.com");
    team.getCollaborators().add(collaborator);
    task.setTeam(team);
    agenda.addAgendaEntry(task, "31-12-2024");
    assertEquals(1, agenda.getAgendaEntriesByUserEmail("johndoe@mail.com", TaskStatus.PENDING).size());
}
```    

## 5. Construction (Implementation)

### Class AgendaController

```java
public class AgendaController {
    // Other methods...

    public boolean assignTeamToAgendaEntry(AgendaEntry agendaEntry, Team selectedTeam) {
        if (isTeamAvailable(selectedTeam, agendaEntry)) {
            agendaEntry.setTeam(selectedTeam);
            return true;
        } else {
            return false;
        }
    }
}
```

### Class AgendaGUI

```java
@FXML
private void handleAssignTeamButtonAction() {
    // Get the selected item from the agendaEntryList
    AgendaEntry selectedEntry = agendaEntryList.getSelectionModel().getSelectedItem();

    // Call the assignTeam method on the selected item
    if (selectedEntry != null) {
        // Get the list of teams
        ArrayList<Team> teams = agendaController.getTeams();

        // Show a ChoiceDialog for the user to select a team
        ChoiceDialog<Team> dialog = new ChoiceDialog<>(null, teams);
        dialog.setTitle("Assign Team");
        dialog.setHeaderText("Select a team to assign to the agenda entry:");
        dialog.setContentText("Team:");

        // Show the ChoiceDialog and get the user's choice
        Optional<Team> result = dialog.showAndWait();
        if (result.isPresent()) {
            Team selectedTeam = result.get();

            // Check if the selected team is available
            if (agendaController.assignTeamToAgendaEntry(selectedEntry, selectedTeam)) {
                // Show a success alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Team successfully assigned to the agenda entry.");
                alert.showAndWait();
                // Refresh the list of agenda entries
                populateEntries();
            } else {
                // Show an error alert
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Team Unavailable");
                alert.setContentText("The selected team is not available for the duration of the task.");
                alert.showAndWait();
            }
        }
    }
}
```

### 6. Integration and Demo
 - A new option on the GSM menu options was added to assign a team to an agenda entry.
 - For demo purposes, some teams and agenda entries are bootstrapped while the system starts.

### 7. Observations
 - n/a.