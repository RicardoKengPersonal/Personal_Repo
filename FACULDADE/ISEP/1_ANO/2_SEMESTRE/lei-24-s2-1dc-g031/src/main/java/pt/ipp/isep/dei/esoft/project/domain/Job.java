/**
 * The Job class represents a job in the project.
 */
package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

public class Job implements Serializable {
    private String jobName;

    /**
     * Constructs a new Job with the specified name.
     *
     * @param jobName The name of the job.
     */
    public Job(String jobName) {
        this.jobName = jobName;
    }

    /**
     * Retrieves the name of the job.
     *
     * @return The name of the job.
     */
    public String getjobName() {
        return jobName;
    }

    /**
     * Sets the name of the job.
     *
     * @param jobName The name of the job to be set.
     */
    public void setjobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * Checks if the job name equals the given job name.
     *
     * @param jobName The job name to be compared.
     * @return True if the job names are equal, false otherwise.
     */
    public boolean equals(String jobName) {
        return this.getjobName().equals(jobName);
    }

    /**
     * Returns a string representation of the job.
     *
     * @return A string representation of the job.
     */
    @Override
    public String toString() {
        return "Job name: " + jobName;
    }
}
