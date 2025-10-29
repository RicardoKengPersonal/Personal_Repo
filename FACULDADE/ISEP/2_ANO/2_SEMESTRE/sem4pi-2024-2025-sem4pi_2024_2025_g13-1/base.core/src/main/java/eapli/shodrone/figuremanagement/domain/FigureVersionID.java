package eapli.shodrone.figuremanagement.domain;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class FigureVersionID implements ValueObject, Comparable<FigureVersionID>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "figure_version_id")
    private Integer id;

    protected FigureVersionID() {
        // for ORM
    }

    public FigureVersionID(final Integer id) {
        if (id == null)
            throw new IllegalArgumentException("FigureVersionID must not be null.");
        this.id = id;
    }

    public static FigureVersionID valueOf(final Integer id) {
        return new FigureVersionID(id);
    }

    public Integer toInteger() {
        return id;
    }

    @Override
    public int compareTo(FigureVersionID other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FigureVersionID)) return false;
        FigureVersionID that = (FigureVersionID) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
