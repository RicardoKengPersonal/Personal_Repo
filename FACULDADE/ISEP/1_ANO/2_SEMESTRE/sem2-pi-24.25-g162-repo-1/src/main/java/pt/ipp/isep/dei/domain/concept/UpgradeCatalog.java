package pt.ipp.isep.dei.domain.concept;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pt.ipp.isep.dei.domain.objects.BuildingUpgrade;

public class UpgradeCatalog {

    private final ArrayList<BuildingUpgrade> upgradesCatalog = new ArrayList<>();

    private static final String RESOURCE_PATH = "/data/stations/upgrades.svg";

    public UpgradeCatalog() throws IOException {
        loadAllUpgradesFromFile();
    }

    public void loadAllUpgradesFromFile() throws IOException {
        Map<String, String[]> rawData = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                UpgradeCatalog.class.getResourceAsStream(RESOURCE_PATH)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4)
                    continue;
                rawData.put(parts[0], parts);
            }
        }

        Map<String, BuildingUpgrade> upgradeMap = new HashMap<>();

        for (String[] parts : rawData.values()) {
            String name = parts[0];
            LocalDate availableFrom = LocalDate.parse(parts[1]);
            String replacesName = parts[2];
            double cost = Double.parseDouble(parts[3]);

            BuildingUpgrade replaces = replacesName.isBlank() ? null : upgradeMap.get(replacesName);
            BuildingUpgrade upgrade = new BuildingUpgrade(name, availableFrom, replaces, cost);
            upgradeMap.put(name, upgrade);
        }

        upgradesCatalog.clear();
        upgradesCatalog.addAll(upgradeMap.values());
    }

    public ArrayList<BuildingUpgrade> getUpgradesCatalog() {
        return new ArrayList<>(upgradesCatalog);
    }

    public BuildingUpgrade getBuildingUpgradeByName(String buildingUpgradeName) throws Exception {
        for (BuildingUpgrade buildingUpgrade : upgradesCatalog) {
            if (buildingUpgrade.getName().equals(buildingUpgradeName)) {
                return buildingUpgrade;
            }
        }
        throw new Exception("There is no upgrade with that name!");
    }
}
