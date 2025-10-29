package eapli.shodrone.figuremanagement.domain;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


@Embeddable
@EqualsAndHashCode
@ToString
public class FigureID implements ValueObject, Comparable<FigureID>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "figure_id")
    private Integer id;

    protected FigureID() {
        // for ORM
    }

    public FigureID(Integer id) {
        if (id == null)
            throw new IllegalArgumentException("Figure ID must not be null");
        this.id = id;
    }

    public static FigureID valueOf(final Integer id) {
        return new FigureID(id);
    }

    public Integer toInteger() {
        return id;
    }

    @Override
    public int compareTo(FigureID other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
