package pt.ipp.isep.dei.esoft.project.ui.gui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.io.File;
import java.util.ArrayList;

/**
 * Bootstrap class to initialize the application.
 */
public class Bootstrap implements Runnable {

    /**
     * Method to run the Bootstrap initialization process.
     */
    public void run() {
        File file = new File("repositories.ser");
        if (!file.exists()) {
            addSkills();
            addGreenSpaces();
            addJobs();
            addCollaborators();
            addUsers();
            addSkillsToCollaborators();
            addAgendaEntries();
            addVehicles();
        }
    }

    /**
     * Method to add green spaces.
     */
    public void addGreenSpaces() {
        GreenSpacesRepository greenSpacesRepository = Repositories.getInstance().getGreenSpaceRepository();
        greenSpacesRepository.addGreenSpace("Jardim do Morro", "Rua do Espaço Verde", GreenSpaceType.LARGE_SIZED_PARK, "gsm@gsm.app", 2);
        greenSpacesRepository.addGreenSpace("Parque da Cidade", "Rua do Parque da cidade", GreenSpaceType.LARGE_SIZED_PARK, "gsm@gsm.app", 12);
        greenSpacesRepository.addGreenSpace("Parque de Avioso", "Rua do Parque de Avioso", GreenSpaceType.GARDEN, "gsm@gsm.app", 9);
        greenSpacesRepository.addGreenSpace("Parque do Covelo", "Rua do Covelo", GreenSpaceType.LARGE_SIZED_PARK, "gsm@gsm.app", 50);

    }

    /**
     * Method to add vehicles.
     */
    public void addVehicles() {
        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
        vehicleRepository.addVehicle(new Vehicle("AA-10-BB", "Opel", "Corsa", "categoria B", 1000, 1200, 150000, "30-09-2023", "30-04-2024", 30000));
        vehicleRepository.addVehicle(new Vehicle("AA-10-BC", "Fiat", "Punto", "categoria B", 1000, 1200, 150000, "30-09-2023", "30-04-2024", 30000));
        vehicleRepository.addVehicle(new Vehicle("AA-10-BD", "Lamborghini", "Huracan", "categoria B", 1000, 1200, 150000, "30-09-2023", "30-04-2024", 30000));
        vehicleRepository.addVehicle(new Vehicle("AA-10-BE", "Nissan", "GTR", "categoria B", 1000, 1200, 150000, "30-09-2023", "30-04-2024", 30000));
        vehicleRepository.addVehicle(new Vehicle("AA-10-BF", "Range Rover", "Evoque", "categoria B", 1000, 1200, 150000, "30-09-2023", "30-04-2024", 30000));
    }

    /**
     * Method to add agenda entries.
     */
    private void addAgendaEntries() {
        ToDoList toDoList = Repositories.getInstance().getToDoList();
        GreenSpacesRepository greenSpacesRepository = Repositories.getInstance().getGreenSpaceRepository();

        toDoList.addTask(new Task("entry1 description", "entry1", greenSpacesRepository.getGreenSpacesArrayList().get(0), 8, TaskUrgencyDegree.LOW));
    }

    /**
     * Method to add jobs.
     */
    private void addJobs() {
        JobsRepository jobsRepository = Repositories.getInstance().getJobsRepository();
        jobsRepository.addJob("Park Ranger");
        jobsRepository.addJob("Landscape Architect");
        jobsRepository.addJob("Arborist");
        jobsRepository.addJob("Groundskeeper");
        jobsRepository.addJob("Horticulturist");
        jobsRepository.addJob("Botanist");
        jobsRepository.addJob("Ecologist");
        jobsRepository.addJob("Park Manager");
        jobsRepository.addJob("Urban Forester");
        jobsRepository.addJob("Natural Resource Manager");
        jobsRepository.addJob("Park Maintenance Supervisor");
        jobsRepository.addJob("Wildlife Biologist");
        jobsRepository.addJob("Environmental Educator");
        jobsRepository.addJob("Irrigation Specialist");
        jobsRepository.addJob("Greenhouse Manager");
        jobsRepository.addJob("Community Garden Coordinator");
        jobsRepository.addJob("Trail Steward");
        jobsRepository.addJob("Sustainability Coordinator");
        jobsRepository.addJob("Park Events Coordinator");
        jobsRepository.addJob("Conservation Technician");
    }

