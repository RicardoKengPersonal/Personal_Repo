package pt.ipp.isep.dei.ui.gui.service;

import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import pt.ipp.isep.dei.controller.CreateMapController;
import pt.ipp.isep.dei.ui.gui.menu.UI.Navigator;

public class CreateMapUI {

    private final CreateMapController controller = new CreateMapController();

    public BorderPane getView() {

        ImageView logoImageView = new ImageView(new Image(getClass().getResourceAsStream("/images/title.png")));
        logoImageView.setFitHeight(300);
        logoImageView.setPreserveRatio(true);

        Label title = new Label("Create New Map");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        TextField nameField = new TextField();
        nameField.setPromptText("Enter a name for the map (e.g., map1.ser)");

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Write a description for your map");

        TextField scaleField = new TextField();
        scaleField.setPromptText("Enter a scale for the map tiles");

        TextField widthField = new TextField();
        widthField.setPromptText("Enter map width (positive integer)");

        TextField heightField = new TextField();
        heightField.setPromptText("Enter map height (positive integer)");

        // Estilo comum para campos
        for (TextField field : new TextField[] { nameField, descriptionField, scaleField, widthField, heightField }) {
            field.setMaxWidth(400);
            field.setMinHeight(40);
            field.setStyle("-fx-font-size: 16px;");
        }

        Button createButton = new Button("Create Map");
        Button backButton = new Button("Back");

        for (Button btn : new Button[] { createButton, backButton }) {
            btn.setMinWidth(200);
            btn.setMinHeight(45);
            btn.setStyle("-fx-font-size: 16px;");
        }

        createButton.setOnAction(e -> {
            String name = nameField.getText();
            String description = descriptionField.getText();
            String widthText = widthField.getText();
            String heightText = heightField.getText();
            String scaleText = scaleField.getText();

            try {
                int width = Integer.parseInt(widthText);
                int height = Integer.parseInt(heightText);
                double scale = Double.parseDouble(scaleText);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Do you wish to proceed?");
                alert.setContentText("Name: " + name + "\n" + "Description: " + description + "\n"
                        + "Scale: " + scale + "km\n" + "Dimensions (Tiles): " + width + "x" + height);
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    controller.createMap(name, description, scale, width, height);

                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                    infoAlert.setTitle("Success");
                    infoAlert.setHeaderText("Map created successfully!");
                    infoAlert.setContentText("The map \"" + name + "\" was created and saved.");

                    infoAlert.setOnHidden(e1 -> Navigator.goBack());

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

        VBox layout = new VBox(15, title, nameField, descriptionField, scaleField, widthField, heightField,
                createButton,
                backButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        BorderPane rootLayout = new BorderPane();

        HBox topLayout = new HBox();
        topLayout.setPadding(new Insets(10));
        topLayout.setAlignment(Pos.TOP_LEFT);
        topLayout.getChildren().add(logoImageView);

        rootLayout.setTop(topLayout);
        rootLayout.setCenter(layout);

        return rootLayout;
    }
}
