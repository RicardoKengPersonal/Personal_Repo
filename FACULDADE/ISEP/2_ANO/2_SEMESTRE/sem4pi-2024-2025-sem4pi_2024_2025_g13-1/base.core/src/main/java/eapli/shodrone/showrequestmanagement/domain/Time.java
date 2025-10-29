package eapli.shodrone.showrequestmanagement.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalTime;

@Embeddable
public class Time implements Serializable {

    @Column(name = "event_time")
    private String time; // Armazena o tempo como uma única string no formato HH:mm

    protected Time() {
        // for ORM
    }

    public Time(int hour, int minute) {
        if (hour < 0 || hour > 23 || minute < 0 || minute > 59)
            throw new IllegalArgumentException("Invalid time");
        this.time = String.format("%02d:%02d", hour, minute);
    }

    public static Time from(LocalTime localTime) {
        return new Time(localTime.getHour(), localTime.getMinute());
    }

    @Override
    public String toString() {
        return time;
    }

    // Getters, equals, hashCode...
}