package pt.ipp.isep.dei.domain.objects;

import java.io.Serializable;
import java.time.LocalDate;

public class BuildingUpgrade implements Serializable {
    private final String name;
    private final LocalDate availableFrom;
    private final BuildingUpgrade replaces;
    private final double cost;

    public BuildingUpgrade(String name, LocalDate availableFrom, BuildingUpgrade replaces, double cost) {
        this.name = name;
        this.availableFrom = availableFrom;
        this.replaces = replaces;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public LocalDate getAvailableFrom() {
        return availableFrom;
    }

    public BuildingUpgrade getReplaces() {
        return replaces;
    }

    public double getCost() {
        return cost;
    }

    public boolean isAvailable(LocalDate currentDate) {
        return !currentDate.isBefore(availableFrom);
    }

    @Override
    public String toString() {
        return String.format("Upgrade: %s Replaces: %s Cost: %s", name, replaces, cost);
    }
}
