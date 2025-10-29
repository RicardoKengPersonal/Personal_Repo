package pt.ipp.isep.dei.repository;

import java.io.*;
import java.util.ArrayList;

import pt.ipp.isep.dei.domain.map.Map;

public class MapRepository {
    private final File baseFolder = new File("./data/maps/");
    private ArrayList<Map> maps;

    public MapRepository() {
        maps = new ArrayList<>();
        loadAllMaps();
    }

    public void add(String name, String description, double scale, int width, int height) {
        for (Map map : maps) {
            if (map.getName().equals(name)) {
                throw new IllegalArgumentException("There is already a map with that name!");
            }
        }
        Map map = new Map(name, description, scale, width, height);
        maps.add(map);
        saveMap(map);
    }

    public ArrayList<Map> getMapsList() {
        return new ArrayList<>(maps);
    }

    public Map getMapByName(String name) {
        for (Map map : maps) {
            if (map.getName().equals(name)) {
                return map;
            }
        }
        return null;
    }

    // --- Métodos para ficheiros ---

    public void saveMap(Map map) {
        if (!baseFolder.exists())
            baseFolder.mkdirs();
        String fileName = map.getName();
        File mapFile = new File(baseFolder, fileName);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(mapFile))) {
            out.writeObject(map);
        } catch (Exception e) {
            System.err.println("Error saving map: " + e.getMessage());
        }
    }

    public void loadAllMaps() {
        maps.clear();
        if (!baseFolder.exists())
            return;
        File[] files = baseFolder.listFiles((dir, name) -> name.endsWith(".ser"));
        if (files == null)
            return;
        for (File file : files) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                Map map = (Map) in.readObject();
                maps.add(map);
            } catch (Exception e) {
                System.err.println("Error loading map: " + file.getName() + " - " + e.getMessage());
            }
        }
    }

    public void deleteMap(Map selectedMap) {
        if (selectedMap == null)
            return;

        // Remover da lista em memória
        maps.removeIf(map -> map.getName().equals(selectedMap.getName()));

        // Apagar o ficheiro correspondente
        File mapFile = new File(baseFolder, selectedMap.getName());
        if (mapFile.exists()) {
            boolean deleted = mapFile.delete();
            if (!deleted) {
                System.err.println("Failed to delete file: " + mapFile.getAbsolutePath());
            }
        }
    }

}