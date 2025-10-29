package pt.ipp.isep.dei.ui.gui.service;

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
import pt.ipp.isep.dei.controller.CreateCityController;
import pt.ipp.isep.dei.domain.concept.Coordinates;
import pt.ipp.isep.dei.domain.map.Map;
import pt.ipp.isep.dei.domain.objects.HouseBlock;
import pt.ipp.isep.dei.ui.gui.menu.UI.Navigator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddCityUI {

    private final CreateCityController controller = new CreateCityController();
    private TextArea gridArea;

    public Parent getView(Map map) {
        Label title = new Label("Add City");
        Label mapLabel = new Label("Selected map: " + map.getName());

        TextField coordinatexField = new TextField();
        coordinatexField.setPromptText("Enter x coordinates");

        Label dash = new Label(" - ");

        TextField coordinateyField = new TextField();
        coordinateyField.setPromptText("Enter y coordinates");

        HBox coordinatesBox = new HBox(10, coordinatexField, dash, coordinateyField);
        coordinatesBox.setAlignment(Pos.CENTER);

        TextField nameField = new TextField();
        nameField.setPromptText("Enter city name");

        ComboBox<String> houseBlockChoice = new ComboBox<>(FXCollections.observableArrayList("Yes", "No"));
        houseBlockChoice.setPromptText("Add HouseBlocks automatically?");

        TextField numBlocksField = new TextField();
        numBlocksField.setPromptText("Number of HouseBlocks");
        numBlocksField.setDisable(true);

        Button addManualBlockBtn = new Button("Add HouseBlock");
        addManualBlockBtn.setDisable(true);

        List<HouseBlock> manualHouseBlocks = new ArrayList<>();

        Label statusLabel = new Label();

        // Controla habilitação dos campos baseado na escolha do usuário
        houseBlockChoice.setOnAction(e -> {
            boolean isAuto = "Yes".equalsIgnoreCase(houseBlockChoice.getValue());
            numBlocksField.setDisable(!isAuto);
            addManualBlockBtn.setDisable(isAuto);
        });

        Button createButton = new Button("Create City");
        createButton.setOnAction(e -> {
            String coordxInput = coordinatexField.getText();
            String coordyInput = coordinateyField.getText();
            String cityName = nameField.getText();

            Coordinates coordinates;
            try {
                coordinates = new Coordinates(Integer.parseInt(coordxInput), Integer.parseInt(coordyInput));
            } catch (Exception ex) {
                showAlert("Invalid coordinate format.");
                return;
            }

            if (houseBlockChoice.getValue() == null) {
                showAlert("Please select whether to add house blocks automatically.");
                return;
            }

            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirmation");
            confirmAlert.setHeaderText("Do you wish to proceed?");
            String content = "City Name: " + cityName + "\nCoordinates: " + coordinates + "\n";
            if ("Yes".equalsIgnoreCase(houseBlockChoice.getValue())) {
                content += "HouseBlocks (auto): " + numBlocksField.getText();
            } else {
                content += "HouseBlocks (manual): " + manualHouseBlocks.size();
            }
            confirmAlert.setContentText(content);

            Optional<ButtonType> result = confirmAlert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    if ("Yes".equalsIgnoreCase(houseBlockChoice.getValue())) {
                        int numberOfBlocks = Integer.parseInt(numBlocksField.getText().trim());
                        controller.createCityAuto(cityName.trim(), coordinates, numberOfBlocks, map);
                        statusLabel.setText("City created with " + numberOfBlocks + " house blocks.");
                    } else {
                        // Loop para adicionar casas manualmente antes de criar a city
                        manualHouseBlocks.clear(); // Limpa lista para evitar duplicatas
                        boolean adding = true;
                        while (adding) {
                            TextInputDialog dialog = new TextInputDialog();
                            dialog.setTitle("Add House Block");
                            dialog.setHeaderText("Add manual house block coordinates");
                            dialog.setContentText("Enter coordinates (x-y) or leave empty to finish:");

                            Optional<String> input = dialog.showAndWait();
                            if (input.isPresent()) {
                                String coords = input.get().trim();
                                if (coords.isEmpty()) {
                                    adding = false; // Usuário terminou de adicionar casas
                                } else {
                                    try {
                                        String[] parts = coords.split("-");
                                        if (parts.length != 2)
                                            throw new IllegalArgumentException("Coordinates must be in x-y format.");

                                        int x = Integer.parseInt(parts[0].trim());
                                        int y = Integer.parseInt(parts[1].trim());
                                        manualHouseBlocks.add(new HouseBlock(new Coordinates(x, y)));
                                    } catch (Exception ex) {
                                        showAlert("Invalid coordinates format. Please enter as x-y, e.g. 3-4.");
                                    }
                                }
                            } else {
                                adding = false; // Usuário cancelou diálogo
                            }
                        }

                        if (manualHouseBlocks.isEmpty()) {
                            showAlert("No house blocks were added manually. City creation canceled.");
                            return;
                        }

                        // Cria a city com as casas manuais adicionadas
                        controller.createCityManualy(cityName.trim(), coordinates, new ArrayList<>(manualHouseBlocks),
                                map);

                        statusLabel.setText("City created with " + manualHouseBlocks.size() + " manual house blocks.");
                    }
                    gridArea.setText(map.getGrid()); // Atualiza grelha
                } catch (Exception ex) {
                    showAlert("Error creating city: " + ex.getMessage());
                }
            }

        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> Navigator.goBack());

        VBox contentBox = new VBox(15,
                title,
                mapLabel,
                coordinatesBox,
                nameField,
                houseBlockChoice,
                numBlocksField,
                addManualBlockBtn,
                createButton,
                backButton,
                statusLabel);
        contentBox.setPadding(new Insets(40));
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setMaxWidth(600);

        // Grelha do mapa à direita
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

        ImageView logoImageView = new ImageView(new Image(getClass().getResourceAsStream("/images/title.png")));
        logoImageView.setFitHeight(300);
        logoImageView.setPreserveRatio(true);

        HBox topBox = new HBox(logoImageView);
        topBox.setSpacing(20);
        topBox.setPadding(new Insets(10));
        topBox.setAlignment(Pos.TOP_LEFT); // Logo à esquerda, grelha à direita

        BorderPane root = new BorderPane();
        root.setLeft(topBox);
        root.setCenter(contentBox);
        root.setRight(gridBox);

        return root;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
