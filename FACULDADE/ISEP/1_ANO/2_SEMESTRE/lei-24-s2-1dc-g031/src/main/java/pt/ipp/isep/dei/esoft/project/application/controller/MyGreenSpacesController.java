package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpacesRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * This class is responsible for controlling the operations related to the green spaces.
 * It interacts with the repositories to fetch and manipulate data.
 */
public class MyGreenSpacesController {

    private GreenSpacesRepository greenSpacesRepository;

    /**
     * Default constructor for the MyGreenSpacesController class.
     * Initializes the greenSpacesRepository.
     */
    public MyGreenSpacesController() {
        this.greenSpacesRepository = getGreenSpaceRepository();
    }

    /**
     * Returns the GreenSpacesRepository.
     * @return GreenSpacesRepository object.
     */
    private GreenSpacesRepository getGreenSpaceRepository() {
        if (greenSpacesRepository == null) {
            Repositories repositories = Repositories.getInstance();
            greenSpacesRepository = repositories.getInstance().getGreenSpaceRepository();
        }
        return greenSpacesRepository;
    }

    /**
     * Returns the green spaces of the current user.
     * @return ArrayList of GreenSpace objects.
     */
    public ArrayList<GreenSpace> getMyGreenSpaces() {
        String managerEmail = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().getEmail();
        ArrayList<GreenSpace> greenSpaces = greenSpacesRepository.getGreenSpaceByManagerEmail(managerEmail);

        // Read the sorting method from the config.properties file
        String sortingMethod = getSortingMethod();

        // Sort the list of green spaces using the specified sorting method
        if ("bubblesort".equals(sortingMethod)) {
            bubbleSort(greenSpaces);
        } else if ("selectionsort".equals(sortingMethod)) {
            selectionSort(greenSpaces);
        }

        return greenSpaces;
    }

    /**
     * Returns the sorting method from the config.properties file.
     * @return String representing the sorting method.
     */
    private String getSortingMethod() {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            prop.load(input);
            return prop.getProperty("sortingMethod");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Sorts the given list of green spaces using the bubble sort algorithm.
     * @param greenSpaces The list of green spaces to be sorted.
     */
    void bubbleSort(ArrayList<GreenSpace> greenSpaces) {
        int n = greenSpaces.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (greenSpaces.get(j).getGreenSpaceArea() < greenSpaces.get(j+1).getGreenSpaceArea()) {
                    // Swap greenSpaces[j+1] and greenSpaces[j]
                    GreenSpace temp = greenSpaces.get(j);
                    greenSpaces.set(j, greenSpaces.get(j+1));
                    greenSpaces.set(j+1, temp);
                }
            }
        }
    }

    /**
     * Sorts the given list of green spaces using the selection sort algorithm.
     * @param greenSpaces The list of green spaces to be sorted.
     */
    void selectionSort(ArrayList<GreenSpace> greenSpaces) {
        int n = greenSpaces.size();
        for (int i = 0; i < n-1; i++) {
            int min_idx = i;
            for (int j = i+1; j < n; j++) {
                if (greenSpaces.get(j).getGreenSpaceArea() > greenSpaces.get(min_idx).getGreenSpaceArea()) {
                    min_idx = j;
                }
            }
            // Swap greenSpaces[min_idx] and greenSpaces[i]
            GreenSpace temp = greenSpaces.get(min_idx);
            greenSpaces.set(min_idx, greenSpaces.get(i));
            greenSpaces.set(i, temp);
        }
    }
}