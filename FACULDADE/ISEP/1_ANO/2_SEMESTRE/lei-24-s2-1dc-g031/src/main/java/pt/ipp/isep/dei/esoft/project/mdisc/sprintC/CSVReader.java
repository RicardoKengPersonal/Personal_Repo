package pt.ipp.isep.dei.esoft.project.mdisc.sprintC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static List<Point> readPoints(String filename) throws IOException {
        List<Point> points = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = br.readLine();
        if (line != null) {
            String[] pointNames = line.split(";");
            for (String name : pointNames) {
                points.add(new Point(name.trim()));
            }
        }
        br.close();
        return points;
    }

    public static double[][] readCostMatrix(String filename) throws IOException {
        List<double[]> rows = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(";");
            double[] row = new double[values.length];
            for (int i = 0; i < values.length; i++) {
                row[i] = Double.parseDouble(values[i].trim());
            }
            rows.add(row);
        }
        br.close();

        double[][] matrix = new double[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            matrix[i] = rows.get(i);
        }
        return matrix;
    }

    public static List<Route> createRoutes(List<Point> points, double[][] costMatrix) {
        List<Route> routes = new ArrayList<>();
        for (int i = 0; i < costMatrix.length; i++) {
            for (int j = 0; j < costMatrix[i].length; j++) {
                if (i != j && costMatrix[i][j] != 0) {
                    routes.add(new Route(points.get(i), points.get(j), costMatrix[i][j]));
                }
            }
        }
        return routes;
    }
}