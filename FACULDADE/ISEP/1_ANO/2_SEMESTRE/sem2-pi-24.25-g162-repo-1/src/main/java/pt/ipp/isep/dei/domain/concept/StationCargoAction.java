package pt.ipp.isep.dei.domain.concept;

import java.util.ArrayList;
import java.util.List;

public class StationCargoAction {
    private final List<Cargo> pickUp = new ArrayList<>();
    private final List<Cargo> dropOff = new ArrayList<>();

    public void addPickUp(Cargo cargo) {
        pickUp.add(cargo);
    }

    public void addDropOff(Cargo cargo) {
        dropOff.add(cargo);
    }

    public List<Cargo> getPickUp() {
        return pickUp;
    }

    public List<Cargo> getDropOff() {
        return dropOff;
    }
}
