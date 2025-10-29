package pt.ipp.isep.dei.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import pt.ipp.isep.dei.domain.ClassType.LocomotiveType;
import pt.ipp.isep.dei.domain.ClassType.ResourceType;
import pt.ipp.isep.dei.domain.map.Map;
import pt.ipp.isep.dei.domain.scenario.HistoricalEvent;
import pt.ipp.isep.dei.repository.MapRepository;
import pt.ipp.isep.dei.repository.Repositories;
import pt.isep.lei.esoft.auth.UserSession;

public class CreateScenarioController {

    private final UserSession userSession;
    private final MapRepository repo;

    public CreateScenarioController() {
        this.userSession = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession();
        this.repo = Repositories.getInstance().getMapRepository();
    }

    public void createScenario(Map map, String name, String description,
            ArrayList<HistoricalEvent> historicalEvents,
            ArrayList<LocomotiveType> locomotiveTypes,
            ArrayList<ResourceType> industryTypes, LocalDate startingDate,
            LocalDate endingDate, double initialMoney,
            String editorName) {
        map.createScenario(name, description, historicalEvents, locomotiveTypes, industryTypes,
                startingDate,
                endingDate, initialMoney, editorName);
        repo.saveMap(map);
    }

    public ArrayList<ResourceType> getIndustryTypes() {
        return new ArrayList<>(Arrays.asList(ResourceType.values()));
    }

    public ArrayList<LocomotiveType> getLocomotiveTypes() {
        return new ArrayList<>(Arrays.asList(LocomotiveType.values()));
    }

    public String getuserName() {
        return userSession.getUserName();
    }
}
