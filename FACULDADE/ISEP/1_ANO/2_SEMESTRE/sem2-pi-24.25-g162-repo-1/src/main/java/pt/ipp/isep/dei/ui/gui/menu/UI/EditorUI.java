package pt.ipp.isep.dei.ui.gui.menu.UI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.ui.gui.service.CreateMapUI;
import pt.ipp.isep.dei.ui.gui.service.CreateScenarioUI;
import pt.ipp.isep.dei.ui.gui.service.EditMapUI;

public class EditorUI extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Navigator.setStage(primaryStage);
        showMainMenu();
    }

    private void showMainMenu() {
        primaryStage.setTitle("Editor Interface");
        primaryStage.setFullScreen(true); // Ativar fullscreen

        // Imagem no canto superior esquerdo
        ImageView logoImageView = new ImageView(new Image(getClass().getResourceAsStream("/images/title.png")));
        logoImageView.setFitHeight(300);
        logoImageView.setPreserveRatio(true);

        // Label principal
        Label label = new Label("Welcome Editor!");
        label.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        // Botões
        Button createMapBtn = new Button("Create map");
        Button editMapBtn = new Button("Edit map");
        Button createScenarioBtn = new Button("Create scenario");
        Button playBtn = new Button("Start simulator");

        for (Button btn : new Button[] { createMapBtn, editMapBtn, createScenarioBtn, playBtn }) {
            btn.setMinWidth(250);
            btn.setMinHeight(60);
            btn.setStyle("-fx-font-size: 18px;");
        }

        createMapBtn.setOnAction(e -> {
            CreateMapUI createMapUI = new CreateMapUI();
            Scene scene = new Scene(createMapUI.getView(), 600, 400);
            Navigator.navigateTo(scene);
        });

        editMapBtn.setOnAction(e -> {
            EditMapUI editMapBtnUI = new EditMapUI();
            Scene scene = new Scene(editMapBtnUI.getView(), 600, 400);
            Navigator.navigateTo(scene);
        });

        createScenarioBtn.setOnAction(e -> {
            CreateScenarioUI createScenarioUI = new CreateScenarioUI();
            Scene scene;
            try {
                scene = new Scene(createScenarioUI.getView(), 600, 400);
            } catch (Exception e1) {
                return;
            }
            Navigator.navigateTo(scene);
        });

        playBtn.setOnAction(e -> {
            SelectMapUI selectMapUI = new SelectMapUI();
            Scene scene = new Scene(selectMapUI.getView(), 600, 400);
            Navigator.navigateTo(scene);
        });

        // Layout central com os botões
        VBox buttonLayout = new VBox(20, label, createMapBtn, editMapBtn, createScenarioBtn, playBtn);
        buttonLayout.setAlignment(Pos.CENTER);

        // Layout superior com a imagem no canto superior esquerdo
        BorderPane rootLayout = new BorderPane();
        HBox topLayout = new HBox();
        topLayout.setPadding(new Insets(10));
        topLayout.setAlignment(Pos.TOP_LEFT);
        topLayout.getChildren().add(logoImageView);

        rootLayout.setTop(topLayout);
        rootLayout.setCenter(buttonLayout);

        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void returnToMainMenu() {
        showMainMenu();
    }
}
