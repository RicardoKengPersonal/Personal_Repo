package eapli.shodrone.app.testing.console.presentation;

import eapli.framework.actions.menu.Menu;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

public class MainMenu extends AbstractUI {

    private static final int TEST_SHOW_OPTION = 1;
    private static final int EXIT_OPTION = 0;

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    @Override
    public boolean doShow() {
        final Menu menu = new Menu();

        menu.addItem(TEST_SHOW_OPTION, "Show Testing", new RunSimulationAction());
        menu.addItem(EXIT_OPTION, "Exit", () -> false);

        final MenuRenderer renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);

        boolean exitSelected = false;
        while (!exitSelected) {
            exitSelected = !renderer.render();
        }

        return true;
    }

    @Override
    public String headline() {
        return "Testing Console";
    }
}
