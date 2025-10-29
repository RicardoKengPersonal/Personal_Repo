package org.example.Domain;

import java.util.*;

/**
 * The {@code CriticalPathAnalyzer} class is responsible for analyzing and displaying
 * the critical path of operations in a tree-like structure of nodes.
 * It calculates the depth of each node in the tree and orders them based on
 * their importance for analysis.
 */
public class CriticalPathAnalyzer {
    /**
     * A priority queue (max-heap) for storing nodes and their depths, ordered by depth.
     */
    private final PriorityQueue<TreeNodeDepth> criticalPathQueue;

    /**
     * Constructs a new {@code CriticalPathAnalyzer} with an empty priority queue.
     */
    public CriticalPathAnalyzer() {
        // Max-heap based on depth
        this.criticalPathQueue = new PriorityQueue<>();
    }

    /**
     * Analyzes the critical path starting from the root node.
     * Traverses the tree and records nodes and their depths into the priority queue.
     *
     * @param root the root node of the tree to analyze
     */
    public void analyze(TreeNode root) {
        traverseAndAddToQueue(root, 0); // Start traversal from depth 0
    }

    /**
     * Traverses the tree recursively and adds each node along with its depth to the priority queue.
     *
     * @param node  the current node being traversed
     * @param depth the depth of the current node in the tree
     */
    private void traverseAndAddToQueue(TreeNode node, int depth) {
        if (node == null) return;

        // Adiciona o nó atual à fila de prioridade (max-heap)
        criticalPathQueue.offer(new TreeNodeDepth(node, depth));

        // Percorre recursivamente os nós filhos
        for (TreeNode child : node.getChildren()) {
            traverseAndAddToQueue(child, depth + 1); // Aumenta a profundidade para cada nó filho
        }
    }

    /**
     * Displays the critical path by printing nodes in order of their importance (depth).
     * Nodes with greater depth are displayed first.
     */
    public void displayCriticalPath() {
        System.out.println("Critical Path Operations (in order of importance):");
        // Enquanto a fila não estiver vazia, imprime os nós na ordem de profundidade
        while (!criticalPathQueue.isEmpty()) {
            TreeNodeDepth nodeDepth = criticalPathQueue.poll();
            // Imprime o nome, tipo e profundidade do nó
            System.out.println("Node: " + nodeDepth.node.getName() +
                    ", Type: " + nodeDepth.node.getType() +
                    ", Depth: " + nodeDepth.depth);
        }
    }

    /**
     * Retrieves the critical path as a list of nodes ordered by their depth.
     *
     * @return a list of {@code TreeNodeDepth} objects in descending order of depth
     */
    public List<TreeNodeDepth> getCriticalPath() {
        // Cria uma lista a partir dos elementos da fila de prioridade
        List<TreeNodeDepth> result = new ArrayList<>(criticalPathQueue);
        // Ordena a lista em ordem decrescente de profundidade
        result.sort(Collections.reverseOrder());
        return result; // Retorna a lista ordenada
    }

    /**
     * A helper class that pairs a {@code TreeNode} with its depth for prioritization in the queue.
     */
    public static class TreeNodeDepth implements Comparable<TreeNodeDepth> {
        public final TreeNode node; // O nó da árvore
        public final int depth;     // A profundidade do nó na árvore

        /**
         * Constructs a new {@code TreeNodeDepth} object.
         *
         * @param node  the tree node to associate
         * @param depth the depth of the node in the tree
         */
        public TreeNodeDepth(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }

        /**
         * Compares this {@code TreeNodeDepth} object to another based on depth.
         * Used to prioritize nodes in the queue in descending order of depth.
         *
         * @param other the other {@code TreeNodeDepth} object to compare
         * @return a negative integer, zero, or a positive integer as this object's depth
         * is greater than, equal to, or less than the other object's depth
         */
        @Override
        public int compareTo(TreeNodeDepth other) {
            // Implementa a comparação inversa para criar uma max-heap
            return Integer.compare(other.depth, this.depth);
        }
    }

    /**
     * Performs a complete critical path analysis by analyzing and displaying the critical path
     * of the provided tree starting from the root node.
     *
     * @param root the root node of the tree to analyze
     */
    public static void performCriticalPathAnalysis(TreeNode root) {
        try {
            CriticalPathAnalyzer analyzer = new CriticalPathAnalyzer();
            // Executa a análise do caminho crítico a partir do nó raiz
            analyzer.analyze(root);
            // Exibe os resultados do caminho crítico
            analyzer.displayCriticalPath();
        } catch (Exception e) {
            // Trata possíveis exceções durante a análise
            System.out.println("Error during analysis: " + e.getMessage());
        }
    }
}
