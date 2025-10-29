package eapli.shodrone.showrequestmanagement.domain;

import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ShowRequestDescription {
    private String description;

    protected ShowRequestDescription() {
    }

    public ShowRequestDescription(final String description) {
        if (description == null || description.isBlank())
            throw new IllegalArgumentException("Description must not be blank");
        this.description = description;
    }

    public String description() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShowRequestDescription)) return false;
        ShowRequestDescription that = (ShowRequestDescription) o;
        return description.equalsIgnoreCase(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description.toLowerCase());
    }

    public String getdesc() {
        return description;
    }
}
