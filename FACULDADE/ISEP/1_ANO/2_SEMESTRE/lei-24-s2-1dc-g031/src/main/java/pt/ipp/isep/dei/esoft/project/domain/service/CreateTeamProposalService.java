package pt.ipp.isep.dei.esoft.project.domain.service;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorsRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;

public class CreateTeamProposalService {
    private CollaboratorsRepository collaboratorRepository;

    public CreateTeamProposalService() {
        this.collaboratorRepository = Repositories.getInstance().getCollaboratorsRepository();
    }

    public ArrayList<Collaborator> arrangeCollaborattorsBySkill(ArrayList<Skill> skills) {
        var collaborators = new ArrayList<Collaborator>();

        for (var s : skills) {
            var collaboratorsBySkill = collaboratorRepository.getDeactivatedCollaboratorsBySkill(skills);

            if (collaboratorsBySkill.size() == 0) {
                var errorMessage = new StringBuilder();
                errorMessage.append("No collaborators found for skill ");
                errorMessage.append(s.getNameOfTheSkill());

                throw new InputMismatchException(errorMessage.toString());
            }

            for (var c : collaboratorsBySkill) {
                if (!collaborators.contains(c))
                    collaborators.add(c);
            }
        }

        Collections.sort(collaborators, new Comparator<Collaborator>() {
            @Override
            public int compare(Collaborator c1, Collaborator c2) {
                return Integer.compare(c2.getSkills().size(), c1.getSkills().size());
            }
        });

        return collaborators;
    }

    public ArrayList<ArrayList<Collaborator>> arrangeTeams(int max, int min, ArrayList<Skill> skills, ArrayList<Collaborator> collaborators) {
        var allTeams = new ArrayList<ArrayList<Collaborator>>();
        var skillsCopy = (ArrayList<Skill>) skills.clone();

        for (int i = min; i <= max; i++) {
            List<List<Collaborator>> combinations = generateCombinations(collaborators, i);
            for (List<Collaborator> combination : combinations) {
                if (hasAllSkills(combination, skillsCopy)) {
                    allTeams.add(new ArrayList<>(combination));
                }
            }
        }

        if (allTeams.isEmpty()) {
            throw new InputMismatchException("Could not form any teams with the specified criteria.");
        }

        return allTeams;
    }

    private List<List<Collaborator>> generateCombinations(ArrayList<Collaborator> collaborators, int teamSize) {
        List<List<Collaborator>> combinations = new ArrayList<>();
        generateCombinationsHelper(collaborators, teamSize, 0, new ArrayList<>(), combinations);
        return combinations;
    }

    private void generateCombinationsHelper(ArrayList<Collaborator> collaborators, int teamSize, int start, ArrayList<Collaborator> current, List<List<Collaborator>> combinations) {
        if (current.size() == teamSize) {
            combinations.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < collaborators.size(); i++) {
            current.add(collaborators.get(i));
            generateCombinationsHelper(collaborators, teamSize, i + 1, current, combinations);
            current.remove(collaborators.get(i));
        }
    }

    private boolean hasAllSkills(List<Collaborator> team, ArrayList<Skill> skills) {
        for (Skill skill : skills) {
            boolean skillFound = false;
            for (Collaborator collaborator : team) {
                if (collaborator.getSkills().contains(skill)) {
                    skillFound = true;
                    break;
                }
            }
            if (!skillFound) {
                return false;
            }
        }
        return true;
    }
}