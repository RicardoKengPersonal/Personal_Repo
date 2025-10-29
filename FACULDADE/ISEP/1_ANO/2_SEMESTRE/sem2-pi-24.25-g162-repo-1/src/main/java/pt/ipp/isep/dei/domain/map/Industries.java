package pt.ipp.isep.dei.domain.map;

import java.io.Serializable;
import java.util.ArrayList;

import pt.ipp.isep.dei.domain.ClassType.IndustryGenerationFactor;
import pt.ipp.isep.dei.domain.ClassType.IndustryType;
import pt.ipp.isep.dei.domain.ClassType.ResourceType;
import pt.ipp.isep.dei.domain.concept.Coordinates;
import pt.ipp.isep.dei.domain.objects.Industry;
import pt.ipp.isep.dei.domain.objects.PrimaryIndustry;
import pt.ipp.isep.dei.domain.objects.TransformativeIndustry;

public class Industries implements Serializable {
    private ArrayList<Industry> industries;

    public Industries() {
        this.industries = new ArrayList<>();
    }

    public void createIndustry(Coordinates coordinates, ResourceType outputResourceType, IndustryType industryType,
                               IndustryGenerationFactor industryGenerationFactor) {
        Industry industry;

        switch (industryType) {
            case Primary:
                industry = new PrimaryIndustry(coordinates, industryType, industryGenerationFactor, outputResourceType);
                break;
            case Transforming:
                ArrayList<ResourceType> inputResourceTypes = new ArrayList<>();
                switch (outputResourceType) {
                    case Steel:
                        inputResourceTypes.add(ResourceType.Coal);
                        inputResourceTypes.add(ResourceType.Iron);
                        industry = new TransformativeIndustry(coordinates, industryType, industryGenerationFactor,
                                inputResourceTypes, outputResourceType);
                        break;
                    case Bread:
                        inputResourceTypes.add(ResourceType.Cereals);
                        industry = new TransformativeIndustry(coordinates, industryType, industryGenerationFactor,
                                inputResourceTypes, outputResourceType);
                        break;
                    case Meat:
                        inputResourceTypes.add(ResourceType.Cattle);
                        industry = new TransformativeIndustry(coordinates, industryType, industryGenerationFactor,
                                inputResourceTypes, outputResourceType);
                        break;
                    case Textil:
                        inputResourceTypes.add(ResourceType.Wool);
                        industry = new TransformativeIndustry(coordinates, industryType, industryGenerationFactor,
                                inputResourceTypes, outputResourceType);
                        break;
                    case Automobile:
                        inputResourceTypes.add(ResourceType.Steel);
                        inputResourceTypes.add(ResourceType.Rubber);
                        industry = new TransformativeIndustry(coordinates, industryType, industryGenerationFactor,
                                inputResourceTypes, outputResourceType);
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported resource type: " + outputResourceType);
                }
                break;  // <-- This break was missing and is essential here!
            default:
                throw new IllegalArgumentException("Unsupported industry type: " + industryType);
        }
        industries.add(industry);
    }

    public ArrayList<Industry> getAllIndustries() {
        return new ArrayList<>(industries);
    }
}
