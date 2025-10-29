package MDISC.US13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputReader {
    private final List<Station> stations = new ArrayList<>();
    private final List<Line> lines = new ArrayList<>();


    public void readCSVNoUnderscore(String filepath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length != 4) continue;

                Station stationA = new Station(parts[0].trim());
                Station stationB = new Station(parts[1].trim());
                boolean isElectrified = parts[2].trim().equals("1");
                double distance = Double.parseDouble(parts[3].trim());

                stationA = addStation(stationA);
                stationB = addStation(stationB);

                lines.add(new Line(stationA, stationB, isElectrified, distance));
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV: " + e.getMessage());
        }
    }

    public void readCSV(String filepath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length != 4) {
                    continue;
                }

                Station station1 = parseStation(parts[0].trim());
                Station station2 = parseStation(parts[1].trim());
                boolean isElectrified = parts[2].trim().equals("1");
                double distance = Double.parseDouble(parts[3].trim());

                station1 = addStation(station1);
                station2 = addStation(station2);

                lines.add(new Line(station1, station2, isElectrified, distance));
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV: " + e.getMessage());
        }
    }

    private Station parseStation(String str) {
        String[] parts = str.split("_", 2);
        StationType type = StationType.getType(parts[0]);
        return new Station(parts[1], type);
    }

    private Station addStation(Station station) {
        Station existing = findStationByNameAndType(station.getName(), station.getType());
        if (existing != null) {
            return existing;
        }

        stations.add(station);
        return station;
    }

    private Station findStationByNameAndType(String name, StationType type) {
        for (Station station : stations) {
            if (station.getName().equals(name) && station.getType() == type) {
                return station;
            }
        }
        return null;
    }

    public List<Station> getStations() {
        return stations;
    }

    public List<Line> getLines() {
        return lines;
    }

    public List<Station> getStations(List<StationType> types) {
        List<Station> result = new ArrayList<>();
        for (Station s : stations) {
            for (StationType type : types) {
                if (s.getType() == type) {
                    result.add(s);
                }
            }
        }
        return result;
    }

    public int[][] buildAdjacencyMatrix(List<Station> filter, boolean onlyElectrified) {
        List<Station> nodes = getAdjacencyStations(filter, onlyElectrified);

        int n = nodes.size();
        int[][] matrix = new int[n][n];

        for (Line line : lines) {
            Station a = line.getStationA();
            Station b = line.getStationB();

            // verifica se ambas as estações da ligação estão na lista de nós selecionados, e se a linha é eletrificada (caso onlyElectrified seja true)
            if (nodes.contains(a) && nodes.contains(b) && (!onlyElectrified || line.isElectrified())) {

                int i = nodes.indexOf(a);
                int j = nodes.indexOf(b);

                // ligação na matriz (grafo não-direcionado → simétrica)
                matrix[i][j] = 1;
                matrix[j][i] = 1;
            }
        }

        return matrix;
    }

    // mesma forma mas com as restrições de tipo de estação
    public int[][] buildAdjMatrixRestricted(boolean electrified, List<StationType> types) {
        List<Station> nodes = getAdjacencyStationsRestricted(electrified, types);

        int n = nodes.size();
        int[][] matrix = new int[n][n];
        for (Line conn : lines) {
            if (electrified && !conn.isElectrified()) continue;

            Station a = conn.getStationA();
            Station b = conn.getStationB();

            if (nodes.contains(a) && nodes.contains(b)) {
                int i = nodes.indexOf(a);
                int j = nodes.indexOf(b);
                matrix[i][j] = 1;
                matrix[j][i] = 1;
            }
        }

        return matrix;
    }

    public List<Station> getAdjacencyStationsRestricted(boolean electrified, List<StationType> types) {
        List<Station> all = getStations();
        List<Station> nodes = new ArrayList<>();

        // eletricals
        if (electrified) {
            for (Line line : lines) {
                if (line.isElectrified()) {
                    Station a = line.getStationA();
                    Station b = line.getStationB();

                    // adds electrified involved stations with no duplications
                    if (!nodes.contains(a)) {
                        nodes.add(a);
                    }
                    if (!nodes.contains(b)) {
                        nodes.add(b);
                    }
                }
            }

            // makes sure selected-type stations are included
            for (Station s : all) {
                if (types.contains(s.getType()) && !nodes.contains(s)) {
                    nodes.add(s);
                }
            }
        } else {
            nodes = all;
        }

        return nodes;
    }

    public List<Station> getAdjacencyStations(List<Station> stations, boolean onlyElectrified) {
        List<Station> nodes = new ArrayList<>(stations);

        if (onlyElectrified) {
            List<Station> electrifiedNodes = new ArrayList<>();

            for (Line conn : lines) {
                if (conn.isElectrified()) {
                    Station a = conn.getStationA();
                    Station b = conn.getStationB();

                    if (stations.contains(a) && stations.contains(b)) {
                        if (!electrifiedNodes.contains(a)) {
                            electrifiedNodes.add(a);
                        }
                        if (!electrifiedNodes.contains(b)) {
                            electrifiedNodes.add(b);
                        }
                    }
                }
            }

            nodes = electrifiedNodes;
        }
        return nodes;
    }

    public int getStationIndex(Station station, List<Station> stations) {
        return stations.indexOf(station);
    }
}
