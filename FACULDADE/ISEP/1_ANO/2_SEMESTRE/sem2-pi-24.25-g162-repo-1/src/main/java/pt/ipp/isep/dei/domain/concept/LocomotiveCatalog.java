package pt.ipp.isep.dei.domain.concept;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import pt.ipp.isep.dei.domain.ClassType.LocomotiveType;
import pt.ipp.isep.dei.domain.objects.Locomotive;

public class LocomotiveCatalog implements Serializable {

    private final ArrayList<Locomotive> locomotiveCatalog = new ArrayList<>();

    private static final String RESOURCE_PATH = "/data/locomotives/locomotives.svg";

    public LocomotiveCatalog() throws IOException {
        loadAllLocomotivesFromFile();
    }

    public void loadAllLocomotivesFromFile() throws IOException {
        ArrayList<Locomotive> loadedLocomotives = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                LocomotiveCatalog.class.getResourceAsStream(RESOURCE_PATH)))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length < 9) {
                    System.err.println("Skipping invalid line: " + line);
                    continue;
                }

                try {
                    String name = parts[0].trim();
                    LocomotiveType type = LocomotiveType.valueOf(parts[1].trim());
                    LocalDate availableFrom = LocalDate.parse(parts[2].trim());
                    double power = Double.parseDouble(parts[3].trim());
                    double acceleration = Double.parseDouble(parts[4].trim());
                    double topSpeed = Double.parseDouble(parts[5].trim());
                    double fuelCost = Double.parseDouble(parts[6].trim());
                    double maintenanceCost = Double.parseDouble(parts[7].trim());
                    double acquisitionPrice = Double.parseDouble(parts[8].trim());

                    Locomotive locomotive = new Locomotive(
                            name, type, availableFrom, power, acceleration,
                            topSpeed, fuelCost, maintenanceCost, acquisitionPrice);

                    loadedLocomotives.add(locomotive);

                } catch (Exception e) {
                    System.err.println("Error parsing line: " + line);
                    e.printStackTrace();
                }
            }
        }

        this.locomotiveCatalog.clear();
        this.locomotiveCatalog.addAll(loadedLocomotives);
    }

    public ArrayList<Locomotive> getLocomotiveCatalog() {
        return new ArrayList<>(locomotiveCatalog);
    }

    public Locomotive getLocomotiveByName(String locomotiveName) throws Exception {
        for (Locomotive locomotive : locomotiveCatalog) {
            if (locomotive.getName().equals(locomotiveName)) {
                return locomotive;
            }
        }
        throw new Exception("There is no locomotive with that name!");
    }
}
