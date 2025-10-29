package org.example.esinf.usei13;

import org.example.Domain.TreeNode;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductionCalculatorTest {

    @Test
    public void testCalculateMaterialTotalsRecursive() {
        // Create a  production tree
        TreeNode root = new TreeNode("1", "root", "assembly", 1.0);

        TreeNode material1 = new TreeNode("2", "MaterialA", "material", 10.0);
        TreeNode material2 = new TreeNode("3", "MaterialB", "material", 5.0);
        TreeNode material3 = new TreeNode("4", "MaterialC", "material", 15.0);
        TreeNode material4 = new TreeNode("5", "MaterialA", "material", 8.0);
        TreeNode material5 = new TreeNode("6", "MaterialB", "material", 4.0);


        TreeNode subAssembly = new TreeNode("7", "SubAssembly", "assembly", 1.0);
        subAssembly.addChild(material1);
        subAssembly.addChild(material2);
        subAssembly.addChild(material3);

        root.addChild(material4);
        root.addChild(material5);
        root.addChild(subAssembly);

        Map<String, Double> materialTotals = new HashMap<>();

        // Act: Calculate material totals recursively
        ProductionCalculator.calculateMaterialTotalsRecursive(root, materialTotals);

        // Assert: Check that totals match expected values
        assertEquals(18.0, materialTotals.get("MaterialA"), 0.01); // 10.0 + 8.0 = 18.0
        assertEquals(9.0, materialTotals.get("MaterialB"), 0.01); // 5.0 + 4.0 = 9.0
        assertEquals(15.0, materialTotals.get("MaterialC"), 0.01); // 15.0

    }

    @Test
    public void testCalculateMaterialTotalsWithEmptyTree() {
        // Test with an empty tree (no nodes)
        TreeNode root = null;
        Map<String, Double> materialTotals = new HashMap<>();

        ProductionCalculator.calculateMaterialTotalsRecursive(root, materialTotals);

        assertTrue(materialTotals.isEmpty()); // Check that no totals were calculated
    }
}
