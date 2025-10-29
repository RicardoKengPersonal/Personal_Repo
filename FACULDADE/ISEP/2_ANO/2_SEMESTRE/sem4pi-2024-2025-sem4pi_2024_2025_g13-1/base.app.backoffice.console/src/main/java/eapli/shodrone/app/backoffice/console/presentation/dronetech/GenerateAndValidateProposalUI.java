package eapli.shodrone.app.backoffice.console.presentation.dronetech;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalGenerator;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class GenerateAndValidateProposalUI extends AbstractUI {

    @Override
    protected boolean doShow() {
        Iterable<ShowProposal> proposals = PersistenceContext.repositories().showProposalRepository().findAll();

        Map<Integer, ShowProposal> indexMap = new HashMap<>();
        int index = 1;

        for (ShowProposal proposal : proposals) {
            System.out.printf("%d. %s%n", index, proposal);
            indexMap.put(index, proposal);
            index++;
        }

        if (indexMap.isEmpty()) {
            System.out.println("No proposals ready to generate.");
            return false;
        }

        final int choice = Console.readInteger("Select proposal to generate:");
        final ShowProposal selected = indexMap.get(choice);

        if (selected == null) {
            System.out.println("Invalid selection.");
            return false;
        }

        // Geração e validação do documento final
        try {
            ShowProposalGenerator generator = new ShowProposalGenerator();
            Path outputPath = generator.generateProposalFile(selected, selected.templateIdentifier());
            System.out.println("Proposal successfully validated and file generated at " + outputPath);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }

    }

    @Override
    public String headline() {
        return "Generate and Validate Proposal";
    }
}
