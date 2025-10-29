package eapli.shodrone.figuremanagement.domain;
import eapli.shodrone.dronemanagement.domain.DroneModel;
import eapli.shodrone.dronemanagement.domain.DroneType;
import jakarta.persistence.*;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Entity
public class FigureElement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private int totalDrones;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ElementType elementType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Movement movement;

    @Enumerated(EnumType.STRING)
    @Column(name = "required_drone_type", nullable = false)
    private DroneType requiredDroneType;

    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "figure_version_id")
    private FigureVersion figureVersion;

    protected FigureElement() {}

    public FigureElement(ElementType elementType, Movement movement, DroneType requiredDroneType, int totalDrones) {
        this.elementType = elementType;
        this.movement = movement;
        this.requiredDroneType = requiredDroneType;
        this.totalDrones = totalDrones;
    }

    public ElementType elementType() { return elementType; }
    public Movement movement() { return movement; }
    public DroneType requiredDroneType() { return requiredDroneType; }
    public int totalDrones() { return totalDrones; }
}
