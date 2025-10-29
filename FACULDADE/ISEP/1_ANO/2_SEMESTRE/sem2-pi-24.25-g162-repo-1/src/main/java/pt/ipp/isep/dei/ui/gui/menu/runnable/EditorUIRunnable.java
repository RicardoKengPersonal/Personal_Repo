package pt.ipp.isep.dei.ui.gui.menu.runnable;

import javafx.application.Application;
import javafx.application.Platform;
import pt.ipp.isep.dei.ui.gui.menu.UI.EditorUI;

public class EditorUIRunnable implements Runnable {

    private static boolean javafxStarted = false;

    @Override
    public void run() {
        if (!javafxStarted) {
            javafxStarted = true;
            new Thread(() -> Application.launch(EditorUI.class)).start();
        } else {
            Platform.runLater(() -> {
                try {
                    new EditorUI().start(new javafx.stage.Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
