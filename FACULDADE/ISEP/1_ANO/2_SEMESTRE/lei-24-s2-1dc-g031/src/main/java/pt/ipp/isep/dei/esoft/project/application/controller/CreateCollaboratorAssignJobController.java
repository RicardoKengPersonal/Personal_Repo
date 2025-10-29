/**
 * The CreateCollaboratorAssignJobController class represents a controller for creating collaborators and assigning them to jobs.
 */
package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.DocumentType;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.Arrays;

public class CreateCollaboratorAssignJobController {
    private JobsRepository jobsRepository;
    private CollaboratorsRepository collaboratorsRepository;
    AuthenticationRepository authenticationRepository;


    /**
     * Constructs a new CreateCollaboratorAssignJobController and initializes the repositories.
     */
    public CreateCollaboratorAssignJobController() {
        this.jobsRepository = getJobsRepository();
        this.collaboratorsRepository = getCollaboratorsRepository();
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    private CollaboratorsRepository getCollaboratorsRepository() {
        if (collaboratorsRepository == null) {
            Repositories repositories = Repositories.getInstance();
            collaboratorsRepository = repositories.getInstance().getCollaboratorsRepository();
        }
        return collaboratorsRepository;
    }

    private JobsRepository getJobsRepository() {
        if (jobsRepository == null) {
            Repositories repositories = Repositories.getInstance();
            jobsRepository = repositories.getInstance().getJobsRepository();
        }
        return jobsRepository;
    }

    /**
     * Retrieves a list of all jobs from the repository.
     *
     * @return ArrayList of Job objects.
     */
    public ArrayList<Job> getJobsList() {
        return jobsRepository.getJobsArrayList();
    }

    /**
     * Retrieves a list of all collaborators from the repository.
     *
     * @return ArrayList of Collaborator objects.
     */
    public ArrayList<Collaborator> getCollaboratorsList() {
        return collaboratorsRepository.getCollaboratorsArrayList();
    }

    /**
     * Retrieves a list of all document types from the repository.
     *
     * @return ArrayList of DocumentTypeRepository objects.
     */
    public ArrayList<DocumentType> getDocTypesList() {
        return new ArrayList<>(Arrays.asList(DocumentType.values()));
    }

    /**
     * Adds a new collaborator with the specified details and assigns them to a job.
     *
     * @param name                      The name of the collaborator.
     * @param documentIdentificationNum The document identification number of the collaborator.
     * @param taxPayerNumber            The tax payer number of the collaborator.
     * @param email                     The email of the collaborator.
     * @param mobileNumber              The mobile number of the collaborator.
     * @param address                   The address of the collaborator.
     * @param admissionDate             The admission date of the collaborator.
     * @param birthDate                 The birth date of the collaborator.
     * @param identificationDocumentType The type of identification document.
     * @param chosenJob                 The job to which the collaborator will be assigned.
     * @return True if the collaborator is successfully added and assigned to the job, false otherwise.
     */
    public boolean addCollaborator(String name, String documentIdentificationNum, String taxPayerNumber, String email, int mobileNumber, String address, String admissionDate, String birthDate, DocumentType identificationDocumentType, Job chosenJob) {
        boolean result = collaboratorsRepository.addCollaborator(name, documentIdentificationNum, taxPayerNumber, email, mobileNumber, address, admissionDate, birthDate, identificationDocumentType, chosenJob);
        if(result) {
            authenticationRepository.addUserWithRole(name, email, "password", AuthenticationController.ROLE_COLLAB);
        }
        return result;
    }
}
