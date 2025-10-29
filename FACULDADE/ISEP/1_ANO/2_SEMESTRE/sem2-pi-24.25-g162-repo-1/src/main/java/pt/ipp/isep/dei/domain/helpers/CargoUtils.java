package pt.ipp.isep.dei.domain.helpers;

import pt.ipp.isep.dei.domain.ClassType.CargoType;
import pt.ipp.isep.dei.domain.ClassType.ResourceType;

public class CargoUtils {
    public static CargoType mapResourceToCargoType(ResourceType resource) {
        return switch (resource) {
            case Coal -> CargoType.Coal;
            case Iron -> CargoType.Iron_Ore;
            case Steel -> CargoType.Steel;
            case Automobile -> CargoType.Cars;
            case Vegetables -> CargoType.Vegetables;
            case Cereals -> CargoType.Cereals;
            case Wool -> CargoType.Wool;
            case Coffe -> CargoType.Coffe;
            case Rubber -> CargoType.Rubber;
            case Cattle -> CargoType.Cattle;
            case Bread -> CargoType.Bread;
            case Meat -> CargoType.Meat;
            case Textil -> CargoType.Textil;
            default -> null;
        };
    }
}
