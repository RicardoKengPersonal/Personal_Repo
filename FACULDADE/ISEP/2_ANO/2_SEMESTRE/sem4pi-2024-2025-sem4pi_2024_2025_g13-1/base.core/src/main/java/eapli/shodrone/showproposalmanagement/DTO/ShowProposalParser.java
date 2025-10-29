package eapli.shodrone.showproposalmanagement.DTO;

import eapli.framework.representations.dto.DTOParser;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalID;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;
import eapli.shodrone.showrequestmanagement.domain.ShowRequest;
import eapli.shodrone.showrequestmanagement.domain.ShowRequestID;
import eapli.shodrone.showrequestmanagement.repository.ShowRequestRepository;


public class ShowProposalParser implements DTOParser<ShowProposalDTO, ShowProposal> {

    private final ShowProposalRepository showProposalRepo;
    private final ShowRequestRepository showRequestRepo;

    public ShowProposalParser(
            final ShowProposalRepository showProposalRepo,
            final ShowRequestRepository showRequestRepo) {
        this.showProposalRepo = showProposalRepo;
        this.showRequestRepo = showRequestRepo;
    }

    @Override
    public ShowProposal valueOf(final ShowProposalDTO dto) {
        final ShowRequestID reqId = ShowRequestID.valueOf(Integer.valueOf(dto.getRequestId()));

        final ShowRequest showRequest = showRequestRepo.ofIdentity(reqId)
                .orElseThrow(() -> new IllegalArgumentException("ShowRequest não encontrada: " + dto.getRequestId()));

        Integer maxId = showProposalRepo.findMaxId();
        final Integer next = (maxId != null ? maxId + 1 : 1);
        final ShowProposalID newId = ShowProposalID.valueOf(next);

        return new ShowProposal(newId, showRequest);
    }
}
