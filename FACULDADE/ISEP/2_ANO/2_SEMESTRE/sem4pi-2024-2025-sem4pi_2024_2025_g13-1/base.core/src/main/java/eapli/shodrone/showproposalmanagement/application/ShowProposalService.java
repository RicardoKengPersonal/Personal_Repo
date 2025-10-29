package eapli.shodrone.showproposalmanagement.application;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalID;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;
import jakarta.transaction.Transactional;

public class ShowProposalService {
    private final ShowProposalRepository showProposalRepo;
    private final UserRepository userRepo;

    public ShowProposalService(
            ShowProposalRepository showProposalRepo,
            UserRepository userRepo) {
        this.showProposalRepo = showProposalRepo;
        this.userRepo = userRepo;
    }

    public Iterable<SystemUser> findAllUsers() {
        return userRepo.findAll();
    }

    public void registerNewShowProposal(final ShowProposal proposal) {
        try {
            showProposalRepo.save(proposal);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save ShowProposal", e);
        }
    }
    @Transactional
    public void updateVideoLink(String proposalIdStr, String videoLink) {
        ShowProposalID proposalId = ShowProposalID.valueOf(Integer.valueOf(proposalIdStr));

        ShowProposal proposal = showProposalRepo.ofIdentity(proposalId)
                .orElseThrow(() -> new IllegalStateException("ShowProposal with ID " + proposalIdStr + " not found."));

        proposal.setVideoLink(videoLink);
        showProposalRepo.save(proposal);
    }

}
