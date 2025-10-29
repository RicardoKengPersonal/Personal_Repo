package eapli.shodrone.dronemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Embeddable
@Getter
@EqualsAndHashCode
public class DroneID implements ValueObject, Comparable<DroneID> {

    @Column(nullable = false, unique = true)
    private String serialNumber;

    // Constructor to generate serial number automatically using UUID
    public DroneID() {
    }

    // Constructor to set a serial number manually
    public DroneID(String serialNumber) {
        Preconditions.nonEmpty(serialNumber, "Drone serial number must not be null or empty.");
        this.serialNumber = serialNumber.trim();
    }

    public String serialNumber() {
        return this.serialNumber;
    }


    @Override
    public String toString() {
        return serialNumber;
    }

    @Override
    public int compareTo(DroneID other) {
        return this.serialNumber.compareTo(other.serialNumber);
    }
}
