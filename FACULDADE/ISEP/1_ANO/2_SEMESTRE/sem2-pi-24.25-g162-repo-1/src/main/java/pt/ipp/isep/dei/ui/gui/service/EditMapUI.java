package pt.ipp.isep.dei.ui.gui.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.ipp.isep.dei.controller.EditMapController;
import pt.ipp.isep.dei.domain.map.Map;
import pt.ipp.isep.dei.ui.gui.menu.UI.Navigator;

import java.util.Optional;

public class EditMapUI {

    private final EditMapController controller = new EditMapController();
    private final ObservableList<Map> observableMaps = FXCollections.observableArrayList();

    public BorderPane getView() {
        Label title = new Label("Edit Map");

        ImageView logoImageView = new ImageView(new Image(getClass().getResourceAsStream("/images/title.png")));
        logoImageView.setFitHeight(300);
        logoImageView.setPreserveRatio(true);

        observableMaps.setAll(controller.getMapsList());
        ListView<Map> mapListView = new ListView<>(observableMaps);
        mapListView.setPrefHeight(150);
        mapListView.setMaxWidth(400);

        // Botões
        Button addIndustryBtn = new Button("Add Industry");
        Button addCityBtn = new Button("Add City");
        Button deleteMapBtn = new Button("Delete Map"); // <-- Novo botão
        Button backBtn = new Button("Back");

        addIndustryBtn.setDisable(true);
        addCityBtn.setDisable(true);
        deleteMapBtn.setDisable(true); // Também só é ativado se um mapa estiver selecionado

        mapListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            boolean hasSelection = newVal != null;
            addIndustryBtn.setDisable(!hasSelection);
            addCityBtn.setDisable(!hasSelection);
            deleteMapBtn.setDisable(!hasSelection);
        });

        addIndustryBtn.setOnAction(e -> {
            Map selectedMap = mapListView.getSelectionModel().getSelectedItem();
            if (selectedMap != null) {
                AddIndustryUI addIndustryUI = new AddIndustryUI();
                Scene scene = new Scene(addIndustryUI.getView(selectedMap), 600, 400);
                Navigator.navigateTo(scene);
            }
        });

        addCityBtn.setOnAction(e -> {
            Map selectedMap = mapListView.getSelectionModel().getSelectedItem();
            if (selectedMap != null) {
                AddCityUI addCityUI = new AddCityUI();
                Scene scene = new Scene(addCityUI.getView(selectedMap), 600, 400);
                Navigator.navigateTo(scene);
            }
        });

        deleteMapBtn.setOnAction(e -> {
            Map selectedMap = mapListView.getSelectionModel().getSelectedItem();
            if (selectedMap != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Do you wish to proceed?");
                alert.setContentText("Delete map " + selectedMap.getName() + "?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    controller.deleteMap(selectedMap);
                    observableMaps.remove(selectedMap);
                } else {
                }
            }
        });

        backBtn.setOnAction(e -> Navigator.goBack());

        // HBox com botões
        HBox buttonsBox = new HBox(15, addIndustryBtn, addCityBtn, deleteMapBtn,
                backBtn);
        buttonsBox.setAlignment(Pos.CENTER);

        VBox contentBox = new VBox(15, title, mapListView,
                buttonsBox);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(30));
        contentBox.setMaxWidth(500);

        VBox centeredBox = new VBox(contentBox);
        centeredBox.setAlignment(Pos.CENTER);

        BorderPane rootLayout = new BorderPane();

        HBox topLayout = new HBox();
        topLayout.setPadding(new Insets(10));
        topLayout.setAlignment(Pos.TOP_LEFT);
        topLayout.getChildren().add(logoImageView);

        rootLayout.setTop(topLayout);
        rootLayout.setCenter(centeredBox);

        return rootLayout;
    }
}
