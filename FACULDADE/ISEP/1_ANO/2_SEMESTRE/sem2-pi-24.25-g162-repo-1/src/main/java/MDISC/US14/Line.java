package MDISC.US14;

public class Line {
    private int begin; //int begin e int end serão os id's das estações em causa
    private int end;
    private boolean isElectrified;

    public Line(int begin, int end, boolean isElectrified) {
        this.begin = begin;
        this.end = end;
        this.isElectrified = isElectrified;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    public boolean isElectrified() {
        return isElectrified;
    }
}
