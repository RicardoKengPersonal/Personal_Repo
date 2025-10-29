package pt.ipp.isep.dei.domain.objects;

import java.util.Collections;
import java.util.List;

import pt.ipp.isep.dei.domain.ClassType.IndustryGenerationFactor;
import pt.ipp.isep.dei.domain.ClassType.IndustryType;
import pt.ipp.isep.dei.domain.ClassType.ResourceType;
import pt.ipp.isep.dei.domain.concept.Coordinates;
import pt.ipp.isep.dei.domain.concept.Output;

public class PrimaryIndustry extends Industry {

    private Output output;

    public PrimaryIndustry(Coordinates coordinates, IndustryType industryType,
                           IndustryGenerationFactor industryGenerationFactor, ResourceType resourceType) {
        super(coordinates, industryType, industryGenerationFactor);
        this.output = new Output(resourceType);
    }

    public Output getOutput() {
        return output;
    }


    @Override
    public Output produce(double[] factors) {
        double amount = super.industryGenerationFactor.getGenerationFactor();
        for (double factor : factors) {
            amount *= factor;
        }
        output.setAmount(amount);
        return output;
    }

    @Override
    public boolean requiresInput() {
        return false;
    }

    @Override
    public ResourceType getOutputResourceType() {
        return output.getResourceType();
    }

    @Override
    public List<ResourceType> getInputResourceTypes() {
        return Collections.emptyList(); // No inputs
    }
}
