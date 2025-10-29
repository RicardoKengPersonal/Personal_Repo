package pt.ipp.isep.dei.domain.concept;

import java.io.Serializable;

import pt.ipp.isep.dei.domain.ClassType.Building;
import pt.ipp.isep.dei.domain.ClassType.Ground;

public class Tile implements Serializable {
    private Ground ground;
    private Building building;
    private boolean hasTrain;

    public Tile(Ground ground, Building building) {
        this.ground = ground;
        this.building = building;
        this.hasTrain = false;
    }

    public Ground getGround() {
        return ground;
    }

    public Building getBuilding() {
        return building;
    }

    public void setGround(Ground ground) {
        this.ground = ground;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public boolean isTileAvailable() {
        if (building != Building.None) {
            return false;
        } else
            return true;
    }

    public boolean hasTrain() {
        return hasTrain;
    }

    public void setHasTrain(boolean hasTrain) {
        this.hasTrain = hasTrain;
    }

}
