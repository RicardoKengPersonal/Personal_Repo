package pt.ipp.isep.dei.ui.gui.menu.UI;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.ipp.isep.dei.controller.SelectScenarioController;
import pt.ipp.isep.dei.domain.game.CurrentGame;
import pt.ipp.isep.dei.domain.map.Map;
import pt.ipp.isep.dei.domain.scenario.Scenario;
import pt.ipp.isep.dei.ui.gui.fx.GameScreen;

import java.util.List;

public class SelectScenarioUI {

    private final Map selectedMap;
    private final SelectScenarioController controller = new SelectScenarioController();

    public SelectScenarioUI(Map map) {
        this.selectedMap = map;
    }

    public Parent getView() {
        StackPane root = new StackPane();

        try {
            Image bg = new Image(getClass().getResource("/images/background.jpg").toExternalForm());
            BackgroundImage bgImage = new BackgroundImage(bg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, false, true));
            root.setBackground(new Background(bgImage));
        } catch (Exception e) {
            root.setBackground(
                    new Background(new BackgroundFill(Color.DARKSLATEGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        }

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));
        layout.setMaxWidth(600);

        Label title = new Label("Select a Scenario for " + selectedMap.getName());
        title.getStyleClass().add("label-title");

        ListView<Scenario> scenarioList = new ListView<>();
        List<Scenario> scenarios = controller.getScenariosForMap(selectedMap);
        scenarioList.setItems(FXCollections.observableArrayList(scenarios));
        scenarioList.setPrefHeight(180);
        scenarioList.setMaxWidth(Double.MAX_VALUE);
        scenarioList.getStyleClass().add("list-view");

        Label feedback = new Label();
        feedback.getStyleClass().add("feedback-label");

        Button startButton = new Button("Start Game");
        startButton.setDisable(true);
        startButton.getStyleClass().add("start-button");

        scenarioList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            startButton.setDisable(newVal == null);
            feedback.setText("");
        });

        startButton.setOnAction(e -> {
            Scenario selectedScenario = scenarioList.getSelectionModel().getSelectedItem();
            try {
                CurrentGame game = controller.setCurrentGame(selectedMap, selectedScenario);
                GameScreen gameScreen = new GameScreen(game);
                Scene scene = new Scene(gameScreen.getView(), Navigator.getStage().getWidth(),
                        Navigator.getStage().getHeight());
                scene.getStylesheets().add(getClass().getResource("/css/playerUI.css").toExternalForm());
                Navigator.navigateTo(scene);
            } catch (Exception ex) {
                feedback.setText("Error: " + ex.getMessage());
                feedback.getStyleClass().add("error");
            }
        });

        layout.getChildren().addAll(title, scenarioList, startButton, feedback);
        root.getChildren().add(layout);
        return root;
    }
}
