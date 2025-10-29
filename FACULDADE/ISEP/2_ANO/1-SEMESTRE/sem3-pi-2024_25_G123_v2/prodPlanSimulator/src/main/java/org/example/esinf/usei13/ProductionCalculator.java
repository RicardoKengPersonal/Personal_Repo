package org.example.esinf.usei13;

import org.example.Domain.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that calculates the total quantities of materials needed for production.
 */
public class ProductionCalculator {

    /**
     * Calculates the total quantities of materials needed for production.
     *
     * @param root The root node of the production tree.
     */
    public static void calculateMaterialTotals(TreeNode root) {
        Map<String, Double> materialTotals = new HashMap<>(); // To store totals per material

        // Perform recursive traversal to calculate material totals
        calculateMaterialTotalsRecursive(root, materialTotals);

        // Display the results
        System.out.println("Total Quantities Per Material:");
        for (Map.Entry<String, Double> entry : materialTotals.entrySet()) {
            System.out.println("Material: " + entry.getKey() + ", Total Quantity: " + entry.getValue());
        }
    }

    /**
     * Method Recursive to calculate total quantities for materials.
     *
     * @param node            The current tree node being traversed.
     * @param materialTotals  Map to store total quantities for each material.
     */
    public static void calculateMaterialTotalsRecursive(TreeNode node, Map<String, Double> materialTotals) {
        if (node == null) {
            return;
        }

        // Process material nodes
        if (node.getType().equals("material")) {
            materialTotals.put(
                    node.getName(),
                    materialTotals.getOrDefault(node.getName(), 0.0) + node.getQuantity()
            );
        }

        // Recursively traverse children
        for (TreeNode child : node.getChildren()) {
            calculateMaterialTotalsRecursive(child, materialTotals);
        }
    }
}
