package org.example.Domain;

import java.util.ArrayList;
import java.util.List;

public class MaterialBSTNode {
    private double quantity;
    private List<TreeNode> materials;
    private MaterialBSTNode left;
    private MaterialBSTNode right;

    public MaterialBSTNode(double quantity) {
        this.quantity = quantity;
        this.materials = new ArrayList<>();
        this.left = null;
        this.right = null;
    }

    public double getQuantity() {
        return quantity;
    }

    public List<TreeNode> getMaterials() {
        return materials;
    }

    public MaterialBSTNode getLeft() {
        return left;
    }

    public void setLeft(MaterialBSTNode left) {
        this.left = left;
    }

    public MaterialBSTNode getRight() {
        return right;
    }

    public void setRight(MaterialBSTNode right) {
        this.right = right;
    }

    public void addMaterial(TreeNode material) {
        this.materials.add(material);
    }
}