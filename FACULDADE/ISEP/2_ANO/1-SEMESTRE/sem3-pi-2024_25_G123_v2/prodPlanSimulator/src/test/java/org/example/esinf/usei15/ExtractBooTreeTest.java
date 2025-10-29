package org.example.esinf.usei15;

import org.example.Domain.TreeNode;
import org.example.FileIO.Files;
import org.example.structures.tree.AVL;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ExtractBooTreeTest {

    @Test
    @DisplayName("Ensure AVL is not null")
    public void test1() {

        Map<String, String> operations = new HashMap<>();

        operations.put("1", "CUT");
        operations.put("2", "ASSEMBLE");
        operations.put("3", "DRILL");
        operations.put("4", "FIX");
        operations.put("5", "POLISH");

        Map<String, String> items = new HashMap<>();

        items.put("1", "item1");
        items.put("2", "item2");
        items.put("3", "item3");
        items.put("4", "item4");
        items.put("5", "item5");

        Map<String, TreeNode> nodeMap = new HashMap<>();

        // Adding example nodes to nodeMap
        TreeNode node1 = new TreeNode("1", "CUT", "operation", 0);
        TreeNode node2 = new TreeNode("2", "ASSEMBLE", "operation", 0);
        TreeNode node3 = new TreeNode("3", "item1", "material", 10);
        TreeNode node4 = new TreeNode("4", "item2", "material", 20);

        node1.addChild(node3);
        node2.addChild(node4);

        nodeMap.put("1", node1);
        nodeMap.put("2", node2);
        nodeMap.put("3", node3);
        nodeMap.put("4", node4);

        TreeNode tree = Files.readBooFile(nodeMap, items, operations);

        ExtractBooTree extractBooTree = new ExtractBooTree();
        AVL<TreeNode> avlTree = extractBooTree.extractBooTree(tree);

        assertNotNull(avlTree);
    }

    @Test
    @DisplayName("Test extractBooTree with single node")
    public void test2() {

        TreeNode singleNode = new TreeNode("1", "CUT", "OPERATION", 0);

        AVL<TreeNode> avl = ExtractBooTree.extractBooTree(singleNode);

        assertNotNull(avl);
        assertEquals(1, avl.size());
    }

    @Test
    @DisplayName("Test extractBooTree with multiple nodes")
    public void test3() {

        TreeNode root = new TreeNode("1", "CUT", "OPERATION",0);
        TreeNode child1 = new TreeNode("2", "ASSEMBLE", "OPERATION",0);
        TreeNode child2 = new TreeNode("3", "item", "MATERIAL",0);

        root.addChild(child1);
        root.addChild(child2);

        AVL<TreeNode> avl = ExtractBooTree.extractBooTree(root);

        int expected = 3;

        assertEquals(expected, avl.size());
    }

    @Test
    @DisplayName("Test extractBooTree with complex tree")
    public void test4() {

        TreeNode root = new TreeNode("1", "CUT", "operation", 0);

        TreeNode child1 = new TreeNode("2", "ASSEMBLE", "operation", 0);
        TreeNode child2 = new TreeNode("3", "item1", "material", 10);

        TreeNode subChild1 = new TreeNode("4", "DRILL", "operation", 0);
        TreeNode subChild2 = new TreeNode("5", "item2", "material", 20);

        child1.addChild(subChild1);
        child1.addChild(subChild2);
        root.addChild(child1);
        root.addChild(child2);

        AVL<TreeNode> avlTree = ExtractBooTree.extractBooTree(root);

        assertNotNull(avlTree);
        assertEquals(5, avlTree.size());
    }
}