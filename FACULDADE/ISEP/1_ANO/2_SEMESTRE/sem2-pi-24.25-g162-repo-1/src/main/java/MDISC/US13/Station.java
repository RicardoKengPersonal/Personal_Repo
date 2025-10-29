package MDISC.US13;

public class Station {
    private final String name;
    private final StationType type;
    private int id;

    public Station(String name, StationType type) {
        this.name = name;
        this.type = type;
    }

    public Station (String name){
        this.name=name;
        this.type = StationType.STATION;
    }


    public String getName() {
        return name;
    }

    public StationType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + "_" + name;
    }

    /**
     * Sets an ID for this station.
     * @param id The ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of this station.
     * @return The station ID
     */
    public int getId() {
        return id;
    }

}
