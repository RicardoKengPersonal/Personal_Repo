package eapli.shodrone.showproposalmanagement.domain;

import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.shodrone.showproposalmanagement.application.ShowProposalService;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;
import eapli.shodrone.showrequestmanagement.domain.ShowRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddVideoTest {

    private ShowProposalRepository repo;
    private ShowProposalService service;

    @BeforeEach
    void setUp() {
        repo = mock(ShowProposalRepository.class);
        UserRepository userRepo = mock(UserRepository.class);
        service = new ShowProposalService(repo, userRepo);
    }

    @Test
    void updateVideoLink_ValidProposal_SetsVideoLink() {
        ShowProposalID proposalId = ShowProposalID.valueOf(1);
        ShowProposal proposal = new ShowProposal(proposalId, mock(ShowRequest.class));
        when(repo.ofIdentity(proposalId)).thenReturn(Optional.of(proposal));

        service.updateVideoLink("1", "https://video.com/clip.mp4");

        assertEquals("https://video.com/clip.mp4", proposal.videoLink());
    }

    @Test
    void updateVideoLink_NonexistentProposal_ThrowsIllegalStateException() {
        ShowProposalID proposalId = ShowProposalID.valueOf(42);
        when(repo.ofIdentity(proposalId)).thenReturn(Optional.empty());

        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> service.updateVideoLink("42", "https://video.com/clip.mp4")
        );
        assertEquals("ShowProposal with ID 42 not found.", ex.getMessage());
    }
}
