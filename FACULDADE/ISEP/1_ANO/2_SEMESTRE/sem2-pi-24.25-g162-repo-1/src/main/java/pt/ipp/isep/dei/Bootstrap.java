package pt.ipp.isep.dei;

import pt.ipp.isep.dei.controller.AuthenticationController;
import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.repository.*;
import pt.ipp.isep.dei.ui.gui.fx.SpriteLoader;

public class Bootstrap implements Runnable {

        // Add some task categories to the repository as bootstrap
        public void run() {
                addTaskCategories();
                addOrganization();
                addUsers();
                SpriteLoader.loadSprites();
        }

        private void addOrganization() {
                // get organization repository
                OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();

                Organization organization = new Organization("This Company");
                organization.addEmployee(new Employee("admin@this.app"));
                organization.addEmployee(new Employee("employee@this.app"));
                organizationRepository.add(organization);
        }

        private void addTaskCategories() {
                // get task category repository
                TaskCategoryRepository taskCategoryRepository = Repositories.getInstance().getTaskCategoryRepository();

                taskCategoryRepository.add(new TaskCategory("Analysis"));
                taskCategoryRepository.add(new TaskCategory("Design"));
                taskCategoryRepository.add(new TaskCategory("Implementation"));
                taskCategoryRepository.add(new TaskCategory("Development"));
                taskCategoryRepository.add(new TaskCategory("Testing"));
                taskCategoryRepository.add(new TaskCategory("Deployment"));
                taskCategoryRepository.add(new TaskCategory("Maintenance"));
        }

        private void addUsers() {
                // get authentication repository
                AuthenticationRepository authenticationRepository = Repositories.getInstance()
                                .getAuthenticationRepository();

                authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN,
                                AuthenticationController.ROLE_ADMIN);
                authenticationRepository.addUserRole(AuthenticationController.ROLE_PLAYER,
                                AuthenticationController.ROLE_PLAYER);
                authenticationRepository.addUserRole(AuthenticationController.ROLE_EDITOR,
                                AuthenticationController.ROLE_EDITOR);

                authenticationRepository.addUserWithRole("Admin", "admin@this.app", "admin",
                                AuthenticationController.ROLE_ADMIN);
                authenticationRepository.addUserWithRole("Player", "player@this.app", "player",
                                AuthenticationController.ROLE_PLAYER);
                authenticationRepository.addUserWithRole("Editor", "editor@this.app", "editor",
                                AuthenticationController.ROLE_EDITOR);
        }

}