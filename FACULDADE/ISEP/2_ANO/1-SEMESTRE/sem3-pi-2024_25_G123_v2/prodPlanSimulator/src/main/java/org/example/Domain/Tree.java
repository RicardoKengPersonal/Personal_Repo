package org.example.Domain;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Tree {
    private TreeNode root;
    private Map<String, TreeNode> nodeMap;
    private final Map<String, TreeNode> nameMap;
    private final MaterialBST materialBST;

    public Tree(TreeNode root, Map<String, TreeNode> nodeMap) {
        this.nameMap = new HashMap<>();
        this.materialBST = new MaterialBST();

        this.root = root;
        this.nodeMap = nodeMap;
    }



    public void CreateTree() {
        FillMaterialBST(root);
        FillNameMap(root);
    }


    public void displayTree(TreeNode treeNode, String indent) {
        if (treeNode == null)
            return;
        System.out.println(indent + treeNode.getName() + " (" + treeNode.getType() + ")");
        for (TreeNode child : treeNode.getChildren()) {
            displayTree(child, indent + "-----");
        }

        FillNameMap(root);
    }


    private void FillMaterialBST(TreeNode treeNode) {
        if (treeNode == null)
            return;
        if (treeNode.getType().equals("material")) {
            materialBST.insert(treeNode);
        }
        for (TreeNode child : treeNode.getChildren()) {
            FillMaterialBST(child);
        }
    }

    private void FillNameMap(TreeNode treeNode) {
        if (treeNode == null)
            return;
        nameMap.put(treeNode.getName(), treeNode);
        for (TreeNode child : treeNode.getChildren()) {
            FillNameMap(child);
        }
    }


    /**
     * Allows searching of nodes by their name and ID.
     *
     * @param identifier The unique identifier (name or ID) of the node to be searched.
     * @return The TreeNode associated with the identifier, or null if not found.
     */
    public TreeNode searchNode(String identifier) {
        // First, check the nameMap to see if the identifier matches a node name
        if (nameMap.containsKey(identifier)) {
            return nameMap.get(identifier);
        }

        // If not found in nameMap, check the nodeMap by ID
        return nodeMap.get(identifier);
    }

    public String searchNodeE(TreeNode targetNode){

        TreeNode parentOperation = findParentOperation(root, targetNode);

        // Build the response with node details
        StringBuilder result = new StringBuilder();
        result.append("Node Type: ").append(targetNode.getType()).append("\n");

        if (targetNode.getType().equals("material")) {
            result.append("Quantity: ").append(targetNode.getQuantity()).append("\n");
        }

        if (parentOperation != null) {
            result.append("Parent Operation: ").append(parentOperation.getName()).append("\n");
        } else {
            result.append("Parent Operation: None\n");
        }

        return result.toString();
    }

    /**
     * Helper method to find the parent operation of a given node.
     *
     * @param currentNode The current node being traversed.
     * @param targetNode  The target node for which the parent is being searched.
     * @return The parent operation node, or null if not found.
     */
    private TreeNode findParentOperation(TreeNode currentNode, TreeNode targetNode) {
        if (currentNode == null) {
            return null;
        }

        // Check if the current node is the parent of the target node
        for (TreeNode child : currentNode.getChildren()) {
            if (child.equals(targetNode)) {
                return currentNode.getType().equals("operation") ? currentNode : null;
            }

            // Recursively search in the child nodes
            TreeNode parent = findParentOperation(child, targetNode);
            if (parent != null) {
                return parent;
            }
        }

        return null;
    }

    /**
     * Updates the quantity of a material node and propagates the change to its dependent nodes.
     *
     * @param materialNode The material node to be updated.
     * @param newQuantity  The new quantity to be set.
     */
    // US12 - Update Material Quantities in the Production Tree
    public static void updateMaterialQuantities(TreeNode materialNode, double newQuantity) {
        if (newQuantity <= 0) {
            throw new IllegalArgumentException("New quantity must be greater than 0.");
        }

        double updateFactor = newQuantity / materialNode.getQuantity();

        materialNode.setQuantity(newQuantity);
        System.out.println("Updated " + materialNode.getName() + " to " + newQuantity);

        if (!materialNode.getChildren().isEmpty()) {
            materialNode.propagateUpdate(updateFactor);
        } else {
            System.out.println("Material has no dependent nodes to update.");
        }
    }


    public List<TreeNode> getMaterialsInOrder(boolean ascending) {
        return materialBST.getMaterialsInOrder(ascending);
    }

    public TreeNode getRoot() {
        return root;
    }
}