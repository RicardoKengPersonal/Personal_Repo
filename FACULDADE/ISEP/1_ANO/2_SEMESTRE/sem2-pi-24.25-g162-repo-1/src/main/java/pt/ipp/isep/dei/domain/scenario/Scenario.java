package pt.ipp.isep.dei.domain.scenario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import pt.ipp.isep.dei.domain.ClassType.LocomotiveType;
import pt.ipp.isep.dei.domain.ClassType.ResourceType;
import pt.ipp.isep.dei.domain.objects.Locomotive;

public class Scenario implements Serializable {
    private final String name;
    private final String description;
    private final ArrayList<HistoricalEvent> historicalEvents;
    private final ArrayList<LocomotiveType> locomotiveTypes;
    private final ArrayList<ResourceType> industryTypes;
    private final LocalDate startingDate;
    private final LocalDate endingDate;
    private final double initialMoney;
    private final String editor;
    private int cargoBaseAmount ;
    private int passengerBaseAmount ;

    public Scenario(String name, String description, ArrayList<HistoricalEvent> historicalEvents,
            ArrayList<LocomotiveType> locomotiveTypes,
            ArrayList<ResourceType> industryTypes, LocalDate startingDate,
            LocalDate endingDate, double initialMoney,
            String editorName,int cargoBaseAmount,int passengerBaseAmount) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Scenatio name must not be empty!");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Scenatio name must not be empty!");
        }

        if (locomotiveTypes == null || locomotiveTypes.isEmpty()) {
            throw new IllegalArgumentException("There must be at least one locomotive type available!");
        }

        if (locomotiveTypes == null || locomotiveTypes.isEmpty()) {
            throw new IllegalArgumentException("There must be at least one locomotive type available!");
        }

        if (industryTypes == null || industryTypes.isEmpty()) {
            throw new IllegalArgumentException("There must be at least one industry type available!");
        }

        if (startingDate == null) {
            throw new IllegalArgumentException("There must be a starting date!");
        }

        if (endingDate == null) {
            throw new IllegalArgumentException("There must be a ending date!");
        }

        if (initialMoney <= 0) {
            throw new IllegalArgumentException("The initial money must not be negative or 0!");
        }

        if (editorName == null || editorName.isBlank()) {
            this.editor = "not disclosed";
        } else {
            this.editor = editorName;
        }

        this.name = name;
        this.description = description;
        this.historicalEvents = historicalEvents;
        this.locomotiveTypes = locomotiveTypes;
        this.industryTypes = industryTypes;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.initialMoney = initialMoney;
        this.cargoBaseAmount = 5;
        this.passengerBaseAmount = 10;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<HistoricalEvent> getHistoricalEvents() {
        return historicalEvents;
    }

    public ArrayList<LocomotiveType> getLocomotiveTypes() {
        return locomotiveTypes;
    }

    public ArrayList<ResourceType> getIndustryTypes() {
        return industryTypes;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public double getInitialMoney() {
        return initialMoney;
    }

    public String getEditor() {
        return editor;
    }

    public int getCargoBaseAmount() {
        return cargoBaseAmount;
    }

    public int getPassengerBaseAmount() {
        return passengerBaseAmount;
    }

    @Override
    public String toString() {
        return String.format(
                "Scenario: %s\nDescription: %s\nStart Date: %s\nEnd Date: %s\nInitial Money: %.2f\nEditor: %s",
                name,
                description,
                startingDate,
                endingDate,
                initialMoney,
                editor);
    }
}