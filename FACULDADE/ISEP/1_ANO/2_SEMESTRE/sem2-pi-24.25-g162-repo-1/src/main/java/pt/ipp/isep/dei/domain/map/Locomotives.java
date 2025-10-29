package pt.ipp.isep.dei.domain.map;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import pt.ipp.isep.dei.domain.concept.LocomotiveCatalog;
import pt.ipp.isep.dei.domain.objects.Locomotive;

public class Locomotives implements Serializable {

    private LocomotiveCatalog catalog;
    private ArrayList<Locomotive> locomotives;

    public Locomotives() throws IOException {
        this.locomotives = new ArrayList<>();
        this.catalog = new LocomotiveCatalog();
        locomotives = catalog.getLocomotiveCatalog();
    }

    public Locomotive getLocomotiveByName(String name) throws Exception {
        for (Locomotive locomotive : locomotives) {
            if (locomotive.getName().equals(name)) {
                return locomotive;
            }
        }
        throw new Exception("There is no locomotive with that name!");
    }

    public ArrayList<Locomotive> getAllLocomotives() {
        return new ArrayList<>(locomotives);
    }

    public void removeLocomotive(Locomotive locomotive) {
        locomotives.remove(locomotive);
    }
}
