package eapli.shodrone.app.customer.console.presentation;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.shodrone.app.customer.console.application.AnalyseProposalController;

import java.awt.Desktop; // Required to open the file
import java.io.File;      // Required to handle the file object
import java.io.FileWriter;
import java.io.IOException;

public class AnalyseProposalUI extends AbstractUI {

    private final AnalyseProposalController controller = new AnalyseProposalController();

    @Override
    protected boolean doShow() {
        System.out.println("To analyse a proposal, you need the access code you received.");
        final String accessCode = Console.readLine("Please enter the Proposal Access Code: ");

        if (accessCode == null || accessCode.trim().isEmpty()) {
            System.out.println("Operation cancelled. The access code cannot be empty.");
            return false;
        }

        try {
            System.out.println("\nDownloading proposal from server...");
            String proposalDocument = controller.analyseProposal(accessCode);

            // Check if the server returned an error before proceeding
            if (proposalDocument.startsWith("ERROR:")) {
                System.err.println(proposalDocument);
                return false;
            }

            // --- 1. Save the document to a file ---
            File downloadedFile = saveProposalToFile(proposalDocument, accessCode);
            System.out.println("Proposal downloaded successfully to: " + downloadedFile.getAbsolutePath());

            // --- 2. Open the downloaded file ---
            openFile(downloadedFile);

        } catch (IOException e) {
            System.err.println("\nERROR: An error occurred while saving or opening the file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("\nAn unexpected error occurred: " + e.getMessage());
        }

        return false; // Return to the previous menu
    }

    /**
     * Saves the proposal content to a .txt file in the system's temporary directory.
     *
     * @param content    The proposal document as a String.
     * @param accessCode The access code, used for the filename.
     * @return The File object representing the saved file.
     * @throws IOException if an error occurs during file writing.
     */
    private File saveProposalToFile(String content, String accessCode) throws IOException {
        // Find the system's temporary directory in a platform-independent way
        String tempDir = System.getProperty("java.io.tmpdir");
        String fileName = "proposal_" + accessCode + ".txt";
        File file = new File(tempDir, fileName);

        // Use try-with-resources to ensure the writer is closed automatically
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }
        return file;
    }

    /**
     * Attempts to open the specified file using the system's default application.
     *
     * @param file The file to open.
     */
    private void openFile(File file) {
        // Check if the Desktop API is supported on the current platform
        if (Desktop.isDesktopSupported()) {
            try {
                System.out.println("Attempting to open the file with the default text editor...");
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                System.err.println("ERROR: Could not open the file. Please navigate to it manually.");
                e.printStackTrace();
            }
        } else {
            System.out.println("NOTE: Automatic file opening is not supported on this system. Please find the file at the path above.");
        }
    }

    @Override
    public String headline() {
        return "Analyse and Download a Show Proposal";
    }
}