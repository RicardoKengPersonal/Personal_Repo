package org.example.Domain;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class OrganizationTreeTest {

    private Tree tree;
    private TreeNode root;
    private Map<String, TreeNode> nodeMap;

    @BeforeEach
    public void setUp() {
        // Criando o nó raiz
        root = new TreeNode("Op1", "Operação 1", "operation", 0);

        // Criando um mapa de nós para operações e materiais
        nodeMap = new HashMap<>();

        // Criando nós de materiais
        TreeNode material1 = new TreeNode("M1", "Material 1", "material", 10.5);
        TreeNode material2 = new TreeNode("M2", "Material 2", "material", 5.0);
        TreeNode material3 = new TreeNode("M3", "Material 3", "material", 15.0);

        // Associando materiais à operação raiz
        root.addChild(material1);
        root.addChild(material2);
        root.addChild(material3);

        // Adicionando nós ao mapa
        nodeMap.put(root.getId(), root);
        nodeMap.put(material1.getId(), material1);
        nodeMap.put(material2.getId(), material2);
        nodeMap.put(material3.getId(), material3);

        // Criando a árvore com o nó raiz e o mapa
        tree = new Tree(root, nodeMap);
    }

    @Test
    public void testCreateTree() {
        // Teste para verificar a árvore foi corretamente criada
        tree.CreateTree();

        // Verificar se os materiais foram corretamente adicionados à árvore
        assertEquals(3, root.getChildren().size());
        assertTrue(root.getChildren().contains(nodeMap.get("M1")));
        assertTrue(root.getChildren().contains(nodeMap.get("M2")));
        assertTrue(root.getChildren().contains(nodeMap.get("M3")));
    }

    @Test
    public void testDisplayTree() {
        // Teste para verificar a exibição da árvore
        tree.CreateTree();

        // Redefina o conteúdo do método displayTree para que ele use System.out.println
        // Aqui, verificamos a árvore manualmente ou por logs de saída se necessário
        tree.displayTree(root, ""); // Caso de uso para exibir a árvore
        // Este teste pode ser verificado manualmente ou por capturar a saída do console
    }

    @Test
    public void testNodeMapContainsCorrectNodes() {
        // Teste para verificar se todos os nós estão presentes no nodeMap
        tree.CreateTree();

        // Verificar se o nodeMap contém os materiais e operações corretas
        assertTrue(nodeMap.containsKey("Op1"));
        assertTrue(nodeMap.containsKey("M1"));
        assertTrue(nodeMap.containsKey("M2"));
        assertTrue(nodeMap.containsKey("M3"));
    }

    @Test
    public void testNodeRelations() {
        // Teste para verificar as relações entre nós
        tree.CreateTree();

        // Verificando se a operação raiz contém os materiais corretos
        assertTrue(root.getChildren().contains(nodeMap.get("M1")));
        assertTrue(root.getChildren().contains(nodeMap.get("M2")));
        assertTrue(root.getChildren().contains(nodeMap.get("M3")));

        // Verificando se o material M1 tem quantidade correta
        assertEquals(10.5, nodeMap.get("M1").getQuantity());
    }
}



