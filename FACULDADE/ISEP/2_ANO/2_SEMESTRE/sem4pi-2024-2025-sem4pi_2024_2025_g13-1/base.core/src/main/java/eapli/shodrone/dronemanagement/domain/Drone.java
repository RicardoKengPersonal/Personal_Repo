package eapli.shodrone.dronemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;
import eapli.shodrone.dronemanagement.dto.DroneLanguagesDTO;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Drone implements AggregateRoot<DroneID>, DTOable<DroneLanguagesDTO> {

    @EmbeddedId
    private DroneID id;

    @Column(nullable = true)
    private String programmingLanguage;

    @Column(nullable = false)
    private String droneName;

    @ManyToOne(optional = false)
    private DroneModel model;

    @Column(nullable = false)
    private LocalDate acquisitionDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DroneStatus status;

    @Column
    private String removalReason;

    @Column
    private LocalDate removalDate;

    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<DroneMaintenance> maintenanceRecords = new ArrayList<>();


    protected Drone() {
        // For ORM
    }

    public Drone(final DroneID id,final String programmingLanguage,final String droneName, final DroneModel model, final LocalDate acquisitionDate) {
        Preconditions.noneNull(id,droneName, model, acquisitionDate);
        this.id = id;
        this.programmingLanguage = programmingLanguage;
        this.droneName = droneName;
        this.model = model;
        this.acquisitionDate = acquisitionDate;
        this.status = DroneStatus.ACTIVE;
    }

    public void removeFromInventory(String reason, LocalDate date, DroneStatus newStatus) {
        if (newStatus == DroneStatus.ACTIVE) {
            throw new IllegalArgumentException("Cannot set status to ACTIVE on removal.");
        }

        this.status = newStatus;
        this.removalReason = reason;
        this.removalDate = date;
    }

    public void addMaintenanceRecord(DroneMaintenance record) {
        Preconditions.ensure(record != null && record.drone().equals(this));
        if (this.status == DroneStatus.DECOMMISSIONED) {
            throw new IllegalStateException("Cannot add maintenance to a decommissioned drone.");
        }
        this.maintenanceRecords.add(record);
    }

    public DroneID id() {
        return id;
    }

    public String programmingLanguage() {
        return programmingLanguage;
    }

    public String droneName() {
        return droneName;
    }

    public DroneModel model() {
        return model;
    }

    public LocalDate acquisitionDate() {
        return acquisitionDate;
    }

    public DroneStatus status() {
        return status;
    }

    public void markAsInRepair() {
        this.status = DroneStatus.IN_REPAIR;
    }

    public void markAsDecommissioned() {
        this.status = DroneStatus.DECOMMISSIONED;
    }

    public void markAsActive() {
        this.status = DroneStatus.ACTIVE;
    }

    public String removalReason() { return removalReason; }

    public LocalDate removalDate() { return removalDate; }

    public List<DroneMaintenance> maintenanceRecords() {
        return List.copyOf(maintenanceRecords);
    }

    public void update(String newProgrammingLanguage) {

        if (newProgrammingLanguage == null || newProgrammingLanguage.trim().isEmpty()) {
            throw new IllegalArgumentException("Programming Language must not be null or empty.");
        }

        this.programmingLanguage = newProgrammingLanguage.trim();
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Drone)) return false;
        final Drone that = (Drone) other;
        return this.id.equals(that.id);
    }

    @Override
    public DroneID identity() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Drone)) return false;
        Drone drone = (Drone) o;
        return id.equals(drone.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Drone{" +
                "id=" + id +
                ", programmingLanguage='" + programmingLanguage +
                ", droneName='" + droneName +
                ", model=" + model.name() +
                ", acquisitionDate=" + acquisitionDate +
                ", status=" + status +
                '}';
    }

    @Override
    public DroneLanguagesDTO toDTO() {
        return new DroneLanguagesDTO(
                this.id.toString(),
                this.programmingLanguage
        );
    }
}
