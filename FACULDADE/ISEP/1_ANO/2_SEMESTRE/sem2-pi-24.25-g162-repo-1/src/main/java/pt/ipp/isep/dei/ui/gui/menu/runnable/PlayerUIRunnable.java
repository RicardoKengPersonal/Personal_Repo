package pt.ipp.isep.dei.ui.gui.menu.runnable;

import javafx.application.Application;
import javafx.application.Platform;
import pt.ipp.isep.dei.ui.gui.menu.UI.PlayerUI;

public class PlayerUIRunnable implements Runnable {

    private static boolean javafxStarted = false;

    @Override
    public void run() {
        if (!javafxStarted) {
            javafxStarted = true;
            new Thread(() -> Application.launch(PlayerUI.class)).start();
        } else {
            Platform.runLater(() -> {
                try {
                    new PlayerUI().start(new javafx.stage.Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
