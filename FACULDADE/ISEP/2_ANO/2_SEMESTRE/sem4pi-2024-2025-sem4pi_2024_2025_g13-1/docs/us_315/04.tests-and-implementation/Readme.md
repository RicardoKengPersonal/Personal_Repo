# US 315 Add video of simulation to the proposal

## 4. Tests 
 
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


## 5. Construction (Implementation)


### Class AddVideoToShowProposalUI

```java
    @Override
    protected boolean doShow() {
        final String proposalId = Console.readLine("Enter the ID of the Show Proposal to update:");
        final String videoLink = Console.readLine("Enter the video link:");
    
        try {
            controller.updateVideo(proposalId, videoLink);
            System.out.println("Video link updated successfully.");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    
        return false;
    }
    
    @Override
    public String headline() {
        return "Add Video Link to Show Proposal";
    }

```

### Class ShowProposalService

```java
 @Transactional
public void updateVideoLink(String proposalIdStr, String videoLink) {
    ShowProposalID proposalId = ShowProposalID.valueOf(Integer.valueOf(proposalIdStr));

    ShowProposal proposal = showProposalRepo.ofIdentity(proposalId)
            .orElseThrow(() -> new IllegalStateException("ShowProposal with ID " + proposalIdStr + " not found."));

    proposal.setVideoLink(videoLink);
    showProposalRepo.save(proposal);
}

```


### Class AddVideoToShowProposalController

```java
public void updateVideo(String proposalIdStr, String videoLink) {
    authz.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN, Roles.CRM_COLLABORATOR);
    service.updateVideoLink(proposalIdStr, videoLink);
}


```
