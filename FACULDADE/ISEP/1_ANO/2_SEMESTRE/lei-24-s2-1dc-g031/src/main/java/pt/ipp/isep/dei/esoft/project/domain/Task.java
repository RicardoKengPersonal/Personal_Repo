package pt.ipp.isep.dei.esoft.project.domain;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pt.ipp.isep.dei.esoft.project.repository.TaskStatus;
import pt.ipp.isep.dei.esoft.project.repository.TaskUrgencyDegree;

import java.io.Serializable;

/**
 * This class represents a Task in the system.
 * It contains information about the task such as description, task name, green space, estimated duration, task status, and task urgency degree.
 */
public class Task implements Serializable {
    private String description;
    private String taskName;
    private GreenSpace greenSpace;
    private int estimatedDuration;
    private TaskStatus taskStatus;
    private TaskUrgencyDegree taskUrgencyDegree;

    /**
     * Constructor for the Task class.
     * @param description The description of the task.
     * @param taskName The name of the task.
     * @param greenSpace The green space of the task.
     * @param estimatedDuration The estimated duration of the task.
     * @param taskUrgencyDegree The urgency degree of the task.
     */
    public Task(String description, String taskName, GreenSpace greenSpace, int estimatedDuration, TaskUrgencyDegree taskUrgencyDegree) {
        validateString(description);
        validateString(taskName);
        validateGreenSpace(greenSpace);
        this.description = description;
        this.taskName = taskName;
        this.greenSpace = greenSpace;
        this.estimatedDuration = estimatedDuration;
        this.taskUrgencyDegree = taskUrgencyDegree;
        taskStatus = TaskStatus.PENDING;
    }

    /**
     * Validates a string.
     * @param str The string to be validated.
     * @throws IllegalArgumentException if the string is not valid.
     */
    private void validateString(String str) {
        if (str == null || !str.matches("^[a-zA-Z0-9 ]*$")) {
            throw new IllegalArgumentException("Invalid input. Only alphanumeric characters and spaces are allowed.");
        }
    }

    /**
     * Validates a green space.
     * @param greenSpace The green space to be validated.
     * @throws IllegalArgumentException if the green space is not valid.
     */
    private void validateGreenSpace(GreenSpace greenSpace) {
        if (greenSpace == null) {
            throw new IllegalArgumentException("GreenSpace cannot be null.");
        }
    }

    /**
     * Gets the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     * @param description The new description of the task.
     */
    public void setDescription(String description) {
        validateString(description);
        this.description = description;
    }

    /**
     * Gets the name of the task.
     * @return The name of the task.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Sets the name of the task.
     * @param taskName The new name of the task.
     */
    public void setTaskName(String taskName) {
        validateString(taskName);
        this.taskName = taskName;
    }

    /**
     * Gets the green space of the task.
     * @return The green space of the task.
     */
    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    /**
     * Sets the green space of the task.
     * @param greenSpace The new green space of the task.
     */
    public void setGreenSpace(GreenSpace greenSpace) {
        validateGreenSpace(greenSpace);
        this.greenSpace = greenSpace;
    }

    /**
     * Gets the status of the task.
     * @return The status of the task.
     */
    public Object getTaskStatus() {
        return taskStatus;
    }

    /**
     * Sets the status of the task.
     * @param taskStatus The new status of the task.
     */
    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * Returns a string representation of the task.
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return taskName + " - " + description + " - " + greenSpace + " - " + taskStatus + " - " + estimatedDuration + " - " + taskUrgencyDegree;
    }

    /**
     * Returns a string property of the task name.
     * @return A string property of the task name.
     */
    public StringProperty taskNameProperty() {
        return new SimpleStringProperty(this.taskName);
    }

    /**
     * Returns a string property of the task.
     * @return A string property of the task.
     */
    public StringProperty toStringProperty() {
        return new SimpleStringProperty(this.toString());
    }

    /**
     * Gets the estimated duration of the task.
     * @return The estimated duration of the task.
     */
    public int getEstimatedDuration() {
        return estimatedDuration;
    }

    /**
     * Sets the estimated duration of the task.
     * @param estimatedDuration The new estimated duration of the task.
     */
    public void setEstimatedDuration(int estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }
}