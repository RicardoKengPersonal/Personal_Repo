package org.example.Domain;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MaterialBSTTest {

    private MaterialBST materialBST;

    @BeforeEach
    public void setUp() {
        // Setup before each test
        materialBST = new MaterialBST();

        TreeNode material1 = new TreeNode("M1", "Material 1", "material", 10.5);
        TreeNode material2 = new TreeNode("M2", "Material 2", "material", 5.0);
        TreeNode material3 = new TreeNode("M3", "Material 3", "material", 20.0);
        TreeNode material4 = new TreeNode("M4", "Material 4", "material", 15.0);

        // Insert materials into the BST
        materialBST.insert(material1);
        materialBST.insert(material2);
        materialBST.insert(material3);
        materialBST.insert(material4);
    }

    @Test
    public void testGetMaterialsInOrderAscending() {
        setUp();
        // Test to check materials in ascending order
        List<TreeNode> materials = materialBST.getMaterialsInOrder(true);

        assertNotNull(materials);
        assertEquals(4, materials.size());

        // Verifying the order by quantity (ascending)
        assertEquals("Material 2", materials.get(0).getName());
        assertEquals("Material 1", materials.get(1).getName());
        assertEquals("Material 4", materials.get(2).getName());
        assertEquals("Material 3", materials.get(3).getName());
    }

    @Test
    public void testGetMaterialsInOrderDescending() {
        setUp();
        // Test to check materials in descending order
        List<TreeNode> materials = materialBST.getMaterialsInOrder(false);

        assertNotNull(materials);
        assertEquals(4, materials.size());

        // Verifying the order by quantity (descending)
        assertEquals("Material 3", materials.get(0).getName());
        assertEquals("Material 4", materials.get(1).getName());
        assertEquals("Material 1", materials.get(2).getName());
        assertEquals("Material 2", materials.get(3).getName());
    }

    @Test
    public void testInsertMaterial() {
        setUp();
        // Test insertion of a new material
        TreeNode material5 = new TreeNode("M5", "Material 5", "material", 12.0);
        materialBST.insert(material5);

        List<TreeNode> materials = materialBST.getMaterialsInOrder(true);

        assertNotNull(materials);
        assertEquals(5, materials.size());

        // Verifying the new material is inserted correctly
        assertEquals("Material 2", materials.get(0).getName());
        assertEquals("Material 1", materials.get(1).getName());
        assertEquals("Material 5", materials.get(2).getName()); // The newly inserted material
        assertEquals("Material 4", materials.get(3).getName());
        assertEquals("Material 3", materials.get(4).getName());
    }

    @Test
    public void testEmptyBST() {
        // Test for an empty tree
        MaterialBST emptyBST = new MaterialBST();
        List<TreeNode> materials = emptyBST.getMaterialsInOrder(true);

        assertNotNull(materials);
        assertTrue(materials.isEmpty(), "The list should be empty for an empty BST.");
    }
}


