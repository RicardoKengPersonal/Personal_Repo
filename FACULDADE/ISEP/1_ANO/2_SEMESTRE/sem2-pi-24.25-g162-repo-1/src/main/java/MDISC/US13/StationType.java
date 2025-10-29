package MDISC.US13;

public enum StationType {
    STATION, DEPOT, TERMINAL;

    public static StationType getType(String prefix) {
        return switch (prefix) {
            case "S" -> STATION;
            case "D" -> DEPOT;
            case "T" -> TERMINAL;
            default -> throw new IllegalArgumentException("Invalid Station Type: " + prefix);
        };
    }
}

