package eapli.shodrone.showrequestmanagement.repository;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.shodrone.showrequestmanagement.domain.Date;
import eapli.shodrone.showrequestmanagement.domain.ShowRequest;
import eapli.shodrone.showrequestmanagement.domain.ShowRequestID;
import eapli.shodrone.showrequestmanagement.domain.ShowRequestStatus;

import java.util.List;
import java.util.Optional;

public interface ShowRequestRepository extends DomainRepository<ShowRequestID, ShowRequest> {

    List<ShowRequest> findByStatus(ShowRequestStatus status);

    Integer findMaxId();


}
