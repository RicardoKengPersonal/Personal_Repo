package eapli.shodrone.showrequestmanagement.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class ShowRequestDuration {
    private int durationInMinutes;

    protected ShowRequestDuration() {
    }

    public ShowRequestDuration(final int durationInMinutes) {
        if (durationInMinutes <= 0) throw new IllegalArgumentException("Duration must be positive");
        this.durationInMinutes = durationInMinutes;
    }

    public int minutes() {
        return durationInMinutes;
    }

    @Override
    public String toString() {
        return durationInMinutes + " minutes";
    }
}
