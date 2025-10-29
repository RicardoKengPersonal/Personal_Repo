package org.example.Dto;

/**
 * Represents statistics for a workstation, including total operational time and
 * the percentage of this time relative to a larger time frame.
 */
public class WorkstationStats {
    private String workstationId;
    private int totalTime;
    private double percentageOfTotalTime;

    /**
     * Constructs a WorkstationStats object with specified workstation ID, total time, and percentage of total time.
     *
     * @param workstationId       the ID of the workstation
     * @param totalTime           the total time spent on the workstation in minutes
     * @param percentageOfTotalTime the percentage of the total operational time this workstation occupies
     */
    public WorkstationStats(String workstationId, int totalTime, double percentageOfTotalTime) {
        this.workstationId = workstationId;
        this.totalTime = totalTime;
        this.percentageOfTotalTime = percentageOfTotalTime;
    }

    /**
     * Returns the workstation ID.
     *
     * @return the workstation ID
     */
    public String getWorkstationId() {
        return workstationId;
    }

    /**
     * Returns the total operational time for the workstation.
     *
     * @return the total time in minutes
     */
    public int getTotalTime() {
        return totalTime;
    }

    /**
     * Returns the percentage of the total operational time that this workstation occupies.
     *
     * @return the percentage of total time
     */
    public double getPercentageOfTotalTime() {
        return percentageOfTotalTime;
    }
}
