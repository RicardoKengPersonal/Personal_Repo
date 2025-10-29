package eapli.shodrone.showproposalmanagement.export;

import eapli.shodrone.showproposalmanagement.DTO.SendProposalDTO;
import java.util.Map;

public class Model2Formatter implements IProposalFormatter {

    @Override
    public String format(SendProposalDTO dto) {
        StringBuilder content = new StringBuilder();

        // Parte inicial
        content.append("Dear Sirs,\n");
        content.append(dto.customerName()).append("\n");
        content.append(dto.customerAddress()).append("\n");
        content.append(dto.customerVAT()).append("\n\n");

        content.append("Reference ").append(dto.proposalId()).append(" / ").append(dto.dateProposal()).append("\n");
        content.append("Show Proposal\n\n");

        content.append("Shodrone is pleased to submit for your consideration a proposal for the execution of an aerial show with drones, as described below.\n");
        content.append("Shodrone is a company that prioritizes safety, which is why it uses the most advanced AI technology to support the development of its shows, with all shows being previously and carefully tested/simulated with AI-Test© technology before being presented to the client. ");
        content.append("In the link ").append(dto.videoLink()).append(" there is a video with a simulation of the proposed show.\n\n");

        content.append("With the application of AI-Test©, a Shodrone exclusive, we are confident in offering liability insurance in the amount of 1000€ for the show. ");
        content.append("Detailed show data is presented in the attachment.\n\n");

        content.append("Being certain that we will be the target of your preference.\n\n");
        content.append("We subscribe at your disposal.\n\n");
        content.append("Best regards,\n\n");
        content.append(dto.createName()).append("\n");
        content.append(dto.createRole()).append("\n\n");

        // Page break
        content.append("-\n\n");

        // Anexo
        content.append("Attachment – Show Details ").append(dto.showID()).append("\n\n");

        content.append("Location – ").append(dto.Address()).append("\n");
        content.append("Date – ").append(dto.showDate()).append("\n");
        content.append("Time – ").append(dto.showHour()).append("\n");
        content.append("Duration – ").append(dto.showDuration()).append(" minutes\n\n");

        // Lista de drones
        content.append("#List of used drones\n");
        for (Map.Entry<String, Integer> entry : dto.droneModels().entrySet()) {
            content.append(entry.getKey()).append(" – ").append(entry.getValue()).append(" units.\n");
        }

        // Lista de figuras
        content.append("\n#List of figures\n");
        for (Map.Entry<Integer, String> entry : dto.figureSequence().entrySet()) {
            content.append(entry.getKey()).append(" – ").append(entry.getValue()).append("\n");
        }

        return content.toString();
    }

}