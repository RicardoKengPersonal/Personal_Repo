package eapli.shodrone.showproposalmanagement.export;

import eapli.shodrone.showproposalmanagement.DTO.SendProposalDTO;

/**
 * Interface for proposal formatting plugins. Each implementation is responsible
 * for generating a proposal document based on a specific template.
 */
public interface IProposalFormatter {

    /**
     * Formats the proposal data into a string representation of the document.
     *
     * @param dto The data transfer object containing all necessary proposal information.
     * @return A string containing the full, formatted document.
     */
    String format(SendProposalDTO dto);
}