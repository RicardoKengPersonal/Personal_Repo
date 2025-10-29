package eapli.shodrone.showproposalmanagement.application;


import eapli.shodrone.figuremanagement.domain.FigureVersion;
import eapli.shodrone.figuremanagement.repository.FigureRepository;
import eapli.shodrone.showproposalmanagement.domain.FigureSelectionMode;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.showproposalmanagement.DTO.DroneTypeMappingDTO;
import eapli.shodrone.showrequestmanagement.domain.ShowRequest;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AddFiguresToProposalService {

    private final FigureRepository figureRepository;

    public AddFiguresToProposalService(final FigureRepository figureRepository) {
        this.figureRepository = figureRepository;
    }


    /**
     * Seleciona as figuras candidatas com base no modo escolhido pelo utilizador.
     */
    public Set<FigureVersion> selectFigures(final FigureSelectionMode mode, final ShowRequest request) {
        Set<FigureVersion> allFigures = StreamSupport.stream(figureRepository.findAllFigureVersions().spliterator(), false)
                .collect(Collectors.toSet());

        if (mode == FigureSelectionMode.USE) {
            Set<String> requestedIds = request.figureVersions().stream()
                    .map(Object::toString)
                    .collect(Collectors.toSet());

            return allFigures.stream()
                    .filter(fv -> requestedIds.contains(fv.id().toString()))
                    .collect(Collectors.toSet());
        }
        return allFigures;
    }

    /**
     * Adiciona a figura à proposta. A validação ocorre no método de domínio.
     */
    public void addFigure(ShowProposal proposal, FigureVersion figure, DroneTypeMappingDTO mappingDTO) {
        proposal.addFigureToShow(figure.id(), mappingDTO.mappings);
    }
}
