package pt.ipp.isep.dei.ui.gui.menu.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pt.ipp.isep.dei.controller.LoadGameController;
import pt.ipp.isep.dei.domain.game.CurrentGame;
import pt.ipp.isep.dei.ui.gui.fx.GameScreen;
import pt.isep.lei.esoft.auth.UserSession;

public class LoadGameUI {

    private final LoadGameController controller = new LoadGameController();

    public Parent getView() {
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 400);

        // Background (image or fallback color)
        try {
            Image backgroundImage = new Image(getClass().getResource("/images/background.jpg").toExternalForm());

            BackgroundImage bgImage = new BackgroundImage(
                    backgroundImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, true, false));

            root.setBackground(new Background(bgImage));
        } catch (Exception e) {
            root.setBackground(
                    new Background(new BackgroundFill(Color.DARKSLATEGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        }

        // Center content
        VBox centerBox = new VBox(15);
        centerBox.setAlignment(Pos.CENTER);

        Label title = new Label("Select a Saved Game");
        title.setFont(new Font("Arial", 24));
        title.setTextFill(Color.WHITE);

        ListView<String> gameList = new ListView<>();
        gameList.setPrefHeight(200);
        gameList.setPrefWidth(300);

        Button loadBtn = new Button("Load Selected Game");
        loadBtn.setFont(new Font(16));

        Label feedback = new Label();
        feedback.setTextFill(Color.ORANGERED);

        // Load saved games
        UserSession session = controller.getCurrentUser();
        String[] games = controller.listSavedGames(session);

        if (games != null && games.length > 0) {
            gameList.getItems().addAll(games);
        } else {
            feedback.setText("No saved games found.");
            loadBtn.setDisable(true);
        }

        // Load button action
        loadBtn.setOnAction(e -> {
            String selected = gameList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                try {
                    CurrentGame game = controller.loadGame(session, selected);
                    feedback.setText("Game loaded successfully!");
                    GameScreen gameScreen = new GameScreen(game);
                    Scene scene = new Scene(gameScreen.getView(), 600, 400);
                    Navigator.navigateTo(scene);
                } catch (Exception ex) {
                    feedback.setText("Error loading game: " + ex.getMessage());
                }
            }
        });

        centerBox.getChildren().addAll(title, gameList, loadBtn, feedback);
        root.setCenter(centerBox);

        return root;
    }
}
