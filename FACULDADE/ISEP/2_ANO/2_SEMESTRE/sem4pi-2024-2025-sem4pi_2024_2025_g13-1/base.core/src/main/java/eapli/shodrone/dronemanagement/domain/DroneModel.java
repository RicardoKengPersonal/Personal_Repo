package eapli.shodrone.dronemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import lombok.Getter;

import jakarta.persistence.*;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Entity
public class DroneModel implements AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private float xAxisTolerance;

    @Column(nullable = false)
    private float yAxisTolerance;

    @Column(nullable = false)
    private float zAxisTolerance;

    @Column(nullable = false,name = "ofday")
    private Calendar day;

    @Column(nullable = false)
    private float maxSpeed;

    @Column(nullable = false)
    private float maxRotationSpeed;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "drone_model_lighting_options", joinColumns = @JoinColumn(name = "drone_model_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "lighting_option")
    private Set<DroneType> lightingOptions;

    @Getter
    private boolean active;

    protected DroneModel() {};

    public DroneModel(String name, String manufacturer,
                      float xAxisTolerance, float yAxisTolerance, float zAxisTolerance,
                      final Calendar ofDay,
                      float maxSpeed, float maxRotationSpeed,
                      Set<DroneType> lightingOptions) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (manufacturer == null || manufacturer.trim().isEmpty()) {
            throw new IllegalArgumentException("Manufacturer cannot be null or empty");
        }
        if (xAxisTolerance < 0) {
            throw new IllegalArgumentException("X axis tolerance must be non-negative");
        }
        if (yAxisTolerance < 0) {
            throw new IllegalArgumentException("Y axis tolerance must be non-negative");
        }
        if (zAxisTolerance < 0) {
            throw new IllegalArgumentException("Z axis tolerance must be non-negative");
        }
        if (ofDay == null) {
            throw new IllegalArgumentException("Registration date cannot be null");
        }

        if (maxSpeed < 0) {
            throw new IllegalArgumentException("Max speed must be non-negative");
        }

        if (maxRotationSpeed < 0) {
            throw new IllegalArgumentException("Max rotation speed must be non-negative");
        }

        if (lightingOptions == null || lightingOptions.isEmpty()) {
            throw new IllegalArgumentException("At least one lighting option must be specified");
        }

        this.name = name.trim();
        this.manufacturer = manufacturer.trim();
        this.xAxisTolerance = xAxisTolerance;
        this.yAxisTolerance = yAxisTolerance;
        this.zAxisTolerance = zAxisTolerance;
        this.day = ofDay;
        this.active = true;
        this.maxSpeed = maxSpeed;
        this.maxRotationSpeed = maxRotationSpeed;
        this.lightingOptions = new HashSet<>(lightingOptions);

    }


    public String name(){return name;}
    public String manufacturer(){return manufacturer;}
    public float xAxisTolerance(){return xAxisTolerance;}
    public float yAxisTolerance(){return yAxisTolerance;}
    public float zAxisTolerance(){return zAxisTolerance;}
    public Calendar day(){return day;}
    public float maxSpeed(){return maxSpeed;}
    public float maxRotationSpeed(){return maxRotationSpeed;}

    public Set<DroneType> lightingOptions() {
        return lightingOptions;
    }


    @Override
    public boolean sameAs(Object other){
        if(!(other instanceof DroneModel)) return false;
        DroneModel that = (DroneModel) other;
        return this.name.equalsIgnoreCase(that.name);
    }

    @Override
    public Long identity() {return this.id;};

    @Override
    public int hashCode() {return name.toLowerCase().hashCode();}

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (!(o instanceof DroneModel)) return false;
        DroneModel that = (DroneModel) o;
        return name.equalsIgnoreCase(that.name);
    }

    @Override
    public String toString() {
        return "DroneModel{" +
                "id=" + id +
                ", manufacturer='" + manufacturer +
                ", name='" + name + '\'' +
                ", xAxisTolerance=" + xAxisTolerance +
                ", yAxisTolerance=" + yAxisTolerance +
                ", zAxisTolerance=" + zAxisTolerance +
                ", Date of registration in the system=" + day +
                ", active=" + active +
                ", maxSpeed=" + maxSpeed +
                ", maxRotationSpeed=" + maxRotationSpeed +
                ", lightingOptions=" + lightingOptions +
                '}';
    }
}
