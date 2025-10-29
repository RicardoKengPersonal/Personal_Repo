package eapli.shodrone.app.testing.console.application;

import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalStatus;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;


public class ListShowsProposalService {

    private final ShowProposalRepository showProposalRepository = PersistenceContext.repositories().showProposalRepository();

    /**
     * Devolve todos os espetáculos disponíveis registados no sistema.
     *
     * @return um iterável de objetos Show.
     */
    public Iterable<ShowProposal> allShowsProposal() {

        return this.showProposalRepository.findByStatus(ShowProposalStatus.CREATED);
    }
}