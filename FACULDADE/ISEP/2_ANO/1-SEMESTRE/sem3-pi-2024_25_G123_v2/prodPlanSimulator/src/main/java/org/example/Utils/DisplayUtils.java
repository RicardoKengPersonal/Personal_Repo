package org.example.Utils;

import org.example.Domain.OperationScheduled;
import org.example.Dto.WorkstationStats;
import org.example.structures.graph.pert.PertVertex;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Utility class for displaying various statistics and tables related to the production simulation.
 */
public class DisplayUtils {

    /**
     * Prints a formatted table displaying scheduled operations.
     *
     * @param operationsScheduled the list of scheduled operations to display
     */
    public static void printScheduledOperationsTable(List<OperationScheduled> operationsScheduled) {
        System.out.println("+------------+------------+----------------+-----------------------+-------------------------+");
        System.out.println("| Article ID | Operation  | Workstation ID | Expected Start Time   | Expected End Time       |");
        System.out.println("+------------+------------+----------------+-----------------------+-------------------------+");
        for (OperationScheduled schedule : operationsScheduled) {
            String formattedStartTime = TimeFormatter.formatTime(schedule.getStartTime());
            String formattedEndTime = TimeFormatter.formatTime(schedule.getEndTime());

            System.out.printf("| %-10s | %-10s | %-14s | %-21s | %-23s |\n", schedule.getType(), schedule.getOperation(), schedule.getWorkstationId(), formattedStartTime, formattedEndTime);
        }
        System.out.println("+------------+------------+----------------+-----------------------+-------------------------+");
    }

    /**
     * Prints a formatted table showing the total time expected for each article type.
     *
     * @param totalTimePerArticleType a map containing article types and their corresponding total expected time
     */
    public static void printTotalTimePerArticleType(Map<String, Integer> totalTimePerArticleType) {
        System.out.println("+------------+-------------------------+");
        System.out.println("| Article    | Expected Total Time     |");
        System.out.println("+------------+-------------------------+");
        for (Map.Entry<String, Integer> entry : totalTimePerArticleType.entrySet()) {
            String formattedTotalTime = TimeFormatter.formatTime(entry.getValue());
            System.out.printf("| %-10s | %-23s |\n", entry.getKey(), formattedTotalTime);
        }
        System.out.println("+------------+-------------------------+");
    }

    /**
     * Prints a formatted table displaying the time spent on each operation.
     *
     * @param operationTimeMap a map containing operations and their corresponding total time spent
     */
    public static void printTimeSpentPerOperation(Map<String, Integer> operationTimeMap) {
        System.out.println("+------------+-------------------------+");
        System.out.println("| Operation  | Expected Total Time     |");
        System.out.println("+------------+-------------------------+");
        for (Map.Entry<String, Integer> entry : operationTimeMap.entrySet()) {
            String formattedTotalTime = TimeFormatter.formatTime(entry.getValue());
            System.out.printf("| %-10s | %-23s |\n", entry.getKey(), formattedTotalTime);
        }
        System.out.println("+------------+-------------------------+");
    }

    /**
     * Prints a formatted table showing the operation times and percentages for each workstation.
     *
     * @param workstationStats a list of statistics for each workstation
     */
    public static void printWorkstationOperationTimes(List<WorkstationStats> workstationStats) {
        System.out.println("+----------------+------------------+-----------------+");
        System.out.println("| Workstation ID | Total Time       | % of Total Time |");
        System.out.println("+----------------+------------------+-----------------+");

        for (WorkstationStats stats : workstationStats) {
            String formattedTotalTime = TimeFormatter.formatTime(stats.getTotalTime());
            System.out.printf("| %-14s | %-16s | %-15.2f |\n",
                    stats.getWorkstationId(),
                    formattedTotalTime,
                    stats.getPercentageOfTotalTime());
        }

        System.out.println("+----------------+------------------+-----------------+");
    }

    /**
     * Prints a formatted table displaying the average execution and waiting times for each operation.
     *
     * @param averageExecutionTime a map containing operations and their average execution times
     * @param averageWaitingTime a map containing operations and their average waiting times
     */
    public static void printAverageExecutionAndWaitingTimes(Map<String, Double> averageExecutionTime, Map<String, Double> averageWaitingTime) {
        System.out.println("+------------+------------------+------------------+");
        System.out.println("| Operation  | Average Execution | Average Waiting   |");
        System.out.println("+------------+------------------+------------------+");

        for (Map.Entry<String, Double> entry : averageExecutionTime.entrySet()) {
            String formattedExecutionTime = TimeFormatter.formatTime(entry.getValue().intValue());
            String formattedWaitingTime = TimeFormatter.formatTime(averageWaitingTime.get(entry.getKey()).intValue());
            System.out.printf("| %-10s | %-16s | %-16s |\n", entry.getKey(), formattedExecutionTime, formattedWaitingTime);
        }

        System.out.println("+------------+------------------+------------------+");
    }

    public static void printSortedCalculateDependentCounts(Map<PertVertex<String, Integer>, Integer> dependentCounts) {
        // Analyze bottlenecks
        System.out.println("Top 5 Bottlenecks (Dependencies):\n");
        // Start and end vertices are not bottlenecks
        AtomicInteger count = new AtomicInteger();
        dependentCounts.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) // Sort by dependents
                .limit(5) // Top 5 bottlenecks by dependencies
                .forEach(entry -> {
                            count.getAndIncrement();
                            System.out.println(count + "º: " + entry.getKey().getElement() + " -> " + entry.getValue() + " dependencies: " + entry.getKey().getPertOutVertices().keySet());
                        }
                );
    }
}
