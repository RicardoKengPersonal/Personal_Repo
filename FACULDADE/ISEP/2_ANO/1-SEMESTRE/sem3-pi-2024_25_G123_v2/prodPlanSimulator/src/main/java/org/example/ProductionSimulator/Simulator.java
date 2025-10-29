package org.example.ProductionSimulator;

import org.example.Domain.OperationScheduled;
import org.example.Domain.Article;
import org.example.Domain.Workstation;
import org.example.Dto.WorkstationStats;
import org.example.Utils.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * The type Simulator.
 */
public class Simulator {
    private List<Article> articles;
    private Map<String, Workstation> workstations;

    // List to store scheduling information for each production order
    private List<OperationScheduled> operationsScheduled = new ArrayList<>();

    /*------------------------------------------US02------------------------------------------*/

    /**
     * Simulate.
     *
     * @throws RuntimeException the runtime exception
     */
    // executar a simulação da produção, agendando operações para os artigos em diferentes estações de trabalho (workstations
    public void computeSimulation(List<Article> articleList, Map<String, Workstation> workstationsList) throws Exception {
        articles = articleList;
        workstations = workstationsList;

        int id = 0;
        // Para cada artigo obtém-se a lista de operações (operationList) associadas a ele.
        for (Article article : articleList) {
            List<String> operationList = article.getOperations();
            // é inicializado para rastrear o tempo de término da operação
            int previousOperationEndingTime = 0;
            // é inicializado para rastrear a ordem das operações para o artigo.
            int operationNumber = 0;
            for (String operation : operationList) {
                // Find a suitable workstation for the operation
                Workstation bestWorkstation = findBestWorkstation(operation, previousOperationEndingTime);
                if (bestWorkstation != null) {
                int minWaitTime = bestWorkstation.checkWorkstationTimeAvailable(previousOperationEndingTime);

                        //expected start and end time
                        int startingTime = previousOperationEndingTime + minWaitTime;
                        int endingTime = startingTime + bestWorkstation.getTime();

                        bestWorkstation.addArticleOrder(article, previousOperationEndingTime, article.getOperations().indexOf(operation));
                        operationsScheduled.add(new OperationScheduled(id, operation, bestWorkstation.getIdWorkstation(), startingTime, endingTime, operationNumber, article.getIdItem()));
                        previousOperationEndingTime = endingTime; // Update previous operation end time
                        operationNumber++;
                } else {
                    System.out.println("No suitable workstation found for Operation " + operation);
                }
            }
            id++;
        }
    }
    /**
     * Find the best suitable workstation for the given operation.
     *
     * @param operation the operation to be scheduled
     * @param previousOperationEndTime the end time of the previous operation
     * @return the best suitable workstation or null if no suitable workstation is found
     */

    private Workstation findBestWorkstation(String operation, int previousOperationEndTime) throws Exception {
        List<Workstation> suitableWorkstations = WorkstationUtils.getSuitableWorkstations(operation, new ArrayList<>(workstations.values()));
        return WorkstationUtils.findBestWorkstation(operation, suitableWorkstations, previousOperationEndTime);
    }
    /**
     * Display the scheduling table of the scheduled operations.
     */
    public void displayScheduledOperationsTable() {
        DisplayUtils.printScheduledOperationsTable(operationsScheduled);
    }

    // Setters
    public void setArticleList(List<Article> articleList) {
        this.articles = articleList;
    }

    public void setWorkstations(Map<String, Workstation> workstations) {
        this.workstations = workstations;
    }

    public List<OperationScheduled> getOperationsScheduled() {
        return operationsScheduled;
    }
    /*------------------------------------------US03------------------------------------------*/

    /**
     * Displays the total time spent per article type.
     */
    public void displayTotalTimePerArticleType() {
        Map<String, Integer> totalTimePerArticleType = new HashMap<>();
        for (OperationScheduled schedule : operationsScheduled) {
            String articleType = schedule.getType();
            int operationTime = schedule.getEndTime() - schedule.getStartTime();
            totalTimePerArticleType.put(articleType, totalTimePerArticleType.getOrDefault(articleType, 0) + operationTime);
        }
        DisplayUtils.printTotalTimePerArticleType(totalTimePerArticleType);
    }

