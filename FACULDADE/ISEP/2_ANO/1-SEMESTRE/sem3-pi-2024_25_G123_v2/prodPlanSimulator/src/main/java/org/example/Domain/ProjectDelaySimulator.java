package org.example.Domain;

import org.example.structures.graph.pert.PertGraph;
import org.example.structures.graph.pert.PertVertex;
import org.example.structures.graph.Edge;

import java.util.*;

/**
 * A classe {@code ProjectDelaySimulator} simula atrasos em projetos e os seus efeitos
 * num grafo PERT. Permite aplicar atrasos a atividades específicas, redefinir atrasos
 * e calcular métricas importantes, como tempos de início/término e a duração do caminho crítico.
 */
public class ProjectDelaySimulator {

    /* Declaração de atributos privados para o grafo PERT e mapas de durações e atrasos. */
    private final PertGraph<PertVertex<String, Integer>, Integer> pertGraph;
    private final Map<String, Integer> originalDurations; // Armazena a duração original de cada atividade.
    private final Map<String, Integer> delays; // Armazena os atrasos aplicados a cada atividade.

    /**
     * Construtor que inicializa a instância do simulador de atrasos.
     *
     * @param graph O grafo PERT que representa as atividades e dependências do projeto.
     */
    public ProjectDelaySimulator(PertGraph<PertVertex<String, Integer>, Integer> graph) {
        this.pertGraph = graph;
        this.originalDurations = new HashMap<>(); // Inicializa o mapa das durações originais.
        this.delays = new HashMap<>(); // Inicializa o mapa dos atrasos.

        /* Itera pelos vértices do grafo e armazena as durações iniciais e os atrasos (inicialmente zero). */
        for (PertVertex<String, Integer> vertex : graph.vertices()) {
            String activityId = vertex.getElement(); // Obtém o identificador da atividade.
            originalDurations.put(activityId, vertex.getDuration()); // Regista a duração original.
            delays.put(activityId, 0); // Define o atraso inicial como zero.
        }
    }

    /**
     * Aplica um atraso a uma atividade específica no projeto.
     *
     * @param activityId  ID da atividade a ser atrasada.
     * @param delayAmount Quantidade de atraso a aplicar (em unidades de tempo).
     * @return {@code true} se o atraso foi aplicado com sucesso, {@code false} caso contrário.
     */
    public boolean applyDelay(String activityId, int delayAmount) {
        if (!originalDurations.containsKey(activityId)) { // Verifica se a atividade existe.
            return false; // Retorna falso se a atividade não for encontrada.
        }
        int currentDelay = delays.getOrDefault(activityId, 0); // Obtém o atraso atual.
        delays.put(activityId, currentDelay + delayAmount); // Atualiza o atraso com o novo valor.
        return true; // Retorna verdadeiro para indicar sucesso.
    }

    /**
     * Redefine todos os atrasos, restaurando o projeto ao estado original.
     */
    public void resetDelays() {
        delays.clear(); // Limpa o mapa de atrasos.
        /* Reaplica o atraso inicial (zero) a todas as atividades. */
        for (String activityId : originalDurations.keySet()) {
            delays.put(activityId, 0);
        }
    }

