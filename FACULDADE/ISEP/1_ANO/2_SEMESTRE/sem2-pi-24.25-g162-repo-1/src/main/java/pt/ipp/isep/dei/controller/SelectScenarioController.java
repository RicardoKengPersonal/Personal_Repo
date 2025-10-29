package pt.ipp.isep.dei.controller;

import java.util.ArrayList;

import pt.ipp.isep.dei.domain.game.CurrentGame;
import pt.ipp.isep.dei.domain.map.Map;
import pt.ipp.isep.dei.domain.scenario.Scenario;

public class SelectScenarioController {

    public ArrayList<Scenario> getScenariosForMap(Map map) {
        return map.getScenarios();
    }

    public CurrentGame setCurrentGame(Map map, Scenario scenario) {
        return new CurrentGame(map, scenario);
    }
}
