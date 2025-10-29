package org.example.Domain;


import java.util.ArrayList;
import java.util.List;

public class MaterialBST {
    private MaterialBSTNode root;

    public MaterialBST() {
        this.root = null;
    }

    public void insert(TreeNode material) {
        root = insertRec(root, material);
    }

    private MaterialBSTNode insertRec(MaterialBSTNode root, TreeNode material) {
        if (root == null) {
            root = new MaterialBSTNode(material.getQuantity());
            root.addMaterial(material);
            return root;
        }

        if (material.getQuantity() < root.getQuantity()) {
            root.setLeft(insertRec(root.getLeft(), material));
        } else if (material.getQuantity() > root.getQuantity()) {
            root.setRight(insertRec(root.getRight(), material));
        } else {
            root.addMaterial(material);
        }

        return root;
    }

    public List<TreeNode> getMaterialsInOrder(boolean ascending) {
        List<TreeNode> materials = new ArrayList<>();
        if (ascending) {
            inOrder(root, materials);
        } else {
            reverseInOrder(root, materials);
        }
        return materials;
    }

    private void inOrder(MaterialBSTNode root, List<TreeNode> materials) {
        if (root != null) {
            inOrder(root.getLeft(), materials);
            materials.addAll(root.getMaterials());
            inOrder(root.getRight(), materials);
        }
    }

    private void reverseInOrder(MaterialBSTNode root, List<TreeNode> materials) {
        if (root != null) {
            reverseInOrder(root.getRight(), materials);
            materials.addAll(root.getMaterials());
            reverseInOrder(root.getLeft(), materials);
        }
    }
}