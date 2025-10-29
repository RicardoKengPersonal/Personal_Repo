package pt.ipp.isep.dei.domain.concept;

import java.io.Serializable;
import java.util.ArrayList;

import pt.ipp.isep.dei.domain.ClassType.ResourceType;

public class Input implements Serializable {

    private final ArrayList<ResourceType> resource;
    private double[] amount;

    public Input(ArrayList<ResourceType> resource) {
        this.resource = resource;
    }

    public ArrayList<ResourceType> getResourceTypes() {
        return resource;
    }

    public double[] getAmount() {
        return amount;
    }

    public void setAmount(double[] amount) {
        this.amount = amount;
    }
}
