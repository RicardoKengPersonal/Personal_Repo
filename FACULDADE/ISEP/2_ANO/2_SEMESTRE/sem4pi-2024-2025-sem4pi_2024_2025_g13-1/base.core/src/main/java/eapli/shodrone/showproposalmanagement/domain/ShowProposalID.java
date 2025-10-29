package eapli.shodrone.showproposalmanagement.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@ToString
public class ShowProposalID implements Serializable, Comparable<ShowProposalID> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "showproposal_id")
    private Integer id;

    protected ShowProposalID() {

    }

    public ShowProposalID(final Integer id) {
        if (id == null)
            throw new IllegalArgumentException("ID must not be null or blank");
        this.id = id;
    }

    public static ShowProposalID valueOf(final Integer id) {
        return new ShowProposalID(id);
    }


    public Integer toInteger() {
        return id;
    }

    @Override
    public int compareTo(ShowProposalID other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return id.toString();
    }
}

