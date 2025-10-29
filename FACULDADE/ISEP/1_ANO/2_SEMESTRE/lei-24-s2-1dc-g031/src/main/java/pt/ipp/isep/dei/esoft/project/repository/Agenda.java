package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * This class represents an Agenda in the system.
 * It contains a list of AgendaEntry objects and provides methods to add and retrieve entries.
 */
public class Agenda implements Serializable {

    public final ArrayList<AgendaEntry> agendaEntries = new ArrayList<>();

    public ArrayList<AgendaEntry> getActiveAgendaEntries(){
        ArrayList<AgendaEntry> activeAgendaEntries = new ArrayList<>();
        for(AgendaEntry agendaEntry : agendaEntries){
            if(agendaEntry.getAssociatedTask().getTaskStatus() == TaskStatus.PENDING || agendaEntry.getAssociatedTask().getTaskStatus() == TaskStatus.PLANNED){
                activeAgendaEntries.add(agendaEntry);
            }
        }
        return activeAgendaEntries;
    }

    /**
     * Adds an AgendaEntry to the agenda.
     * @param task The task associated with the entry.
     * @param date The date of the entry.
     * @return true if the entry was added successfully, false otherwise.
     * @throws IllegalArgumentException if an entry for the given date and green space already exists.
     */
    public boolean addAgendaEntry(Task task, String date) {
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, date);
            for(AgendaEntry agendaEntryAux : agendaEntries){
                if(agendaEntryAux.equals(agendaEntry)){
                    throw new IllegalArgumentException("Agenda Entry already exists for that date and green space.");
                }
            }
            agendaEntries.add(agendaEntry);
            return true;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Retrieves a list of AgendaEntry objects associated with a given user email.
     * @param email The email of the user.
     * @return A list of AgendaEntry objects associated with the given user email.
     */
    public ArrayList<AgendaEntry> getAgendaEntriesByUserEmail(String email) {
        ArrayList<AgendaEntry> agendaEntriesByUserEmail = new ArrayList<>();
        ArrayList<Collaborator> collaborators = new ArrayList<>();
        for (AgendaEntry agendaEntry : this.agendaEntries) {
            Team team = agendaEntry.getTeam();
            if (team != null) {
                collaborators = team.getCollaborators();
                for (Collaborator collaborator: collaborators) {
                    if (collaborator.getEmail().equals(email)) {
                        agendaEntriesByUserEmail.add(agendaEntry);
                    }
                }
            }
        }
        Collections.sort(agendaEntriesByUserEmail, Comparator.comparing(AgendaEntry::getDate));
        return agendaEntriesByUserEmail;
    }

    /**
     * Retrieves a list of AgendaEntry objects associated with a given user email and within a given date range.
     * @param email The email of the user.
     * @param status The status of the task.
     * @param startDateString The start date of the range.
     * @param endDateString The end date of the range.
     * @return A list of AgendaEntry objects associated with the given user email and within the given date range.
     * @throws IllegalArgumentException if the date format is invalid or if an error occurs while retrieving the entries.
     */
    public ArrayList<AgendaEntry> getAgendaEntriesByUserEmailAndDateRange(String email, TaskStatus status ,String startDateString, String endDateString) {
        try{
            ArrayList<AgendaEntry> agendaEntriesByUserEmailAndDateRange = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate startDate = LocalDate.parse(startDateString, formatter);
            LocalDate endDate = LocalDate.parse(endDateString, formatter);

            ArrayList<AgendaEntry> agendaEntriesByUserEmail = getAgendaEntriesByUserEmail(email);
            for (AgendaEntry agendaEntry : agendaEntriesByUserEmail) {
                LocalDate entryDate = LocalDate.parse(agendaEntry.getDate(), formatter);
                if ((entryDate.isAfter(startDate) || entryDate.isEqual(startDate)) && (entryDate.isBefore(endDate) || entryDate.isEqual(endDate)) && agendaEntry.getAssociatedTask().getTaskStatus() == status){
                    agendaEntriesByUserEmailAndDateRange.add(agendaEntry);
                }
            }
            Collections.sort(agendaEntriesByUserEmailAndDateRange, Comparator.comparing(AgendaEntry::getDate));
            return agendaEntriesByUserEmailAndDateRange;
        }catch (DateTimeParseException e){
            throw new IllegalArgumentException("Invalid date format. Date must be in the format dd-MM-yyyy.");
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Retrieves a list of AgendaEntry objects associated with a given user email and task status.
     * @param email The email of the user.
     * @param status The status of the task.
     * @return A list of AgendaEntry objects associated with the given user email and task status.
     */
    public ArrayList<AgendaEntry> getAgendaEntriesByUserEmail(String email, TaskStatus status) {
        ArrayList<AgendaEntry> agendaEntriesByUserEmail = new ArrayList<>();
        ArrayList<Collaborator> collaborators = new ArrayList<>();
        for (AgendaEntry agendaEntry : this.agendaEntries) {
            if(agendaEntry.getAssociatedTask().getTaskStatus() == status){
                Team team = agendaEntry.getTeam();
                if (team != null) {
                    collaborators = team.getCollaborators();
                    for (Collaborator collaborator: collaborators) {
                        if (collaborator.getEmail().equals(email)) {
                            agendaEntriesByUserEmail.add(agendaEntry);
                        }
                    }
                }
            }
        }
        Collections.sort(agendaEntriesByUserEmail, Comparator.comparing(AgendaEntry::getDate));
        return agendaEntriesByUserEmail;
    }
}