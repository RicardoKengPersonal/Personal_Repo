package eapli.shodrone.showproposalmanagement.domain;

import eapli.shodrone.integrations.plugins.dsl.proposal.ShowProposalDocumentValidator;
import eapli.shodrone.showproposalmanagement.application.ProposalDocumentGeneratorService;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ShowProposalGenerator {

    private static final String OUTPUT_DIR = "base.integrations.plugins.dsl/lprog/files output"; // cria esta pasta no projeto

    public Path generateProposalFile(ShowProposal proposal, String templateId) throws Exception {
        // Gerar o conteúdo final com dados reais
        ProposalDocumentGeneratorService service = new ProposalDocumentGeneratorService();
        String documentContent = service.generateDocumentAsString(proposal, templateId);
//VOLTAR A METER ISTO DEPOIS DA VALIDAÇÃO
        //...........................................................................................
        // Guardar o ficheiro
        Path outputPath = Paths.get(OUTPUT_DIR, "show_proposal_" + proposal.identity() + ".txt");
        Files.createDirectories(outputPath.getParent());
        Files.writeString(outputPath, documentContent);
        //...........................................................................................

        // Validar o documento final
        ShowProposalDocumentValidator validator = new ShowProposalDocumentValidator();
        validator.validate(new ByteArrayInputStream(documentContent.getBytes(StandardCharsets.UTF_8)));

        return outputPath;
    }
}
