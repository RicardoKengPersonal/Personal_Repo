package org.example.ProductionSimulator;

import org.example.Domain.Article;
import org.example.Domain.OperationScheduled;
import org.example.Domain.Timer;
import org.example.Domain.Workstation;
import org.example.FileIO.Files;
import org.example.Utils.TimeFormatter;

import java.util.*;
/**
 * PrioritySimulator class that simulates based on priority.
 */

public class PrioritySimulator {

    private List<Article> articleList;
    private Map<String, Workstation> workstations;
    private List<OperationScheduled> priorityOperationsScheduled = new ArrayList<>();

    /**
     * Constructor to initialize articles and workstations for the priority simulator.
     */
    public PrioritySimulator() {
        // Load articles and workstations from file
        this.articleList = Files.readArticles("prodPlanSimulator/src/main/java/resources/articles.csv");
        this.workstations = Files.readWorkstations("prodPlanSimulator/src/main/java/resources/workstations.csv");
    }

    /**
     * Compute the simulation, assigning articles to workstations based on priority.
     */
    public void computePrioritySimulation() {
        // Sort articles by priority (high to low)
        articleList.sort(Comparator.comparingInt(Article::getPriority).reversed());

        int id = 0;
        for (Article article : articleList) {
            List<String> operationList = article.getOperations();
            int previousOperationEndingTime = 0;
            int operationNumber = 0;

            for (String operation : operationList) {
                // Find the best workstation based on priority (fastest available workstation for the operation)
                Workstation bestWorkstation = findBestWorkstationByPriority(operation, previousOperationEndingTime);

                if (bestWorkstation != null) {
                    bestWorkstation.addArticle(article, operation, previousOperationEndingTime);

                    Timer timer = bestWorkstation.getTimeScheduler().getTimer().stream()
                            .filter(b -> b.article == article && b.operation.equals(operation))
                            .findFirst().orElse(null);

                    if (timer != null) {
                        int startingTime = timer.startTime;
                        int endingTime = timer.endTime;

                        priorityOperationsScheduled.add(new OperationScheduled(
                                id, operation, bestWorkstation.getIdWorkstation(), startingTime, endingTime,
                                operationNumber, article.getIdItem()
                        ));
                        previousOperationEndingTime = endingTime;
                        operationNumber++;
                    }
                } else {
                    System.out.println("No suitable workstation found for Operation " + operation);
                }
            }
            id++;
        }
    }

    /**
     * Find the best workstation for a given operation based on priority.
     * @param operation the operation to be performed
     * @param previousOperationEndTime the end time of the previous operation
     * @return the best available workstation, or null if none are available
     */

    private Workstation findBestWorkstationByPriority(String operation, int previousOperationEndTime) {
        List<Workstation> suitableWorkstations = getSuitableWorkstations(operation);

        // Find the closest available workstation based on the previous operation's end time
        return suitableWorkstations.stream()
                .min(Comparator.comparingInt(ws -> Math.abs(ws.getTimeScheduler().timeUntilAvailable(previousOperationEndTime))))
                .stream().min(Comparator.comparingInt(Workstation::getTime))
                .orElse(null);
    }

    /**
     * Get a list of suitable workstations for the specified operation.
     * @param operation the operation to filter workstations by
     * @return a sorted list of suitable workstations that can perform the operation
     */

    private List<Workstation> getSuitableWorkstations(String operation) {
        List<Workstation> bestWorkstations = new ArrayList<>();
        for (Workstation workstation : workstations.values()) {
            if (workstation.getOperation().equals(operation)) {
                bestWorkstations.add(workstation);
            }
        }
        // Sort workstations by their processing time
        bestWorkstations.sort(Comparator.comparingInt(Workstation::getTime));
        return bestWorkstations;
    }

