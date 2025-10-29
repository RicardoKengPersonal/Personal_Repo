package pt.ipp.isep.dei.esoft.project.domain;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents an entry in an agenda.
 * It contains a task, a team, a date, a list of vehicles and an entry status.
 */
public class AgendaEntry implements Serializable {
    Task associatedTask;
    Team team;
    String date;
    ArrayList<Vehicle> vehicles;

    /**
     * Constructor for the AgendaEntry class.
     * @param task The task associated with the agenda entry.
     * @param date The date of the agenda entry.
     */
    public AgendaEntry(Task task, String date) {
        this.associatedTask = task;
        setDate(date);
    }

    /**
     * Adds a vehicle to the agenda entry.
     * @param vehicle The vehicle to be added.
     */
    public void addVehicle(Vehicle vehicle) {
        if (vehicles == null) {
            vehicles = new ArrayList<>();
        }
        vehicles.add(vehicle);
    }

    /**
     * Returns a string representation of the agenda entry.
     * @return A string representation of the agenda entry.
     */
    @Override
    public String toString() {
        return associatedTask.getTaskName() + " - " + associatedTask.getDescription() + " - " + date + " - " + associatedTask.getGreenSpace().getGreenSpaceName() + " - " + associatedTask.getGreenSpace().getGreenSpaceManagerEmail() + " - " + this.date + " - " + this.associatedTask.getTaskStatus();
    }

    /**
     * Returns the description of the task associated with the agenda entry.
     * @return The description of the task associated with the agenda entry.
     */
    public String getDescription() {
        return associatedTask.getDescription();
    }

    /**
     * Sets the description of the task associated with the agenda entry.
     * @param description The new description of the task.
     */
    public void setDescription(String description) {
        this.associatedTask.setDescription(description);
    }

    /**
     * Returns the team associated with the agenda entry.
     * @return The team associated with the agenda entry.
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Sets the team associated with the agenda entry.
     * @param team The new team associated with the agenda entry.
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * Returns the date of the agenda entry.
     * @return The date of the agenda entry.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the agenda entry.
     * @param newDate The new date of the agenda entry.
     */
    public void setDate(String newDate) {
        validateDate(newDate);
        this.date = newDate;
    }

    /**
     * Returns the vehicles associated with the agenda entry.
     * @return The vehicles associated with the agenda entry.
     */
    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     * Returns the task associated with the agenda entry.
     * @return The task associated with the agenda entry.
     */
    public Task getAssociatedTask() {
        return associatedTask;
    }

    /**
     * Checks if the given date is in the correct format (dd-MM-yyyy).
     * @param date The date to be checked.
     * @return true if the date is in the correct format, false otherwise.
     */
    boolean isValidDateFormat(String date) {
        String regex = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-(19|20)\\d\\d$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    /**
     * Validates the date of the agenda entry.
     * The date must be in the format dd-MM-yyyy, must be in the future, and must be after the existing date.
     * @param newDate The new date of the agenda entry.
     * @throws IllegalArgumentException if the date is not valid.
     */
    void validateDate(String newDate) {
        if (!isValidDateFormat(newDate)) {
            throw new IllegalArgumentException("Invalid date format. Date must be in the format dd-MM-yyyy.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate newLocalDate = LocalDate.parse(newDate, formatter);
        LocalDate currentDate = LocalDate.now();

        if (newLocalDate.isBefore(currentDate)) {
            throw new IllegalArgumentException("New date must be in the future.");
        }

        if (this.date != null) {
            LocalDate existingLocalDate = LocalDate.parse(this.date, formatter);
            if (newLocalDate.isBefore(existingLocalDate)) {
                throw new IllegalArgumentException("New date must be after the existing date.");
            }
        }
    }

    /**
     * Returns a string property representation of the agenda entry.
     * @return A string property representation of the agenda entry.
     */
    public StringProperty toStringProperty() {
        return new SimpleStringProperty(this.toString());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgendaEntry that = (AgendaEntry) o;
        return Objects.equals(associatedTask, that.associatedTask) &&
                Objects.equals(date, that.date) &&
                Objects.equals(associatedTask.getGreenSpace(), that.associatedTask.getGreenSpace());
    }

    @Override
    public int hashCode() {
        return Objects.hash(associatedTask, date, associatedTask.getGreenSpace());
    }

}