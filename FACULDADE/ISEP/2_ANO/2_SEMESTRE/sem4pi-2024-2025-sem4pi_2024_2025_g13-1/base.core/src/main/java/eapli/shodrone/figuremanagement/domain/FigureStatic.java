package eapli.shodrone.figuremanagement.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class FigureStatic {

    private String staticData;

    protected FigureStatic() {
        // for ORM
    }

    public FigureStatic(String staticData) {
        if (staticData == null || staticData.isBlank())
            throw new IllegalArgumentException("FigureStatic data must not be null or blank");

        String trimmed = staticData.trim().toLowerCase();
        if (!trimmed.equals("yes") && !trimmed.equals("no"))
            throw new IllegalArgumentException("FigureStatic must be 'yes' or 'no'");

        this.staticData = trimmed;
    }

    public String value() {
        return staticData;
    }

    @Override
    public String toString() {
        return staticData;
    }
}
