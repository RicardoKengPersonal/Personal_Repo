package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateCollaboratorAssignJobController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.DocumentType;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.HRMUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * CreateCollaboratorAssignJobUI class represents the user interface for creating a collaborator and assigning a job to them.
 */
public class CreateCollaboratorAssignJobUI implements Runnable {

    private final CreateCollaboratorAssignJobController controller;

    /**
     * Constructs a new CreateCollaboratorAssignJobUI object.
     */
    public CreateCollaboratorAssignJobUI() {
        controller = new CreateCollaboratorAssignJobController();
    }

    /**
     * Gets the controller associated with this UI.
     *
     * @return The CreateCollaboratorAssignJobController instance.
     */
    private CreateCollaboratorAssignJobController getController() {
        return controller;
    }

    /**
     * Displays the list of available jobs.
     *
     * @param jobs The list of jobs to display.
     */
    public void displayJobsList(ArrayList<Job> jobs) {
        int i = 1;
        System.out.println();
        for (Job job : jobs) {
            System.out.println(i + " - " + job.getjobName());
            i++;
        }
        System.out.println("0 - Stop/None");
    }

    /**
     * Displays the list of collaborators.
     *
     * @param collaborators The list of collaborators to display.
     */
    public void displayCollaboratorsList(ArrayList<Collaborator> collaborators) {
        int i = 1;
        System.out.println();
        for (Collaborator collab : collaborators) {
            System.out.println(i + " - " + collab.getName() + " - " + collab.getDocumentIdentificationNumber());
            i++;
        }
        System.out.println("0 - Stop/None");
    }
    /**
     * Allows the user to select and add a job.
     *
     * @return The selected job.
     */
    public Job selectAndAddJob() {
        Job selectedJob;
        ArrayList<Job> jobs = controller.getJobsList();
        int numberOfJobs = jobs.size();
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        while (option != 0) {
            displayJobsList(jobs);
            System.out.println("\nSelect a job to be added (0 for none/stop)");
            option = scanner.nextInt();
            if(option >= 1 && option <= numberOfJobs) {
                return jobs.get(option - 1);
            }
        }
        return null;
    }

    /**
     * Displays the list of document types and allows the user to select one.
     *
     * @return The selected document type.
     */
    private DocumentType displayAndSelectDocumentType() {
        List<DocumentType> DocumentTypesList = controller.getDocTypesList();
        int listSize = DocumentTypesList.size();
        int answer = -1;
        Scanner sc = new Scanner(System.in);
        displayDocumentTypeList(DocumentTypesList);
        int value;
        do {
            String in = Utils.readLineFromConsole("\nSelect a document type: ");
            try {
                assert in != null;
                value = Integer.parseInt(in);
            } catch (NumberFormatException ex) {
                value = -1;
            }
        } while (value < 0 || value > listSize);
        if (value == 0) {
            backToHRMUI();
        }
        return DocumentTypesList.get(value - 1);
    }

    /**
     * Displays the list of document types.
     *
     * @param documentTypeList The list of document types to display.
     */
    private void displayDocumentTypeList(List<DocumentType> documentTypeList) {
        int i = 1;
        System.out.println();
        for (DocumentType DocumentType : documentTypeList) {
            System.out.println("  " + i + " - " + DocumentType);
            i++;
        }
    }

    /**
     * Navigates back to the HRMUI menu.
     */
    private void backToHRMUI() {
        MenuItem item = new MenuItem(AuthenticationController.ROLE_HRM, new HRMUI());
        item.run();
    }

    /**
     * Creates a collaborator and assigns a job to them.
     *
     * @return True if the collaborator was successfully created and the job assigned, false otherwise.
     */
    public boolean createCollaborator() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Please enter the name of the collaborator: ");
        String name = scanner.nextLine();
        System.out.print("Please enter the birth date: ");
        String birthDate = scanner.nextLine();
        System.out.print("Please enter the admission date of the collaborator: ");
        String admissionDate = scanner.nextLine();
        System.out.print("Please enter the address of the collaborator: ");
        String address = scanner.nextLine();
        System.out.print("Please enter the mobile number of the collaborator: ");
        int mobileNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Please enter the email of the collaborator: ");
        String email = scanner.nextLine();
        System.out.print("Please enter the tax payer number of the collaborator: ");
        String taxPayerNumber = scanner.nextLine();
        scanner.nextLine();
        DocumentType identificationDocumentType = displayAndSelectDocumentType();
        System.out.print("Please enter the document identification number of the collaborator: ");
        String documentIdentificationNumber = scanner.nextLine();
        Job chosenJob = selectAndAddJob();
        return controller.addCollaborator(name, documentIdentificationNumber, taxPayerNumber, email, mobileNumber, address, admissionDate, birthDate, identificationDocumentType, chosenJob);
    }

    /**
     * Runs the CreateCollaboratorAssignJobUI.
     */
    public void run() {
        boolean result = createCollaborator();
        if(result){
            System.out.println("Collaborator added successfully");
        }else{
            System.out.println("Collaborator could not be added");
        }
    }
}
