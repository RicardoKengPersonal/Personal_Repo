package pt.ipp.isep.dei.domain.concept;

import pt.ipp.isep.dei.domain.ClassType.CargoType;
import pt.ipp.isep.dei.domain.objects.Station;

public class Cargo {

    private double quantity;
    private Station origin;
    private Station destination;
    private CargoType cargoType;

    public Cargo(double quantity, Station origin, Station destination, CargoType cargoType) {

        this.quantity = quantity;
        this.origin = origin;
        this.destination = destination;
        this.cargoType = cargoType;

    }

    public double getQuantity() {

        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;  // overwrite instead of add
    }

    public void addQuantity(double amount) {
        this.quantity += amount;  // add to quantity
    }



    public Station getOrigin() {
        return origin;
    }

    public Station getDestination() {
        return destination;
    }

    public CargoType getCargoType() {
        return cargoType;
    }

    public void setOrigin(Station origin) {
        this.origin = origin;
    }

    public void setDestination(Station destination) {
        this.destination = destination;
    }

    public void setCargoType(CargoType cargoType) {
        this.cargoType = cargoType;
    }

    public String toString() {
        return "{ quantity=" + quantity +
                ", type=" + cargoType +
                '}';
    }

}