    /**
     * Method to add users.
     */
    private void addUsers() {
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_HRM, AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_VFM, AuthenticationController.ROLE_VFM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_GSM, AuthenticationController.ROLE_GSM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_COLLAB, AuthenticationController.ROLE_COLLAB);
        authenticationRepository.addUserWithRole("Main Administrator", "vfm@vfm.app", "a", AuthenticationController.ROLE_VFM);
        authenticationRepository.addUserWithRole("HRM", "hrm@hrm.app", "a", AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserWithRole("GSM", "gsm@gsm.app", "a", AuthenticationController.ROLE_GSM);
        authenticationRepository.addUserWithRole("Nuno Teixeira", "nunoepteixeira@outlook.pt", "password", AuthenticationController.ROLE_COLLAB);
        authenticationRepository.addUserWithRole("Simao Sabrosa", "nunoepteixeira2@outlook.pt", "password", AuthenticationController.ROLE_COLLAB);
        authenticationRepository.addUserWithRole("CollaboratorOne", "collab1@outlook.pt", "password", AuthenticationController.ROLE_COLLAB);
        authenticationRepository.addUserWithRole("Collaborator Two", "collab2@outlook.pt", "password", AuthenticationController.ROLE_COLLAB);
        authenticationRepository.addUserWithRole("Collaborator Three", "collab3@outlook.pt", "password", AuthenticationController.ROLE_COLLAB);
        authenticationRepository.addUserWithRole("Collaborator Four", "collab4@outlook.pt", "password", AuthenticationController.ROLE_COLLAB);
        authenticationRepository.addUserWithRole("Collaborator Five", "collab5@outlook.pt", "password", AuthenticationController.ROLE_COLLAB);
        authenticationRepository.addUserWithRole("Collaborator Six", "collab6@outlook.pt", "password", AuthenticationController.ROLE_COLLAB);
        authenticationRepository.addUserWithRole("Collaborator Seven", "collab7@outlook.pt", "password", AuthenticationController.ROLE_COLLAB);
        authenticationRepository.addUserWithRole("Collaborator Eight", "collab8@outlook.pt", "password", AuthenticationController.ROLE_COLLAB);
    }

    /**
     * Method to add skills.
     */
    private void addSkills() {
        SkillsRepository skillsRepository = Repositories.getInstance().getSkillsRepository();
        skillsRepository.addSkill("Plant Identification");
        skillsRepository.addSkill("Soil Management");
        skillsRepository.addSkill("Pruning and Trimming");
        skillsRepository.addSkill("Watering Techniques");
        skillsRepository.addSkill("Pest and Disease Management");
        skillsRepository.addSkill("Companion Planting");
        skillsRepository.addSkill("Garden Design");
        skillsRepository.addSkill("Propagation");
        skillsRepository.addSkill("Seasonal Gardening");
        skillsRepository.addSkill("Organic Gardening Practices");
        skillsRepository.addSkill("Harvesting and Storage");
        skillsRepository.addSkill("Tool Maintenance");
        skillsRepository.addSkill("Environmental Awareness");
        skillsRepository.addSkill("Botany");
        skillsRepository.addSkill("Garden Photography");
        skillsRepository.addSkill("Landscaping");
        skillsRepository.addSkill("Container Gardening");
        skillsRepository.addSkill("Herbalism");
        skillsRepository.addSkill("Community Engagement");
        skillsRepository.addSkill("Continuous Learning");
        skillsRepository.addSkill("Plant Health Diagnosis");
        skillsRepository.addSkill("Fertilization Techniques");
        skillsRepository.addSkill("Mulching and Weed Control");
        skillsRepository.addSkill("Seed Saving");
        skillsRepository.addSkill("Rainwater Harvesting");
        skillsRepository.addSkill("Wildlife Gardening");
        skillsRepository.addSkill("Indoor Plant Care");
        ;
        skillsRepository.addSkill("Driving License");
    }

