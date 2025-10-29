package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Repository class for managing jobs.
 */
public class JobsRepository implements Serializable {

    private final ArrayList<Job> jobsArrayList = new ArrayList<>();

    /**
     * Gets the list of jobs.
     *
     * @return The list of jobs.
     */
    public ArrayList<Job> getJobsArrayList() {
        return jobsArrayList;
    }

    /**
     * Checks if a job already exists in the repository.
     *
     * @param jobName The name of the job to check.
     * @return True if the job already exists, false otherwise.
     */
    private boolean jobAlreadyExist(String jobName) {
        for (Job job : jobsArrayList) {
            if (job.getjobName().equals(jobName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a job name is valid.
     *
     * @param jobName The name of the job to check.
     * @return True if the job name is valid, false otherwise.
     */
    private boolean jobNameIsValid(String jobName) {
        String regex = "^[a-zA-Z\\s]+$";
        return jobName.matches(regex);
    }

    /**
     * Adds a new job to the repository.
     *
     * @param jobName The name of the job to add.
     */
    public boolean addJob(String jobName) {
        Job job = new Job(jobName);
        if (!jobAlreadyExist(job.getjobName()) && jobNameIsValid(job.getjobName())){
            jobsArrayList.add(job);
            return true;
        };
        return false;
    }
}
