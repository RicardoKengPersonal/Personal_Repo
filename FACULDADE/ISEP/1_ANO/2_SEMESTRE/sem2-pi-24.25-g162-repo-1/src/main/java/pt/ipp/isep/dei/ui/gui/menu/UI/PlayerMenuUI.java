package pt.ipp.isep.dei.ui.gui.menu.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.ipp.isep.dei.controller.SaveGameController;
import pt.ipp.isep.dei.domain.game.CurrentGame;
import pt.ipp.isep.dei.ui.gui.fx.GameScreen;

public class PlayerMenuUI {

    private final CurrentGame currentGame;

    public PlayerMenuUI(CurrentGame currentGame) {
        this.currentGame = currentGame;
    }

    public Parent getView() {
        // Root layout
        StackPane root = new StackPane();

        // Background
        try {
            Image backgroundImage = new Image(getClass().getResource("/images/background.jpg").toExternalForm());
            BackgroundImage bgImage = new BackgroundImage(
                    backgroundImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, false, true));
            root.setBackground(new Background(bgImage));
        } catch (Exception e) {
            root.setBackground(new Background(new BackgroundFill(Color.DARKSLATEBLUE, CornerRadii.EMPTY, null)));
        }

        // VBox central com título + botões
        VBox centerBox = new VBox(30);
        centerBox.setAlignment(Pos.CENTER);

        // Título com imagem (igual ao PlayerUI)
        ImageView titleImage = new ImageView(new Image(getClass().getResource("/images/title.png").toExternalForm()));
        titleImage.setPreserveRatio(true);
        titleImage.setFitHeight(400); // menor que o do PlayerUI para dar espaço aos botões

        // Botões
        Button startBtn = new Button("Start New Game");
        Button loadBtn = new Button("Load Game");
        Button exitBtn = new Button("Exit");

        for (Button btn : new Button[] { startBtn, loadBtn, exitBtn }) {
            btn.getStyleClass().add("menu-button"); // estilo CSS
            btn.setOnMouseEntered(e -> btn.setStyle("-fx-cursor: hand;"));
        }

        Stage primaryStage = Navigator.getStage();

        startBtn.setOnAction(e -> {
            SelectMapUI selectMapUI = new SelectMapUI();
            Scene scene = new Scene(selectMapUI.getView(), primaryStage.getWidth(), primaryStage.getHeight());
            Navigator.navigateTo(scene);
        });

        loadBtn.setOnAction(e -> {
            showLoadGameDialog(primaryStage);
        });

        exitBtn.setOnAction(e -> System.exit(0));

        VBox buttonBox = new VBox(20, startBtn, loadBtn, exitBtn);
        buttonBox.setAlignment(Pos.CENTER);

        centerBox.getChildren().addAll(titleImage, buttonBox);
        root.getChildren().add(centerBox);

        return root;
    }

    // Novo método para mostrar o diálogo de carregar jogo
    private void showLoadGameDialog(Stage mainStage) {
        SaveGameController saveGameController = new SaveGameController();

        Stage dialog = new Stage();
        dialog.initOwner(mainStage);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Select a Saved Game");

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        ListView<String> listView = new ListView<>();
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        String[] files = saveGameController.listSavedGames();
        if (files != null && files.length > 0) {
            for (String file : files) {
                listView.getItems().add(file);
            }
        }

        Button loadBtn = new Button("Load Selected Game");
        loadBtn.setDisable(true);

        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> dialog.close());

        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            loadBtn.setDisable(newVal == null);
        });

        loadBtn.setOnAction(e -> {
            String selectedFile = listView.getSelectionModel().getSelectedItem();
            if (selectedFile != null) {
                try {
                    CurrentGame loadedGame = saveGameController.loadGame(selectedFile);
                    dialog.close();
                    // Avança para o ecrã do jogo com o save carregado
                    GameScreen gameScreen = new GameScreen(loadedGame);
                    Scene gameScene = new Scene(gameScreen.getView(), mainStage.getWidth(), mainStage.getHeight());
                    gameScene.getStylesheets().add(getClass().getResource("/css/playerUI.css").toExternalForm());
                    mainStage.setScene(gameScene);
                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to load game: " + ex.getMessage());
                    alert.showAndWait();
                }
            }
        });

        HBox buttonBox = new HBox(10, loadBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(new Label("Select a save file:"), listView, buttonBox);

        Scene scene = new Scene(vbox, 400, 300);
        dialog.setScene(scene);
        dialog.showAndWait();
    }
}