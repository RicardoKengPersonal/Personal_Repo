package org.example.esinf.usei15;

import org.example.Domain.Tree;
import org.example.Domain.TreeNode;
import org.example.structures.tree.AVL;

public class ExtractBooTree {

    public static AVL<TreeNode> extractBooTree(TreeNode booTree) {

        AVL<TreeNode> avlBooTree = new AVL<>();

        addNodesToAVL(booTree, avlBooTree);

        return avlBooTree;
    }

    private static void addNodesToAVL(TreeNode node, AVL<TreeNode> avlTree) {

        if (node == null) return;

        avlTree.insert(node);

        for (TreeNode child : node.getChildren()) {

            addNodesToAVL(child, avlTree);
        }
    }
}
