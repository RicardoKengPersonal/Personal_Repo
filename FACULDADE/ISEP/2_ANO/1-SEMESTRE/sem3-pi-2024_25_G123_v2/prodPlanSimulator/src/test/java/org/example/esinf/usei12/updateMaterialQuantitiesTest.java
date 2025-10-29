package org.example.esinf.usei12;

import org.example.Domain.Tree;
import org.example.Domain.TreeNode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class updateMaterialQuantitiesTest {

    /*----------------------------------Tests USEI12 (Update Material Quantities)----------------------------------*/
    @Test
    public void testUpdateWithNoChildren() {
        // Create a single TreeNode without children
        TreeNode node = new TreeNode("1", "Material A", "Material", 100.0);

        Tree.updateMaterialQuantities(node, 200.0);

        // Assert the updated quantity
        assertEquals(200.0, node.getQuantity(), 0.01);
    }

    @Test
    public void testUpdateWithChildren() {
        // Create TreeNode with children
        TreeNode parent = new TreeNode("1", "Parent Material", "Material", 100.0);
        TreeNode child1 = new TreeNode("2", "Child 1", "Material", 50.0);
        TreeNode child2 = new TreeNode("3", "Child 2", "Material", 30.0);

        parent.addChild(child1);
        parent.addChild(child2);

        Tree.updateMaterialQuantities(parent, 200.0);

        // Assert the updated quantities
        assertEquals(200.0, parent.getQuantity(), 0.01);
        assertEquals(100.0, child1.getQuantity(), 0.01); // 50.0 * 2.0 = 100.0
        assertEquals(60.0, child2.getQuantity(), 0.01);  // 30.0 * 2.0 = 60.0
    }

    @Test
    public void testUpdateWithGrandchildren() {
        // Create TreeNode hierarchy with grandchildren
        TreeNode parent = new TreeNode("1", "Parent Material", "Material", 100.0);
        TreeNode child = new TreeNode("2", "Child", "Material", 50.0);
        TreeNode grandchild = new TreeNode("3", "Grandchild", "Material", 25.0);

        parent.addChild(child);
        child.addChild(grandchild);

        Tree.updateMaterialQuantities(parent, 150.0);

        // Assert the updated quantities
        assertEquals(150.0, parent.getQuantity(), 0.01);
        assertEquals(75.0, child.getQuantity(), 0.01);      // 50.0 * 1.5 = 75.0
        assertEquals(37.5, grandchild.getQuantity(), 0.01); // 25.0 * 1.5 = 37.5
    }

    @Test
    public void testUpdateWithZeroQuantity() {
        // Create a single TreeNode
        TreeNode node = new TreeNode("1", "Material A", "Material", 100.0);

        // Assert that updating to zero throws an exception
        assertThrows(IllegalArgumentException.class, () -> Tree.updateMaterialQuantities(node, 0.0));
    }
}
