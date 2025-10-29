package pt.ipp.isep.dei.ui.gui.menu.UI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Modality;
import pt.ipp.isep.dei.domain.game.CurrentGame;
import pt.ipp.isep.dei.controller.SaveGameController;
import java.io.File;

public class PlayerUI extends Application {

    // Store the current game instance
    private CurrentGame currentGame;
    private final SaveGameController saveGameController = new SaveGameController();

    @Override
    public void start(Stage primaryStage) {
        Navigator.setStage(primaryStage);

        primaryStage.setTitle("Railroad Simulator");

        // Root layout
        StackPane root = new StackPane();

        // Background image (substitui se quiser uma nova)
        Image backgroundImage = new Image(getClass().getResource("/images/background.jpg").toExternalForm());
        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, false, true));
        root.setBackground(new Background(bgImage));

        // VBox central (título + botão)
        VBox centerBox = new VBox(40); // Espaço maior entre os elementos
        centerBox.setAlignment(Pos.CENTER);

        // Título maior
        ImageView titleImage = new ImageView(new Image(getClass().getResource("/images/title.png").toExternalForm()));
        titleImage.setPreserveRatio(true);
        titleImage.setFitHeight(500); // Dobro do anterior (250 → 500)

        // Botão "Start" maior
        Button startButton = new Button("Start");
        startButton.getStyleClass().add("start-button");

        startButton.setOnAction(e -> {
            // Initialize the actual CurrentGame instance if not already loaded
            if (currentGame == null) {
                // TODO: Replace with actual logic to create a new game
                // Map map = ...;
                // Scenario scenario = ...;
                // currentGame = new CurrentGame(map, scenario);
            }
            PlayerMenuUI playerMenuUI = new PlayerMenuUI(currentGame); // Pass the currentGame to the next UI
            Scene scene = new Scene(playerMenuUI.getView(), primaryStage.getWidth(), primaryStage.getHeight());
            scene.getStylesheets().add(getClass().getResource("/css/playerUI.css").toExternalForm());
            Navigator.navigateTo(scene);
        });

        centerBox.getChildren().addAll(titleImage, startButton);
        root.getChildren().add(centerBox);

        // Cena
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/playerUI.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
    }

    private void showLoadGameDialog(Stage owner) {
        Stage dialog = new Stage();
        dialog.initOwner(owner);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Select a Saved Game");

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new javafx.geometry.Insets(20));

        ListView<String> listView = new ListView<>();
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Usar SaveGameController para listar saves corretamente
        String[] files = saveGameController.listSavedGames();
        if (files != null && files.length > 0) {
            for (String file : files) {
                listView.getItems().add(file);
            }
        }

        Button loadBtn = new Button("Load Selected Game");
        loadBtn.setDisable(true);

        Button backBtn = new Button("Back to Main Menu");
        backBtn.setOnAction(e -> {
            dialog.close();
            // Voltar ao ecrã principal
            Stage stage = (Stage) owner;
            start(stage);
        });

        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            loadBtn.setDisable(newVal == null);
        });

        loadBtn.setOnAction(e -> {
            String selectedFile = listView.getSelectionModel().getSelectedItem();
            if (selectedFile != null) {
                try {
                    // Usar SaveGameController para carregar o jogo
                    CurrentGame loadedGame = saveGameController.loadGame(selectedFile);
                    currentGame = loadedGame;
                    showAlert("Game Loaded", "Game was loaded successfully.");
                    dialog.close();
                    // Optionally, navigate directly to the game or menu with the loaded game
                    // PlayerMenuUI playerMenuUI = new PlayerMenuUI(currentGame);
                    // Scene scene = new Scene(playerMenuUI.getView(), owner.getWidth(), owner.getHeight());
                    // scene.getStylesheets().add(getClass().getResource("/css/playerUI.css").toExternalForm());
                    // Navigator.navigateTo(scene);
                } catch (Exception ex) {
                    showAlert("Error", "Failed to load game: " + ex.getMessage());
                }
            }
        });

        HBox buttonBox = new HBox(10, loadBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(listView, buttonBox);

        Scene scene = new Scene(vbox, 400, 300);
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}