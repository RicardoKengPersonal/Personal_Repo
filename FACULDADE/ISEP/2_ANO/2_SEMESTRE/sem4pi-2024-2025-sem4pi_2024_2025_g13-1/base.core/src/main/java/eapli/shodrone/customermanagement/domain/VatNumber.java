package eapli.shodrone.customermanagement.domain;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
public class VatNumber implements ValueObject, Comparable<VatNumber>, Serializable {

    private static final long serialVersionUID = 1L;

    private static final Pattern EU_VAT_PATTERN = Pattern.compile("^[A-Z]{2}[0-9A-Z]{8,12}$");

    private String number;

    protected VatNumber() {
        // for ORM
    }

    public VatNumber(final String number) {
        Preconditions.nonEmpty(number, "VAT number must not be empty");
        Preconditions.ensure(EU_VAT_PATTERN.matcher(number).matches(),
                "Invalid VAT number format. Must follow EU format (e.g., PT123456789)");
        this.number = number;
    }

    public String number() {
        return number;
    }

    @Override
    public int compareTo(final VatNumber other) {
        return this.number.compareTo(other.number);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof VatNumber)) return false;
        final VatNumber that = (VatNumber) o;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return number;
    }
}



