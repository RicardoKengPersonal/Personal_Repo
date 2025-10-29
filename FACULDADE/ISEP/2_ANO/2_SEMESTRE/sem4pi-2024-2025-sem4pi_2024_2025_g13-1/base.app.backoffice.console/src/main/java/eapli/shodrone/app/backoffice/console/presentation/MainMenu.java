package eapli.shodrone.app.backoffice.console.presentation;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import eapli.shodrone.Application;
import eapli.shodrone.app.backoffice.console.presentation.authz.*;
import eapli.shodrone.app.backoffice.console.presentation.crmcollaborator.*;
import eapli.shodrone.app.backoffice.console.presentation.crmmanager.*;
import eapli.shodrone.app.backoffice.console.presentation.dronetech.*;
import eapli.shodrone.app.backoffice.console.presentation.showdesigner.*;
import eapli.shodrone.app.common.console.presentation.authz.MyUserMenu;
import eapli.shodrone.usermanagement.domain.Roles;

/**
 * Main menu for the Shodrone Backoffice Console
 */
public class MainMenu extends AbstractUI {

    private static final String SEPARATOR_LABEL = "--------------";
    private static final int EXIT_OPTION = 0;

    private static final String RETURN_LABEL = "Return ";

    // USERS
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int DEACTIVATE_USER_OPTION = 3;
    private static final int ACTIVATE_USER_OPTION = 4;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 5;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int USERS_OPTION = 2;
    private static final int SETTINGS_OPTION = 3;
    private static final int SHOW_DESIGNER_MENU_OPTION = 4;
    private static final int SHOW_DRONE_TECH_MENU_OPTION = 5;
    private static final int CRM_COLLABORATOR_MENU_OPTION = 6;
    private static final int CRM_MANAGER_MENU_OPTION = 7;

    // SHOW DESIGNER
    private static final int ADD_CATEGORY_OPTION = 1;
    private static final int EDIT_FIGURE_CATEGORY_OPTION = 2;
    private static final int TOOGLE_FIGURE_CATEGORY_OPTION = 3;
    private static final int ADD_FIGURE_CATALOGUE_OPTION = 4;

    //DRONE TECH
    private static final int REGISTER_DRONE_MODEL = 1;
    private static final int ADD_DRONE_TO_INVENTORY = 2;
    private static final int REMOVE_DRONE_FROM_INVENTORY = 3;
    private static final int LIST_ACTIVE_DRONES_BY_MODEL = 4;
    private static final int ADD_DRONE_LANGUAGE = 5;
    private static final int ADD_FIGURE_LANGUAGE = 6;
    private static final int ADD_MAINTENANCE_TYPE = 7;
    private static final int LIST_MAINTENANCE_TYPES = 8;
    private static final int LIST_MAINTENANCE_TYPES_VIA_DTO = 9;
    private static final int REGISTER_DRONE_MAINTENANCE = 10;
    private static final int EDIT_MAINTENANCE_TYPE_OPTION = 11;
    private static final int VALIDATE_DRONE_LANGUAGE = 12;

    //CRM COLLABORATOR
    private static final int LIST_FIGURE_CATEGORIES_OPTION = 1;
    private static final int SEARCH_FIGURE_CATALOGUE_OPTION = 2;
    private static final int LIST_FIGURE_CATALOGUE_OPTION = 3;
    private static final int ADD_SHOW_REQUEST_OPTION = 4;
    private static final int EDIT_SHOW_REQUEST_OPTION = 5;
    private static final int LIST_SHOW_REQUEST_OPTION = 6;
    private static final int REGISTER_CUSTOMER_OPTION = 7;
    private static final int ADD_CUSTOMER_REPRESENTATIVE = 8;
    private static final int LIST_CUSTOMER_REPRESENTATIVE = 9;
    private static final int EDIT_CUSTOMER_REPRESENTATIVE = 10;
    private static final int DISABLE_CUSTOMER_REPRESENTATIVE = 11;
    private static final int CREATE_SHOW_PROPOSAL = 12;
    private static final int ADD_FIGURE_PROPOSAL = 13;
    private static final int SEND_SHOW_PROPOSAL = 14;
    private static final int ADD_VIDEO_SHOW_PROPOSAL = 15;
    private static final int ACCEPT_SHOW_PROPOSAL = 16;
    private static final int ADD_DRONES_TO_PROPOSAL = 17;