    /**
     * Calcula os tempos mais cedo de início e término para todas as atividades do projeto.
     */
    public void calculateEarliestTimes() {
        /* Define os tempos mais cedo de início e término para todos os vértices como zero. */
        for (PertVertex<String, Integer> vertex : pertGraph.vertices()) {
            vertex.setEarliestStartTime(0);
            vertex.setEarliestFinishTime(0);
        }

        /* Inicializa a fila com os vértices de início (grau de entrada zero). */
        Queue<PertVertex<String, Integer>> queue = new LinkedList<>();
        for (PertVertex<String, Integer> vertex : pertGraph.vertices()) {
            if (pertGraph.inDegree(vertex) == 0) {
                queue.add(vertex);
                vertex.setEarliestStartTime(0); // Início mais cedo = 0 para as atividades iniciais.
                vertex.setEarliestFinishTime(getEffectiveDuration(vertex)); // Define o término mais cedo.
            }
        }

        /* Propaga os tempos mais cedo pelos vértices adjacentes. */
        while (!queue.isEmpty()) {
            PertVertex<String, Integer> currentVertex = queue.poll(); // Remove o próximo vértice.

            for (Edge<PertVertex<String, Integer>, Integer> edge : pertGraph.outgoingEdges(currentVertex)) {
                PertVertex<String, Integer> neighbor = edge.getVDest(); // Obtém o vértice destino.
                int earliestStart = Math.max(
                        neighbor.getEarliestStartTime(),
                        currentVertex.getEarliestFinishTime() // O término mais cedo do predecessor.
                );
                neighbor.setEarliestStartTime(earliestStart); // Atualiza o início mais cedo.
                neighbor.setEarliestFinishTime(earliestStart + getEffectiveDuration(neighbor)); // Atualiza o término.
                queue.add(neighbor); // Adiciona o vértice na fila para processar os seus vizinhos.
            }
        }
    }

    /**
     * Calcula os tempos mais tarde de início e término para todas as atividades do projeto.
     */
    public void calculateLatestTimes() {
        /* Determina o tempo máximo (duração do projeto). */
        int maxTime = pertGraph.vertices().stream()
                .mapToInt(PertVertex::getEarliestFinishTime)
                .max()
                .orElse(0);

        /* Define os tempos mais tarde inicialmente como o tempo máximo. */
        for (PertVertex<String, Integer> vertex : pertGraph.vertices()) {
            vertex.setLatestFinishTime(maxTime);
            vertex.setLatestStartTime(maxTime - getEffectiveDuration(vertex));
        }

        /* Inicializa a fila com os vértices finais (grau de saída zero). */
        Queue<PertVertex<String, Integer>> queue = new LinkedList<>();
        for (PertVertex<String, Integer> vertex : pertGraph.vertices()) {
            if (pertGraph.outDegree(vertex) == 0) {
                queue.add(vertex);
            }
        }

        /* Propaga os tempos mais tarde pelos vértices predecessores. */
        while (!queue.isEmpty()) {
            PertVertex<String, Integer> currentVertex = queue.poll();

            for (Edge<PertVertex<String, Integer>, Integer> edge : pertGraph.incomingEdges(currentVertex)) {
                PertVertex<String, Integer> predecessor = edge.getVOrig();
                int latestFinish = Math.min(
                        predecessor.getLatestFinishTime(),
                        currentVertex.getLatestStartTime()
                );
                predecessor.setLatestFinishTime(latestFinish); // Atualiza o término mais tarde.
                predecessor.setLatestStartTime(latestFinish - getEffectiveDuration(predecessor)); // Atualiza o início.
                queue.add(predecessor); // Adiciona o predecessor à fila.
            }
        }
    }

    /**
     * Calcula a duração total do caminho crítico (sequência com folga zero).
     *
     * @return A duração total do caminho crítico.
     */
    public int calculateCriticalPath() {
        return pertGraph.vertices().stream()
                .filter(vertex -> vertex.getLatestStartTime() == vertex.getEarliestStartTime()) // Verifica folga zero.
                .mapToInt(this::getEffectiveDuration) // Soma as durações.
                .sum();
    }

    /**
     * Calcula a duração efetiva de um vértice, considerando os atrasos aplicados.
     *
     * @param vertex O vértice para calcular a duração efetiva.
     * @return A duração efetiva do vértice.
     */
    private int getEffectiveDuration(PertVertex<String, Integer> vertex) {
        String activityId = vertex.getElement(); // Obtém o ID da atividade.
        int originalDuration = originalDurations.getOrDefault(activityId, 0); // Duração original.
        int delay = delays.getOrDefault(activityId, 0); // Atraso aplicado.
        return originalDuration + delay; // Retorna a duração efetiva.
    }
}
