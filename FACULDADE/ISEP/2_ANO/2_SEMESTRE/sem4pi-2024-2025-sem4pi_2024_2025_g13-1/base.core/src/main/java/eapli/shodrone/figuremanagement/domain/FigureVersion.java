package eapli.shodrone.figuremanagement.domain;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.shodrone.dronemanagement.domain.DroneType;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class FigureVersion {

    @EmbeddedId
    @Getter
    private FigureVersionID id;

    @Column(nullable = false)
    private String version;

    @ManyToOne(optional = false)
    private SystemUser designer;

    @ManyToOne(optional = false)
    private Figure figure;

    @Embedded
    private DSL dsl;

    @Embedded
    private FigureStatic figureStatic;

    @Embedded
    private FigureDynamic figureDynamic;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @OneToMany(mappedBy = "figureVersion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FigureElement> elements = new ArrayList<>();

    protected FigureVersion() {
        // for ORM
    }

    public FigureVersion(final FigureVersionID id, final Figure figure,
                         final SystemUser designer,
                         final DSL dsl, final FigureStatic figureStatic,
                         final FigureDynamic figureDynamic) {
        if (id == null)
            throw new IllegalArgumentException("ID must not be null.");
        this.id = id;
        this.figure = Objects.requireNonNull(figure);
        this.version = generateVersionFromFigure(figure);
        this.designer = Objects.requireNonNull(designer);
        this.dsl = Objects.requireNonNull(dsl);
        this.figureStatic = Objects.requireNonNull(figureStatic);
        this.figureDynamic = Objects.requireNonNull(figureDynamic);
        this.timestamp = LocalDateTime.now();
    }

    private String generateVersionFromFigure(Figure figure) {
        int versionNumber = figure.versions().size() + 1;
        return "v" + versionNumber;
    }

    /**
     * MÉTODO: Essencial para a US312.
     * Percorre todos os elementos desta figura e coleciona os tipos de drone
     * únicos que são necessários para a executar.
     *
     * @return Um conjunto de DroneType's requeridos pela figura.
     */
    public Set<DroneType> getRequiredDroneTypes() {
        if (this.elements == null || this.elements.isEmpty()) {
            return Set.of();
        }
        return this.elements.stream()
                .map(FigureElement::requiredDroneType)
                .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * NOVO MÉTODO: Essencial para a lógica de validação de stock.
     * Calcula o número total de drones necessários para cada tipo de drone.
     * A informação vem dos FigureElements associados.
     *
     * @return Um mapa onde a chave é o DroneType e o valor é a quantidade total necessária.
     */
    public Map<DroneType, Integer> getRequiredDronesSummary() {
        if (this.elements == null || this.elements.isEmpty()) {
            return Collections.emptyMap();
        }
        return this.elements.stream()
                .collect(Collectors.groupingBy(
                        FigureElement::requiredDroneType,
                        Collectors.summingInt(FigureElement::totalDrones)
                ));
    }


    public String version() {
        return version;
    }

    public SystemUser designer() {
        return designer;
    }

    public DSL dsl() {
        return dsl;
    }

    public FigureStatic figureStatic() {
        return figureStatic;
    }

    public FigureDynamic figureDynamic() {
        return figureDynamic;
    }

    public LocalDateTime timestamp() {
        return timestamp;
    }

    public List<FigureElement> elements() {
        return List.copyOf(elements);
    }

    public Figure figure() {
        return figure;
    }

    public void setFigure(final Figure figure) {
        this.figure = Objects.requireNonNull(figure);
    }

    public void addElement(FigureElement element) {
        element.setFigureVersion(this);
        elements.add(element);
    }

    public FigureVersionID id() {
        return id;
    }

    @Override
    public String toString() {
        return "FigureVersion{" +
                "id=" + id +
                ", version='" + version + '\'' +
                ", designer=" + designer.username() +
                ", timestamp=" + timestamp +
                ", Figure=" + figure.figureName() +
                '}';
    }
}