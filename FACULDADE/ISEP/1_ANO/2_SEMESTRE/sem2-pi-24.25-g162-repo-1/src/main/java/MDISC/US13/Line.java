package MDISC.US13;

public class Line {
    private final Station stationA;
    private final Station stationB;
    private final boolean isElectrified;
    private final double distance;

    public Line(Station stationA, Station stationB, boolean isElectrified, double distance) {
        this.stationA = stationA;
        this.stationB = stationB;
        this.isElectrified = isElectrified;
        this.distance = distance;
    }

    public Station getStationA() {
        return stationA;
    }

    public Station getStationB() {
        return stationB;
    }

    public boolean isElectrified() {
        return isElectrified;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return stationA + " <--> " + stationB + " | Electrified: " + isElectrified + ", Distance: " + distance;
    }
}

