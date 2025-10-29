package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpacesRepository;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GreenSpacesRepositoryTest {
    private GreenSpacesRepository repository;

    @BeforeEach
    void setUp() {
        repository = new GreenSpacesRepository();
    }

    @Test
    void testAddGreenSpace() {
        assertTrue(repository.addGreenSpace("GreenSpace1", "Street1", GreenSpaceType.GARDEN, "email1@mail.com", 100));
        assertEquals(1, repository.getGreenSpacesArrayList().size());
    }

    @Test
    void testAddGreenSpace_AlreadyExists() {
        repository.addGreenSpace("GreenSpace1", "Street1", GreenSpaceType.GARDEN, "email1@mail.com", 100);
        assertFalse(repository.addGreenSpace("GreenSpace1", "Street1", GreenSpaceType.GARDEN, "email1@mail.com", 100));
        assertEquals(1, repository.getGreenSpacesArrayList().size());
    }

    @Test
    void testGetGreenSpaceByManagerEmail() {
        repository.addGreenSpace("GreenSpace1", "Street1", GreenSpaceType.GARDEN, "email1@mail.com", 100);
        ArrayList<GreenSpace> greenSpaces = repository.getGreenSpaceByManagerEmail("email1@mail.com");
        assertEquals(1, greenSpaces.size());
    }
}