    /**
     * Display the scheduled operations table.
     */
    public void displayScheduledOperationsTable() {
        System.out.println("+------------+------------+----------------+-----------------------+-------------------------+");
        System.out.println("| Article ID | Operation  | Workstation ID | Expected Start Time   | Expected End Time       |");
        System.out.println("+------------+------------+----------------+-----------------------+-------------------------+");
        for (OperationScheduled schedule : priorityOperationsScheduled) {
            String formattedStartTime = TimeFormatter.formatTime(schedule.getStartTime());
            String formattedEndTime = TimeFormatter.formatTime(schedule.getEndTime());

            System.out.printf("| %-10s | %-10s | %-14s | %-21s | %-23s |\n",
                    schedule.getType(), schedule.getOperation(), schedule.getWorkstationId(),
                    formattedStartTime, formattedEndTime);
        }
        System.out.println("+------------+------------+----------------+-----------------------+-------------------------+");
    }

    /**
     * Display the total time per article type.
     */
    public void displayTotalTimePerArticleType() {
        Map<String, Integer> totalTimePerArticleType = new HashMap<>();

        for (OperationScheduled schedule : priorityOperationsScheduled) {
            String articleType = schedule.getType();
            int operationTime = schedule.getEndTime() - schedule.getStartTime();
            totalTimePerArticleType.put(articleType,
                    totalTimePerArticleType.getOrDefault(articleType, 0) + operationTime);
        }

        System.out.println("+------------+-------------------------+");
        System.out.println("| Article    | Expected Total Time      |");
        System.out.println("+------------+-------------------------+");
        for (Map.Entry<String, Integer> entry : totalTimePerArticleType.entrySet()) {
            String formattedTotalTime = TimeFormatter.formatTime(entry.getValue());
            System.out.printf("| %-10s | %-23s |\n", entry.getKey(), formattedTotalTime);
        }
        System.out.println("+------------+-------------------------+");
    }

    /**
     * Display the time spent per operation.
     */
    public void timeSpentPerOperation() {
        HashMap<String, Integer> map = new HashMap<>();

        for (OperationScheduled schedule : priorityOperationsScheduled) {
            String operation = schedule.getOperation();
            int timeSpent = schedule.getEndTime() - schedule.getStartTime();
            map.put(operation, map.getOrDefault(operation, 0) + timeSpent);
        }

        System.out.println("+------------+-------------------------+");
        System.out.println("| Operation  |   Expected Total Time   |");
        System.out.println("+------------+-------------------------+");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String formattedTotalTime = TimeFormatter.formatTime(entry.getValue());
            System.out.printf("| %-10s | %-23s |\n", entry.getKey(), formattedTotalTime);
        }
        System.out.println("+------------+-------------------------+");
    }

    /**
     * Display the total time spent per operation.
     */
    public void displayWorkstationOperationTimes() {
        Map<String, Integer> totalWorkstationTime = new HashMap<>();
        int totalExecutionTime = 0;

        for (OperationScheduled schedule : priorityOperationsScheduled) {
            String workstationId = schedule.getWorkstationId();
            int operationTime = schedule.getEndTime() - schedule.getStartTime();

            totalWorkstationTime.put(workstationId,
                    totalWorkstationTime.getOrDefault(workstationId, 0) + operationTime);
            totalExecutionTime += operationTime;
        }

        System.out.println("+----------------+------------------+-----------------+");
        System.out.println("| Workstation ID | Total Time (min) | % of Total Time |");
        System.out.println("+----------------+------------------+-----------------+");

        for (Map.Entry<String, Integer> entry : totalWorkstationTime.entrySet()) {
            String workstationId = entry.getKey();
            int workstationTime = entry.getValue();
            double percentageOfTotalTime = ((double) workstationTime / totalExecutionTime) * 100;
            String formattedTotalTime = TimeFormatter.formatTime(workstationTime);

            System.out.printf("| %-14s | %-16s | %-15.2f |\n", workstationId, formattedTotalTime, percentageOfTotalTime);
        }

        System.out.println("+----------------+------------------+-----------------+");
    }
}
