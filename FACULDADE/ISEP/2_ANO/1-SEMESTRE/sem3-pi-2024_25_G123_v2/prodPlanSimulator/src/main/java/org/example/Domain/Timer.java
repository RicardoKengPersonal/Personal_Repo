package org.example.Domain;

/**
 * Represents a timer block with start and end times for an operation on an article.
 * Each timer is associated with a unique ID, an operation, and an article.
 */
public class Timer {
    int id;
    public int startTime;
    public int endTime;
    public String operation;
    public Article article;

    /**
     * Constructs a Timer with the specified ID, start time, and end time.
     *
     * @param id        the unique identifier for the timer
     * @param startTime the start time of the timer
     * @param endTime   the end time of the timer
     */
    public Timer(int id, int startTime, int endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a string representation of the Timer, including ID, start time,
     * end time, associated operation, and article.
     *
     * @return a string representation of the Timer
     */
    @Override
    public String toString() {
        return "Timer{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", operation='" + operation + '\'' +
                ", article=" + article +
                '}';
    }
}
