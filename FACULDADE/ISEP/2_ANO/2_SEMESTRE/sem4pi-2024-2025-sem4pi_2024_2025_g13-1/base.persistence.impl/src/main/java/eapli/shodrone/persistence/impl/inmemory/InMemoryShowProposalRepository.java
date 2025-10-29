package eapli.shodrone.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalID;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalStatus;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

public class InMemoryShowProposalRepository
        extends InMemoryDomainRepository<ShowProposal, ShowProposalID>
        implements ShowProposalRepository {

    @Override
    public List<ShowProposal> findByStatus(ShowProposalStatus status) {
        return (List<ShowProposal>) match(sr -> sr.status().equals(status));
    }

    @Override
    public Integer findMaxId() {
        return StreamSupport.stream(findAll().spliterator(), false)
                .map(c -> c.identity().toInteger())
                .max(Integer::compareTo)
                .orElse(0);
    }

    @Override
    public List<ShowProposal> findAllReadyToSend() {
        return (List<ShowProposal>) match(p ->
                p.status().equals(ShowProposalStatus.CREATED)
                        && p.videoLink() != null
                        && !p.videoLink().isBlank()
                        && !p.figureEntries().isEmpty()
                        && !p.getDroneFleet().isEmpty()
        );
    }

    @Override
    public Optional<ShowProposal> findByAccessCode(String accessCode) {
        return StreamSupport.stream(match(p -> p.accessCode().equalsIgnoreCase(accessCode)).spliterator(), false)
                .findFirst();
    }

    @Override
    public List<ShowProposal> findAllReadyToTest() {
        return (List<ShowProposal>) match(p ->
                p.status().equals(ShowProposalStatus.TESTED)
                        && p.videoLink() != null
                        && !p.videoLink().isBlank()
                        && !p.figureEntries().isEmpty()
                        && !p.getDroneFleet().isEmpty()
        );
    }

    @Override
    public List<ShowProposal> findProposalsWithoutFigures() {
        return (List<ShowProposal>) match(p ->
                p.status().equals(ShowProposalStatus.CREATED)
                        && p.figureEntries().isEmpty());
    }
}
