package eapli.shodrone.showrequestmanagement.domain;

import eapli.shodrone.figuremanagement.domain.FigureID;
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
public class ShowRequestID implements Serializable, Comparable<ShowRequestID> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "showrequest_id")
    private Integer id;

    protected ShowRequestID() {

    }

    public ShowRequestID(final Integer id) {
        if (id == null)
            throw new IllegalArgumentException("ID must not be null or blank");
        this.id = id;
    }

    public static ShowRequestID valueOf(final Integer id) {
        return new ShowRequestID(id);
    }


    public Integer toInteger() {
        return id;
    }

    @Override
    public int compareTo(ShowRequestID other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
