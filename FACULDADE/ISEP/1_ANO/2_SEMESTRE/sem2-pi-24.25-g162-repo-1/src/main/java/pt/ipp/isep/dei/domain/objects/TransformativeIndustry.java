package pt.ipp.isep.dei.domain.objects;

import java.util.ArrayList;
import java.util.List;

import pt.ipp.isep.dei.domain.ClassType.IndustryGenerationFactor;
import pt.ipp.isep.dei.domain.ClassType.IndustryType;
import pt.ipp.isep.dei.domain.ClassType.ResourceType;
import pt.ipp.isep.dei.domain.concept.Coordinates;
import pt.ipp.isep.dei.domain.concept.Input;
import pt.ipp.isep.dei.domain.concept.Output;

public class TransformativeIndustry extends Industry {

    private final Input input;
    private final Output output;

    public TransformativeIndustry(Coordinates coordinates, IndustryType type,
                                  IndustryGenerationFactor industryGenerationFactor,
                                  ArrayList<ResourceType> input,
                                  ResourceType output) {
        super(coordinates, type, industryGenerationFactor);
        this.input = new Input(input);
        this.output = new Output(output);
    }

    @Override
    public boolean requiresInput() {
        return true;
    }

    public Input getInput() {
        return input;
    }

    public Output getOutput() {
        return output;
    }

    @Override
    public Output produce(double[] factors) {
        double amount = industryGenerationFactor.getGenerationFactor();
        for (double factor : factors) {
            amount *= factor;
        }
        output.setAmount(amount);
        return output;
    }

    public Input consume(double[] factors) {
        double amount = industryGenerationFactor.getGenerationFactor();
        for (double factor : factors) {
            amount *= factor;
        }
        output.setAmount(amount);  // Possibly unnecessary here, but kept for symmetry
        return input;
    }

    @Override
    public ResourceType getOutputResourceType() {
        return output.getResourceType();
    }

    @Override
    public List<ResourceType> getInputResourceTypes() {
        return input.getResourceTypes();
    }
}