    /**
     * Displays the total time per instance for each unique article type and operation in a table format.
     * <p>
     * This method iterates over a list of scheduled operations, calculates the total execution time for each
     * unique combination of article type and operation, and then prints a formatted table displaying the
     * article type and expected total time.
     * <p>
     * For each operation:
     * - If the article type and operation match the last processed item, the operation's time is added to the total.
     * - If they differ, a new entry is created in the total time list.
     */
    public void displayTotalTimePerInstanceWithArticleType() {
        List<Temp> totalTimePerInstance = new ArrayList<>();

        // Initialize the first Temp object with the type and operation of the first scheduled operation.
        Temp firstTemp = new Temp(operationsScheduled.get(0).getType(), operationsScheduled.get(0).getOperation());
        firstTemp.totalTime = operationsScheduled.get(0).getEndTime() - operationsScheduled.get(0).getStartTime();
        totalTimePerInstance.add(firstTemp);
        int counter = 0;

        // Process each scheduled operation to calculate total time per instance.
        for (int i = 1; i < operationsScheduled.size(); i++) {
            // Check if the current operation has the same type and operation as the last entry in the list.
            if (totalTimePerInstance.get(counter).type.equals(operationsScheduled.get(i).getType())) {
                if (totalTimePerInstance.get(counter).operation.equals(operationsScheduled.get(i).getOperation())) {
                    // Create a new Temp object if both type and operation match.
                    Temp newTemp = new Temp(operationsScheduled.get(i).getType(), operationsScheduled.get(i).getOperation());
                    newTemp.totalTime = operationsScheduled.get(i).getEndTime() - operationsScheduled.get(i).getStartTime();
                    totalTimePerInstance.add(newTemp);
                    counter++;
                } else {
                    // Add the operation time to the existing total if only the type matches.
                    totalTimePerInstance.get(counter).totalTime += (operationsScheduled.get(i).getEndTime() - operationsScheduled.get(i).getStartTime());
                }
            } else {
                // Create a new Temp object if the type differs from the last entry in the list.
                Temp newTemp = new Temp(operationsScheduled.get(i).getType(), operationsScheduled.get(i).getOperation());
                newTemp.totalTime = operationsScheduled.get(i).getEndTime() - operationsScheduled.get(i).getStartTime();
                totalTimePerInstance.add(newTemp);
                counter++;
            }
        }

        // Print the formatted table displaying article type and total time.
        System.out.println("+------------+-------------------------+");
        System.out.println("| Article    | Expected Total Time     |");
        System.out.println("+------------+-------------------------+");
        for (int x = 0; x < totalTimePerInstance.size(); x++) {
            String formattedTotalTime = TimeFormatter.formatTime(totalTimePerInstance.get(x).totalTime);
            System.out.printf("| %-10s | %-23s |\n", totalTimePerInstance.get(x).type, formattedTotalTime);
        }
        System.out.println("+------------+-------------------------+");
    }


    /*------------------------------------------US04------------------------------------------*/

    /**
     * Calculates and displays the total time spent on each unique operation.
     * <p>
     * This method iterates through the list of scheduled operations, calculates the total time spent
     * on each operation by summing the time for each instance, and stores the results in a HashMap.
     * The total time per operation is then printed using an external utility method.
     */
    public void timeSpentPerOperation() {

        // HashMap to store total time spent on each operation
        HashMap<String, Integer> map = new HashMap<>();

        // Loop through each scheduled operation
        for (int i = 0; i < operationsScheduled.size(); i++) {
            String operation = operationsScheduled.get(i).getOperation();
            int time = operationsScheduled.get(i).getEndTime() - operationsScheduled.get(i).getStartTime();

            // If the operation already exists in the map, add the time to the current total
            if (map.containsKey(operation)) {
                int currentTime = map.get(operation);
                map.put(operation, currentTime + time);
            } else {
                // Otherwise, add the operation with its initial time
                map.put(operation, time);
            }
        }

        // Display the total time spent per operation
        DisplayUtils.printTimeSpentPerOperation(map);
    }


