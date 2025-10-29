package eapli.shodrone.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address implements ValueObject {

    private String street;
    private String city;
    private String zipCode;

    protected Address() {
        // para JPA
    }

    public Address(String street, String city, String zipCode) {
        Preconditions.noneNull(street,city,zipCode);
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String street() {
        return street;
    }

    public String city() {
        return city;
    }

    public String zipCode() {
        return zipCode;
    }

    public static void validateCity(String s) {
        String[] letters = s.split("");
        for (String letter : letters) {
            if (!letter.matches("[a-zA-Z\\s]*")) {
                throw new IllegalArgumentException("Invalid name: not a valid city name.");
            }
        }
    }

    @Override
    public String toString() {
        return street + ", " + zipCode + ", " + city;
    }
}
