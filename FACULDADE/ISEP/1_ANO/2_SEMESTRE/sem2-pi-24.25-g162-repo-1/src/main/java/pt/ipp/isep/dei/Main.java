package pt.ipp.isep.dei;

import pt.ipp.isep.dei.ui.gui.menu.UI.MainMenuUI;

public class Main {

    public static void main(String[] args) {
        System.setProperty("prism.order", "sw");
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();

        try {
            MainMenuUI menu = new MainMenuUI();
            menu.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}