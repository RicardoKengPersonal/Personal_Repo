package org.example.Utils;

import org.example.Domain.Workstation;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for operations related to Workstations.
 */
public class WorkstationUtils {

    /**
        * Gets a list of suitable workstations for the specified operation.
     * @param operation the operation to filter workstations by
     */
    public static Workstation findBestWorkstation(String operation, List<Workstation> workstations,int currentTime) throws Exception {
        List<Workstation> suitableWorkstations = getSuitableWorkstations(operation, workstations);
        Workstation bestWorkstation = null;
        int minTime = Integer.MAX_VALUE;
        for (Workstation workstation : suitableWorkstations) {
            int time = workstation.checkWorkstationTimeAvailable(currentTime);
            if (time < minTime && workstation.getOperation().equals(operation)) {
                minTime = time;
                bestWorkstation = workstation;
            }
        }
        return bestWorkstation;
    }

    /**
     * Gets a list of suitable workstations for the specified operation.
     *
     * @param operation         the operation to filter workstations by
     * @param allWorkstations   the list of all available workstations
     * @return a sorted list of suitable workstations that can perform the operation
     */
    public static List<Workstation> getSuitableWorkstations(String operation, List<Workstation> allWorkstations) {
        List<Workstation> suitableWorkstations = new ArrayList<>();
        for (Workstation workstation : allWorkstations) {
            if (workstation.getOperation().equals(operation)) {
                suitableWorkstations.add(workstation);
            }
        }
        // Sort suitable workstations by their processing time
        SortUtils.sortWorkstationsByTime(suitableWorkstations);
        return suitableWorkstations;
    }
}
