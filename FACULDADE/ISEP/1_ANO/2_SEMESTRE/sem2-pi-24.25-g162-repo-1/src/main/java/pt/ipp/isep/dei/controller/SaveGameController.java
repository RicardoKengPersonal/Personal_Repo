package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.game.CurrentGame;
import pt.ipp.isep.dei.domain.saves.saveFile;

import java.io.File;
import java.io.IOException;

public class SaveGameController {

    public void saveGame(CurrentGame game, String fileName) throws IOException {
        // Sempre salva na pasta "saves" e garante extensão .dat
        if (!fileName.toLowerCase().endsWith(".dat")) {
            fileName += ".dat";
        }
        saveFile.saveGame(game, fileName);
    }

    public CurrentGame loadGame(String fileName) throws IOException, ClassNotFoundException {
        // Sempre carrega da pasta "saves" e garante extensão .dat
        if (!fileName.toLowerCase().endsWith(".dat")) {
            fileName += ".dat";
        }
        File file = new File(System.getProperty("user.dir") + File.separator + "saves", fileName);
        return saveFile.loadGame(file.getAbsolutePath());
    }

    public String[] listSavedGames() {
        File saveDir = new File(System.getProperty("user.dir"), "saves");
        if (saveDir.exists() && saveDir.isDirectory()) {
            return saveDir.list((dir, name) -> name.toLowerCase().endsWith(".dat"));
        }
        return new String[0];
    }
}
