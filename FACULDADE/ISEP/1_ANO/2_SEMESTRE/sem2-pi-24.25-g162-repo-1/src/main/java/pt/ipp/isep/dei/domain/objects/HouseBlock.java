package pt.ipp.isep.dei.domain.objects;

import java.io.Serializable;

import pt.ipp.isep.dei.domain.concept.Coordinates;

public class HouseBlock implements Serializable {

    private Coordinates position;

    public HouseBlock(Coordinates position) {
        this.position = position;
    }

    public Coordinates getCoordinates() {
        return position;
    }
}
