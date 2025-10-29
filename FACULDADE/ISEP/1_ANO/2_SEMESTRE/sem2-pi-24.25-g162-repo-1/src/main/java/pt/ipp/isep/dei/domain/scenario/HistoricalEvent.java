package pt.ipp.isep.dei.domain.scenario;

import java.io.Serializable;

public class HistoricalEvent implements Serializable {
    private String name;

    public HistoricalEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "HistoricalEvent{" +
                "name='" + name + '\'' +
                '}';
    }
}
