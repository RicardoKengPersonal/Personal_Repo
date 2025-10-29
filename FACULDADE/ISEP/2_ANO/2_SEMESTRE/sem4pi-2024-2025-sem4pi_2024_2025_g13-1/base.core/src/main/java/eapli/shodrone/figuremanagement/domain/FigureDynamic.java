package eapli.shodrone.figuremanagement.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class FigureDynamic {

    private String dynamicData;

    protected FigureDynamic() {
        // for ORM
    }

    public FigureDynamic(String dynamicData) {
        if (dynamicData == null || dynamicData.isBlank())
            throw new IllegalArgumentException("FigureDynamic data must not be null or blank");

        String trimmed = dynamicData.trim().toLowerCase();
        if (!trimmed.equals("yes") && !trimmed.equals("no"))
            throw new IllegalArgumentException("FigureDynamic must be 'yes' or 'no'");

        this.dynamicData = trimmed;
    }

    public String value() {
        return dynamicData;
    }

    @Override
    public String toString() {
        return dynamicData;
    }
}
