package eapli.shodrone.figuremanagement.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class FigureTotalDrone {
    private int total;

    protected FigureTotalDrone() {
        // for ORM
    }

    public FigureTotalDrone(int total) {
        if (total <= 0) throw new IllegalArgumentException("Total number of drones must be greater than zero");
        this.total = total;
    }

    public int total() {
        return total;
    }

    @Override
    public String toString() {
        return String.valueOf(total);
    }
}
