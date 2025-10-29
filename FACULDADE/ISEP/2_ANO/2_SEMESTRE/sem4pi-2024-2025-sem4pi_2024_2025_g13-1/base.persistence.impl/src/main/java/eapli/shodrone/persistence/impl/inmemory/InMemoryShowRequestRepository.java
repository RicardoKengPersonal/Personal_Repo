package eapli.shodrone.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.shodrone.showrequestmanagement.domain.ShowRequest;
import eapli.shodrone.showrequestmanagement.domain.ShowRequestID;
import eapli.shodrone.showrequestmanagement.domain.ShowRequestStatus;
import eapli.shodrone.showrequestmanagement.repository.ShowRequestRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class InMemoryShowRequestRepository
        extends InMemoryDomainRepository<ShowRequest, ShowRequestID>
        implements ShowRequestRepository {

    @Override
    public List<ShowRequest> findByStatus(ShowRequestStatus status) {
        return (List<ShowRequest>) match(sr -> sr.status().equals(status));
    }

    @Override
    public Integer findMaxId() {
        return StreamSupport.stream(findAll().spliterator(), false)
                .map(c -> c.identity().toInteger())
                .max(Integer::compareTo)
                .orElse(0);
    }

}
