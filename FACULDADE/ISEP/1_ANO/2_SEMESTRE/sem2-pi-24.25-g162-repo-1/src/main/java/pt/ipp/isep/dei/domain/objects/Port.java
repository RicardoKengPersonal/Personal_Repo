package pt.ipp.isep.dei.domain.objects;

import java.util.List;

import pt.ipp.isep.dei.domain.ClassType;
import pt.ipp.isep.dei.domain.ClassType.PortBehaviour;
import pt.ipp.isep.dei.domain.concept.Cargo;

public class Port {

    private String name;
    private int x;
    private int y;
    private List<Cargo> cargo;
    private PortBehaviour portBehaviour;

    public Port(String name, int x, int y, List<Cargo> cargo, PortBehaviour portBehaviour) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.cargo = cargo;
        this.portBehaviour = portBehaviour;
    }

    public String getName() {
        return this.name;
    }
}