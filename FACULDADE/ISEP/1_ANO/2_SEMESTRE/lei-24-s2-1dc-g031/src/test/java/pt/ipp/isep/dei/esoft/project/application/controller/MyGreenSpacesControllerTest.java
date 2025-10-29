package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.MyGreenSpacesController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpacesRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MyGreenSpacesControllerTest {
    private MyGreenSpacesController controller;
    private GreenSpacesRepository greenSpacesRepository;

    @BeforeEach
    void setUp() {
        controller = new MyGreenSpacesController();
        greenSpacesRepository = Repositories.getInstance().getGreenSpaceRepository();
    }

    @Test
    void testBubbleSort() {
        ArrayList<GreenSpace> greenSpaces = new ArrayList<>();
        greenSpaces.add(new GreenSpace("GreenSpace1", "Street1", GreenSpaceType.GARDEN, "email1@mail.com", 100));
        greenSpaces.add(new GreenSpace("GreenSpace2", "Street2", GreenSpaceType.GARDEN, "email2@mail.com", 200));
        controller.bubbleSort(greenSpaces);
        assertEquals(greenSpaces.get(0).getGreenSpaceArea(), 200);
        assertEquals(greenSpaces.get(1).getGreenSpaceArea(), 100);
    }

    @Test
    void testSelectionSort() {
        ArrayList<GreenSpace> greenSpaces = new ArrayList<>();
        greenSpaces.add(new GreenSpace("GreenSpace1", "Street1", GreenSpaceType.GARDEN, "email1@mail.com", 100));
        greenSpaces.add(new GreenSpace("GreenSpace2", "Street2", GreenSpaceType.GARDEN, "email2@mail.com", 200));
        controller.selectionSort(greenSpaces);
        assertEquals(greenSpaces.get(0).getGreenSpaceArea(), 200);
        assertEquals(greenSpaces.get(1).getGreenSpaceArea(), 100);
    }
}
