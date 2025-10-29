package eapli.shodrone.showproposalmanagement.export;

import eapli.shodrone.showproposalmanagement.DTO.SendProposalDTO;
import java.util.Map;

public class Model1Formatter implements IProposalFormatter {

    @Override
    public String format(SendProposalDTO dto) {
        StringBuilder content = new StringBuilder();

        // Parte inicial
        content.append("Exmos. Senhores\n");
        content.append(dto.customerName()).append("\n");
        content.append(dto.customerAddress()).append("\n");
        content.append(dto.customerVAT()).append("\n\n");

        content.append("Referência ").append(dto.proposalId()).append(" / ").append(dto.dateProposal()).append("\n");
        content.append("Proposta de Show\n\n");

        content.append("A Shodrone tem o prazer de submeter à V/ apreciação uma proposta para execução de um show aéreo com drones, conforme descrição abaixo. \n");
        content.append("A Shodrone é uma empresa que dá prioridade à segurança, pelo que usa a mais avançada tecnologia de IA para apoiar o desenvolvimento dos seus shows, sendo que todos os shows são prévia e cuidadosamente testados/simulados com a tecnologia AI-Test© antes de serem apresentados ao cliente. ");
        content.append("No link ").append(dto.videoLink()).append(" encontra-se um vídeo com a simulação do show proposto.\n\n");

        content.append("Com a aplicação do AI-Test©, um exclusivo da Shodrone, temos a confiança de oferecer um seguro de responsabilidade civil no valor de 100€ para o show. ");
        content.append("Os dados detalhados do show são apresentados em anexo.\n\n");

        content.append("Estando certos que seremos alvo da V/ preferência.\n\n");
        content.append("Subscrevemo-nos ao dispor.\n\n");
        content.append("Melhores cumprimentos,\n\n");
        content.append(dto.createName()).append("\n");
        content.append(dto.createRole()).append("\n\n");

        // Page break
        content.append("-\n\n");

        // Anexo
        content.append("Anexo – Detalhes do Show ").append(dto.showID()).append("\n\n");

        content.append("Local de realização – ").append(dto.Address()).append("\n");
        content.append("Data – ").append(dto.showDate()).append("\n");
        content.append("Hora – ").append(dto.showHour()).append("\n");
        content.append("Duração – ").append(dto.showDuration()).append(" minutos\n\n");

        // Lista de drones
        content.append("#Lista de drones utilizados\n");
        for (Map.Entry<String, Integer> entry : dto.droneModels().entrySet()) {
            content.append(entry.getKey()).append(" – ").append(entry.getValue()).append(" unidades.\n");
        }

        // Lista de figuras
        content.append("\n#Lista de figuras\n");
        for (Map.Entry<Integer, String> entry : dto.figureSequence().entrySet()) {
            content.append(entry.getKey()).append(" – ").append(entry.getValue()).append("\n");
        }


        return content.toString();
    }
}