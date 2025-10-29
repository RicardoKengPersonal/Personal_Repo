package org.example.esinf.usei11;

import org.example.FileIO.Files;
import org.example.structures.heapPriorityQueue.HeapPriorityQueue;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PrioritizeQualityChecksTest {

    @Test
    @DisplayName("Ensure result is not null")
    public void test1() {

        Map<String, String> operations = Files.readOperationsFile();

        HeapPriorityQueue<Integer, String> priorityQueue = PrioritizeQualityChecks.prioritizeQualityChecks(operations);

        assertNotNull(operations);
        assertNotNull(priorityQueue);
    }

    @Test
    @DisplayName("Ensure all priorities are different")
    public void test2() {

        Map<String, String> operations = Files.readOperationsFile();

        HeapPriorityQueue<Integer, String> priorityQueue = PrioritizeQualityChecks.prioritizeQualityChecks(operations);

        Set<Integer> prioritySet = new HashSet<>();

        while (!priorityQueue.isEmpty()) {
            int priority = priorityQueue.removeMin().getKey();
            prioritySet.add(priority);
        }

        int priorityQueueSize = priorityQueue.size();
        int prioritySetSize = prioritySet.size();

        assertEquals(priorityQueueSize, prioritySetSize);
    }

    @Test
    @DisplayName("Test with unique operations")
    public void test3() {

        Map<String, String> operations = new HashMap<>();

        operations.put("1", "cut");
        operations.put("2", "assemble");
        operations.put("3", "drill");
        operations.put("4", "fix");
        operations.put("5", "polish");

        HeapPriorityQueue<Integer, String> priorityQueue = PrioritizeQualityChecks.prioritizeQualityChecks(operations);

        int expected = 5;

        assertEquals(expected, priorityQueue.size());
    }

    @Test
    @DisplayName("Test with repeated operations")
    public void test4() {

        Map<String, String> operations = new HashMap<>();

        operations.put("1", "cut");
        operations.put("2", "cut");
        operations.put("3", "assemble");
        operations.put("4", "assemble");

        HeapPriorityQueue<Integer, String> priorityQueue = PrioritizeQualityChecks.prioritizeQualityChecks(operations);

        Set<Integer> prioritySet = new HashSet<>();

        while (!priorityQueue.isEmpty()) {

            int priority = priorityQueue.removeMin().getKey();
            assertTrue(prioritySet.add(priority));
        }
    }

    @Test
    @DisplayName("Test with unknown operations")
    public void test5() {

        Map<String, String> operations = new HashMap<>();

        operations.put("1", "mistery");
        operations.put("2", "mistery2");

        HeapPriorityQueue<Integer, String> priorityQueue = PrioritizeQualityChecks.prioritizeQualityChecks(operations);

        int expected = 2;

        assertEquals(expected, priorityQueue.size());
    }

    @Test
    @DisplayName("Test with mixed operations")
    public void test6() {

        Map<String, String> operations = new HashMap<>();
        operations.put("1", "cut");
        operations.put("2", "unknown");
        operations.put("3", "assemble");
        operations.put("4", "anotherUnknown");

        HeapPriorityQueue<Integer, String> priorityQueue = PrioritizeQualityChecks.prioritizeQualityChecks(operations);

        int expected = 4;

        assertEquals(expected, priorityQueue.size());
    }

}