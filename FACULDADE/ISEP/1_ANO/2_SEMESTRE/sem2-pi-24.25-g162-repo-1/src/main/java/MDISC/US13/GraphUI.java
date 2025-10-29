package MDISC.US13;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;
import org.graphstream.ui.view.Viewer;

import java.util.List;

public class GraphUI {
    public static void visualizeGraphFromMatrix(List<Station> stations, List<Line> connections) {
        Graph graph = new SingleGraph("Railway Network");

        graph.setAttribute("ui.stylesheet", """
    node {
        size: 24px;
        shape: circle;
        text-size: 14px;
        text-alignment: above;
        text-color: #2b2d42;
        text-style: bold;
        stroke-mode: plain;
        stroke-color: #333;
        stroke-width: 2px;
        shadow-mode: gradient-radial;
        shadow-color: #aaaaaa, #ffffff;
    }

    node.station {
        fill-color: #4ea8de; /* azul claro */
    }

    node.terminal {
        fill-color: #f9c74f; /* amarelo dourado */
        size: 28px;
    }

    node.depot {
        fill-color: #f3722c; /* laranja queimado */
        size: 22px;
    }

    edge {
        size: 2px;
        fill-color: #adb5bd; /* cinza suave */
        arrow-shape: arrow;
        arrow-size: 8px, 5px;
    }

    edge.electrified {
        fill-color: #43aa8b; /* verde turquesa */
        size: 3px;
    }
""");



        // Adicionar nós com classes para estilo
        for (Station station : stations) {
            Node node = graph.addNode(station.getName());
            node.setAttribute("ui.label", station.getName());

            String nodeClass = switch (station.getType()) {
                case STATION -> "station";
                case TERMINAL -> "terminal";
                case DEPOT -> "depot";
            };

            node.setAttribute("ui.class", nodeClass);
        }

        for (Line conn : connections) {
            Station a = conn.getStationA();
            Station b = conn.getStationB();

            if (stations.contains(a) && stations.contains(b)) {
                String nodeA = a.getName();
                String nodeB = b.getName();
                String edgeId = nodeA + "_" + nodeB;
                String reverseId = nodeB + "_" + nodeA;

                if (graph.getEdge(edgeId) == null && graph.getEdge(reverseId) == null) {
                    Edge edge = graph.addEdge(edgeId, nodeA, nodeB);
                    if (conn.isElectrified()) {
                        edge.setAttribute("ui.class", "electrified");
                    }
                }
            }
        }


        Viewer viewer = graph.display();
        viewer.enableAutoLayout();
    }

    private static Viewer lastViewer = null;

    /*public static void Graph(List<Station> stations, List<Line> connections, List<Station> shortestPath) {
        // Fechar viewer anterior se existir
        if (lastViewer != null) {
            lastViewer.close();
        }

        Graph graph = new SingleGraph("Network");

        graph.setAttribute("ui.stylesheet", """
            node {
                size: 18px;
                text-size: 14px;
                text-alignment: above;
                fill-color: #4A90E2;
                stroke-mode: plain;
                stroke-color: #1C3F75;
            }
            edge {
                size: 2px;
                fill-color: #999999;
            }
            edge.shortest {
                fill-color: #E74C3C;
                size: 4px;
            }
        """);

        // Evitar duplicação de nós
        for (Station station : stations) {
            String stationName = station.getName();
            if (graph.getNode(stationName) == null) {
                Node node = graph.addNode(stationName);
                node.setAttribute("ui.label", stationName);
            }
        }

        // Adicionar arestas (ligações)
        for (Line conn : connections) {
            Station a = conn.getStationA();
            Station b = conn.getStationB();
            if (stations.contains(a) && stations.contains(b)) {
                String nodeA = a.getName();
                String nodeB = b.getName();
                String edgeId = nodeA + "_" + nodeB;
                if (graph.getEdge(edgeId) == null && graph.getEdge(nodeB + "_" + nodeA) == null) {
                    graph.addEdge(edgeId, nodeA, nodeB);
                }
            }
        }

        // Destacar caminho mais curto
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            String nodeA = shortestPath.get(i).getName();
            String nodeB = shortestPath.get(i + 1).getName();
            Edge edge = graph.getEdge(nodeA + "_" + nodeB);
            if (edge == null) edge = graph.getEdge(nodeB + "_" + nodeA);
            if (edge != null) edge.setAttribute("ui.class", "shortest");
        }

        lastViewer = graph.display(); // guardar viewer atual para fechar depois
        lastViewer.enableAutoLayout();
    }*/

    public static void Graph(List<Station> stations, List<Line> connections, List<Station> shortestPath) {

        Graph graph = new SingleGraph("Railway Network");

        graph.setAttribute("ui.stylesheet", """
    graph {
        padding: 100px;
    }

    node {
        size: 20px;
        shape: circle;
        fill-color: #66ccff;
        stroke-mode: plain;
        stroke-color: #003366;
        text-size: 14px;
        text-alignment: under;
        text-color: black;
        text-style: bold;
    }

    edge {
        size: 2px;
        fill-color: #333333;
        arrow-shape: arrow;
        arrow-size: 10px, 7px;
    }

    edge.shortest {
        fill-color: red;
        size: 4px;
    }
""");


        for (Station station : stations) {

            Node node = graph.addNode(station.getName());

            node.setAttribute("ui.label", station.getName());


        }




        for (Line conn : connections) {

            Station a = conn.getStationA();

            Station b = conn.getStationB();

            if (stations.contains(a) && stations.contains(b)) {

                String nodeA = a.getName();

                String nodeB = b.getName();

                String edgeId = nodeA + "_" + nodeB;

                if (graph.getEdge(edgeId) == null && graph.getEdge(nodeB + "_" + nodeA) == null) {

                    Edge edge = graph.addEdge(edgeId, nodeA, nodeB);

                }

            }

        }



        // Highlight the shortest path

        for (int i = 0; i < shortestPath.size() - 1; i++) {

            String nodeA = shortestPath.get(i).getName();

            String nodeB = shortestPath.get(i + 1).getName();

            Edge edge = graph.getEdge(nodeA + "_" + nodeB);

            if (edge == null) edge = graph.getEdge(nodeB + "_" + nodeA);

            if (edge != null) edge.setAttribute("ui.class", "shortest");

        }



        Viewer viewer = graph.display();

        viewer.enableAutoLayout();
    }

}