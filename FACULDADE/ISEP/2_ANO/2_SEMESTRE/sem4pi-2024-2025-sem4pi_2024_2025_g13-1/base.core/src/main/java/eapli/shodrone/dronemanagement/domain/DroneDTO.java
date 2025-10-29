package eapli.shodrone.dronemanagement.domain;

public class DroneDTO {
    public final String id;
    public final String name;
    public final String model;

    public DroneDTO(final String id, final String name, final String model) {
        this.id = id;
        this.name = name;
        this.model = model;
    }

    @Override
    public String toString() {
        return String.format("Drone ID: %s | Name: %s | Model: %s", id, name, model);
    }
}
