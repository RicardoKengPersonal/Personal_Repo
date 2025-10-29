package eapli.shodrone.showproposalmanagement.repository;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalID;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalStatus;

import java.util.List;
import java.util.Optional;

public interface ShowProposalRepository extends DomainRepository<ShowProposalID, ShowProposal> {

    List<ShowProposal> findByStatus(ShowProposalStatus status);

    Integer findMaxId();

    List<ShowProposal> findAllReadyToTest();

    List<ShowProposal> findAllReadyToSend();

    List<ShowProposal> findProposalsWithoutFigures();

    Optional<ShowProposal> findByAccessCode(String accessCode);

}
