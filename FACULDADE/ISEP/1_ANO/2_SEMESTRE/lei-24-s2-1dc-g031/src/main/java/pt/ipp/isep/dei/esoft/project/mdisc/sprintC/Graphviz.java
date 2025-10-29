package pt.ipp.isep.dei.esoft.project.mdisc.sprintC;

import pt.ipp.isep.dei.esoft.project.mdisc.sprintC.Route;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public abstract class Graphviz {

    public static void execute(List<Route> allRoutes, List<Route> shortestPath, String filename) {
        try {
            writeGraphvizDotFile(allRoutes, shortestPath);

            String caminhoImgFinal = "src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/sprintC/files/output/" + filename + ".svg";
            String comando = "dot -Tsvg src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/sprintC/files/output/temp_gv_file.dot -o " + caminhoImgFinal;
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", comando);
            pb.start().waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao executar o GraphViz: " + e.getMessage());
        }
    }

    private static void writeGraphvizDotFile(List<Route> allRoutes, List<Route> shortestPath) throws IOException {
        List<Route> filteredAllRoutes = new ArrayList<>();

        // Filter allRoutes to remove duplicates
        for (Route r : allRoutes) {
            boolean isDuplicate = false;
            for (Route s : filteredAllRoutes) {
                if (s.getOrigin().getId().equals(r.getDestination().getId()) && s.getDestination().getId().equals(r.getOrigin().getId())) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate && !shortestPath.contains(r)) {
                filteredAllRoutes.add(r);
            }
        }

        // Remove routes from filteredAllRoutes that are in shortestPath
        filteredAllRoutes.removeIf(r -> {
            for (Route s : shortestPath) {
                if (r.getOrigin().getId().equals(s.getDestination().getId()) && r.getDestination().getId().equals(s.getOrigin().getId())) {
                    return true;
                }
            }
            return false;
        });

        try (PrintWriter pW = new PrintWriter("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/sprintC/files/output/temp_gv_file.dot")) {
            pW.print("graph G {\n" +
                    "fontname=\"Helvetica,Arial,sans-serif\"\n" +
                    "node [fontname=\"Helvetica,Arial,sans-serif\", color=black, fontcolor=black]\n" +
                    "edge [fontname=\"Helvetica,Arial,sans-serif\"]\n" +
                    "layout=dot\n");

            for (Route r : filteredAllRoutes) {
                pW.println(r.getOrigin().getId() + " -- " + r.getDestination().getId() + " [label=\"" + r.getCost() + "\"]");
            }

            for (Route r : shortestPath) {
                pW.println(r.getOrigin().getId() + " -- " + r.getDestination().getId() + " [label=\"" + r.getCost() + "\", color=blue, penwidth=2.0]");
            }

            pW.println("}");
        }
    }
}