    /**
     * Method to add collaborators.
     */
    private void addCollaborators() {
        JobsRepository jobsRepository = Repositories.getInstance().getJobsRepository();
        ArrayList<Job> jobsList = jobsRepository.getJobsArrayList();
        CollaboratorsRepository collabRepository = Repositories.getInstance().getCollaboratorsRepository();

        collabRepository.addCollaborator("Nuno Teixeira", "303673011ZY7", "217594832", "1231375@isep.ipp.pt", 936148562, "Rua dos Gunoes 4470", "30-09-2023", "30-09-2000", DocumentType.ID_CARD, jobsList.get(0));
        collabRepository.addCollaborator("Simao Sabrosa", "303673001ZY8", "255331584", "nunoepteixeira@outlook.pt", 936148561, "Rua dos Gunoes 4471", "30-09-2023", "30-09-2000", DocumentType.ID_CARD, jobsList.get(1));
        collabRepository.addCollaborator("Collaborator One", "303673002ZY9", "255331585", "collab1@outlook.pt", 936148563, "Rua dos Gunoes 4472", "30-09-2023", "30-09-2000", DocumentType.ID_CARD, jobsList.get(2));
        collabRepository.addCollaborator("Collaborator Two", "303673003ZY0", "255331586", "collab2@outlook.pt", 936148564, "Rua dos Gunoes 4473", "30-09-2023", "30-09-2000", DocumentType.ID_CARD, jobsList.get(3));
        collabRepository.addCollaborator("Collaborator Three", "303673004ZY1", "255331587", "collab3@outlook.pt", 936148565, "Rua dos Gunoes 4474", "30-09-2023", "30-09-2000", DocumentType.ID_CARD, jobsList.get(4));
        collabRepository.addCollaborator("Collaborator Four", "303673005ZY2", "255331588", "collab4@outlook.pt", 936148566, "Rua dos Gunoes 4475", "30-09-2023", "30-09-2000", DocumentType.ID_CARD, jobsList.get(5));
        collabRepository.addCollaborator("Collaborator Five", "303673006ZY3", "255331589", "collab5@outlook.pt", 936148567, "Rua dos Gunoes 4476", "30-09-2023", "30-09-2000", DocumentType.ID_CARD, jobsList.get(6));
        collabRepository.addCollaborator("Collaborator Six", "303673007ZY4", "255331590", "collab6@outlook.pt", 936148568, "Rua dos Gunoes 4477", "30-09-2023", "30-09-2000", DocumentType.ID_CARD, jobsList.get(7));
        collabRepository.addCollaborator("Collaborator Seven", "303673008ZY5", "255331591", "collab7@outlook.pt", 936148569, "Rua dos Gunoes 4478", "30-09-2023", "30-09-2000", DocumentType.ID_CARD, jobsList.get(8));
        collabRepository.addCollaborator("Collaborator Eight", "303673009ZY6", "255331592", "collab8@outlook.pt", 936148570, "Rua dos Gunoes 4479", "30-09-2023", "30-09-2000", DocumentType.ID_CARD, jobsList.get(9));
    }


    /**
     * Method to add collaborators' skills.
     */
    private void addSkillsToCollaborators() {
        ArrayList<Skill> listToAdd = new ArrayList<Skill>();
        ArrayList<Skill> registeredSkills = new ArrayList<Skill>();

        CollaboratorsRepository collabRepository = Repositories.getInstance().getCollaboratorsRepository();
        ArrayList<Collaborator> registeredCollaborators = new ArrayList<Collaborator>();
        registeredCollaborators = collabRepository.getCollaboratorsArrayList();


        SkillsRepository skillsRepository = Repositories.getInstance().getSkillsRepository();

        registeredSkills = skillsRepository.getSkillsArrayList();
        listToAdd.add(registeredSkills.get(0));
        listToAdd.add(registeredSkills.get(1));
        listToAdd.add(registeredSkills.get(2));
        listToAdd.add(registeredSkills.get(3));
        listToAdd.add(registeredSkills.get(4));

        //collabRepository.addSkill(registeredCollaborators.get(0), listToAdd);
    }
}
