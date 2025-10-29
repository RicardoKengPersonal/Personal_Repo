package eapli.shodrone.showrequestmanagement.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class Date implements Serializable {

    @Column(name = "date_value", nullable = false)
    private LocalDate value;

    protected Date() {
        // for ORM
    }

    public Date(LocalDate date) {
        if (date == null) throw new IllegalArgumentException("Date cannot be null");
        if (date.isBefore(LocalDate.now())) throw new IllegalArgumentException("Date cannot be in the past");
        this.value = date;
    }

    public static Date from(LocalDate date) {
        return new Date(date);
    }

    public static Date from(LocalDateTime dateTime) {
        return new Date(dateTime.toLocalDate());
    }

    public LocalDate value() {
        return value;
    }

    @Override
    public String toString() {
        return value.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date)) return false;
        Date that = (Date) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
