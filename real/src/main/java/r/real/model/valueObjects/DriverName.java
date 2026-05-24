package r.real.model.valueObjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import r.real.model.base.ValueObject;

import static org.apache.commons.lang3.Validate.*;

@Embeddable
public class DriverName implements ValueObject {

    @Column(name = "driver_name", nullable = false)
    private String value;

    public DriverName(String value) {
        notNull(value, "Driver name must not be null");
        matchesPattern(value, "^[A-Za-z .'-]{2,100}$",
                "Driver name must be 2-100 characters and contain only letters, spaces, apostrophes, dots or hyphens");
        this.value = value;
    }

    protected DriverName() {
    }

    public String getValue() {
        return value;
    }
}