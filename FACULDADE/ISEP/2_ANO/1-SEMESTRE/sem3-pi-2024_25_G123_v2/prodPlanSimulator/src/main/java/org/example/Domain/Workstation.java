package org.example.Domain;

import java.util.*;

/**
 * Represents a workstation that performs a specific operation on articles.
 * The workstation maintains a schedule for time blocks and manages a queue of articles.
 */
public class Workstation {
    private String idWorkstation;
    private String operation;
    private int time;
    private List<Article> queuedArticles;
    private TimeScheduler timeScheduler;
    private Map<Integer, Map<Article, Integer>> schedulerBlock;

    /**
     * Constructs a Workstation with the specified ID, operation, and time.
     * Initializes a TimeScheduler with the specified time as the block duration.
     *
     * @param idWorkstation the workstation ID
     * @param operation     the operation performed by the workstation
     * @param time          the time block duration for the workstation in minutes
     *
     */
    public Workstation(String idWorkstation, String operation, int time) {
        this.idWorkstation = idWorkstation;
        this.operation = operation;
        this.time = time;
        this.queuedArticles = new ArrayList<>();
        timeScheduler = new TimeScheduler(time);
        this.schedulerBlock = new HashMap<>();
    }

    /**
     * Returns the TimeScheduler associated with this workstation.
     *
     * @return the TimeScheduler
     */
    public TimeScheduler getTimeScheduler() {
        return timeScheduler;
    }

    /**
     * Checks the time until the workstation is next available, starting from a given time.
     *
     * @param currentTime the time to check availability from
     * @return the time until the workstation is available, or -1 if unavailable
     */
        public int checkWorkstationTimeAvailable(int currentTime) {
            // Get the keys of the schedulerBlock map
            List<Integer> keys = new ArrayList<>(schedulerBlock.keySet());
            Collections.sort(keys);

            if (keys.isEmpty()) {
                // If no tasks are scheduled, the workstation is available immediately
                return 0;
            }

            for (int i = 0; i < keys.size(); i++) {
                int start = keys.get(i);
                int end = start + time;

                // If the workstation is available now, return 0
                if (i < keys.size() - 1) {
                    int nextStart = keys.get(i + 1);
                    if (end <= currentTime && nextStart - end >= time) {
                        return end - currentTime; // Return the time until the next available block
                    }
                } else if (end <= currentTime) {
                    // If the last block is available now, return 0
                    return end - currentTime;
                }
            }

            // If no available blocks were found, return the time until the next block
            return keys.get(keys.size() - 1) + time - currentTime;
        }

    public void addArticleOrder(Article article, int currentTime, int operationIndex) {
        int availableStartTime = currentTime + checkWorkstationTimeAvailable(currentTime);
        schedulerBlock.put(availableStartTime, Map.of(article, operationIndex));
    }

    /**
     * Returns the list of articles currently queued at the workstation.
     *
     * @return the queued articles
     */
    public List<Article> getQueuedProductionOrders() {
        return queuedArticles;
    }

    /**
     * Sets the list of articles queued at the workstation.
     *
     * @param queuedArticles the list of articles to queue
     */
    public void setQueuedProductionOrders(List<Article> queuedArticles) {
        this.queuedArticles = queuedArticles;
    }

    /**
     * Returns the operation this workstation performs.
     *
     * @return the operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Sets the operation this workstation performs.
     *
     * @param operation the operation
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * Returns the time block duration for this workstation.
     *
     * @return the time block duration
     */
    public int getTime() {
        return time;
    }

    /**
     * Sets the time block duration for this workstation.
     *
     * @param time the time block duration
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Returns the workstation ID.
     *
     * @return the workstation ID
     */
    public String getIdWorkstation() {
        return idWorkstation;
    }

    /**
     * Sets the workstation ID.
     *
     * @param idWorkstation the workstation ID
     */
    public void setIdWorkstation(String idWorkstation) {
        this.idWorkstation = idWorkstation;
    }

    /**
     * Schedules an article for processing on the workstation at the next available time block.
     *
     * @param article                the article to process
     * @param order                  the order name or ID for the operation
     * @param previousOperationEndTime the end time of the previous operation
     * @throws RuntimeException if no available blocks can be scheduled for the operation
     */
    public void addArticle(Article article, String order, int previousOperationEndTime) throws RuntimeException {
        int timeUntilAvailable = timeScheduler.timeUntilAvailable(previousOperationEndTime);

        if (timeUntilAvailable != -1) {
            // Schedule the production order in the next available block
            Timer nextAvailableTimer = timeScheduler.getTimer().stream()
                    .filter(timer -> timer.startTime == previousOperationEndTime + timeUntilAvailable)
                    .findFirst()
                    .orElse(null);

            if (nextAvailableTimer != null) {
                nextAvailableTimer.operation = order;
                nextAvailableTimer.article = article;
            }
        } else {
            System.out.println("No available blocks for Production Order " + article.getIdItem() + " on Workstation " + idWorkstation);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workstation that = (Workstation) o;
        return time == that.time && Objects.equals(idWorkstation, that.idWorkstation) && Objects.equals(operation, that.operation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idWorkstation, operation, time);
    }

    /**
     * Returns a string representation of the Workstation.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "Workstation{" + "idWorkstation='" + idWorkstation + '\'' + ", operation='" + operation + '\'' + ", time=" + time + '}';
    }
}
