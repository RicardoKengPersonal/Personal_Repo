package pt.ipp.isep.dei.repository;

import java.io.File;
import java.io.FileInputStream;

import pt.ipp.isep.dei.domain.game.CurrentGame;
import pt.isep.lei.esoft.auth.UserSession;

import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;

public class GamesRepository {
    private final File baseFolder = new File("./data/saves/");

    public void saveGame(UserSession userSession, CurrentGame currentGame, String fileName) throws Exception {
        File userFolder = new File(baseFolder + userSession.getUserName() + "/");
        if (!userFolder.exists()) {
            userFolder.mkdirs();
        }
        if (!fileName.endsWith(".ser")) {
            fileName += ".ser";
        }
        File saveFile = new File(userFolder, fileName);
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(saveFile))) {
            out.writeObject(currentGame);
        } catch (Exception e) {
            throw new Exception("It was not possible to save the game: " + e);
        }
    }

    public boolean isUserFolderEmpty(UserSession userSession) {
        File userFolder = new File(baseFolder + userSession.getUserName() + "/");
        String[] files = userFolder.list((dir, name) -> name.endsWith(".ser"));
        return files == null || files.length == 0;
    }

    public String[] listSavedGames(UserSession userSession) {
        File userFolder = new File(baseFolder + userSession.getUserName() + "/");
        if (!userFolder.exists() || !userFolder.isDirectory()) {
            return new String[0];
        }
        return userFolder.list((dir, name) -> name.endsWith(".ser"));
    }

    public CurrentGame loadGame(UserSession userSession, String fileName) throws Exception {
        File userFolder = new File(baseFolder + userSession.getUserName() + "/");
        File saveFile = new File(userFolder, fileName);
        if (!saveFile.exists()) {
            throw new Exception("Save file does not exist: " + saveFile.getAbsolutePath());
        }
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(saveFile))) {
            return (CurrentGame) in.readObject();
        } catch (Exception e) {
            throw new Exception("It wasn't possible to load this file: " + e);
        }
    }
}
