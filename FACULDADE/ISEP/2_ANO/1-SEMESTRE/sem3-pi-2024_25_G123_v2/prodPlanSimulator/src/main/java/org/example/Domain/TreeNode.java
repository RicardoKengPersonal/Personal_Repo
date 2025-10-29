package org.example.Domain;

import java.util.ArrayList;
import java.util.List;

public class TreeNode implements Comparable<TreeNode> {
    private String id;
    private String name;
    private String type;
    private double quantity;
    private List<TreeNode> children;

    public TreeNode(String id, String name, String type, double quantity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.children = new ArrayList<>();
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void addChild(TreeNode child) {
        this.children.add(child);
    }

    @Override
    public String toString() {
        return "Node{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                ", children=" + children +
                '}';
    }

    /**
     * Update the quantity of this node and propagate the update to its children.
     *
     * @param factor the factor by which to update the quantity
     */
    public void propagateUpdate(double factor) {
        // Update dependent children based on the new quantity
        for (TreeNode child : children) {
            double updatedQuantity = child.getQuantity() * factor;
            child.setQuantity(updatedQuantity);
            System.out.println("Updated " + child.getName() + " to " + updatedQuantity);

            child.propagateUpdate(factor);
        }
    }

    @Override
    public int compareTo(TreeNode o) {
        return this.id.compareTo(o.id);
    }
}