    /*------------------------------------------US05------------------------------------------*/

    /**
     * Calculates workstation operation times and their respective percentages.
     *
     * @return a list of workstation statistics
     */
    public List<WorkstationStats> calculateWorkstationOperationTimes() {
        Map<String, Integer> totalWorkstationTime = new HashMap<>();
        int totalExecutionTime = 0;

        // Calculate the total operation time per workstation and total execution time
        for (OperationScheduled schedule : operationsScheduled) {
            String workstationId = schedule.getWorkstationId();
            int operationTime = schedule.getEndTime() - schedule.getStartTime();

            totalWorkstationTime.put(workstationId, totalWorkstationTime.getOrDefault(workstationId, 0) + operationTime);

            totalExecutionTime += operationTime;
        }

        // List that saves the workstations with their times and percentages
        List<WorkstationStats> workstationStats = new ArrayList<>();

        // Calculate percentages for each workstation
        for (Map.Entry<String, Integer> entry : totalWorkstationTime.entrySet()) {
            String workstationId = entry.getKey();
            int workstationTime = entry.getValue();

            double percentageOfTotalTime = ((double) workstationTime / totalExecutionTime) * 100;

            workstationStats.add(new WorkstationStats(workstationId, workstationTime, percentageOfTotalTime));
        }

        return workstationStats;
    }

    /**
     * Displays workstation operation times in a table format.
     *
     * @param workstationStats the list of workstation statistics
     */
    public void displayWorkstationOperationTimes(List<WorkstationStats> workstationStats) {
        SortUtils.sortWorkstationsByTotalTimePercentage(workstationStats);

        DisplayUtils.printWorkstationOperationTimes(workstationStats);
    }

    /*------------------------------------------US06------------------------------------------*/

    /**
     * Calculates average execution and waiting times for each operation.
     *
     * @return a map containing average execution and waiting times
     */
    public Map<String, Map<String, Double>> calculateAverageExecutionAndWaitingTimes() {
        // Maps to save the total execution and waiting times for each operation
        Map<String, Integer> operationCount = new HashMap<>();
        Map<String, Integer> totalExecutionTime = new HashMap<>();
        Map<String, Integer> totalWaitingTime = new HashMap<>();

        // Calculate the execution and waiting times for each operation
        int previousOperationEndingTime = 0;
        for (OperationScheduled schedule : operationsScheduled) {
            if (schedule.getStartTime() < previousOperationEndingTime) {
                previousOperationEndingTime = 0;
            }
            String operation = schedule.getOperation();
            int executionTime = schedule.getEndTime() - schedule.getStartTime();
            int waitingTime = schedule.getStartTime() - previousOperationEndingTime;
            previousOperationEndingTime = schedule.getEndTime();

            operationCount.put(operation, operationCount.getOrDefault(operation, 0) + 1);
            totalExecutionTime.put(operation, totalExecutionTime.getOrDefault(operation, 0) + executionTime);
            totalWaitingTime.put(operation, totalWaitingTime.getOrDefault(operation, 0) + waitingTime);

        }

        // Maps to save the average execution and waiting times for each operation
        Map<String, Double> averageExecutionTime = new HashMap<>();
        Map<String, Double> averageWaitingTime = new HashMap<>();

        // Calculate the average execution and waiting times for each operation
        for (Map.Entry<String, Integer> entry : totalExecutionTime.entrySet()) {
            String operation = entry.getKey();
            int totalExecution = totalExecutionTime.get(operation);
            int totalWaiting = totalWaitingTime.get(operation);
            int count = operationCount.get(operation);

            double averageExecution = (double) totalExecution / count;
            double averageWaiting = (double) totalWaiting / count;

            averageExecutionTime.put(operation, averageExecution);
            averageWaitingTime.put(operation, averageWaiting);
        }

        return Map.of("averageExecutionTime", averageExecutionTime, "averageWaitingTime", averageWaitingTime);
    }