    //CRM MANAGER
    private static final int DECOMMISSION_FIGURE_OPTION = 1;
    private static final int LIST_SHOW_REQUEST_OPTION_MANAGER = 2;
    private static final int CONFIGURE_SHOW_PROPOSAL = 3;
    private static final int GENERATE_AND_VALIDATE_PROPOSAL = 4;


    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {
        return authz.session()
                .map(s -> "Shodrone Backoffice Console [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("Shodrone Backoffice Console [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(Roles.ADMIN)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(USERS_OPTION, usersMenu);
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
        }

       if (authz.isAuthenticatedUserAuthorizedTo(Roles.ADMIN, Roles.SHOW_DESIGNER)) {
           final Menu showDesignerMenu = buildShowDesignerMenu();
           mainMenu.addSubMenu(SHOW_DESIGNER_MENU_OPTION, showDesignerMenu);
       }

        if(authz.isAuthenticatedUserAuthorizedTo(Roles.ADMIN, Roles.DRONE_TECH)){
            final Menu droneTechMenu = buildDroneTechMenu();
            mainMenu.addSubMenu(SHOW_DRONE_TECH_MENU_OPTION, droneTechMenu);
        }

        if(authz.isAuthenticatedUserAuthorizedTo(Roles.ADMIN, Roles.CRM_COLLABORATOR)){
            final Menu collaboratorMenu = buildCRMCollaboratorMenu();
            mainMenu.addSubMenu(CRM_COLLABORATOR_MENU_OPTION, collaboratorMenu);
        }

        if(authz.isAuthenticatedUserAuthorizedTo(Roles.ADMIN , Roles.CRM_MANAGER)){
            final Menu crmManagerMenu = buildCRMManagerMenu();
            mainMenu.addSubMenu(CRM_MANAGER_MENU_OPTION, crmManagerMenu);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Goodbye!"));

        return mainMenu;
    }

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Admin Settings >");

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }


    private Menu buildUsersMenu() {
        final var menu = new Menu("Users >");

        menu.addItem(ADD_USER_OPTION, "Register User", new AddUserAction());
        menu.addItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction());
        menu.addItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction());
        menu.addItem(ACTIVATE_USER_OPTION,"Activate User", new ReactivateUserAction());

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildShowDesignerMenu() {
        final Menu menu = new Menu("Show Designer >");

        menu.addItem(ADD_CATEGORY_OPTION, "Add Figure Category", new AddFigureCategoryAction());
        menu.addItem(EDIT_FIGURE_CATEGORY_OPTION, "Edit Figure Category", new EditFigureCategoryAction());
        menu.addItem(TOOGLE_FIGURE_CATEGORY_OPTION, "Toogle Figure Category", new ToggleFigureCategoryAction());
        menu.addItem(ADD_FIGURE_CATALOGUE_OPTION, "Add Figures to the Catalogue", new AddFigureCatalogueAction());

        menu.addItem(EXIT_OPTION, "Return", Actions.SUCCESS);

        return menu;
    }

    private Menu buildDroneTechMenu() {
        final Menu menu = new Menu("Drone Tech >");

        menu.addItem(REGISTER_DRONE_MODEL,"Add Drone Model", new DroneModelCreationAction());
        menu.addItem(ADD_DRONE_TO_INVENTORY,"Add drone to inventory",new DroneInventoryAdditionAction());
        menu.addItem(REMOVE_DRONE_FROM_INVENTORY,"Remove drone from inventory",new DroneInventoryRemovalAction());
        menu.addItem(LIST_ACTIVE_DRONES_BY_MODEL,"List Drones By Model And Status",new ListActiveDronesByModelAction());
        menu.addItem(ADD_DRONE_LANGUAGE,"Add Drone Language",new DroneLanguageAction());
        menu.addItem(ADD_FIGURE_LANGUAGE,"Add Figure Language",new FigurePluginAction());
        menu.addItem(ADD_MAINTENANCE_TYPE,"Add Maintenance Type",new MaintenanceTypeCreationAction());
        menu.addItem(LIST_MAINTENANCE_TYPES,"List Maintenance Types",new ListAllMaintenanceTypesAction());
        menu.addItem(LIST_MAINTENANCE_TYPES_VIA_DTO,"List Maintenance Types via DTO",new ListMaintenanceTypesDTOUI()::show);
        menu.addItem(REGISTER_DRONE_MAINTENANCE,"Register Drone Maintenance",new RegisterMaintenanceRecordDroneAction());
        menu.addItem(EDIT_MAINTENANCE_TYPE_OPTION,"Edit Maintenance Type",new EditMaintenanceTypeAction());
        menu.addItem(VALIDATE_DRONE_LANGUAGE,"Validate a Drone's Programming Language",new ValidateDroneLanguageAction());
        menu.addItem(EXIT_OPTION, "Return", Actions.SUCCESS);

        return menu;
    }

