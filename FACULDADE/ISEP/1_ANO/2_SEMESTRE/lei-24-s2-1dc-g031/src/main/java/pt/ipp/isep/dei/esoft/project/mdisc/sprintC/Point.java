package pt.ipp.isep.dei.esoft.project.mdisc.sprintC;

public class Point {
    private String id;

    public Point(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point point = (Point) obj;
        return id.equals(point.id);
    }


    public int hashCode() {
        return id.hashCode();
    }
}
