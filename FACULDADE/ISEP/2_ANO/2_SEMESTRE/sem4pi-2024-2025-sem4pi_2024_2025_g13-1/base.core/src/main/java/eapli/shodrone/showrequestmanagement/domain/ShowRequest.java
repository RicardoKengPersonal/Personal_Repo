package eapli.shodrone.showrequestmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;
import eapli.shodrone.customermanagement.domain.Customer;
import eapli.shodrone.customermanagement.domain.CustomerRepresentative;
import eapli.shodrone.figuremanagement.domain.FigureVersionID;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "SHOWREQUEST")
public class ShowRequest implements AggregateRoot<ShowRequestID>, Serializable {

    @EmbeddedId
    @Getter
    private ShowRequestID id;

    @Embedded
    private Address address;

    @Embedded
    private ShowRequestDuration duration;

    @Embedded
    private ShowRequestDescription description;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "submission_date"))
    private Date submissionDate;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "event_date"))
    private Date eventDate;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "event_time"))
    private Time eventTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShowRequestStatus status;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "showrequest_figures", joinColumns = @JoinColumn(name = "showrequest_id"))
    private List<FigureVersionID> figureVersions = new ArrayList<>();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designer_user_id")
    private SystemUser assignedDesigner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_user_id", nullable = false)
    private SystemUser author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "representative_id", nullable = false)
    private CustomerRepresentative representative;

    @Column(nullable = false)
    private boolean showProposal;

    @Column(name = "num_drones", nullable = false)
    private int numDrones;

    protected ShowRequest() {
        // for ORM
    }

    public ShowRequest(ShowRequestID id,
                       Address address,
                       ShowRequestDuration duration,
                       ShowRequestDescription description,
                       Date submissionDate,
                       Date eventDate,
                       Time eventTime,
                       Customer customer,
                       Set<FigureVersionID> figureVersions,
                       int numDrones,
                       SystemUser assignedDesigner,
                       CustomerRepresentative representative,
                       SystemUser author) {
        Preconditions.noneNull(id, address, duration, description, submissionDate, eventDate, eventTime, customer,
                figureVersions, assignedDesigner, representative, author);

        if (numDrones <= 0) {
            throw new IllegalArgumentException("Number of drones must be positive.");
        }

        this.id = id;
        this.address = address;
        this.duration = duration;
        this.description = description;
        this.submissionDate = submissionDate;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.customer = customer;
        this.figureVersions = new ArrayList<>(figureVersions);
        this.status = ShowRequestStatus.SUBMITTED;
        this.numDrones = numDrones;
        this.showProposal = false;
        this.assignedDesigner = assignedDesigner;
        this.representative = representative;
        this.author = author;
    }


    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (!(other instanceof ShowRequest)) return false;
        ShowRequest that = (ShowRequest) other;
        return Objects.equals(id, that.id);
    }

    @Override
    public ShowRequestID identity() {
        return id;
    }

    public ShowRequestStatus status() {
        return status;
    }

    public Customer customer() {
        return customer;
    }

    public Address address() {
        return address;
    }

    public ShowRequestDuration duration() {
        return duration;
    }

    public ShowRequestDescription description() {
        return description;
    }

    public Date submissionDate() {
        return submissionDate;
    }

    public Date eventDate() { return eventDate; }

    public Time eventTime() { return eventTime; }

    public int numDrones() {
        return numDrones;
    }

    public Set<FigureVersionID> figureVersions() {
        return new HashSet<>(figureVersions);
    }

    public SystemUser assignedDesigner() {
        return assignedDesigner;
    }

    public SystemUser author() {
        return author;
    }

    public CustomerRepresentative representative() {
        return representative;
    }

    public boolean hasProposal() {
        return showProposal;
    }

    public void markProposalCreated() {
        this.showProposal = true;
    }

    public void editRequest(Address newAddress,
                            ShowRequestDuration newDuration,
                            ShowRequestDescription newDescription,
                            int newNumDrones) {
        if (status != ShowRequestStatus.SUBMITTED && status != ShowRequestStatus.SCHEDULED) {
            throw new IllegalStateException("Only requests in SUBMITTED or SCHEDULED state can be edited.");
        }

        Preconditions.noneNull(newAddress, newDuration, newDescription);
        if (newNumDrones <= 0) {
            throw new IllegalArgumentException("Number of drones must be positive.");
        }

        boolean changed = false;

        if (!this.address.equals(newAddress)) {
            this.address = newAddress;
            changed = true;
        }

        if (!this.duration.equals(newDuration)) {
            this.duration = newDuration;
            changed = true;
        }

        if (!this.description.equals(newDescription)) {
            this.description = newDescription;
            changed = true;
        }

        if (this.numDrones != newNumDrones) {
            this.numDrones = newNumDrones;
            changed = true;
        }

        if (!changed) {
            throw new IllegalArgumentException("No changes detected to update.");
        }
    }

    public void updateStatus(ShowRequestStatus status) {
        Preconditions.nonNull(status, "Status cannot be null");
        this.status = status;
    }

    public void updateDescription(ShowRequestDescription description) {
        Preconditions.nonNull(description, "Description cannot be null");
        this.description = description;
    }

    public void updateAddress(Address newAddress) {
        Preconditions.nonNull(newAddress, "Address cannot be null.");
        this.address = newAddress;
    }

    public void updateDuration(ShowRequestDuration duration) {
        Preconditions.nonNull(duration, "Duration cannot be null");
        this.duration = duration;
    }

    public void updateFigureVersions(Set<FigureVersionID> newVersions) {
        this.figureVersions = new ArrayList<>(newVersions);
    }

    public void updateEventDate(Date newEventDate) {
        Preconditions.nonNull(newEventDate, "Event date cannot be null.");
        this.eventDate = newEventDate;
    }

    public void updateEventTime(Time newEventTime) {
        Preconditions.nonNull(newEventTime, "Event time cannot be null.");
        this.eventTime = newEventTime;
    }

    public String customerName() {
        return customer.companyName();
    }

    public String eventDateStr() {
        return eventDate.toString(); // ou usar SimpleDateFormat para formato mais legível
    }

    public String eventTimeStr() {
        return eventTime.toString();
    }

    public String durationStr() {
        return duration.toString(); // garante que ShowRequestDuration tem um toString útil
    }

    public String locationStr() {
        return address.toString(); // garante que Address tem um toString formatado com GPS
    }


    @Override
    public String toString() {
        StringBuilder figuresStr = new StringBuilder();
        if (figureVersions != null && !figureVersions.isEmpty()) {
            for (FigureVersionID figId : figureVersions) {
                figuresStr.append(figId).append(" ");
            }
        } else {
            figuresStr.append("None");
        }

        return String.format(
                "ID: %s | Status: %s | Customer: %s | Representative: %s %s | Date: %s | Drones: %d | Figures: %s",
                id,
                status,
                customer.companyName(),
                representative.firstName(),
                representative.lastName(),
                submissionDate,
                numDrones,
                figuresStr.toString().trim()
        );
    }



}
