package org.example.Domain;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * The type Dot generation.
 */
public class DotGeneration {

    /**
     * Generate dot file.
     *
     * @param root     the root
     * @param fileName the file name
     * @throws IOException the io exception
     */
    public static void generateDotFile(TreeNode root, String fileName) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("digraph Tree {\n");
            writer.write("  node [shape=ellipse, fontsize=10];\n");
            writeNode(root, writer, new HashMap<>());
            writer.write("}\n");
        }
    }

    private static void writeNode(TreeNode node, FileWriter writer, Map<TreeNode, String> nodeIdMap) throws IOException {
        String nodeId = nodeIdMap.computeIfAbsent(node, k -> UUID.randomUUID().toString());

        writer.write(String.format("  \"%s\" [label=\"%s\\n(%s)\"];\n", nodeId, node.getName(), node.getType()));

        for (TreeNode child : node.getChildren()) {
            String childId = nodeIdMap.computeIfAbsent(child, k -> UUID.randomUUID().toString());
            writer.write(String.format("  \"%s\" -> \"%s\";\n", nodeId, childId));
            writeNode(child, writer, nodeIdMap);
        }
    }

    /**
     * Generate material tree files.
     *
     * @param root        the root
     * @param dotFileName the dot file name
     * @param svgFileName the svg file name
     */
    public static void generateMaterialTreeFiles(TreeNode root, String dotFileName, String svgFileName) {
        try {
            TreeNode helperRoot = transformToMaterialTree(root);
            generateDotFile(helperRoot, dotFileName);
            generateSvg(dotFileName, svgFileName);
            System.out.println("Material tree files generated successfully.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void generateOperationTreeFiles(TreeNode root, String dotFileName, String svgFileName) {
        try {
            TreeNode helperRoot = transformToOperationTree(root);
            generateDotFile(helperRoot, dotFileName);
            generateSvg(dotFileName, svgFileName);
            System.out.println("Operation tree files generated successfully.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static TreeNode transformToMaterialTree(TreeNode node) {
        if (node == null) {
            return null;
        }

        // Se o nó for do tipo "material", cria-se uma cópia deste nó.
        if ("material".equals(node.getType())) {
            TreeNode materialNode = new TreeNode(node.getId(), node.getName(), node.getType(), node.getQuantity());

            // Recursivamente adicionar os filhos, se forem do tipo "material".
            for (TreeNode child : node.getChildren()) {
                TreeNode materialChild = transformToMaterialTree(child);
                if (materialChild != null) {
                    materialNode.addChild(materialChild);
                }
            }
            return materialNode;
        }

        // Se o nó for do tipo "operation", encontramos o primeiro nó "material" como filho.
        else if ("operation".equals(node.getType())) {
            TreeNode firstMaterialNode = null;

            // Procura pelo primeiro nó "material".
            for (TreeNode child : node.getChildren()) {
                if ("material".equals(child.getType())) {
                    firstMaterialNode = child;
                    break;
                }
            }

            if (firstMaterialNode != null) {
                // Substitui o nó de operação pelo primeiro nó "material" encontrado.
                TreeNode materialNode = transformToMaterialTree(firstMaterialNode);

                // Adiciona os outros filhos (exceto o primeiro "material") como filhos do novo nó.
                for (TreeNode sibling : node.getChildren()) {
                    if (!sibling.equals(firstMaterialNode)) {
                        TreeNode siblingMaterial = transformToMaterialTree(sibling);
                        if (siblingMaterial != null) {
                            materialNode.addChild(siblingMaterial);
                        }
                    }
                }
                return materialNode;
            } else {
                // Se não houver nó "material", combina os filhos da operação em um único nó.
                TreeNode combinedNode = null;
                for (TreeNode child : node.getChildren()) {
                    TreeNode materialChild = transformToMaterialTree(child);
                    if (materialChild != null) {
                        if (combinedNode == null) {
                            combinedNode = materialChild;
                        } else {
                            combinedNode.addChild(materialChild);
                        }
                    }
                }
                return combinedNode;
            }
        }

        // Retorna null se o nó não for nem "material" nem "operation".
        return null;
    }

    private static TreeNode transformToOperationTree(TreeNode node) {
        if (node == null) {
            return null;
        }

        // Se o nó for do tipo "operation", cria-se uma cópia deste nó.
        if ("operation".equals(node.getType())) {
            TreeNode operationNode = new TreeNode(node.getId(), node.getName(), node.getType(), node.getQuantity());

            // Recursivamente adicionar os filhos, se forem do tipo "operation".
            for (TreeNode child : node.getChildren()) {
                TreeNode operationChild = transformToOperationTree(child);
                if (operationChild != null) {
                    operationNode.addChild(operationChild);
                }
            }
            return operationNode;
        }

        // Se o nó for do tipo "material", encontramos o primeiro nó "operation" como filho.
        else if ("material".equals(node.getType())) {
            TreeNode firstOperationNode = null;

            // Procura pelo primeiro nó "operation".
            for (TreeNode child : node.getChildren()) {
                if ("operation".equals(child.getType())) {
                    firstOperationNode = child;
                    break;
                }
            }

            if (firstOperationNode != null) {
                // Substitui o nó de material pelo primeiro nó "operation" encontrado.
                TreeNode operationNode = transformToOperationTree(firstOperationNode);

                // Adiciona os outros filhos (exceto o primeiro "operation") como filhos do novo nó.
                for (TreeNode sibling : node.getChildren()) {
                    if (!sibling.equals(firstOperationNode)) {
                        TreeNode siblingOperation = transformToOperationTree(sibling);
                        if (siblingOperation != null) {
                            operationNode.addChild(siblingOperation);
                        }
                    }
                }
                return operationNode;
            } else {
                // Se não houver nó "operation", combina os filhos da material em um único nó.
                TreeNode combinedNode = null;
                for (TreeNode child : node.getChildren()) {
                    TreeNode operationChild = transformToOperationTree(child);
                    if (operationChild != null) {
                        if (combinedNode == null) {
                            combinedNode = operationChild;
                        } else {
                            combinedNode.addChild(operationChild);
                        }
                    }
                }
                return combinedNode;
            }
        }

        // Retorna null se o nó não for nem "operation" nem "material".
        return null;
    }


    /**
     * Generate svg.
     *
     * @param dotFileName the dot file name
     * @param svgFileName the svg file name
     * @throws IOException          the io exception
     * @throws InterruptedException the interrupted exception
     */
    public static void generateSvg(String dotFileName, String svgFileName) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("dot", "-Tsvg", "-o", svgFileName, dotFileName);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        process.waitFor();
    }

}