package eapli.shodrone.figuremanagement.domain;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@ToString
public class FigureCategoryID implements ValueObject, Comparable<FigureCategoryID>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "figure_category_id")
    private Integer id;

    protected FigureCategoryID() {
        // for ORM
    }

    private FigureCategoryID(final Integer id) {
        this.id = id;
    }

    public static FigureCategoryID valueOf(final Integer id) {
        return new FigureCategoryID(id);
    }

    public Integer toInteger() {
        return id;
    }

    @Override
    public int compareTo(FigureCategoryID other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return id.toString();
    }
}

