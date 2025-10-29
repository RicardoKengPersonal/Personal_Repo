package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.repository.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TaskStatus;

import java.util.ArrayList;

/**
 * This class is responsible for controlling the operations related to the tasks.
 * It interacts with the repositories to fetch and manipulate data.
 */
public class MyTasksController {

    private Agenda agenda;

    /**
     * Default constructor for the MyTasksController class.
     * Initializes the agenda.
     */
    public MyTasksController() {
        this.agenda = getAgenda();
    }

    /**
     * Returns the Agenda.
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
     * Returns the agenda entries of the current user with a specific status.
     * @param status The status of the agenda entries.
     * @return ArrayList of AgendaEntry objects.
     */
    public ArrayList<AgendaEntry> getMyAgendaEntries(TaskStatus status) {
        String collabEmail = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().getEmail();
        return agenda.getAgendaEntriesByUserEmail(collabEmail, status);
    }

    /**
     * Returns the agenda entries of the current user with a specific status and within a specific date range.
     * @param status The status of the agenda entries.
     * @param startDate The start date of the date range.
     * @param endDate The end date of the date range.
     * @return ArrayList of AgendaEntry objects.
     */
    public ArrayList<AgendaEntry> getMyAgendaEntries(TaskStatus status, String startDate, String endDate) {
        String collabEmail = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().getEmail();
        return agenda.getAgendaEntriesByUserEmailAndDateRange(collabEmail, status, startDate, endDate);
    }

    /**
     * Returns all the agenda entries of the current user.
     * @return ArrayList of AgendaEntry objects.
     */
    public ArrayList<AgendaEntry> getMyAgendaEntries() {
        String collabEmail = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().getEmail();
        return agenda.getAgendaEntriesByUserEmail(collabEmail);
    }
}