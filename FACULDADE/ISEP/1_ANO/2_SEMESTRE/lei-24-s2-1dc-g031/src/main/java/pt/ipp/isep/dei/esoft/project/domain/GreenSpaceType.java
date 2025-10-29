package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

/**
 * This enum represents the types of green spaces in the system.
 * It contains methods for converting a string to a GreenSpaceType.
 */
public enum GreenSpaceType implements Serializable {
    GARDEN,             // Represents a garden green space type
    MEDIUM_SIZED_PARK,  // Represents a medium-sized park green space type
    LARGE_SIZED_PARK;   // Represents a large-sized park green space type

    /**
     * Converts a string to a GreenSpaceType.
     * @param text The string to be converted.
     * @return The corresponding GreenSpaceType.
     * @throws IllegalArgumentException if no GreenSpaceType with the given text is found.
     */
    public static GreenSpaceType fromString(String text) {
        for (GreenSpaceType type : GreenSpaceType.values()) {
            if (type.name().equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}