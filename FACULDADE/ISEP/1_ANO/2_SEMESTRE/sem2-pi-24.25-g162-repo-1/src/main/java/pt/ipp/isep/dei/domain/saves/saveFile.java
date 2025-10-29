package pt.ipp.isep.dei.domain.saves;

import pt.ipp.isep.dei.domain.game.CurrentGame;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class saveFile {

    private static final String SAVE_DIR = "saves";

    public static void saveGame(CurrentGame game, String fileName) throws IOException {
        File dir = new File(SAVE_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // Garante extensão .dat
        if (!fileName.toLowerCase().endsWith(".dat")) {
            fileName += ".dat";
        }
        File file = new File(dir, fileName);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(game);
        }
    }

    public static CurrentGame loadGame(String filePath) throws IOException, ClassNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("Save file not found: " + filePath);
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (CurrentGame) ois.readObject();
        }
    }
}
