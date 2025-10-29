package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * This class is responsible for controlling the operations related to the agenda.
 * It interacts with the repositories to fetch and manipulate data.
 */
public class AgendaController {

    private Agenda agenda;
    private TeamsRepository teamsRepository;
    private GreenSpacesRepository greenSpacesRepository;
    private VehicleRepository vehicleRepository;

    /**
     * Default constructor for the AgendaController class.
     * Initializes the agenda and repositories.
     */
    public AgendaController() {
        this.agenda = getAgenda();
        this.teamsRepository = Repositories.getInstance().getTeamsRepository();
        this.greenSpacesRepository = Repositories.getInstance().getGreenSpaceRepository();
        this.vehicleRepository = Repositories.getInstance().getVehicleRepository();
    }

    /**
     * Returns the active agenda.
     * @return Agenda object.
     */
    private Agenda getAgenda() {
        if (agenda == null) {
            Repositories repositories = Repositories.getInstance();
            agenda = repositories.getInstance().getAgenda();
        }
        return agenda;
    }

    /**
     * Returns the tasks in the to-do list.
     * @return ArrayList of Task objects.
     */
    public ArrayList<Task> getToDoListTasks() {
        return Repositories.getInstance().getToDoList().getPendingTasks();
    }

    /**
     * Postpones an agenda entry to a new date.
     * @param agendaEntry The AgendaEntry to be postponed.
     * @param newDate The new date for the agenda entry.
     * @return true if the operation was successful, false otherwise.
     */
    public boolean postponeAgendaEntry(AgendaEntry agendaEntry, String newDate) {
        try {
            Task task = agendaEntry.getAssociatedTask();
            Team team = agendaEntry.getTeam();
            AgendaEntry newAgendaEntry = new AgendaEntry(task, newDate);

            if (team != null) {
                for (Collaborator collaborator : team.getCollaborators()) {
                    if (!isCollaboratorAvailable(collaborator, newAgendaEntry)) {
                        throw new IllegalArgumentException(collaborator.getName() + " is not available on the new date, he might have already been assigned to a team working on that day.");
                    }
                }
            }

            agendaEntry.setDate(newDate);
            return true;
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Returns the teams.
     * @return ArrayList of Team objects.
     */
    public ArrayList<Team> getTeams(){
        return teamsRepository.getTeams();
    }

    /**
     * Returns the vehicles.
     * @return ArrayList of Vehicle objects.
     */
    public ArrayList<Vehicle> getVehicles() {
        return vehicleRepository.getAllVehicles();
    }

    /**
     * Cancels a task.
     * @param task The Task to be cancelled.
     */
    public void cancelTask(Task task) {
        task.setTaskStatus(TaskStatus.CANCELLED);
    }

    /**
     * Checks if a team is available for a new agenda entry.
     * @param team The Team to be checked.
     * @param newAgendaEntry The new AgendaEntry.
     * @return true if the team is available, false otherwise.
     */
    public boolean isTeamAvailable(Team team, AgendaEntry newAgendaEntry) {
        try {

            if (team != null) {
                for (Collaborator collaborator : team.getCollaborators()) {
                    if (!isCollaboratorAvailable(collaborator, newAgendaEntry)) {
                        System.out.println("A collaborator in the team is not available on the new date.");
                        return false;
                    }
                }
            } else {
                System.out.println("The team is not assigned.");
                return false;
            }
            return true;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Checks if a collaborator is available for a new agenda entry.
     * @param collaborator The Collaborator to be checked.
     * @param newAgendaEntry The new AgendaEntry.
     * @return true if the collaborator is available, false otherwise.
     */
    public boolean isCollaboratorAvailable(Collaborator collaborator, AgendaEntry newAgendaEntry) {
        String collabEmail = collaborator.getEmail();
        ArrayList<AgendaEntry> agendaEntries = agenda.getAgendaEntriesByUserEmail(collabEmail, TaskStatus.PLANNED);

        String newAgendaEntryDate = newAgendaEntry.getDate();
        int newTaskDuration = newAgendaEntry.getAssociatedTask().getEstimatedDuration();

        LocalDate newTaskStartDate = LocalDate.parse(newAgendaEntryDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate newTaskEndDate = calculateTaskEndDate(newTaskStartDate, newTaskDuration);

        ArrayList<DaySchedule> usedMinutesPerDay = new ArrayList<>();

        for (AgendaEntry agendaEntry : agendaEntries) {
            LocalDate agendaEntryStartDate = LocalDate.parse(agendaEntry.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            int existingTaskDuration = agendaEntry.getAssociatedTask().getEstimatedDuration();
            LocalDate agendaEntryEndDate = calculateTaskEndDate(agendaEntryStartDate, existingTaskDuration);

            LocalDate current = agendaEntryStartDate;
            while (!current.isAfter(agendaEntryEndDate)) {
                addMinutesToSchedule(usedMinutesPerDay, current, Math.min(existingTaskDuration, 480));
                existingTaskDuration -= 480;
                current = current.plusDays(1);
            }
        }

        LocalDate current = newTaskStartDate;
        int remainingDuration = newTaskDuration;
        while (!current.isAfter(newTaskEndDate)) {
            int usedMinutes = getUsedMinutesForDate(usedMinutesPerDay, current);
            if (usedMinutes + Math.min(remainingDuration, 480) > 480) {
                throw new IllegalArgumentException(collaborator.getName() + " already has a task on that day.");
            }
            remainingDuration -= 480;
            current = current.plusDays(1);
        }
        return true;
    }

    /**
     * Adds minutes to the schedule.
     * @param schedule The schedule to add minutes to.
     * @param date The date to add minutes to.
     * @param minutes The minutes to add.
     */
    private void addMinutesToSchedule(ArrayList<DaySchedule> schedule, LocalDate date, int minutes) {
        for (DaySchedule daySchedule : schedule) {
            if (daySchedule.date.equals(date)) {
                daySchedule.minutes += minutes;
                return;
            }
        }
        schedule.add(new DaySchedule(date, minutes));
    }

    /**
     * Gets the used minutes for a specific date.
     * @param schedule The schedule to get the used minutes from.
     * @param date The date to get the used minutes for.
     * @return The used minutes for the specified date.
     */
    private int getUsedMinutesForDate(ArrayList<DaySchedule> schedule, LocalDate date) {
        for (DaySchedule daySchedule : schedule) {
            if (daySchedule.date.equals(date)) {
                return daySchedule.minutes;
            }
        }
        return 0;
    }

    /**
     * Calculates the end date of a task.
     * @param startDate The start date of the task.
     * @param durationInMinutes The duration of the task in minutes.
     * @return The end date of the task.
     */
    private LocalDate calculateTaskEndDate(LocalDate startDate, int durationInMinutes) {
        int totalDays = durationInMinutes / 480;
        if (durationInMinutes % 480 > 0) {
            totalDays++;
        }
        return startDate.plusDays(totalDays - 1);
    }

    public ArrayList<AgendaEntry> getActiveAgendaEntries() {
        return agenda.getActiveAgendaEntries();
    }

    /**
     * A class representing a day's schedule.
     */
    private static class DaySchedule {
        LocalDate date;
        int minutes;

        /**
         * Constructs a new DaySchedule with the specified date and minutes.
         * @param date The date of the DaySchedule.
         * @param minutes The minutes of the DaySchedule.
         */
        DaySchedule(LocalDate date, int minutes) {
            this.date = date;
            this.minutes = minutes;
        }
    }

    /**
     * Returns the unassigned vehicles for an agenda entry.
     * @param ae The AgendaEntry.
     * @return ArrayList of unassigned Vehicle objects.
     */
    public ArrayList<Vehicle> getUnassignedVehicles(AgendaEntry ae) {
        ArrayList<Vehicle> unassignedVehicles = new ArrayList<>();
        for (Vehicle vehicle : getVehicles()) {
            if(ae.getVehicles() == null){
                unassignedVehicles.add(vehicle);
            }else if (!ae.getVehicles().contains(vehicle)) {
                unassignedVehicles.add(vehicle);
            }
        }
        return unassignedVehicles;
    }

    /**
     * Adds an agenda entry.
     * @param task The Task for the agenda entry.
     * @param entryDate The date for the agenda entry.
     * @return true if the operation was successful, false otherwise.
     */
    public boolean addAgendaEntry(Task task, String entryDate) {
        boolean result = agenda.addAgendaEntry(task, entryDate);
        if(result){
            task.setTaskStatus(TaskStatus.PLANNED);
        }
        return result;
    }

    public boolean assignTeamToAgendaEntry(AgendaEntry agendaEntry, Team selectedTeam) {
        try {

            if (isTeamAvailable(selectedTeam, agendaEntry)) {
                agendaEntry.setTeam(selectedTeam);
                return true;
            } else {
                return false;
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}