package eapli.shodrone.showproposalmanagement.application;

import eapli.framework.application.UseCaseController;
import eapli.shodrone.dronemanagement.domain.DroneModel;
import eapli.shodrone.dronemanagement.domain.DroneType;
import eapli.shodrone.figuremanagement.domain.FigureVersion;
import eapli.shodrone.showproposalmanagement.domain.FigureSelectionMode;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.showproposalmanagement.DTO.DroneTypeMappingDTO;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;
import eapli.shodrone.showrequestmanagement.domain.ShowRequest;

import java.util.List;
import java.util.Map;
import java.util.Set;

@UseCaseController
public class AddFiguresToProposalController {

    private final ShowProposalRepository proposalRepository;
    private final AddFiguresToProposalService service;

    public AddFiguresToProposalController(final ShowProposalRepository proposalRepository,
                                          final AddFiguresToProposalService service) {
        this.proposalRepository = proposalRepository;
        this.service = service;
    }

    public List<ShowProposal> listAvailableProposals() {
        return proposalRepository.findProposalsWithoutFigures();
    }

    public ShowRequest getShowRequestFromProposal(final ShowProposal proposal) {
        return proposal.showRequest();
    }

    public Set<FigureVersion> figuresForMode(final FigureSelectionMode mode, final ShowRequest req) {
        return service.selectFigures(mode, req);
    }

    /**
     * Obtém os tipos de drone requeridos por uma figura. Delega para o objeto de domínio.
     */
    public Set<DroneType> getRequiredDroneTypesForFigure(FigureVersion figure) {
        return figure.getRequiredDroneTypes();
    }

    /**
     * ALTERADO: Obtém a frota completa (modelos e quantidades) de drones disponíveis na proposta.
     */
    public Map<DroneModel, Integer> getAvailableDroneFleetForProposal(ShowProposal proposal) {
        return proposal.getDroneFleet();
    }

    /**
     * Chama o serviço para adicionar a figura com o seu mapeamento à proposta.
     */
    public void addFigureToProposal(ShowProposal proposal, FigureVersion figure, DroneTypeMappingDTO mappingDTO) {
        service.addFigure(proposal, figure, mappingDTO);
    }

    /**
     * Persiste as alterações feitas na proposta.
     */
    public void saveProposal(ShowProposal proposal) {
        proposalRepository.save(proposal);
    }
}