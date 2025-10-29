package eapli.shodrone.figuremanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.money.domain.model.Money;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
public class Figure implements AggregateRoot<FigureID> {

    @EmbeddedId
    @Getter
    private FigureID id;

    @Column(nullable = false)
    private String figureName;

    @Column(nullable = false)
    private String keyword;

    @Column(nullable = false)
    private String description;

    @Column(nullable = true)
    private String dslPath;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "figure_category_id", nullable = false))
    private FigureCategoryID figureCategoryID;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FigureVisibility visibility;

    @Setter
    @OneToMany(mappedBy = "figure", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FigureVersion> versions = new ArrayList<>();

    protected Figure() {
        // for ORM
    }

    public Figure(final FigureID id, final String figureName, final FigureVisibility visibility, final String keyword,
                  final String description, final FigureCategoryID figureCategoryID) {
        this.id = id;
        this.figureName = Objects.requireNonNull(figureName);
        this.visibility = Objects.requireNonNull(visibility);
        this.keyword = Objects.requireNonNull(keyword);
        this.description = Objects.requireNonNull(description);
        this.figureCategoryID = Objects.requireNonNull(figureCategoryID);
    }

    public void addVersion(FigureVersion version) {
        versions.add(version);
        version.setFigure(this);
    }
    public void addDslPathTo(final String path) {
        dslPath = path;
    }
    public boolean removeVersionById(FigureVersionID id) {
        return versions.removeIf(v -> v.getId().equals(id));
    }


    public boolean isPublic() {
        return this.visibility == FigureVisibility.PUBLIC;
    }

    public String figureName() {
        return figureName;
    }

    public String keyword() {
        return keyword;
    }

    public String description() {
        return description;
    }

    public FigureVisibility visibility() {
        return visibility;
    }

    public FigureCategoryID figureCategoryID() {
        return figureCategoryID;
    }

    public Set<FigureVersion> versions() {
        return Set.copyOf(versions);
    }

    @Override
    public FigureID identity() {
        return id;
    }

    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Figure that = (Figure) other;
        return this.id.equals(that.id);
    }

    @Override
    public String toString() {
        return "Figure{" +
                "id=" + id +
                ", figureName='" + figureName + '\'' +
                ", keyword='" + keyword + '\'' +
                ", description='" + description + '\'' +
                ", figureCategoryID=" + figureCategoryID +
                ", visibility=" + visibility +
                ", versions=" + versions +
                '}';
    }

}
