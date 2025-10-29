package eapli.shodrone.showproposalmanagement.domain;

import eapli.shodrone.figuremanagement.domain.FigureVersionID;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Entity
@Table(name = "show_proposal_figure_entries")
public class ShowProposalFigureEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private FigureVersionID figure;

    @Column(nullable = false)
    private int position;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "figure_entry_drone_mapping", joinColumns = @JoinColumn(name = "figure_entry_id"))
    @MapKeyColumn(name = "drone_type")
    @Column(name = "drone_model_name")
    private Map<String, String> droneMappings = new HashMap<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_proposal_id", nullable = false)
    private ShowProposal proposal;

    protected ShowProposalFigureEntry() {
        // for ORM
    }

    public ShowProposalFigureEntry(final ShowProposal proposal, final FigureVersionID figure,
                                   final int position, final Map<String, String> droneMappings) {
        this.proposal = Objects.requireNonNull(proposal);
        this.figure = Objects.requireNonNull(figure);
        if (position < 0) throw new IllegalArgumentException("Position must be non-negative.");
        if (droneMappings == null || droneMappings.isEmpty()) {
            throw new IllegalArgumentException("Drone mappings must be provided.");
        }
        this.position = position;
        this.droneMappings = new HashMap<>(droneMappings);
    }

    public FigureVersionID figure() {
        return figure;
    }

    public int position() {
        return position;
    }

    public Map<String, String> droneMappings() {
        return Collections.unmodifiableMap(droneMappings);
    }

    public ShowProposal proposal() {
        return proposal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShowProposalFigureEntry)) return false;
        ShowProposalFigureEntry that = (ShowProposalFigureEntry) o;
        return position == that.position && figure.equals(that.figure) && proposal.equals(that.proposal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(figure, position, proposal);
    }
}
