package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.DocumentType;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents a CollaboratorsRepository in the system.
 * It contains a list of Collaborator objects and provides methods to add and retrieve collaborators.
 */
public class CollaboratorsRepository implements Serializable {

    private final ArrayList<Collaborator> collaboratorsArrayList = new ArrayList<>();

    /**
     * Gets the list of collaborators.
     * @return The list of collaborators.
     */
    public ArrayList<Collaborator> getCollaboratorsArrayList() {
        return collaboratorsArrayList;
    }

    /**
     * Adds a collaborator to the repository.
     * @param name The name of the collaborator.
     * @param documentIdentificationNumber The document identification number of the collaborator.
     * @param taxPayerNumber The tax payer number of the collaborator.
     * @param email The email of the collaborator.
     * @param mobileNumber The mobile number of the collaborator.
     * @param address The address of the collaborator.
     * @param admissionDate The admission date of the collaborator.
     * @param birthDate The birth date of the collaborator.
     * @param identificationDocumentType The identification document type of the collaborator.
     * @param job The job of the collaborator.
     * @return true if the collaborator was added successfully, false otherwise.
     * @throws IllegalArgumentException if an error occurs while adding the collaborator.
     */
    public boolean addCollaborator(String name, String documentIdentificationNumber, String taxPayerNumber, String email, int mobileNumber, String address, String admissionDate, String birthDate, DocumentType identificationDocumentType, Job job) {
        try {
            Collaborator collab = new Collaborator(name, documentIdentificationNumber, taxPayerNumber, email, mobileNumber, address, admissionDate, birthDate, identificationDocumentType, job);
            for(Collaborator collaborator : collaboratorsArrayList){
                if(collaborator.equals(collab)){
                    return false;
                }
            }
            collaboratorsArrayList.add(collab);
            return true;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Gets a list of deactivated collaborators by skill.
     * @param skills The skills to be matched.
     * @return A list of deactivated collaborators that match the given skills.
     */
    public ArrayList<Collaborator> getDeactivatedCollaboratorsBySkill(ArrayList<Skill> skills) {
        ArrayList<Collaborator> selectedCollaborators = new ArrayList<>();
        for (Collaborator c : this.collaboratorsArrayList) {
            if (c.getSkills().containsAll(skills)) {
                selectedCollaborators.add(c);
            }
        }

        return selectedCollaborators;
    }

    /**
     * Adds a skill to a collaborator.
     * @param collab The collaborator to which the skill will be added.
     * @param skills The skills to be added.
     * @return true if the skill was added successfully, false otherwise.
     */
    public boolean addSkill(Collaborator collab, ArrayList<Skill> skills) {
        boolean result = false;
        for (Skill skill : skills) {
            if (!collab.skillAlreadyAssigned(skill)) {
                collab.addSkillToCollaboratorSkills(skill);
                result = true;
            }
        }
        return result;
    }
}