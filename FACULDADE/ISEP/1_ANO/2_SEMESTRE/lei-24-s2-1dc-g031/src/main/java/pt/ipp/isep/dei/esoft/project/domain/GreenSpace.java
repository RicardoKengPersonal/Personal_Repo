package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

/**
 * This class represents a GreenSpace in the system.
 * It contains information about the green space such as name, street, type, manager's email, and area.
 */
public class GreenSpace implements Serializable {
    private String greenSpaceName;
    private String greenSpaceStreet;
    private GreenSpaceType greenSpaceType;
    private String greenSpaceManagerEmail;
    private int greenSpaceArea;

    /**
     * Constructor for the GreenSpace class.
     * @param greenSpaceName The name of the green space.
     * @param greenSpaceStreet The street of the green space.
     * @param greenSpaceType The type of the green space.
     * @param greenSpaceManagerEmail The manager's email of the green space.
     * @param greenSpaceArea The area of the green space.
     */
    public GreenSpace(String greenSpaceName, String greenSpaceStreet, GreenSpaceType greenSpaceType, String greenSpaceManagerEmail , int greenSpaceArea) {
        this.greenSpaceName = greenSpaceName;
        this.greenSpaceStreet = greenSpaceStreet;
        this.greenSpaceType = greenSpaceType;
        this.greenSpaceManagerEmail = greenSpaceManagerEmail;
        this.greenSpaceArea = greenSpaceArea;
    }

    /**
     * Gets the name of the green space.
     * @return The name of the green space.
     */
    public String getGreenSpaceName() {
        return greenSpaceName;
    }

    /**
     * Sets the name of the green space.
     * @param greenSpaceName The new name of the green space.
     */
    public void setGreenSpaceName(String greenSpaceName) {
        this.greenSpaceName = greenSpaceName;
    }

    /**
     * Gets the street of the green space.
     * @return The street of the green space.
     */
    public String getGreenSpaceStreet() {
        return greenSpaceStreet;
    }

    /**
     * Sets the street of the green space.
     * @param greenSpaceStreet The new street of the green space.
     */
    public void setGreenSpaceStreet(String greenSpaceStreet) {
        this.greenSpaceStreet = greenSpaceStreet;
    }

    /**
     * Gets the manager's email of the green space.
     * @return The manager's email of the green space.
     */
    public String getGreenSpaceManagerEmail() {
        return greenSpaceManagerEmail;
    }

    /**
     * Sets the manager's email of the green space.
     * @param greenSpaceManagerEmail The new manager's email of the green space.
     */
    public void setGreenSpaceManagerEmail(String greenSpaceManagerEmail) {
        this.greenSpaceManagerEmail = greenSpaceManagerEmail;
    }

    /**
     * Gets the area of the green space.
     * @return The area of the green space.
     */
    public int getGreenSpaceArea() {
        return greenSpaceArea;
    }

    /**
     * Sets the area of the green space.
     * @param greenSpaceArea The new area of the green space.
     */
    public void setGreenSpaceArea(int greenSpaceArea) {
        this.greenSpaceArea = greenSpaceArea;
    }

    /**
     * Returns a string representation of the green space.
     * @return A string representation of the green space.
     */
    @Override
    public String toString() {
        return this.greenSpaceName + " - " + this.greenSpaceStreet + " - " + this.greenSpaceType + " - " + this.greenSpaceArea;
    }
}