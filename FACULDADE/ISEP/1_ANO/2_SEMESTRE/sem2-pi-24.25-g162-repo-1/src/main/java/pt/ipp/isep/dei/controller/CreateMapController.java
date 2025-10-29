package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.repository.MapRepository;
import pt.ipp.isep.dei.repository.Repositories;

public class CreateMapController {

    private final MapRepository mapRepository;

    public CreateMapController() {
        this.mapRepository = Repositories.getInstance().getMapRepository();
    }

    public void createMap(String name, String description, double scale, int width, int height) {
        mapRepository.add(name, description, scale, width, height);
    }
}
