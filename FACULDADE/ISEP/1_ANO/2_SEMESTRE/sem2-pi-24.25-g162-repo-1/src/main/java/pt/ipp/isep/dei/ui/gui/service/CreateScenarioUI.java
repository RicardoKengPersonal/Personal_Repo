package pt.ipp.isep.dei.ui.gui.service;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import pt.ipp.isep.dei.controller.CreateScenarioController;
import pt.ipp.isep.dei.domain.ClassType.LocomotiveType;
import pt.ipp.isep.dei.domain.ClassType.ResourceType;
import pt.ipp.isep.dei.domain.map.Map;
import pt.ipp.isep.dei.repository.MapRepository;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.ui.gui.menu.UI.Navigator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateScenarioUI {

    private final CreateScenarioController scenarioController = new CreateScenarioController();
    private final MapRepository mapRepo = Repositories.getInstance().getMapRepository();

    public Parent getView() {
        Label title = new Label("Create New Scenario");
        Label mapLabel = new Label("Select a map:");

        List<Map> maps = mapRepo.getMapsList();
        ListView<Map> mapListView = new ListView<>(FXCollections.observableArrayList(maps));
        mapListView.setPrefHeight(100);

        TextField nameField = new TextField();
        nameField.setPromptText("Scenario name");

        TextArea descriptionArea = new TextArea();
        descriptionArea.setPromptText("Scenario description");

        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setPromptText("Start date");

        DatePicker endDatePicker = new DatePicker();
        endDatePicker.setPromptText("End date");

        TextField moneyField = new TextField();
        moneyField.setPromptText("Initial money (e.g., 10000)");

        // Industry Types
        ArrayList<ResourceType> availableIndustries = scenarioController.getIndustryTypes();
        ListView<ResourceType> industryList = new ListView<>();
        industryList.getItems().addAll(availableIndustries);
        industryList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        industryList.setPrefHeight(100);

        // Locomotive Types (Mantido)
        List<LocomotiveType> availableLocosTypes = scenarioController.getLocomotiveTypes();
        ListView<LocomotiveType> locoList = new ListView<>();
        locoList.getItems().addAll(availableLocosTypes);
        locoList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        locoList.setPrefHeight(100);

        Button createButton = new Button("Create Scenario");
        Button backButton = new Button("Back");
        Text resultMessage = new Text();

        createButton.setOnAction(e -> {
            Map selectedMap = mapListView.getSelectionModel().getSelectedItem();
            String name = nameField.getText();
            String description = descriptionArea.getText();
            LocalDate startDate = startDatePicker.getValue();
            LocalDate endDate = endDatePicker.getValue();

            if (selectedMap == null) {
                resultMessage.setText("You must select a map.");
                return;
            }

            double initialMoney;
            try {
                initialMoney = Double.parseDouble(moneyField.getText());
            } catch (NumberFormatException ex) {
                resultMessage.setText("Initial money must be a valid number.");
                return;
            }

            if (name.isBlank() || description.isBlank() || startDate == null || endDate == null) {
                resultMessage.setText("Please fill in all required fields.");
                return;
            }

            if (endDate.isBefore(startDate)) {
                resultMessage.setText("End date must be after start date.");
                return;
            }

            ArrayList<ResourceType> selectedIndustries = new ArrayList<>(
                    industryList.getSelectionModel().getSelectedItems());
            ArrayList<LocomotiveType> selectedLocos = new ArrayList<>(locoList.getSelectionModel().getSelectedItems());

            try {
                scenarioController.createScenario(
                        selectedMap,
                        name,
                        description,
                        new ArrayList<>(), // No historical events for now
                        selectedLocos,
                        selectedIndustries,
                        startDate,
                        endDate, initialMoney,
                        "");
                resultMessage.setText("Scenario created successfully!");

            } catch (Exception ex) {
                resultMessage.setText("Error: " + ex.getMessage());
            }
        });

        backButton.setOnAction(e -> Navigator.goBack());

        VBox contentBox = new VBox(15,
                title,
                mapLabel, mapListView,
                nameField,
                descriptionArea,
                startDatePicker,
                endDatePicker,
                moneyField,
                new Label("Select Industry Types:"), industryList,
                new Label("Select Locomotive Types:"), locoList,
                createButton,
                backButton,
                resultMessage);
        contentBox.setPadding(new Insets(40));
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setMaxWidth(600);

        // Imagem à esquerda
        ImageView logoImageView = new ImageView(new Image(getClass().getResourceAsStream("/images/title.png")));
        logoImageView.setFitHeight(300);
        logoImageView.setPreserveRatio(true);
        HBox topBox = new HBox(logoImageView);
        topBox.setAlignment(Pos.TOP_LEFT);
        topBox.setPadding(new Insets(10));

        // Layout principal
        BorderPane root = new BorderPane();
        root.setLeft(topBox);
        root.setCenter(contentBox);

        return root;
    }
}
