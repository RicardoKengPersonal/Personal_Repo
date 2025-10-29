package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.concept.Coordinates;
import pt.ipp.isep.dei.domain.map.Map;
import pt.ipp.isep.dei.domain.objects.HouseBlock;
import pt.ipp.isep.dei.repository.MapRepository;
import pt.ipp.isep.dei.repository.Repositories;

import java.util.ArrayList;

public class CreateCityController {

    private final MapRepository repo;

    public CreateCityController() {
        this.repo = Repositories.getInstance().getMapRepository();
    }

    public void createCityAuto(String trim, Coordinates coordinates, int numberOfBlocks, Map map) {
        map.createCityAuto(trim, coordinates, numberOfBlocks);
        repo.saveMap(map);
    }

    public void createCityManualy(String trim, Coordinates coordinates, ArrayList<HouseBlock> houseBlocks, Map map) {
        map.createCityManualy(trim, coordinates, houseBlocks);
        repo.saveMap(map);
    }
}
