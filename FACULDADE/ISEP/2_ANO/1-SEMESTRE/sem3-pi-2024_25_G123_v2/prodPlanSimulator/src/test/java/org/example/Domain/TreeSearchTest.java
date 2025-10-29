package org.example.Domain;

import org.example.Domain.Tree;
import org.example.Domain.TreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TreeSearchTest {
    private Tree tree;

    @BeforeEach
    public void setup() {
        // Create the root node
        TreeNode root = new TreeNode("1", "RootOperation", "operation", 0);

        // Create child nodes
        TreeNode child1 = new TreeNode("2", "ChildOperation1", "operation", 0);
        TreeNode child2 = new TreeNode("3", "ChildOperation2", "operation", 0);
        TreeNode material1 = new TreeNode("4", "Material1", "material", 100);
        TreeNode material2 = new TreeNode("5", "Material2", "material", 200);

        // Build the tree structure
        child1.addChild(material1);
        child2.addChild(material2);
        root.addChild(child1);
        root.addChild(child2);

        // Create node maps
        Map<String, TreeNode> nodeMap = new HashMap<>();
        nodeMap.put("1", root);
        nodeMap.put("2", child1);
        nodeMap.put("3", child2);
        nodeMap.put("4", material1);
        nodeMap.put("5", material2);

        // Initialize the Tree object
        tree = new Tree(root, nodeMap);
        tree.CreateTree(); // Populate nameMap and other helpers
    }

    @Test
    public void testSearchNodeById() {
        TreeNode result = tree.searchNode("4");
        assertNotNull(result);
        assertEquals("Material1", result.getName());
        assertEquals(100, result.getQuantity());
    }

    @Test
    public void testSearchNodeByName() {
        TreeNode result = tree.searchNode("Material2");
        assertNotNull(result);
        assertEquals("Material2", result.getName());
        assertEquals(200, result.getQuantity());
    }

    @Test
    public void testSearchNodeNotFound() {
        TreeNode result = tree.searchNode("NonExistentNode");
        assertNull(result);
    }

    @Test
    public void testSearchNodeEWithParentOperation() {
        TreeNode material1 = tree.searchNode("4");
        String result = tree.searchNodeE(material1);

        String expected = "Node Type: material\n" +
                "Quantity: 100.0\n" +
                "Parent Operation: ChildOperation1\n";

        assertEquals(expected, result);
    }

    @Test
    public void testSearchNodeEWithoutParentOperation() {
        TreeNode rootNode = tree.searchNode("1");
        String result = tree.searchNodeE(rootNode);

        String expected = "Node Type: operation\n" +
                "Parent Operation: None\n";

        assertEquals(expected, result);
    }

    @Test
    public void testUpdateMaterialQuantities() {
        TreeNode material1 = tree.searchNode("4");

        Tree.updateMaterialQuantities(material1, 200);

        assertEquals(200, material1.getQuantity());

        // Verify propagation
        TreeNode childOfMaterial1 = material1.getChildren().isEmpty() ? null : material1.getChildren().get(0);
        if (childOfMaterial1 != null) {
            double expectedQuantity = 200 * (childOfMaterial1.getQuantity() / 100);
            assertEquals(expectedQuantity, childOfMaterial1.getQuantity());
        }
    }

    @Test
    public void testMaterialQuantitiesUpdateNegative() {
        TreeNode material1 = tree.searchNode("4");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Tree.updateMaterialQuantities(material1, -10);
        });

        assertEquals("New quantity must be greater than 0.", exception.getMessage());
    }
}
