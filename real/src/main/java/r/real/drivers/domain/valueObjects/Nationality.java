package r.real.drivers.domain.valueObjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import r.real.common.domain.base.ValueObject;

import static org.apache.commons.lang3.Validate.*;

@Embeddable
public class Nationality implements ValueObject {

    @Column(name = "nationality", nullable = false)
    private String value;

    public Nationality(String value) {
        notNull(value, "Nationality must not be null");
        matchesPattern(value, "^[A-Za-z -]{2,60}$",
                "Nationality must be 2-60 alphabetic characters");
        this.value = value;
    }

    protected Nationality() { }

    public String getValue() {
        return value;
    }
}