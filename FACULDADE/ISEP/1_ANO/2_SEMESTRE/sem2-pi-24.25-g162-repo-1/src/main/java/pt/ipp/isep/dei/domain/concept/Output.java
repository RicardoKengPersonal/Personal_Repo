package pt.ipp.isep.dei.domain.concept;

import java.io.Serializable;

import pt.ipp.isep.dei.domain.ClassType.ResourceType;

public class Output implements Serializable {

    private final ResourceType resource;
    private double amount;

    public Output(ResourceType resource) {
        this.resource = resource;
    }

    public ResourceType getResourceType() {
        return resource;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
