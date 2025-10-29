package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Task;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents a ToDoList in the system.
 * It contains a list of Task objects and provides methods to add and retrieve tasks.
 */
public class ToDoList implements Serializable {
    private final ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Adds a task to the to-do list.
     * @param task The task to be added.
     * @return true if the task was added successfully, false otherwise.
     */
    public boolean addTask(Task task) {
        if (!taskAlreadyExists(task)) {
            tasks.add(task);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if a task already exists in the to-do list.
     * @param task The task to be checked.
     * @return true if the task already exists, false otherwise.
     */
    private boolean taskAlreadyExists(Task task) {
        for (Task t : tasks) {
            if (t.equals(task)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the list of tasks in the to-do list.
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves the list of pending tasks in the to-do list.
     * @return The list of pending tasks.
     */
    public ArrayList<Task> getPendingTasks() {
        ArrayList<Task> pendingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTaskStatus().equals(TaskStatus.PENDING)) {
                pendingTasks.add(task);
            }
        }
        return pendingTasks;
    }
}