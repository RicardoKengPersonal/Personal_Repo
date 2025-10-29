package eapli.shodrone.figuremanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class FigureCategory implements AggregateRoot<FigureCategoryID> {

    @EmbeddedId
    @Getter
    private FigureCategoryID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Getter
    private boolean active;

    protected FigureCategory() {
        // for ORM
    }

    public FigureCategory(final FigureCategoryID id, final String name) {
        if (id == null)
            throw new IllegalArgumentException("ID must not be null.");
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Category name must not be empty.");

        this.id = id;
        this.name = name.trim();
        this.active = true;
    }

    public String name() {
        return name;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    public void rename(String newName) {
        if (newName == null || newName.trim().isEmpty())
            throw new IllegalArgumentException("Category name cannot be empty.");

        this.name = newName.trim();
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof FigureCategory)) return false;
        FigureCategory that = (FigureCategory) other;
        return this.name.equalsIgnoreCase(that.name);
    }

    @Override
    public FigureCategoryID identity() {
        return this.id;
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FigureCategory)) return false;
        FigureCategory that = (FigureCategory) o;
        return name.equalsIgnoreCase(that.name);
    }

    @Override
    public String toString() {
        return "FigureCategory{" +
                "id=" + (id != null ? id.toString() : "null") +
                ", name='" + name + '\'' +
                ", active=" + active +
                '}';
    }
}
