package eapli.shodrone.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;


@Embeddable
@EqualsAndHashCode
@ToString
public class CustomerID implements ValueObject, Comparable<CustomerID>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "customer_id")
    private Integer id;

    protected CustomerID() {
        // for ORM
    }

    public CustomerID(Integer id) {
        if (id == null)
            throw new IllegalArgumentException("Figure ID must not be null");
        this.id = id;
    }

    public static CustomerID valueOf(final Integer id) {
        return new CustomerID(id);
    }

    public Integer toInteger() {
        return id;
    }

    @Override
    public int compareTo(CustomerID other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
