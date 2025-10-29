package eapli.shodrone.showmanagement.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;


@Embeddable
public class ShowID implements Serializable, Comparable<ShowID> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "show_id")
    private Integer id;

    protected ShowID() {
        // For ORM
    }

    public ShowID(final Integer id) {
        if (id == null)
            throw new IllegalArgumentException("ID must not be null or blank");
        this.id = id;
    }

    public static ShowID valueOf(final Integer id) {
        return new ShowID(id);
    }


    public Integer toInteger() {
        return id;
    }

    @Override
    public int compareTo(ShowID other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
