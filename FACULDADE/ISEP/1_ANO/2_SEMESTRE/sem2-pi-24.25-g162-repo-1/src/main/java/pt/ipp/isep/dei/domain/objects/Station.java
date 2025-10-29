package pt.ipp.isep.dei.domain.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pt.ipp.isep.dei.domain.ClassType;
import pt.ipp.isep.dei.domain.ClassType.Orientation;
import pt.ipp.isep.dei.domain.ClassType.StationType;
import pt.ipp.isep.dei.domain.concept.Cargo;
import pt.ipp.isep.dei.domain.concept.Coordinates;

public class Station implements Serializable {
    private final String name;
    private final Coordinates coordinates;
    private final StationType stationType;
    private final Orientation orientation;
    private ArrayList<BuildingUpgrade> upgrades;
    private ArrayList<Train> trains;
    private ArrayList<Cargo> cargoList;
    private final List<Cargo> availableCargoList = new ArrayList<>();


    public Station(String name, Coordinates coordinates, StationType stationType, Orientation orientation) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Station name cannot be null or empty.");
        }
        this.name = name;
        this.coordinates = coordinates;
        this.stationType = stationType;
        this.orientation = orientation;
        this.upgrades = new ArrayList<>();
        this.trains = new ArrayList<>();
        this.cargoList = new ArrayList<>();
    }

    public ArrayList<BuildingUpgrade> getBuildingUpgrades() {
        return upgrades;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public StationType getStationType() {
        return stationType;
    }

    public ArrayList<Train> getAllTrains() {
        return trains;
    }

    public Train getTrainByName(String name) {
        for (Train train : trains) {
            if (train.getTrainName().equals(name)) {
                return train;
            }
        }
        return null;
    }

    public void addTrain(Train train) {
        this.trains.add(train);
    }

    public void addUpgrade(BuildingUpgrade newUpgrade) {
        BuildingUpgrade replaced = newUpgrade.getReplaces();
        if (replaced != null) {
            for (BuildingUpgrade upgrade : upgrades) {
                if (upgrade.equals(replaced)) {
                    upgrades.remove(replaced);
                    upgrades.add(newUpgrade);
                }
            }
        } else {
            upgrades.add(newUpgrade);
        }
    }

    public String toString() {
        return String.format("Name: %s", name);
    }

    public String getDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("Station: ").append(name).append("\n");
        sb.append("Type: ").append(stationType).append("\n");
        sb.append("Coordinates: ").append(coordinates).append("\n");
        sb.append("Orientation: ").append(orientation).append("\n");

        sb.append("Upgrades: ");
        if (upgrades == null || upgrades.isEmpty()) {
            sb.append("None\n");
        } else {
            for (BuildingUpgrade upgrade : upgrades) {
                sb.append(upgrade.getName()).append(" ");
            }
            sb.append("\n");
        }

        sb.append("Trains: ");
        if (trains == null || trains.isEmpty()) {
            sb.append("None\n");
        } else {
            for (Train train : trains) {
                sb.append(train.getTrainName()).append(" ");
            }
            sb.append("\n");
        }

        sb.append("Cargo list: ");
        if (cargoList == null || cargoList.isEmpty()) {
            sb.append("None\n");
        } else {
            for (Cargo cargo : cargoList) {
                sb.append(cargo.toString()).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public ArrayList<Cargo> getCargoList() {
        return cargoList;
    }

    public void receiveCargo(ArrayList<Cargo> cargos) {
        int i, j;
        for (i = 0; i < cargoList.size(); i++) {
            for (j = 0; j < cargos.size(); j++) {
                if (cargoList.get(i).getCargoType().equals(cargos.get(j).getCargoType())) {
                    cargoList.get(i).setQuantity(cargos.get(j).getQuantity());
                }
            }
        }
    }

    public int getRadius() {
        return stationType.getRadius();
    }

    public List<Industry> getNearbyIndustries(List<Industry> allIndustries) {
        return allIndustries.stream()
                .filter(industry -> this.getCoordinates().distanceTo(industry.getCoordinates()) <= this.getStationType().getRadius())
                .toList();
    }
    public boolean hasNearbyCity(List<City> cities) {
        return cities.stream()
                .anyMatch(city -> this.coordinates.distanceTo(city.getCoordinates()) <= this.getRadius());
    }

    public void removeTrain(Train train) {
        this.trains.remove(train);
    }

    public boolean hasTrain(Train train) {
        return this.trains.contains(train);
    }


    public void addCargo(Cargo cargo) {
        availableCargoList.add(cargo);
    }

    public List<Cargo> getAvailableCargoList() {
        return availableCargoList;
    }

    public void removeCargo(Cargo cargo) {
        availableCargoList.remove(cargo);
    }
    public void updateCargoQuantity(ClassType.CargoType type, double newQuantity) {
        for (Cargo cargo : availableCargoList) {
            if (cargo.getCargoType() == type) {
                cargo.setQuantity(newQuantity);
                return;
            }
        }
    }

}
