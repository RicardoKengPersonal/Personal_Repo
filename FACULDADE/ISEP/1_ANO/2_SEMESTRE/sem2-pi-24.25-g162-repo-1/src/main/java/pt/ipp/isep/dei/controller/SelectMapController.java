package pt.ipp.isep.dei.controller;

import java.util.ArrayList;

import pt.ipp.isep.dei.domain.game.CurrentGame;
import pt.ipp.isep.dei.domain.map.Map;
import pt.ipp.isep.dei.domain.scenario.Scenario;
import pt.ipp.isep.dei.repository.MapRepository;
import pt.ipp.isep.dei.repository.Repositories;

public class SelectMapController {

    private final MapRepository repo;

    public SelectMapController() {
        this.repo = Repositories.getInstance().getMapRepository();
    }

    public ArrayList<Map> getMapsList() {
        return repo.getMapsList();
    }

    public ArrayList<Scenario> getScenariosForMap(Map map) {
        return map.getScenarios();
    }

    public CurrentGame setCurrentGame(Map map, Scenario scenario) {
        return new CurrentGame(map, scenario);
    }
}
