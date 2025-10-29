package org.example.FileIO;

import org.example.Domain.Article;
import org.example.Domain.TreeNode;
import org.example.Domain.Workstation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Provides methods for reading data from CSV files for articles and workstations
 * and converting them into Java objects.
 */
public class Files {
    static String ArticlesFile = "prodPlanSimulator/src/main/java/resources/articles.csv";
    static String WorkstationsFile = "prodPlanSimulator/src/main/java/resources/workstations.csv";
    static String operationsFile = "prodPlanSimulator/src/main/java/resources/operations.csv";
    static String booFile = "prodPlanSimulator/src/main/java/resources/boo_v2.csv";
    static String itemsFile = "prodPlanSimulator/src/main/java/resources/items.csv";

    /**
     * Reads articles from the specified CSV file and converts them into a list of Article objects.
     * Each row in the CSV file represents an article with fields for ID, priority, and operations.
     *
     * @return a list of Article objects parsed from the CSV file, or null if an error occurs
     */
    public static List<Article> readArticles(String ArticlesFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(ArticlesFile))) {
            br.readLine(); // Skip the header
            List<Article> articles = new ArrayList<>();
            int id = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                String idItem = values[0];
                int priority = priorityToInt(values[1]);
                List<String> ops = Arrays.asList(Arrays.copyOfRange(values, 2, values.length));
                Article order = new Article(id, idItem, priority, ops);
                articles.add(order);
                id++;
            }
            return articles;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Reads workstations from the specified CSV file and converts them into a map of Workstation objects.
     * Each row in the CSV file represents a workstation with fields for ID, operation, and time duration.
     *
     * @return a map where each key is a workstation ID and each value is a corresponding Workstation object
     */
    public static Map<String, Workstation> readWorkstations(String WorkstationsFile) {
        String line;
        String csvSplitBy = ";";
        Map<String, Workstation> workstations = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(WorkstationsFile))) {
            br.readLine(); // Skip the header

            while ((line = br.readLine()) != null) {
                String[] values = line.split(csvSplitBy);
                String idWorkstation = values[0];
                String operation = values[1];
                int time = Integer.parseInt(values[2]);
                Workstation workstation = new Workstation(idWorkstation, operation, time);
                workstations.put(idWorkstation, workstation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return workstations;
    }

    public static Map<String, String> readItemsFile(String itemsFile) {
        Map<String, String> items = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(itemsFile))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                items.put(values[0], values[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    public static Map<String, String> readOperationsFile() {
        Map<String, String> operations = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(operationsFile))) {
            String line;

            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                operations.put(values[0], values[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return operations;
    }


    public static TreeNode readBooFile(Map<String, TreeNode> nodeMap, Map<String, String> items, Map<String, String> operations, String booFile) {
        boolean rootSet = false;
        TreeNode root = null;

        try (BufferedReader br = new BufferedReader(new FileReader(booFile))) {
            String line;

            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                String opId = values[0];
                String itemId = values[1];
                double itemQtd = Double.parseDouble(values[2]);

                String operationName = operations.get(opId);
                if (operationName == null) {
                    throw new IllegalArgumentException("Operation ID " + opId + " not found in operations data.");
                }


                TreeNode operationTreeNode = nodeMap.computeIfAbsent(opId, id -> new TreeNode(id, operationName, "operation", 0));
                TreeNode itemTreeNode = nodeMap.computeIfAbsent(itemId, id -> new TreeNode(id, items.getOrDefault(id, "Unknown Material"), "material", itemQtd));

                operationTreeNode.addChild(itemTreeNode);

                if (!rootSet) {
                    root = operationTreeNode;
                    rootSet = true;
                }
                parseSubOperationsAndMaterials(line, operations, items, operationTreeNode, nodeMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }


    private static void parseSubOperationsAndMaterials(String line, Map<String, String> operations, Map<String, String> items, TreeNode operationTreeNode, Map<String, TreeNode> nodeMap) {
        int startOps = line.indexOf("(;");
        int endOps = line.indexOf(";)", startOps);

        // Parse sub-operations
        if (startOps != -1 && endOps != -1) {
            String[] ops = line.substring(startOps + 2, endOps).split(";");
            for (int i = 0; i < ops.length; i += 2) {
                String subOpId = ops[i];
                if (subOpId.isEmpty()) break;

                String operationName = operations.get(subOpId);


                double subOpQtd = Double.parseDouble(ops[i + 1]);
                TreeNode subOpTreeNode = nodeMap.computeIfAbsent(subOpId, id -> new TreeNode(id, operations.getOrDefault(id, "Unknown Operation"), "operation", subOpQtd));
                operationTreeNode.addChild(subOpTreeNode);
            }
        }

        // Parse input materials (leaf nodes)
        int startMaterials = line.indexOf("(;", endOps);
        int endMaterials = line.indexOf(";)", startMaterials);

        if (startMaterials != -1 && endMaterials != -1) {
            String[] materials = line.substring(startMaterials + 2, endMaterials).split(";");
            for (int i = 0; i < materials.length; i += 2) {
                String materialId = materials[i];
                if (materialId.isEmpty()) break;

                materials[i + 1] = materials[i + 1].replace(",", ".");

                double materialQtd = Double.parseDouble(materials[i + 1]);
                TreeNode materialTreeNode = nodeMap.computeIfAbsent(materialId, id -> new TreeNode(id, items.getOrDefault(id, "Unknown Material"), "material", materialQtd));

                operationTreeNode.addChild(materialTreeNode);
            }
        }
    }


    /**
     * Converts a priority level represented as a string to an integer.
     *
     * @param val the priority level as a string ("HIGH", "NORMAL", "LOW")
     * @return an integer representation of the priority: 3 for "HIGH", 2 for "NORMAL", 1 for "LOW", 0 for unknown
     */
    public static int priorityToInt(String val) {
        return switch (val) {
            case "HIGH" -> 3;
            case "NORMAL" -> 2;
            case "LOW" -> 1;
            default -> 0;
        };
    }
}
