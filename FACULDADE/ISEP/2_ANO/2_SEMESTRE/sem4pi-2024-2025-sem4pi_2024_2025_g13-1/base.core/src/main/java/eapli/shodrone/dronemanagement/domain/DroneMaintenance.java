package eapli.shodrone.dronemanagement.domain;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class DroneMaintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "drone_id")
    private Drone drone;

    @ManyToOne(optional = false)
    @JoinColumn(name = "maintenance_type_id")
    private MaintenanceType maintenanceType;


    @Column(nullable = false)
    private LocalDate date;

    protected DroneMaintenance() {
        // For JPA
    }

    public DroneMaintenance(Drone drone, MaintenanceType maintenanceType, LocalDate date) {
        Preconditions.noneNull(drone, maintenanceType, date);
        this.drone = drone;
        this.maintenanceType = maintenanceType;
        this.date = date;
    }

    public Drone drone() {
        return drone;
    }

    public MaintenanceType maintenanceType() {
        return maintenanceType;
    }

    public LocalDate date() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DroneMaintenance)) return false;
        DroneMaintenance that = (DroneMaintenance) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return "DroneMaintenance{" +
                "id=" + id +
                ", drone=" + drone.id() +
                ", maintenanceType=" + maintenanceType.name() +
                ", date=" + date +
                '}';
    }

}
