package org.example.esinf.usei11;

import org.example.structures.heapPriorityQueue.HeapPriorityQueue;

import java.util.HashMap;
import java.util.Map;

public class PrioritizeQualityChecks {

    public static HeapPriorityQueue<Integer, String> prioritizeQualityChecks(Map<String, String> operations) {

        HeapPriorityQueue<Integer, String> priorityQueue = new HeapPriorityQueue<>();

        Map<String, Integer> operationCount = new HashMap<>();

        Map<Integer, String> basePriorities = addOperations(operations);

        for (Map.Entry<String, String> entry : operations.entrySet()) {

            String operationName = entry.getValue();

            int basePriority = basePriorities.entrySet().stream()
                    .filter(e -> e.getValue().equals(operationName))
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse(0);

            int count = operationCount.getOrDefault(operationName, 0);
            operationCount.put(operationName, count + 1);

            int priority = basePriority * 10 + count;

            priorityQueue.insert(priority, operationName);
        }

        return priorityQueue;
    }

    public static Map<Integer, String> addOperations(Map<String, String> operations) {

        Map<Integer, String> basePriorities = new HashMap<>();

        int key = 1;

        for (Map.Entry<String, String> entry : operations.entrySet()) {

            basePriorities.put(key, entry.getValue());
            key++;
        }

        return basePriorities;
    }
}
