package org.example.esinf.usei14;

import org.example.Domain.CriticalPathAnalyzer;
import org.example.Domain.Tree;
import org.example.Domain.TreeNode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test suite for the CriticalPathAnalyzer class with Tree and TreeNode.
 */
public class CriticalPathAnalyzerTest {

    private CriticalPathAnalyzer analyzer;
    private Tree productionTree;

    @Before
    public void setUp() {
        // Initialize the tree nodes
        TreeNode root = new TreeNode("1", "AssembleCar", "task", 0);
        TreeNode engine = new TreeNode("2", "InstallEngine", "task", 5);
        TreeNode wheels = new TreeNode("3", "AttachWheels", "task", 2);
        TreeNode paint = new TreeNode("4", "PaintCar", "task", 3);
        TreeNode inspection = new TreeNode("5", "InspectCar", "task", 1);

        // Set up the hierarchy
        root.addChild(engine);
        root.addChild(wheels);
        engine.addChild(paint);
        paint.addChild(inspection);

        // Initialize the production tree
        productionTree = new Tree(root, new HashMap<>());
        productionTree.CreateTree();

        // Initialize CriticalPathAnalyzer
        analyzer = new CriticalPathAnalyzer();
    }

    @After
    public void tearDown() {
        analyzer = null;
        productionTree = null;
    }

    @Test
    public void testTreeStructure() {
        System.out.println("Testing Tree Structure");

        TreeNode root = productionTree.searchNode("AssembleCar");
        assertNotNull(root);

        // Verify children of the root node
        assertEquals(2, root.getChildren().size());
        assertEquals("InstallEngine", root.getChildren().get(0).getName());
        assertEquals("AttachWheels", root.getChildren().get(1).getName());

        // Verify child of "InstallEngine"
        TreeNode engine = productionTree.searchNode("InstallEngine");
        assertEquals(1, engine.getChildren().size());
        assertEquals("PaintCar", engine.getChildren().get(0).getName());
    }


    @Test
    public void testUpdateMaterialQuantities() {
        System.out.println("Testing Material Quantity Update");

        TreeNode paint = productionTree.searchNode("PaintCar");
        assertNotNull(paint);
        assertEquals(3, paint.getQuantity(), 0);

        // Update the quantity of "PaintCar" and propagate changes
        Tree.updateMaterialQuantities(paint, 6);

        assertEquals(6, paint.getQuantity(), 0);

        // Verify propagation to dependent tasks
        TreeNode inspection = productionTree.searchNode("InspectCar");
        assertNotNull(inspection);
        assertEquals(2, inspection.getQuantity(), 0); // Assuming dependency doubles quantity
    }

    @Test
    public void testSearchNode() {
        System.out.println("Testing Node Search");

        TreeNode node = productionTree.searchNode("InstallEngine");
        assertNotNull(node);
        assertEquals("InstallEngine", node.getName());

        node = productionTree.searchNode("NonExistentTask");
        assertNull(node);
    }

    @Test
    public void testDepthCalculation() {
        System.out.println("Testing Depth Calculation...");

        // Perform critical path analysis
        analyzer.analyze(productionTree.getRoot());

        // Retrieve critical path results
        List<CriticalPathAnalyzer.TreeNodeDepth> result = analyzer.getCriticalPath();

        // Validate the depths
        assertEquals("InspectCar should be at depth 0", 0, result.get(0).depth);
        assertEquals("PaintCar should be at depth 1", 1, result.get(1).depth);
        assertEquals("AttachWheels should be at depth 1", 1, result.get(2).depth);
        assertEquals("InstallEngine should be at depth 2", 2, result.get(3).depth);
        assertEquals("AssembleCar should be at depth 3", 3, result.get(4).depth);
    }
    @Test
    public void testStaticAnalysisMethod() {
        System.out.println("Testing Static Analysis Method...");

        // Call the static method
        CriticalPathAnalyzer.performCriticalPathAnalysis(productionTree.getRoot());

        // Validate the analysis ran without exceptions
        List<CriticalPathAnalyzer.TreeNodeDepth> result = analyzer.getCriticalPath();
        assertNotNull("Critical path result should not be null", result);
    }

    @Test
    public void testEmptyTree() {
        System.out.println("Testing Empty Tree Handling...");

        // Create an empty tree
        TreeNode emptyRoot = null;
        CriticalPathAnalyzer emptyAnalyzer = new CriticalPathAnalyzer();

        // Analyze the empty tree
        emptyAnalyzer.analyze(emptyRoot);

        // Validate results
        List<CriticalPathAnalyzer.TreeNodeDepth> result = emptyAnalyzer.getCriticalPath();
        assertTrue("Critical path should be empty for an empty tree", result.isEmpty());
    }
}
