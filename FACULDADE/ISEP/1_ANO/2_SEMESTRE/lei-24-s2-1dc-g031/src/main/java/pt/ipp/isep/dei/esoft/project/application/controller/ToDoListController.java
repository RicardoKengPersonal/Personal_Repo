package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is responsible for controlling the operations related to the to-do list.
 * It interacts with the repositories to fetch and manipulate data.
 */
public class ToDoListController {

    private GreenSpacesRepository greenSpacesRepository;
    private ToDoList toDoList;

    /**
     * Default constructor for the ToDoListController class.
     * Initializes the toDoList and greenSpacesRepository.
     */
    public ToDoListController() {
        this.toDoList = getToDoList();
        this.greenSpacesRepository = getGreenSpaceRepository();
    }

    /**
     * Returns the GreenSpacesRepository.
     * @return GreenSpacesRepository object.
     */
    private GreenSpacesRepository getGreenSpaceRepository() {
        if (greenSpacesRepository == null) {
            Repositories repositories = Repositories.getInstance();
            greenSpacesRepository = repositories.getInstance().getGreenSpaceRepository();
        }
        return greenSpacesRepository;
    }

    /**
     * Returns the green spaces of the current user.
     * @return ArrayList of GreenSpace objects.
     */
    public ArrayList<GreenSpace> getMyGreenSpaces() {
        String managerEmail = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().getEmail();
        return greenSpacesRepository.getGreenSpaceByManagerEmail(managerEmail);
    }

    /**
     * Returns the ToDoList.
     * @return ToDoList object.
     */
    private ToDoList getToDoList() {
        if (toDoList == null) {
            Repositories repositories = Repositories.getInstance();
            toDoList = repositories.getInstance().getToDoList();
        }
        return toDoList;
    }

    /**
     * Returns the tasks in the to-do list.
     * @return ArrayList of Task objects.
     */
    public ArrayList<Task> getToDoListTasks() {
        return toDoList.getTasks();
    }

    /**
     * Adds a task to the to-do list.
     * @param taskName The name of the task.
     * @param taskDescription The description of the task.
     * @param taskDuration The duration of the task.
     * @param greenSpace The green space associated with the task.
     * @param taskUrgencyDegree The urgency degree of the task.
     * @return true if the operation was successful, false otherwise.
     */
    public boolean addTask(String taskName, String taskDescription, int taskDuration, GreenSpace greenSpace, TaskUrgencyDegree taskUrgencyDegree) {
        Task task = new Task(taskDescription, taskName, greenSpace, taskDuration, taskUrgencyDegree);
        return toDoList.addTask(task);
    }

    /**
     * Returns the urgency degrees.
     * @return ArrayList of TaskUrgencyDegree objects.
     */
    public ArrayList<TaskUrgencyDegree> getUrgencyDegrees() {
        return new ArrayList<>(Arrays.asList(TaskUrgencyDegree.values()));
    }
}