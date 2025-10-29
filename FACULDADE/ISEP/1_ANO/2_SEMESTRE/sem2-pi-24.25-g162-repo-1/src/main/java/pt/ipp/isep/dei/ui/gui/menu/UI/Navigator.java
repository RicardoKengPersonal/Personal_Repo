package pt.ipp.isep.dei.ui.gui.menu.UI;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Stack;

public class Navigator {

    private static Stage primaryStage;
    private static final Stack<Scene> history = new Stack<>();

    private Navigator() {
    }

    public static void setStage(Stage stage) {
        primaryStage = stage;
    }

    public static void navigateTo(Scene scene) {
        if (primaryStage.getScene() != null) {
            history.push(primaryStage.getScene());
        }
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
    }

    public static void goBack() {
        if (!history.isEmpty()) {
            Scene previousScene = history.pop();
            primaryStage.setScene(previousScene);
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("");
        }
    }

    public static void resetHistory() {
        history.clear();
    }

    public static Stage getStage() {
        return primaryStage;
    }
}
