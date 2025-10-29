package eapli.shodrone.shodroneusermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@EqualsAndHashCode
public class PhoneNumber implements ValueObject, Comparable<PhoneNumber> {

    private static final long serialVersionUID = 1L;

    private String number;

    public PhoneNumber(final String phoneNumber) {
        if (StringPredicates.isNullOrEmpty(phoneNumber)) {
            throw new IllegalArgumentException(
                    " Number should neither be null nor empty");
        }
        // TODO validate invariants, i.e., mecanographic number regular
        // expression
        this.number = phoneNumber;
    }

    protected PhoneNumber() {
        // for ORM
    }

    public static PhoneNumber valueOf(final String phoneNumeber) {
        return new PhoneNumber(phoneNumeber);
    }

    @Override
    public String toString() {
        return this.number;
    }

    @Override
    public int compareTo(final PhoneNumber arg0) {
        return number.compareTo(arg0.number);
    }
}

