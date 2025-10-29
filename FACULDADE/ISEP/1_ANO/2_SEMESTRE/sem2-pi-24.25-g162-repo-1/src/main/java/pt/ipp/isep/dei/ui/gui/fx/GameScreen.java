package pt.ipp.isep.dei.ui.gui.fx;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.ipp.isep.dei.controller.GameController;
import pt.ipp.isep.dei.domain.ClassType;
import pt.ipp.isep.dei.domain.ClassType.CargoType;
import pt.ipp.isep.dei.domain.ClassType.LineType;
import pt.ipp.isep.dei.domain.ClassType.Orientation;
import pt.ipp.isep.dei.domain.ClassType.StationType;
import pt.ipp.isep.dei.domain.concept.Cargo;
import pt.ipp.isep.dei.domain.concept.Coordinates;
import pt.ipp.isep.dei.domain.concept.PointOfRoute;
import pt.ipp.isep.dei.domain.concept.Route;
import pt.ipp.isep.dei.domain.concept.Tile;
import pt.ipp.isep.dei.domain.game.CurrentGame;
import pt.ipp.isep.dei.domain.objects.*;
import pt.ipp.isep.dei.domain.ClassType.CarriageType;

import java.time.LocalDate;
import java.util.Map;
import pt.ipp.isep.dei.domain.helpers.CargoUtils;
import pt.ipp.isep.dei.domain.scenario.Scenario;


