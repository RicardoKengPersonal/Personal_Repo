package pt.ipp.isep.dei.domain.map;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import pt.ipp.isep.dei.domain.ClassType.LocomotiveType;
import pt.ipp.isep.dei.domain.ClassType.ResourceType;
import pt.ipp.isep.dei.domain.scenario.HistoricalEvent;
import pt.ipp.isep.dei.domain.scenario.Scenario;

public class Scenarios implements Serializable {
    private ArrayList<Scenario> scenarios;

    public Scenarios() {
        this.scenarios = new ArrayList<>();
    }

    public void createScenario(String name, String description, ArrayList<HistoricalEvent> historicalEvents,
            ArrayList<LocomotiveType> locomotiveTypes,
            ArrayList<ResourceType> industryTypes, LocalDate startingDate,
            LocalDate endingDate, double initialMoney,
            String editorName) {
        scenarios.add(new Scenario(name, description, historicalEvents, locomotiveTypes, industryTypes,
                startingDate,
                endingDate, initialMoney, editorName,5,10));
    }

    public Scenario getScenarioByName(String name) {
        for (Scenario scenario : scenarios) {
            if (scenario.getName().equals(name)) {
                return scenario;
            }
        }
        return null;
    }

    public int size() {
        return scenarios.size();
    }

    public ArrayList<Scenario> getAllScenarios() {
        return new ArrayList<>(scenarios);
    }
}
