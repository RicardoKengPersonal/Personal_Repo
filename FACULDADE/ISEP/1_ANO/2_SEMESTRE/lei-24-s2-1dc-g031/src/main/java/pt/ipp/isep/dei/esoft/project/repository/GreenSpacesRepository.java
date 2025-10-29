package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents a GreenSpacesRepository in the system.
 * It contains a list of GreenSpace objects and provides methods to add and retrieve green spaces.
 */
public class GreenSpacesRepository implements Serializable {

    private final ArrayList<GreenSpace> greenSpacesArrayList = new ArrayList<>();

    /**
     * Gets the list of green spaces.
     * @return The list of green spaces.
     */
    public ArrayList<GreenSpace> getGreenSpacesArrayList() {
        return greenSpacesArrayList;
    }

    /**
     * Checks if a green space already exists in the repository.
     * @param greenSpaceName The name of the green space.
     * @return true if the green space already exists, false otherwise.
     */
    private boolean greenSpaceAlreadyExist(String greenSpaceName) {
        for (GreenSpace greenSpace : greenSpacesArrayList) {
            if (greenSpace.getGreenSpaceName().equals(greenSpaceName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a green space to the repository.
     * @param greenSpaceName The name of the green space.
     * @param greenSpaceStreet The street of the green space.
     * @param greenSpaceType The type of the green space.
     * @param greenSpaceManagerEmail The email of the manager of the green space.
     * @param greenSpaceArea The area of the green space.
     * @return true if the green space was added successfully, false otherwise.
     */
    public boolean addGreenSpace(String greenSpaceName, String greenSpaceStreet, GreenSpaceType greenSpaceType, String greenSpaceManagerEmail, int greenSpaceArea) {
        GreenSpace greenSpace = new GreenSpace(greenSpaceName, greenSpaceStreet, greenSpaceType, greenSpaceManagerEmail, greenSpaceArea);
        if (!greenSpaceAlreadyExist(greenSpace.getGreenSpaceName())){
            greenSpacesArrayList.add(greenSpace);
            return true;
        }
        return false;
    }

    /**
     * Gets a list of green spaces managed by a given manager.
     * @param greenSpaceManagerEmail The email of the manager.
     * @return A list of green spaces managed by the given manager.
     */
    public ArrayList<GreenSpace> getGreenSpaceByManagerEmail(String greenSpaceManagerEmail) {
        ArrayList<GreenSpace> greenSpacesManaged = new ArrayList<GreenSpace>();

        for (GreenSpace greenSpace : greenSpacesArrayList) {
            if (greenSpace.getGreenSpaceManagerEmail().equals(greenSpaceManagerEmail)) {
                greenSpacesManaged.add(greenSpace);
            }
        }
        return greenSpacesManaged;
    }

}