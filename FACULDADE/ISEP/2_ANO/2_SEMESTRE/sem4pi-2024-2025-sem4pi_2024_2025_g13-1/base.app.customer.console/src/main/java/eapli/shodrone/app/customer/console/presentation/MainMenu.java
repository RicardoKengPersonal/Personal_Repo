package eapli.shodrone.app.customer.console.presentation;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import eapli.shodrone.app.customer.console.application.ClientSession;

public class MainMenu extends AbstractUI {


    private static final int CUSTOMER_MENU_OPTION = 1;
    private static final int LOGOUT_OPTION = 2;

    private static final int EXIT_OPTION = 0;

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    @Override
    public String headline() {
        return "Customer Console";
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        if (ClientSession.getInstance().isAuthenticated()) {
            final Menu customerMenu = buildCustomerMenu();
            mainMenu.addSubMenu(CUSTOMER_MENU_OPTION, customerMenu);
            mainMenu.addItem(LOGOUT_OPTION, "Logout", new LogoutAction());
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Goodbye!"));

        return mainMenu;
    }

    private Menu buildCustomerMenu() {
        final Menu menu = new Menu("Customer >");
        menu.addItem(1, "Analyse Proposal", new AnalyseProposalAction());
        menu.addItem(2, "Accept/Reject Proposal", new AcceptProposalAction());
        menu.addItem(3, "Check shows dates", new CheckShowsDatesAction());
        menu.addItem(4, "Get show info", new GetShowInfoAction());
        menu.addItem(EXIT_OPTION, "Return", Actions.SUCCESS);
        return menu;
    }
}