package pt.ipp.isep.dei.ui.gui.fx;

import javafx.scene.image.Image;
import pt.ipp.isep.dei.domain.ClassType.Building;
import pt.ipp.isep.dei.domain.ClassType.Ground;

import java.io.InputStream;
import java.util.EnumMap;

public class SpriteLoader {

    private static final EnumMap<Ground, Image> groundSprites = new EnumMap<>(Ground.class);
    private static final EnumMap<Building, Image> buildingSprites = new EnumMap<>(Building.class);

    private static Image trainLeftUp;
    private static Image trainLeft;
    private static Image trainRightUp;
    private static Image trainRight;
    private static Image playerAvatar;

    public static void loadSprites() {
        groundSprites.put(Ground.Grass, loadImage("grass.png"));

        buildingSprites.put(Building.City, loadImage("cityCenter.png"));
        buildingSprites.put(Building.House, loadImage("house.png"));
        buildingSprites.put(Building.Industry, loadImage("industry.png"));
        buildingSprites.put(Building.Depot, loadImage("depot.png"));
        buildingSprites.put(Building.Station, loadImage("station.png"));
        buildingSprites.put(Building.Terminal, loadImage("terminal.png"));

        // Load avatar and train images if needed
        playerAvatar = loadImage("player_icon.png");

    }

    private static Image loadImage(String fileName) {
        InputStream is = SpriteLoader.class.getResourceAsStream("/sprites/" + fileName);
        if (is == null) {
            System.err.println("Error: Image not found: /sprites/" + fileName);
            throw new RuntimeException("Image not found: /sprites/" + fileName);
        }
        return new Image(is);
    }

    public static Image getGroundSprite(Ground ground) {
        return groundSprites.get(ground);
    }

    public static Image getBuildingSprite(Building building) {
        return buildingSprites.get(building);
    }

    public static Image getTrainLeftUp() {
        return trainLeftUp;
    }

    public static Image getTrainLeft() {
        return trainLeft;
    }

    public static Image getTrainRightUp() {
        return trainRightUp;
    }

    public static Image getTrainRight() {
        return trainRight;
    }

    public static Image getPlayerAvatar() {
        return playerAvatar;
    }
}
