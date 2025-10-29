package eapli.shodrone.showproposalmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;
import eapli.shodrone.dronemanagement.domain.DroneModel;
import eapli.shodrone.figuremanagement.domain.FigureVersionID;
import eapli.shodrone.showproposalmanagement.DTO.ShowProposalDTO;
import eapli.shodrone.showrequestmanagement.domain.ShowRequest;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "SHOW_PROPOSAL")
public class ShowProposal implements AggregateRoot<ShowProposalID>, DTOable<ShowProposalDTO>, Serializable {

    @EmbeddedId
    private ShowProposalID showProposalId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "show_request_id", nullable = false)
    private ShowRequest showRequest;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShowProposalStatus status;

    @Column(name = "video_link")
    private String videoLink;

    @Column(name = "template_identifier")
    private String templateIdentifier;

    @Column(name = "configured_by_user")
    private String configuredByUser;

    @Column(name = "configured_by_role")
    private String configuredByRole;

    @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ShowProposalFigureEntry> figureEntries = new ArrayList<>();

    @Column(name = "access_code", unique = true, nullable = true)
    private String accessCode;

    // US311 Armazena os modelos de drone e a sua quantidade para este espetáculo.
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "show_proposal_drone_fleet", joinColumns = @JoinColumn(name = "show_proposal_id"))
    @MapKeyJoinColumn(name = "drone_model_id")
    @Column(name = "quantity")
    private Map<DroneModel, Integer> droneFleet = new HashMap<>();

    protected ShowProposal() {
        // for ORM
    }

    public ShowProposal(final ShowProposalID id, final ShowRequest showRequest) {
        Preconditions.noneNull(id, showRequest);
        this.showProposalId = id;
        this.showRequest = showRequest;
        this.status = ShowProposalStatus.CREATED;
    }

    // ==== Identity ====

    @Override
    public ShowProposalID identity() {
        return showProposalId;
    }

    @Override
    public boolean sameAs(final Object other) {
        if (this == other) return true;
        if (!(other instanceof ShowProposal)) return false;
        final ShowProposal that = (ShowProposal) other;
        return Objects.equals(showProposalId, that.showProposalId);
    }

    // ==== Getters ====

    public String templateIdentifier() { return templateIdentifier; }

    public ShowRequest showRequest() {
        return showRequest;
    }

    public ShowProposalStatus status() {
        return status;
    }

    public String videoLink() {
        return videoLink;
    }

    public List<ShowProposalFigureEntry> figureEntries() {
        return Collections.unmodifiableList(figureEntries);
    }

    public Map<DroneModel, Integer> getDroneFleet() { return Collections.unmodifiableMap(droneFleet); }

    public String ConfiguredByUser() {
        return configuredByUser;
    }

    public String ConfiguredByRole() {
        return configuredByRole;
    }

    public String accessCode() {
        return this.accessCode;
    }

    // ==== Commands ====

    public void updateStatus(final ShowProposalStatus newStatus) {
        Preconditions.nonNull(newStatus, "Status cannot be null.");
        this.status = newStatus;
    }

    public void setVideoLink(final String videoLink) {
        Preconditions.ensure(!videoLink.isBlank(), "Video link must be provided.");
        this.videoLink = videoLink;
    }

    public void addFigureToShow(final FigureVersionID figure, final Map<String, String> droneMappings) {
            Preconditions.noneNull(figure, droneMappings);

            if (!figureEntries.isEmpty()) {
                ShowProposalFigureEntry last = figureEntries.get(figureEntries.size() - 1);
                if (last.figure().equals(figure)) {
                    throw new IllegalArgumentException("You cannot add the same figure in two consecutive positions.");
                }
            }

            int nextPosition = figureEntries.size();
            ShowProposalFigureEntry entry = new ShowProposalFigureEntry(this, figure, nextPosition, droneMappings);
            figureEntries.add(entry);
    }

    public void markAsReadyToTest() {
        if (this.status != ShowProposalStatus.CREATED) {
             throw new IllegalStateException("Only Proposal with status 'CREATED' .");
        }
        this.status = ShowProposalStatus.TESTED;
    }

    public void addDronesOfModel(final DroneModel model, final int quantity) {
        Preconditions.nonNull(model);
        Preconditions.ensure(quantity > 0, "Quantity must be positive.");

        this.droneFleet.merge(model, quantity, Integer::sum);
    }

    public void assignTemplate(final String templateId) {
        if (this.status != ShowProposalStatus.TESTED) { // ou outro estado apropriado
            throw new IllegalStateException("Proposal must be in 'TESTED' state to be configured.");
        }
        Preconditions.ensure(templateId != null && !templateId.isBlank(), "Template identifier cannot be empty.");
        this.templateIdentifier = templateId;
    }

    public void clearDroneFleet() {
        this.droneFleet.clear();
    }


    public String markAsSentAndGenerateAccessCode() {
        if (this.status != ShowProposalStatus.READY_TO_SEND) {
            throw new IllegalStateException("Proposal must be in READY_TO_SEND state to be sent.");
        }
        if (this.accessCode != null) {
            throw new IllegalStateException("Proposal has already been sent and has an access code.");
        }

        this.status = ShowProposalStatus.SENT;
        this.accessCode = UUID.randomUUID().toString();
        return this.accessCode;
    }

    public void assignTemplateAndAuditor(final String templateId, final String user, final String role) {
        if (this.status != ShowProposalStatus.TESTED) {
            throw new IllegalStateException("Proposal must be in 'TESTED' state to be configured.");
        }
        Preconditions.ensure(templateId != null && !templateId.isBlank(), "Template identifier cannot be empty.");
        Preconditions.ensure(user != null && !user.isBlank(), "Configuring user cannot be empty.");
        Preconditions.ensure(role != null && !role.isBlank(), "Configuring user role cannot be empty.");

        this.templateIdentifier = templateId;
        this.configuredByUser = user;
        this.configuredByRole = role;
    }

    // ==== Debug/Logging ====

    @Override
    public String toString() {
        int totalDrones = this.droneFleet.values().stream().mapToInt(Integer::intValue).sum();

        return String.format("Proposal ID: %s | Status: %s | Request ID: %s | Video: %s | Drones: %d | Figures: %d | Template: %s",
                showProposalId,
                status,
                showRequest.identity(),
                videoLink != null ? videoLink : "N/A",
                totalDrones,
                figureEntries.size(),
                templateIdentifier != null ? templateIdentifier : "N/A"
        );
    }

    @Override
    public ShowProposalDTO toDTO() {
        return new ShowProposalDTO(
                showRequest.identity().toString()
        );
    }
}

