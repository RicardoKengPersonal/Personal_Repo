package pt.ipp.isep.dei.domain.objects;

import java.io.Serializable;
import java.time.LocalDate;

import pt.ipp.isep.dei.domain.ClassType.LocomotiveType;

public class Locomotive implements Serializable {
    private final String name;
    private final LocomotiveType locomotiveType;
    private final LocalDate availableFrom;
    private final double power;
    private final double acceleration;
    private final double topSpeed;
    private final double fuelCost;
    private final double maintenanceCost;
    private final double acquisitionPrice;

    public Locomotive(String name, LocomotiveType locomotiveType, LocalDate availableFrom, double power,
            double acceleration, double topSpeed, double fuelCost, double maintenanceCost, double acquisitionPrice) {
        this.name = name;
        this.locomotiveType = locomotiveType;
        this.availableFrom = availableFrom;
        this.power = power;
        this.acceleration = acceleration;
        this.topSpeed = topSpeed;
        this.fuelCost = fuelCost;
        this.maintenanceCost = maintenanceCost;
        this.acquisitionPrice = acquisitionPrice;
    }

    public String getName() {
        return name;
    }

    public LocomotiveType getLocomotiveType() {
        return locomotiveType;
    }

    public LocalDate availableFrom() {
        return availableFrom;
    }

    public double getPower() {
        return power;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getTopSpeed() {
        return topSpeed;
    }

    public double getFuelCost() {
        return fuelCost;
    }

    public double getMaintenanceCost() {
        return maintenanceCost;
    }

    public double getAcquisitionPrice() {
        return acquisitionPrice;
    }

    public LocalDate getAvailableFrom() {
        return availableFrom;
    }

    @Override
    public String toString() {
        return String.format(
                "Locomotive: %s, Type: %s, Power: %.2f, Acceleration: %.2f, Top Speed: %.2f, Fuel Cost: %.2f, Maintenance Cost: %.2f, Acquisition Price: %.2f",
                name, locomotiveType, power, acceleration, topSpeed, fuelCost, maintenanceCost, acquisitionPrice);
    }
}
