package eapli.shodrone.showmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalStatus;
import eapli.shodrone.showrequestmanagement.domain.ShowRequest;
import eapli.shodrone.customermanagement.domain.Customer;
import eapli.shodrone.customermanagement.domain.CustomerRepresentative;
import eapli.shodrone.showrequestmanagement.domain.Date;
import eapli.shodrone.showrequestmanagement.domain.Time;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name = "SHOW")
public class Show implements AggregateRoot<ShowID>, Serializable {

    private static final AtomicInteger idGenerator = new AtomicInteger(0);

    @EmbeddedId
    private ShowID id;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "proposal_id", nullable = false)
    private ShowProposal proposal;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id", nullable = false)
    private ShowRequest request;

    @Embedded
    private Date scheduledDate;

    @Embedded
    private Time scheduledTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ShowStatus status;

    protected Show() {
        // For ORM
    }

    public Show(ShowID id, ShowProposal proposal, ShowRequest request, Date scheduledDate, Time scheduledTime) {
        Preconditions.noneNull(id, proposal, request, scheduledDate, scheduledTime);

        this.id = id;
        this.proposal = proposal;
        this.request = request;
        this.scheduledDate = scheduledDate;
        this.scheduledTime = scheduledTime;
        this.status = ShowStatus.SCHEDULED;
    }

    public ShowProposal proposal() {
        return proposal;
    }

    public ShowRequest request() {
        return request;
    }

    public Date scheduledDate() {
        return scheduledDate;
    }

    public Time scheduledTime() {
        return scheduledTime;
    }

    public Customer customer() {
        return request.customer();
    }

    public CustomerRepresentative representative() {
        return request.representative();
    }

    public ShowStatus status() {
        return status;
    }

    public void markAsCompleted() {
        this.status = ShowStatus.COMPLETED;
    }

    public void cancel() {
        this.status = ShowStatus.CANCELLED;
    }

    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (!(other instanceof Show)) return false;
        final Show that = (Show) other;
        return Objects.equals(id, that.id);
    }

    public static Show createShowFromProposal(ShowProposal proposal, ShowRequest request, Date date, Time time) {
        if (proposal.status() != ShowProposalStatus.ACCEPTED_CUSTOMER) {
            throw new IllegalArgumentException("The proposal is not yet accepted.");
        }
        ShowID newId = ShowID.valueOf(idGenerator.incrementAndGet());
        return new Show(newId, proposal, request, date, time);
    }

    @Override
    public ShowID identity() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Show ID: %s | Date: %s | Time: %s",
                id, scheduledDate, scheduledTime);
    }

}