import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class GameScreen {

    private final GameController controller;
    private Canvas canvas;
    private Pane objectsLayer;
    private Group zoomGroup;
    private Pane railwayPane;
    private Label userMoney;
    private Label infoLabel;
    private ListView<BuildingUpgrade> ownedList;
    private boolean isVisible;
    private boolean isSimulationRunning = false;
    private Timeline simulationTimeline;
    private Label dayCounterLabel;
    private int currentDay = 0;
    private LocalDate currentSimulationDate;
    private AnimationTimer simulationTimer;
    private long lastUpdateTime = 0;
    private long dayDurationNs = 1_000_000_000; // 1 second in nanoseconds (default speed)
    private Label speedInfoLabel;
    private double simulationSpeed = 1.0;
    private final Set<String> announcedLocomotives = new HashSet<>();



    private static final int TILE_SIZE = 40;

    private static class MapObject {
        int x, y;
        Image sprite;

        MapObject(int x, int y, Image sprite) {
            this.x = x;
            this.y = y;
            this.sprite = sprite;
        }
    }

    private final List<MapObject> mapObjects = new ArrayList<>();

    // Store reference to current game for save/load
    private final CurrentGame currentGame;

    public GameScreen(CurrentGame currentGame) throws IOException {
        this.controller = new GameController(currentGame);
        this.currentGame = currentGame; // Save reference for save/load
        currentSimulationDate = controller.getCurrentScenario().getStartingDate();
    }

    public Parent getView() {
        VBox root = new VBox();

        StackPane gamePane = createGamePane();
        Pane infoPane = createInfoPane();

        StackPane gameWithHud = new StackPane(gamePane);

        root.getChildren().addAll(gameWithHud, infoPane);

        root.heightProperty().addListener((obs, oldVal, newVal) -> {
            double total = newVal.doubleValue();
            gameWithHud.setPrefHeight(total * 2 / 3);
            infoPane.setPrefHeight(total * 1 / 3);
        });

        startAnimationTimer(gameWithHud);

        return root;
    }

    private StackPane createGamePane() {
        StackPane gamePane = new StackPane();
        gamePane.setStyle("-fx-background-color: black;");

        zoomGroup = new Group();
        canvas = new Canvas();
        objectsLayer = new Pane();
        railwayPane = new Pane();
        railwayPane.setVisible(isVisible);

        zoomGroup.getChildren().addAll(canvas, railwayPane, objectsLayer);
        gamePane.getChildren().add(zoomGroup);

        Label mouseCoordsLabel = createMouseCoordsLabel();
        gamePane.getChildren().add(mouseCoordsLabel);

        // Bind sizes
        bindCanvasSizeToGroup(canvas, objectsLayer, zoomGroup, gamePane);

        // Setup zoom and drag
        setupZoomAndDrag();

        // Setup mouse coordinate tracking
        setupMouseCoordinateTracking(gamePane, mouseCoordsLabel);

        // Initialize currentSimulationDate if not set
        if (currentSimulationDate == null) {
            currentSimulationDate = controller.getCurrentScenario().getStartingDate();
        }

        // Format the date using currentSimulationDate (not local currentDate)
        String dateText = String.format("Year: %d | Month: %d | Day: %d",
                currentSimulationDate.getYear(),
                currentSimulationDate.getMonthValue(),
                currentSimulationDate.getDayOfMonth());

        // Add day counter label here, aligned top-right
        dayCounterLabel = new Label(dateText);
        dayCounterLabel.setStyle("-fx-background-color: rgba(0,0,0,0.6); -fx-text-fill: white; -fx-padding: 5; -fx-font-size: 14px;");
        StackPane.setAlignment(dayCounterLabel, Pos.TOP_RIGHT);
        StackPane.setMargin(dayCounterLabel, new Insets(10, 10, 0, 0));
        gamePane.getChildren().add(dayCounterLabel);

        return gamePane;
    }


    private Label createMouseCoordsLabel() {
        Label mouseCoordsLabel = new Label();
        mouseCoordsLabel.setStyle(
                "-fx-background-color: rgb(0, 0, 0); -fx-text-fill: white; -fx-padding: 2 4 2 4; -fx-font-size: 12;");
        mouseCoordsLabel.setMouseTransparent(true);
        return mouseCoordsLabel;
    }

    private void bindCanvasSizeToGroup(Canvas canvas, Pane objectsLayer, Group zoomGroup, StackPane gamePane) {
        zoomGroup.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
            canvas.setWidth(newVal.getWidth());
            canvas.setHeight(newVal.getHeight());
            objectsLayer.setPrefWidth(newVal.getWidth());
            objectsLayer.setPrefHeight(newVal.getHeight());
        });

        gamePane.widthProperty().addListener((obs, oldVal, newVal) -> {
            canvas.setWidth(newVal.doubleValue());
            objectsLayer.setPrefWidth(newVal.doubleValue());
        });
        gamePane.heightProperty().addListener((obs, oldVal, newVal) -> {
            canvas.setHeight(newVal.doubleValue());
            objectsLayer.setPrefHeight(newVal.doubleValue());
        });
    }

    private void setupZoomAndDrag() {
        final double[] dragDelta = new double[2];

        zoomGroup.setOnScroll(event -> {
            double zoomFactor = Math.pow(1.1, event.getDeltaY() / 40);
            double oldScale = zoomGroup.getScaleX();
            double newScale = oldScale * zoomFactor;
            newScale = Math.min(Math.max(newScale, 0.5), 3);

            Point2D mousePos = new Point2D(event.getX(), event.getY());
            Point2D mousePosInScene = zoomGroup.localToScene(mousePos);

            zoomGroup.setScaleX(newScale);
            zoomGroup.setScaleY(newScale);

            Point2D newMousePosInScene = zoomGroup.localToScene(mousePos);
            Point2D delta = newMousePosInScene.subtract(mousePosInScene);

            zoomGroup.setTranslateX(zoomGroup.getTranslateX() - delta.getX());
            zoomGroup.setTranslateY(zoomGroup.getTranslateY() - delta.getY());

            event.consume();
        });

        zoomGroup.setOnMousePressed(event -> {
            dragDelta[0] = zoomGroup.getTranslateX() - event.getSceneX();
            dragDelta[1] = zoomGroup.getTranslateY() - event.getSceneY();
        });

        zoomGroup.setOnMouseDragged(event -> {
            zoomGroup.setTranslateX(event.getSceneX() + dragDelta[0]);
            zoomGroup.setTranslateY(event.getSceneY() + dragDelta[1]);
        });
    }

    private void setupMouseCoordinateTracking(StackPane gamePane, Label mouseCoordsLabel) {
        gamePane.setOnMouseMoved(event -> {
            Point2D mousePoint = zoomGroup.sceneToLocal(event.getSceneX(), event.getSceneY());

            int width = controller.getMapWidth();

            double isoX = mousePoint.getX() - width * TILE_SIZE / 2.0;
            double isoY = mousePoint.getY();

            double x = (isoY / (TILE_SIZE / 4.0) + isoX / (TILE_SIZE / 2.0)) / 2.0;
            double y = (isoY / (TILE_SIZE / 4.0) - isoX / (TILE_SIZE / 2.0)) / 2.0;

            int tileX = (int) Math.floor(x);
            int tileY = (int) Math.floor(y);

            if ((tileX >= 0 && tileX < controller.getMapWidth()) &&
                    (tileY >= 0 && tileY < controller.getMapHeight())) {
                mouseCoordsLabel.setText(String.format("Tile: (%d, %d)", tileY, tileX));
            }

            double labelX = event.getX() - 900;
            double labelY = event.getY() - 365;
            mouseCoordsLabel.setTranslateX(labelX);
            mouseCoordsLabel.setTranslateY(labelY);
        });

        gamePane.setOnMouseExited(event -> mouseCoordsLabel.setText(""));
    }

    private Pane createInfoPane() {
        Pane infoPane = new Pane();
        infoPane.setStyle("-fx-background-color: white;");

        HBox topButtons = new HBox(10);
        topButtons.setPadding(new Insets(10));
        topButtons.setLayoutX(10);
        topButtons.setLayoutY(10);

        Button btn1 = new Button("Build Station");
        Button btn2 = new Button("Buy Locomotive");
        Button btn3 = new Button("View Stations");
        Button btn4 = new Button("Build Railway Line");
        Button btn5 = new Button("Assign Train to Route");
        Button btn6 = new Button("List All Trains");
        Button btn7 = new Button("Build Train");
        Button btn8 = new Button("Route Builder");
        Button btn9 = new Button("Start"); // Start/Pause button

        // --- US23: Save and Load Game Buttons ---
        Button saveButton = new Button("Save Game");
        Button loadButton = new Button("Load Game");

        saveButton.setOnAction(e -> {
            try {
                currentGame.saveToFile("savegame.dat");
                showAlert("Game Saved", "Game was saved successfully.");
            } catch (Exception ex) {
                showAlert("Error", "Failed to save game: " + ex.getMessage());
            }
        });

        loadButton.setOnAction(e -> {
            try {
                CurrentGame loadedGame = CurrentGame.loadFromFile("savegame.dat");
                showAlert("Game Loaded", "Game was loaded successfully.");
                // Recreate GameScreen with loadedGame and navigate to it
                GameScreen newScreen = new GameScreen(loadedGame);
                Scene scene = new Scene(newScreen.getView(), infoPane.getScene().getWindow().getWidth(), infoPane.getScene().getWindow().getHeight());
                scene.getStylesheets().add(getClass().getResource("/css/playerUI.css").toExternalForm());
                infoPane.getScene().setRoot(newScreen.getView());
            } catch (Exception ex) {
                showAlert("Error", "Failed to load game: " + ex.getMessage());
            }
        });

        topButtons.getChildren().addAll(
            btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
            saveButton, loadButton
        );

        VBox dynamicContent = new VBox(10);
        dynamicContent.setPadding(new Insets(15));
        dynamicContent.setLayoutX(10);
        dynamicContent.setLayoutY(40);

        // User Info Section
        VBox userInfo = new VBox(10);
        userInfo.setLayoutX(1700);
        userInfo.setLayoutY(10);

        // Player avatar image
        Image playerAvatar = SpriteLoader.getPlayerAvatar();
        ImageView avatarView = new ImageView(playerAvatar);
        avatarView.setFitWidth(64);
        avatarView.setFitHeight(64);
        avatarView.setPreserveRatio(true);

        Label userName = new Label("User: " + controller.getCurrentUserName());
        this.userMoney = new Label(String.format("Money: %.2f", controller.getCurrentMoney()));

        // Toggle button for railways
        Button toggleRailwayBtn = new Button("Show Railways");
        toggleRailwayBtn.setOnAction(e -> {
            railwayPane.setVisible(!isVisible);
            isVisible = railwayPane.isVisible();
            toggleRailwayBtn.setText(isVisible ? "Show Railways" : "Hide Railways");
        });

        Button speedButton = new Button("Speed: 1x");

        speedButton.setOnAction(e -> {
            simulationSpeed *= 2;
            if (simulationSpeed > 8) simulationSpeed = 1;

            dayDurationNs = (long)(1_000_000_000 / simulationSpeed);
            speedButton.setText("Speed: " + (int)simulationSpeed + "x");
        });

        userInfo.getChildren().addAll(avatarView, userName, userMoney, toggleRailwayBtn, speedButton);

        infoPane.getChildren().addAll(topButtons, dynamicContent, userInfo);

        // Button handlers
        btn1.setOnAction(e -> {
            dynamicContent.getChildren().clear();
            showBuildStationUI(dynamicContent);
        });

        btn2.setOnAction(e -> {
            dynamicContent.getChildren().clear();
            showBuyLocomotiveUI(dynamicContent);
        });

        btn3.setOnAction(e -> {
            dynamicContent.getChildren().clear();
            showViewStationsUI(dynamicContent);
        });

        btn4.setOnAction(e -> {
            dynamicContent.getChildren().clear();
            showBuildRailwayLineUI(dynamicContent);
        });

        btn5.setOnAction(e -> {
            dynamicContent.getChildren().clear();
            showAssignTrainToRouteUI(dynamicContent);
        });

        btn6.setOnAction(e -> showListAllTrainsPopup());

        btn7.setOnAction(e -> openTrainBuilderPopup());

        btn8.setOnAction(e -> showCreateRoutePopup());

        btn9.setOnAction(e -> toggleSimulation(btn9));

        return infoPane;
    }

    private void toggleSimulation(Button button) {
        if (isSimulationRunning) {
            simulationTimer.stop();
            button.setText("Start");
        } else {
            if (currentSimulationDate == null) {
                currentSimulationDate = controller.getCurrentScenario().getStartingDate();
                updateDayCounterLabel(); // update label immediately on start
            }

            if (simulationTimer == null) {
                setupSimulationTimer();
            }

            simulationTimer.start();
            button.setText("Pause");
        }
        isSimulationRunning = !isSimulationRunning;
    }

    private void setupSimulationTimer() {
        simulationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lastUpdateTime == 0) {
                    lastUpdateTime = now;
                    return;
                }
                long elapsed = now - lastUpdateTime;
                if (elapsed >= dayDurationNs) {
                    simulateDay(controller.getStations(), controller);
                    currentSimulationDate = currentSimulationDate.plusDays(1);  // Advance the simulation date by 1 day
                    updateDayCounterLabel();  // Update the label text to reflect new date
                    lastUpdateTime = now;
                }
            }
        };
    }

    private void checkForNewLocomotives(List<Locomotive> allLocomotives, LocalDate currentDate) {
        for (Locomotive loco : allLocomotives) {
            if (!announcedLocomotives.contains(loco.getName()) &&
                    (loco.getAvailableFrom().isEqual(currentDate) || loco.getAvailableFrom().isBefore(currentDate))) {

                announcedLocomotives.add(loco.getName());

                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("New Locomotive Available!");
                    alert.setHeaderText("Locomotive Unlocked");
                    alert.setContentText("You can now acquire the locomotive: " + loco.getName());
                    alert.showAndWait();
                });
            }
        }
    }


    public void simulateDay(List<Station> allStations, GameController controller) {
        Set<Train> uniqueTrains = new HashSet<>();

        for (Station station : allStations) {
            uniqueTrains.addAll(station.getAllTrains());
        }

        for (Train train : uniqueTrains) {
            if (!train.hasRoute()) continue;

            List<PointOfRoute> pointOfRoutes = train.getRoute().getPointOfRoutes();
            int currentIndex = train.getCurrentRouteIndex();
            int nextIndex = (currentIndex + 1) % pointOfRoutes.size();

            Station currentStation = train.getCurrentStation();
            Station nextStation = pointOfRoutes.get(nextIndex).getStation();

            boolean isDoubleTrack = controller.isDoubleTrackBetween(currentStation, nextStation);
            int baseTravelDays = isDoubleTrack ? 10 : 13;

            int carriageCount = train.getCarriages().size();
            double degradationFactor = Math.min(carriageCount * 0.05, 0.3);

            int requiredTravelDays = (int) Math.ceil(baseTravelDays * (1 + degradationFactor));
            int travelDays = train.getDaysTravelingBetweenStations() + 1;
            train.setDaysTravelingBetweenStations(travelDays);

            if (travelDays >= requiredTravelDays) {
                if (currentStation != null) {
                    train.setSimulationDepartureDate(currentSimulationDate);
                    train.setSimulationDepartureStation(currentStation);
                    currentStation.removeTrain(train);
                }

                nextStation.addTrain(train);
                train.setCurrentStation(nextStation);
                train.setCoordinates(nextStation.getCoordinates());
                train.setCurrentRouteIndex(nextIndex);
                train.setDaysTravelingBetweenStations(0);
                train.setSimulationArrivalDate(currentSimulationDate);

                assignCargoToTrainAtStation(train, nextStation, controller);
            }
        }

        checkForNewLocomotives(controller.getAllLocomotives(), currentSimulationDate);
        generateCargoForStations(controller.getStations(), controller.getCurrentScenario(), controller);

    }

    private void assignCargoToTrainAtStation(Train train, Station station, GameController controller) {
        List<Industry> allIndustries = controller.getIndustries();
        List<Industry> nearbyIndustries = station.getNearbyIndustries(allIndustries);

        Set<CargoType> demandedCargoTypes = nearbyIndustries.stream()
                .flatMap(ind -> ind.getInputResourceTypes().stream())
                .map(CargoUtils::mapResourceToCargoType)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Set<CargoType> suppliedCargoTypes = nearbyIndustries.stream()
                .map(ind -> CargoUtils.mapResourceToCargoType(ind.getOutputResourceType()))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        List<City> nearbyCities = controller.getNearbyCities(station);

        if (!nearbyCities.isEmpty()) {
            demandedCargoTypes.add(CargoType.Passenger);
            suppliedCargoTypes.add(CargoType.Passenger);
        }

        Map<CargoType, Integer> maxLoadPerCargoType = new HashMap<>();

        if (!nearbyCities.isEmpty()) {
            int totalHouseBlocks = nearbyCities.stream()
                    .mapToInt(city -> city.getHouseBlocks().size())
                    .sum();
            int basePassengersPerBlock = 5;
            maxLoadPerCargoType.put(CargoType.Passenger, totalHouseBlocks * basePassengersPerBlock);
        }

        for (Industry industry : nearbyIndustries) {
            CargoType outputCargoType = CargoUtils.mapResourceToCargoType(industry.getOutputResourceType());
            if (outputCargoType == null) continue;

            int baseAmount = outputCargoType.getBaseAmount();
            double generationFactor = industry.getIndustryGenerationFactor().getGenerationFactor();
            int industryMaxLoad = (int) Math.round(baseAmount * generationFactor);

            System.out.println("[DEBUG] IndustryType: " + industry.getIndustryType() +
                    ", Coordinates: " + industry.getCoordinates() +
                    ", OutputCargoType: " + outputCargoType +
                    ", BaseAmount: " + baseAmount +
                    ", GenerationFactor: " + generationFactor +
                    ", CalculatedIndustryMaxLoad: " + industryMaxLoad);

            maxLoadPerCargoType.merge(outputCargoType, industryMaxLoad, Integer::sum);
        }

        StringBuilder log = new StringBuilder();
        boolean anyActionTaken = false;

        // === UNLOADING ===
        for (Carriage carriage : train.getCarriages()) {
            Cargo cargo = carriage.getCargo();
            if (cargo != null && demandedCargoTypes.contains(cargo.getCargoType())) {
                int unloadedAmount = (int) Math.round(cargo.getQuantity());

                log.append("Unloaded ").append(unloadedAmount).append(" units of ")
                        .append(cargo.getCargoType().name())
                        .append(" at ").append(station.getName()).append("\n");

                station.addCargo(new Cargo(unloadedAmount, station, null, cargo.getCargoType()));
                carriage.setCargo(null);
                anyActionTaken = true;
            }
        }

        // === LOADING ===
        List<Cargo> stationCargoList = new ArrayList<>(station.getAvailableCargoList());
        Map<CargoType, Integer> totalLoadedPerType = new HashMap<>();

        for (Cargo stationCargo : stationCargoList) {
            CargoType cargoType = stationCargo.getCargoType();
            int availableAmount = (int) Math.round(stationCargo.getQuantity());

            if (availableAmount <= 0) continue;
            if (!suppliedCargoTypes.contains(cargoType)) continue;

            int maxAllowedLoad = maxLoadPerCargoType.getOrDefault(cargoType, Integer.MAX_VALUE);
            int alreadyLoaded = totalLoadedPerType.getOrDefault(cargoType, 0);

            int loadAmountRemaining = Math.min(availableAmount, maxAllowedLoad - alreadyLoaded);
            if (loadAmountRemaining <= 0) continue;

            for (Carriage carriage : train.getCarriages()) {
                if (loadAmountRemaining <= 0) break;
                if (!carriage.getCarriageType().supports(cargoType)) continue;

                Cargo currentCargo = carriage.getCargo();
                int capacity = (int) Math.round(carriage.getCapacity());
                int currentLoad = currentCargo != null ? (int) Math.round(currentCargo.getQuantity()) : 0;
                int spaceAvailable = capacity - currentLoad;

                if (spaceAvailable <= 0) continue;

                int amountToLoad = Math.min(spaceAvailable, loadAmountRemaining);
                if (amountToLoad <= 0) continue;

                if (currentCargo == null) {
                    carriage.setCargo(new Cargo(amountToLoad, station, null, cargoType));
                } else {
                    currentCargo.addQuantity(amountToLoad);
                }

                availableAmount -= amountToLoad;
                stationCargo.setQuantity(availableAmount);

                alreadyLoaded += amountToLoad;
                totalLoadedPerType.put(cargoType, alreadyLoaded);

                loadAmountRemaining = Math.min(availableAmount, maxAllowedLoad - alreadyLoaded);

                anyActionTaken = true;
            }

            if (availableAmount <= 0) {
                station.removeCargo(stationCargo);
            }
        }

        // === LOG SUMMARY ===
        for (Map.Entry<CargoType, Integer> entry : totalLoadedPerType.entrySet()) {
            log.append("Loaded ").append(entry.getValue()).append(" units of ")
                    .append(entry.getKey().name()).append(" onto train\n");
        }

        // === UI FEEDBACK ===
        final boolean showActions = anyActionTaken;
        final String logMessage = log.toString();

        Platform.runLater(() -> {
            Alert alert;
            if (showActions) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cargo Loading/Unloading Info");
                alert.setHeaderText("Train " + train.getTrainName() + " at station " + station.getName());

                Label contentLabel = new Label(logMessage);
                contentLabel.setWrapText(true);
                contentLabel.setMaxWidth(400);

                ScrollPane scrollPane = new ScrollPane(contentLabel);
                scrollPane.setPrefSize(420, 300);
                scrollPane.setFitToWidth(true);

                alert.getDialogPane().setContent(scrollPane);
            } else {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Cargo Action");
                alert.setHeaderText(null);
                alert.setContentText("Train " + train.getTrainName() + " at station " + station.getName() +
                        " had no cargo to unload or load. All carriages may be full or incompatible.");
            }

            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.getDialogPane().setMaxWidth(450);
            alert.showAndWait();
        });
    }



    private void updateDayCounterLabel() {
        if (currentSimulationDate == null) {
            currentSimulationDate = controller.getCurrentScenario().getStartingDate();
        }
        String dateText = String.format("Year: %d | Month: %d | Day: %d",
                currentSimulationDate.getYear(),
                currentSimulationDate.getMonthValue(),
                currentSimulationDate.getDayOfMonth());
        dayCounterLabel.setText(dateText);
    }

    private void moveTrainsAlongRoutes() {
        List<Train> trains = controller.getTrains();

        for (Train train : trains) {
            if (!train.hasRoute()) continue;

            Route route = train.getRoute();
            List<PointOfRoute> points = route.getPointOfRoutes();

            int currentIndex = train.getCurrentRouteIndex();
            int nextIndex = (currentIndex + 1) % points.size(); // loop route if needed

            train.setDaysTravelingBetweenStations(train.getDaysTravelingBetweenStations() + 1);

            if (train.getDaysTravelingBetweenStations() >= 5) {
                // Move train to next station
                PointOfRoute nextPoint = points.get(nextIndex);
                Station nextStation = nextPoint.getStation();

                train.setCoordinates(nextStation.getCoordinates());
                train.setCurrentRouteIndex(nextIndex);
                train.setDaysTravelingBetweenStations(0);

                // Optionally update train's current station reference, if you keep one
                train.setCurrentStation(nextStation);
            }
            // else train is still traveling between currentIndex station and nextIndex station,
            // you might want to interpolate position for smoother animation, if desired
        }
    }


    private void speedUpSimulation() {
        if (!isSimulationRunning) return;

        simulationSpeed *= 2;
        if (simulationSpeed > 8) simulationSpeed = 1;

        dayDurationNs = (long)(1_000_000_000 / simulationSpeed);

        speedInfoLabel.setText(String.format("Press SPACE to speed up simulation (Current speed: %.1fx)", simulationSpeed));
    }



    private void showListAllTrainsPopup() {
        // Create a new modal stage for the popup
        Stage popupStage = new Stage();
        popupStage.setTitle("All Trains");
        popupStage.initModality(Modality.APPLICATION_MODAL);

        VBox content = new VBox(10);
        content.setPadding(new Insets(15));
        content.setStyle("-fx-background-color: white;");

        Label title = new Label("All Trains");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        content.getChildren().add(title);

        ArrayList<Train> trains = controller.getTrains();

        // Group trains by locomotive type, sorted alphabetically
        Map<String, List<Train>> groupedTrains = trains.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getLocomotive().getLocomotiveType().toString(),
                        TreeMap::new,
                        Collectors.toList()
                ));

        // Sort each group by train name
        groupedTrains.values().forEach(trainList ->
                trainList.sort(Comparator.comparing(Train::getTrainName))
        );

        // Display each group
        for (String locoType : groupedTrains.keySet()) {
            Label typeLabel = new Label("Locomotive Type: " + locoType);
            typeLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            content.getChildren().add(typeLabel);

            for (Train train : groupedTrains.get(locoType)) {
                VBox trainBox = new VBox(5);
                trainBox.setStyle("-fx-padding: 8; -fx-background-color: #f0f0f0; -fx-border-color: #ccc;");

                // Carriage and cargo summary
                StringBuilder cargoSummary = new StringBuilder();
                for (Carriage carriage : train.getCarriages()) {
                    String type = carriage.getCarriageType().toString();
                    String cargo = (carriage.getCargo() != null) ? carriage.getCargo().toString() : "Empty";
                    cargoSummary.append(String.format(" - %s: %s%n", type, cargo));
                }

                // Route display (or None if unassigned)
                String routeInfo;
                if (train.getRoute() == null) {
                    routeInfo = "Route: None";
                } else {
                    List<PointOfRoute> points = train.getRoute().getPointOfRoutes();
                    String routeStations = points.stream()
                            .map(p -> p.getStation().getName())
                            .collect(Collectors.joining(" -> "));
                    routeInfo = "Route: " + routeStations;
                }

                // Labels for each section
                Label trainLabel = new Label("Train: " + train.getTrainName());
                Label cargoLabel = new Label("Carriages:\n" + cargoSummary);
                Label routeLabel = new Label(routeInfo);

                trainBox.getChildren().addAll(trainLabel, cargoLabel, routeLabel);
                content.getChildren().add(trainBox);
            }
        }

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);

        Scene scene = new Scene(scrollPane, 600, 500);
        popupStage.setScene(scene);
        popupStage.showAndWait();
    }

    private void showAssignTrainToRouteUI(VBox dynamicContent) {
        dynamicContent.getChildren().clear();
        dynamicContent.setPadding(new Insets(15));
        dynamicContent.setSpacing(10);
        dynamicContent.setAlignment(Pos.TOP_LEFT);

        Label trainsLabel = new Label("Available Trains:");
        ComboBox<Train> trainBox = new ComboBox<>();
        trainBox.setPrefWidth(200);

        List<Train> unassignedTrains = controller.getTrains().stream()
                .filter(t -> !t.hasRoute())
                .toList();
        trainBox.getItems().addAll(unassignedTrains);

        Label routesLabel = new Label("Available Routes:");
        ComboBox<Route> routeBox = new ComboBox<>();
        routeBox.setPrefWidth(200);
        routeBox.getItems().addAll(controller.getRoutes());

        Button confirmationBtn = new Button("Assign Route");
        confirmationBtn.setOnAction(e -> {
            Train selectedTrain = trainBox.getValue();
            Route selectedRoute = routeBox.getValue();

            if (selectedTrain == null || selectedRoute == null) {
                new Alert(Alert.AlertType.WARNING, "Please select both a train and a route.").showAndWait();
                return;
            }

            selectedTrain.setRoute(selectedRoute);

            // Set train's current station to the first station on the route
            List<PointOfRoute> points = selectedRoute.getPointOfRoutes();
            if (!points.isEmpty()) {
                Station firstStation = points.get(0).getStation();

                // Remove from old station if necessary
                Station oldStation = selectedTrain.getCurrentStation();
                if (oldStation != null) {
                    oldStation.getAllTrains().remove(selectedTrain);
                }

                // Update train position
                selectedTrain.setCurrentStation(firstStation);
                selectedTrain.setCurrentRouteIndex(0);
                selectedTrain.setDaysTravelingBetweenStations(0);

                // Add train to new station
                firstStation.addTrain(selectedTrain);
            }

            // Inform user of successful route assignment
            new Alert(Alert.AlertType.INFORMATION, "Train " + selectedTrain.getTrainName() +
                    " assigned to the selected route.").showAndWait();
        });

        dynamicContent.getChildren().addAll(
                trainsLabel, trainBox,
                routesLabel, routeBox,
                confirmationBtn
        );
    }


    private void showBuildRailwayLineUI(VBox dynamicContent) {
        dynamicContent.getChildren().clear();
        dynamicContent.setPadding(new Insets(15));
        dynamicContent.setSpacing(10);
        dynamicContent.setAlignment(Pos.TOP_LEFT);

        Label stationALabel = new Label("Station A:");
        ComboBox<Station> stationABox = new ComboBox<>();
        stationABox.setPrefWidth(200);

        Label stationBLabel = new Label("Station B:");
        ComboBox<Station> stationBBox = new ComboBox<>();
        stationBBox.setPrefWidth(200);

        // Lista original de estações
        List<Station> allStations = controller.getStations();
        stationABox.getItems().addAll(allStations);
        stationBBox.getItems().addAll(allStations);

        Label lineType = new Label("Line type:");
        ComboBox<LineType> lineBox = new ComboBox<>();
        lineBox.setPrefWidth(200);

        lineBox.getItems().addAll(LineType.values());

        Button confirmationBtn = new Button("Confirm");

        dynamicContent.getChildren().addAll(stationALabel, stationABox, stationBLabel, stationBBox, lineType, lineBox,
                confirmationBtn);

        confirmationBtn.setOnAction(e -> {
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setContentText(
                        String.format("It will cost %.2f$ to conclude this operation! Do you wish to proceed?",
                                +controller.getRailwayLineCost(stationABox.getValue(),
                                        stationBBox.getValue(), lineBox.getValue())));
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    controller.buildRailwayLine(stationABox.getValue(), stationBBox.getValue(), lineBox.getValue());
                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("A railway line was successfully built between "
                            + stationABox.getValue().getName() + " and " + stationBBox
                                    .getValue().getName()
                            + "!");

                    success.showAndWait();
                } else {
                }
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information");
                alert.setContentText("Error: " + ex);

                alert.showAndWait();
            }
        });

    }

    private void showViewStationsUI(VBox dynamicContent) {
        dynamicContent.getChildren().clear();
        dynamicContent.setPadding(new Insets(15));
        dynamicContent.setSpacing(10);
        dynamicContent.setAlignment(Pos.TOP_LEFT);

        Label stationsLabel = new Label("Stations:");
        ComboBox<Station> stationBox = new ComboBox<>();
        stationBox.setPrefWidth(200);
        stationBox.getItems().addAll(controller.getStations());

        Button confirmationBtn = new Button("Confirm");
        confirmationBtn.setOnAction(e -> {
            try {
                Station station = stationBox.getValue();
                showDetailPopup(station);
            } catch (Exception ex) {
                Station station = stationBox.getValue();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information");
                alert.setHeaderText(station.getName());
                alert.setContentText("Error: " + ex);

                alert.showAndWait();
            }
        });

        dynamicContent.getChildren().addAll(stationsLabel, stationBox, confirmationBtn);
    }

    private void showDetailPopup(Station station) {
        Stage popup = new Stage();
        popup.setTitle("Information");

        // Select the appropriate station image
        Image image;
        switch (station.getStationType()) {
            case Depot:
                image = new Image(getClass().getResource("/sprites/depot.png").toExternalForm());
                break;
            case Station:
                image = new Image(getClass().getResource("/sprites/station.png").toExternalForm());
                break;
            case Terminal:
                image = new Image(getClass().getResource("/sprites/terminal.png").toExternalForm());
                break;
            default:
                image = new Image(getClass().getResource("/sprites/station.png").toExternalForm());
                break;
        }

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);

        Rectangle background = new Rectangle(220, 180);
        background.setArcWidth(20);
        background.setArcHeight(20);
        background.setFill(Color.LIGHTGRAY);

        StackPane imageContainer = new StackPane(background, imageView);
        imageContainer.setAlignment(Pos.TOP_CENTER);

        // Fetch nearby cities and industries
        ArrayList<City> nearbyCities = controller.getNearbyCities(station);
        ArrayList<Industry> nearbyIndustries = controller.getNearbyIndustries(station);

        // Fetch trains currently at this station
        List<Train> trainsAtStation = controller.getTrains().stream()
                .filter(t -> station.equals(t.getCurrentStation()))
                .collect(Collectors.toList());

        String citiesStr = nearbyCities.isEmpty()
                ? "None"
                : nearbyCities.stream()
                .map(City::getName)
                .sorted()
                .collect(Collectors.joining(", "));

        // Compose trains string with arrival date info
        String trainsStr = trainsAtStation.isEmpty()
                ? "None"
                : trainsAtStation.stream()
                .sorted(Comparator.comparing(Train::getTrainName))
                .map(train -> {
                    LocalDate arrivalDate = train.getSimulationArrivalDate();
                    LocalDate departureDate = train.getSimulationDepartureDate();
                    Station departureStation = train.getSimulationDepartureStation();

                    String arrivalStr = (arrivalDate != null) ? arrivalDate.toString() : "Unknown arrival";
                    String departureStr = (departureDate != null) ? departureDate.toString() : "Not departed yet";
                    String departureStationStr = (departureStation != null) ? departureStation.getName() : "Unknown station";

                    return train.getTrainName() +
                            " (Arrived: " + arrivalStr +
                            ", Departed: " + departureStr +
                            " from " + departureStationStr + ")";
                })
                .collect(Collectors.joining("\n"));

        // Compose industries string including supply/demand info, resource types, and generation factor
        String industriesStr = nearbyIndustries.isEmpty()
                ? "None"
                : nearbyIndustries.stream()
                .map(industry -> {
                    String industryType = industry.getIndustryType().name();
                    String supplyDemandInfo;

                    if (industry instanceof PrimaryIndustry primary) {
                        String outputResource = primary.getOutput().getResourceType().name();
                        supplyDemandInfo = "Supplies: " + outputResource;
                    } else if (industry instanceof TransformativeIndustry trans) {
                        String inputs = trans.getInput().getResourceTypes().stream()
                                .map(Enum::name)
                                .sorted()
                                .collect(Collectors.joining(", "));
                        String outputResource = trans.getOutput().getResourceType().name();
                        supplyDemandInfo = "Demands: " + inputs + "; Supplies: " + outputResource;
                    } else {
                        supplyDemandInfo = "Unknown supply/demand info";
                    }

                    double genFactor = industry.getIndustryGenerationFactor().getGenerationFactor();

                    return industryType + " Industry (" + supplyDemandInfo + ") - Factor: " + genFactor;
                })
                .sorted()
                .collect(Collectors.joining("\n"));

        // Info section
        VBox info = new VBox();
        info.setSpacing(10);
        info.setPadding(new Insets(15));
        info.setAlignment(Pos.CENTER_LEFT);

        Label infoLabel = new Label(station.getDetails());
        infoLabel.setWrapText(true);

        Label citiesLabel = new Label("Nearby cities: " + citiesStr);
        Label industriesLabel = new Label("Nearby industries:\n" + industriesStr);
        industriesLabel.setWrapText(true);

        Label trainsLabel = new Label("Trains currently at this station:\n" + trainsStr);
        trainsLabel.setWrapText(true);

        Button upgradeBtn = new Button("Buy upgrade");
        upgradeBtn.setOnAction(e -> showUpgradeStationUI(station));
        upgradeBtn.setAlignment(Pos.CENTER);

        info.getChildren().addAll(infoLabel, citiesLabel, industriesLabel, trainsLabel, upgradeBtn);

        // Assemble everything
        VBox root = new VBox(15, imageContainer, info);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(root, 550, 620);
        popup.setScene(scene);
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.showAndWait();
    }



    private void showUpgradeStationUI(Station station) {
        Stage popup = new Stage();
        popup.setTitle("Upgrade " + station.getName());

        VBox ownedBox = new VBox();
        ownedBox.setPadding(new Insets(10));
        ownedBox.setAlignment(Pos.TOP_LEFT);

        Label ownedLabel = new Label("Owned upgradees:");
        ownedList = new ListView<>();

        ownedList.getItems().addAll(station.getBuildingUpgrades());

        ownedBox.getChildren().addAll(ownedLabel, ownedList);

        VBox availableBox = new VBox(10);
        availableBox.setPadding(new Insets(10));
        availableBox.setAlignment(Pos.TOP_RIGHT);

        Label availableLabel = new Label("Available upgrades:");
        ListView<BuildingUpgrade> availableList = new ListView<>();
        availableList.getItems().addAll(controller.getBuildingUpgrades());

        availableBox.getChildren().addAll(availableLabel, availableList);

        VBox manageUpgrade = new VBox(10);
        availableBox.setPadding(new Insets(10));
        availableBox.setAlignment(Pos.CENTER);

        HBox lists = new HBox(20, ownedBox, availableBox);
        lists.setPadding(new Insets(20));
        lists.setAlignment(Pos.CENTER);

        Button buyButton = new Button("Buy");
        buyButton.setAlignment(Pos.CENTER);
        buyButton.setOnAction(e -> {
            try {
                controller.upgradeStation(station, availableList.getSelectionModel().getSelectedItem());
                infoLabel.setText(station.getDetails());
                ownedList.getItems().addAll(station.getBuildingUpgrades());
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information");
                alert.setHeaderText(station.getName());
                alert.setContentText("Error: " + ex);

                alert.showAndWait();
            }
        });

        manageUpgrade.getChildren().addAll(lists, buyButton);

        Scene scene = new Scene(manageUpgrade, 500, 300);
        popup.setScene(scene);
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.showAndWait();
    }

    private void showBuyLocomotiveUI(VBox dynamicContent) {
        dynamicContent.getChildren().clear();
        dynamicContent.setPadding(new Insets(15));
        dynamicContent.setSpacing(10);
        dynamicContent.setAlignment(Pos.TOP_LEFT);

        Label locomotiveLabel = new Label("Available Locomotives:");
        ComboBox<Locomotive> locomotiveBox = new ComboBox<>();
        locomotiveBox.setPrefWidth(250);

        // Use the current simulation date from wherever you keep it
        LocalDate simulationDate = currentSimulationDate != null
                ? currentSimulationDate
                : controller.getCurrentScenario().getStartingDate();

        // Pass simulationDate to getAvailableLocomotives
        locomotiveBox.getItems().addAll(getAvailableLocomotives(simulationDate));

        // Show tooltips with availability dates
        locomotiveBox.setCellFactory(cb -> new ListCell<>() {
            @Override
            protected void updateItem(Locomotive loco, boolean empty) {
                super.updateItem(loco, empty);
                if (empty || loco == null) {
                    setText(null);
                    setTooltip(null);
                } else {
                    setText(loco.getName());
                    Tooltip tooltip = new Tooltip("Available from: " + loco.getAvailableFrom());
                    setTooltip(tooltip);
                }
            }
        });

        locomotiveBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Locomotive loco, boolean empty) {
                super.updateItem(loco, empty);
                setText((empty || loco == null) ? null : loco.getName());
            }
        });

        Label priceLabel = new Label("Price: $0.00");
        Button confirmationBtn = new Button("Confirm");
        confirmationBtn.setDisable(true);

        locomotiveBox.setOnAction(e -> {
            Locomotive selected = locomotiveBox.getValue();
            if (selected != null) {
                priceLabel.setText(String.format("Price: $%.2f", selected.getAcquisitionPrice()));
                confirmationBtn.setDisable(false);
            } else {
                priceLabel.setText("Price: $0.00");
                confirmationBtn.setDisable(true);
            }
        });

        confirmationBtn.setOnAction(e -> {
            Locomotive selected = locomotiveBox.getValue();
            if (selected == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Selection Error");
                alert.setHeaderText(null);
                alert.setContentText("Please select a locomotive before confirming.");
                alert.showAndWait();
                return;
            }

            try {
                controller.buyLocomotive(selected);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(selected.getName());
                alert.setContentText(String.format(
                        "The locomotive was successfully purchased!\nRemaining funds: $%.2f",
                        controller.getCurrentMoney()));
                alert.showAndWait();

                // Refresh list and UI with updated date
                locomotiveBox.getItems().clear();
                locomotiveBox.getItems().addAll(getAvailableLocomotives(simulationDate));
                locomotiveBox.setValue(null);
                priceLabel.setText("Price: $0.00");
                confirmationBtn.setDisable(true);

            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Purchase Error");
                alert.setHeaderText(selected.getName());
                alert.setContentText("Error: " + ex.getMessage());
                alert.showAndWait();
            }
        });

        dynamicContent.getChildren().addAll(locomotiveLabel, locomotiveBox, priceLabel, confirmationBtn);
    }

    public ArrayList<Locomotive> getAvailableLocomotives(LocalDate simulationDate) {
        ArrayList<Locomotive> availableLocomotives = new ArrayList<>();
        ArrayList<Locomotive> allLocomotives = controller.getAllLocomotives();
        for (Locomotive loco : allLocomotives) {
            if (!loco.getAvailableFrom().isAfter(simulationDate)) {
                availableLocomotives.add(loco);
            }
        }
        return availableLocomotives;
    }

    private void showBuildStationUI(VBox dynamicContent) {
        dynamicContent.getChildren().clear();
        dynamicContent.setPadding(new Insets(15));
        dynamicContent.setSpacing(10);
        dynamicContent.setAlignment(Pos.TOP_LEFT);

        // Inputs de coordenadas X e Y
        HBox inputRow = new HBox(5);
        inputRow.setAlignment(Pos.CENTER_LEFT);

        TextField inputX = new TextField();
        inputX.setPromptText("X");
        inputX.setPrefWidth(60);

        Label dash = new Label("-");

        TextField inputY = new TextField();
        inputY.setPromptText("Y");
        inputY.setPrefWidth(60);

        inputRow.getChildren().addAll(inputX, dash, inputY);

        // StationType ComboBox
        Label stationTypeLabel = new Label("Station Type:");
        ComboBox<StationType> stationTypeBox = new ComboBox<>();
        stationTypeBox.setPrefWidth(200);
        stationTypeBox.getItems().addAll(StationType.values());

        // Suggested Name Label
        Label suggestedNameLabel = new Label();
        suggestedNameLabel.setStyle("-fx-font-style: italic; -fx-text-fill: gray;");
        suggestedNameLabel.setVisible(false);

        // Nome
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        nameField.setPrefWidth(200);

        // Orientação
        Label orientationLabel = new Label("Orientation:");
        ComboBox<Orientation> orientationBox = new ComboBox<>();
        orientationBox.setPrefWidth(200);
        orientationBox.getItems().addAll(Orientation.values());

        Button confirmarBtn = new Button("Confirm");

        // Inicialmente escondemos os campos dependentes
        suggestedNameLabel.setVisible(false);
        nameLabel.setVisible(false);
        nameField.setVisible(false);
        orientationLabel.setVisible(false);
        orientationBox.setVisible(false);
        confirmarBtn.setVisible(false);

        Runnable updateSuggestedNameAndFields = () -> {
            try {
                int x = Integer.parseInt(inputX.getText().trim());
                int y = Integer.parseInt(inputY.getText().trim());
                StationType stationType = stationTypeBox.getValue();

                if (stationType != null) {
                    String closestCity = controller.findClosestCityName(new Coordinates(x, y));
                    String suggestedName = closestCity + " " + stationType.toString();
                    suggestedNameLabel.setText("Suggested Name: " + suggestedName);
                    suggestedNameLabel.setVisible(true);

                    nameLabel.setVisible(true);
                    nameField.setVisible(true);
                    nameField.setText(suggestedName);

                    orientationLabel.setVisible(true);
                    orientationBox.setVisible(true);
                    confirmarBtn.setVisible(true);
                } else {
                    suggestedNameLabel.setVisible(false);
                    nameLabel.setVisible(false);
                    nameField.setVisible(false);
                    orientationLabel.setVisible(false);
                    orientationBox.setVisible(false);
                    confirmarBtn.setVisible(false);
                }
            } catch (NumberFormatException ex) {
                suggestedNameLabel.setVisible(false);
                nameLabel.setVisible(false);
                nameField.setVisible(false);
                orientationLabel.setVisible(false);
                orientationBox.setVisible(false);
                confirmarBtn.setVisible(false);
            }
        };

        ChangeListener<String> coordsListener = (obs, oldVal, newVal) -> updateSuggestedNameAndFields.run();
        inputX.textProperty().addListener(coordsListener);
        inputY.textProperty().addListener(coordsListener);

        stationTypeBox.valueProperty().addListener((obs, oldVal, newVal) -> updateSuggestedNameAndFields.run());

        confirmarBtn.setOnAction(e -> {
            try {
                int x = Integer.parseInt(inputX.getText().trim());
                int y = Integer.parseInt(inputY.getText().trim());
                String name = nameField.getText().trim();
                StationType stationType = stationTypeBox.getValue();
                Orientation orientation = orientationBox.getValue();

                if (stationType != null && orientation != null && !name.isEmpty()) {
                    controller.buildStation(name, stationType, new Coordinates(x, y), orientation);

                }
            } catch (Exception ex) {
                String name = nameField.getText().trim();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information");
                alert.setHeaderText(name);
                alert.setContentText("Error: " + ex.getMessage());

                alert.showAndWait();
            }
        });

        dynamicContent.getChildren().addAll(new Label("Insert coordinates:"), inputRow, stationTypeLabel,
                stationTypeBox, suggestedNameLabel, nameLabel, nameField, orientationLabel, orientationBox,
                confirmarBtn);

    }

    private void startAnimationTimer(StackPane gameWithHud) {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateUI(gameWithHud);
            }
        };
        timer.start();
    }

    private void updateUI(StackPane gamePane) {
        this.userMoney.setText(String.format("Money: %.2f", controller.getCurrentMoney()));

        GraphicsContext gc = canvas.getGraphicsContext2D();

        int width = controller.getMapWidth();
        int height = controller.getMapHeight();

        drawTiles(gc, width, height);
        drawObjects(objectsLayer);
        drawRailways(railwayPane);

    }

    private void drawTiles(GraphicsContext gc, int width, int height) {
        gc.clearRect(0, 0, width * TILE_SIZE, height * TILE_SIZE);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Tile tile = controller.getTile(x, y);

                double isoX = (x - y) * (TILE_SIZE / 2.0) + width * TILE_SIZE / 2.0;
                double isoY = (x + y) * (TILE_SIZE / 4.0);

                // Apenas o chão
                Image groundImg = SpriteLoader.getGroundSprite(tile.getGround());
                if (groundImg != null) {
                    gc.drawImage(groundImg, isoX, isoY, TILE_SIZE, TILE_SIZE / 2.0);
                }
            }
        }
    }

    private void drawObjects(Pane objectsLayer) {
        objectsLayer.getChildren().clear();

        int width = controller.getMapWidth();
        int height = controller.getMapHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Tile tile = controller.getTile(x, y);

                double isoX = (x - y) * (TILE_SIZE / 2.0) + width * TILE_SIZE / 2.0;
                double isoY = (x + y) * (TILE_SIZE / 4.0);

                // Edifício
                Image buildingImg = SpriteLoader.getBuildingSprite(tile.getBuilding());
                if (buildingImg != null) {
                    ImageView iv = new ImageView(buildingImg);
                    iv.setFitWidth(TILE_SIZE);
                    iv.setFitHeight(TILE_SIZE);
                    iv.setTranslateX(isoX);
                    iv.setTranslateY(isoY - TILE_SIZE / 2.0);
                    objectsLayer.getChildren().add(iv);
                }
            }
        }

        // Outros objetos genéricos
        for (MapObject obj : mapObjects) {
            double isoX = (obj.x - obj.y) * (TILE_SIZE / 2.0) + width * TILE_SIZE / 2.0;
            double isoY = (obj.x + obj.y) * (TILE_SIZE / 4.0);

            ImageView iv = new ImageView(obj.sprite);
            iv.setFitWidth(TILE_SIZE);
            iv.setFitHeight(TILE_SIZE);
            iv.setTranslateX(isoX);
            iv.setTranslateY(isoY - TILE_SIZE / 2.0);
            objectsLayer.getChildren().add(iv);
        }

    }

    private void drawRailways(Pane railwayPane) {
        railwayPane.getChildren().clear(); // remove linhas antigas

        List<RailwayLine> railways = controller.getRailwayLines(); // ou outro método
        for (RailwayLine railway : railways) {
            Station stationA = railway.getStationA();
            Station stationB = railway.getStationB();

            Point2D posA = getStationPosition(stationA.getCoordinates());
            Point2D posB = getStationPosition(stationB.getCoordinates());

            Line line = new Line(posA.getX(), posA.getY(), posB.getX(), posB.getY());
            line.setStroke(Color.RED);
            line.setStrokeWidth(3);
            railwayPane.getChildren().add(line);
        }
    }

    private Point2D getStationPosition(Coordinates coord) {
        int tileY = coord.getX();
        int tileX = coord.getY();

        int width = controller.getMapWidth();

        double isoX = (tileX - tileY) * (TILE_SIZE / 2.0);
        double isoY = (tileX + tileY) * (TILE_SIZE / 4.0);

        isoX += width * TILE_SIZE / 2.0;

        return new Point2D(isoX, isoY);
    }

    private void openTrainBuilderPopup() {
        Stage popup = new Stage();
        popup.setTitle("Build New Train");
        popup.initModality(Modality.APPLICATION_MODAL);

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        // Locomotive selection
        ComboBox<Locomotive> locomotiveBox = new ComboBox<>();
        locomotiveBox.setPromptText("Select Locomotive");
        locomotiveBox.getItems().setAll(controller.getOwnedLocomotives());

        // Spinner for number of carriages
        Spinner<Integer> carriageCountSpinner = new Spinner<>(1, 10, 1);
        carriageCountSpinner.setEditable(true);

        VBox carriageInputsContainer = new VBox(10);
        carriageInputsContainer.setPadding(new Insets(10));

        carriageCountSpinner.valueProperty().addListener((obs, oldVal, newVal) -> {
            carriageInputsContainer.getChildren().clear();
            for (int i = 0; i < newVal; i++) {
                carriageInputsContainer.getChildren().add(createCarriageInput(i + 1));
            }
        });

        // Initial carriage input
        carriageInputsContainer.getChildren().add(createCarriageInput(1));

        Label totalCostLabel = new Label("Total Cost: $0.00");
        Label remainingFundsLabel = new Label(String.format("Available Funds: $%.2f", controller.getCurrentMoney()));

        // Update total cost whenever carriage type or capacity changes
        Runnable updateCost = () -> {
            try {
                double totalCost = 0;
                for (Node node : carriageInputsContainer.getChildren()) {
                    if (node instanceof HBox hbox) {
                        ComboBox<CarriageType> typeBox = (ComboBox<CarriageType>) hbox.getChildren().get(1);
                        if (typeBox.getValue() != null) {
                            CarriageType type = typeBox.getValue();
                            // Cost per carriage type (capacity doesn't affect cost in this case)
                            totalCost += (type == CarriageType.Cargo) ? 500 : 1000;
                        }
                    }
                }
                totalCostLabel.setText(String.format("Total Cost: $%.2f", totalCost));
                remainingFundsLabel.setText(String.format("Available Funds: $%.2f", controller.getCurrentMoney() - totalCost));
            } catch (Exception ex) {
                totalCostLabel.setText("Total Cost: Error");
                remainingFundsLabel.setText("");
            }
        };

        // Add listeners to each carriage input to update cost dynamically
        carriageCountSpinner.valueProperty().addListener((obs, oldVal, newVal) -> updateCost.run());

        // Also update cost when carriage type changes
        carriageInputsContainer.getChildren().forEach(node -> {
            if (node instanceof HBox hbox) {
                ComboBox<CarriageType> typeBox = (ComboBox<CarriageType>) hbox.getChildren().get(1);
                typeBox.valueProperty().addListener((obs, oldVal, newVal) -> updateCost.run());
            }
        });

        // Wrap updating the listeners every time carriage inputs are recreated
        carriageCountSpinner.valueProperty().addListener((obs, oldVal, newVal) -> {
            carriageInputsContainer.getChildren().clear();
            for (int i = 0; i < newVal; i++) {
                HBox carriageInput = createCarriageInput(i + 1);
                // Add listener to this new typeBox
                ComboBox<CarriageType> typeBox = (ComboBox<CarriageType>) carriageInput.getChildren().get(1);
                typeBox.valueProperty().addListener((obs2, oldVal2, newVal2) -> updateCost.run());
                carriageInputsContainer.getChildren().add(carriageInput);
            }
            updateCost.run();
        });

        Button buildBtn = new Button("Build Train");
        buildBtn.setOnAction(e -> {
            try {
                Locomotive selectedLoco = locomotiveBox.getValue();
                if (selectedLoco == null)
                    throw new IllegalArgumentException("Select a locomotive.");

                ArrayList<Carriage> carriages = new ArrayList<>();
                double totalCost = 0;

                for (Node row : carriageInputsContainer.getChildren()) {
                    if (row instanceof HBox hbox) {
                        ComboBox<CarriageType> typeBox = (ComboBox<CarriageType>) hbox.getChildren().get(1);
                        Spinner<Double> capacitySpinner = (Spinner<Double>) hbox.getChildren().get(2);

                        if (typeBox.getValue() == null)
                            throw new IllegalArgumentException("Select a type for all carriages.");

                        CarriageType type = typeBox.getValue();
                        double carriageCost = (type == CarriageType.Cargo) ? 500 : 1000;
                        totalCost += carriageCost;

                        carriages.add(new Carriage(type, capacitySpinner.getValue()));
                    }
                }

                double availableFunds = controller.getCurrentMoney();
                if (totalCost > availableFunds) {
                    throw new IllegalArgumentException(
                            String.format("Insufficient funds! Train cost: $%.2f, Available: $%.2f", totalCost, availableFunds));
                }

                // Deduct cost from player money (You may want to create a method in your controller to do this)
                controller.deductMoney(totalCost);

                // Build the train (locomotive already owned)
                controller.buildTrain(selectedLoco, carriages);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Success");
                alert.setContentText(String.format("Train built successfully.\nTotal Cost: $%.2f\nRemaining Funds: $%.2f",
                        totalCost, controller.getCurrentMoney()));
                alert.showAndWait();
                popup.close();

            } catch (Exception ex) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText("Error");
                error.setContentText(ex.getMessage());
                error.showAndWait();
            }
        });

        root.getChildren().addAll(
                new Label("Select Locomotive:"), locomotiveBox,
                new Label("Number of Carriages (1–10):"), carriageCountSpinner,
                carriageInputsContainer,
                totalCostLabel,
                remainingFundsLabel,
                buildBtn
        );

        Scene scene = new Scene(root, 400, 600);
        popup.setScene(scene);
        popup.showAndWait();
    }


    private HBox createCarriageInput(int index) {
        HBox row = new HBox(10);
        row.setAlignment(Pos.CENTER_LEFT);

        Label label = new Label("Carriage " + index + ":");
        ComboBox<CarriageType> typeBox = new ComboBox<>();
        typeBox.getItems().addAll(CarriageType.values());

        Spinner<Double> capacitySpinner = new Spinner<>(10.0, 100.0, 50.0, 10.0);
        capacitySpinner.setEditable(true);

        row.getChildren().addAll(label, typeBox, capacitySpinner);
        return row;
    }

    private void showCreateRoutePopup() {
        Stage popupStage = new Stage();
        popupStage.setTitle("Create Route from Connections");
        popupStage.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows
        popupStage.setResizable(false);

        VBox layout = new VBox();
        layout.setPadding(new Insets(15));
        layout.setSpacing(10);
        layout.setAlignment(Pos.TOP_LEFT);

        Label title = new Label("Select Connections to Create Route:");

        ListView<RailwayLine> railwayLineListView = new ListView<>();
        railwayLineListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        railwayLineListView.setPrefHeight(300);

        List<RailwayLine> railwayLines = controller.getRailwayLines();
        railwayLineListView.getItems().addAll(railwayLines);

        railwayLineListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(RailwayLine item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getStationA().getName() + " ↔ " + item.getStationB().getName()
                            + " (" + item.getLineType() + ")");
                }
            }
        });

        Button createRouteBtn = new Button("Create Route");
        createRouteBtn.setDisable(true);

        railwayLineListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            createRouteBtn.setDisable(railwayLineListView.getSelectionModel().getSelectedItems().isEmpty());
        });

        createRouteBtn.setOnAction(e -> {
            List<RailwayLine> selectedLines = railwayLineListView.getSelectionModel().getSelectedItems();

            if (selectedLines.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select at least one connection.");
                alert.initOwner(popupStage);
                alert.showAndWait();
                return;
            }

            try {
                controller.createRouteFromSelectedLines(new ArrayList<>(selectedLines));
                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setTitle("Success");
                success.setHeaderText(null);
                success.setContentText("Route created successfully from selected connections.");
                success.initOwner(popupStage);
                success.showAndWait();
                popupStage.close(); // close popup after success
            } catch (Exception ex) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("Could not create route");
                error.setContentText(ex.getMessage());
                error.initOwner(popupStage);
                error.showAndWait();
            }
        });

        layout.getChildren().addAll(title, railwayLineListView, createRouteBtn);

        Scene scene = new Scene(layout, 450, 400);
        popupStage.setScene(scene);
        popupStage.showAndWait();
    }

    // Utility method for showing alerts
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void generateCargoForStations(List<Station> stations, Scenario scenario, GameController controller) {
        for (Station station : stations) {
            List<Industry> nearbyIndustries = controller.getNearbyIndustries(station);
            List<City> nearbyCities = controller.getNearbyCities(station);

            // --- INDUSTRY CARGO GENERATION ---
            for (Industry industry : nearbyIndustries) {
                double factor = industry.getIndustryGenerationFactor().getGenerationFactor();
                int cargoAmount = (int) Math.ceil(factor * scenario.getCargoBaseAmount());

                CargoType cargoType = null;
                if (industry instanceof PrimaryIndustry) {
                    ClassType.ResourceType output = ((PrimaryIndustry) industry).getOutput().getResourceType();
                    cargoType = CargoUtils.mapResourceToCargoType(output);
                } else if (industry instanceof TransformativeIndustry) {
                    ClassType.ResourceType output = ((TransformativeIndustry) industry).getOutput().getResourceType();
                    cargoType = CargoUtils.mapResourceToCargoType(output);
                }

                if (cargoType != null) {
                    for (int i = 0; i < cargoAmount; i++) {
                        station.addCargo(new Cargo(1, station, null, cargoType));
                    }
                }
            }

            // --- CITY (PASSENGER) CARGO GENERATION ---
            for (City city : nearbyCities) {
                double passengerFactor = city.getGenerationFactor();
                int passengerCount = (int) Math.ceil(passengerFactor * scenario.getPassengerBaseAmount());

                for (int i = 0; i < passengerCount; i++) {
                    station.addCargo(new Cargo(1, station, null, CargoType.Passenger));
                }
            }
        }
    }



}