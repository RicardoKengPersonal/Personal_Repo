package eapli.shodrone.dronemanagement.domain;

public class AvailableDroneModelDTO {
    public final String modelName;
    public final int availableCount;

    public AvailableDroneModelDTO(String modelName, int availableCount) {
        this.modelName = modelName;
        this.availableCount = availableCount;
    }
}
