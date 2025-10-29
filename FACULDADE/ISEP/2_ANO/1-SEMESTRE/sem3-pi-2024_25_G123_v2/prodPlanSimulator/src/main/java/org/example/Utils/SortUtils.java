package org.example.Utils;

import org.example.Domain.OperationScheduled;
import org.example.Domain.Workstation;
import org.example.Dto.WorkstationStats;

import java.util.Comparator;
import java.util.List;

/**
 * Utility class for sorting various entities related to the production simulation.
 */
public class SortUtils {

    /**
     * Sorts the given list of workstations based on their processing time in ascending order.
     *
     * @param workstations the list of workstations to be sorted
     */
    public static void sortWorkstationsByTime(List<Workstation> workstations) {
        workstations.sort(Comparator.comparingInt(Workstation::getTime));
    }

    /**
     * Sorts the given list of scheduled operations of an article by their operation number in ascending order.
     *
     * @param operationsScheduled the list of operations to be sorted
     */
    public static void sortOperationsByNumber(List<OperationScheduled> operationsScheduled) {
        operationsScheduled.sort(Comparator.comparingInt(OperationScheduled::getOperationNumber));
    }

    /**
     * Sorts the given list of workstation statistics by their percentage of total time in ascending order.
     *
     * @param workstationStats the list of workstation statistics to be sorted
     */
    public static void sortWorkstationsByTotalTimePercentage(List<WorkstationStats> workstationStats) {
        workstationStats.sort(Comparator.comparingDouble(WorkstationStats::getPercentageOfTotalTime));
    }
}
