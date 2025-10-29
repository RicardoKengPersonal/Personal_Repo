package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

/**
 * The main class for starting the GUI application.
 */
public class Main extends Application {

    /**
     * Starts the GUI application.
     *
     * @param primaryStage the primary stage
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("Integrative Project - Group 031");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method for launching the application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        for (String arg : args) {
            if (arg.equalsIgnoreCase("-b")) {
                Bootstrap bootstrap = new Bootstrap();
                bootstrap.run();
            }
        }
        launch(args);
        Repositories.save();
    }
}
