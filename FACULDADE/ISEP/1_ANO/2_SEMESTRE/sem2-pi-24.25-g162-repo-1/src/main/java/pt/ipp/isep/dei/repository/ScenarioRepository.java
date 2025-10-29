package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.scenario.Scenario;

import java.util.ArrayList;
import java.util.List;

public class ScenarioRepository {
    private final List<Scenario> scenarios;

    public ScenarioRepository() {
        this.scenarios = new ArrayList<>();
    }

    public void add(Scenario scenario) {
        scenarios.add(scenario);
    }

    public List<Scenario> getScenarioList() {
        return List.copyOf(scenarios);
    }

    public Scenario getScenarioByName(String name) {
        for (Scenario scenario : scenarios) {
            if (scenario.getName().equals(name)) {
                return scenario;
            }
        }
        return null;
    }
}
