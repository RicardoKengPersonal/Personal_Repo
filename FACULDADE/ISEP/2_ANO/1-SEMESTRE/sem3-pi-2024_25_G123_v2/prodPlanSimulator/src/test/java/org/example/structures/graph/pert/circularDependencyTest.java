package org.example.structures.graph.pert;

import org.example.structures.graph.pert.PertGraph;
import org.example.structures.graph.pert.PertVertex;

import java.util.HashMap;
import java.util.Map;


import java.util.HashMap;
import java.util.Map;

public class circularDependencyTest {

        public static void main(String[] args) {
            // Criação do grafo PERT
            PertGraph<String, Integer> graph = new PertGraph<>(true);

            // Adiciona os vértices
            graph.addVertex("A");
            graph.addVertex("B");
            graph.addVertex("C");
            graph.addVertex("D");

            // Adiciona as arestas (com pesos/duração)
            graph.addEdge("A", "B", 3);
            graph.addEdge("B", "C", 2);
            graph.addEdge("C", "D", 1);

            // Teste sem ciclo
            System.out.println("Grafo contém ciclo? " + graph.hasCircularDependency()); // Deve imprimir: false

            // Adiciona um ciclo
            graph.addEdge("D", "B", 4); // Agora existe um ciclo: B -> C -> D -> B

            // Teste com ciclo
            System.out.println("Grafo contém ciclo? " + graph.hasCircularDependency()); // Deve imprimir: true
        }
    }
