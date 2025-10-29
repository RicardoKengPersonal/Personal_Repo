package eapli.shodrone.showproposalmanagement.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShowProposalIDTest {
    @Test
    void validIdCreationShouldSucceed() {
        ShowProposalID id = new ShowProposalID(5);
        assertNotNull(id);
        assertEquals(5, id.toInteger());
    }

    @Test
    void staticValueOfShouldReturnSameValue() {
        ShowProposalID id = ShowProposalID.valueOf(10);
        assertEquals(10, id.toInteger());
    }

    @Test
    void toIntegerShouldReturnCorrectValue() {
        ShowProposalID id = new ShowProposalID(7);
        assertEquals(7, id.toInteger());
    }

    @Test
    void toStringShouldReturnStringRepresentation() {
        ShowProposalID id = new ShowProposalID(12);
        assertEquals("12", id.toString());
    }

    @Test
    void compareToShouldSortCorrectly() {
        ShowProposalID id1 = new ShowProposalID(1);
        ShowProposalID id2 = new ShowProposalID(2);
        assertTrue(id1.compareTo(id2) < 0);
    }

    @Test
    void equalsAndHashCodeShouldBeCorrect() {
        ShowProposalID id1 = new ShowProposalID(3);
        ShowProposalID id2 = new ShowProposalID(3);
        ShowProposalID id3 = new ShowProposalID(4);

        assertEquals(id1, id2);
        assertNotEquals(id1, id3);
        assertEquals(id1.hashCode(), id2.hashCode());
    }

    @Test
    void nullIdShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new ShowProposalID(null));
    }

}