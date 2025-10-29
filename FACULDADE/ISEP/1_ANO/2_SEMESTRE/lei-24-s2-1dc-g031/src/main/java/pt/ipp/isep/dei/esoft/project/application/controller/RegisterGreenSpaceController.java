package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpacesRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

/**
 * This class is responsible for controlling the operations related to the registration of green spaces.
 * It interacts with the repositories to fetch and manipulate data.
 */
public class RegisterGreenSpaceController {

    private GreenSpacesRepository greenSpaceRepository;
    private AuthenticationRepository authenticationRepository;

    /**
     * Default constructor for the RegisterGreenSpaceController class.
     * Initializes the greenSpaceRepository and authenticationRepository.
     */
    public RegisterGreenSpaceController() {
        this.greenSpaceRepository = getGreenSpaceRepository();
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    /**
     * Returns the GreenSpacesRepository.
     * @return GreenSpacesRepository object.
     */
    private GreenSpacesRepository getGreenSpaceRepository() {
        if (greenSpaceRepository == null) {
            Repositories repositories = Repositories.getInstance();
            greenSpaceRepository = repositories.getInstance().getGreenSpaceRepository();
        }
        return greenSpaceRepository;
    }

    /**
     * Creates a new green space and adds it to the repository.
     * @param greenSpaceName The name of the green space.
     * @param greenSpaceStreet The street of the green space.
     * @param greenSpaceType The type of the green space.
     * @param greenSpaceSize The size of the green space.
     * @return true if the operation was successful, false otherwise.
     */
    public boolean createGreenSpace(String greenSpaceName, String greenSpaceStreet, GreenSpaceType greenSpaceType, String greenSpaceSize) {
        String greenSpaceManagerEmail = this.authenticationRepository.getCurrentUserSession().getUserId().getEmail();
        return greenSpaceRepository.addGreenSpace(greenSpaceName, greenSpaceStreet, greenSpaceType, greenSpaceManagerEmail, Integer.parseInt(greenSpaceSize));
    }
}