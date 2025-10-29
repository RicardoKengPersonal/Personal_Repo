package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * This enum represents the types of identification documents a Collaborator can have.
 * It contains methods for validating document numbers based on the type of document.
 */
public enum DocumentType implements Serializable {
    PASSPORT,         // Represents a passport document type
    ID_CARD,          // Represents an ID card document type
    DRIVERS_LICENSE;   // Represents a driver's license document type
}