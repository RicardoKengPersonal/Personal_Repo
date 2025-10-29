package pt.ipp.isep.dei.esoft.project.mdisc.sprintC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExternalCSV {

    private final String CSVFilepath;
    private final String PointsFilePath;

    private List<Route> allRoutes = new ArrayList<>();
    private List<Point> allPoints = new ArrayList<>();

    public ExternalCSV(String csvFilepath, String pointsFilePath) {
        CSVFilepath = csvFilepath;
        PointsFilePath = pointsFilePath;
    }

    public void readRoutesFromMatrixAndNames() throws IOException {
        List<Point> points = readPoints(this.PointsFilePath);
        double[][] costMatrix = readCostMatrix(this.CSVFilepath, points.size());

        List<Route> routes = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            for (int j = 0; j < points.size(); j++) {
                if (i != j && costMatrix[i][j] != 0) { // Ignorando auto-loops e rotas de custo zero
                    Point startPoint = points.get(i);
                    Point endPoint = points.get(j);
                    double distance = costMatrix[i][j];
                    Route route = new Route(startPoint, endPoint, distance);
                    routes.add(route);
                }
            }
        }
        this.allRoutes = routes;
    }

    public List<Route> getAllRoutes() {
        return allRoutes;
    }

    private List<Point> readPoints(String filePath) throws IOException {
        List<Point> points = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            if (line != null) {
                String[] pointNames = line.split(";");
                for (String pointName : pointNames) {
                    if (pointName.trim().toUpperCase().startsWith("AP")) {
                        MainUS17.index.add(points.size());
                        MainUS18.index.add(points.size());
                    }
                    points.add(new Point(pointName));
                }
            }
        }
        this.allPoints = points;
        return points;
    }

    private double[][] readCostMatrix(String filePath, int size) throws IOException {
        double[][] costMatrix = new double[size][size];
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int row = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] costs = line.split(";");
                if (row == 0 && costs[0].charAt(0) == '\uFEFF') {
                    costs[0] = costs[0].substring(1);
                }
                for (int col = 0; col < costs.length; col++) {
                    costMatrix[row][col] = Double.parseDouble(costs[col]);
                }
                row++;
            }
        }
        return costMatrix;
    }

    public static void exportRoutesToCSV(List<List<Route>> allShortestPaths) {
        try (FileWriter writer = new FileWriter("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/sprintC/files/output/outCSVRoutes.csv")) {
            writer.append("Path").append(";").append("Cost").append("\n");

            for (List<Route> shortestPath : allShortestPaths) {
                if (!shortestPath.isEmpty()) {
                    double totalCost = shortestPath.stream().mapToDouble(Route::getCost).sum();
                    StringBuilder pathBuilder = new StringBuilder();
                    pathBuilder.append("(");

                    // Adicionar o ponto de partida do primeiro segmento
                    pathBuilder.append(shortestPath.get(0).getOrigin().getId());

                    // Adicionar os pontos finais dos segmentos subsequentes
                    for (Route route : shortestPath) {
                        pathBuilder.append(", ").append(route.getDestination().getId());
                    }

                    pathBuilder.append(");").append(totalCost);
                    writer.append(pathBuilder.toString()).append("\n");
                }
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportRoutesToCSVUS18(List<List<Route>> allShortestPaths) {
        try (FileWriter writer = new FileWriter("src/main/java/pt/ipp/isep/dei/esoft/project/mdisc/sprintC/files/output/outCSVRoutesUS18.csv")) {
            writer.append("Path").append(";").append("Cost").append("\n");

            for (List<Route> shortestPath : allShortestPaths) {
                if (!shortestPath.isEmpty()) {
                    double totalCost = shortestPath.stream().mapToDouble(Route::getCost).sum();
                    StringBuilder pathBuilder = new StringBuilder();
                    pathBuilder.append("(");

                    // Adicionar o ponto de partida do primeiro segmento
                    pathBuilder.append(shortestPath.get(0).getOrigin().getId());

                    // Adicionar os pontos finais dos segmentos subsequentes
                    for (Route route : shortestPath) {
                        pathBuilder.append(", ").append(route.getDestination().getId());
                    }

                    pathBuilder.append(");").append(totalCost);
                    writer.append(pathBuilder.toString()).append("\n");
                }
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Point> getAllPoints() {
        return allPoints;
    }
}
