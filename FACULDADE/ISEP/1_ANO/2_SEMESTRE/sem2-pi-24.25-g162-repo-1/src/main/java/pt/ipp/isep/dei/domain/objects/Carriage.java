package pt.ipp.isep.dei.domain.objects;

import java.io.Serializable;

import pt.ipp.isep.dei.domain.ClassType.CarriageType;
import pt.ipp.isep.dei.domain.concept.Cargo;

public class Carriage implements Serializable {

    private final CarriageType carriageType;
    private final double capacity;
    private Cargo cargo;

    public Carriage(CarriageType carriageType, double capacity) {
        this.carriageType = carriageType;
        this.capacity = capacity;
    }

    public CarriageType getCarriageType() {
        return carriageType;
    }

    public double getCapacity() {
        return capacity;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
