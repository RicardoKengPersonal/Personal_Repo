package eapli.shodrone.showrequestmanagement.application;

import eapli.shodrone.showrequestmanagement.domain.ShowRequest;
import eapli.shodrone.showrequestmanagement.domain.ShowRequestStatus;
import eapli.shodrone.showrequestmanagement.repository.ShowRequestRepository;

public class ListShowRequestController {

    private final ShowRequestRepository repo;

    public ListShowRequestController(ShowRequestRepository repo) {
        this.repo = repo;
    }

    public Iterable<ShowRequest> findAll() {
        return repo.findAll();
    }

    public Iterable<ShowRequest> findByStatus(ShowRequestStatus status) {
        return repo.findByStatus(status);
    }
}
