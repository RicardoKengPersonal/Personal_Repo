package eapli.shodrone.showrequestmanagement.application;

import eapli.shodrone.figuremanagement.domain.FigureVersionID;
import eapli.shodrone.showrequestmanagement.domain.*;
import eapli.shodrone.showrequestmanagement.repository.ShowRequestRepository;

import java.util.Optional;
import java.util.Set;

public class EditShowRequestController {

    private final ShowRequestRepository repo;

    public EditShowRequestController(ShowRequestRepository repo) {
        this.repo = repo;
    }

    public Iterable<ShowRequest> findAll() {
        return repo.findAll();
    }

    public Optional<ShowRequest> findById(ShowRequestID id) {
        return repo.ofIdentity(id);
    }

    public ShowRequest updateRequest(
            ShowRequestID id,
            ShowRequestStatus status,
            ShowRequestDescription description,
            Address address,
            ShowRequestDuration duration,
            Set<FigureVersionID> figures
    ) {
        Optional<ShowRequest> optionalRequest = repo.ofIdentity(id);
        if (optionalRequest.isEmpty()) {
            throw new IllegalArgumentException("No ShowRequest with ID: " + id);
        }

        ShowRequest request = optionalRequest.get();

        request.updateStatus(status);
        request.updateDescription(description);
        request.updateAddress(address);
        request.updateDuration(duration);
        request.updateFigureVersions(figures);

        return repo.save(request);
    }
}
