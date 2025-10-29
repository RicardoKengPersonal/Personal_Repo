package eapli.shodrone.showproposalmanagement.DTO;

import java.io.Serializable;
import java.util.Map;

public class SendProposalDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String proposalId;
    private final String customerName;
    private final String customerEmail;
    private final String customerAddress;
    private final String customerVAT;
    private final String Address;
    private final String showDate;
    private final String showHour;
    private final int showDuration;
    private final String showID;
    private final String videoLink;
    private final Map<String, Integer> droneModels;
    private final Map<Integer, String> figureSequence;
    private final String dateProposal;
    private final String createName;
    private final String createRole;

    public SendProposalDTO(String proposalId, String customerName, String customerEmail,
                           String customerAddress, String customerVAT, String Address,
                           String showDate, String showHour, int showDuration, String showID,
                           String videoLink,
                           Map<String, Integer> droneModels,
                           Map<Integer, String> figureSequence, String dateProposal,
                           String createName, String createRole) {
        this.proposalId = proposalId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.customerVAT = customerVAT;
        this.Address = Address;
        this.showDate = showDate;
        this.showHour = showHour;
        this.showDuration = showDuration;
        this.showID = showID;
        this.videoLink = videoLink;
        this.droneModels = droneModels;
        this.figureSequence = figureSequence;
        this.dateProposal = dateProposal;
        this.createName = createName;
        this.createRole = createRole;
    }

    public String proposalId() { return proposalId; }
    public String customerName() { return customerName; }
    public String customerEmail() { return customerEmail; }
    public String customerAddress() { return customerAddress; }
    public String customerVAT() { return customerVAT; }
    public String Address() { return Address; }
    public String showDate() { return showDate; }
    public String showHour() { return showHour; }
    public int showDuration() { return showDuration; }
    public String showID() { return showID; }
    public String videoLink() { return videoLink; }
    public Map<String, Integer> droneModels() { return droneModels; }
    public Map<Integer, String> figureSequence() { return figureSequence; }
    public String dateProposal() { return dateProposal; }
    public String createName() { return createName; }
    public String createRole() { return createRole; }
}
