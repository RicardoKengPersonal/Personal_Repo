package pt.ipp.isep.dei.domain.game;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import pt.ipp.isep.dei.domain.ClassType;
import pt.ipp.isep.dei.domain.scenario.Scenario;
import pt.ipp.isep.dei.interfaces.GameObjectProvider;
import pt.ipp.isep.dei.domain.ClassType.ResourceType;
import pt.ipp.isep.dei.domain.concept.Cargo;
import pt.ipp.isep.dei.domain.concept.Coordinates;
import pt.ipp.isep.dei.domain.objects.Carriage;
import pt.ipp.isep.dei.domain.objects.City;
import pt.ipp.isep.dei.domain.objects.Industry;
import pt.ipp.isep.dei.domain.objects.Locomotive;
import pt.ipp.isep.dei.domain.objects.RailwayLine;
import pt.ipp.isep.dei.domain.objects.Station;
import pt.ipp.isep.dei.domain.objects.Train;

public class GameEngine {
    private final Timeline timeline;
    private boolean running = false;
    private GameObjectProvider provider;

    public GameEngine() {
        timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> update()));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void setObjectProvider(GameObjectProvider provider) {
        this.provider = provider;
    }

    public void start() {
        running = true;
        timeline.play();
    }

    public void pause() {
        timeline.pause();
    }

    public void stop() {
        running = false;
        timeline.stop();
    }

    public void update() {
        if (!running)
            return;
    }

    // Outras lógicas de simulação (tempo, eventos), sem dependência direta do jogo

}