    /**
     * Displays average execution and waiting times for each operation.
     *
     * @param averageTimes the map containing average execution and waiting times
     */
    public void displayAverageExecutionAndWaitingTimes(Map<String, Map<String, Double>> averageTimes) {
        Map<String, Double> averageExecutionTime = averageTimes.get("averageExecutionTime");
        Map<String, Double> averageWaitingTime = averageTimes.get("averageWaitingTime");

        DisplayUtils.printAverageExecutionAndWaitingTimes(averageExecutionTime, averageWaitingTime);
    }

     /*------------------------------------------US07------------------------------------------*/

    /**
     * Produces a flow dependency listing between workstations.
     */
    public void produceFlowDependencyListing() {
        // Create a map to store flow dependencies for each workstation
        Map<String, Map<String, Integer>> flowDependencyMap = new HashMap<>();

        // For each article, find the flow of operations across workstations
        for (Article article : articles) {
            List<OperationScheduled> articleOperations = new ArrayList<>();

            // Collect the operations for the current article
            for (OperationScheduled operationScheduled : operationsScheduled) {
                if (operationScheduled.getType().equals(article.getIdItem())) {
                    articleOperations.add(operationScheduled);
                }
            }

            // Sort the operations by their number, to process them in sequence
            SortUtils.sortOperationsByNumber(articleOperations);

            // Build the flow dependency from one workstation to the next
            for (int i = 0; i < articleOperations.size() - 1; i++) {
                String currentWorkstation = articleOperations.get(i).getWorkstationId();
                String nextWorkstation = articleOperations.get(i + 1).getWorkstationId();

                // Initialize the flow map for the current workstation if it's not there
                flowDependencyMap.putIfAbsent(currentWorkstation, new HashMap<>());

                // Increment the count of the flow from currentWorkstation to nextWorkstation
                Map<String, Integer> flowMap = flowDependencyMap.get(currentWorkstation);
                flowMap.put(nextWorkstation, flowMap.getOrDefault(nextWorkstation, 0) + 1);
            }
        }

        // Display the result sorted by the number of processed items
        for (Map.Entry<String, Map<String, Integer>> entry : flowDependencyMap.entrySet()) {
            String workstation = entry.getKey();
            Map<String, Integer> dependencies = entry.getValue();

            // Sort dependencies by count in descending order
            List<Map.Entry<String, Integer>> sortedDependencies = new ArrayList<>(dependencies.entrySet());
            sortedDependencies.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

            // Print the flow dependency for this workstation
            System.out.print(workstation + " : [");
            for (int i = 0; i < sortedDependencies.size(); i++) {
                Map.Entry<String, Integer> dependency = sortedDependencies.get(i);
                System.out.print("(" + dependency.getKey() + "," + dependency.getValue() + ")");
                if (i < sortedDependencies.size() - 1) {
                    System.out.print(",");
                }
            }
            System.out.println("]");
        }

    }

//    public void exportScheduleToCSV() {
//        String fileName = "schedule.csv"; // Predefined file name
//        try (FileWriter csvWriter = new FileWriter(fileName)) {
//            csvWriter.append("machineID;machineState;OperationID\n");
//            for (OperationScheduled operation : operationsScheduled) {
//                String machineID = operation.getWorkstationId();
//                String operationID = String.valueOf(operation.getId());
//                String machineState = "ACTIVE"; // Placeholder for the actual machine state
//
//                csvWriter.append(machineID)
//                        .append(";")
//                        .append(machineState)
//                        .append(";")
//                        .append(operationID)
//                        .append("\n");
//            }
//            System.out.println("CSV file created successfully as " + fileName);
//        } catch (IOException e) {
//            System.out.println("Error occurred while writing to CSV file: " + e.getMessage());
//        }
//    }

}
