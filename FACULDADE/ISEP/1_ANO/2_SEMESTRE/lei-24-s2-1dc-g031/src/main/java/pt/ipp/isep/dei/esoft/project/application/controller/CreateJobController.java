/**
 * The CreateJobController class represents a controller for creating jobs.
 */
package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.JobsRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class CreateJobController {
    private JobsRepository jobsRepository;

    /**
     * Constructs a new CreateJobController and initializes the jobs repository.
     */
    public CreateJobController() {
        this.jobsRepository = getJobsRepository();
    }

    private JobsRepository getJobsRepository() {
        if (jobsRepository == null) {
            Repositories repositories = Repositories.getInstance();
            jobsRepository = repositories.getInstance().getJobsRepository();
        }
        return jobsRepository;
    }

    /**
     * Creates a new job with the specified name.
     *
     * @param jobName The name of the job to be created.
     * @return True if the job is successfully created, false otherwise.
     */
    public boolean createJob(String jobName) {
        return jobsRepository.addJob(jobName);
    }
}
