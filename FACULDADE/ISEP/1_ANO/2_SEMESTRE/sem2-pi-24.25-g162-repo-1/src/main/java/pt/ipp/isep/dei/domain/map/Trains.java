package pt.ipp.isep.dei.domain.map;

import java.io.Serializable;
import java.util.ArrayList;

import pt.ipp.isep.dei.domain.objects.Locomotive;
import pt.ipp.isep.dei.domain.objects.Train;

public class Trains implements Serializable {

    private ArrayList<Train> trains;

    public Trains() {
        this.trains = new ArrayList<>();
    }

    public void createTrain(Locomotive locomotive) {
        trains.add(new Train(locomotive));
    }

    public Train getTrainByName(String name) {
        for (Train train : trains) {
            if (train.getTrainName().equals(name)) {
                return train;
            }
        }
        return null;
    }

    public ArrayList<Train> getAllTrains() {
        return new ArrayList<>(trains);
    }
}
