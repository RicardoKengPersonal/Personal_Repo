package pt.ipp.isep.dei.domain.objects;

import java.io.Serializable;

import java.util.List;
import java.util.Objects;

import pt.ipp.isep.dei.domain.ClassType.CargoType;
import pt.ipp.isep.dei.domain.ClassType.IndustryGenerationFactor;
import pt.ipp.isep.dei.domain.ClassType.IndustryType;
import pt.ipp.isep.dei.domain.concept.Coordinates;
import pt.ipp.isep.dei.domain.concept.Output;
import pt.ipp.isep.dei.domain.ClassType.ResourceType;

public abstract class Industry implements Serializable {
    private final Coordinates coordinates;
    private final IndustryType industryType;
    protected final IndustryGenerationFactor industryGenerationFactor;

    public Industry(Coordinates coordinates, IndustryType industryType,
            IndustryGenerationFactor industryGenerationFactor) {
        this.coordinates = coordinates;
        this.industryType = industryType;
        this.industryGenerationFactor = industryGenerationFactor;
    }

    public abstract Output produce(double[] factors);

    public abstract boolean requiresInput();

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public IndustryType getIndustryType() {
        return industryType;
    }

    public IndustryGenerationFactor getIndustryGenerationFactor() {
        return industryGenerationFactor;
    }

    public abstract ResourceType getOutputResourceType();

    public abstract List<ResourceType> getInputResourceTypes();

    public List<CargoType> getDemandedCargoTypes() {
        if (!requiresInput()) return List.of(); // Primary industry: no input demand

        return getInputResourceTypes().stream()
                .map(resource -> switch (resource) {
                    case Coal -> CargoType.Coal;
                    case Iron -> CargoType.Iron_Ore;
                    case Steel -> CargoType.Steel;
                    case Automobile -> CargoType.Cars; // probably Automobile resource means Cars cargo
                    case Vegetables -> CargoType.Vegetables;
                    case Cereals -> CargoType.Cereals;
                    case Wool -> CargoType.Wool;
                    case Coffe -> CargoType.Coffe;
                    case Rubber -> CargoType.Rubber;
                    case Cattle -> CargoType.Cattle;
                    case Bread -> CargoType.Bread;
                    case Meat -> CargoType.Meat;
                    case Textil -> CargoType.Textil;
                    // Add default if any resource is unmapped or throw exception if you want strict check
                    default -> null;
                })
                .filter(Objects::nonNull)
                .toList();
    }



}
