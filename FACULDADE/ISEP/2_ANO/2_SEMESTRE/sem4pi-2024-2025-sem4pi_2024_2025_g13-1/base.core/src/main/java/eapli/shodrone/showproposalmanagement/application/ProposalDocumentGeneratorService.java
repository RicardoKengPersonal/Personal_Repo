package eapli.shodrone.showproposalmanagement.application;

import eapli.shodrone.figuremanagement.repository.FigureRepository;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.showproposalmanagement.DTO.SendProposalDTO;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalFigureEntry;
import eapli.shodrone.showproposalmanagement.export.IProposalFormatter;
import eapli.shodrone.showproposalmanagement.export.Model1Formatter;
import eapli.shodrone.showproposalmanagement.export.Model2Formatter;
import eapli.shodrone.showproposalmanagement.export.Model3Formatter;
import eapli.shodrone.showrequestmanagement.domain.ShowRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Collectors;

public class ProposalDocumentGeneratorService {

    public String generateDocumentAsString(ShowProposal proposal, String templateId) {

        SendProposalDTO dto = buildDTO(proposal);

        IProposalFormatter formatter = switch (templateId) {
            case "MODEL_1" -> new Model1Formatter();
            case "MODEL_2" -> new Model2Formatter();
            case "MODEL_3" -> new Model3Formatter();
            default -> throw new IllegalArgumentException("Unknown proposal template identifier: " + templateId);
        };

        return formatter.format(dto);
    }

    private SendProposalDTO buildDTO(ShowProposal proposal) {

        FigureRepository repoFigure = PersistenceContext.repositories().figure();

        ShowRequest request = proposal.showRequest();
        String proposalId = proposal.identity().toString();
        String customerName = request.customer().companyName();
        String customerEmail = request.representative().email().toString();
        String customerAddress = request.customer().address().toString();
        String customerVAT = request.customer().vatNumber().toString();
        String address = request.address().toString();
        String showDate = request.eventDate().toString();
        String showHour = request.eventTime().toString();
        String videoLink = proposal.videoLink();
        int showDuration = request.duration().minutes();
        String showID = request.identity().toString();

        Map<Integer, String> figureSequence = proposal.figureEntries().stream()
                .collect(Collectors.toMap(
                        ShowProposalFigureEntry::position,
                        entry -> repoFigure.ofIdentity(entry.figure())
                                .map(fv -> fv.figure().figureName() + " (" + fv.version() + ")")
                                .orElse("Unknown Figure")
                ));
        Map<String, Integer> droneModels = proposal.getDroneFleet().entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().name(), Map.Entry::getValue));
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateProposal = date.format(formatter);

        return new SendProposalDTO(
                proposalId,
                customerName,
                customerEmail,
                customerAddress,
                customerVAT,
                address,
                showDate,
                showHour,
                showDuration,
                showID,
                videoLink,
                droneModels,
                figureSequence,
                dateProposal,
                proposal.ConfiguredByUser(),
                proposal.ConfiguredByRole()

        );
    }
}