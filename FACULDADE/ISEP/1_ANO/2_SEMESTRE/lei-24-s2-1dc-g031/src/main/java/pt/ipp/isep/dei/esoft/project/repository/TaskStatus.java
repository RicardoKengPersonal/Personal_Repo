package pt.ipp.isep.dei.esoft.project.repository;

import java.io.Serializable;

/**
 * The AgendaEntryStatus enum represents the status of a collaborator.
 */
public enum TaskStatus implements Serializable {
    PENDING,
    PLANNED,
    CANCELLED,
    DONE
}