    private Menu buildCRMCollaboratorMenu() {
        final Menu menu = new Menu("CRM Collaborator >");

        menu.addItem(LIST_FIGURE_CATEGORIES_OPTION, "List Figure Categories", new ListFigureCategoriesAction());
        menu.addItem(SEARCH_FIGURE_CATALOGUE_OPTION, "Search Figures Catalogue", new SearchFigureCatalogueAction());
        menu.addItem(LIST_FIGURE_CATALOGUE_OPTION, "List Public Figures in Catalogue", new ListFigureCatalogueAction());
        menu.addItem(ADD_SHOW_REQUEST_OPTION, "Add Show Request", new AddShowRequestAction());
        menu.addItem(EDIT_SHOW_REQUEST_OPTION, "Edit a Show Request", new EditShowRequestAction());
        menu.addItem(LIST_SHOW_REQUEST_OPTION, "List Show Requests", new ListShowRequestAction());
        menu.addItem(REGISTER_CUSTOMER_OPTION, "Register Customer", new CreateCustumerAction());
        menu.addItem(ADD_CUSTOMER_REPRESENTATIVE, "Add a Customer Representative", new AddCustomerRepresentativeAction());
        menu.addItem(LIST_CUSTOMER_REPRESENTATIVE, "List Customer Representatives", new ListCustomerRepresentativeAction());
        menu.addItem(EDIT_CUSTOMER_REPRESENTATIVE, "Edit a Customer Representative", new EditCustomerRepresentativeAction());
        menu.addItem(DISABLE_CUSTOMER_REPRESENTATIVE, "Disable a Customer Representative", new DisableCustomerRepresentativeAction());
        menu.addItem(CREATE_SHOW_PROPOSAL, "Create a show proposal", new CreateShowProposalAction());
        menu.addItem(ADD_FIGURE_PROPOSAL, "Add figures to a proposal", new AddFiguresToProposalAction());
        menu.addItem(SEND_SHOW_PROPOSAL, "Send Show Proposal", new SendShowProposalAction());
        menu.addItem(ADD_VIDEO_SHOW_PROPOSAL, "Add video to Show Proposal", new AddVideoToShowProposalAction());
        menu.addItem(ACCEPT_SHOW_PROPOSAL,"Accept a Show Proposal", new MarkShowProposalAcceptedAction());
        menu.addItem(ADD_DRONES_TO_PROPOSAL,"Add drones to a Show Proposal", new AddDronesToProposalAction());
        menu.addItem(EXIT_OPTION, "Return", Actions.SUCCESS);

        return menu;
    }

    private Menu buildCRMManagerMenu() {
        final Menu menu = new Menu("CRM Manager >");

        menu.addItem(DECOMMISSION_FIGURE_OPTION, "Decommission Figures", new DecommissionFigureAction());
        menu.addItem(LIST_SHOW_REQUEST_OPTION_MANAGER, "List Show Requests", new ListShowRequestAction());
        menu.addItem(CONFIGURE_SHOW_PROPOSAL, "Configure Show Proposal", new ConfigureShowProposalAction());
        menu.addItem(GENERATE_AND_VALIDATE_PROPOSAL, "Generate and Validate Proposal Document", new GenerateAndValidateProposalAction());

        menu.addItem(EXIT_OPTION, "Return", Actions.SUCCESS);

        return menu;
    }
}
