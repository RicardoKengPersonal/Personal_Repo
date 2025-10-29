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
import pt.ipp.isep.dei.controller.SelectMapController;
import pt.ipp.isep.dei.domain.map.Map;

public class SelectMapUI {

    private final SelectMapController controller = new SelectMapController();

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

        Label title = new Label("Select a Map");
        title.getStyleClass().add("label-title");

        ListView<Map> mapList = new ListView<>();
        mapList.setItems(FXCollections.observableArrayList(controller.getMapsList()));
        mapList.setPrefHeight(180);
        mapList.setMaxWidth(Double.MAX_VALUE);
        mapList.getStyleClass().add("list-view");

        Button nextButton = new Button("Next");
        nextButton.setDisable(true);
        nextButton.getStyleClass().add("start-button");

        mapList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            nextButton.setDisable(newVal == null);
        });

        nextButton.setOnAction(e -> {
            Map selected = mapList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                SelectScenarioUI scenarioUI = new SelectScenarioUI(selected);
                Scene scene = new Scene(scenarioUI.getView(), Navigator.getStage().getWidth(),
                        Navigator.getStage().getHeight());
                scene.getStylesheets().add(getClass().getResource("/css/playerUI.css").toExternalForm());
                Navigator.navigateTo(scene);
            }
        });

        layout.getChildren().addAll(title, mapList, nextButton);
        root.getChildren().add(layout);
        return root;
    }

    public Scene createScene() {
        Scene scene = new Scene(getView());
        scene.getStylesheets().add(getClass().getResource("/css/playerUI.css").toExternalForm());
        return scene;
    }
}
