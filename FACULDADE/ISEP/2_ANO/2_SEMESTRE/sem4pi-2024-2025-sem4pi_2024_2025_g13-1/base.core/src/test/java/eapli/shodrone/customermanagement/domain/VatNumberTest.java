package eapli.shodrone.customermanagement.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class VatNumberTest {
    @Test
    void ensureValidVatNumberIsAccepted() {
        VatNumber vat = new VatNumber("PT123456789");
        assertEquals("PT123456789", vat.number());
    }

    @Test
    void ensureNullVatNumberIsRejected() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new VatNumber(null));
        assertTrue(exception.getMessage().contains("must not be empty"));
    }

    @Test
    void ensureEmptyVatNumberIsRejected() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new VatNumber(""));
        assertTrue(exception.getMessage().contains("must not be empty"));
    }

    @Test
    void ensureInvalidFormatIsRejected() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new VatNumber("123456789"));
        assertTrue(exception.getMessage().contains("Invalid VAT number format"));
    }

    @Test
    void ensureLowerCasePrefixIsRejected() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new VatNumber("pt123456789"));
        assertTrue(exception.getMessage().contains("Invalid VAT number format"));
    }

    @Test
    void ensureEqualsWorks() {
        VatNumber vat1 = new VatNumber("PT123456789");
        VatNumber vat2 = new VatNumber("PT123456789");
        assertEquals(vat1, vat2);
    }

    @Test
    void ensureHashCodeIsConsistent() {
        VatNumber vat1 = new VatNumber("PT123456789");
        VatNumber vat2 = new VatNumber("PT123456789");
        assertEquals(vat1.hashCode(), vat2.hashCode());
    }

    @Test
    void ensureCompareToWorks() {
        VatNumber vat1 = new VatNumber("PT123456789");
        VatNumber vat2 = new VatNumber("PT223456789");
        assertTrue(vat1.compareTo(vat2) < 0);
    }

    @Test
    void ensureToStringReturnsCorrectValue() {
        VatNumber vat = new VatNumber("PT123456789");
        assertEquals("PT123456789", vat.toString());
    }

}