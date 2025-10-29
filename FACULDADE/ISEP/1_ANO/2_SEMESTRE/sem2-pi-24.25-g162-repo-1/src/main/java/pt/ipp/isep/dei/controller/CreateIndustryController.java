package pt.ipp.isep.dei.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pt.ipp.isep.dei.domain.ClassType.IndustryGenerationFactor;
import pt.ipp.isep.dei.domain.ClassType.IndustryType;
import pt.ipp.isep.dei.domain.ClassType.ResourceType;
import pt.ipp.isep.dei.domain.concept.Coordinates;
import pt.ipp.isep.dei.domain.map.Map;
import pt.ipp.isep.dei.repository.MapRepository;
import pt.ipp.isep.dei.repository.Repositories;

public class CreateIndustryController {

    private final MapRepository repo;

    public CreateIndustryController() {
        this.repo = Repositories.getInstance().getMapRepository();
    }

    public void createIndustry(Coordinates coordinates, ResourceType output, IndustryType industryType,
            IndustryGenerationFactor industryGenerationFactor, Map map) {
        map.createIndustry(coordinates, output, industryType, industryGenerationFactor);
        repo.saveMap(map);
    }

    public ArrayList<ResourceType> getResourceTypes() {
        return new ArrayList<>(Arrays.asList(ResourceType.values()));
    }

    public ArrayList<IndustryType> getIndustryTypes() {
        return new ArrayList<>(Arrays.asList(IndustryType.values()));
    }

    public ArrayList<IndustryGenerationFactor> getIndustryGenerationFactors() {
        return new ArrayList<>(Arrays.asList(IndustryGenerationFactor.values()));
    }

    public List<ResourceType> getPrimaryResourceTypes() {
        ArrayList<ResourceType> primary = new ArrayList<>();
        for (ResourceType resource : ResourceType.values()) {
            if (resource.getFlag()) {
                primary.add(resource);
            }
        }
        return primary;
    }

    public List<ResourceType> getTransformativeResourceTypes() {
        ArrayList<ResourceType> transforming = new ArrayList<>();
        for (ResourceType resource : ResourceType.values()) {
            if (!resource.getFlag()) {
                transforming.add(resource);
            }
        }
        return transforming;
    }
}
