package pt.ipp.isep.dei.ui.gui.service;

import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.ipp.isep.dei.controller.CreateIndustryController;
import pt.ipp.isep.dei.domain.ClassType.IndustryGenerationFactor;
import pt.ipp.isep.dei.domain.ClassType.IndustryType;
import pt.ipp.isep.dei.domain.ClassType.ResourceType;
import pt.ipp.isep.dei.domain.concept.Coordinates;
import pt.ipp.isep.dei.domain.map.Map;
import pt.ipp.isep.dei.ui.gui.menu.UI.Navigator;

public class AddIndustryUI {

    private final CreateIndustryController controller = new CreateIndustryController();
    private TextArea gridArea;

    public Parent getView(Map map) {
        Label title = new Label("Add Industry");
        Label mapLabel = new Label("Selected map: " + map.getName());

        TextField coordinatexField = new TextField();
        coordinatexField.setPromptText("Enter x coordinates");

        Label dash = new Label(" - ");

        TextField coordinateyField = new TextField();
        coordinateyField.setPromptText("Enter y coordinates");

        HBox coordinatesBox = new HBox(10, coordinatexField, dash, coordinateyField);
        coordinatesBox.setAlignment(Pos.CENTER);

        ComboBox<IndustryType> industryTypeBox = new ComboBox<>();
        industryTypeBox.setItems(FXCollections.observableArrayList(controller.getIndustryTypes()));
        industryTypeBox.setPromptText("Select industry type");

        ComboBox<IndustryGenerationFactor> factorBox = new ComboBox<>();
        factorBox.setItems(FXCollections.observableArrayList(controller.getIndustryGenerationFactors()));
        factorBox.setPromptText("Select generation factor");

        ComboBox<ResourceType> resourceTypeBox = new ComboBox<>();
        resourceTypeBox.setPromptText("Select resource type");
        resourceTypeBox.setVisible(false);

        Label resultLabel = new Label();

        industryTypeBox.setOnAction(e -> {
            IndustryType selected = industryTypeBox.getValue();
            if (selected != null) {
                List<ResourceType> resources;
                if (selected == IndustryType.Primary) {
                    resources = controller.getPrimaryResourceTypes();
                } else if (selected == IndustryType.Transforming) {
                    resources = controller.getTransformativeResourceTypes();
                } else {
                    resources = List.of();
                }

                resourceTypeBox.setItems(FXCollections.observableArrayList(resources));
                resourceTypeBox.setVisible(true);
            }
        });

        Button createButton = new Button("Create Industry");
        Button backButton = new Button("Back");

        createButton.setOnAction(e -> {
            String xText = coordinatexField.getText();
            String yText = coordinateyField.getText();

            ResourceType resourceType = resourceTypeBox.getValue();
            IndustryType industryType = industryTypeBox.getValue();
            IndustryGenerationFactor factor = factorBox.getValue();

            try {
                Coordinates coordinates;
                int x = Integer.parseInt(xText);
                int y = Integer.parseInt(yText);
                coordinates = new Coordinates(x, y);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Do you wish to proceed?");
                alert.setContentText(coordinates + "\n" + "Industry Type: " + industryType + "\n"
                        + "Output: " + resourceType + "\n" + "Generation factor: " + factor);
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    controller.createIndustry(coordinates, resourceType, industryType, factor, map);
                    gridArea.setText(map.getGrid()); // Atualiza a grelha
                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                    infoAlert.setTitle("Success");
                    infoAlert.setHeaderText("Industry created successfully!");
                    infoAlert.setContentText("The industry was created and saved.");
                    infoAlert.show();
                } else {
                }
            } catch (IllegalArgumentException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(ex.getMessage());
                alert.show();
            }
        });

        backButton.setOnAction(e -> Navigator.goBack());

        VBox contentBox = new VBox(15,
                title,
                mapLabel,
                coordinatesBox,
                industryTypeBox,
                factorBox,
                resourceTypeBox,
                resultLabel);
        contentBox.setPadding(new Insets(40));
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setMaxWidth(600);

        HBox buttonBox = new HBox(20, createButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox centerVBox = new VBox(30, contentBox, buttonBox);
        centerVBox.setAlignment(Pos.CENTER);

        ImageView logoImageView = new ImageView(new Image(getClass().getResourceAsStream("/images/title.png")));
        logoImageView.setFitHeight(300);
        logoImageView.setPreserveRatio(true);

        // ➕ Grelha da map
        gridArea = new TextArea(map.getGrid());
        gridArea.setEditable(false);
        gridArea.setPrefColumnCount(20);
        gridArea.setPrefRowCount(15);
        gridArea.setStyle("-fx-font-family: monospace;");

        VBox gridBox = new VBox(new Label("Map Grid:"), gridArea);
        gridBox.setPadding(new Insets(10));
        gridBox.setAlignment(Pos.TOP_RIGHT);
        gridBox.setPrefHeight(300);
        gridBox.setPrefWidth(300);

        // Combinar logo + grelha no topo
        HBox topBox = new HBox(logoImageView);
        topBox.setSpacing(20);
        topBox.setPadding(new Insets(10));
        topBox.setAlignment(Pos.TOP_LEFT); // Logo à esquerda, grelha à direita

        BorderPane root = new BorderPane();
        root.setLeft(topBox);
        root.setCenter(centerVBox);
        root.setRight(gridBox);

        return root;
    }

}
