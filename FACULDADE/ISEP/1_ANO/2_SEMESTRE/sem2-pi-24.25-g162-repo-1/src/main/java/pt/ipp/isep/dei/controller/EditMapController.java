package pt.ipp.isep.dei.controller;

import java.util.ArrayList;

import pt.ipp.isep.dei.domain.map.Map;
import pt.ipp.isep.dei.repository.MapRepository;
import pt.ipp.isep.dei.repository.Repositories;

public class EditMapController {

    private final MapRepository repo;

    public EditMapController() {
        this.repo = Repositories.getInstance().getMapRepository();
    }

    public ArrayList<Map> getMapsList() {
        return repo.getMapsList();
    }

    public void deleteMap(Map selectedMap) {
        repo.deleteMap(selectedMap);
    }
}
