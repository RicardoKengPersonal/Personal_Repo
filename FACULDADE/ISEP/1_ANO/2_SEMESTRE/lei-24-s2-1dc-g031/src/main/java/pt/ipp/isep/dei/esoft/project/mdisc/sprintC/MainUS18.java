package pt.ipp.isep.dei.esoft.project.mdisc.sprintC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainUS18 {
    public static List<Integer> index = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //ExternalCSV e1 = new ExternalCSV("routesTest.csv", "names.csv");
        ExternalCSV e1 = new ExternalCSV("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/sprintC/files/us18_matrix.csv", "src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/sprintC/files/us18_points_names.csv");

        e1.readRoutesFromMatrixAndNames();
        List<Route> routes = e1.getAllRoutes();

        List<List<Route>> allShortestPaths = new ArrayList<>();

        // Asks to select the starting point
        int i = 1;
        for (Point pointIn : e1.getAllPoints()) {
            System.out.println(i + " - " + pointIn.getId());
            i++;
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("Choose your point: ");
        int ind3 = sc.nextInt() - 1;

        // Find all AP points
        List<Point> allAPPoints = new ArrayList<>();
        for (Integer apIndex : index) {
            allAPPoints.add(e1.getAllPoints().get(apIndex));
        }

        for (Point pointIn : e1.getAllPoints()) {
            List<Route> shortestPath = DijkstraAlgorithm.findShortestPathToAnyEndPoint(pointIn, allAPPoints, routes);
            allShortestPaths.add(shortestPath);
        }

        Graphviz.execute(routes, allShortestPaths.get(ind3), "outMstRoutesUS18"); // Aqui deve ser outShortestPath
        ExternalCSV.exportRoutesToCSVUS18(allShortestPaths);
    }
}
