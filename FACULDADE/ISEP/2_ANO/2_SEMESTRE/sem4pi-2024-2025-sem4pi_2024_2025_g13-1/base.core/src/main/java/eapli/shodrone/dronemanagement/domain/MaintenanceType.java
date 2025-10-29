package eapli.shodrone.dronemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.representations.dto.DTOable;
import eapli.shodrone.dronemanagement.dto.MaintenanceTypeDTO;
import jakarta.persistence.*;
import lombok.Getter;


@Entity
public class MaintenanceType implements AggregateRoot<Long>, DTOable<MaintenanceTypeDTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Getter
    private boolean active;

    public MaintenanceType(final String name, final String description) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name must not be null or empty.");
        if (description == null || description.trim().isEmpty())
            throw new IllegalArgumentException("Description must not be null or empty.");

        this.name = name.trim();
        this.description = description.trim();
        this.active = true;
    }

    public void update(String newName, String newDescription, boolean isInUse) {
        if (isInUse) {
            throw new IllegalStateException("Cannot update MaintenanceType '" + name + "' because it is already in use.");
        }

        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty.");
        }

        if (newDescription == null || newDescription.trim().isEmpty()) {
            throw new IllegalArgumentException("Description must not be null or empty.");
        }

        this.name = newName.trim();
        this.description = newDescription.trim();
    }


    public MaintenanceType() {
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    @Override
    public boolean sameAs(Object other){
        if(!(other instanceof MaintenanceType)) return false;
        MaintenanceType that = (MaintenanceType) other;
        return this.name.equalsIgnoreCase(that.name);
    }

    @Override
    public Long identity() {return this.id;};

    @Override
    public int hashCode() {return name.toLowerCase().hashCode();}

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (!(o instanceof MaintenanceType)) return false;
        MaintenanceType that = (MaintenanceType) o;
        return name.equalsIgnoreCase(that.name);
    }

    @Override
    public String toString() {
        return "Maintenance Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                '}';
    }

    @Override
    public MaintenanceTypeDTO toDTO() {
        return new MaintenanceTypeDTO(
                this.id,
                this.name,
                this.description,
                this.active
        );
    }
}
