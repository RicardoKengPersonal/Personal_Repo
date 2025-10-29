package pt.ipp.isep.dei.domain.objects;

import java.io.Serializable;
import java.util.ArrayList;

import pt.ipp.isep.dei.domain.concept.Coordinates;

public class City implements Serializable {
    private final String name;
    private final Coordinates coordinates;
    private final ArrayList<HouseBlock> houseBlocks;
    private double generationFactor = 1.0d;

    public City(String name, Coordinates coordinates, ArrayList<HouseBlock> houseBlocks) {
        if (!isValidName(name)) {
            throw new IllegalArgumentException(
                    "Name must not be empty and must not have special characters or digits!");
        }
        this.name = name;
        this.coordinates = coordinates;
        this.houseBlocks = houseBlocks;
        this.generationFactor = generationFactor;
    }

    private boolean isValidName(String name) {
        return name != null && name.matches("[a-zA-Z\\s]+");
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ArrayList<HouseBlock> getHouseBlocks() {
        return houseBlocks;
    }

    public double getGenerationFactor() {
        return generationFactor;
    }
}
